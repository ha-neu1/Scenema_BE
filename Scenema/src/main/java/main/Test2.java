package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
 
 
public class Test2 {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
 
        String urlPath = "https://www.kmdb.or.kr/db/kor/detail/movie/F/57556/own/videoData";
        String pageContents = "";
        StringBuilder contents = new StringBuilder();
 
        try{
 
            URL url = new URL(urlPath);
            URLConnection con = (URLConnection)url.openConnection();
            InputStreamReader reader = new InputStreamReader (con.getInputStream(), "utf-8");
 
            BufferedReader buff = new BufferedReader(reader);
 
            while((pageContents = buff.readLine())!=null){
            	if(pageContents.contains("fcnPlay")&&pageContents.contains("\'")) {
            		String link = pageContents.strip();
            		link = link.substring(link.indexOf("\'")+1, link.lastIndexOf("\'"));
	                contents.append(link+"|");
            	}
            }
 
            buff.close();
            
            
            System.out.println(contents.toString());
 
        }catch(Exception e){
            e.printStackTrace();
        }
 
    }
 
}