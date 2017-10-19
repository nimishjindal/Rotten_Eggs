package movie_review_app;

import java.awt.*;
import javafx.scene.image.Image ;
import java.io.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.IOException;
import javax.swing.*;


public class Movie_review_app extends Application{
    
    int rating = 0;
    int pos=0,neg=0,total=0;
    String Senti;
    @Override
    public void start(Stage myStage) {

        String Movie = "Interstellar";
                
        myStage.setTitle("Rotten Eggs");
        
        Container p = new Container();

        GridPane rootNode = new GridPane();
        rootNode.setPadding(new Insets(15));
        rootNode.setHgap(10);
        rootNode.setVgap(20);
        rootNode.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(rootNode, 800,700);

        TextField Comment = new TextField("Enter your review here");
        Label result = new Label("");
        Label AllComments = new Label("");
        Label AllSenti = new Label("");
        Button Submit = new Button("submit");


        Image img = new Image("File:C:\\Users\\Sanjay Jindal\\Documents\\movie_review_app\\src\\movie_review_app\\Posters\\"+Movie+".jpg");
        ImageView pic = new ImageView();
        pic.setFitWidth(200);
        pic.setFitHeight(200);
        pic.setImage(img);

        Comment.setPrefWidth(600); 
        
        //String Reviews = new String();
        //String Senti = new String();
        
        String[] res = getReviews(Movie);
        AllComments.setText(res[0]);
        AllSenti.setText(res[1]);
                
        Label Heading = new Label("");
        Heading.setFont( new Font("Serif",40) );
        GridPane.setHalignment(Heading, HPos.CENTER);
        
        Heading.setText(updateSenti(Movie));

        Button YES = new Button ("Yes");
        Button NO = new Button ("No");
        Button CANCEL = new Button("Cancel");
        
        HBox box = new HBox();
        box.getChildren().addAll(YES,new Text(" "),NO,new Text(" "),CANCEL);
       
        
        rootNode.add(pic,1,0);
        rootNode.add(Heading,0,0);        
        rootNode.add(Comment, 0, 1);
        rootNode.add(Submit, 1, 1);
        rootNode.add(result, 0, 2);
        rootNode.add(AllComments,0,3);
        rootNode.add(AllSenti,1,3);        
        
        
        Submit.setOnAction((ActionEvent e) -> {
            result.setText("Processing your review");
            String x = Comment.getText();
            String res1 = runPython.showMSG(x);
            String[] Res = res1.split("\\s+");
            //String sentiment = Res[1];
            Senti = Res[1];
            String conf = Res[0];
            result.setText("Your review is likely to be "+Senti+". Is this correct?");
            
            rootNode.add(box,1,2);
/*            
            AddNewReview.NewRev(x,Res[1],Movie);
            
            String[] resin = getReviews(Movie);
            String Old = AllComments.getText();
            Old+=(x+"\n");
            String Cat = AllSenti.getText();
            Cat+=(Senti+"\n");
            AllComments.setText(Old);
            AllSenti.setText(Cat);
            
            calcRating(Senti);
            Heading.setText(updateSenti(Movie));
*/
        });
      

        
        
        YES.setOnAction((ActionEvent e) -> {

            String x = Comment.getText();

            AddNewReview.NewRev(x,Senti,Movie);
            
            String Old = AllComments.getText();
            Old+=(x+"\n");
            String Cat = AllSenti.getText();
            Cat+=(Senti+"\n");
            AllComments.setText(Old);
            AllSenti.setText(Cat);
            
            calcRating(Senti);
            Heading.setText(updateSenti(Movie));
            
            rootNode.getChildren().remove(box);
            result.setText("");

            
        });

        NO.setOnAction((ActionEvent e) -> {
            
            String x = Comment.getText();
            
            if(Senti.equals("Negative"))
                Senti = "Positive";
            else
                Senti = "Negative";
            
            AddNewReview.NewRev(x,Senti,Movie);
            
            new ReTrain().start();
            
            String Old = AllComments.getText();
            Old+=(x+"\n");
            String Cat = AllSenti.getText();
            Cat+=(Senti+"\n");
            AllComments.setText(Old);
            AllSenti.setText(Cat);
            
            calcRating(Senti);
            Heading.setText(updateSenti(Movie));
            
            rootNode.getChildren().remove(box);
            result.setText("");

        });
        
       CANCEL.setOnAction((ActionEvent e) -> {
            rootNode.getChildren().remove(box);
            result.setText("");

       });
         
        myStage.setScene(myScene);

        myStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public String updateSenti(String Movie){
        
        String Ttl = new String();
        Ttl += Movie;
        if(total!=0)
            Ttl += ("\nrating: "+rating+"/5");     
        else
            Ttl += ("\n(No reviews yet)");     
        return Ttl;
    }
    
    public void calcRating(String decide){
        
        if(decide.equals("Negative"))
            neg++;
        else
            pos++;
        
        total++;
        if(total!=0)
            rating = (pos*5)/total;
         
    }
    
    public String[] getReviews(String Movie){
       
        int num = 0;
        int total = 0;
        
        String PosFile = "C:\\Users\\Sanjay Jindal\\Documents\\movie_review_app\\src\\movie_review_app\\DataSets\\NewReviews\\"+Movie+"\\review.pos";
        String NegFile = "C:\\Users\\Sanjay Jindal\\Documents\\movie_review_app\\src\\movie_review_app\\DataSets\\NewReviews\\"+Movie+"\\review.neg";

        String Reviews = new String();
        String Senti = new String();

        try (BufferedReader br = new BufferedReader(new FileReader(PosFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.print(line);
                Reviews += line;
                Reviews += "\n";
                Senti += "Positive\n";
                calcRating("Positive");
            }
        }catch(Exception e){
            
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(NegFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.print(line);
                Reviews += line;
                Reviews += "\n";
                Senti += "Negative\n";
                calcRating("Negative");
            }
        }
        catch(Exception e){
            
        }
        if(total!=0){
            rating = (num/total)*5;
        }
        return new String[]{Reviews,Senti};

   }
}