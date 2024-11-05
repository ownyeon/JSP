<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//listMassage.jsp에서 messageId가지고 오기 
String id = request.getParameter("messageId"); //글번호를 받아서 변수에 저장


%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 방명록 삭제 </title>
</head>
<body>
	메세지를 삭제하려면 암호를 입력하세요. <br/><br/>
	<form action="deleteConfirm.jsp" method="post">
	<!-- 폼태그 안에다가 글번호를 넘길거임. 이걸 개발자모드에서 elements에서 볼 수 있음 -->
		<input type='hidden' name='messageId' value='<%=id%>'/>
		암호 : <input type="password" name="password" />
		<input type="submit" value="메세지 삭제"/>
	</form>
</body>
</html>