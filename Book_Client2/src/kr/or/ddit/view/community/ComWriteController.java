package kr.or.ddit.view.community;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import kr.or.ddit.service.community.ICommunityService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.questions.selectmain;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;

public class ComWriteController implements Initializable {

	FilesVO filesvo = new FilesVO();  
	private ObservableList<BoardVO> a;
	private ObservableList<File> f;
	
	private Registry reg;

	ICommunityService service;

	@FXML TextField boardContent;

	@FXML TextField boardTitle;

	@FXML Button insert;

	@FXML Text boardDate;
	
	@FXML
    private TextField boardfile;

	@FXML Text memName;

	@FXML Button files;

//커뮤니티 게시판 등록

	@SuppressWarnings("null")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		//boardDate.setCellValueFactory(new PropertyValueFactory<>("boardDate"));
		try {

			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (ICommunityService) reg.lookup("communityService");
			System.out.println("AllEatdealHomeController RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월dd일 HH : mm분");
		Date time = new Date();
		String time1 = format.format(time);
		boardDate.setText(time1);
		

		insert.setOnAction(e -> {
			BoardVO vo = new BoardVO();
			vo.setBoardTitle(boardTitle.getText());
			vo.setBoardContent(boardContent.getText());		
			vo.setMemNum(Session.loginUser.getMemNum());

			a = FXCollections.observableArrayList(vo);
			int cnt = 0;
			try {
				cnt = service.insertBoard(vo);

				if (cnt == 1) {
					System.out.println("성공");
				}
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}
			
			
			Stage stage = (Stage) insert.getScene().getWindow();
			stage.close();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("selCom.fxml")); // 경로 땡겨오기?
				Scene sc = new Scene(root);
				stage.setScene(sc);
				stage.show();

			} catch (Exception e1) {
				System.out.println("오류내용" + e1.getMessage());
				e1.printStackTrace();
			}

		});
	}

}
