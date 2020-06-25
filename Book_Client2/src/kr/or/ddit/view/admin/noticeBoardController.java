package kr.or.ddit.view.admin;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import kr.or.ddit.service.notice.INoticeService;
import kr.or.ddit.view.notice.a;
import kr.or.ddit.vo.BoardVO;

public class noticeBoardController implements Initializable {
	

	
	private ObservableList<BoardVO> ob , currentPageData;
			
	private int from, to, itemsForPage;
	private Registry reg;
	INoticeService service;
	List<BoardVO> list = null;
	List<BoardVO> insert = null;
	
	
	
	
	@FXML ComboBox menuBtn;  // 전체, 제목 버튼 
	@FXML JFXButton titleBtn;   //제목검색버튼
	@FXML TextField titleText;     //제목쓰는 입력란  
	@FXML TableView<BoardVO> table;         //공지사항 테이블박스
	@FXML TableColumn<BoardVO, Integer> no;         //게시글 번호
	@FXML TableColumn<BoardVO, String> title;       //게시글 제목
	@FXML TableColumn<BoardVO, String> date;

			
	//게시글 날짜
	@FXML TableColumn<BoardVO, Integer> count;       //게시글 조회수
	@FXML JFXButton insertBtn;  //등록버튼
	@FXML JFXButton deleteBtn;  //삭제버튼
	@FXML Pagination pn;  // 페이징버튼
	@FXML Button main_btn;

	


	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (INoticeService) reg.lookup("noticeService");
			System.out.println("AllEatdealHomeController RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		
		
		//테이블 목록
		no.setCellValueFactory(new PropertyValueFactory<>("boardNum"));
		title.setCellValueFactory(new PropertyValueFactory<>("boardTitle"));
		date.setCellValueFactory(new PropertyValueFactory<>("boardDate2"));
		count.setCellValueFactory(new PropertyValueFactory<>("boardCount"));

		ob = FXCollections.observableArrayList();
		
		List<BoardVO> list = new ArrayList<BoardVO>(); 
		
		try {
			  list = service.selectAll();
			  int listSize = list.size();
			  System.out.println(listSize);
			  for(int i=0; i<listSize; i++) {
				  System.out.println(list.get(i).getBoardTitle()); 
			  }
			 
		} catch (RemoteException e) {
			System.out.println("selectAll 메소드 에러");
			e.printStackTrace();
		}
		
		
		ob.addAll(list);
		
		table.setItems(ob);
		
		
		menuBtn.getItems().add("제목");
		menuBtn.getItems().add("전체");
		menuBtn.setValue("전체");
		
		
		//글 목록중에 찾고싶은 데이터 검색 버튼 
		titleBtn.setOnAction(e ->{
			
			String searchTitle = null;
			searchTitle = titleText.getText();
			
			List<BoardVO> vo;
			try {
				ob.clear();
				vo = service.selectNotice(searchTitle);
				System.out.println("vo 확인 : " + vo);
				System.out.println(searchTitle);
				ob.addAll(vo);
				table.setItems(ob);
				
				//System.out.println(vo.getBoardNum());
				System.out.println("검색된 데이터 : ");
				//System.out.println(vo.getBoardTitle());
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
		});	
		
		
		
		//글 작성 버튼
		insertBtn.setOnAction(e -> {
			Stage dialog = new Stage(StageStyle.UTILITY);

			// 2.모달창 여부 설정
			// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
				dialog.initModality(Modality.APPLICATION_MODAL);

				dialog.setTitle("추가");

			// 4. 자식창에 나타날 컨테이너객체 생성
				Parent parent = null;
				try {
					 parent = FXMLLoader.load(getClass().getResource("insertNotice.fxml"));
					} catch (IOException e2) {
						e2.printStackTrace();
					}

			// 5. Scene 객체 생성해서 컨테이너 객체 추가
				  Scene scene = new Scene(parent);

			// 6. Stage객체에 Scene 객체 추가
				  dialog.setScene(scene);
				  dialog.setResizable(false); // 크기 고정
				  dialog.show();
					
				});
		
		
		
		
		// 목록 클릭 선택		
				table.setOnMouseClicked(e -> {
					
					  //더블클릭했을때 세부사항 화면창
					if(e.getClickCount() > 1) {
						Stage dialog = new Stage(StageStyle.UTILITY);
						
						List<BoardVO> list1 = table.getSelectionModel().getSelectedItems();
						BoardVO board = (BoardVO) list1.get(0);
						
						a.boardData = board;
						
						System.out.println(a.boardData.getBoardNum());
						
						// 2.모달창 여부 설정
						// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
						dialog.initModality(Modality.APPLICATION_MODAL);
			
						// 3. 부모창 지정
						dialog.initOwner(table.getScene().getWindow());
						dialog.setTitle("추가");
						
						// 4. 자식창에 나타날 컨테이너객체 생성
						Parent parent = null;
						try {
							parent = FXMLLoader.load(getClass().getResource("updateNotice.fxml"));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						// 5. Scene 객체 생성해서 컨테이너 객체 추가
						Scene scene = new Scene(parent);
			
						// 6. Stage객체에 Scene 객체 추가
						dialog.setScene(scene);
						dialog.setResizable(false); // 크기 고정
						dialog.show();
		                
					}	
				});
				
				
				
				//페이징버튼
				itemsForPage = 11; // 한페이에 보여줄 항목 수 설정
				int totalDataCnt = ob.size();
				int totalPageCnt = totalDataCnt % itemsForPage == 0 ? 
						totalDataCnt / itemsForPage : totalDataCnt / itemsForPage + 1;
				
				pn.setPageCount(totalPageCnt); // 전체 페이지수 설정
				
									// 방법1 Callback타입의 익명객체 생성
				pn.setPageFactory(new Callback<Integer, Node>() {
					
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
						int totSize = ob.size();
						for(int i = from; i <= to && i < totSize; i++) {
							currentPageData.add(ob.get(i));
						}
						
						return currentPageData;
					}
				});
				
				
				
				
				//
				
		
	
		
		
	}




	private void errMsg(String string, String string2) {
		// TODO Auto-generated method stub
		
	}




}
