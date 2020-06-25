package kr.or.ddit.view.monthBook;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import kr.or.ddit.service.monthBook.IMonthBookService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MonthBookVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;

public class SelectMonthBook implements Initializable {

	private Registry reg;
	private IMonthBookService service;
	List<MonthBookVO> list = new ArrayList<>();
	Parent root = null;

	@FXML
	ImageView search;
	@FXML
	ImageView logo;
	@FXML
	ImageView image;
	@FXML
	JFXButton myPage;
	@FXML
	JFXButton logout;
	@FXML
	Button insertBtn;
	@FXML
	JFXMasonryPane img;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Image image1 = new Image("file:src/images/book2.png");
		image.setImage(image1);

		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IMonthBookService) reg.lookup("monthService");
		} catch (Exception e) {
			System.out.println("rmi오류");
			e.printStackTrace();
		}

		try {
			list = service.selectMonthBookAll();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {

			Image image = new Image("file:" + list.get(i).getMonthBookImage());
			ImageView imageView = new ImageView(image);

			imageView.setUserData(list.get(i));
			imageView.setFitHeight(300);
			imageView.setFitWidth(200);

			Label label = new Label(list.get(i).getMonthBookName());
			label.setStyle("-fx-alignment: center;");
			label.setStyle("-fx-font-size: 15px;");

			VBox vbox = new VBox();
			vbox.getChildren().add(imageView);
			vbox.getChildren().add(label);

			img.getChildren().add(vbox);

			if (!Session.loginUser.getAuthority().equals("0")) {
				insertBtn.setVisible(false);
				imageView.setDisable(true);
			}

			imageView.setOnMouseClicked(e -> {

				if (e.getClickCount() > 1) {
					Alert infoAlert = new Alert(AlertType.INFORMATION);
					infoAlert.setTitle("도서 삭제");
					infoAlert.setHeaderText("신중하게 결정하십시오");
					infoAlert.setContentText("진짜 삭제 하시겠습니까?잘못 클릭하지는 않았는지..확인해보세요");

					Optional<ButtonType> result = infoAlert.showAndWait();

					if (result.get() == ButtonType.OK) {

						MonthBookVO m = new MonthBookVO();
						DataFlow.mb = m;
						m = (MonthBookVO) imageView.getUserData();

						int cnt = 0;
						try {
							cnt = service.deleteMonthBook(m.getMonthBookNum());
							if (cnt == 1) {
								System.out.println("성공");
							}
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}

						Alert infoAlert2 = new Alert(AlertType.INFORMATION);
						infoAlert.setTitle("정보 확인!!");
						infoAlert.setHeaderText("도서 삭제");
						infoAlert.setContentText("도서가 삭제 되었습니다!");
						infoAlert.showAndWait();

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

					}
				}
			});

		}

		insertBtn.setOnAction(e -> {

			Stage dialog = new Stage(StageStyle.UTILITY);

			// 2.모달창 여부 설정
			// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
			dialog.initModality(Modality.APPLICATION_MODAL);

			dialog.setTitle("추가");

			// 4. 자식창에 나타날 컨테이너객체 생성
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("InsertMonthBook.fxml"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			// 5. Scene 객체 생성해서 컨테이너 객체 추가
			Scene scene = new Scene(parent);

			// 6. Stage객체에 Scene 객체 추가
			dialog.setScene(scene);
			dialog.setResizable(false); // 크기 고정
			dialog.show();

			Stage stage = (Stage) insertBtn.getScene().getWindow();
			stage.close();
		});

	}
}
