<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="temp.*" %> <!-- TEMP패키지에 있는 파일들을 모두 쓸 거임.  -->
<%@ page import="java.util.*" %> 

<%@ page errorPage="02_NormalErrorPage.jsp" %>

<%
EmpDAO dao = EmpDAO.getInstance();  //생성자 함수가 보이지 않아 -> 생성자가 private라. 그래서 EmpDAO(클래스명)으로 접근
ArrayList<EmpVO> list = dao.selectEmp(); //어레이 리스트로 리스트를 받아올 거임. 

%>


<!DOCTYPE html>
<html><head><title> 디비 테스트 </title>
</head>
<body>
 
<div align='center'>
<table border='2' cellSpacing='3'>

  <tr class="title">
    <td>사번</td>
    <td>사원명</td>
    <td>업무</td>
    <td>관리자사번</td>
    <td>입사일</td></tr>

	<!-- (6) 결과출력 -->
	<% for(EmpVO vo : list){  //제너릭을 쓰면 향상된 for문을 사용할 수 있음. %>
	  <tr>
		<td><%= vo.getEmpno() %></td>
		<td><%= vo.getEname() %></td>
		<td><%= vo.getJob() %></td>
		<td><%= vo.getMgr() %></td>
		<td><%= vo.getHiredate() %></td>
	  </tr>
	<% } //end of for%>

</table>
</div>
</body>
</html>
