package kr.or.ddit.view.monthBook;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import kr.or.ddit.service.monthBook.IMonthBookService;
import kr.or.ddit.vo.MonthBookVO;
import kr.or.ddit.vo.MonthFileVO;

public class InsertMonthBook implements Initializable {

	private ObservableList<MonthBookVO> data;
	private IMonthBookService service;
	private Window primaryStage;
	private Registry reg;

	@FXML
	Button insertBtn; // 글 등록 버튼
	@FXML
	Button cancelBtn;
	@FXML
	JFXButton insertFile; // 파일 등록 버튼
	@FXML
	JFXTextField monthName;
	@FXML
	JFXTextField file;
	@FXML
	ImageView save;
	@FXML
	ImageView book;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Image image = new Image("file:src/images/save.png");
		book.setImage(image);
		
		Image image2 = new Image("file:src/images/book2.png");
		save.setImage(image2);
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IMonthBookService) reg.lookup("monthService");
			System.out.println("RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		insertBtn.setOnAction(e -> {
			if (monthName.getText().isEmpty()) {

				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}

			MonthBookVO mv = new MonthBookVO();

			mv.setMonthBookName(monthName.getText());
			mv.setMonthBookImage(file.getText());

			data = FXCollections.observableArrayList(mv);

			int cnt = 0;

			try {
				cnt = service.insertMonthBook(mv);
				if (cnt == 1) {
					System.out.println("성공");
				}
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}
			infoMsg("작업결과 >>", "추천도서 추가 완료!");

			Stage stage = (Stage) insertBtn.getScene().getWindow();
			stage.close();


			Stage dialog = new Stage(StageStyle.UTILITY);

			// 2.모달창 여부 설정
			// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
			dialog.initModality(Modality.APPLICATION_MODAL);

			dialog.setTitle("추가");

			// 4. 자식창에 나타날 컨테이너객체 생성
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("SelectMonthBook.fxml"));
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

		insertFile.setOnAction(e -> {

			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
					new ExtensionFilter("Audio Files", "*.wav", "*.mp3"), new ExtensionFilter("All Files", "*.*"));
			File uploadFile = fileChooser.showOpenDialog(primaryStage);

			try {
				if (uploadFile != null) {

					File dir = uploadFile.getParentFile();
					fileChooser.setInitialDirectory(dir);
					file.setText(uploadFile.getPath().replaceAll("\n", "\r\n"));
				}
			} catch (Exception ex) {

			}
		});

		cancelBtn.setOnAction(e -> {

			Stage stage = (Stage) cancelBtn.getScene().getWindow();
			stage.close();

		});

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
