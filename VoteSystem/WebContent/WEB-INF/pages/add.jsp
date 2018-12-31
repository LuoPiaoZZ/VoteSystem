<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>发起投票项目</title>
<link href="./css/add.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
var isIE = !!document.all;
function addOption()
{
	var voteoptions = document.getElementById("voteoptions");
	var _p = document.createElement("p");
	var _input = document.createElement("input");
	_input.type = "text";
	_input.className = "form-control";
	_input.setAttribute("name", "options");
	_p.appendChild(_input);
	var _a = document.createElement("a");
	_a.className = "del";
	_a.setAttribute("href", "javascript:;");
	if(isIE) {
		_a.attachEvent("onclick", delOption);
	} else {
		_a.addEventListener("click", delOption, false);
	}
	_a.appendChild(document.createTextNode("删除"));
	_p.appendChild(_a);
	voteoptions.appendChild(_p);
}
function delOption(e)
{
	if(!e) e = window.event;
	var a = e.srcElement || e.target;
	var obj = a.parentNode;
	obj.parentNode.removeChild(obj);
}
</script>
</head>
<body class="container-fluid">
<%@ include file="nav.jsp" %>
<div class="rest  row">
<h2 class="page-header text-center">发起投票项目</h2>
<form action="${pageContext.request.contextPath}/doAdd" class="form-horizontal"  method="post">
<div class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2">
<input type="hidden" name="subjectId" value="${subject.subjectId }"/>
<div class="form-group">
<span class="glyphicon glyphicon-file"><label for="title">投票主题</label></span>
<input type="text" name="title" class="form-control" value="${subject.title }"/>
</div>
<div class="form-group">
<span class="glyphicon glyphicon-star"><label for="optionWay">投票方式</label></span>&nbsp;&nbsp;&nbsp;&nbsp;
<label class="radio-inline"><input type="radio" name="optionWay" <c:if test="${subject.number==1 }"> checked="checked" </c:if> value="1"/>单选</label>
<label class="radio-inline"><input type="radio" name="optionWay" <c:if test="${subject.number==2 }"> checked="checked" </c:if> value="2"/>多选</label>
</div>
<div class="form-group">
<span class="glyphicon glyphicon-file"><label for="options">投票选项</label></span>
<c:if test="${subject.subjectId==null }">
<div id="voteoptions">
<p><input type="text" name="options" class="form-control"/></p>
<p><input type="text" name="options" class="form-control"/></p>
</div>
</c:if>
<c:if test="${subject.id!=null}">
<div id="voteoptions">
  <c:forEach items="${subject.options}" var="option" varStatus="st">
	<p><input type="text" name="options" class="form-control" value="${option.optionContent }"/><c:if test="${st.index>1}"><a class="del" href="javascript:;" onclick="delOption()">删除</a></c:if></p>
  </c:forEach>
 </div>
</c:if>
</div>
<a href="javascript:;" onclick="addOption()">增加选项</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/list">取消操作</a>
<br><br>
<div class="btn form-group ">
<input type="submit" name="submit" value="提交" class="btn-primary btn-lg commit-btn"/>&nbsp;&nbsp;&nbsp;&nbsp;
<input type="reset" name="reset" value="重置" class="btn-primary btn-lg"/>
</div>
<div class="error">${message }</div>	
	<c:remove  var="subject"  scope="session"  />
	<c:remove  var="message"  scope="session"  />
</div>
</form>
</div>
</body>
</html>