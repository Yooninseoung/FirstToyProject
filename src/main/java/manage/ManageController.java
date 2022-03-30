package manage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import pcMenu.MemberDAO;
import pcMenu.MemberVO;



/**
 * Servlet implementation class ManageController
 */
@WebServlet("/manageCon/*")
public class ManageController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "C:\\MenuImg\\image";
	private static final long serialVersionUID = 1L;
	MenuDAO menuDAO;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		menuDAO = new MenuDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request , response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request , response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage=null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action = " + action);
		
		if(action == null || action.equals("/listMenu.do")) { // action한 경로가 없거나 listMember.do라면 리스트화면을 보여줌
			List<MenuVO> menuList = menuDAO.listMenu();
			request.setAttribute("menuList", menuList);
			nextPage = "/manage/listMenu.jsp";

		}else if(action.equals("/delMenu.do")) { //관리자가 손님의 데이터를 삭제
			String menuNO = request.getParameter("menuNO");
			menuDAO.delMenu(Integer.parseInt(menuNO));
			
			File imgDir =new File(ARTICLE_IMAGE_REPO +"\\" +menuNO);
			if(imgDir.exists()) {
				FileUtils.deleteDirectory(imgDir);
			}
			PrintWriter pw = response.getWriter();
			pw.print("<script>alert('메뉴 삭제 완료'); location.href='"+request.getContextPath()+"/manageCon/listMenu.do'; </script>");

			return;
		
		}else if(action.equals("/foodForm.do")) {
			nextPage="/manage/foodForm.jsp";
			
		}else if(action.equals("/addFood.do")) {
			
			int foodNO = 0; //메뉴의 사진을 파일별로 관리하기 위해 번호를 이용하여 경로 구별
			Map<String, String> menuMap = upload(request, response); // menuNO은 데이터를 생성시 설정
			int parentNO = Integer.parseInt(menuMap.get("parentNO")); // 폼에서 누구의 자식인지 알아 와야함 (종류)
			String food = menuMap.get("food");
			int price = Integer.parseInt(menuMap.get("price"));
			String imageFileName = menuMap.get("imageFileName");
			
			
			MenuVO menuVO = new MenuVO();
			menuVO.setParentNO(parentNO);
			menuVO.setFood(food);
			menuVO.setPrice(price);
			menuVO.setImageFileName(imageFileName);
			
			foodNO = menuDAO.insertNewFood(menuVO);
			
			//임시 파일(temp)을 각 메뉴번호의 경로파일로 이동
			if(imageFileName != null && imageFileName.length()!=0) {
				File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName); //임시 파일
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+foodNO); //실제 저장할 파일
				destDir.mkdirs(); //destDir의 파일 경로가 없다면 생성
				FileUtils.moveToDirectory(srcFile, destDir, false);//srcFile을 destDir로 이동
			}
			PrintWriter pw = response.getWriter();
			pw.print("<script>alert('메뉴 추가 완료'); location.href='"+request.getContextPath()+"/manageCon/listMenu.do'; </script>");

			return;
			
		}else if(action.equals("/viewMenu.do")) { //NO을 통해 메뉴 확인
			String menuNO = request.getParameter("menuNO");
			MenuVO menuVO = menuDAO.viewFood(Integer.parseInt(menuNO));
			request.setAttribute("foodView", menuVO);
			nextPage="/manage/viewFood.jsp";
			
		}else if(action.equals("/modMenu.do")) { //메뉴 수정
			Map<String, String> menuMap = upload(request, response);
			
			int menuNO = Integer.parseInt(menuMap.get("menuNO"));
			int parentNO = Integer.parseInt(menuMap.get("parentNO"));
			String food = menuMap.get("food");
			int price = Integer.parseInt(menuMap.get("price"));
			String imageFileName = menuMap.get("imageFileName");
			
			
			MenuVO menuVO = new MenuVO();
			menuVO.setMenuNO(menuNO);
			menuVO.setParentNO(parentNO);
			menuVO.setFood(food);
			menuVO.setPrice(price);
			menuVO.setImageFileName(imageFileName);
			
			menuDAO.updateMenu(menuVO);
			
			if(imageFileName != null && imageFileName.length()!=0) {
				String originalFileName = menuMap.get("originalFileName");
				File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName); //임시 파일
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+menuNO); //실제 저장할 파일
				destDir.mkdirs(); //destDir의 파일 경로가 없다면 생성
				FileUtils.moveToDirectory(srcFile, destDir, false);//srcFile을 destDir로 이동
				File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+menuNO+"\\"+originalFileName);
				oldFile.delete();
			}
			PrintWriter pw = response.getWriter();
			pw.print("<script>alert('메뉴 수정 완료'); location.href='"+request.getContextPath()+"/manageCon/viewMenu.do?menuNO="+menuNO+"'; </script>");

			return;
			
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
	}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String,String>();
		
		String encoding="utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest(request);
			for(int i =0; i < items.size();i++) {
				FileItem fileItem = (FileItem)items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName()+ "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {
					System.out.println("매개변수 이름: " + fileItem.getFieldName());
					System.out.println("파일 이름 : : " + fileItem.getName());
					System.out.println("파일 크기: " + fileItem.getSize() + "bytes");
					if(fileItem.getSize()>0) {
						int idx=fileItem.getName().lastIndexOf("\\");
						if(idx==-1) {
							idx=fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx+1);
						articleMap.put(fileItem.getFieldName(), fileName);
						File uploadFile = new File(currentDirPath+"\\temp\\"+fileName); //일단 temp에 임시로 파일을 저장
					
						fileItem.write(uploadFile);
						
					}
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}

}


