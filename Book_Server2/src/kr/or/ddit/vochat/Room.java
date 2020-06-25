package kr.or.ddit.vochat;

import java.io.Serializable;
import java.util.LinkedList;


public class Room implements Serializable{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 534966471256551790L;
	
	public LinkedList<ChatClient> clientList;  //problem code
	
	private String roomName;
	
	public int roomID;
	
	public int getRoomID() {
		return roomID;
	}
	
	public Room() {
	}
	
	public Room(String roomName) {
		super();
		this.roomName = roomName;
	}
	
	public Room(Room room) {
		// TODO Auto-generated constructor stub
	}
	
	public LinkedList<ChatClient> getClientList() {
		return clientList;
	}
	
	public void setClientList(LinkedList<ChatClient> clientList) {
		this.clientList = clientList;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
//	public void sendMessage(MessageData message){ //�ش� room�� client�鿡�� MessageData ��ü ����
//
//		
//		Iterator<ChatClient> iter = clientList.iterator();
//		
//		while (iter.hasNext()) {
//			ChatClient chatClient = iter.next();
//			try {
//				Server.hmap.get(chatClient).oos.writeObject(Server.roomList);
//			} catch (IOException e) {
//				// TODO Auto-generated catch bloc
//				e.printStackTrace();
//			}
//		}//while end
//
//	} //method end 
	
	
//	public void sendRoomList(){ // �ش� room�� client�鿡�� roomlist�� ����
//		
//		Iterator<ChatClient> iter = clientList.iterator();
//		
//		while (iter.hasNext()) {
//			ChatClient chatClient = iter.next();
//			try {
//				Server.hmap.get(chatClient).oos.writeObject(Server.roomList);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}//while end
//	}// method end
	
//	public void sendClientList(){ // �ش� room�� client�鿡�� clientlist ����
//		
//		Iterator<ChatClient> iter = clientList.iterator();
//		
//		while (iter.hasNext()) {
//			ChatClient chatClient = iter.next();
//		}//while end
//	}// method end
	
	
}// class end
