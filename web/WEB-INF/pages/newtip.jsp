<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/25
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<html>
<head>
    <title>新增提醒</title>
</head>
<body>
<div class="container"><h5>
    <form class="form-horizontal" action="/riqin2/addtip" method="post">
        <!--任务类型 计划任务、临时任务-->

                <div class="form-group">
                    <label class="col-sm-2 control-label">提醒内容</label>
                    <div class="col-sm-10">
                        <textarea name="icomment" class="form-control"  required></textarea>
                    </div>
                </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">要提醒的人的ID(手机号码):</label>
            <div class="col-sm-10">
                <input name="handler" class="form-control" placeholder="默认提醒自己">
            </div>
        </div>


        <!--任务来源-->
        <div class="form-group">
            <label class="col-sm-2 control-label">时间</label>
            <div class="col-sm-10">
                <div class="col-sm-10">
                    <input id="datetimepicker" name="alertTime" class="form-control"   required/>
                </div>

            </div>
        </div>



        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" onclick="$(this).attr('disabled',false); " class="btn btn-primary">提交</button>
                <a href="/riqin2/returnMyTips"> <button type="button"  class="btn btn-primary">返回我的提醒</button></a>
            </div>
        </div>
    </form>
    </h5>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="bootstrap/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="bootstrap/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</body>

<script>


    $(function () {

        $('#datetimepicker').datetimepicker({
            language:  'zh-CN',
            format: 'yyyy-mm-dd hh:ii'
        });
    })
</script>
</html>
