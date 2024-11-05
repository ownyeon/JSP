<%@ page contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>

<%@ page import="info.beans.InfoBean"%>  

<!-- InfoBean bean = new InfoBean();을 쓴 게 바로 아래임. -->
<jsp:useBean id="bean" class="info.beans.InfoBean">
	<!-- id값이 name에 지정 / 앞에 폼 name의 이름을 property로 넣어줘야 함.
	위 태그에 쓴 class를 bean이라고 하는 거임-->
	<jsp:setProperty name='bean' property='*'/> <!-- set -->
<%-- 	<jsp:setProperty name='bean' property='id'/> --%>
</jsp:useBean>

<!DOCTYPE html>
<html>
<body>
	<h2>  당신의 신상명세서 확인 </h2>
	<!-- property='name' 변수명 아님 get + Name => getName()함수를 호출 -->
	이   름  : 	<jsp:getProperty name='bean' property='name'/><!-- get --><br/>
	주민번호 : 	<jsp:getProperty name='bean' property='id'/><br/>
	성  별   : <jsp:getProperty name='bean' property='gender'/><br/>  
	맞습니까????
</body>
</html>