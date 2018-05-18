<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="com.fun4g.riqin.model.Job" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fun4g.riqin.util.DateHelper" %><%--
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
    <title>我的待提醒项清单</title>
</head>
<body>
<div class="container" style="padding-top:20px;">

    <div class="panel panel-default "  >

        <div class="panel-body" style="padding: 0px;">

            <table class="table " style="margin-bottom: 0px;">
                <c:forEach items="${notAlertedTips}" var="notAlertedTip">

                    <tr  >
                        <td >
                            <label>

                                        ${notAlertedTip.icomment}

                            </label>
                        </td>
                        <td >
                            <label>
                                <fmt:formatDate value="${notAlertedTip.alertTime}"
                                                pattern="M月d日 hh:mm"/>

                            </label>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <a>
        <button onclick="WeixinJSBridge.call('closeWindow');" type="button" class="btn btn-danger">关闭</button>
    </a>

</div>
</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="bootstrap/js/bootstrap.min.js"></script>


</html>
