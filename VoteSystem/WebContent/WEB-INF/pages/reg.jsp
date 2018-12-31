<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>新用户注册</title>
<link href="./css/reg.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body class="container-fluid">
<h2 class="page-header text-center">新用户注册</h2>
<form action="${pageContext.request.contextPath }/doReg" class="form-horizontal row" method="post">
<div class="col-md-6 col-md-offset-3 col-xs-8 col-xs-offset-2">
<div class="form-group">
<span class="glyphicon glyphicon-user"><label for="name">用户名</label></span>
<input type="text" name="name" class="form-control" value="${user.name }"/>
</div>
<div class="form-group">
<span class="glyphicon glyphicon-lock"><label for="psw">密码</label></span>
<input type="password" name="psw" class="form-control" value="${user.psw }"/>
</div>
<div class="form-group">
<span class="glyphicon glyphicon-check"><label for="psw">确认密码</label></span>
<input type="password" name="confirmPsw" class="form-control" value="${user.confirmPsw}"/>
</div>
<div class="form-group">
<span class="glyphicon glyphicon-cloud"><label for="gender">性别</label></span>&nbsp;&nbsp;&nbsp;&nbsp;
<label class="radio-inline"><input type="radio" name="gender" value="1"/>男</label>
<label class="radio-inline"><input type="radio" name="gender" value="2"/>女</label>
<label class="radio-inline"><input type="radio" name="gender" value="3" checked="checked"/>保密</label>
</div>
<div class="form-group">
<span class="glyphicon glyphicon-cloud"><label for="age">年龄</label></span>
<input type="number" name="age" class="form-control" value="${user.age }" min="1" max="1000" />
</div>
<div class="btn form-group">
<input type="submit" name="submit" value="注册" class="btn-primary btn-lg reg-btn"/>
<input type="reset" name="reset" value="重置" class="btn-primary btn-lg"/>
</div>
</div>
</form>
<div class="error">${message }</div>
<c:remove  var="user"  scope="session"  />
<c:remove  var="message"  scope="session" />	
</body>
</html>