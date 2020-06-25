package kr.or.ddit.dao.usedbookboard;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FilesVO;
import kr.or.ddit.vo.PriceVO;

public class UsedBookBoardDaoImpl implements IUserBookBoardDao{
	
	private SqlMapClient smc;
	private static IUserBookBoardDao dao;
	
	private UsedBookBoardDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IUserBookBoardDao getInstance() {
		if(dao == null) {
			dao = new UsedBookBoardDaoImpl();
		}
		return dao;
	}

	@Override
	public List<BoardVO> selectAll(int num) {
		List<BoardVO> list = null;

		try {
			list = smc.queryForList("userBookBoard.selectAll", num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BoardVO> selectTitleUserBookBoard(BoardVO vo) {
		List<BoardVO> list = null;

		try {
			list = smc.queryForList("userBookBoard.selectTitleUserBookBoard", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BoardVO> selectMemUserBookBuyBoard(String str) {
		List<BoardVO> list = null;

		try {
			list = smc.queryForList("userBookBoard.selectMemUserBookBuyBoard",str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BoardVO> selectMemUserBookCellBoard(String str) {
		List<BoardVO> list = null;

		try {
			list = smc.queryForList("userBookBoard.selectMemUserBookCellBoard", str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<FilesVO> selectFile(int num) {
		List<FilesVO> list= null;

		try {
			list = smc.queryForList("userBookBoard.selectFile", num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	@Override
	public int insertUserBookBoard(BoardVO vo) {
		int cnt = 0;

		try {
			Object obj = smc.insert("userBookBoard.insertUserBookBoard", vo);
			
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int searchboardNum(){
		int num = 0;
		
		try {
			num = (int)smc.queryForObject("userBookBoard.searchboardNum");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
		
	}

	@Override
	public int insertfile(FilesVO vo) {
		int cnt = 0;

		try {
			Object obj = smc.insert("userBookBoard.insertfile", vo);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}
	
	@Override
	public int deleteUsedBookBoard(int num) {
		int cnt = 0;

		try {
			cnt = smc.delete("userBookBoard.deleteUserBookBoard", num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int deletefile(int num) {
		int cnt = 0;

		try {
			cnt = smc.delete("userBookBoard.deletefile", num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}


	@Override
	public int updateUserBookBoard(BoardVO vo) {
		int cnt = 0;

		try {
			cnt = smc.update("userBookBoard.updateUserBookBoard", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int updatefile(FilesVO vo) {
		int cnt = 0;

		try {
			cnt = smc.update("userBookBoard.updatefile", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public BoardVO detailUserBookBoard(BoardVO vo) {
		BoardVO vo2 = null;
		
		try {
			vo2 = (BoardVO) smc.queryForObject("detailUserBookBoard", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo2;
	}

	@Override
	public int upboardCount(BoardVO vo) throws RemoteException {
		int cnt = 0;

		try {
			cnt = smc.update("userBookBoard.upboardCount", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertBookBoardBuyPrice(PriceVO vo) throws RemoteException {
		int cnt = 0;

		try {
			Object obj = smc.insert("userBookBoard.insertBookBoardBuyPrice", vo);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public PriceVO selectPrice(int num) throws RemoteException {
		PriceVO vo= null;

		try {
			vo = (PriceVO)smc.queryForObject("userBookBoard.selectPrice", num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public int deletePrice(int num) throws RemoteException {
		int cnt = 0;

		try {
			cnt = smc.delete("userBookBoard.deletePrice", num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}
	


}
