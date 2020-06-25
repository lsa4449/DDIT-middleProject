package kr.or.ddit.view.login;

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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kr.or.ddit.service.login.ILoginService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.login.capcha.CaptchaFinal;
import kr.or.ddit.view.login.capcha.CaptchaImage;
import kr.or.ddit.view.login.capcha.CaptchaKey;
import kr.or.ddit.vo.MemberVO;


public class LoginCapchaController implements Initializable{

	
	 @FXML
	    private JFXTextField textId;

	    @FXML
	    private JFXPasswordField textPw;

	    @FXML
	    private JFXButton btnLogin;

	    @FXML
	    private JFXButton btnsearch;

	    @FXML
	    private JFXButton btnJoin;

	    @FXML
	    private JFXButton btnKakaoLogin;

	    @FXML
	    private ImageView ImageChapcha;

	    @FXML
	    private JFXTextField capCha;
	    
	    String key;
	    
	    Registry reg;
	    
	    ILoginService service;
	    
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
		
		
		
		
		
        key = newCaptcha();

        btnLogin.setOnAction(event -> {
        	
        	
            String value = capCha.getText();

            boolean chk = CaptchaFinal.check(key, value);

            if (chk == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("결과");
                alert.setContentText("성공!");
                alert.showAndWait();
                capCha.clear();
            } else if (chk == false) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("결과");
                alert.setContentText("실패!");
                alert.showAndWait();
                capCha.clear();
                key = newCaptcha();
            }
            
            
            
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
			
			if((vo2 != null) && chk) {
				System.out.println("로그인에 성공했습니다.");
				
				// 부모창 다시 띄우기
			
				 Parent root;
					Session.loginUser = vo2;
					Session.loginUser.getName();
				//	con.name();
		         Stage stage = (Stage)btnLogin.getScene().getWindow();
		         stage.close();
			}
			
			
        });
    }

    public String newCaptcha() {
        String key = CaptchaKey.getKey();
        String fileName = CaptchaImage.captchaImage(key);
        Image image = new Image("file:" + fileName + ".jpg");
        ImageChapcha.setImage(image);
        return key;
    }
		
		

}
