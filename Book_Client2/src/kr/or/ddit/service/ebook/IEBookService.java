package kr.or.ddit.service.ebook;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.EBookVO;

public interface IEBookService extends Remote{
	/*
	 * ebook 전체 조회
	 */
	public List<EBookVO> selectEBookAll() throws RemoteException;
	
	/*
	 * 제목으로 조회
	 */
	public List<EBookVO> selectEBookName(EBookVO vo) throws RemoteException;
	
	/*
	 * 저자로 조회
	 */
	public List<EBookVO> selectEBookAuthor(EBookVO vo) throws RemoteException;
	

}
