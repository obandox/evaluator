/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Lib.Util;
import View.Gui;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class Scripter {
    
    public static final int SUCCESS=0;
    public static final int COMPILER_ERROR=1000;
    public static final int RUNTIME_ERROR=2000;
    public static final int TIMELIMIT_ERROR=3000;
    public static final int UNKNOWN_ERROR=4000;
    
    public static String CASES = "./cases/";
    public static String FILES="./files/";
    
    
    public static String[] getCases(){
        return Util.listDirs(CASES);
    }
    
    public static String[] getLanguages(String casename){
    
        return Util.list(CASES+casename+"/languages");
    }
    
  
    public static void makeCase(String casename, String languages, String input, String output){
        Util.mkdir(CASES+casename);
        Util.write(CASES+casename+"/languages", languages);
        Util.write(CASES+casename+"/input", input);
        Util.write(CASES+casename+"/output", output);
    }
    
    public static String execute(String casename, String language, String filename, String content){
        Gui.print("CASE: "+casename);
        Gui.print("LANG: "+language);
        Gui.print("FILE: "+filename);
        
        
        long timestamp = System.currentTimeMillis()/1000;
        String folder= "temp"+timestamp+"/";
        Util.mkdir(FILES+folder);
        String pwd=FILES+folder;
        
        Gui.print("PWD: "+pwd);
        long initMillis = System.currentTimeMillis();
        String result= _execute(pwd,casename, language, filename, content);
        long endMillis = System.currentTimeMillis();
        String millis= String.valueOf(endMillis - initMillis);
        
        
        Gui.print("RESULT: "+result);
        Gui.print("TIME MILLIS: "+ millis );
        
        return result+"\n"+millis;
    }
    
    public static String _execute(String pwd,String casename, String language, String filename, String content){
        
        String inputFile=pwd+"input";
        String scriptFile=pwd+filename;
        String shFile=pwd+language+".sh";
       
        String[] arr= filename.split("\\.");
        String name= arr[0];
        
        Util.write(scriptFile, content);
        
        Util.copy(CASES+casename+"/input",inputFile);
        Util.copy(language+".sh",shFile);
        try {
            Util.exec("chmod +x "+shFile);
        } catch (TimeoutException ex) {
        }
        
        
        String out;
        try {
            out = Util.exec(shFile+" "+pwd+" "+filename+" "+name).trim();
            
            String realOut = Util.read(CASES + casename + "/output").trim();
                        
            if (out.equals(realOut)) {
                return "SUCCESS";
            }

            if (out.contains("COMPILER_ERROR")) {
                return "COMPILER_ERROR";
            }
            if (out.contains("RUNTIME_ERROR")) {
                return "RUNTIME_ERROR";
            }
            if (out.contains("TIMELIMIT_ERROR")) {
                return "TIMELIMIT_ERROR";
            }
        
            
        } catch (TimeoutException ex) {
            return "TIMELIMIT_ERROR";
        }
        
        return "UNKNOWN_ERROR";
    }
    
    
}
