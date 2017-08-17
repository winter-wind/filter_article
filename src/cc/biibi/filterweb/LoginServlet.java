package cc.biibi.filterweb;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��ȡ����
		String methodName = request.getParameter("method");
		
		try {
			Method method = getClass().getMethod(methodName, 
					HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private UserDao userDao = new UserDao();
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡname
		String name = request.getParameter("name");
		
		//2.����userDao ��ȡ�û���Ϣ�����û���Ϣ���뵽HttpSession��
		User user = userDao.get(name);
		request.getSession().setAttribute("user", user);
		
		//3.�ض���articles.jsp
		response.sendRedirect(request.getContextPath() + "/articles.jsp");
		
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.��ȡHttpSession
		
		//2.ʹHttpSession ʧЧ
		request.getSession().invalidate();
		
		//3.�ض���/login.jsp
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
}
