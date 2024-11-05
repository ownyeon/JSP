<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- 변수 또는 함수 선언 -->
   <%! String msg; %> 
   
   <!-- 자바코딩 마음대로 쓰자 -->
   <% msg = "안녕하세요"; %>
   
   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
           
우리 팀 화이팅!!  ..... 
<hr/>
<!-- 출력하자. -->
<%= msg %>

</body>
</html>