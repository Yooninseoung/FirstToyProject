package manage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderDAO {

	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;

	public OrderDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			e.printStackTrace();

		}
	}

	public List<OrderVO> listOrder(){
		List<OrderVO> orderList = new ArrayList<OrderVO>();
		try {

			con = dataFactory.getConnection();
			String query ="SELECT LEVEL, orderNO, parentNO, content, ok, id, orderDate" 
					+" from pc_order"
					+" START WITH parentNO=0"
					+" CONNECT BY PRIOR orderNO=parentNO"
					+" ORDER SIBLINGS BY orderDate asc";
			System.out.println("prestatement : "+query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("level");
				int orderNO = rs.getInt("orderNO");
				int parentNO = rs.getInt("parentNO");
				String content = rs.getString("content");
				int ok = rs.getInt("ok");
				String id = rs.getString("id");
				Date orderDate = rs.getDate("orderDate");
				
				OrderVO orderVO = new OrderVO(level, orderNO, parentNO, content, ok, id, orderDate);
				orderList.add(orderVO);


			}

			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	public void addOrder(OrderVO orderVO) {
		
		try {
			con=dataFactory.getConnection();
			int parentNO = orderVO.getParentNO();
			String content = orderVO.getContent();
			int ok= orderVO.getOk();
			String id = orderVO.getId();
			
			String query = "insert into pc_order (parentNO, content, ok, id) values (?,?,?,?)";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, parentNO);
			pstmt.setString(2, content);;
			pstmt.setInt(3, ok);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		
	}

}
