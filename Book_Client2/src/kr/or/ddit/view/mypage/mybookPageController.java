package kr.or.ddit.view.mypage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.mypage.IMyPageBookService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MyBookPageVO;
import kr.or.ddit.vo.MybookVO;

public class mybookPageController implements Initializable {

	private Registry reg;
	IMyPageBookService service;
	ObservableList<MyBookPageVO> ob = FXCollections.observableArrayList();
	
	  @FXML
	    private Button rentaledBook;

	    @FXML
	    private Button wannaBook;

	    @FXML
	    private TableView<MyBookPageVO> tb;

	    @FXML
	    private TableColumn<MyBookPageVO, String> bookName;

	    @FXML
	    private TableColumn<MyBookPageVO, String> author;

	    @FXML
	    private TableColumn<MyBookPageVO, String> rental;

	    @FXML
	    private TableColumn<MyBookPageVO, String> returnd;

	    @FXML
	    private TableColumn<MyBookPageVO, String> state;
	    
	    List<MyBookPageVO> list = null;
	
	//메뉴바
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

//	@FXML
//	private MenuItem wannaBook; // 희망도서 신청

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
		
		
		
		bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
		author.setCellValueFactory(new PropertyValueFactory<>("author"));
		rental.setCellValueFactory(new PropertyValueFactory<>("rentalDate"));
		returnd.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
		//state.setCellValueFactory(new PropertyValueFactory<>("state"));
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IMyPageBookService) reg.lookup("myPageBookService");
			System.out.println("rmi성공");
		}catch (Exception e) {
			System.out.println("MyBookService lookup오류");
			e.printStackTrace();
		}
		
		
		/*
		MemberVO vo= new MemberVO();
		vo = Session.loginUser;
		
		MemberVO vo2 = new MemberVO();
		vo2 = vo;
		*/
		
		
		rentaledBook.setOnAction(e->{
			
			try {
				list = service.selectMyBookReturn(Session.loginUser);
			} catch (RemoteException e1) {
				System.out.println("rentalbedBook버튼 에러");
				e1.printStackTrace();
			}
			for(MyBookPageVO pageVO : list) {
				System.out.println(pageVO.getBookName());
				ob.add(pageVO);
			}
			tb.setItems(ob);
		});
		
		
		
		/* 메뉴바 이동 경로 */
		
		logo.setOnMouseClicked(e -> {

			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("main.fxml"));
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

		wannaBook.setOnAction(e -> {
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
		
	}
}
