
package Listing31_01;

import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
/**
 *
 * @author jm0015052
 */
public class Server extends Application{
    @Override
    public void start(Stage primaryStage){
        // text area for display contents
        
        TextArea ta = new TextArea();
        
        // CREATE A SCENE
        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        
        primaryStage.setTitle("server");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        new Thread(()-> {
            try{
                // create server socket
                ServerSocket serverSocket = new ServerSocket(5000);
                Platform.runLater(()->
                    ta.appendText("Server started " + new Date() + "\n"));
                // listen for conn request
                Socket socket = serverSocket.accept();
                // create data input / output streams
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
                
                while(true){
                    // receive radius from client
                    double radius = inputFromClient.readDouble();
                    
                    // compute area
                    double area = radius * radius * Math.PI;
                    
                    //send area back
                    outputToClient.writeDouble(area);
                    
                    Platform.runLater(()->{
                        ta.appendText("Radius received from client " + radius + "\n");
                    });
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }).start();
    }
    
    public static void main(String args[]){
        launch(args);
    }
}
