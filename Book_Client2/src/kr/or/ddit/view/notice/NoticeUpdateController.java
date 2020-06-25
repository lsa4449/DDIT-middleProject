package kr.or.ddit.view.notice;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import kr.or.ddit.service.notice.INoticeService;
import kr.or.ddit.session.Session;
import kr.or.ddit.util.MessageFactory;
import kr.or.ddit.view.usedbookboard.UserBookBoardLoader;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;

public class NoticeUpdateController implements Initializable {

	private ObservableList<BoardVO> data;
	private Registry reg;
	private INoticeService service;
	private Window primaryStage;

	Parent parent = null;

	@FXML
	JFXButton backBtn;
	@FXML
	JFXButton delBtn; // 삭제버튼
	@FXML
	JFXButton updateBtn; // 수정버튼
	@FXML
	JFXTextField title; // 게시글 제목입력란
	@FXML
	JFXTextField date; // 작성일
	@FXML
	JFXTextArea content; // 게시글 내용입력란
	@FXML
	JFXButton fileVBtn; // 첨부파일 불러오기 버튼
	@FXML
	ImageView img; // 이미지
	
	
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
	private MenuItem wannaBook; // 희망도서 신청

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
	
	
	
	private NoticeController noticeCon;
	

	public void setNoticeCon(NoticeController noticeCon) {
		this.noticeCon = noticeCon;
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
//		Session.loginUser.setAuthority("00000");
		
		if (!Session.loginUser.getAuthority().equals("0")) {
			updateBtn.setVisible(false);
			delBtn.setVisible(false);
		}

		// 첨부파일 이미지
		Image image1 = new Image("file:src/images/save.png");
		img.setImage(image1);

		date.setStyle("-fx-alignment: CENTER;"); // 중앙 정렬

		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (INoticeService) reg.lookup("noticeService");
			System.out.println("RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {

			e.printStackTrace();
		}

		title.setText(a.boardData.getBoardTitle());
		content.setText(a.boardData.getBoardContent());
		date.setText(a.boardData.getBoardDate2());
//		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
//		
//		String to = transFormat.format(a.boardData.getBoardDate());
//		date.setText(to);

//////////////////////////////////////////////////////
		// 첨부파일불러오기버튼
		// 폴더(디렉토리)만 선택하는 Dialog창
		// Button fileVBtn = new Button("DirectoryChooser 실행");
		fileVBtn.setOnAction(e -> {

			DirectoryChooser dirChooser = new DirectoryChooser();
			File selDir = dirChooser.showDialog(primaryStage);

			if (selDir != null) {
				System.out.println("Directory : " + selDir);
			}

			// 1. Key값을 가지고 DB를 조회
			// select * from files where boardnum = '166';

			// 키값을 전역변수에서 가지고온다.
			int boardKey = a.boardData.getBoardNum();

			// 리턴받을 VO생성
			FilesVO filesVO = new FilesVO();

			// fileVO로 리턴받는다
			// 1. 서비스 메서드 생성(키값:보드넘버)

			/* filesVO = service.selectNoticeFile(boardKey); */

			System.out.println(filesVO.getFileName());
			System.out.println(filesVO.getFileNum());
			System.out.println(filesVO.getBoardNum());

			// 2. 조회해오면... fileName을 가지고, 게시판 저장경로에서 파일 카피해서 -> 새로운경로에 복사

			// 3. 첨부파일 "아래아래의 경로 다운로드 하였습니다."

		});
/////////////////////////////////////////////////////		

		// 수정버튼
		updateBtn.setOnAction(e -> {

			if (title.getText().isEmpty() || content.getText().isEmpty()) {

				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}

			try {
				parent = FXMLLoader.load(getClass().getResource("noticeMain.fxml"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			BoardVO b = new BoardVO();
			b.setBoardNum(a.boardData.getBoardNum()); // vo에 저장된 num을 가져오는것
			b.setBoardContent(content.getText()); // textfield안의 값을 가져오는것
			b.setBoardTitle(title.getText());
			b.setBoardDate2(a.boardData.getBoardDate2());

			data = FXCollections.observableArrayList(b);

			int cnt = 0;
			int cnt2 = 0;

			try {
				cnt = service.updateNotice(b);
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}

			if (cnt == 1)

				infoMsg("작업결과 >>", "정보 수정 완료!");
			else {
				System.out.println("실패.. 컨디션 난조");
			}

			title.clear();
			content.clear();

			Stage stage = (Stage) updateBtn.getScene().getWindow();
			stage.close();

			if (cnt > 0 || cnt2 > 0) {
				MessageFactory.infoMsg("작성완료", "작성성공", "게시글 작성를 완료하였습니다.");

				/*
				FXMLLoader loader = new FXMLLoader(getClass().getResource("noticeMain.fxml"));
				Parent parent = null;
				try {
					parent = loader.load();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				noticeMain.pStage.getScene().setRoot(parent);
				*/
			}
			
			noticeCon.searchList();

		});

		// 삭제버튼
		delBtn.setOnAction(e -> {

			int cnt = 0;
			int cnt2 = 0;

			try {
				cnt = service.deleteNotice(a.boardData.getBoardNum());
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}
			infoMsg("작업결과 >>", "정보 삭제 완료!");

			title.clear();
			content.clear();

			Stage stage = (Stage) delBtn.getScene().getWindow();
			stage.close();

			if (cnt > 0 || cnt2 > 0) {
				MessageFactory.infoMsg("작성완료", "작성성공", "게시글 작성를 완료하였습니다.");

			}
			
			
			noticeCon.searchList();

		});

		// X버튼 :목록
		backBtn.setOnAction(e -> {

			noticeCon.searchList();
			Stage stage = (Stage) backBtn.getScene().getWindow();
			stage.close();

		});
		
		
		
		
		/* 메뉴바 이동 경로 */
		
		logo.setOnMouseClicked(e -> {

			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/admin/adminMain.fxml"));
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

	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 확인!!");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();

	}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류!!");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}

	// 내가만든매서드
	public void newMethod(String a) {

		// void 리턴않하겠다.
		//

	}

}
