package kr.or.ddit.service.chat;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import kr.or.ddit.vochat.ChatClient;
import kr.or.ddit.vochat.CreateRoomData;
import kr.or.ddit.vochat.EnterRoomData;
import kr.or.ddit.vochat.ExitRoomData;
import kr.or.ddit.vochat.MessageData;
import kr.or.ddit.vochat.Room;
import kr.or.ddit.vochat.TerminateData;



public class ChatClientThread extends Thread{
	Room myRoom; //현재 클라이언트가 들어가있는 방
	
	LinkedList<Room> roomList; //모든 스레드가 roomList를 공유해야 하기 때문에
	
	ChatClient cli; //방안의 클라이언트 관리용 객체
	
	Sockets skts;
	String nickName;
	Server myServer;
	
	public ChatClientThread(Socket socket, Server server) {
		this.myServer = server;
		this.roomList = server.getRoomList(); // 서버의 룸 리스트를 모든 클라이언트가 공유하게 전달?
		System.out.println("스레드 시작");
		this.skts = new Sockets();
		this.skts.socket = socket;
		this.skts = new Sockets(socket);
		
		try {
			this.nickName = (String)this.skts.ois.readObject(); //맨처음 로그인창에서 nickName전달받기를 대기
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		cli = new ChatClient(nickName, Server.cid++);
		
		Server.hmap.put(this.cli, this.skts);
		
		System.out.println(this.nickName + "접속!");
		
		sendRoomList();
//		Thread receiveThread = new Thread(this);
//		receiveThread.setDaemon(true);
//		receiveThread.start(); //run메소드 실행
	}

	@Override
	public void run() { //수신용 스레드
		System.out.println("수신용 쓰레드 시작");
		
		while (true) {
			
			try {
//				java.io.StreamCorruptedException: invalid type code: 00
				
				Object obj;
				
				synchronized (this) {
					obj = this.skts.ois.readObject(); 
					//아무리 스레드 별로 각각의 ois, oos 객체를 가지더라도 한 프로그램에서는 아나의 ois, oos객체를 쓴다 카더라
					
					//따라서 ois, oos를 쓸때는 항상 sync작업이 필요하다더라 -
					
				}
				System.out.println("수신!");
				if(obj instanceof MessageData)
				{
					sendMessage((MessageData)obj);
				}
				else if(obj instanceof EnterRoomData)
				{
					enterRoom((EnterRoomData)obj);
				}
				else if(obj instanceof CreateRoomData)
				{
					createRoom((CreateRoomData)obj);
				}
				else if(obj instanceof ExitRoomData)
				{
					exitRoom((ExitRoomData)obj);
				}
				else if(obj instanceof TerminateData)
				{
					break;
				}
			} catch (ClassNotFoundException | IOException e ) {
				e.printStackTrace();
				Server.hmap.remove(this.cli);
				System.out.print(cli.getNickName());
				System.out.println(": 연결해제..");
				System.out.println("스레드 제거..");
				return;
			}
		} //while end
	} //run end

	private void exitRoom(ExitRoomData obj) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("exitroom");
		myRoom.clientList.remove(this.cli);
		
		if (myRoom.getClientList().size()==0) {
			this.roomList.remove(myRoom);
			sendRoomList(); // myroom안의 client들에게 업데이트된 clientlist 뿌리기
			return;
		}
		Iterator<ChatClient> cit = myRoom.clientList.iterator();
		while (cit.hasNext()) {
			ChatClient chatClient = (ChatClient) cit.next();
			Server.hmap.get(chatClient).oos.reset();
			Server.hmap.get(chatClient).oos.writeObject(myRoom);
		}
		this.myRoom = null;
	}

	private void createRoom(CreateRoomData obj) throws IOException {
		System.out.println("createRoom" + " : " + cli.getNickName());
		System.out.println(obj.getRoomname());
		this.myRoom = new Room(obj.getRoomname());
		this.myRoom.roomID = Server.rid++;
		
		this.myRoom.clientList = new LinkedList<>();
		this.myRoom.clientList.add(this.cli);
		this.roomList.add(myRoom); //만든 자기방을 add
		sendMyRoom();
		sendRoomList();
	}

	public synchronized void  sendRoomList() {
//		if(this.roomList.size() == 0)
//			return;
		if(Server.hmap.size() == 0)
		{
			System.out.println("보낼 인원이 없습니다.");
			return;
		}

		Collection<Sockets> slist = Server.hmap.values();
		Iterator<Sockets> sit = slist.iterator();
		while (sit.hasNext()) {
			Sockets sockets = (Sockets) sit.next();
			try {
				sockets.oos.reset();
				sockets.oos.writeObject(this.roomList);
//				sockets.oos.writeObject(new LinkedList(this.roomList));
			} catch (IOException e) {
				System.out.println("보낼 인원이 없습니다.");
				e.printStackTrace();
			}
		}
	}
	public void sendMyRoom()
	{
		try {
			synchronized (this.skts.oos)
			{
				this.skts.oos.reset();
				this.skts.oos.writeObject(myRoom);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void enterRoom(EnterRoomData obj) {
		// 수정 후 
		System.out.println("enterRoom");
		int roomID = obj.getRoomID();
		if(this.myRoom != null)
		{
			if(this.myRoom.getRoomID() == roomID)
			{
				System.out.println("이미 같은방");
				return;
			}
		}
		System.out.println(roomID);
		Iterator<Room> itr = this.roomList.iterator(); //방리스트에 찾아서 myroom으로 설정
		while (itr.hasNext()) {
			Room room = (Room) itr.next();
			if (room.getRoomID() == roomID) {
				this.myRoom= room;
				this.myRoom.clientList.add(this.cli);
				sendMyRoom();
				break;
			}
		}
		Iterator<ChatClient> cit = this.myRoom.clientList.iterator();
		while (cit.hasNext()) {
			ChatClient chatClient = (ChatClient) cit.next();
			try {
				Server.hmap.get(chatClient).oos.reset();;
				Server.hmap.get(chatClient).oos.writeObject(myRoom);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void sendMessage(MessageData message) { //myRoom 의 client들에게 MessageData 객체 전달 
		System.out.println("send message");
		
		Iterator<ChatClient> mit = this.myRoom.clientList.iterator();
		while (mit.hasNext()) {
			ChatClient chatClient = (ChatClient) mit.next();
			try {
				Server.hmap.get(chatClient).oos.writeObject(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
	}
}// class end
