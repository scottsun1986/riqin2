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


    <div class="panel panel-default " style="width:800px"  >
        <div class="panel-heading">
            <h4 class="panel-title">本月日清统计</h4>
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
<script>




    $(function () {





    })
</script>
</html>
