package kr.or.ddit.service.mypage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.PointCategoryVO;

public interface IMyPagePointService extends Remote{
	// 나의 포인트

	/**
	 * 포인트 등록 
	 * @param pointCategoryVO
	 * @author sua
	 */
	public int insertNowPoint(PointCategoryVO pVo) throws RemoteException;

	/**
	 * 총 포인트 조회
	 * @param pointCategoryVO
	 * @author sua
	 */
	public String selectAllPoint(int memNum) throws RemoteException;
	
	public int insertTransPoint(PointCategoryVO pVo) throws RemoteException;

	public String selectAllPointTrans(int memNum) throws RemoteException;

}
