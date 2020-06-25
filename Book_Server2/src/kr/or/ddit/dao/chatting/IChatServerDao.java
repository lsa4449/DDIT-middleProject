package kr.or.ddit.dao.chatting;

import java.util.List;

import kr.or.ddit.vo.ChatVO;
import kr.or.ddit.vo.MessageVO;
import kr.or.ddit.vo.ParticipationVO;

public interface IChatServerDao {
	/**
	 * 사용자 접속 창 출력
	 * @param ChatClient client, String name
	 * @return void
	 */
	public void addClient(IChatClientDao client, String name);
	
	/**
	 * 사용자 퇴장시 
	 * @param ChatClient client, String name
	 * @return void
	 */
	public void disconnect(IChatClientDao client, String name);
	
	/**
	 * 채팅입력
	 * @param String message
	 * @return void
	 */
	public void say(String message);
	
	
	/**
	 * 참여 테이블에 채팅 회원 insert
	 * @param participationVO VO
	 * @return 성공시 null값 -> int cnt로 반환형 재설정
	 */
	public int insertParticipate(ParticipationVO vo);
	
	/**
	 * 채팅방 테이블에 채팅 회원 insert
	 * @return 성공시 null값 -> int cnt로 반환형 재설정
	 * 	 
	 */
	public int insertChat(ChatVO chatVO);
	
	/**
	 * 메세지 테이블에 메세지 정보 insert
	 * 추후 로그아웃했다가 다시 들어와도 내역관리하기 위해서
	 * @param MessageVO messageVO
	 * @return 성공시 null값 -> int cnt로 반환형 재설정 
	 */
	public int insertMessage(MessageVO messageVo);
	
	/**
	 * 해당 회원이 참여중인 채팅방 조회
	 * 참여 테이블에서 회원번호를 키값으로 채팅방 번호 가져오기
	 * @param int memNum -> 회원번호
	 * @return Integer -> 채팅방번호
	 */
	public List<Integer> selectParticipation(int memNum);
	
	/**
	 * 참여테이블에서 구한 채팅방 번호로 채팅방 테이블의 VO 가져오기
	 * @param int ChatNum -> 채팅방번호
	 * @return List<ChatVO> -> 채팅방 테이블 vo
	 * 
	 */
	public List<ChatVO> selectChat(int ChatNum);
	
	/**
	 * 메세지 테이블에서 채팅방 번호와 회원번호가 일치할때 메시지테이블 vo가져오기
	 * @param int chatNum, int memNum
	 * @return List<MessageVO>
	 */
	public List<MessageVO> selectMessage(int chatNum, int memNum);
	
	
	/**
	 * 관리자와 채팅방 조회
	 * @param 
	 * @return 
	 */
	public List<Integer> selectManagerParticipation(int memNum);
}