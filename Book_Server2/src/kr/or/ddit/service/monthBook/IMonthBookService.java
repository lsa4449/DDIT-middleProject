package kr.or.ddit.service.monthBook;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MonthBookVO;
import kr.or.ddit.vo.MonthFileVO;

public interface IMonthBookService extends Remote{
	
	public List<MonthBookVO> selectMonthBookAll() throws RemoteException;
	
	public int insertMonthBook(MonthBookVO mVo) throws RemoteException;
	
	public int deleteMonthBook(int monthBookNum) throws RemoteException;
	
	public MonthFileVO selectFiles(int num) throws RemoteException;
	
	public int insertfiles(MonthFileVO vo) throws RemoteException;

}
