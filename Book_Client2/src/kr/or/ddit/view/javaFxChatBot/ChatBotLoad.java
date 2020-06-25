package kr.or.ddit.view.javaFxChatBot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatBotLoad extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("chatBot.fxml"));
		Scene scene = new Scene(parent);
		
		primaryStage.setTitle("챗봇 창입니다.");
		primaryStage.setScene(scene); 																												
		primaryStage.show();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
