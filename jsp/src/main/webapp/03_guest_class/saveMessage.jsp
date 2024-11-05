<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="guest.service.WriteMessageService"%>    

<!-- 
	0. 넘겨받는 데이타의 한글처리
	1. 화면의 입력값을 Message 클래스로 전달
	2. Service 클래스의 함수 호출
 -->     
 <!-- id에 변수명을 씀 -->
<jsp:useBean id="m" class='guest.model.Message'/>
<jsp:setProperty name = 'm' property="*"></jsp:setProperty>

 
 <%
 //이전화면의 입력값을 멤버 변수에 저장. 얘를 service함수 안에 wirte함수로 보낼 거임.
//입력하기 
 WriteMessageService service = 
 			WriteMessageService.getInstance();
 
 service.write(m);
 
 
 //목록보기 페이지로 전환 
 response.sendRedirect("listMessage.jsp");
 
 
 %>
 
