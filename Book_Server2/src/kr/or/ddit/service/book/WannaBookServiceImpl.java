package kr.or.ddit.service.book;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.book.IWannaBookDao;
import kr.or.ddit.dao.book.WannaBookDaoImpl;
import kr.or.ddit.vo.WannaBookVO;

public class WannaBookServiceImpl extends UnicastRemoteObject implements IWannaBookService {

	private static IWannaBookService service;
	private IWannaBookDao dao;
	
	public static IWannaBookService getInstance() throws RemoteException{
		if(service ==null) {
			service = new WannaBookServiceImpl();
		}
		return service;
	};
	
	private WannaBookServiceImpl() throws RemoteException {
		dao = WannaBookDaoImpl.getInstance();
		
	}
	@Override
	public int insertWannaBook(WannaBookVO wannaBookvo) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<WannaBookVO> selectWannaBook() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int updateAcceptWannaBook(String num) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int updateRejectWannaBook(String num) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
