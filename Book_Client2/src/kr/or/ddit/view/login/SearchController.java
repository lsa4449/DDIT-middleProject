package kr.or.ddit.view.login;

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

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kr.or.ddit.service.login.ILoginService;
import kr.or.ddit.vo.MemberVO;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

public class SearchController implements Initializable{
	
	@FXML JFXButton btnSearch;
	@FXML JFXTextField textEmail;
	@FXML JFXTextField textName;

	private Registry reg;
	private ILoginService service;
	

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
		
		
		
		btnSearch.setOnAction(e->{
			
			
			String email1 = textEmail.getText(); // 이거에 맞는거 찾아서 이메일 보낼때 => 아이디랑 비밀번호 같이 보내기
			String name1 = textName.getText();
			
			MemberVO vo = new MemberVO();
			System.out.println(email1);
			System.out.println(name1);
			vo.setEmail(email1);
			vo.setName(name1);
			
			MemberVO vo2 = new MemberVO();
			try {
				vo2 = service.selectEmailMemberId(vo);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(vo2 == null) {
				
				
				memberisNot("확인!", "존재 하지 않는 회원 입니다.");
				
				return;
			}
			
			
			
			
			
			
			naverMailSend(vo2.getEmail(), vo2.getId(), vo2.getPw()); // 이메일 보내기
			
		});
		
		
		
		
		
		
	}
	
	
	
	public static String naverMailSend(String send_email_addr, String id, String pw) { // 1. 받을 이메일 주소, 아이디, 비밀번호
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
            message.setSubject("회원 인증 번호 ID 및 비밀번호 입니다.");
            // 메일 내용
            String contentMessage = "ID : " + id + "\nPW  : " + pw;
            System.out.println(contentMessage); // 확인을 위해서 검사
            message.setText(contentMessage); // send the message
            Transport.send(message);
            System.out.println("Success Message Send");
           
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return randomAccordingNumber;
        
    }
	
	
	
	private void memberisNot(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	
	
	
	
	
}
