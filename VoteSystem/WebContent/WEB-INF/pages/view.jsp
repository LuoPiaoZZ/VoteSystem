<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>查看投票情况</title>
<link href="./css/view.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body class="container-fluid">
<%@ include file="nav.jsp" %>
<div class="rest row">
<table class="table table-bordered table-hover table-condensed col-md-12">
<tr><td colspan="${size+1 }"><h3 class="text-center">${title}</h3></td></tr>
<tr><td>选项</td>
<c:forEach items="${options }" var="option" varStatus="st">
<td><h5 class="text-center">${option.optionContent }</h5></td>
</c:forEach>
</tr>
<tr><td>数量</td>
<c:forEach items="${optionsCount }" var="count" varStatus="st">
<td><h5 class="text-center">${count }</h5></td>
</c:forEach>
</tr>
</table>
</div>
</body>
</html>