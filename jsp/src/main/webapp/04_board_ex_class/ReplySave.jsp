<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 3 -->
<%@ page import="reply_ex.model.*" %>
<%@ page import="reply_ex.service.*" %>

<!-- useBean태그 사용하는 것이 훨씬 좋음! -->
<%

//2
ReplyVO vo = new ReplyVO();

//1
vo.setSeq(Integer.parseInt(request.getParameter("seq"))); //값을 가지고 오자마자 setter에 지정
vo.setUsername(request.getParameter("username"));
vo.setReply(request.getParameter("reply"));

InsertReplyService.getInstance().insert(vo); //insertReplyService.java의 insert에 접근함. 

%>