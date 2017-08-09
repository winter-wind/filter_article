<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<center>
		<br><br>
		<form action="AuthorityServlet?method=getAuthorities" method="post">
			name: <input type="text" name="username"/>
			<input type="submit" value="Submit"/>
		</form>
	
	

	<c:if test="${requestScope.user != unll }">
		<br><br>
		${requestScope.user.username } 的权限是：
		<br><br>
		<form action="AuthorityServlet?method=UpdateAuthorities" method="post">
			
			<input type="hidden" name="username" value="${requestScope.user.username }"/>
			
			<!-- 此循环输出所有url -->
			<c:forEach items="${authorities }" var="auth">
				<c:set var="flag" value="false"></c:set>
				
					<c:forEach items="${user.authorities }" var="ua">
						<c:if test="${ua.url == auth.url }">
							<c:set var="flag" value="true"></c:set>
						</c:if>
					
					</c:forEach>
					<!-- 此循环对输出的url进行比较是否为选中（有权限） -->
					<c:if test="${flag == true }">
						<input type="checkbox" name="authority" 
						value="${auth.url }" checked="checked" />${auth.displayName }
					</c:if>
					<c:if test="${flag == false }">
						<input type="checkbox" name="authority" 
						value="${auth.url }" />${auth.displayName }
					</c:if>
					
					<br><br>
			</c:forEach>
			
			<!--  
			<input type="checkbox" name="authority" value="Aritcle-1"/>Aritcle-1
			<br><br>
			<input type="checkbox" name="authority" value="Aritcle-2"/>Aritcle-2
			<br><br>
			<input type="checkbox" name="authority" value="Aritcle-3"/>Aritcle-3
			<br><br>
			<input type="checkbox" name="authority" value="Aritcle-4"/>Aritcle-4
			<br><br>
			-->
			
			<input type="submit" value="Update"/>
		</form>
	</c:if>
	</center>
</body>
</html>