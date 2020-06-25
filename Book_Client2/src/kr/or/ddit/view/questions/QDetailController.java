package kr.or.ddit.view.questions;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kr.or.ddit.service.questions.IQuestionsService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.community.DataBoard;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.RippleVO;

public class QDetailController implements Initializable {

	private ObservableList<BoardVO> b;
	private ObservableList<RippleVO> a;
	private Registry reg;
	IQuestionsService service;
	Parent parent = null;
	List<BoardVO> list = null;
	
	
	
	
	@FXML
	JFXTextField title;
	@FXML
	JFXTextField memname;
	@FXML
	JFXTextField data;
	@FXML
	JFXTextArea content;
	@FXML
	JFXButton backBtn1;
	@FXML
	JFXButton listBtn;
	@FXML
	JFXButton duamBtn3;
	@FXML
	Button delete;
	@FXML
	Button update;
	@FXML
	Button reple;

	@FXML
	TextArea repletext;
	//등록
	BoardVO vo = new BoardVO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 조회(데이터가져오기)
		vo = DataQ.board;
		title.setText(vo.getBoardTitle());
		memname.setText(vo.getMemNum() + "");
		content.setText(vo.getBoardContent());
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		String to = transFormat.format(vo.getBoardDate());
		data.setText(to);

		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IQuestionsService) reg.lookup("questionsService");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		vo = DataQ.board;
		int mem1 = Session.loginUser.getMemNum();
		int mem2 = vo.getMemNum();
		
		System.out.println("mem1:" + mem1);
		System.out.println("mem2:" + mem2);
		
		if (!Session.loginUser.getAuthority().equals("0")) {
			if (mem1 != mem2) {
				update.setVisible(false);
				delete.setVisible(false);
			}
		}

		// 게시글 삭제
		delete.setOnAction(e -> {

			int cnt = 0;

			try {
				cnt = service.deleteQuestions(DataQ.board.getBoardNum());
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}
			infoMsg("작업결과 >>", "정보 삭제 완료!");

			title.clear();
			content.clear();

			Stage stage = (Stage) delete.getScene().getWindow();
			stage.close();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("selQuestion.fxml")); // 경로 땡겨오기?
				Scene sc = new Scene(root);
				stage.setScene(sc);
				stage.show();

			} catch (Exception e1) {
				System.out.println("오류내용" + e1.getMessage());
				e1.printStackTrace();
			}

		});
		// 게시글 수정
		update.setOnAction(e -> {

			if (title.getText().isEmpty() || content.getText().isEmpty()) {

				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}

			try {
				parent = FXMLLoader.load(getClass().getResource("detailQuestion.fxml"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			BoardVO vo = new BoardVO();
			vo.setBoardNum(DataQ.board.getBoardNum()); // vo에 저장된 num을 가져오는것
			vo.setBoardContent(content.getText()); // textfield안의 값을 가져오는것
			vo.setBoardTitle(title.getText());

			b = FXCollections.observableArrayList(vo);

			int cnt = 0;

			try {
				cnt = service.updateQuestions(vo);
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

			Stage stage = (Stage) update.getScene().getWindow();
			stage.close();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("selQuestion.fxml")); // 경로 땡겨오기?
				Scene sc = new Scene(root);
				stage.setScene(sc);
				stage.show();

			} catch (Exception e1) {
				System.out.println("오류내용" + e1.getMessage());
				e1.printStackTrace();
			}

		});

		listBtn.setOnAction(e -> {

			Stage stage = (Stage) listBtn.getScene().getWindow();
			stage.close();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("selQuestion.fxml")); // 경로 땡겨오기?
				Scene sc = new Scene(root);
				stage.setScene(sc);
				stage.show();

			} catch (Exception e1) {
				System.out.println("오류내용" + e1.getMessage());
				e1.printStackTrace();
			}

		});
		
		
		if(Session.loginUser.getAuthority().equals("0")) {
			reple.setDisable(false);
		}else{
			reple.setDisable(true);			
		}

		
		/////////////////////////////////////////////////////////////////////////
		
		reple.setOnAction( e -> {
			BoardVO boardvo = DataQ.board;
			int boardNum =boardvo.getBoardNum();
			RippleVO vo = new RippleVO();
			vo.setRippleContent(repletext.getText());
			vo.setBoardNum(boardNum);
			
			/* repletext.setText(vo.getRippleContent()); */
			
			a = FXCollections.observableArrayList(vo);
			
			int cnt = 0;
			try {
				cnt = service.rippleQuestions(vo);

				if (cnt == 1) {
					System.out.println("성공");
				}
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}
			
			
			Stage stage = (Stage) reple.getScene().getWindow();
			stage.close();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("detailQuestion.fxml")); // 경로 땡겨오기?
				Scene sc = new Scene(root);
				stage.setScene(sc);
				stage.show();

			} catch (Exception e1) {
				System.out.println("오류내용" + e1.getMessage());
				e1.printStackTrace();
			}
			
		});

		System.out.println(DataQ.board.getBoardNum());
		RippleVO vo = new RippleVO();
		int cnt = 0;
		if(vo.getBoardNum() == 0) {
			try {
				vo = (RippleVO) service.ripple(DataQ.board.getBoardNum());
				try {
					if (vo.getRippleContent() == null) {
						vo.setRippleContent("답글이 없습니다.");
					}
				} catch (NullPointerException e) {
					repletext.setText("답글이 없습니다.");
					return;
				}
				
				repletext.setText(vo.getRippleContent());
			} catch (RemoteException e1) {
				
				e1.printStackTrace();
			}
		}
}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류!!");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}

	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 확인!!");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();

	}

}