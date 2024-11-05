<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--0. 패키지 임포트 -->
<%@ page import = 'review.*' %>

<%

InfoVO vo = new InfoVO();

//1. 이전 화면에서 입력값을 얻어와서 VO객체의 멤버로 지정
String info_id = request.getParameter("info_id");
vo.setInfo_id(info_id);
String info_pw = request.getParameter("info_pw");
vo.setInfo_pw(info_pw);
String info_name = request.getParameter("info_name");
vo.setInfo_name(info_name);

String info_tel = request.getParameter("info_tel");
vo.setInfo_tel(info_tel);

String info_addr = request.getParameter("info_addr");
vo.setInfo_addr(info_addr);

//2. 모델단 dao 클래스의 insertEmp()메소드 호출
	InfoDAO dao = InfoDAO.getInstance(); //싱글톤 패턴 -> 클래스로 접근.getInstance를 사용
	dao.insertInfo(vo); 

	//3. 페이지 리다이렉팅
	//응답을 하면서 사용자의 페이지를 바꿀 거임. 
/* 	response.sendRedirect("../1_base/02_DBtest.jsp"); */ 




%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>