<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*" %>
<%@ page import="shop.cart.Goods" %> 
<%
	String id="";
	String name ="";
	int price=0;  

	ArrayList<Goods> glist = null; //한 상품에 대한 정보를 goods라는 arralist에 담을것.

	request.setCharacterEncoding("utf-8");
	
	//0. goods에서 setter가 하나받게 없기 떄문에 여기서 초기화를 지정해 줘야 한다.
	// 1. Form의 값(hidden값) 넘겨받기 ( id, name, price )
	id = request.getParameter("id");
	name = request.getParameter("name");
	price = Integer.parseInt(request.getParameter("price")); //형변환 (문자열 -> 인트 )
	
	// 2. 세션의 cart 속성을 얻어온다. 
	Object obj = session.getAttribute("cart"); //getAttribute로 얻어온 애는 무조건 object형임.
	//근데 obj는 세션이 지금 없으니까 null이 가지고 있음. 
	
	// 3. 만일 null이면 ArrayList 객체 새로 생성하고 그렇지 않으면 ArrayList 변수(glist)에 지정
	if(obj ==null){
		glist = new ArrayList<Goods>(); //배열 생성. 
		
		
	}else{
		glist = (ArrayList<Goods>)obj;//값을 가지고 옴 
	}
	
	// 4. 1번의 값들을 Goods 객체로 생성후 ArrayList 에 추가
	/* Goods g = new Goods(id, name, price); */
	glist.add(new Goods(id, name, price)); //추가. 
	
	// 5. 세션에 cart 라는 이름에 ArrayList를 저장
	session.setAttribute("cart", glist); 

%>		 
		 
<html> 
<body bgcolor=white>
<%= name %> 을 구입하셨습니다.
 
<br><br><br>

<table>
<tr bgcolor="#e7a068"><th>상품명</th>
<th>가격</th></tr>

<%
		int n = glist.size(); 
		int sum = 0; 
		for(int i=0; i < n; i++) { 
			Goods goods = (Goods) glist.get(i); 
			int gp = goods.getPrice(); 
			sum += gp; 
%>
			<tr><td align="center"> <%= goods.getName() %> </td>
				<td align="right"> <%= gp %> </td></tr>
<%
		} 		 
%>

<tr bgcolor="#e7a068"><td colspan="2" align="right"> 총액 : <%= sum  %></td></tr>
</table>

<br/><br/>
[<a href="wshop.jsp">쇼핑하러 가기</a>]
[<a href="Buy.jsp">구입하기</a>]

</body></html>

