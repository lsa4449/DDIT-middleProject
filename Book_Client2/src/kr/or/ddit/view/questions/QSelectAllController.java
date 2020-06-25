package kr.or.ddit.view.questions;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.service.questions.IQuestionsService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.community.DataBoard;
import kr.or.ddit.view.community.selectallmain;
import kr.or.ddit.view.notice.a;
import kr.or.ddit.view.usedbookboard.UserBookBoardLoader;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;

import javafx.scene.control.ComboBox;

import com.jfoenix.controls.JFXButton;


public class QSelectAllController implements Initializable{
	private ObservableList<BoardVO> b, currentPageData;
	private ObservableList<MemberVO> a;
	private Registry reg;
	IQuestionsService service;
	private int from, to, itemsForPage;
	List<BoardVO> list = null;
	List<BoardVO> insert = null;
	@FXML
	TableView<BoardVO> table;
	//조회수
	@FXML
	TableColumn<BoardVO, Integer> boardCount;
	@FXML
	TableColumn<BoardVO, String> boardDate;
	@FXML
	TableColumn<MemberVO, String> name;
	@FXML
	TableColumn<BoardVO, String> boardTitle;
	@FXML
	TableColumn<BoardVO, Integer> boardNum;
	@FXML 
	TableColumn<BoardVO, Integer> memNum;
	@FXML 
	TableColumn<BoardVO, Integer> groupNo;
	@FXML
	Pagination pagination;
	@FXML
	Button write;
	@FXML
	Button selbutton;
	@FXML
	TextField select;
	@FXML 
	ComboBox menuBtn;
	

	@FXML 
	JFXButton backBtn;  //뒤로가기 버튼
	
	
	
	
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
	
	
	
	
	public void setdate(int cnt) {// 초기데이터값 세팅
		try {
			List<BoardVO> list = (List<BoardVO>) service.detailQuestions(cnt);

			ObservableList<BoardVO> list2 = FXCollections.observableArrayList(list);
			table.setItems(list2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		//버튼 숨기기 
		if(!Session.loginUser.getAuthority().equals("0")) {
			backBtn.setVisible(false);
		}
		
		
		
		groupNo.setCellValueFactory(new PropertyValueFactory<>("boardGroupNum"));
		boardNum.setCellValueFactory(new PropertyValueFactory<>("boardNum"));
		boardTitle.setCellValueFactory(new PropertyValueFactory<>("boardTitle"));
		memNum.setCellValueFactory(new PropertyValueFactory<>("memNum"));
		boardDate.setCellValueFactory(new PropertyValueFactory<>("boardDate"));
		boardCount.setCellValueFactory(new PropertyValueFactory<>("boardCount"));

		b = FXCollections.observableArrayList();

		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IQuestionsService) reg.lookup("questionsService");
			System.out.println("AllEatdealHomeController RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		if(Session.loginUser.getAuthority().equals("0")) {
			write.setDisable(false);
		}else if(Session.loginUser.getAuthority().equals("1")){
			write.setDisable(false);			
		}else {
			write.setDisable(true);
		}
		
		List<BoardVO> list = new ArrayList<BoardVO>(); 
		
		try {
			  list = service.Questions();
			  int listSize = list.size();
			  System.out.println(listSize);
			  for(int i=0; i<listSize; i++) {
					 System.out.println(list.get(i).getBoardTitle()); 
			  }
			 
		} catch (RemoteException e) {
			System.out.println("selectAll 메소드 에러");
			e.printStackTrace();
		}
		b.addAll(list);	
		table.setItems(b);

		//상세조회
		table.setOnMouseClicked(e->{
			int cnt = 0;
			BoardVO vo = new BoardVO();
			vo = table.getSelectionModel().getSelectedItem();
			
			DataQ.board = vo;
			System.out.println(vo.getBoardCount());
			System.out.println("board Num" + vo.getBoardNum());
			try {
				cnt = service.upboardCount(vo);
				System.out.println("조회수");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(cnt > 0) {
				System.out.println("조회수 증가 성공");
	
			}
			
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("detailQuestion.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		});
		
		menuBtn.getItems().add("제목");
		menuBtn.getItems().add("전체");
		menuBtn.setValue("전체");
		
		selbutton.setOnAction(e ->{
			
			searchList();
			
		});	

	
					

				//페이징
				itemsForPage = 5; 
				int totalDataCnt = b.size();
				int totalPageCnt = totalDataCnt % itemsForPage == 0 ? 
						totalDataCnt / itemsForPage : totalDataCnt / itemsForPage + 1;
				
				pagination.setPageCount(totalPageCnt); 
				
									
				pagination.setPageFactory(new Callback<Integer, Node>() {
					
					@Override
					public Node call(Integer pageIndex) {
						from = pageIndex * itemsForPage;
						to = from + itemsForPage - 1;
						table.setItems(getTableViewData(from, to));
						
						return table;
					}

					private ObservableList<BoardVO> 
							getTableViewData(int from, int to) 
					{
						currentPageData = FXCollections.observableArrayList();
						int totSize = b.size();
						for(int i = from; i <= to && i < totSize; i++) {
							currentPageData.add(b.get(i));
						}
						
						return currentPageData;
					}
				});
				write.setOnAction(e -> {
					Parent root;
					try {
						root = FXMLLoader.load(getClass().getResource("writeQuestion.fxml"));
						Scene scene = new Scene(root);
						Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
						stage.setScene(scene);
						stage.show();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					
				});
				
				
				
				
				//뒤로가기 버튼
				backBtn.setOnAction(e ->{
						Parent root = null;
						try {
							root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/admin/boardManage.fxml"));
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
				public void searchList() {
					
					String searchTitle = null;
					searchTitle = select.getText();
					
					List<BoardVO> vo;
					try {
						b.clear();
						vo = service.selectQuestions(searchTitle);
						System.out.println("vo 확인 : " + vo);
						System.out.println(searchTitle);
						b.addAll(vo);
						table.setItems(b);
						
						//System.out.println(vo.getBoardNum());
						System.out.println("검색된 데이터 : ");
						//System.out.println(vo.getBoardTitle());
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}

				
				
				
		
	
				
				
				
				
				
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
}