<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>		


<%	
/*서버가(서버에서 쿠키를 만들어) 클라이언트에 데이터를 저장하는 걸 쿠키라고 한다. */
/*즉, 쿠키가 클라이언트의 웹브라우저에 저장되는 거임. */
	// 1. Cookie 객체 생성
	Cookie c = new Cookie("yourid", "김서연"); //이름과 벨유를 지정해야 함.  
	// 2. 속성 부여
	c.setMaxAge(1*60*3); //second
	// 3. 클라이언트에 쿠키 전송
	//서버에서 클라이언트로 날릴때 -> response 
	//클라이언트에서 서버로 날릴 때 -> request 
	response.addCookie(c);
	
%>

<html>
<head><title>쿠키</title></head>
<body>

<b>Simple Cookie Example</b><hr>

<br><a href="01_GetCookie.jsp"> 쿠키검색 </a>

</body></html>