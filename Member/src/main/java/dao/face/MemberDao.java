package dao.face;

import java.sql.Connection;

import dto.Member;

public interface MemberDao {

	/**
	 * 전달된 파라미터를 DB에 저장한다
	 * 
	 * @param conn - DB 연결 객체
	 * @param param - 테이블에 삽입될 정보 객체 
	 * @return - 0이면 삽입 실패, 1이면 삽입 성공
	 */
	public int insertMember(Connection conn, Member param);

	/**
	 * member_seq의 nextval을 조회한다
	 * 
	 * @param conn - DB 연결 객체
	 * @return member_seq.nextval
	 */
	public int getSeq(Connection conn);

	public Member selectResult(Connection conn, Member member);

	
	/**
	 * useno(pk)를 이용한 데이터 조회
	 * 
	 * @param seq - 조회할 pk 값
	 * @param conn 
	 * @return - 조회된 정보 객체, 없으면 null
	 */
	public Member selectbyUserno(Connection conn, int seq);
}
