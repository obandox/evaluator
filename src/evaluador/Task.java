package evaluador;


import Core.Scripter;
import Lib.Util;
import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;
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
    private static String localhost="http://obando.game-host.org/";
    
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
            Map data=parser.parseJson(resp);
            
            for(Object key_o : data.keySet()){
                if(data.get(key_o) instanceof Map){
                    Map problema=(Map) data.get(key_o);
                    String caseName=problema.get("title").toString();
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
            String host="http://sistema.joincic.com.ve/programas/validar?host="+Task.localhost;
            String resp= Util.readUrl(host);
            
            System.out.println(resp);
        } catch (Exception ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
