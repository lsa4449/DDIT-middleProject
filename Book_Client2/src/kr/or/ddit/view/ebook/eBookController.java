package kr.or.ddit.view.ebook;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXMasonryPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.ebook.IEBookService;
import kr.or.ddit.vo.EBookVO;

public class eBookController implements Initializable {

	private Registry reg;
	private IEBookService service;

	List<EBookVO> list = new ArrayList<>();

	Parent root = null;

	@FXML
	AnchorPane imageMain;
	@FXML
	ImageView shopping;
	@FXML
	ImageView sound;
	@FXML
	JFXMasonryPane mason;
	@FXML
	TextField tf;
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Image image1 = new Image("file:src/images/sound.png");
		sound.setImage(image1);

		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IEBookService) reg.lookup("eBookService");
		} catch (Exception e) {
			System.out.println("rmi오류");
			e.printStackTrace();
		}

		try {
			list = service.selectEBookAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			
			Image image = new Image("file:src/imagesEBook/" + list.get(i).geteBookImage() + ".png");
			
			ImageView imageView = new ImageView(image);
			
			imageView.setUserData(list.get(i));
			
			imageView.setFitHeight(200);
			
			imageView.setFitWidth(200);
			
			Label label = new Label(list.get(i).geteBookName());
			
			VBox vbox = new VBox();

			vbox.getChildren().add(imageView);
			vbox.setMargin(imageView,new Insets(30, 30, 20, 20)); 


			vbox.getChildren().add(label);
			mason.getChildren().add(vbox);

			imageView.setOnMouseClicked(e -> {
				
				DataEBook.eBook = (EBookVO) imageView.getUserData();
				
				Stage dialog = new Stage(StageStyle.UTILITY);

				// 2.모달창 여부 설정
				// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
				dialog.initModality(Modality.APPLICATION_MODAL);

				dialog.setTitle("ebook");

				// 4. 자식창에 나타날 컨테이너객체 생성
				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("ebookDetail.fxml"));
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

		}

	}
}
