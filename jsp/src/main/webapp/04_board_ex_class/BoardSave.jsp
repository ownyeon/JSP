<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="board_ex.model.*,board_ex.service.*" %>

<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="rec" class="board_ex.model.BoardVO">
	<jsp:setProperty name="rec" property="*"/>
</jsp:useBean>

<%	
	int seq = WriteArticleService.getInstance().write(rec);  
	//BoardSave.jsp에서 새로고침을 하게 되면 중복 입력되기에
	// 추후에 입력 후 BoardView.jsp?id=글번호 로 넘겨서 처리하고자
	//* id로 받는 게 아니라 반드시 seq로 받아줘야 함. 
	
	//1. 입력을 하고 나서 해당하는 그 글번호를 가지고 오는지 확인 
	//2. 글을 쓰고, 상세보기가 뜨는 걸 확인 했으면 새로고침 했을 때 조회수가 계속 올라가는 걸 확인할 수 있다. 
	response.sendRedirect("BoardView.jsp?seq=" + seq);	
	
	
%>
