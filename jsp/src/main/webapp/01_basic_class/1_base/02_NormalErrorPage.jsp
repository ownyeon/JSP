<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>

<!-- 에러를 전담하는 페이지. -->
<%@ page isErrorPage = 'true' %>

<!DOCTYPE html>
<html>
 <head>
  <title> ERROR PAGE </title>
 </head>

 <body>
	<h2>죄송합니다. 요청 처리 과정중 오류가 발생하였습니다.</h2> 
	<br/><br/>
	<img src="imgs/error-image.jpg">
	<br/><br/>
	Error : <%= exception.getMessage() %>
 </body>
</html>
