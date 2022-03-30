package pcMenu;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.MenuDAO;
import manage.MenuVO;



/**
 * Servlet implementation class MemberController
 */
@WebServlet("/memberCon/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();
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

		if(action == null || action.equals("/listMember.do")) { // action�� ��ΰ� ���ų� listMember.do��� ����Ʈȭ���� ������
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/member/listMember.jsp";

		}else if(action.equals("/addMember.do")) {  //ȸ�� �߰� ��Ʈ�ѷ�
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			MemberVO memberVO = new MemberVO(id,pwd,name);
			memberDAO.addMember(memberVO);
			nextPage="/memberCon/listMember.do";
		
		}else if(action.equals("/delMember.do")) { //�����ڰ� �մ��� �����͸� ����
			String id = request.getParameter("id");
			memberDAO.delMember(id);
			nextPage="/memberCon/listMember.do";
		
		}else if(action.equals("/loginCheck.do")) { 
			String id = request.getParameter("id");
			String check = memberDAO.checkMember(id);
			System.out.println(check); // ��ȯ�� Ȯ�� 
			if(check == "-1") { //-1�̶�� ��ġ�ϴ� �����Ͱ� ������ �ǹ�
				nextPage="/member/login.jsp";
			}else if(check.equals("admin")) { //��ȯ ���� admin�̶�� ������ ������ ����
				nextPage="/member/homeManage.jsp";
			}else {
				nextPage="/memberCon/order.do"; //�Ϲ� �����
			}
			
		}else if(action.equals("/order.do")) { 
			MenuDAO menuDAO = new MenuDAO();
			String id=request.getParameter("id");
			List<MenuVO> menuList = menuDAO.listMenu();
			request.setAttribute("menuList", menuList);
			request.setAttribute("id", id);
			nextPage="/order/orderHome.jsp";
		}

		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
