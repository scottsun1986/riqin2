<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/28
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-datepicker3.min.css" rel="stylesheet">
    <title>绑定用户</title>
</head>
<body>
<div class="container">
    <div class="weui_cells weui_cells_form">

    <form action="/riqin2/bindUserSubmit">
        <div class="form-group">
            <label   class="col-sm-2 control-label"> 请输入手机号码</label>
            <div class="col-sm-10">
                <input hidden name="wx_id" value="${wx_id}">
                <input type="text" name="id" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
    </div>
</div>
</body>
</html>
