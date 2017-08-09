<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<a href="article-1.jsp">Article111 Page</a>
<br><br>
<a href="article-2.jsp">Article222 Page</a>
<br><br>
<a href="article-3.jsp">Article333 Page</a>
<br><br>
<a href="article-4.jsp">Article444 Page</a>
<br><br>

<a href="LoginServlet?method=logout">logout...</a>

<br><br>


<form action="index.jsp" method="post">
<script src="js/WdatePicker.js" type="text/javascript"></script>
<input class="Wdate" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" name="orderDate"> <font color=red></font>
<input type="submit" value="Submit">
</form>

</body>
</html>