<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 회원가입  </title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">

$(function () {
	$('#btnIdCheck').click(function () {
		
		let inputId = $('input[ name="id"]').val(); //사용자 입력값을 얻어옴. 
		//파라미터를 줌. => 이거를 request.getParameter로 가지고 오는 거임. 
		//[0] 기존 방식 
		//window.open('CheckId.jsp?id='+inputId,'창이름','width=400, height=150');
		
		//[1] ajax 방식
		$.ajax({
			type: 'get', 
			data : { id : inputId },
			url : 'CheckId.jsp',
			success : function (data) {
				//alert("<" + data + ">") //뭐가 넘어오는지 확인, 공백이 있는지 없는지 확인하고싶어서 <>를 그냥 쓴거임. 
			
				if(data.trim() == "yes"){ //공백을 제거하기 위해서 trim()사용. 통신할 때 보통 공백이 많이 낀다고 함.
					$('#idCheck').text("중복된 아이디가 있어서 사용 불가");
				}else{
					$('#idCheck').text("사용 가능한 아이디 입니다.");
				}
				
			},
			error : function (err) {
				console.log(err);
				alert('에러발생');
			}
			
		})
	})
})


</script>
</head>
<body>

<h1>회원가입서 작성하기</h1>
 
	<form action="InsertMember.jsp" method="post" name="frm">
		<table>
			<tr>
				<td width="100">
				<font color="blue">아이디</font>
				</td>
				<td width="100">
				<input type="text" name="id">
				<input type="button" value="중복확인" id='btnIdCheck'><br/>
				
				<span id='idCheck'></span>
				
				</td>
			</tr>
			<tr>
				<td width="100">
				<font color="blue">비밀번호</font>
				</td>
				<td width="100">
				<input type="password" name="password"/><br/>
				</td>
			</tr>
			<tr>
				<td width="100">
				<font color="blue">비밀번호학인</font>
				</td>
				<td width="100">
				<input type="password" name="repassword"/><br/>
				</td>
			</tr>
			<tr>
				<td width="100">
				<font color="blue">이름</font>
				</td>
				<td width="100">
				<input type="text" name="name"/><br/>
				</td>
			</tr>
			<tr>
				<td width="100">
				<font color="blue">전화번호</font>
				</td>
				<td>
				<input type="text" size="15" name="tel"/>
				<br/>
				</td>
			</tr>
			<tr>
				<td width="100">
				<font color="blue">주소</font>
				</td>
				<td>
				<input type="text" size="50" name="addr"/><br/>
				</td>
			</tr>
			<tr>
				<td width="100">
				 <!--로그인 버튼-->
				 <input type="submit" value="회원가입">
				</td>
				<td width="100">
				<input type="reset" name="cancel" value="취소"><br/>
				</td>
			</tr>
		</table>
	</form>



 </body>
</html>
    