package kr.or.ddit.service.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sockets {
	Socket socket;
	
	ObjectOutputStream oos = null;
	
	ObjectInputStream ois = null;
	
	public Sockets() {
		// TODO Auto-generated constructor stub
	}
	public Sockets(Socket socket) {
		this.socket = socket;
		try {
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
