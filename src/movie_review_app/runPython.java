package movie_review_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class runPython {

    public static String showMSG(String X){
        
        String s = null;

        try {
            
            String Str=X;
            String com = "python \"C:\\Users\\Sanjay Jindal\\Documents\\movie_review_app\\src\\movie_review_app\\Classify.py\" \""+Str+"\"";
            Process p = Runtime.getRuntime().exec(com);
            System.out.println(com);
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            while ((s = stdInput.readLine()) != null) {
                return s;
            }
            
            //System.exit(0);
        }
        catch (IOException e) {
           
        }
        return "0";
    }
}