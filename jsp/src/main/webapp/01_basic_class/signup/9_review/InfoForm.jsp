<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/info.css">
    <script src='js/info.js' type="text/javascript"></script>
</head>
<body>

<h3>회원가입서 작성하기 </h3> 
<!-- js를 사용하기 위해서는 onsubmit으로 해야 한다. -->
<form action="InfoSave.jsp" method="get" onsubmit="return validateForm()">
    <div class="form-group">
        <div class="form-labels">
            <label for="info_id">아이디:</label> 
            <label for="info_pw">비밀번호:</label> 
            <label for="password">비밀번호 확인:</label>
            <label for="info_name">이름: </label>
            <label for="info_tel">전화번호:</label>
            <label for="info_addr">주소:</label>
        </div>
        <div class="form-inputs">
            <input name='info_id' type='text' placeholder="5자~10자 영어와 숫자 조합"> 
            <input type='button' value='중복확인'><br/>
            <input name='info_pw' type='password' placeholder="4자이상 영어와 숫자조합"><br/>
            <input name='password' type='password'><br/>
            <input name='info_name' type='text' placeholder="한글 5자 이내"><br/>
            <input name='info_tel' type='text' placeholder="-없이 숫자조합"><br/>
            <input name='info_addr' type='text' placeholder="20자 이내"><br/>
        </div>
    </div>
    <input type='submit' value='회원가입'> 
    <input type='reset' value='취소'>
</form>

</body>
</html>