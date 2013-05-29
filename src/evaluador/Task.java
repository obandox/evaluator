package evaluador;


import Core.Scripter;
import Lib.Util;
import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author miguel
 */
public class Task implements Runnable{

    public static final int refreshCaseTime=3600;
    public static final int validateTime=60;
    public static  String SALT="J0iNcIC";
    
    public int nextCase=0;
    public int nextValidate=0;
    
    
    @Override
    public void run() {
        
        
        while(true){
            
            if(nextCase<=0){
                
        try {
                refreshCase();
        }catch(Exception e){ e.printStackTrace(); }
                nextCase=refreshCaseTime;
            }
            if(nextValidate<=0){
        try {
                validate();
        }catch(Exception e){ e.printStackTrace(); }
                nextValidate=validateTime;
            }
            
            nextCase-=1;
            nextValidate-=1;
            try {  Thread.sleep(1000); } catch (InterruptedException ex) {}
        }
        
        
    }

    public void refreshCase() {
        try {
            String host="http://sistema.joincic.com.ve/problemas.json";
            String resp= Util.readUrl(host);
            System.out.println(resp);
            JsonParserFactory factory=JsonParserFactory.getInstance();
            JSONParser parser=factory.newJsonParser();
            ArrayList data=(ArrayList) parser.parseJson(resp).get("root");
            
            for(Object map_o : data){
                if(map_o instanceof Map){
                    Map problema=(Map) map_o;
                    String caseName=problema.get("titulo").toString();
                    String language="java\nc++\n";
                    String input=problema.get("entradas").toString();
                    String output=problema.get("salidas").toString();
                    Scripter.makeCase(caseName, language, input, output);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
    }
    
    
    public void validate() {
        try {
            
            String host="http://sistema.joincic.com.ve/programas/listar";
            String resp= Util.readUrl(host);
            System.out.println(resp);
            JsonParserFactory factory=JsonParserFactory.getInstance();
            JSONParser parser=factory.newJsonParser();
            ArrayList data=(ArrayList) parser.parseJson(resp).get("root");
            for(Object map_o : data){
                if(map_o instanceof Map){
                    Map p=(Map) map_o;
                    String id=p.get("id").toString();
                    String language=p.get("language").toString();
                    String caseName=p.get("case").toString();
                    String filename=p.get("filename").toString();
                    String content=Util.readUrl("http://sistema.joincic.com.ve/programas/"+id+"/serve_privado?salt="+SALT);
                    String respuesta= Scripter.execute(caseName, language, filename, content);
                    String[] data2=respuesta.split("\n");
                    if(respuesta.contains("SUCCESS")){
                             Util.readUrl("http://sistema.joincic.com.ve/programas/validar?id="+id+"&estado=correcto&salt="+SALT);
                    }else{
                             Util.readUrl("http://sistema.joincic.com.ve/programas/validar?id="+id+"&estado="+data2[0]+"&salt="+SALT);
                    }
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
