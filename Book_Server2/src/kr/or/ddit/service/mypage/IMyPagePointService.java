package kr.or.ddit.service.mypage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.PointCategoryVO;
import kr.or.ddit.vo.PointCategoryVO;

public interface IMyPagePointService extends Remote{
	
	public int insertNowPoint(PointCategoryVO pVo) throws RemoteException;
	public String selectAllPoint(int memNum) throws RemoteException;
	public int insertTransPoint(PointCategoryVO pVo) throws RemoteException;
	public String selectAllPointTrans(int memNum) throws RemoteException;

}
