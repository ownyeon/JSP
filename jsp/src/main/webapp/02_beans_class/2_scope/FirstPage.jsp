<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%  //--------------------------- 값 지정
	pageContext.setAttribute("name", "KIM");//이 페이지에서만 인지 
	request.setAttribute("name", "SEUNGMIN"); //다른 페이지를 가면 다른 요청으로 나오지 않음.
	session.setAttribute("name", "DOKDO"); //세션에 저장 
	application.setAttribute("name", "KOREA"); //현재 구동되고 있는 애들한테 저장 (우리는x)
	//application.log("FirstPage.jsp : " + pageContext.getAttribute("name") + "님 접속");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 첫번째 페이지 </title>
</head>
<body>

하나의 페이지 속성 : <%= pageContext.getAttribute("name") %> <br>
하나의 요청 속성 :	 <%= request.getAttribute("name") %> <br>
하나의 세션 속성 :	 <%= session.getAttribute("name") %> <br>
하나의 어플리케이션 속성 : <%= application.getAttribute("name") %> <br>

<a href="SecondPage.jsp">다음페이지</a>

<!-- #############  -->
<!-- 페이지 forward 이동된다면  
request로 firstpage가 url로 쓰지만, 두번째 페이지가 뜬다. 
forward를 쓰면 두번째 페이지에서 첫번째거를 받아서 쓸 수 있다는 거임. **

request: 하나의 요청
session: 브라우저 
-->
		<jsp:forward page="SecondPage.jsp"></jsp:forward> <!-- request에 저장한 게 먹힘 -->

</body>
</html>