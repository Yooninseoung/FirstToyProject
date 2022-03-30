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
		
		if(action == null || action.equals("/listOrder.do")) { // action�� ��ΰ� ���ų� listOrder.do��� ����Ʈȭ���� ������
			List<OrderVO> orderList = orderDAO.listOrder();
			request.setAttribute("orderList", orderList);
			nextPage = "/manage/orderList.jsp";

		}else if(action.equals("/orderContent.do")) { //���� �ֹ� �޴��� ����(��) //- ��� ���� //- ����ڴ� �ֹ��Ϸ� ǥ��.jsp
			int parentNO=1; //�̿ϼ� 1 �ϼ� 2 ������ ����(�ϼ����ο� ���� ������ �ٸ��� �ϱ�����)
			String orderContent = request.getParameter("orderContent"); //�ֹ� ����
			int ok=0; //�ϼ�����
			String orderId = request.getParameter("orderId");//�ֹ����� id
			OrderVO orderVO = new OrderVO(parentNO, orderContent, ok, orderId);
			orderDAO.addOrder(orderVO);
			
			System.out.println("�ֹ� ���� : " + orderId+"  " +orderContent);
			nextPage="/order/orderCom.jsp";
			
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
		
	}

}
