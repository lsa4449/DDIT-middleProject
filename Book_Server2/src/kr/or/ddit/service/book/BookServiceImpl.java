package kr.or.ddit.service.book;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.book.BookDaoImpl;
import kr.or.ddit.dao.book.IBookDao;
import kr.or.ddit.vo.BookVO;

public class BookServiceImpl extends UnicastRemoteObject implements IBookService{
	
	private IBookDao dao; 
	private static IBookService service;
	
	protected BookServiceImpl() throws RemoteException {
		super();
		dao = BookDaoImpl.getInstance();
	}
	
	public static IBookService getInstance() throws RemoteException {
		if(service== null) {
			service = new BookServiceImpl();
		}
		return service;
	}
	

	@Override
	public int insertBook(BookVO bookVo) throws RemoteException{
		return dao.insertBook(bookVo);
	}
	


	@Override
	public int returnBook(String bookState) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BookVO> bookList() throws RemoteException {

		return dao.bookList();
	}

	@Override
	public List<BookVO> categoryBookList(int CategoryNum) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookVO> purchaseExpectBook(int memNum) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookVO> selectSearchBook(BookVO bookVo) throws RemoteException {
		return dao.selectSearchBook(bookVo);
	}

	@Override
	public int updateBook(BookVO bookVo) throws RemoteException {
		return dao.updateBook(bookVo);
	}

	@Override
	public int deleteBook(int bookNum) throws RemoteException {
		return dao.delete(bookNum);
	}

	@Override
	public int beforeReserBook(String bookState) throws RemoteException {
		return dao.beforeReserBook(bookState);
	}

	@Override
	public int afterReserBook(int bookNum) throws RemoteException {
		return dao.afterReserBook(bookNum);
	}
}
