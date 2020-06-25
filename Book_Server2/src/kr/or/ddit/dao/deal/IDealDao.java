package kr.or.ddit.dao.deal;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.DealVO;

public interface IDealDao {
	public List<DealVO> selectAllDeal(int num) throws RemoteException;
	public int insertDeal(int num1, int num2, int num3) throws RemoteException;
}
