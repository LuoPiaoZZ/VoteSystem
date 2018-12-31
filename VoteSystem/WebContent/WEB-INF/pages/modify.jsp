<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>所创投票列表</title>
<link href="./css/modify.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body class="container-fluid">
<%@ include file="nav.jsp" %>
<div class="rest">
<h2 class="page-header text-center">所创投票列表</h2>
<div class="row message">
<ul class="list col-md-12">
	<c:forEach items="${subjects}" var="subject" varStatus="st">
		<li class="col-md-4">
		 <div class="panel panel-info tbox">
              <div class="panel-heading"><p class="info">已有 ${subject.userCount}个网友参与了投票。</p>
               </div>
              <div class="panel-body long">
                   <p class="letter"><a href="${pageContext.request.contextPath }/doView?subjectId=${subject.subjectId}">${subject.title}</a></p>
               </div>
              <div class="panel-footer">
                  <cite> <a href="${pageContext.request.contextPath }/doModify?subjectId=${subject.subjectId}">删除</a></cite>
               </div>
         </div>
		</li>
	</c:forEach>
</ul>
</div>
</div>
</body>
</html>