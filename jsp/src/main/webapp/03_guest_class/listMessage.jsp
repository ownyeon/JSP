<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="guest.model.*,guest.service.*" %>   
<%@ page import="java.util.List" %>
 
<%
	//넘겨오는 파라미터 값 받아오기 
	String pageNum = request.getParameter("page"); //아래에서 url로 지정한 page 번호를 넘겨주기 
	//해당하는 페이지 번호를 목록보기에 넣어줄 거임. 
	
	// 전체 메세지 레코드 검색, mList라는 변수에 Message들을 arraylist형태로 담아놓음 
	ListMessageService service =  ListMessageService.getInstance();
	
	List <Message> mList =  service.getMessageList(pageNum); 
	
	//목록 레코드를 부를 거임. 
	int totalPageCount = service.getTotalPage();
 	 
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 방명록 목록 </title>
</head>
<body>

	<% if( mList.isEmpty() ) { %>
		남겨진 메세지가 하나도~~없습니다. <br>
	<% } else { %>
	<table border="1">
	
	<!-- 제네릭스 사용한 이유는 향상된 for문을 사용하기 위해서이다. -->
		<% for(Message m :mList){ %>
		<tr>	
			<td> <%=m.getId() %> </td> 
			<td> <%=m.getGuestName() %></td> 
			<!-- 번호값을 파라미터로 넘겨주기. -->
			<td> <a href='deleteMessage.jsp?messageId=<%=m.getId() %>'>[삭제하기]</a>  </td>			
		</tr>
		<tr>
			<td colspan='3'> 
			<!-- textarea는 개행을 해버리면 db값도 같이 개행됨. 그래서 한줄로 써야 함. -->
			<textarea cols=35 rows=3 style="font-family: '돋움', '돋움체'; font-size: 10pt; font-style: normal; line-height: normal; color: #003399;background-color:#D4EBFF;border:1 solid #00009C;"><%= m.getMessage() %></textarea>
			</td>
		</tr>
<%} %>
	</table>
	
	<% } // end if-else %>

<hr/>
<!-- 페이지 번호 출력  -->
<%  for(int i=1; i<=totalPageCount; i++){%>
	
<a href='listMessage.jsp?page=<%= i%>'>[<%= i %>]</a>
<%} %>
<hr/>

	<!-- 글쓰기를 했을 때 들어가고 싶음 -->
	<a href="insertMessage.jsp">글쓰기</a>
</body>
</html>