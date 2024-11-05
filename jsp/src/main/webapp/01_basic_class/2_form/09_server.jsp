<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 0. 패키지 임포트 -->
<%@ page import ='temp.*' %>

<%
	EmpVO vo = new EmpVO();
	
//1. 이전 화면에서 입력값을 얻어와서 VO객체의 멤버로 지정 
	String empno = request.getParameter("empno");
	vo.setEmpno(Integer.parseInt(empno));	
	String ename = request.getParameter("ename");
	vo.setEname(ename);	
	String deptno = request.getParameter("deptno");
	vo.setDeptno(Integer.parseInt(deptno));
	
	String job = request.getParameter("job");
	vo.setJob(job);
	String sal = request.getParameter("sal");
	vo.setSal(Integer.parseInt(sal));
	

//2. 모델단 dao 클래스의 insertEmp()메소드 호출
	EmpDAO dao = EmpDAO.getInstance(); //싱글톤 패턴 -> 클래스로 접근.getInstance를 사용
	dao.insertEmp(vo);

	//3. 페이지 리다이렉팅
	//응답을 하면서 사용자의 페이지를 바꿀 거임. 
	response.sendRedirect("../1_base/02_DBtest.jsp"); 
	
	
%>

<!-- <!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 사원등록 </title>
</head>
<body>
	 성공적으로 입력되었지 DB에서 확인합니다.
	 
	 <hr/> 상대경로
	 <a href='../1_base/02_DBtest.jsp'>사원목록보기</a>
	  <hr/>절대경로
	 <a href='/jsp/01_basic_class/1_base/02_DBtest.jsp'>사원목록보기</a>
	 
</body>
</html> -->