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
    	
    	//��ȡServletPath,������/filter_article/article1.jsp
    	String servletPath = request.getServletPath();
    	
    	//����Ҫ�����ص�url�б�
    	List<String> uncheckedUrls = Arrays.asList("/403.jsp","/articles.jsp",
    			"/authority-manager.jsp","/login.jsp","/logout.jsp","/index.jsp");
    	
    	//����ȡ��url�Ƿ�����ڲ����ص�url�б��ڣ�����ǲ�����
    	if(uncheckedUrls.contains(servletPath)){
    		chain.doFilter(request, response);
    		return;
    	}
    	
    	//���û��Ѿ���¼����ʹ���û��Ƿ��¼�Ĺ�������������£���ȡ�û���Ϣ��session.getAttribute("user")
    	User user = (User)request.getSession().getAttribute("user");
    	if(user == null){
    		response.sendRedirect(request.getContextPath() + "/login.jsp");
    		return;
    	}
    	
    	//�ڻ�ȡ�û������е�Ȩ�޵���Ϣ��List<Authority>
    	List<Authority> authorities = user.getAuthorities();
    	
    	//�����û��Ƿ������� servletPath��Ȩ�ޣ�����˼�����˱������⣬��û�и��õ�ʵ�ַ�ʽ
    	Authority authority = new Authority(null,servletPath);
    	
    	//����Ȩ������Ӧ
    	if(authorities.contains(authority)){
    		chain.doFilter(request, response);
    		return;
    	}
    	
    	//��û��Ȩ�ޣ��ض���403.jsp
    	response.sendRedirect(request.getContextPath() + "/403.jsp");
    	return;
    }
    

}
