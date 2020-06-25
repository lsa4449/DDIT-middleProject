package kr.or.ddit.view.mypage;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.mypage.IMyPageInfoService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.notice.a;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MyBookPageVO;

public class MyInformaitonController implements Initializable {

	private ObservableList<MemberVO> data;
	private Registry reg;
	IMyPageInfoService service;
	ObservableList<MemberVO> ob = FXCollections.observableArrayList();

	@FXML
	ImageView img;
	@FXML
	JFXButton update;
	@FXML
	PasswordField pw;
	@FXML
	TextField nickName;
	@FXML
	TextField tel;
	@FXML 
	JFXButton backBtn;  //뒤돌아가기 버튼


	
	
	/* 메뉴 버튼들 */
	@FXML
	private ImageView logo;// 로고

	@FXML
	private MenuBar mButton1; // 도서관 이용 버튼

	@FXML
	private MenuBar mButton2; // 도서관 소개 버튼

	@FXML
	private MenuBar mButton3; // 게시판 버튼

	@FXML
	private MenuBar mButton4; // 신청 / 참여 버튼

	@FXML
	private MenuBar mButton5; // 독서 문화 활동 버튼

	@FXML
	private MenuItem wannaBook1; // 희망도서 신청

	@FXML
	private MenuItem reserBook; // 도서대여

	@FXML
	private MenuItem seat; // 좌석

	@FXML
	private MenuItem studyRoom; // 스터디룸

	@FXML
	private MenuItem monthBook; // 이달의 도서

	@FXML
	private MenuItem chat; // 채팅

	@FXML
	private MenuItem usedBook; // 중고서적구매

	@FXML
	private MenuItem buy; // 삽니다

	@FXML
	private MenuItem sell; // 팝니다

	@FXML
	private MenuItem searchBook; // 도서 검색

	@FXML
	private MenuItem notice; // 공지사항

	@FXML
	private MenuItem QnA; // 문의사항

	@FXML
	private MenuItem community; // 커뮤니티
	
	
	
	private Object parent;
	
	
	public void dialog(String path, String title) {
		Stage dialog = new Stage(StageStyle.UTILITY);

		// 2.모달창 여부 설정
		// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
		dialog.initModality(Modality.APPLICATION_MODAL);

		dialog.setTitle(title);

		// 4. 자식창에 나타날 컨테이너객체 생성
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource(path));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// 5. Scene 객체 생성해서 컨테이너 객체 추가
		Scene scene = new Scene(parent);

		// 6. Stage객체에 Scene 객체 추가
		dialog.setScene(scene);
		dialog.setResizable(false); // 크기 고정
		dialog.show();

	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		nickName.setCellValueFactory()

		// 첨부파일 이미지
		Image image1 = new Image("file:src/images/member.png");
		img.setImage(image1);
	

		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IMyPageInfoService) reg.lookup("MyPageInfoService");
			System.out.println("RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		

		// .setMemNum(1);
		nickName.setText(Session.loginUser.getNickName());
		tel.setText(Session.loginUser.getTel());
		pw.setText(Session.loginUser.getPw());

//		  data = FXCollections.observableArrayList(v); 
//		
//		  int cnt = 0;
//		  try {
//			cnt = service.selectMyInfo(v);
//		} catch (RemoteException e2) {
//
//			e2.printStackTrace();
//		} 
//		  if(v == null) { 
//			  System.out.println("성공"); 
//			  }else {
//				  System.out.println("실패"); 
//		  }				
//		

		update.setOnAction(e -> {

			System.out.println("수정버튼을 눌렀습니다!");

			MemberVO vo = new MemberVO();
			// vo = DataFlow.MemberData;
			// 임시로 넣은 데이터
			// MemberVO memVO = new MemberVO();
//				memVO.setId("sua");
//				memVO.setNickName("영기엄마");

			vo.setMemNum(Session.loginUser.getMemNum());
			vo.setPw(pw.getText());
			vo.setNickName(nickName.getText());
			vo.setTel(tel.getText());

			data = FXCollections.observableArrayList(vo);

			int cnt = 0;

			try {
				cnt = service.updateMyInfo(vo);
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}

			if (cnt == 1)

				infoMsg("작업결과 >>", "정보 수정 완료!");
			else {
				System.out.println("실패.. 컨디션 난조");
			}

			pw.clear();
			nickName.clear();
			tel.clear();

			Stage stage = (Stage) update.getScene().getWindow();
			stage.close();

			try {
				// 부모창 정보 불러오기
				Parent root = FXMLLoader.load(getClass().getResource("myPageMain.fxml")); // 경로 땡겨오기?

				// 씬에 레이아웃 추가
				Scene sc = new Scene(root);
				stage.setScene(sc);
				stage.show();

			} catch (Exception e1) {
				System.out.println("오류내용" + e1.getMessage());
				e1.printStackTrace();
			}
		});
		
		
		
		/* 메뉴바 이동 경로 */
		
		logo.setOnMouseClicked(e -> {

			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("kr/or/ddit/view/main/main.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Scene scene = new Scene(root);
			Stage stage = (Stage) logo.getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		});
		
		
		notice.setOnAction(e -> {
			dialog("/kr/or/ddit/view/notice/noticeMain.fxml", "notice");
		});

		studyRoom.setOnAction(e -> {
			// ㅠㅠ
		});

		QnA.setOnAction(e -> {
			dialog("/kr/or/ddit/view/questions/selQuestion.fxml", "QnA");
		});

		community.setOnAction(e -> {
			dialog("/kr/or/ddit/view/community/selCom.fxml", "Community");
		});

		wannaBook1.setOnAction(e -> {
			dialog("/kr/or/ddit/view/wannabook/Wannabook.fxml", "WannaBook");
		});

		reserBook.setOnAction(e -> {
			dialog("/kr/or/ddit/view/book/reserBook.fxml", "reserveBook");
		});

		searchBook.setOnAction(e -> {
			dialog("/kr/or/ddit/view/book/selectBook.fxml", "selectBook");
		});

		studyRoom.setOnAction(e -> {
			dialog("/kr/or/ddit/view/studyroom/studyRoom.fxml", "StudyRoom");
		});
		usedBook.setOnAction(e -> {
			dialog("/kr/or/ddit/view/usedbookboard/userBookBoard.fxml", "buyBookBoard");
		});

		buy.setOnAction(e -> {
			dialog("/kr/or/ddit/view/usedbookboard/userBookBoard.fxml", "buyBookBoard");
		});

		monthBook.setOnAction(e -> {
			dialog("/kr/or/ddit/view/monthBook/SelectMonthBook.fxml", "monthBook");
		});
		seat.setOnAction(e -> {
			// ㅜㅠ
		});
		
		
		
		
		//뒤로가기버튼
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

	private void infoMsg(String string, String string2) {
		// TODO Auto-generated method stub
	}

}
