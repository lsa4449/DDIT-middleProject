package kr.or.ddit.view.chat;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kr.or.ddit.vochat.CreateRoomData;

public class CreateRoomForm extends Application{
	
	static Scene scene;
	@FXML
    TextField roomName_txtfield; //방이름 텍스트 필드

    @FXML
    Button createOK_btn; //확인버튼
    
    @FXML
    Button createCancle_btn; //취소버튼
    
    @FXML
    public void CreateRoom(ActionEvent event) //방만들기 확인 
    {
    	String roomName = roomName_txtfield.getText();
    	
    	if(roomName.isEmpty()) //만약 방이름이 공백이면 
    	{
    		Alert alert = new Alert(AlertType.WARNING); //에러창 띄움
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("방이름 확인");
			alert.setContentText("방이름을 입력해주십시오!");
			alert.showAndWait();
    	}
    	else //만약 방이름이 잘 들어갔다면 
    	{
    		CreateRoomData createdRoom = new CreateRoomData(roomName);
    		try {
				MainForm.oos.writeObject(createdRoom);
			} catch (IOException e) {
				e.printStackTrace();
			}
    		Stage primaryStage = (Stage)createCancle_btn.getScene().getWindow(); //메인화면으로 이동
    		primaryStage.setScene(MainForm.scene);
    		// 방만들기 버튼 -> 나가기버튼 변경  
    	}
    }
    @FXML
    public void CacleCreateRoom(ActionEvent event) //방만들기 취소 
    {
    	MainForm.createRoomPtr.setText("방만들기");
		Stage primaryStage = (Stage)createCancle_btn.getScene().getWindow();
		primaryStage.setScene(MainForm.scene);
    }
    @Override
	public void start(Stage primaryStage)
    {
    	
    	try
    	{
			AnchorPane createRoom = (AnchorPane)FXMLLoader.load(getClass().getResource("CreateRoom.fxml"));
			Scene scene =new Scene(createRoom);
			primaryStage.setTitle("방만들기");
			primaryStage.setScene(scene);
		} 
    	catch(Exception e) 
    	{
			e.printStackTrace();
		}
	}
}
