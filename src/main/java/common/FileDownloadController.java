package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownloadController
 */
@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "C:\\MenuImg\\image";
	private static final long serialVersionUID = 1L;

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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String imageFileName = request.getParameter("imageFileName");
		String foodNO = request.getParameter("foodNO");
		System.out.println("imageFileName="+imageFileName);
		System.out.println("foodNO="+foodNO);
		OutputStream out = response.getOutputStream();
		String path = ARTICLE_IMAGE_REPO+"\\"+foodNO + "\\" + imageFileName;
		File imageFile = new File(path);

		response.setHeader("Cahche-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+imageFileName);

		FileInputStream in = new FileInputStream(imageFile);
		byte[] buffer = new byte[1024*8];
		while(true) {
			int count = in.read(buffer);
			if(count == -1 ) {
				break;
			}
			out.write(buffer,0,count);
		}
		in.close();
		out.close();

	}

}


