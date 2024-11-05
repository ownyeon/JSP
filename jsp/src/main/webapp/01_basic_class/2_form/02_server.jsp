<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//자바코딩
//1. 이전 화면에서 사용자의 입력값 (선택값) 얻어오기
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String occupation = request.getParameter("occupation");
	
	
	
	String[] hobby= request.getParameterValues("hobby");
	String hobbystr = "";
	/* for(String h : hobby){
		hobbystr += h + "/";
	} */

	if(hobby!=null){
		for(int i=0; i<hobby.length; i++){
			hobbystr += hobby[i] + "/";
		}
	}
%>    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02_server.jsp</title>
</head>
<body>
<!-- 2. 얻어온 사용자의 값을 출력 -->
이름: <%= name %> <br/> <!-- 이름을 출력할 거니까 %=을 사용해야 한다. -->
성별: <%= gender %> <br/> 
직업: <%= occupation %> <br/> 
취미:  <%= hobbystr %>

</body>
</html>