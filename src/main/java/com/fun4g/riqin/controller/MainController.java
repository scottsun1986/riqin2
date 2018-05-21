package com.fun4g.riqin.controller;

import com.fun4g.riqin.iDao.*;
import com.fun4g.riqin.model.Iuser;
import com.fun4g.riqin.model.Job;
import com.fun4g.riqin.model.JobBackup;
import com.fun4g.riqin.model.Tips;
import com.fun4g.riqin.util.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


/**
 *
 */
@Controller
@SessionAttributes("currentUser")
public class MainController {
    public JobBackupMapper getJobBackupMapper() {
        return jobBackupMapper;
    }

    public void setJobBackupMapper(JobBackupMapper jobBackupMapper) {
        this.jobBackupMapper = jobBackupMapper;
    }

    private JobBackupMapper jobBackupMapper;

    public TipsMapper getTipsMapper() {
        return tipsMapper;
    }

    public void setTipsMapper(TipsMapper tipsMapper) {
        this.tipsMapper = tipsMapper;
    }

    private TipsMapper tipsMapper;

    public JobMapper getJobMapper() {
        return jobMapper;
    }

    public void setJobMapper(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    private JobMapper jobMapper;

    public SourceMapper getSourceMapper() {
        return sourceMapper;
    }

    public void setSourceMapper(SourceMapper sourceMapper) {
        this.sourceMapper = sourceMapper;
    }

    private SourceMapper sourceMapper;

    public IuserMapper getIuserMapper() {
        return iuserMapper;
    }

    public void setIuserMapper(IuserMapper iuserMapper) {
        this.iuserMapper = iuserMapper;
    }

    private IuserMapper iuserMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        return "index";

//s

    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testit(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        try {
            OAuthAPI.OAuthIfNesscary(httpRequest, httpResponse);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //远程的方法
    @RequestMapping(value = "/wx_login", method = RequestMethod.GET)
    public String wx_login(Model model, @RequestParam("state") String state, @RequestParam("code") String code) {
        System.out.println("code:" + code);
        String nextUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + OAuthAPI.APP_ID + "&secret=" + OAuthAPI.APP_SECRET + "&code=" + code + "&grant_type=authorization_code";

        //在用 String nextUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx45b6ea3d9f9eb681&secret=87ea9c1da65bb7518a809ad473b6445d&code=" + code + "&grant_type=authorization_code";
        //  System.out.println(nextUrl);
        String responseText = HttpRequest.sendPost(nextUrl, null);
        // System.out.println(responseText);
        String openid = "";
        Iuser tempUser;
        Iuser currentUser;
        try {
            //将字符串转换成jsonObject对象
            JSONObject myJsonObject = JSONObject.fromObject(responseText);
            //获取对应的值
            openid = myJsonObject.getString("openid");

        } catch (JSONException e) {
        }

        if (openid == "") {
            return "error";

        } else {

            tempUser = iuserMapper.selectByWxId(openid);

            if (tempUser == null) {
                model.addAttribute("wx_id", openid);
                return "bindUser";
            } else {
                //System.out.println("sdsd");
                model.addAttribute("currentUser", tempUser);
                if (state.equals("myjob")) {
                    List<Job> todayPlanedJobs = jobMapper.selectByHandlerIdAndJobType(tempUser.getId(), "计划任务");
                    List<Job> todayTemporaryJobs = jobMapper.selectByHandlerIdAndJobType(tempUser.getId(), "临时任务");
                    List<Job> next7DayJobs = jobMapper.selectNext7DayJobsByHandlerId(tempUser.getId());
                    model.addAttribute("todayPlanedJobs", todayPlanedJobs);
                    model.addAttribute("todayTemporaryJobs", todayTemporaryJobs);
                    model.addAttribute("next7DayJobs", next7DayJobs);
//                    model.addAttribute("todayJobs",jobMapper.selectByHandlerIdAndJobType(tempUser.getId(),"计划任务"));
//                    model.addAttribute("next7DayJobs",jobMapper.selectNext7DayJobsByHandlerId(tempUser.getId()));
                    return "myjob";

                } else if (state.equals("manage")) {
                    List<JobBackup> jobBackups = jobBackupMapper.selectByBackupTime(DateHelper.parseDateYMD(DateHelper.parseSimpleString(DayHelper.getThisMonthSecondDay())), DateHelper.parseDateYMD(DateHelper.parseSimpleString(DayHelper.getTomorrowDay())));

                    model.addAttribute("jobBackups", jobBackups);

                    return "manage";
                } else if (state.equals("mytips")) {
                    List<Tips> notAlertedTips = tipsMapper.selectNotAlertedByUserId(tempUser.getId());
                    model.addAttribute("notAlertedTips", notAlertedTips);
                    return "mytips";
                } else if (state.equals("newtip")) {
                //    return "newtip";
                  return   "redirect:/toAddTipsPage";
                }
            }
        }
        return "index";
    }


    @RequestMapping(value = "/bindUserSubmit", method = RequestMethod.GET)
    public String bindUserSubmit(@RequestParam("id") String userId, @RequestParam("wx_id") String wxId) {
        Iuser temp = iuserMapper.selectByPrimaryKey(userId);

        if (temp != null && (temp.getWxId() == null || temp.getWxId().equals(""))) {
            temp.setWxId(wxId);
            iuserMapper.updateByPrimaryKey(temp);
            return "bindSuccess";
        } else {
            return "error";
        }

    }

    /**
     * 本地测试使用
     *
     * @param state
     * @param code
     * @return
     */
    @RequestMapping(value = "/wx_loginXXX", method = RequestMethod.GET)
    public String wx_loginx(@RequestParam("state") String state, @RequestParam("code") String code, ModelMap model) {


        String openid = "o7yZ1szO_7HKNOgq2Kkd_NJi-MiY";
        Iuser tempUser;


        tempUser = iuserMapper.selectByWxId(openid);
        System.out.println(tempUser.getName());
        if (tempUser == null) {

            return "bindUser";
        } else {
            //System.out.println("sdsd");
            model.addAttribute("currentUser", tempUser);
            if (state.equals("myjob")) {
                List<Job> todayPlanedJobs = jobMapper.selectByHandlerIdAndJobType(tempUser.getId(), "计划任务");
                List<Job> todayTemporaryJobs = jobMapper.selectByHandlerIdAndJobType(tempUser.getId(), "临时任务");
                List<Job> next7DayJobs = jobMapper.selectNext7DayJobsByHandlerId(tempUser.getId());
                model.addAttribute("todayPlanedJobs", todayPlanedJobs);
                model.addAttribute("todayTemporaryJobs", todayTemporaryJobs);
                model.addAttribute("next7DayJobs", next7DayJobs);
//                    model.addAttribute("todayJobs",jobMapper.selectByHandlerIdAndJobType(tempUser.getId(),"计划任务"));
//                    model.addAttribute("next7DayJobs",jobMapper.selectNext7DayJobsByHandlerId(tempUser.getId()));
                return "myjob";
            } else if (state.equals("manage")) {

                List<JobBackup> jobBackups = jobBackupMapper.selectByBackupTime(DateHelper.parseDateYMD(DateHelper.parseSimpleString(DayHelper.getThisMonthSecondDay())), DateHelper.parseDateYMD(DateHelper.parseSimpleString(DayHelper.getTomorrowDay())));

                model.addAttribute("jobBackups", jobBackups);
                return "manage";
            }
        }

        //获取返回值里面的openid
        //判断openid有没有绑定过用户
        /**
         * if(openid未绑定用户){
         * 跳转到用户绑定页面，请求转发中要带有openid
         * }else(openid已经绑定用户){
         * 获取当前用户并把用户对象保存至session
         *
         * if（state=='myjob'){
         *  跳转至myjoburl
         * }else if(state=='manage'){
         * 跳转至统计分析页
         * }
         * }
         */

        return "error";

//s

    }

    @RequestMapping(value = "/returnMyJob", method = RequestMethod.GET)
    public String returnMyJob(@ModelAttribute("currentUser") Iuser currentUser, ModelMap model) {


        List<Job> todayPlanedJobs = jobMapper.selectByHandlerIdAndJobType(currentUser.getId(), "计划任务");
        List<Job> todayTemporaryJobs = jobMapper.selectByHandlerIdAndJobType(currentUser.getId(), "临时任务");
        List<Job> next7DayJobs = jobMapper.selectNext7DayJobsByHandlerId(currentUser.getId());
        model.addAttribute("todayPlanedJobs", todayPlanedJobs);
        model.addAttribute("todayTemporaryJobs", todayTemporaryJobs);
        model.addAttribute("next7DayJobs", next7DayJobs);
//                    model.addAttribute("todayJobs",jobMapper.selectByHandlerIdAndJobType(tempUser.getId(),"计划任务"));
//                    model.addAttribute("next7DayJobs",jobMapper.selectNext7DayJobsByHandlerId(tempUser.getId()));
        return "myjob";

    }

    @RequestMapping(value = "/returnMyTips", method = RequestMethod.GET)
    public String returnMyTips(@ModelAttribute("currentUser") Iuser currentUser, ModelMap model) {
        List<Tips> notAlertedTips = tipsMapper.selectNotAlertedByUserId(currentUser.getId());
        model.addAttribute("notAlertedTips", notAlertedTips);
        return "mytips";

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("currentUser") Iuser currentUser, Job job, HttpServletRequest request) {
        // System.out.println(job);
        // System.out.println("fdaff");

        String[] jobHandlerIds = request.getParameterValues("jobHandlerIds");
        String cyclePart1 = request.getParameter("cyclePart1");
        int cyclePart2 = Integer.parseInt(request.getParameter("cyclePart2"));

        Job tempJob = job;
        tempJob.setCreateTime(new Date());
        tempJob.setCreatorId(currentUser.getId());
        tempJob.setJobGroupId(RandomHelper.generateRandomInteger());

        if (job.getJobType().equals("临时任务")) {//如果是临时任务需要设置的字段
            tempJob.setIsSingle(new Byte("1"));
            tempJob.setRequiredTime(new Date());
            tempJob.setCycle("单次");
            if (job.getJobStatus() == 2) {
                tempJob.setFinishTime(new Date());
            }
            for (int i = 0; i < jobHandlerIds.length; i++) {
                tempJob.setJobHandlerId(jobHandlerIds[i]);
                jobMapper.insert(tempJob);

            }

        } else {//如果是计划任务
            tempJob.setJobStatus(0);
            tempJob.setJobFeedback("");
            tempJob.setFinishTime(null);
            if (tempJob.getIsSingle() == 1) {
                tempJob.setCycle("单次");
                for (int i = 0; i < jobHandlerIds.length; i++) {
                    tempJob.setJobHandlerId(jobHandlerIds[i]);
                    jobMapper.insert(tempJob);

                }

            } else {
                tempJob.setCycle(cyclePart1 + ":" + cyclePart2);
                for (int i = 0; i < jobHandlerIds.length; i++) {
                    tempJob.setJobHandlerId(jobHandlerIds[i]);


                    if (cyclePart1.equals("day")) {
                        int xx = 365;
                        Date tempDate = new Date();
                        while (xx > 0) {
                            tempDate = DayHelper.getNextDay(tempDate);
                            tempJob.setRequiredTime(tempDate);
                            jobMapper.insert(tempJob);
                            xx--;
                        }
                    } else if (cyclePart1.equals("week")) {
                        int xx = 52;
                        Date tempDate = new Date();
                        while (xx > 0) {
                            tempDate = DayHelper.getNextWeek(tempDate, cyclePart2);
                            tempJob.setRequiredTime(tempDate);
                            jobMapper.insert(tempJob);
                            xx--;
                        }
                    } else if (cyclePart1.equals("month")) {
                        int xx = 12;
                        Date tempDate = new Date();
                        while (xx > 0) {
                            tempDate = DayHelper.getNextMonth(tempDate, cyclePart2);
                            tempJob.setRequiredTime(tempDate);
                            jobMapper.insert(tempJob);
                            xx--;
                        }
                    }

                }
            }

        }

        return "addJobSuccess";

//s

    }

    @RequestMapping(value = "/toAddPage", method = RequestMethod.GET)
    public String toAddPage(ModelMap model, @ModelAttribute("currentUser") Iuser currentUser) {
        model.addAttribute("sourceList", sourceMapper.selectAllValid());
        List<Iuser> userList = iuserMapper.selectAllValid();
        currentUser.setName("默认为自己");
        userList.add(0, currentUser);
        model.addAttribute("userList", userList);
        return "newJob";

//s

    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(ModelMap model, @ModelAttribute("currentUser") Iuser currentUser, HttpServletRequest request) {
        String keywd = request.getParameter("keywd").trim();
        Integer isImportant = Integer.parseInt(request.getParameter("isImportant"));
        Integer isOrderByTime = Integer.parseInt(request.getParameter("isOrderByTime"));
        String backupTimeF = request.getParameter("backupTimeF");
        String backupTimeT = request.getParameter("backupTimeT");


        List<JobBackup> jobBackups = jobBackupMapper.selectBySearch(backupTimeF != "" ? DayHelper.getNextDay(DateHelper.parseDateYMD(backupTimeF)) : null, backupTimeT != "" ? DayHelper.getNextDay(DateHelper.parseDateYMD(backupTimeT)) : null, keywd == "" ? null : keywd, isImportant, isOrderByTime);
        model.addAttribute("jobBackups", jobBackups);

        model.addAttribute("keywd", request.getParameter("keywd"));
        model.addAttribute("isImportant", request.getParameter("isImportant"));
        model.addAttribute("isOrderByTime", request.getParameter("isOrderByTime"));
        model.addAttribute("backupTimeF", request.getParameter("backupTimeF"));
        model.addAttribute("backupTimeT", request.getParameter("backupTimeT"));

        return "manage";


//s

    }


    @RequestMapping(value = "/viewAndModifyJob", method = RequestMethod.GET)
    public String viewAndModifyJob(@RequestParam("job_id") Integer jobId, ModelMap model, @ModelAttribute("currentUser") Iuser currentUser) {
        model.addAttribute("currentJob", jobMapper.selectByPrimaryKey(jobId));
        return "viewAndModifyJob";
    }

    @RequestMapping(value = "/viewBackupJob", method = RequestMethod.GET)
    public String viewBackupJob(@RequestParam("job_backup_id") Integer jobBackupId, ModelMap model, @ModelAttribute("currentUser") Iuser currentUser) {
        model.addAttribute("currentJobBackup", jobBackupMapper.selectMoreDetailByPrimaryKey(jobBackupId));
        return "viewBackupJob";
    }

    @RequestMapping(value = "/updateJobSubmit", method = RequestMethod.POST)
    public String updateJobSubmit(Job job, @ModelAttribute("currentUser") Iuser currentUser, Model model) {
        Job tempJob = jobMapper.selectByPrimaryKey(job.getJobId());
        tempJob.setJobStatus(job.getJobStatus());
        tempJob.setJobFeedback(job.getJobFeedback());
        if (job.getJobStatus() == 2) {
            tempJob.setFinishTime(new Date());
        }
        jobMapper.updateByPrimaryKey(tempJob);


        return "modifyJobSuccess";
    }


    @RequestMapping(value = "/deleteJob", method = RequestMethod.GET)
    public String deleteJob(Job job, @ModelAttribute("currentUser") Iuser currentUser, @RequestParam("job_id") Integer jobId) {
        if (jobMapper.selectByPrimaryKey(jobId) != null) {
            if (jobMapper.selectByPrimaryKey(jobId).getCreatorId().equals(currentUser.getId())) {

                jobMapper.deleteByPrimaryKey(jobId);
                return "deleteJobSuccess";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/jobChecked", method = RequestMethod.GET)
    public String jobChecked(@RequestParam("job_id") Integer jobId, @RequestParam("set_done") Boolean setDone) {
        if (setDone) {
            Job job = jobMapper.selectByPrimaryKey(jobId);

            job.setFinishTime(new Date());
            job.setJobStatus(2);
            System.out.println("");
            int i = jobMapper.updateByPrimaryKey(job);
            return i + "";

        } else {
            Job job = jobMapper.selectByPrimaryKey(jobId);

            job.setFinishTime(null);
            job.setJobStatus(0);
            int i = jobMapper.updateByPrimaryKey(job);
            return i + "";
        }

    }


    @RequestMapping(value = "/addtip", method = RequestMethod.POST)
    public String addtip(@ModelAttribute("currentUser") Iuser currentUser, HttpServletRequest request) {

        String icomment = request.getParameter("icomment");
        String handler = request.getParameter("handler");
        String alertTime = request.getParameter("alertTime");

        Tips tempTips = new Tips();
        tempTips.setAlerted(false);
        tempTips.setAlertTime(DateHelper.parseDate(alertTime));
        tempTips.setIcomment(icomment);
        String[] jobHandlerIds = request.getParameterValues("jobHandlerIds");
        for (int i = 0; i < jobHandlerIds.length; i++) {
            //tempJob.setJobHandlerId(jobHandlerIds[i]);
            tempTips.setUserId(jobHandlerIds[i]);
            tempTips.setInsertTime(DateHelper.getCurrentYYYYMMDDHHMMSS());
            tipsMapper.insert(tempTips);

        }

//        if (handler != null && (!handler.trim().equals(""))) {
//            tempTips.setUserId(handler.trim());
//        } else
//            tempTips.setUserId(currentUser.getId());
//        tempTips.setInsertTime(DateHelper.getCurrentYYYYMMDDHHMMSS());
//        tipsMapper.insert(tempTips);

        return "addTipsSuccess";

//s

    }

//    @RequestMapping(value = "/toAddTipsPage", method = RequestMethod.GET)
//    public String toAddTipsPage(ModelMap model, @ModelAttribute("currentUser") Iuser currentUser) {
//
//        List<Iuser> userList = iuserMapper.selectAllValid();
//        currentUser.setName("默认为自己");
//        //fdsfaads daf da
//        userList.add(0, currentUser);
//        model.addAttribute("userList", userList);
//
//        return "newtip";
//
////s
//
//    }

    @RequestMapping(value = "/toAddTipsPage", method = RequestMethod.GET)
    public String toAddTipsPage(ModelMap model, @ModelAttribute("currentUser") Iuser currentUser) {
        model.addAttribute("sourceList", sourceMapper.selectAllValid());
        List<Iuser> userList = iuserMapper.selectAllValid();
        currentUser.setName("默认为自己");
        userList.add(0, currentUser);
        model.addAttribute("userList", userList);

        return "newtip";


//s

    }
}