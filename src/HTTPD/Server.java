package HTTPD;

import Core.Scripter;
import java.util.Map;


public class Server extends NanoHTTPD {

    public Server(int port) {
        super(port);
    }

    public Server(String hostname, int port) {
        super(hostname, port);
    }

    
    @Override
    public Response serve(String uri, Method method, Map<String, String> header, Map<String, String> parms, Map<String, String> files) {
        String response="";
    
        if(uri.contains("evaluate")){
            String caseName= parms.get("case");
            String languageName= parms.get("language");

            String filename= parms.get("filename");
            String content= parms.get("content");
            response+=Scripter.execute(caseName, languageName, filename, content);;
        }else
        if(uri.contains("cases")){
            
            for( String casestr: Scripter.getCases()){
                response+=casestr+"\n";            
            }
        }else
        if(uri.contains("languages")){
            String caseName= parms.get("case");
            for( String lang: Scripter.getLanguages(caseName) ){
                response+=lang+"\n";            
            }
        }
        
        return new Response(response);
    
    }
    

    
}
