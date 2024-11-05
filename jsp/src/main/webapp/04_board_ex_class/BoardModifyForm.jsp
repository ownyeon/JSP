<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board_ex.service.*, board_ex.model.*" %>
<%
	// 1. 수정할 레코드의 게시글번호를 넘거받기
	String seq = request.getParameter("seq");
	// 2. Service에 getArticleById()함수를 호출하여 그 게시글번호의 레코드를 검색
	BoardVO vo = ViewArticleService.getInstance().getArticleById(seq);//우리가 넘겨받은 seq값으로 넘겨줘야 함. 
	
%>   
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정하기</title>
</head>
 <body>
	<h4> 게시판 글 수정하기 </h4><br/>
	<form name='frm' method='get' action='BoardModify.jsp'>
	<!-- 글번호 히든으로 넘기기, vo의 변수와 name을 맞춰줘야 함.  -->
	<input type='hidden' name='seq' value='<%=seq%>'/>
	제  목 : <input type='text' name = 'title' value='<%=vo.getTitle() %>'><br/><br/> <!-- value에다가 줘야지 내용을 가지고 오니까. -->
	패스워드(수정/삭제시 필요) :
			<input type='password' name='pass'><br/><br/>
	내  용 : <textarea name='content' rows='10' cols='40'><%=vo.getContent() %></textarea><br/><br/>

	<input type='submit' value='수정하기'>
	<input type='button' value='목록보기' onclick="window.location='BoardList.jsp'">
	</form>

</body>
</html>