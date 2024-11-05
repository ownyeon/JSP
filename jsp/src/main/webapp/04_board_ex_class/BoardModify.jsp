<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board_ex.model.*,board_ex.service.*" %>
  
<%
	// 0. 넘겨받는 데이타의 한글 처리
	
%>

<!-- 1. 이전 화면의 입력값을 넘겨받아 BoardVO 객체의 각 멤버변수로 지정
다음과 같이 지정할 수 있음. -->
<%-- <jsp:useBean id="rec" class="board_ex.model.BoardVO">
	<jsp:setProperty name="rec" property="*"/>
</jsp:useBean> --%>

<%
    // 1. 이전 화면에서 입력받은 값을 받아와서 BoardVO 객체에 설정
    String seq = request.getParameter("seq");
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String pass = request.getParameter("pass");

    BoardVO boardVO = new BoardVO();
    boardVO.setSeq(Integer.parseInt(seq));
    boardVO.setTitle(title);
    boardVO.setContent(content);
    boardVO.setPass(pass);

    // 2. Service에 update() 호출하여 레코드 수정
    ModifyArticleService service = ModifyArticleService.getInstance();
    int result = service.update(boardVO);
    
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판글수정</title>
</head>
<body>

<%  

// 게시글 수정이 성공적으로 되었다면 그 해당 게시글을 보여주는 페이지로 이동하고
	response.sendRedirect("BoardView.jsp?seq=" + seq);	
    // 그렇지 않다면, "암호가 잘못 입력되었습니다"를 출력
	
%>

</body>
</html>