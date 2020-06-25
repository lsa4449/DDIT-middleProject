package kr.or.ddit.dao.ebook;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.dao.community.CommunityDaoImpl;
import kr.or.ddit.dao.community.ICommunityDao;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.EBookVO;

public class EBookDaoImpl implements IEBookDao {
	
	private SqlMapClient smc;
	private static IEBookDao dao;
	
	private EBookDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IEBookDao getInstance() {
		if(dao==null) {
			dao = new EBookDaoImpl();
		} 
		return dao;
	}
	
	@Override
	public List<EBookVO> selectEBookAll() {
		List<EBookVO> list = null;
		
		try {
			list = smc.queryForList("ebook.selectEBookAll");
		}catch (Exception e) {
			System.out.println("Ebook에러");
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<EBookVO> selectEBookAuthor(EBookVO vo) {
		
		List<EBookVO> list=null;
		
		try {
			
			list = smc.queryForList("ebook.selectEBookAuthor",vo);
			
		}catch (Exception e) {
			
			System.out.println("저자검색 에러");
			
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	@Override
	public List<EBookVO> selectEBookName(EBookVO vo) {
		
		List<EBookVO> list = null;
		
		try {
			
			list = smc.queryForList("ebook.selectEBookName",vo);
			
		}catch (Exception e) {
			System.out.println("제목 검색 에러");
			e.printStackTrace();
		}
		return list;
	}
}
