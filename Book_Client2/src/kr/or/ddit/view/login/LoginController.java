package kr.or.ddit.view.login;

import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.login.ILoginService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.join.captcha.CaptchaImage;
import kr.or.ddit.view.join.captcha.CaptchaKey;
import kr.or.ddit.view.main.controller;
import kr.or.ddit.vo.MemberVO;

public class LoginController implements Initializable{

	private controller con; 
	
	public controller getController() {
		return this.con;
	}
	
	public void setController(controller con) {
		this.con = con;
	}
	
	@FXML
	private JFXTextField textId;
	@FXML
	private JFXPasswordField textPw;
	@FXML
	private JFXButton btnLogin;
	@FXML
	private JFXButton btnKakaoLogin;
	@FXML
	private JFXButton btnsearch;
	@FXML
	private JFXButton btnJoin;
	
	private Registry reg;
	private ILoginService service;
	static int cnt = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (ILoginService) reg.lookup("loginService");
			System.out.println("RMI 성공");
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		btnLogin.setOnAction(e ->{
			MemberVO vo = new MemberVO();
			
			vo.setId(textId.getText());
			vo.setPw(textPw.getText());
			
			MemberVO vo2 = new MemberVO(); // 로그인 비교를 위한 값 저장 db에서 가져오기
			
			vo2 = null;
			try {
				vo2 = service.selectLoginMember(vo);
			} catch (RemoteException e1) {
				
				e1.printStackTrace();
			}
			
			
			if(vo2 != null ) {
				if(vo2.getAuthority().equals("1")) {
				System.out.println("로그인에 성공했습니다.");
				
				// 부모창 다시 띄우기
			
				 Parent root;
					Session.loginUser = vo2;
					Session.loginUser.getName();
				//	con.name();
		         Stage stage = (Stage)btnLogin.getScene().getWindow();
		         stage.close();
				}else {

						Session.loginUser = vo2;
						Session.loginUser.getName();
						
					Stage dialog = new Stage(StageStyle.UTILITY);

					// 2.모달창 여부 설정
					// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다 -> 벗 나는 쓸꺼얌
					dialog.initModality(Modality.NONE);

					dialog.setTitle("admin");

					// 4. 자식창에 나타날 컨테이너객체 생성
					Parent parent = null;
					try {
						parent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/admin/adminMain.fxml"));
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					// 5. Scene 객체 생성해서 컨테이너 객체 추가
					Scene scene = new Scene(parent);
					 Stage stage = (Stage)btnLogin.getScene().getWindow();
			         stage.close();
					// 6. Stage객체에 Scene 객체 추가
					dialog.setScene(scene);
					dialog.setResizable(false); // 크기 고정
					dialog.show();
					
					
				}
		         
		       
	
			}else {
				ssi("작업 오류", "틀리셨습니다. 3회 이상 틀리실 경우 캡차를 입력해야 합니다.");
				cnt++;	
				System.out.println(cnt);
				if(cnt == 3) {
					msg("작업 오류", "3회 틀리셨습니다.");
					
				}
			}
			

			if(cnt == 3) {
				msg("작업 오류", "3회 틀리셨습니다.");
			}

			if(cnt > 3) {
				msg("작업 오류", cnt + "회 틀리셨습니다.");
				
				Stage stage = (Stage)btnLogin.getScene().getWindow();
		         stage.close();
		         
		         Stage stageLoginCapcha = new Stage();
		         try {
					Parent parent = FXMLLoader.load(getClass().getResource("loginChapcha.fxml"));
					Scene scene = new Scene(parent);
					stageLoginCapcha.initModality(Modality.APPLICATION_MODAL);
					stageLoginCapcha.initStyle(StageStyle.UTILITY);
					stageLoginCapcha.setScene(scene);
					stageLoginCapcha.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		
			
		btnsearch.setOnAction(e->{
			System.out.println("btnsearch 버튼이 눌렸습니다.1");
			try {
				Stage searchStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("search_ID_PW.fxml"));
				Scene scene = new Scene(root);
				searchStage.initModality(Modality.APPLICATION_MODAL);
				searchStage.initStyle(StageStyle.UTILITY);
				searchStage.initOwner(textId.getScene().getWindow());
				searchStage.setScene(scene);
				searchStage.show();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			System.out.println("btnsearch 버튼이 눌렸습니다.2");
		});

	}

	private void msg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류!!");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
		
	}
	private void ssi(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
		
		
	}
	
	

	

}
