<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>新用户注册</title>
<link href="./css/vote.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body class="container-fluid">
<%@ include file="nav.jsp" %>
<div class="rest row">
<h2 class="page-header text-center">参与投票【${subject.title}】</h2>
<h4 class="text-center">已有${subject.userCount}个网友参与了投票。</h4>
<form method="post" action="${pageContext.request.contextPath}/doVote" class="form-horizontal col-md-10 col-md-offset-1">
<input type="hidden" name="subjectId" value="${subject.subjectId }"/>  
	<c:forEach items="${subject.options}" var="option" varStatus="status"> 
		<div class="form-group">
			<label <c:if test="${subject.optionWay==2}"> class="checkbox-inline"</c:if><c:if test="${subject.optionWay==1}">class="radio-inline"</c:if>>
				<input <c:if test="${subject.optionWay==2}">type="checkbox"</c:if><c:if test="${subject.optionWay==1}">type="radio"</c:if> name="options"  value="${option.optionId }"/>${option.optionContent}
			</label>
		</div>
	</c:forEach> 				 
<div class="btn form-group ">
    <input type="submit" name="submit" value="提交" class="btn-primary btn-lg"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath }/doList">取&nbsp;&nbsp;消</a>
</div>
</form>
<div class="error">${message }</div>			
<c:remove  var="message"  scope="session"  />
</div>
</body>
</html>