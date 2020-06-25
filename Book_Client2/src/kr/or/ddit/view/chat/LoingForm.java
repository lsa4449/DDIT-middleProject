package kr.or.ddit.view.chat;
	
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

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
import kr.or.ddit.session.Session;


public class LoingForm extends Application  {

    @FXML
    TextField serverField;
    @FXML
    TextField nickNameField;
    @FXML
    Button ok_btn;
    
    @FXML
    Button okok_btn;
    
    //관리자와의 채팅
    @FXML
   	public void clickOKOK(ActionEvent event){
    	String nickName = Session.loginUser.getNickName(); //nickNameField.getText();
		String serverIP = "192.168.206.44"; //serverField.getText(); //수아 ip
//		nickName = "koji";
//		serverIP = "127.0.0.1";
		System.out.println(nickName);
		System.out.println(serverIP);
		
		MainForm.userName = nickName;
		
		// 
		
		try {
			//소켓연결
			MainForm.socket = new Socket(serverIP, 5001);
			System.out.println("소켓연결");
		} 
		catch (ConnectException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("IP 다시확인");
			alert.setContentText("서버와 연결되지 않았습니다!");
			alert.showAndWait();
			
			//ok_btn버튼의 scene의 stage를 객체에 담고
			Stage primaryStage = (Stage)ok_btn.getScene().getWindow();
			
			//그 stage에 mainForm의 scene을 띄운다.
			primaryStage.setScene(MainForm.scene);
			
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		if(MainForm.socket != null)
		{
//			System.exit(0);
			Stage primaryStage = (Stage)ok_btn.getScene().getWindow();
			primaryStage.setScene(MainForm.scene);
			
			try {
				MainForm.oos = new ObjectOutputStream(MainForm.socket.getOutputStream());
				MainForm.ois = new ObjectInputStream(MainForm.socket.getInputStream());
				MainForm.oos.writeObject(nickName); //닉네임 전송
				MainForm.mnameptr.setText(nickName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//수신용 쓰레드 생성 및 시행 
			Thread receiveThread = new Thread(new MainForm());
			receiveThread.start();
		}
    }
    
    
    @FXML
	public void clickLogin(ActionEvent event){ //확인
    	
		String nickName = Session.loginUser.getNickName(); //nickNameField.getText();
		String serverIP = "192.168.206.57"; //serverField.getText(); //윤희누나 ip  원랜 localhost
//		nickName = "koji";
//		serverIP = "127.0.0.1";
		System.out.println(nickName);
		System.out.println(serverIP);
		
		MainForm.userName = nickName;
		
		// 
		
		try {
			//소켓연결
			MainForm.socket = new Socket(serverIP, 5001);
			System.out.println("소켓연결");
		} 
		catch (ConnectException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("IP 다시확인");
			alert.setContentText("서버와 연결되지 않았습니다!");
			alert.showAndWait();
			
			//ok_btn버튼의 scene의 stage를 객체에 담고
			Stage primaryStage = (Stage)ok_btn.getScene().getWindow();
			
			//그 stage에 mainForm의 scene을 띄운다.
			primaryStage.setScene(MainForm.scene);
			
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		if(MainForm.socket != null)
		{
//			System.exit(0);
			Stage primaryStage = (Stage)ok_btn.getScene().getWindow();
			primaryStage.setScene(MainForm.scene);
			
			try {
				MainForm.oos = new ObjectOutputStream(MainForm.socket.getOutputStream());
				MainForm.ois = new ObjectInputStream(MainForm.socket.getInputStream());
				MainForm.oos.writeObject(nickName); //닉네임 전송
				MainForm.mnameptr.setText(nickName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//수신용 쓰레드 생성 및 시행 
			Thread receiveThread = new Thread(new MainForm());
			receiveThread.start();
		}
    }
    
    @Override
	public void start(Stage primaryStage) {
    	System.out.println("어ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ");
		try {
			AnchorPane login = (AnchorPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene =new Scene(login);
			primaryStage.setTitle("로그인창");
			primaryStage.setScene(scene);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
}