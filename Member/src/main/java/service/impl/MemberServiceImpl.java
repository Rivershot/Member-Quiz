package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dao.impl.MemberDaoImpl;
import dto.Member;
import service.face.MemberService;

public class MemberServiceImpl implements MemberService{
	
	//DAO
	private MemberDao memberDao = new MemberDaoImpl();
	
	@Override
	public Member join(Member param) {
		
		// DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//member_seq의 nextval을 조회한다
		// - > INSERT에 활용한다
		// - > 삽입된 DB데이터를 조회할 떄에도 사용한다
		// - > 먼저 시퀀스 값을 조회한 후 파라미터에 넣어줘야 한다
		// - > Dao에서 Service로 된 값(시퀀스)을 넘겨 줄 수 없는 것은 다른 이의 접속, 또는 회원 가입 처리가
		// - > 일어날 때에 정보가 일치하지 않다 ( 시퀀스는 멀티스레드처럼 나, 다른 사람의 값이 다르게 관리됨 )
		
		int seq = memberDao.getSeq(conn);
		
		// 삽입될 데이터에 nextval을 적용
		param.setUserno(seq);
		
		System.out.println("MemberSerivce join() - nextval : " + seq);
		
		// DB에 회원 정보 삽입
		int join = memberDao.insertMember(conn, param);
		
		// 결과 트랜잭션 관리
		if( join > 0) {
			
			// DB 삽입 성공 시
			JDBCTemplate.commit(conn);
			
			System.out.println("이거되냐?");
			System.out.println(param);
			
//			return param;
		} else {
			
			// DB 삽입 실패 시
			JDBCTemplate.rollback(conn);
//			return null;
		}
		
		// DB에 삽입된 데이터를 조회해서 반환하기, 없으면 null
//		방법 1. return memberDao.selectbyUserno(conn, param); 
		// 방법 2. 
		return memberDao.selectbyUserno( conn, seq );
		
	}

	@Override
	public Member result(Member member) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member rmem = memberDao.selectResult(conn, member);
		
		
		return rmem;
	}

	@Override
	public Member getParam(HttpServletRequest req) {
		
		Member member = new Member();
		member.setEmail(req.getParameter("mail"));
		member.setNick(req.getParameter("nick"));
		member.setUserid(req.getParameter("id"));
		
		return member;
	}
	
	
	

}
