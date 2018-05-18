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

<html>
<head>
    <title>数据统计</title>
</head>
<body>
<div class="container">
    <p style="margin-top:5px;">


    <div class="panel panel-default ">
        <div class="panel-heading">
            <h4 class="panel-title">搜索条件</h4>
        </div>
        <div class="panel-body" style="padding: 10px;">

                <form class=" navbar-form navbar-left" action="/riqin2/search" method="post">
                <div class="form-group">
                    <label  >是否月清</label>
                    <select name="isImportant"  class="form-control">
                        <option value="-1" <c:if test="${isImportant =='-1'}">
                            selected = "selected"
                        </c:if>>不限</option>
                        <option value="0" <c:if test="${isImportant =='0'}">
                            selected = "selected"
                        </c:if>>否</option>
                        <option value="1" <c:if test="${isImportant =='1'}">
                            selected = "selected"
                        </c:if>>是</option>

                    </select>
                </div>
                <div class="form-group">
                    <label  >排序方式</label>
                    <select name="isOrderByTime"   class="form-control">
                        <option <c:if test="${isOrderByTime =='1'}">
                            selected = "selected"
                        </c:if> value="1">按时间排序</option>
                        <option <c:if test="${isOrderByTime =='0'}">
                            selected = "selected"
                        </c:if> value="0">按人员排序</option>

                    </select>
                </div>
                <div class="form-group">
                    <label>归档时间范围</label>
                    <input name="backupTimeF" value="${backupTimeF}" class="date-picker form-control" readonly="readonly" placeholder="点击选择开始日期">
                    <br/>
                    <input name="backupTimeT" value="${backupTimeT}" class="date-picker form-control" readonly="readonly" placeholder="点击选择结束日期">
                </div>
                <div class="form-group">
                    <label  >关键字</label>
                    <input name="keywd" value="${keywd}" type="text" class="form-control" placeholder="输入要搜索的关键词">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>


        </div>

    </div>
    <div class="panel panel-default ">
        <div class="panel-heading">
            <h4 class="panel-title">搜索结果</h4>
        </div>
        <div class="panel-body" style="padding: 0px;">

            <table class="table table-bordered " style="margin-bottom: 0px;font-size: x-small">
                <c:forEach items="${jobBackups}" var="tempJobBackup">

                    <c:if test="${tempJobBackup.jobStatus==2}"><tr onclick="window.location.href='viewBackupJob?job_backup_id=${tempJobBackup.jobBackId}'" style="color:#228b22">
                        <td style="min-width:20px"><fmt:formatDate value="${tempJobBackup.backupTime}"
                                                                   pattern="M月d日"/></td>
                        <td   style="min-width:20px">${tempJobBackup.jobHandler.name}</td>
                        <td  style="min-width:20px">${tempJobBackup.jobType}</td>
                        <td  style="min-width:20px">${tempJobBackup.source.name}</td>
                        <td>${tempJobBackup.jobComment}</td>

                    </c:if>

                    <c:if test="${tempJobBackup.jobStatus==1}"><tr onclick="window.location.href='viewBackupJob?job_backup_id=${tempJobBackup.jobBackId}'" style="color:#daa520"><td><fmt:formatDate value="${tempJobBackup.backupTime}"
                                                                                                                                                                                                     pattern="M月d日"/></td>
                        <td style="min-width:20px">${tempJobBackup.jobHandler.name}</td>
                        <td style="min-width:20px">${tempJobBackup.jobType}</td>
                        <td style="min-width:20px">${tempJobBackup.source.name}</td>
                        <td style="min-width:20px">${tempJobBackup.jobComment}</td>

                    </c:if>
                    <c:if test="${tempJobBackup.jobStatus==0}"><tr onclick="window.location.href='viewBackupJob?job_backup_id=${tempJobBackup.jobBackId}'" style="color: #F43530"><td><fmt:formatDate value="${tempJobBackup.backupTime}"
                                                                                                                                                                                                      pattern="M月d日"/></td>
                        <td style="min-width:20px">${tempJobBackup.jobHandler.name}</td>
                        <td style="min-width:20px">${tempJobBackup.jobType}</td>
                        <td style="min-width:20px">${tempJobBackup.source.name}</td>
                        <td style="min-width:20px">${tempJobBackup.jobComment}</td>

                    </c:if>
                    </tr>
                </c:forEach>
            </table>

        </div>

    </div>
</div>
</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/jQuery.top-prompt.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script>

    //下面的这个是自定义datepicker的语言
    $('.date-picker').datepicker({
        language: 'zh-CN',
        autoclose: true,
        todayHighlight: true,
        format: 'yyyy-mm-dd'
    });


    $(function () {





    })
</script>
</html>
