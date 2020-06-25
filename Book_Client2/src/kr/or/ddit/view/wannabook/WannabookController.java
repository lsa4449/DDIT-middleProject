package kr.or.ddit.view.wannabook;

import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kr.or.ddit.service.book.IWannaBookService;
import kr.or.ddit.service.mypage.IMyPageBookService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.WannaBookVO;

public class WannabookController implements Initializable {

	IWannaBookService service = null;
	private Registry reg;

	@FXML
	TextField title;
	@FXML
	TextField author;
	@FXML
	TextField publisher;
	@FXML
	TextField translator;
	@FXML
	TextField price;
	@FXML
	Button regBtn;
	@FXML
	TextField state;
	@FXML
	TextField category;
	@FXML
	TextField isbn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			service = (IWannaBookService) reg.lookup("wannaBookService");
		} catch (Exception e) {
			System.out.println("lookup 에러");
			e.printStackTrace();

		}

	}

	@FXML
	public void insertWannaBook() {
		WannaBookVO vo = new WannaBookVO();

		vo.setMemNum(1);  // Session.loginUser.getMemNum()
		vo.setCategoryNum(Integer.parseInt(category.getText()));
		vo.setWannaAuthor(author.getText());
		// vo.setWannaBookNum(); 쿼리에서 시퀀스로 삽입
		// vo.setWannaPublicaiontDate(wannaPublicaiontDate); -> 쿼리에서 sysdate로 삽입
		vo.setWannaPublisher(publisher.getText());
		// vo.setWannaState(wannaState); -> 신청때는 쿼리에서 0 으로 디폴트
		vo.setWannaTitle(title.getText());
		vo.setWannaTranslator(translator.getText());

		try {
			int cnt= 0;
			
			cnt= service.insertWannaBook(vo);
			
			if(cnt == 1) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
