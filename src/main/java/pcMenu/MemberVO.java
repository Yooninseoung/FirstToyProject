package pcMenu;

import java.sql.Date;

public class MemberVO { //회원정보를 담을 수 있는 VO
	private String id;
	private String pwd;
	private String name;
	private Date joinDate;
	
	public MemberVO() { //생성자
		
	}
	
	public MemberVO(String id, String pwd, String name) {
		this.id=id;
		this.pwd=pwd;
		this.name=name;
	}
	
	public MemberVO(String id, String pwd, String name, Date joinDate) { //생성자
		this.id=id;
		this.pwd=pwd;
		this.name=name;
		this.joinDate=joinDate;
	}
	
	// getter / setter 설정

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	

}
