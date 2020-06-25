package kr.or.ddit.view.mypage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.mypage.IMyPagePointService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.PointCategoryVO;

public class mypointPageController implements Initializable {

	@FXML
	ImageView check;
	@FXML
	ImageView img2;
	@FXML
	Button pointBtn;
	@FXML
	ImageView logo;
	@FXML
	JFXTextArea priceText;
	@FXML
	JFXTextArea pointAllText;
	@FXML
	JFXButton backBtn;   //뒤로가기 버튼
	

	private ObservableList<PointCategoryVO> data;
	IMyPagePointService service;
	private Registry reg;
	Stage stage;
	//String allPoint = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		pointAllText.setStyle("-fx-alignment: center;");

		Image image1 = new Image("file:src/images/check2.png");
		check.setImage(image1);

		Image image2 = new Image("file:src/images/kakaopay.png");
		img2.setImage(image2);

		Image image3 = new Image("file:src/images/logo.png");
		logo.setImage(image3);
	
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IMyPagePointService) reg.lookup("myPagePointService");
			System.out.println("rmi성공");
		} catch (Exception e) {
			System.out.println("lookup오류");
			e.printStackTrace();
		}
		
		try {
			//pointAllText.setText(service.selectAllPoint(Session.loginUser.getMemNum()));
			String sum1 = service.selectAllPoint(Session.loginUser.getMemNum());
			String sum2 = service.selectAllPointTrans(Session.loginUser.getMemNum());
			if(sum1 == null) {
				sum1 = "0"; 
			}
			if(sum2 == null) {
				sum2 = "0";
			}
			
			int hap = Integer.parseInt(sum1) - Integer.parseInt(sum2); 
			pointAllText.setText(Integer.toString(hap));
			
		} catch (RemoteException e3) {
			e3.printStackTrace();
		}
		
		
		pointBtn.setOnAction(e -> {

			Alert infoAlert = new Alert(AlertType.INFORMATION);
			infoAlert.setTitle("KakaoPay 결제");
			infoAlert.setHeaderText("신중하게 결정하십시오");
			infoAlert.setContentText("진짜 결제 하시겠습니까? (한도:1,000,000원)");

			Optional<ButtonType> result = infoAlert.showAndWait();
			if (result.get() == ButtonType.OK) {
				
				PointCategoryVO p = new PointCategoryVO();
				
				p.setNowPoint(priceText.getText());
				DataFlow.p = p;
				DataFlow.p.setMemNum(Session.loginUser.getMemNum());

				data = FXCollections.observableArrayList(p);

				int cnt = 0;
				
				try {
					cnt = service.insertNowPoint(p);
					
					if (cnt == 1) {
						System.out.println("성공");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			} 
			
//			Stage stage = (Stage) pointBtn.getScene().getWindow();
//			stage.close();

			
			Stage dialog = new Stage(StageStyle.UTILITY);

			// 2.모달창 여부 설정
			// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
			dialog.initModality(Modality.APPLICATION_MODAL);

			dialog.setTitle("kakaopay");

			// 4. 자식창에 나타날 컨테이너객체 생성
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("kakaopay.fxml"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			// 5. Scene 객체 생성해서 컨테이너 객체 추가
			Scene scene = new Scene(root);
			//dialog.initOwner(pointText.getScene().getWindow());
			// 6. Stage객체에 Scene 객체 추가
			dialog.setScene(scene);
			dialog.setResizable(false); // 크기 고정
			dialog.show();
			
		
		});
		
		
		backBtn.setOnAction(e ->{
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/mypage/myPageMain.fxml"));
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
