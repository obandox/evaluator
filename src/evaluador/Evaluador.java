/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluador;

import HTTPD.Server;
import Lib.Util;
import View.Gui;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class Evaluador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
            int port = 7770;
            
            for(String host : Util.locals()){
                Server server = new Server(host, port);
                try{
                    server.start();
                }catch(IOException unused){}                
            }
            Gui gui = new Gui();
            gui.setVisible(true);
            gui.setTitle("EVALUATOR");
            gui.setLocationRelativeTo(null);
           
    }
}
