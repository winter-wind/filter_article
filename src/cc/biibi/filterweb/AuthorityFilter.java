package cc.biibi.filterweb;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("*.jsp")
public class AuthorityFilter extends HttpFilter {

	
	private static final long serialVersionUID = 1L;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    		throws IOException, ServletException {
    	
    	//获取ServletPath,类似于/filter_article/article1.jsp
    	String servletPath = request.getServletPath();
    	
    	//不需要被拦截的url列表
    	List<String> uncheckedUrls = Arrays.asList("/403.jsp","/articles.jsp",
    			"/authority-manager.jsp","/login.jsp","/logout.jsp","/index.jsp");
    	
    	//检查获取的url是否包含在不拦截的url列表内，如果是不过滤
    	if(uncheckedUrls.contains(servletPath)){
    		chain.doFilter(request, response);
    		return;
    	}
    	
    	//在用户已经登录（可使用用户是否登录的过滤器）的情况下，获取用户信息，session.getAttribute("user")
    	User user = (User)request.getSession().getAttribute("user");
    	if(user == null){
    		response.sendRedirect(request.getContextPath() + "/login.jsp");
    		return;
    	}
    	
    	//在获取用户所具有的权限的信息：List<Authority>
    	List<Authority> authorities = user.getAuthorities();
    	
    	//检验用户是否有请求 servletPath的权限：可以思考除了遍历以外，有没有更好的实现方式
    	Authority authority = new Authority(null,servletPath);
    	
    	//若有权限则：响应
    	if(authorities.contains(authority)){
    		chain.doFilter(request, response);
    		return;
    	}
    	
    	//若没有权限：重定向到403.jsp
    	response.sendRedirect(request.getContextPath() + "/403.jsp");
    	return;
    }
    

}
