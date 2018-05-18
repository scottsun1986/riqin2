<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>新增日清项</title>
</head>
<body>
<div class="container"><h5>
    <form class="form-horizontal" action="/riqin2/add" method="post">
        <!--任务类型 计划任务、临时任务-->
        <div class="form-group">
            <label class="col-sm-2 control-label">任务类型</label>
            <div class="col-sm-10">
                <div class="row">
                    <div class="radio col-sm-6">
                        <label>
                            <input class="radioItem" type="radio" name="jobType" id="blankRadio1" value="临时任务"
                                   checked="checked">临时任务
                        </label>
                    </div>
                    <div class="radio col-sm-6">
                        <label>
                            <input class="radioItem" type="radio" name="jobType" id="blankRadio2" value="计划任务">计划任务
                        </label>
                    </div>
                </div>
            </div>
        </div>

        <!--任务来源-->
        <div class="form-group">
            <label class="col-sm-2 control-label">任务来源</label>
            <div class="col-sm-10">

                <select name="sourceId" class="form-control">
                    <c:forEach items="${sourceList}" var="tempSource">
                        <option value="${tempSource.sourceId}">${tempSource.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <!--任务说明-->
        <div class="form-group">
            <label class="col-sm-2 control-label">任务说明</label>
            <div class="col-sm-10">
                <textarea name="jobComment" class="form-control" rows="3" required></textarea>
            </div>
        </div>

        <div class="form-group for-is-important">
            <label class="col-sm-2 control-label">是否重要任务</label>
            <div class="col-sm-10">
                <select name="isImportant" class="form-control">
                    <option value="0">否</option>
                    <option value="1">是</option>

                </select>
            </div>
        </div>

        <!--单次OR循环-->
        <div class="form-group  for-is-single">
            <label class="col-sm-2 control-label">单次OR循环</label>
            <div class="col-sm-10">
                <div class="row">
                    <div class="radio col-sm-6">
                        <label>
                            <input type="radio" class="radioItem" name="isSingle" value="1" checked="checked">单次任务
                        </label>
                    </div>
                    <div class="radio col-sm-6">
                        <label>
                            <input type="radio" class="radioItem" name="isSingle" value="0">循环任务
                        </label>
                    </div>
                </div>
            </div>
        </div>

        <!--要求完成日期，单次任务才有，循环任务则隐藏-->
        <div class="form-group for-required-time">
            <label class="col-sm-2 control-label">要求完成时间</label>
            <div class="col-sm-10">
                <input name="requiredTime" class="date-picker form-control" readonly="readonly" placeholder="点击选择日期">
            </div>
        </div>

        <!--循环周期，单次任务则隐藏，默认是今天-->
        <div class="form-group for-cycle">
            <label class="col-sm-2 control-label">循环周期</label>
            <div class="col-sm-10">
                <div class="row">

                    <div class="col-sm-6"><select name="cyclePart1" class="form-control">
                        <option value="day">每天</option>
                        <option value="week">每周</option>
                        <option value="month">每月</option>
                    </select></div>
                    <div class="col-sm-6"><select name="cyclePart2" class="form-control">
                        <option value="0">每天</option>
                        <option value="2">周一</option>
                        <option value="3">周二</option>
                        <option value="4">周三</option>
                        <option value="5">周四</option>
                        <option value="6">周五</option>
                        <option value="7">周六</option>
                        <option value="0">周日</option>
                        <option value="1">1日</option>
                        <option value="2">2日</option>
                        <option value="3">3日</option>
                        <option value="4">4日</option>
                        <option value="5">5日</option>
                        <option value="6">6日</option>
                        <option value="7">7日</option>
                        <option value="8">8日</option>
                        <option value="9">9日</option>
                        <option value="10">10日</option>
                        <option value="11">11日</option>
                        <option value="12">12日</option>
                        <option value="13">13日</option>
                        <option value="14">14日</option>
                        <option value="15">15日</option>
                        <option value="16">16日</option>
                        <option value="17">17日</option>
                        <option value="18">18日</option>
                        <option value="19">19日</option>
                        <option value="20">20日</option>
                        <option value="21">21日</option>
                        <option value="22">22日</option>
                        <option value="23">23日</option>
                        <option value="24">24日</option>
                        <option value="25">25日</option>
                        <option value="26">26日</option>
                        <option value="27">27日</option>
                        <option value="28">28日</option>


                    </select></div>
                </div>
            </div>
        </div>

        <!--指定完成人，默认只有自己-->
        <div class="form-group">
            <label class="col-sm-2 control-label">指定完成人</label>
            <div class="col-sm-10">
                <select name="jobHandlerIds" class="form-control selectpicker" multiple required>

                    <c:forEach items="${userList}" var="tempUser">
                        <c:choose>
                            <c:when test="${tempUser.name=='默认为自己'}">
                                <option value="${tempUser.id}" selected>${tempUser.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${tempUser.id}">${tempUser.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>


                </select>
            </div>
        </div>

        <!--如果是循环任务则隐藏，计划任务也隐藏-->
        <div class="form-group for-job-status">
            <label class="col-sm-2 control-label">状态</label>
            <div class="col-sm-10">
                <select name="jobStatus" class="form-control">
                    <option value="0">未处理</option>
                    <option value="1">处理中</option>
                    <option value="2">已完成</option>

                </select>
            </div>
        </div>


        <!--处理备注，若循环的则就隐藏，计划任务也隐藏-->
        <div class="form-group for-job-feedback">
            <label class="col-sm-2 control-label">处理反馈</label>
            <div class="col-sm-10">
                <textarea name="jobFeedback" class="form-control" rows="3"></textarea>
            </div>
        </div>


        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" onclick="$(this).attr('disabled',false); " class="btn btn-primary">提交</button>
                <a href="/riqin2/returnMyJob"> <button type="button"  class="btn btn-primary">返回我的日清</button></a>
            </div>
        </div>
    </form>
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
