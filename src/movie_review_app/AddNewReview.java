package movie_review_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddNewReview {
    /*public static void main(String[] args) {
        ADD("haha","pos","inter");
    }
      */      
    public static void NewRev(String X, String Senti,String Movie){
        
        String s = null;

        try {
            
            String cat;
            
            if(Senti.equals("Positive"))
                cat = "pos";
            else
                cat = "neg";
            
            X += "\n";
            
            String com = "python \"C:\\Users\\Sanjay Jindal\\Documents\\movie_review_app\\src\\movie_review_app\\AddNewReview.py\" \""+X+"\" "+cat+" "+Movie;
            System.out.println(com);

            Process p = Runtime.getRuntime().exec(com);
            //System.out.println(com);
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            
            //System.exit(0);
            
        }
        catch (IOException e) {
           System.out.println(e);
        }
        
        //print("0";
    }
}
