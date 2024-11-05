<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="temp.*" %>    
    
<!DOCTYPE html>
<html>
<head>
<title> 로그인확인 </title>
</head>
<body>
<%

	
	// 이전화면 폼에서 넘겨받는 값
	String user = request.getParameter("User");
	String pass = request.getParameter("Pass");
	
	EmpVO vo = new EmpVO(); //넘겨받은 값
	vo.setEname(user); //아이디 -> 사원명
	vo.setEmpno(Integer.parseInt(pass)); //비빌번호 -> 사번 
			
	EmpDAO dao = EmpDAO.getInstance();
	
	// user, password가 같을 때 로그인 성공, 그렇지 않으면 로그인 실패
	if(  dao.loginCheck(vo) ){
		// #2. 세션에 "id"라는 이름에 변수 user 값을 저장
		session.setAttribute("id",user); //세션의 나의 id값을 저장
		
		// #1. 로그인 성공하면 바로 MainPage.jsp를 요청
		response.sendRedirect("MainPage.jsp");
		
	} else {

		// #1. 로그인에 실패하면 바로 LoginForm.jsp을 요청
		response.sendRedirect("LoginForm.jsp");
	}			
%>
	
</body>
</html>