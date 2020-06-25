package kr.or.ddit.service.ebook;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.ebook.EBookDaoImpl;
import kr.or.ddit.dao.ebook.IEBookDao;
import kr.or.ddit.vo.EBookVO;

public class EBookServiceImpl extends UnicastRemoteObject implements IEBookService {
	
	private static IEBookService service;
	private IEBookDao dao;
	
	private EBookServiceImpl() throws RemoteException {
		dao = EBookDaoImpl.getInstance();
	}
	
	public static IEBookService getInstance() throws RemoteException {
		if(service == null) {
			service = new EBookServiceImpl();
		}
		return service;
	};
	
	@Override
	public List<EBookVO> selectEBookAll() throws RemoteException {
		
		return dao.selectEBookAll();
	}

	@Override
	public List<EBookVO> selectEBookAuthor(EBookVO vo) throws RemoteException {
		
		return dao.selectEBookAuthor(vo);
	}

	@Override
	public List<EBookVO> selectEBookName(EBookVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
