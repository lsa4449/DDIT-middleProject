package kr.or.ddit.service.deal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.DealVO;

public interface IDealService extends Remote{
	public List<DealVO> selectAllDeal(int num) throws RemoteException;
	public int insertDeal(int num1, int num2, int num3) throws RemoteException;

	
}
