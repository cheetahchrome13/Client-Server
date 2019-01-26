
package Listing31_04;

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
public class MultiThreadServer extends Application{
    // Text area for displatying
    private TextArea ta = new TextArea();
    
    private int clientNo = 0;
    
    @Override
    public void start(Stage primaryStage){
        // text area for display contents
        
        TextArea ta = new TextArea();
        
        // CREATE A SCENE
        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        
        primaryStage.setTitle("MultiThreadServer");
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }
}
