package kr.or.ddit.view.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class testLoader extends Application{
@Override
public void start(Stage primaryStage) throws Exception {
	
	Parent root = FXMLLoader.load(getClass().getResource("bookManage.fxml"));
	
	Scene scene = new Scene(root);
	primaryStage.setTitle("test창");
	primaryStage.setScene(scene);
	primaryStage.show();
	
}
public static void main(String[] args) {
	launch(args);
}
}
