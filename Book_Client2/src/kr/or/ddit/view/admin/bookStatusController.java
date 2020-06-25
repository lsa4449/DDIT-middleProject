package kr.or.ddit.view.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;

public class bookStatusController implements Initializable {
	
		Stage stage;


	    @FXML
	    private Button catagory_btn;

	    @FXML
	    private Button age_btn;

	    @FXML
	    private Button BookStatus_btn;

	    @FXML
	    private ImageView logo;

	    @FXML
	    private PieChart pieChart;
	    
	    ObservableList<Data> list;


		@FXML JFXButton backBtn;   //뒤로가기버튼
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//로고 클릭하면 메인화면으로 이동
		logo.setOnMouseClicked(e->{
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("adminMain.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(root);
			
			stage = (Stage)logo.getScene().getWindow();
			stage.setTitle("회원 관리");
			stage.setScene(scene);
		});
		
		BookStatus_btn.setOnMouseClicked(e->{
			 list = FXCollections.observableArrayList(
		            new PieChart.Data("대여중", 50),    
		            new PieChart.Data("보유중", 20),
		            new PieChart.Data("연체중", 30),
		            new PieChart.Data("반납 예정", 10)
		            );
		        pieChart.setData(list);
		});
		
		age_btn.setOnMouseClicked(e->{
			 list = FXCollections.observableArrayList(
			            new PieChart.Data("10대", 50),    
			            new PieChart.Data("20대", 20),
			            new PieChart.Data("30대", 30),
			            new PieChart.Data("40대", 10)
			            
			            );
			        pieChart.setData(list);
		});
		
		catagory_btn.setOnMouseClicked(e->{
			 list = FXCollections.observableArrayList(
			            new PieChart.Data("문화", 50),    
			            new PieChart.Data("예술", 20) ,
			            new PieChart.Data("자기계발", 30),
			            new PieChart.Data("소설", 10),
			            new PieChart.Data("과학", 10),
			            new PieChart.Data("전자책", 10)
			            
			            );
			        pieChart.setData(list);
		});
		 


		backBtn.setOnAction(e ->{
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/admin/bookManage.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Scene scene = new Scene(root);
			Stage stage = (Stage) logo.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		});
	



	}
}
