<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="com.fun4g.riqin.model.Job" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/28
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-datepicker3.min.css" rel="stylesheet">
<html>
<head>
    <title>我的日清单</title>
</head>
<body>
<div class="container">
    <p style="margin-top:5px;">
    <h3>今日日清清单</h3></p>

    <div class="panel panel-default "  >
        <div class="panel-heading">
            <h4 class="panel-title">计划清单</h4>
        </div>
        <div class="panel-body" style="padding: 0px;">

            <table class="table " style="margin-bottom: 0px;">
                <c:forEach items="${todayPlanedJobs}" var="todayPlanedJob">

                    <tr  >
                        <td style="width:50px;"><input id="${todayPlanedJob.jobId}" type="checkbox" style="width:25px; height:25px"
                                                       <c:if test="${todayPlanedJob.jobStatus!=0}">checked</c:if>></td>
                        <td   onclick="window.location.href='viewAndModifyJob?job_id=${todayPlanedJob.jobId}'">
                            <label>
                                <c:choose>
                                    <c:when test="${todayPlanedJob.jobStatus!=0}">
                                        <del>${todayPlanedJob.jobComment}</del>
                                    </c:when>
                                    <c:otherwise>
                                        ${todayPlanedJob.jobComment}
                                    </c:otherwise>
                                </c:choose>
                            </label>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="panel panel-default ">
        <div class="panel-heading">
            <h4 class="panel-title">临时清单</h4>
        </div>
        <div class="panel-body" style="padding: 0px;">

            <table class="table " style="margin-bottom: 0px;">
                <c:forEach items="${todayTemporaryJobs}" var="todayTemporaryJob">

                    <tr>
                        <td style="width:50px;"><input id="${todayTemporaryJob.jobId}" type="checkbox"  style="width:25px; height:25px"
                                                       <c:if test="${todayTemporaryJob.jobStatus!=0}">checked</c:if>>
                        </td>
                        <td onclick="window.location.href='viewAndModifyJob?job_id=${todayTemporaryJob.jobId}'">

                            <label>
                                <c:choose>
                                    <c:when test="${todayTemporaryJob.jobStatus!=0}">
                                        <del>${todayTemporaryJob.jobComment}</del>
                                    </c:when>
                                    <c:otherwise>
                                        ${todayTemporaryJob.jobComment}
                                    </c:otherwise>
                                </c:choose>
                            </label>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>

    <a href="/riqin2/toAddPage">
        <button type="submit" class="btn btn-primary">添加日清项</button>
    </a>
    <a>
        <button onclick="WeixinJSBridge.call('closeWindow');" type="button" class="btn btn-danger">关闭</button>
    </a>
    <p style="margin-top:5px;">
    <h3>近七天日清提醒</h3></p>
    <table class="table   table-bordered">

        <c:forEach items="${next7DayJobs}" var="next7DayJob">
            <tr>
                <td style="width:80px;"><label><fmt:formatDate value="${next7DayJob.requiredTime}"
                                                               pattern="M月d日"/> </label></td>
                <td onclick="window.location.href='viewAndModifyJob?job_id=${next7DayJob.jobId}'">
                    <label>${next7DayJob.jobComment}</label></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/jQuery.top-prompt.min.js"></script>
<script>

    $.initTopPrompt(); //or $.initTopPrompt(300);


    $(function () {

        //下面这个是为了在选择的时候划横线
        $("input[type='checkbox']").click(function () {
            if ($(this).prop("checked")) {
                $(this).parent().next().children().wrapInner("<del></del>");
                var temp_jobid = $(this).attr("id");

                $.ajax({
                    url: '/riqin2/jobChecked',
                    type: 'get',
                    data: 'job_id=' + temp_jobid + '&set_done=1',
                    async: false, //默认为true 异步
                    error: function () {

                        $.showTopPrompt('系统错误，未能更新状态');

                    },
                    success: function (data) {
                        if (data == 1) {
                            $.showTopPrompt('更新成功');
                            setTimeout(function () {
                                $.hideTopPrompt();
                            }, 300);
                        }
                        else {
                            $.showTopPrompt('更新失败');
                            setTimeout(function () {
                                $.hideTopPrompt();
                            }, 300);
                        }
                    }
                });
            }
            else {
                var x = $(this).parent().next().text();
                $(this).parent().next().find("del").detach();
                $(this).parent().next().find("label").prepend(x);
                var temp_jobid = $(this).attr("id");

                $.ajax({
                    url: '/riqin2/jobChecked',
                    type: 'get',
                    data: 'job_id=' + temp_jobid + '&set_done=0',
                    async: false, //默认为true 异步
                    error: function () {
                        alert('错误，未能更新状态');
                    },
                    success: function (data) {
                        if (data == 1) {
                            $.showTopPrompt('更新成功');
                            setTimeout(function () {
                                $.hideTopPrompt();
                            }, 300);
                        }
                        else {
                            $.showTopPrompt('更新失败');
                            setTimeout(function () {
                                $.hideTopPrompt();
                            }, 300);
                        }
                    }
                });
            }

            //alert($(this).parent().next().html());
            /*      var imageId= $(this).getAttribute("checked");

             if( $(e.target).attr("checked")) {
             var d=$(e.target).getParent().getParent().getElementsByName("del");
             alert(d);
             if(d!=null)
             $(e.target).getParent().nextSibling.firstChild.wrap("<del></del>");
             }else{
             $(e.target).getParent().nextSibling.firstChild.unwrap("<del></del>");
             }*/

        });


    })
</script>
</html>
