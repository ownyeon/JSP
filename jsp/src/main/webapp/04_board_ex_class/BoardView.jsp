<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="board_ex.service.*, board_ex.model.*" %> 
<%
	// 게시글번호 넘겨받아
	String seq = request.getParameter("seq");
	// 서비스의 함수를 호출하여 해당 BoardVO를 넘겨받는다.
	BoardVO vo = ViewArticleService.getInstance().getArticleById(seq); //seq값을 넣어준다. (id)



%>    

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js" ></script>

<script type="text/javascript">
$(function () {
	//댓글추가 버튼이 눌렸을 때 
	$('#replyBtn').click(function () {
		
		var param = {
				seq 		: $('#seq').val() 
				, username :  $('#username').val() 
				, reply :  $('#reply').val() 
					
		}
		
		//console.log(param);
		$.ajax({
			type:'post',
			data: param,
			url: 'ReplySave.jsp',
			success : function () {
				$('#username').val('');
				$('#reply').val('');
			},
			error : function (err) {
				alert('댓글입력실패');
				console.log(err);
			}
			
		})
		
	})
	
})
</script>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> 게시글 보기 </title>
</head>
<body>

	<h4> 게시판 글 보기 </h4><br/>
	<table border="1" bordercolor="red">
	<tr>
		<td> 제  목 : </td>
		<td><%=vo.getTitle() %></td> <!-- vo에 담은 내용을 출력 -->
	</tr>
	<tr>
		<td> 작성자 : </td>
		<td><%=vo.getWriter() %></td>
	</tr>
	<tr>
		<td> 작성일자 : </td>
		<td><%=vo.getRegdate()%></td>
	</tr>
	<tr>
		<td> 내  용 : </td>
		<td><%=vo.getContent() %></td>
	</tr>
	<tr>
		<td> 조회수 : </td>
		<td><%=vo.getCnt() %></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="BoardList.jsp"> 목록보기 </a>
			<a href="BoardModifyForm.jsp?seq=<%=seq%>"> 수정하기 </a>
			<a href="BoardDeleteForm.jsp?seq=<%=seq%>"> 삭제하기 </a>	<!-- pk를 끌고갈 수 있는 파라미터 -->	
		</td>
	</tr>
	</table>

	<hr/>
	<!-- 댓글추가 -->
	<!-- 댓글추가는 비동기방식임 그래서 ajax를 사용해야 함. 왜냐하면 댓글을 적을 때 모든 화면이 로딩되는 건 아니기 때문에 -->
	<form id='relyFrm' name='replyFrm' >
		<!-- 무슨 글에 썼는지 알아야 함.  -->
		<input type='hidden' id='seq' value='<%=seq%>'/>
		<input type='text' id='username'>
		<input type='text' id='reply'>
		<input type='button' value='댓글추가' id='replyBtn'>		

	</form>
















</body>
</html>