
package Listing31_02;
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author jm0015052
 */
public class Client extends Application {
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    
    @Override
    public void start(Stage primaryStage){
        // panel for te4xt field
        BorderPane paneForTextField = new BorderPane();
        paneForTextField.setPadding(new Insets(5,5,5,5));
        paneForTextField.setStyle("-fx-border-color: green");
        paneForTextField.setLeft(new Label("Enter a radius"));
        
        TextField tf = new TextField();
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField.setCenter(tf);
        
        BorderPane mainPane = new BorderPane();
        
        TextArea ta = new TextArea();
        mainPane.setCenter(new ScrollPane(ta));
        mainPane.setTop(paneForTextField);
        
        Scene scene = new Scene(mainPane, 450, 200);
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        tf.setOnAction(e-> {
            try{
                // get radius
                double radius = Double.parseDouble(tf.getText().trim());
                // send to server
                toServer.writeDouble(radius);// change here for chat
                toServer.flush();
                // get area from server
                double area = fromServer.readDouble();
                
                // display
                ta.appendText("Radius is " + radius + "\n");
                ta.appendText("Area received from the server is " + area + "\n");
            }//try
            catch(IOException ex){
                System.err.println(ex);
            }//catch
        });// end anon method
        try{
            // create socket
            Socket socket = new Socket("localhost", 5000);
            // new Socket("130.254.204.36", 8000);
            // new Socket("drake.armstrong.edu", 8000);
            
            //create input stream top receive data from server
            fromServer = new DataInputStream(socket.getInputStream());
            
            // create an output stream
            toServer = new DataOutputStream(socket.getOutputStream());
                        
        }//end try
        catch(IOException ex){
            ta.appendText(ex.toString() + "\n");
        }//catch
    }  
    
    public static void main(String args[]){
        launch();
    }
}
