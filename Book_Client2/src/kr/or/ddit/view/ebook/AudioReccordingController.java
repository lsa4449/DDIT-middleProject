package kr.or.ddit.view.ebook;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class AudioReccordingController implements Initializable{
	JavaSoundRecorder javaSoundRecorder;
    @FXML
    private Button start_btn;

@Override
public void initialize(URL location, ResourceBundle resources) {
	
	
	 
     start_btn.setText("Start");
     
     start_btn.setOnAction((ActionEvent event) -> {        
         if (start_btn.getText().equals("Start")) {  
             javaSoundRecorder  = new JavaSoundRecorder();
             Thread thread = new Thread(javaSoundRecorder);
             thread.start();

             start_btn.setText("Stop");
         }
         else {
             javaSoundRecorder.finish();
             javaSoundRecorder.cancel();

             start_btn.setText("Start");                
         }
     });
	
}
}
