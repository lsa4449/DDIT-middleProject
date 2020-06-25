package kr.or.ddit.view.join;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import kr.or.ddit.service.join.IJoinService;
import kr.or.ddit.view.join.captcha.CaptchaFinal;
import kr.or.ddit.view.join.captcha.CaptchaImage;
import kr.or.ddit.view.join.captcha.CaptchaKey;
import kr.or.ddit.vo.MemberVO;
import netscape.javascript.JSObject;

public class JoinController implements Initializable {
	// 쓸 서비스 가져오기

	@FXML
	TextField textName; // 성명
	@FXML
	TextField textId; // 아이디
	@FXML
	TextField textPass1; // 비밀번호
	@FXML
	TextField textPass2; // 비밀번호 확인
	@FXML
	Button idCheck; // 아이디 중복 확인
	@FXML
	TextField textNickName; // 닉네임
	@FXML
	TextField textEamil; // 이메일
	@FXML
	TextField textEmailCheck; // 이메일 인증 번호
	@FXML
	Button btnEmailSend; // 이메일 인증 번호 보내기
	@FXML
	Button btnEmailCheck; // 이메일 인증 번호 확인 버튼
	@FXML
	RadioButton rMale; // 라디오 버튼 남자
	@FXML
	RadioButton rFemale; // 라디오 버튼 여자
	@FXML
	TextField textPhoneNumber; // 전화번호
	@FXML
	TextField textZip; // 우편번호
	@FXML
	TextField textAddr; // 주소
	@FXML
	TextField textAddrDetail; // 상세 주소
	@FXML
	Button btnJoin; // 회원 가입 버튼
	@FXML
	Button btnCancle; // 취소
	@FXML 
	Button nickcheck; //닉네임 중복확인 버튼
	@FXML
	TextField textRrnNum;
	@FXML 
	Button pwcheck; // 패스워드 확인 버튼

	@FXML Button searchZip; // 우편번호 찾는 버튼
	
	@FXML ImageView ivCaptcha;
	@FXML JFXTextField tfCaptchaInput;
	@FXML JFXButton btnCaptchaConfirm;

	private Registry reg;
	private IJoinService service;

	private String send_email_addr;

	// 인증 번호 저장 변수
	String randomNum = null;
	
	Stage stage; // 주소검색을 위한 Stage()
	
    String key;
    
	private JSObject javascriptConnector;
	private JavaConnector javaConnector = new JavaConnector();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IJoinService) reg.lookup("joinService");
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

		btnCaptchaConfirm.setOnMouseClicked(e -> {
            String value = tfCaptchaInput.getText();

            boolean chk = CaptchaFinal.check(key, value);
            System.out.println(chk);

            if (chk == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("결과");
                alert.setContentText("성공!");
                alert.showAndWait();
                tfCaptchaInput.clear();
            } else if (chk == false) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("결과");
                alert.setContentText("실패!");
                alert.showAndWait();
                tfCaptchaInput.clear();
                key = newCaptcha();
            }
        });
        

		btnJoin.setOnAction(e -> {
			if (textName.getText().isEmpty() || textId.getText().isEmpty() || textPass1.getText().isEmpty()
					|| textPass1.getText().isEmpty() || textEamil.getText().isEmpty()
					|| textPhoneNumber.getText().isEmpty() || textZip.getText().isEmpty()) {
				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}

			MemberVO memVO = new MemberVO();

			memVO.setId(textId.getText());// 아이디
			memVO.setPw(textPass1.getText()); // 비밀번호
			memVO.setRrnNum(textRrnNum.getText());// 주민번호
			memVO.setName(textName.getText());// 이름
			memVO.setTel(textPhoneNumber.getText());// 전화번호
			memVO.setEmail(textEamil.getText());// 이메일
			//memVO.setAuthority("1");
			memVO.setAddr(textAddr.getText());// 주소

			memVO.setNickName(textNickName.getText()); // 닉네임

			int cnt = 0;
			try {
				//Object obj = service.insertMember(memVO);
				cnt = service.insertMember(memVO);
				if (cnt == 1) {

					System.out.println("회원 가입 성공");
				}

				okMsg("회원가입 성공", textId.getText() + "님 회원 가입에 성공했습니다.");

			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		

		idCheck.setOnAction(e -> {

			MemberVO vo = new MemberVO(); // 디비에서 받아올 값을 저장
			int cnt = 0;
			try {
				cnt = service.selectIdMember(textId.getText());
				System.out.println(cnt);
				if (cnt > 0) {
					xmsg("x",textId.getText() + "는 사용중입니다." );System.out.println(cnt);
				} else {
					omsg("o", textId.getText() + "는 사용가능합니다");System.out.println(cnt);
				}
			} catch (RemoteException e1) {
				
				e1.printStackTrace();
			}
		});
		
		nickcheck.setOnAction(e -> {

			MemberVO vo = null; // 디비에서 받아올 값을 저장

			int cnt = 0;
			try {
				cnt = service.selectNickNameMember(textNickName.getText());
				if (cnt > 0) {
					xmsg("x",textNickName.getText() + "는 사용중입니다." );
				} else {
					omsg("o", textNickName.getText() + "는 사용가능합니다");
				}
			} catch (RemoteException e1) {
				
				e1.printStackTrace();
			}

		});
		
		pwcheck.setOnAction(e -> {
			if(textPass1.getText().equals(textPass2.getText())) {
				pa1("", "pw가 확인되었습니다.");
			}else {
				pa1("", "pw가 틀렸습니다");
			}
		});
		

		btnEmailSend.setOnAction(e -> {
			send_email_addr = textEamil.getText();
			randomNum = naverMailSend(send_email_addr);
		});

		btnEmailCheck.setOnAction(e -> {
			if (randomNum.equals(textEmailCheck.getText())) {
				okEmailAccordingSuccess("성공", "이메일 인증에 성공했습니다.");
			} else {
				noEmailAccordingSuccess("실패", "이메일 인증에 실패했습니다.");
			}
		});
		
		// 우편번호 찾기
		searchZip.setOnAction(e -> {

			
			try {
				openAddrDialog();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		// 캡차 확인
		btnCaptchaConfirm.setOnAction(e->{
			
		});

	}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	private void okMsg(String headerText, String msg) {
		Alert okAlert = new Alert(AlertType.INFORMATION);
		okAlert.setTitle("성공");
		okAlert.setHeaderText(headerText);
		okAlert.setContentText(msg);
		okAlert.showAndWait();
	}

	private void okEmailAccordingSuccess(String headerText, String msg) {
		Alert okAlert = new Alert(AlertType.INFORMATION);
		okAlert.setTitle("성공");
		okAlert.setHeaderText(headerText);
		okAlert.setContentText(msg);
		okAlert.showAndWait();
	}

	private void noEmailAccordingSuccess(String headerText, String msg) {
		Alert okAlert = new Alert(AlertType.INFORMATION);
		okAlert.setTitle("실패");
		okAlert.setHeaderText(headerText);
		okAlert.setContentText(msg);
		okAlert.showAndWait();
	}
	private void xmsg(String headerText, String msg) {
		Alert xmsgAlert = new Alert(AlertType.INFORMATION);
		xmsgAlert.setTitle("중복체크");
		xmsgAlert.setHeaderText(headerText);
		xmsgAlert.setContentText(msg);
		xmsgAlert.showAndWait();
	}
	private void omsg(String headerText, String msg) {
		Alert omsgAlert = new Alert(AlertType.INFORMATION);
		omsgAlert.setTitle("중복체크");
		omsgAlert.setHeaderText(headerText);
		omsgAlert.setContentText(msg);
		omsgAlert.showAndWait();
	}
	private void pa1(String headerText, String msg) {
		Alert omsgAlert = new Alert(AlertType.INFORMATION);
		omsgAlert.setTitle("pw확인");
		omsgAlert.setHeaderText(headerText);
		omsgAlert.setContentText(msg);
		omsgAlert.showAndWait();
	}


	public static String naverMailSend(String send_email_addr) {
		String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
		String user = "jyh6842@naver.com"; // 패스워드
		String password = "hyun6632";

		String randomAccordingNumber = null; // 전송할 이메일 번호 저장

		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(send_email_addr)); // 보내질 메일 주소
			// 메일 제목
			message.setSubject("회원 인증 번호 메일입니다.");
			// 메일 내용
			randomAccordingNumber = "";
			for (int i = 0; i < 6; i++) {
				int randomNumber = (int) (Math.random() * 9) + 1;
				randomAccordingNumber += randomNumber;
			}
			System.out.println(randomAccordingNumber); // 확인을 위해서 검사
			message.setText(randomAccordingNumber); // send the message
			Transport.send(message);
			System.out.println("Success Message Send");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return randomAccordingNumber;

	}
	
	
	public class JavaConnector {
        /**
         * called when the JS side wants a String to be converted.
         *
         * @param value
         *         the String to convert
         */
        public void toLowerCase(String value) {
            if (null != value) {
                javascriptConnector.call("showResult", value.toLowerCase());
                // call : 자바 스크립트 메소드를 호출합니다. JavaScript의 "this.methodName (args [0], args [1], ...)"과 같습니다. 
            }
        }
        public void sendAddr(String addr, String extraAddr, String zonecode) {
        	System.out.println("자바스크립트에서 보내온 메시지 출력 : " + addr);
        	textAddr.setText(addr);
        	textZip.setText(zonecode);
        	stage.close(); // 창닫기
        	
        	
        }
    }
    
    private void openAddrDialog() throws IOException {
    	
    	URL url = new File("src/basic/webview/sample.html").toURI().toURL();

        WebView webView = new WebView(); // webView 객체 만들기
        final WebEngine webEngine = webView.getEngine(); // webView 객체를 Returns the WebEngine object.

        // set up : 설정
        // listener : 프로그램에서 2가지로 구분할수 있다 
        //				1. 네트워크 프로그램(소켓)에서 소켓 서버의 역할은 연결을 받아주는 것
        //					이 연결을 받아주기 위해서 필요한 것이 바로 listen 함수이며, 말 그대로 listener 라고 불리우기도 한다.
        //					※ socket 서버에서 listener 하는 친구
        // 				2. 다른 의미의 listener 라고 하는 것은 특정 이벤트를 받기위한 기능 을 제공하는 것을 말한다.
        //					※ 이벤트를 받기 위해서 대기하는 친구
        // set up the listener
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
        	// getLoadWorker : 로드 진행률을 추적하는 데 사용할 수있는 javafx.concurrent.Worker 객체를 반환합니다.
        	// stateProperty : 현재 상태를 나타내는 ReadOnlyObjectProperty를 가져옵니다.
        	// addListener : ObservableValue의 값이 변경 될 때마다 통지되는 ChangeListener를 추가합니다. 동일한 리스너가 두 번 이상 추가되면 두 번 이상 알림을받습니다. 즉, 고유성을 보장하기 위해 점검이 이루어지지 않습니다.
        	//				  동일한 실제 ChangeListener 인스턴스는 다른 ObservableValues에 대해 안전하게 등록 될 수 있습니다.
        	//				 ObservableValue는 리스너에 대한 강력한 참조를 저장하므로 리스너가 가비지 수집되지 않으며 메모리 누수가 발생할 수 있습니다. 사용 후 removeListener를 호출하여 리스너를 등록 취소하거나 WeakChangeListener 인스턴스를 사용하여이 상황을 피하는 것이 좋습니다.
        	//				  매개 변수 : listener 등록 할 리스너 Throws : NullPointerException-리스너가 널인 경우 : removeListener (ChangeListener)
            if (Worker.State.SUCCEEDED == newValue) { // 페이지 로딩이 정상으로 모두 읽혀진 경우...
                // set an interface object named 'javaConnector' in the web engine's page
                JSObject window = (JSObject) webEngine.executeScript("window"); // window 객체 가져오기
                window.setMember("javaConnector", javaConnector); // winddow 객체에 Java 객체 등록하기
                // setMember : JavaScript 객체의 명명 된 멤버를 설정합니다. JavaScript에서 "this.name = value"와 동일
                
                // get the Javascript connector object. 
                //javascriptConnector = (JSObject) webEngine.executeScript("getJsConnector()");
                webEngine.executeScript("sample3_execDaumPostcode()");
                // executeScript : 현재 페이지의 컨텍스트(어떤정보의 모음)에서 스크립트를 실행합니다.
                //					context : 경계, 영역, Boundary 와 연관
                //						즉, '어떤 영역, 경계를 구분하는 데이터의 모음' 이거나 '어떤 영역, 경계를 넘어갈때 전달해야 하는 데이터 모음'의 의미가 강하다는 뜻
                // ex) System.Security.SecurityContext 라는 클래스에 대한 MSDN 의 설명 : '실행시 보안과 관련된 정보의 묶음'	
                // https://mhchoi8423.tistory.com/20
                
            }
        });

        Scene scene = new Scene(webView, 520, 470);
        
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        // now load the page
        webEngine.load("http://localhost:8181/Book_Client/sample.html");
        // load : 이 엔진에 웹 페이지를로드합니다. 이 메소드는 비동기 로딩을 시작하고 즉시 리턴합니다.
    }
    
    // 캡차
    public String newCaptcha() {
        String key = CaptchaKey.getKey();
        String fileName = CaptchaImage.captchaImage(key);
        Image image = new Image("file:" + fileName + ".jpg");
        ivCaptcha.setImage(image);
        return key;
    }
	
	
	        
	        

}
