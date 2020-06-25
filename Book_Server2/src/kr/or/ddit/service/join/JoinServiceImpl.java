package kr.or.ddit.service.join;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.dao.join.IJoinDao;
import kr.or.ddit.dao.join.JoinDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class JoinServiceImpl extends UnicastRemoteObject implements IJoinService{
	
	private IJoinDao dao;
	private static IJoinService service;
	
	protected JoinServiceImpl() throws RemoteException{
		//super();
		dao = JoinDaoImpl.getInstance();
	}
	
	public static IJoinService getInstance() throws RemoteException{
		if(service == null) {
			service = new JoinServiceImpl();
		}
		return service;
	}

	@Override
	public int insertMemberSns(MemberVO memberVO) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws RemoteException {
		return dao.insertMember(memberVO);
	}

	@Override
	public int selectIdMember(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.selectIdMember(id);
	}

	@Override
	public int selectNickNameMember(String nickName) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.selectNickNameMember(nickName);
	}
	
	




}
