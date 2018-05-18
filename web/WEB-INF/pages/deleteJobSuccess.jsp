<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/30
  Time: 7:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-datepicker3.min.css" rel="stylesheet">
    <title>日清项删除成功</title>
</head>
<body>
<div class="container">
    <div class="panel panel-default text-center" style="margin-top: 20px">
        <div class="panel-heading">
            <h3 class="panel-title">请选择下一步操作</h3>
        </div>
        <div class="panel-body">
            <a href="/riqin2/returnMyJob"> <button type="button"  class="btn btn-primary">返回我的日清</button></a>
            <a>
                <button onclick="WeixinJSBridge.call('closeWindow');" type="button" class="btn btn-danger">关闭</button>
            </a>
        </div>
    </div>


</div>
</body>
</html>
