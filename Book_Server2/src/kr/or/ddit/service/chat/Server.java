package kr.or.ddit.service.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

import kr.or.ddit.vochat.ChatClient;
import kr.or.ddit.vochat.Room;


public class Server {
	
	LinkedList<Room> roomList; //모든 스레드가 roomList를 공유해야 하기 때문에 static으로 선언
	//static으로 선언하면 Serialize 안된다.....
	
	ServerSocket serverSocket;
	
	static HashMap<ChatClient, Sockets> hmap = new HashMap<>();
	
	static int rid = 1;
	
	static int cid = 1000;
	
	public LinkedList<Room> getRoomList() { // 룸 리스트 안에는 룸, 룸 안에는 클라이언트 리스트
		return roomList;
	}
	
	public Server() { // 서버 객체 생성
		this.roomList = new LinkedList<>();
		this.roomList.clear();
		Server.hmap = new HashMap<>();
		Server.hmap.clear();
		
		try 
		{
			Socket socket = null;
			this.serverSocket = new ServerSocket(5001);
			
			while (true) 
			{
				
				//무한루프 돌며 클라이언트의 접속 대기.
				socket = this.serverSocket.accept();
				
				//여기서 클라이언트 실행
				
				
				System.out.println("접속됨!");
				//접속이 들어오면 스레드에 socket전달후 스레드 실행
				ChatClientThread cct = new ChatClientThread(socket, this);
				
				cct.start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		} //클라이언트
	}
	
	public static void main(String[] args) {
		System.out.println("server start");
		new Server();
	}
}