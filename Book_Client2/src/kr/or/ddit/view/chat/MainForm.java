package kr.or.ddit.view.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import kr.or.ddit.vochat.ChatClient;
import kr.or.ddit.vochat.EnterRoomData;
import kr.or.ddit.vochat.ExitRoomData;
import kr.or.ddit.vochat.MessageData;
import kr.or.ddit.vochat.Room;



public class MainForm extends Application implements Runnable{
	
	@FXML
	Button login_btn;
	@FXML
	Button create_btn;
	@FXML
	Button send_btn;
	@FXML
	TextFlow chatFlow;
	@FXML
	ListView<String> clientList;
	@FXML
	ListView<String> roomList;
	@FXML
	TextField chatField;
	@FXML
	Label myNickName;
	@FXML
	Label inRoomName;
	
	private Stage loginprimaryStage;
	private Stage createRoomprimaryStage;
	private static Room myRoom;
	 
	
	static ObservableList<String> forClientList = FXCollections.observableArrayList(); //clientList를 위한 객체
	static ObservableList<String> forRoomList = FXCollections.observableArrayList(); //roomList를 위한 객체
	//ListView객체를 만지려면 ObservableList를 통해서 접근해야 한다.
	//textflow를 위한 ObservableList 객체
	
	static ObservableList<Node> forChatFlow ; // 채팅 전체 저장하는거??

	static Scene scene; //메인화면으로 돌아오기 위한 scene

	static Socket socket;
	static ObjectInputStream ois = null; //좌표 송신용
	static ObjectOutputStream oos = null; //좌표 수신용
	
	static String userName; // MainForm에서도 userName 사용하여 Message 객체 전송 ( 및 메인폼에 접속자명으사용해도 좋을듯) 
	
	static Button createRoomPtr;
	
	static Label rnameptr;
	static Label mnameptr;
	
	public void clickRoomList(MouseEvent event) //roomList 클릭 
	{
		
		
		System.out.println(roomList.getSelectionModel().getSelectedItem()); // 방이름
		String fullname=roomList.getSelectionModel().getSelectedItem();
		if(this.myRoom != null)
		{
			Alert alert = new Alert(AlertType.WARNING); //에러창 띄움
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("아직 방안에 있습니다.");
			alert.setContentText("방 나가기를 먼저 눌러주세요!");
			alert.showAndWait();			
			return;
		}
		if(fullname == null)
		{
			Alert alert = new Alert(AlertType.WARNING); //에러창 띄움
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("방 확인");
			alert.setContentText("방을 제대로 선택해주세요!");
			alert.showAndWait();			
			return;
		}
		// 클릭한 방의 room객체를 얻어서 EnterRoom객체에 담아 보낼수 없으므로, 
		// 방이름만 보내고 서버 (ChatClientThread)에서 이름으로 방찾아서 처리하도록
		// 수정 부분 : EnteroomData 생성자 / ChatClientThread
		int roomID = Integer.parseInt(fullname.substring(0,fullname.indexOf(">")));
		System.out.println(roomID);
		try 
		{
			MainForm.oos.writeObject(new EnterRoomData(roomID));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void createNewRoom(ActionEvent event) //방만들기 버튼 클릭
	{
		
		
		if (socket == null) //만약 소켓이 NULL이라면 로그인 유도 
		{
			Alert alert = new Alert(AlertType.WARNING); //에러창 
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("로그인 확인");
			alert.setContentText("로그인부터 해주세요!");
			alert.showAndWait();
			return ;
		}
		if(this.create_btn.getText().equals("방만들기"))
		{
			
			
			try
			{
				AnchorPane createRoom = (AnchorPane)FXMLLoader.load(getClass().getResource("CreateRoom.fxml"));
				Scene scene =new Scene(createRoom);
				
				createRoomprimaryStage = (Stage)create_btn.getScene().getWindow();
				createRoomprimaryStage.setTitle("방만들기");
				createRoomprimaryStage.setScene(scene);
				this.create_btn.setText("방나가기");
				this.login_btn.setText("종료");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(this.create_btn.getText().equals("방나가기"))
		{
			try {
				MainForm.oos.writeObject(new ExitRoomData());
				MainForm.forClientList.clear();
				this.chatFlow.getChildren().clear();
				myRoom = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				this.create_btn.setText("방만들기");
			}
		}
	}
	
	public void sendMessage(ActionEvent event) //전송버튼 클릭
	
	
	{
		if (socket == null) //만약 소켓이 NULL이라면 로그인 유도 
		{
			Alert alert = new Alert(AlertType.WARNING); //에러창 띄움
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("로그인 확인");
			alert.setContentText("로그인부터 해주세요!");
			alert.showAndWait();
			return ;
		}
		String tempStr = chatField.getText();

		if(!tempStr.isEmpty()) //텍스트 필드가 빈간이 아니라면
		{
			System.out.println(chatField.getText()); 
			//  MessageData 객체 write  
			try {
				MainForm.oos.writeObject(new MessageData(tempStr, userName ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			chatField.clear();
		}
	}
	
	public void createLoginForm(ActionEvent event) //로그인 버튼 클릭 
	{  // 로그인시에는 종료 text출력, 비로그인시에는 로그인 text출력
		if(this.login_btn.getText().equals("종료")) {
			try {
				if(this.myRoom != null)
					MainForm.oos.writeObject(new ExitRoomData());
				System.out.println("프로그램을 종료합니다.");
		        Platform.exit();
		        System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("프로그램을 종료합니다.");
		        Platform.exit();
		        System.exit(0);
			}
		}
		else if(this.login_btn.getText().equals("접속하기"))
		{
			
			
			try {
				AnchorPane login = (AnchorPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
				Scene scene =new Scene(login);
				
				loginprimaryStage = (Stage)login_btn.getScene().getWindow();
				loginprimaryStage.setTitle("접속창");
				loginprimaryStage.setScene(scene);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else
			this.login_btn.setText("종료");
		
		
		this.login_btn.setText("종료");
		

		//test
		AnchorPane root = null;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scene = new Scene(root/* ,700,400 */);
		this.login_btn.setText("종료");
		//ListView객체를 만지려면 ObservableList를 통해서 접근해야 한다.
		//맨처음 세팅할때 setItems함수를 통해 둘을 매칭!
	}
	@FXML
	private void initialize() {
		this.roomList.setItems(MainForm.forRoomList);
		this.clientList.setItems(MainForm.forClientList);
		MainForm.forChatFlow = chatFlow.getChildren();
		MainForm.createRoomPtr = this.create_btn;
		MainForm.mnameptr = this.myNickName;
		MainForm.rnameptr= this.inRoomName;
	}
	
	@Override
	public void start(Stage primaryStage) //launch 함수가 수행되면 실행되는 메소드
	{
		
		System.out.println("아ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ");
		try 
		{	
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			scene = new Scene(root,700,400);
			//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() { //수신용 쓰레드 
		while (true) {
			try {
				MainForm.oos.flush();
				Object obj = MainForm.ois.readObject();
				//invalid type code: AC
				if(obj instanceof MessageData)
				{
					System.out.println("obj instanceof MessageData");
					MessageData temp = (MessageData)obj;
					System.out.println(temp.printMessage());
					Platform.runLater(()->MainForm.forChatFlow.add(new Text(temp.printMessage())));
				}
				//roomlist 전달
				if(obj instanceof LinkedList)
				{
					Platform.runLater(()->MainForm.forRoomList.clear());
					System.out.println("obj instanceof LinkedList");
					LinkedList<Room> r_list = (LinkedList<Room>)obj;
					Iterator<Room> iter = r_list.iterator();
					while (iter.hasNext()) {
						Room room = (Room) iter.next();
						System.out.println(room.getRoomName());
						Platform.runLater(()->MainForm.forRoomList.add(room.getRoomID() +">"+ room.getRoomName()));
					}
					//백그라운드 스레드에서 add하려면 
					//배운점... 다른 스레드에서 접근하려면 runLater과 static으로 선언된 forRoomList가 필요하다....
				} 
				if(obj instanceof Room)
				{
					System.out.println("obj instanceof Room");
					this.myRoom = (Room) obj;
					Platform.runLater(()->MainForm.forClientList.clear());
					LinkedList<ChatClient> clist = ((Room) obj).getClientList();
					Iterator<ChatClient> cit = clist.iterator();
					while (cit.hasNext()) {
						ChatClient chatClient = (ChatClient) cit.next();
						System.out.println(chatClient.getNickName());
						Platform.runLater(()->forClientList.add(chatClient.getNickName()));
					}
					Platform.runLater(()->rnameptr.setText(((Room) obj).getRoomName()));
					Platform.runLater(()->createRoomPtr.setText("방나가기"));
				}
//				this.ois.skip(this.ois.available());
			}catch (OptionalDataException e) {
				e.printStackTrace();
				System.exit(0);
			}catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		} //while end
	}// run end

	public static void main(String[] args) {
		launch(args);

	} //main end

} //class end