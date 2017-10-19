/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_review_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sanjay Jindal
 */
public class ReTrain extends Thread{
    public void run(){
        try {
            
            System.out.println("Creating new Sets");

            String CreateSets = "python \"C:\\Users\\Sanjay Jindal\\Documents\\movie_review_app\\src\\movie_review_app\\CreateSets.py\"";
            Process p = Runtime.getRuntime().exec(CreateSets);
            System.out.println("Created new Sets");
            
            
            BufferedReader stdInput = new BufferedReader(new
                         InputStreamReader(p.getInputStream()));
            String s;
            
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            
            System.out.println("Training Classifiers");

            String Train = "python \"C:\\Users\\Sanjay Jindal\\Documents\\movie_review_app\\src\\movie_review_app\\TrainClassifiers.py\"";
            Process q = Runtime.getRuntime().exec(Train);
            System.out.println("Trained Classifiers");
            
            stdInput = new BufferedReader(new
                         InputStreamReader(q.getInputStream())); 
            
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
