package kr.or.ddit.service.login;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.dao.login.ILoginDao;
import kr.or.ddit.dao.login.LoginDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class LoginServiceImpl extends UnicastRemoteObject implements ILoginService{
	
	// 필요한 것 가져오는 변수 만들기
	private ILoginDao dao;
	// LoginServiceImpl사용할 변수 만들기
	private static ILoginService service;
	
	private LoginServiceImpl()  throws RemoteException{
		super();
		dao = LoginDaoImpl.getInstance();
	}
	
	public static ILoginService getInstance()  throws RemoteException{
		if(service == null) {
			service = new LoginServiceImpl();
		}
		return service;
	}
	

	@Override
	public MemberVO selectMember(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO selectEmailMemberId(MemberVO memberVo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.selectEmailMemberId(memberVo);
	}

	@Override
	public MemberVO selctPhoneMemberId(MemberVO memberVo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO selectEmailMemeberPass(MemberVO memberVo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO selectPhoneMemberPass(MemberVO memberVo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO findPassUser(MemberVO memberVo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO selectLoginMember(MemberVO memberVo) throws RemoteException {
		return dao.selectLoginMember(memberVo);
	}

}
