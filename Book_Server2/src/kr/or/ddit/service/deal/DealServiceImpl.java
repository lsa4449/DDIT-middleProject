package kr.or.ddit.service.deal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.deal.DealDaoImpl;
import kr.or.ddit.dao.deal.IDealDao;
import kr.or.ddit.vo.DealVO;

public class DealServiceImpl extends UnicastRemoteObject implements IDealService{
	
	private IDealDao dao;
	private static IDealService service;
	
	private DealServiceImpl() throws RemoteException {
		super();
		dao = DealDaoImpl.getInstance();
	}
	
	public static IDealService getInstance() throws RemoteException{
		if(service == null) {
			service = new DealServiceImpl();
		}
		return service;
	}

	@Override
	public List<DealVO> selectAllDeal(int num) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.selectAllDeal(num);
	}

	@Override
	public int insertDeal(int num1, int num2, int num3) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.insertDeal(num1, num2, num3);
	}


	
	
	
}
