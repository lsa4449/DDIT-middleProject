package kr.or.ddit.service.mypage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.mypage.IMyPageBookDao;
import kr.or.ddit.dao.mypage.MyPageBookDaoImpl;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MyBookPageVO;
import kr.or.ddit.vo.WannaBookVO;

public class MyPageBookServiceImpl extends UnicastRemoteObject implements IMyPageBookService {
	
	private static IMyPageBookService service;
	
	private IMyPageBookDao dao;
	
	
	private MyPageBookServiceImpl() throws RemoteException{
		dao = MyPageBookDaoImpl.getInstance();
	}
	
	public static IMyPageBookService getInstance() throws RemoteException{
		if(service ==null) {
			service = new MyPageBookServiceImpl();
			
		}
		return service;
	}
	
	@Override
	public List<MyBookPageVO> selectMyBookRentaling(MemberVO memNum) throws RemoteException{
		
		return dao.selectMyBookRentaling(memNum);
	}

	@Override
	public List<MyBookPageVO> selectMyBookReserve(MemberVO memNum) throws RemoteException{
	
		return dao.selectMyBookReserve(memNum);
	}

	@Override
	public List<MyBookPageVO> selectMyBookReturn(MemberVO memNum) throws RemoteException{
		return dao.selectMyBookReturn(memNum);
	}

	@Override
	public List<WannaBookVO> selectMyWannaBook(MemberVO memNum) throws RemoteException{
	
		return dao.selectMyWannaBook(memNum);
	}
}
