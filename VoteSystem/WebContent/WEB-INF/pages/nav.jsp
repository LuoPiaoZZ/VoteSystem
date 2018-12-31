<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default navbar-fixed-top"> 
<div class="container">
 <c:if test="${CurrentUser!=null }">
 <p class="navbar-text navbar-left"><a href="${pageContext.request.contextPath }/doLogout" class="navbar-link">退&nbsp;&nbsp;出</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong>您好，${CurrentUser.name }</strong></p>
 </c:if>
 <p class="navbar-text navbar-right"> <a href="${pageContext.request.contextPath }/index.jsp" class="navbar-link">登&nbsp;&nbsp;录</a></p>
 <p class="navbar-text navbar-right"> <a href="${pageContext.request.contextPath }/modifyServlet" class="navbar-link">维护中心</a></p>
 <p class="navbar-text navbar-right"> <a href="${pageContext.request.contextPath }/doList" class="navbar-link">投票列表</a></p>
 <p class="navbar-text navbar-right"> <a href="${pageContext.request.contextPath }/add" class="navbar-link">发起投票</a></p>
</div>
</nav>
