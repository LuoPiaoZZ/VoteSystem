<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>欢迎来到投票系统</title>
<link href="css/index.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body class="bounceIn animated">
    <section id="getintouch" class="rotateInUpLeft animated">
         <h3 class="page-header text-center" style="font-size: 2em;color: white">投票系统欢迎您！</h3>
        <div class="container">
            <form class="contact" action="${pageContext.request.contextPath }/doLogin" method="post" id="form">
            <div class="row clearfix col-md-10 col-sm-10">
                <div class="lbl">
                    <label for="uname">
                        UserName</label>
                </div>
                <div class="ctrl">
                    <input type="text" id="name" name="name"  placeholder="用&nbsp;户&nbsp;名" value="${user.name}"/>
                </div>
            </div>
            <div class="row clearfix col-md-10 col-sm-10">
                <div class="lbl">
                    <label for="upass">
                        PassWord</label>
                </div>
                <div class="ctrl">
                    <input type="password" id="psw" name="psw" placeholder="密&nbsp;&nbsp;&nbsp;码" value="${user.psw}"/>
                </div>
            </div>
             <div class=" col-md-10 col-xs-10 col-md-offset-1">
                <input type="submit" name="submit" id="submit" class="submit" value="&nbsp;&nbsp登&nbsp;&nbsp录&nbsp;&nbsp">
                <a href="${pageContext.request.contextPath }/reg">新用户注册</a>
             </div>
             <div class="error col-md-12 col-xs-12">${message}</div>
             <c:remove  var="user"  scope="session"  />
		     <c:remove  var="message"  scope="session"  />
            </form>
        </div>
            <h5 class="page-header text-center text-muted">Copyright&nbsp;&nbsp;江西农业大学&nbsp;&nbsp;&nbsp;&nbsp;罗飘</h5>
    </section>
</body>
</html>