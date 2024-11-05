<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 방명록 </title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js" ></script>
<script type="text/javascript">

$(function () {
	//메시지 남기기 버튼이 눌러졌을 때 
	$('input[value="메세지 남기기"]').click(function () {
	
		$('form[name="frm"]').submit();
		
	})
})


</script>

</head>

<body>

	<form action="saveMessage.jsp" name="frm" method="post">
		이름 : <input type="text" name="guestName" required /><br/><br/>
		암호 : <input type="password" name="password" required /><br/><br/>
		메세지 : <textarea name="message" rows="3" cols="30" required></textarea><br/><br/>
		<input type="button" value="메세지 남기기">
	</form>
</body>
</html>