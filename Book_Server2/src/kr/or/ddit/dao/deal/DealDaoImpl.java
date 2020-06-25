package kr.or.ddit.dao.deal;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.dao.usedbookboard.IUserBookBoardDao;
import kr.or.ddit.dao.usedbookboard.UsedBookBoardDaoImpl;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.DealVO;

public class DealDaoImpl implements IDealDao {

	private SqlMapClient smc;
	private static IDealDao dao;
	
	private DealDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IDealDao getInstance() {
		if(dao == null) {
			dao = new DealDaoImpl();
		}
		return dao;
	}
	
	@Override
	public List<DealVO> selectAllDeal(int num) throws RemoteException {
		List<DealVO> list = null;
		try {
			list = (List<DealVO>) smc.queryForList("deal.selectAllDeal", num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertDeal(int num1, int num2, int num3) throws RemoteException {
		int cnt = 0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("memNum", num1);
        map.put("memNum2", num2);
        map.put("account", num3);
        
        try {
			Object obj = smc.insert("deal.insertDeal", map);
			
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	
	

}
