package manage;

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






public class MenuDAO {

	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;

	public MenuDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			e.printStackTrace();

		}
	}

	public List<MenuVO> listMenu(){
		List<MenuVO> menuList = new ArrayList<MenuVO>();
		try {

			con = dataFactory.getConnection();
			String query ="SELECT LEVEL, menuNO, parentNO, food, price, imageFileName" 
					+" from pc_menu"
					+" START WITH parentNO=0"
					+" CONNECT BY PRIOR menuNO=parentNO"
					+" ORDER SIBLINGS BY menuNO asc";
			System.out.println("prestatement : "+query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("level");
				int menuNO = rs.getInt("menuNO");
				int parentNO = rs.getInt("parentNO");
				String food = rs.getString("food");
				int price = rs.getInt("price");
				String imageFileName = rs.getString("imageFileName");
				
				MenuVO menuVO = new MenuVO(level, menuNO, parentNO, food, price, imageFileName);
				menuList.add(menuVO);


			}

			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return menuList;
	}

	
	public void delMenu(int menuNO) {
		try {
			con=dataFactory.getConnection();
			String query = "delete from pc_menu where menuNO=?";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, menuNO);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int insertNewFood(MenuVO menuVO) {
		int menuNO = getNewMenuNO();
		try {
		con=dataFactory.getConnection();
		int parentNO = menuVO.getParentNO();
		String food = menuVO.getFood();
		int price= menuVO.getPrice();
		String imageFileName = menuVO.getImageFileName();
		
		String query = "insert into pc_menu (menuNO, parentNO, food, price, imageFileName) values (?,?,?,?,?)";
		System.out.println(query);
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, menuNO);
		pstmt.setInt(2, parentNO);
		pstmt.setString(3, food);
		pstmt.setInt(4, price);
		pstmt.setString(5, imageFileName);
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return menuNO;
	}
	
	public int getNewMenuNO() {
		int newNO=-1;
		
		try {
			con=dataFactory.getConnection();
			String query = "select max(menuNO) from pc_menu";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				return(rs.getInt(1)+1);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return newNO;
	}
	
	public void updateMenu(MenuVO menuVO) {
		try {
		con=dataFactory.getConnection();
		int menuNO = menuVO.getMenuNO();
		int parentNO = menuVO.getParentNO();
		String food = menuVO.getFood();
		int price= menuVO.getPrice();
		String imageFileName = menuVO.getImageFileName();
		
		String query = "update pc_menu set parentNO=?, food=?, price=?";
		if(imageFileName != null && imageFileName.length()!=0) {
			query += ", imageFileName=? where menuNO = ?";
		}else {
			query += " where menuNO = ?";
		}
		
		System.out.println(query);
		pstmt = con.prepareStatement(query);
		
		pstmt.setInt(1, parentNO);
		pstmt.setString(2, food);
		pstmt.setInt(3, price);
		if(imageFileName != null && imageFileName.length()!=0) {
			
			pstmt.setString(4, imageFileName);
			pstmt.setInt(5, menuNO);
		}else {
			pstmt.setInt(4, menuNO);
		}
		
		
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	//메뉴 상세보기 : 하나의 메뉴 정보를 가져옴
	public MenuVO viewFood(int NO) { //메뉴번호를 매개변수로 받음
		MenuVO menuVO = new MenuVO();
		try {

			con = dataFactory.getConnection();
			String query ="select * from pc_menu where menuNO = ?" ;
			System.out.println("prestatement : "+query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, NO);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int menuNO = rs.getInt("menuNO");
				int parentNO = rs.getInt("parentNO");
				String food = rs.getString("food");
				int price = rs.getInt("price");
				String imageFileName = rs.getString("imageFileName");
				
				
				menuVO.setMenuNO(menuNO);
				menuVO.setParentNO(parentNO);
				menuVO.setFood(food);
				menuVO.setPrice(price);
				menuVO.setImageFileName(imageFileName);

			}

			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return menuVO;
	}
	

}

