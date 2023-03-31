package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dto.Member;

public class MemberDaoImpl implements MemberDao{

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int insertMember(Connection conn, Member mem) {
		
		// 회원가입 결과 반환 객체 
		Member join = null;
		
		PreparedStatement ps = null;
		
		// insert 수행결과 저장 변수
		int join1 = 0;
		
		String sql="";
		
		sql += "INSERT INTO member ( USERNO, USERID, NICK, EMAIL )";
		sql += " VALUES(?,?,?,?)";
		// 시퀀스를 바로 적용하는 경우
		// 아래의 ps.setInt로 값을 넣으면 안된다
		// 시퀀스 값은 자동으로 올라가기 때문임
		
//		sql += " VALUES(member_seq.nextval,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			
			// ? 파라미터를 편하게 적용시켜 보자
			// 1~4까지 쓰는 것이 아니라 요렇게
//			int paramIdx = 1;
//			
//			ps.setInt(paramIdx++, param.getUserno());
//			ps.setString(paramIdx++,param.getUserid());
//			ps.setString(paramIdx++, param.getNick());
//			ps.setString(paramIdx++, param.getEmail());
			
			ps.setInt(1, mem.getUserno());
			ps.setString(2,mem.getUserid());
			ps.setString(3, mem.getNick());
			ps.setString(4, mem.getEmail());
			// insert 결과 리턴
			join1 = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		// 최종 결과 리턴
		return join1;
	} 


	@Override         
	public int getSeq(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT member_seq.nextval seq";
//		sql += "SELECT member_seq.nextval";
		sql += " FROM DUAL";
		
		int sqnum = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				sqnum = rs.getInt("seq");
				
//				sqnum = rs.getInt(1);
//				Dual로 조회한 열의 이름을 특별히 지정하지 않은 경우
//				getInt(1)로 작성. ResultSet의 1번째 값 == dual테이블의 1번째 데이터
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sqnum;
	}


	@Override
	public Member selectResult(Connection conn, Member member) {
		
		Member rmem = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT * FROM member";
		sql += " WHERE Userno = ?";
		
		try {
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,member.getUserno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				rmem = new Member();
				rmem.setUserno(rs.getInt("userno"));
				rmem.setUserid(rs.getString("userid"));
				rmem.setNick(rs.getString("nick"));
				rmem.setEmail(rs.getString("email"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return rmem;
	}


	@Override
	public Member selectbyUserno(Connection connection, int seq) {
		
		String sql = "";
		sql += "Select";
		sql += " userno, userid, nick, email";
		sql += " From member";
		sql += " Where userno = ?";
		
		Member member = null;
		
		try {
			
			ps = connection.prepareStatement(sql);
			ps.setInt(1, seq);
			rs = ps.executeQuery();
			
			while ( rs.next() ) {
				
				member = new Member();
				
				member.setUserno(rs.getInt("userno"));
				member.setUserid(rs.getString("userid"));
				member.setNick(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return member;
		
	}
	













}
