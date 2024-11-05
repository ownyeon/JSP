<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ='member.beans.*' %>    
    
<%
//파라미터로 넘긴 id를 받아오자. 
	String id = request.getParameter("id");
//db연결하고싶네? 
		MemberDao dao = MemberDao.getInstance(); //해당하는 id가 있는지 없는지 여부만 필요 
		if( dao.isDuplicatedId(id) ) {
			out.print("yes");
		}else{
			out.print("no");
		}
%>    
  
