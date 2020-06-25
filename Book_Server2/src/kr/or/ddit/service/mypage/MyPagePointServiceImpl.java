package kr.or.ddit.service.mypage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.mypage.IMyPagePointDao;
import kr.or.ddit.dao.mypage.MyPagePointDaoImpl;
import kr.or.ddit.vo.PointCategoryVO;

public class MyPagePointServiceImpl extends UnicastRemoteObject implements IMyPagePointService{
	
	private static IMyPagePointService service;
	private IMyPagePointDao dao;
	
	private MyPagePointServiceImpl() throws RemoteException {
		dao = MyPagePointDaoImpl.getInstance();
	} 
	
	public static IMyPagePointService getInstance() throws RemoteException{
		if(service == null) {
			service = new MyPagePointServiceImpl();
		}
	return service;
	}
	
	@Override

	public int insertNowPoint(PointCategoryVO pVo) throws RemoteException {
		return dao.insertNowPoint(pVo);
	}

	@Override
	public String selectAllPoint(int memNum) throws RemoteException {
		return dao.selectAllPoint(memNum);
	}

	@Override
	public int insertTransPoint(PointCategoryVO pVo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.insertTransPoint(pVo);
	}

	@Override
	public String selectAllPointTrans(int memNum) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.selectAllPointTrans(memNum);
	}

}
