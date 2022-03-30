package pcMenu;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
private PreparedStatement pstmt;
private Connection con;
private DataSource dataFactory;


public MemberDAO() {
	try {
		Context ctx = new InitialContext();
		Context envContext = (Context)ctx.lookup("java:/comp/env");
		dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
	}catch(Exception e) {
		e.printStackTrace();
		
	}
}

public List<MemberVO> listMembers(){
	List<MemberVO> memberList = new ArrayList<MemberVO>();
	try {
	
		con = dataFactory.getConnection();
		String query = "select * from pc_member";
		System.out.println("prestatement : "+query);
		pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			Date joinDate = rs.getDate("joinDate");
			MemberVO memberVO = new MemberVO(id, pwd, name, joinDate);
			memberList.add(memberVO);
			
			
		}
		
		rs.close();
		pstmt.close();
		con.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
	return memberList;
}


public void addMember(MemberVO m) {
	try {
		con = dataFactory.getConnection();
		String id = m.getId();
		String pwd = m.getPwd();
		String name = m.getName();
		
		String query = "INSERT INTO pc_member(id, pwd, name)" + " VALUES(?,?,?)";
		System.out.println(query);
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		pstmt.setString(3, name);
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
}

public void delMember(String id) {
	try {
		con=dataFactory.getConnection();
		String query = "delete from pc_member where id=?";
		System.out.println(query);
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, id);
		pstmt.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
	}
}

public String checkMember(String _id) {
	String result = "-1"; //회원의 데이터가 없다면 -1를 반환
	try {
		
		con = dataFactory.getConnection();
		String query = "select * from pc_member where id=?";
		System.out.println("prestatement : "+query);
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, _id);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			result = rs.getString("id"); //회원 데이터가 있다면 해당 id를 반환
		}
		rs.close();
		pstmt.close();
		con.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return result;
}

}
