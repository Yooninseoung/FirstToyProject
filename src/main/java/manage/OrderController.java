package manage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/orderCon/*")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderDAO orderDAO;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		orderDAO = new OrderDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
			}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage=null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action = " + action);
		
		if(action == null || action.equals("/listOrder.do")) { // action한 경로가 없거나 listOrder.do라면 리스트화면을 보여줌
			List<OrderVO> orderList = orderDAO.listOrder();
			request.setAttribute("orderList", orderList);
			nextPage = "/manage/orderList.jsp";

		}else if(action.equals("/orderContent.do")) { //고객의 주문 메뉴를 받음(완) //- 디비에 저장 //- 사용자는 주문완료 표시.jsp
			int parentNO=1; //미완성 1 완성 2 음식을 뜻함(완성여부에 따라 구성을 다르게 하기위함)
			String orderContent = request.getParameter("orderContent"); //주문 내용
			int ok=0; //완성여부
			String orderId = request.getParameter("orderId");//주문자의 id
			OrderVO orderVO = new OrderVO(parentNO, orderContent, ok, orderId);
			orderDAO.addOrder(orderVO);
			
			System.out.println("주문 접수 : " + orderId+"  " +orderContent);
			nextPage="/order/orderCom.jsp";
			
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
		
	}

}
