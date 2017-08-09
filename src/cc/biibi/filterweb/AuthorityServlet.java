package cc.biibi.filterweb;

import java.io.IOException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AuthorityServlet")
public class AuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	public void getAuthorities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("username");
		User user = userDao.get(username);
		
		request.setAttribute("user", user);//设置user类
		request.setAttribute("authorities", userDao.getAuthorities());//设置权限
		request.getRequestDispatcher("/authority-manager.jsp").forward(request, response);
	}
	public void UpdateAuthorities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//隐藏域中的username
		String username = request.getParameter("username");
		//获取页面中的所有authority中的值（url）放入数组
		String[] authorities = request.getParameterValues("authority");
		
		//调用此方法对传入的url进行比较
		List<Authority> authorityList = userDao.getAuthorities(authorities);
		
		userDao.update(username, authorityList);
		response.sendRedirect(request.getContextPath()+"/authority-manager.jsp");
	}
}
