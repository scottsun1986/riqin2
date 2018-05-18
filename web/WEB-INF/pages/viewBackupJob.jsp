<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="com.fun4g.riqin.util.BeanGetter" %>
<%@ page import="com.fun4g.riqin.iDao.IuserMapper" %>
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
<link href="bootstrap/css/bootstrap-datepicker3.min.css" rel="stylesheet">
<html>
<head>
    <title>查看日清统计项</title>
</head>
<body>
<div class="container">
    <h5>

            <!--任务类型 计划任务、临时任务-->
            <input hidden name="jobId" value="${currentJobBackup.jobBackId}">
            <div class="panel panel-default ">
                <div class="panel-heading">
                    <h4 class="panel-title">日清统计项</h4>
                </div>
                <div class="panel-body" style="padding: 0px;">

                    <table class="table " style="margin-bottom: 0px;">
                        <tr>
                            <td><label>任务类型</label></td>
                            <td>${currentJobBackup.jobType}</td>
                        </tr>
                        <tr>
                            <td><label>任务来源</label></td>
                            <td>${currentJobBackup.source.name}</td>
                        </tr>
                        <tr>
                            <td><label>任务说明</label></td>
                            <td>${currentJobBackup.jobComment}</td>
                        </tr>
                        <tr>
                            <td><label>是否单次</label></td>
                            <td><c:choose>

                                <c:when test="${currentJobBackup.isSingle==1}">
                                    单次
                                </c:when>
                                <c:when test="${currentJobBackup.isSingle==0}">
                                    循环
                                </c:when>
                            </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td><label>任务创建时间</label></td>
                            <td><fmt:formatDate value="${currentJobBackup.createTime}"
                                                pattern="M月d日"/></td>
                        </tr>
                        <tr>
                            <td><label>任务创建人</label></td>
                            <td>${currentJobBackup.creatorId}</td>
                        </tr>
                        <tr>
                            <td><label>要求完成时间</label></td>
                            <td><fmt:formatDate value="${currentJobBackup.requiredTime}"
                                                pattern="M月d日"/></td>
                        </tr>
                        <tr>
                            <td><label>实际完成时间</label></td>
                            <td><fmt:formatDate value="${currentJobBackup.finishTime}"
                                                pattern="M月d日"/></td>
                        </tr>
                        <tr>
                            <td><label>状态</label></td>
                            <td> <c:if test="${currentJobBackup.jobStatus==0}"> 未处理 </c:if>
                                <c:if test="${currentJobBackup.jobStatus==1}"> 处理中</c:if>
                                <c:if test="${currentJobBackup.jobStatus==2}"> 已完成 </c:if>

                          </td>

                        </tr>
                        <tr>
                            <td><label>处理反馈</label></td>
                            <td> ${currentJobBackup.jobFeedback}</td>
                        </tr>
                    </table>
                </div>
            </div>



            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button  onclick="history.go(-1) " class="btn btn-primary">返回</button>


                </div>
            </div>

    </h5>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.zh-CN.min.js"></script>
</body>

<script>

    //下面的这个是自定义datepicker的语言
    $('.date-picker').datepicker({
        language: 'zh-CN',
        autoclose: true,
        todayHighlight: true,
        format: 'yyyy-mm-dd'
    });


    function checkdisplay() {
        var isSingle_value = $("input[name='isSingle']:checked").val();
        // alert($selectedvalue);


        /*如果是临时任务则不可选择要求完成时间，只有计划任务可选择*/
        var jobType_value = $("input[name='jobType']:checked").val();
        if (isSingle_value == 1 && jobType_value == "临时任务") {
            $(".for-required-time").hide();//要求完成时间
            $(".for-cycle").hide();//循环周期
            $(".for-is-single").hide();//是否单次任务
            $(".for-job-status").show();//任务状态
            $(".for-job-feedback").show();//任务反馈
        } else if (isSingle_value == 1 && jobType_value != "临时任务") {
            $(".for-required-time").show();//要求完成时间
            $(".for-cycle").hide();//循环周期
            $(".for-is-single").show();//是否单次任务
            $(".for-job-status").hide();//任务状态
            $(".for-job-feedback").hide();//任务反馈
        }
        else if (isSingle_value == 0 && jobType_value != "临时任务") {
            $(".for-required-time").hide();//要求完成时间
            $(".for-cycle").show();//循环周期
            $(".for-is-single").show();//是否单次任务
            $(".for-job-status").hide();//任务状态
            $(".for-job-feedback").hide();//任务反馈
        }
        else if (isSingle_value == 0 && jobType_value == "临时任务") {
            $(".for-required-time").hide();//要求完成时间
            $(".for-cycle").hide();//循环周期
            $(".for-is-single").hide();//是否单次任务
            $(".for-job-status").show();//任务状态
            $(".for-job-feedback").show();//任务反馈
        }
    }
    $(function () {

        checkdisplay();


        $(".radioItem").change(function () {
            checkdisplay();

        });
        var nua = navigator.userAgent
        var isAndroid = (nua.indexOf('Mozilla/5.0') > -1 && nua.indexOf('Android ') > -1 && nua.indexOf('AppleWebKit') > -1 && nua.indexOf('Chrome') === -1)
        if (isAndroid) {
            $('select.form-control').removeClass('form-control').css('width', '100%')
        }
    })
</script>
</html>
