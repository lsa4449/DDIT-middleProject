package kr.or.ddit.dao.book;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.WannaBookVO;



public class WannaBookDaoImpl implements IWannaBookDao{
	
	private SqlMapClient smc;
	private static IWannaBookDao dao;
	
	private WannaBookDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}	
	
	public static IWannaBookDao getInstance() {
		if(dao==null) {
			dao = new WannaBookDaoImpl();
		} 
		return dao;
	}
	
	@Override
	public int insertWannaBook(WannaBookVO wannaBookvo) {
		int cnt = 0;
		try {
			Object object = smc.insert("wannaBook.insertWannaBook", wannaBookvo);
			
			if(object ==null) {
				cnt = 1;
			}
		}catch (Exception e) {
			System.out.println("희망도서 신청 에러");
			e.printStackTrace();
		}
				
		return cnt;
	}

	@Override
	public int updateAcceptWannaBook(WannaBookVO wannaBookvo) {
		int cnt =0;
		try {
			cnt = smc.update("wannaBook.updateAcceptWannaBook","1");
		}catch (Exception e) {
			System.out.println("희망도서 승인 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateRejectWannaBook(WannaBookVO wannaBookvo) {
		int cnt =0;
		try {
			cnt = smc.update("wannaBook.updateRejectWannaBook","2");
		}catch (Exception e) {
			System.out.println("희망도서 반려 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<WannaBookVO> selectWannaBook() {
		
		List<WannaBookVO> list = null;
		try {
			list = smc.queryForList("wannaBook.selectWannaBook");
		}catch (Exception e) {
			System.out.println("희망도서 내역 조회 에러");
			e.printStackTrace();
		}
		
		return list;
	}
//희망도서
	
	
	@Override
	public int deleteWannBook(WannaBookVO wannaBookVO) {
		int cnt = 0;
		try {
			cnt = smc.delete("wannaBook.deleteWannBook");
		}catch (Exception e) {
			System.out.println("희망도서 삭제 에러");
			e.printStackTrace();
		}
		return cnt;
		
	}
}
