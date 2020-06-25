package kr.or.ddit.view.study;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kr.or.ddit.service.study.IStudyService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.questions.selectmain;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;

public class StudyWriteController implements Initializable{
	FilesVO filesvo = new FilesVO();  
	private ObservableList<BoardVO> a;
	private ObservableList<File> f;
	
	private Registry reg;

	IStudyService service;

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
				service = (IStudyService) reg.lookup("studyService");
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
					cnt = service.insertStudy(vo);

					if (cnt == 1) {
						System.out.println("성공");
					}
				} catch (RemoteException e1) {

					e1.printStackTrace();
				}
				
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("selStudy.fxml"));
				Parent parent = null;
			    try{
		            parent = loader.load();
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
			    studymain.t.getScene().setRoot(parent);
			});
			

		}


	}
