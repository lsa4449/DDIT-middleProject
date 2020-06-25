package kr.or.ddit.view.admin;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.admin.IAdminService;
import kr.or.ddit.view.notice.a;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberManageVO;
import com.jfoenix.controls.JFXButton;

public class memManageController implements Initializable {
	
	Registry reg;
	
	static IAdminService adminService;
	
	Stage stage;
	
	
	ObservableList<MemberManageVO> ob;

    @FXML
    private ImageView logo;

    @FXML
    private TableView<MemberManageVO> member;

    @FXML
    private TableColumn<MemberManageVO, String> num;

    @FXML
    private TableColumn<MemberManageVO, String> name;

    @FXML
    private TableColumn<MemberManageVO, String> id;

    @FXML
    private TableColumn<MemberManageVO, String> pw;

    @FXML
    private TableColumn<MemberManageVO, String> rrn;

    @FXML
    private TableColumn<MemberManageVO, String> joinDate;

    @FXML
    private TableColumn<MemberManageVO, String> tel;

    @FXML
    private TableColumn<MemberManageVO, String> lateCount;

    @FXML
    private TableColumn<MemberManageVO, String> state;

	@FXML
    JFXButton backBtn; //뒤로가기버튼
	
	
	
	
	
	
    public void selectMember() {
    	List<MemberManageVO> memlist= null;

		try{
			memlist = adminService.selectMemberAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		for(MemberManageVO vo : memlist) {
			if(vo.getState().equals("1")){
				vo.setState("우수회원");
			}else if(vo.getState().equals("2")) {
				vo.setState("경고회원");
			}else if(vo.getState().equals("3")) {
				vo.setState("불량회원");
			}else if(vo.getState().equals("0")) {
				vo.setState("일반회원");
			}
			ob.add(vo);
		}

		member.setItems(ob);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			adminService = (IAdminService) reg.lookup("adminService");
			System.out.println("adminService RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		//메인창으로 가기
		logo.setOnMouseClicked(e->{
			Parent root = null;
			try {
				root = (Parent)FXMLLoader.load(getClass().getResource("adminMain.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(root);
			
			stage = (Stage)logo.getScene().getWindow();
			stage.setTitle("회원 관리");
			stage.setScene(scene);
		});
		
		num.setCellValueFactory(new PropertyValueFactory<>("memNum"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		pw.setCellValueFactory(new PropertyValueFactory<>("pw"));
		rrn.setCellValueFactory(new PropertyValueFactory<>("rrnNum"));
		joinDate.setCellValueFactory(new PropertyValueFactory<>("joinNow"));
		tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		lateCount.setCellValueFactory(new PropertyValueFactory<>("lateCount"));
		state.setCellValueFactory(new PropertyValueFactory<>("state"));

		

		ob = FXCollections.observableArrayList();
		
		
		selectMember();
		
		//전체 회원 조회
		

		
		//회원 정보 수정
		member.setOnMouseClicked(e -> {
			
			MemberManageVO vo = (MemberManageVO) member.getSelectionModel().getSelectedItem();
			
			AdminData.vo = vo;
			
			// 확인하시겠습니까 다이얼로그 창
			Stage dialog = new Stage(StageStyle.UTILITY);
			
			
			  //더블클릭했을때 세부사항 화면창
			dialog.initModality(Modality.APPLICATION_MODAL);
			
			// 3. 부모창 지정
			dialog.initOwner(member.getScene().getWindow());
			dialog.setTitle("추가");
			
			// 4. 자식창에 나타날 컨테이너객체 생성
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("memStop.fxml"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			// 5. Scene 객체 생성해서 컨테이너 객체 추가
			Scene scene = new Scene(parent);

			// 6. Stage객체에 Scene 객체 추가
			dialog.setScene(scene);
			dialog.setResizable(false); // 크기 고정
			dialog.showAndWait();
			ob.clear();
			selectMember();
		});
		
		
		
		
		
		
		
		backBtn.setOnAction(e ->{
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
		
		
	}
	
}
