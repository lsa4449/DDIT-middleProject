package kr.or.ddit.dao.chatting;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.dao.join.IJoinDao;
import kr.or.ddit.dao.join.JoinDaoImpl;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.ChatVO;
import kr.or.ddit.vo.MessageVO;
import kr.or.ddit.vo.ParticipationVO;

public class ChatServerDaoImpl implements IChatServerDao{

	
	private SqlMapClient smc;
	private static IChatServerDao dao;
	
	public ChatServerDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
		
	
	public static IChatServerDao getInstance() {
		if(dao==null) {
			dao = new ChatServerDaoImpl();
		} 
		return dao;
	}
		
	@Override
	public void addClient(IChatClientDao client, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnect(IChatClientDao client, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void say(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertParticipate(ParticipationVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertChat(ChatVO chatVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertMessage(MessageVO messageVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> selectParticipation(int memNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatVO> selectChat(int ChatNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageVO> selectMessage(int chatNum, int memNum) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Integer> selectManagerParticipation(int memNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
