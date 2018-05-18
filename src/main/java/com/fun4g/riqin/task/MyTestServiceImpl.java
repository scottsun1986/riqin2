package com.fun4g.riqin.task;

import com.fun4g.riqin.iDao.JobBackupMapper;
import com.fun4g.riqin.iDao.MytaskMapper;
import com.fun4g.riqin.iDao.TipsMapper;
import com.fun4g.riqin.mail.AttachedFileMail;
import com.fun4g.riqin.mail.ExportExcel;
import com.fun4g.riqin.model.JobBackup;
import com.fun4g.riqin.model.Mytask;
import com.fun4g.riqin.model.Tips;
import com.fun4g.riqin.util.*;
import net.sf.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.util.Iterator;
import java.util.List;


@Component  //import org.springframework.stereotype.Component;
public class MyTestServiceImpl implements IMyTestService {
    public MytaskMapper getMytaskMapper() {
        return mytaskMapper;
    }

    public void setMytaskMapper(MytaskMapper mytaskMapper) {
        this.mytaskMapper = mytaskMapper;
    }

    private MytaskMapper mytaskMapper;

    public TipsMapper getTipsMapper() {
        return tipsMapper;
    }

    public void setTipsMapper(TipsMapper tipsMapper) {
        this.tipsMapper = tipsMapper;
    }

    private TipsMapper tipsMapper;

    public MyTestServiceImpl() {
        refreshToken();
    }

    // @Scheduled(cron = "1 10 17 ? * MON-FRI")
    //@Scheduled(cron = "0 0/2 * * * ?")
    @Scheduled(cron = "1 10 17 ? * MON-FRI")

    public void myTest() {
        //  System.out.println("调用时间:"+DateHelper.getCurrentYYYYMMDDHHMMSS());

        try {
            List<Mytask> mytaskList = mytaskMapper.selectToBeNotifyTask();
            Iterator<Mytask> mytaskIterator = mytaskList.iterator();
            Mytask d;
            /**
             *日清助手的 template_id=5zGQ_h0n-7yJl4RCuj_Ixti-1z0_NE8LCZTTjEeTKM0
             原来在用的  template_id=wbnqU4WexSEF3dZYs5NdlSV-d6MnlFQpu8GqtXOaUO0
             * 详细内容
             {{first.DATA}}
             员工姓名：{{keyword1.DATA}}
             提醒时间：{{keyword2.DATA}}
             任务内容：{{keyword3.DATA}}
             {{remark.DATA}}
             在发送时，需要将内容中的参数（{{.DATA}}内为参数）赋值替换为需要的信息  ​
             内容示例 OA系统之邮件发送失败BUG修复任务
             员工姓名：徐小明
             提醒时间：2015年4月23日 15:58
             任务内容：目前公司OA系统中邮件发送失败，请尽快安排时间查询问题并解决邮件发送失败的问题！
             请尽快解决，以免影响公司业务正常运作！


             */
            while (mytaskIterator.hasNext()) {
                d = mytaskIterator.next();
                MyTemplate myt = new MyTemplate();
                myt.setFirst(d.getFirst());

                myt.setTouser(d.getIuser().getWxId());
                myt.setTemplate_id(d.getTemplateId());
                myt.setUrl(d.getUrl());
                myt.setRemark(d.getRemark());
                myt.setWork(d.getWork());
                myt.setKeyword1(d.getKeyword1());
                myt.setKeyword2(d.getKeyword2());
                myt.setKeyword3(d.getKeyword3());
                String c = myt.toString();

                //  System.out.println(myt.toString());


                // System.out.println(MyToken.getInstance().getToken()+"uuu");
                HttpRequest.sendPost2("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + MyToken.getInstance().getToken(), c);
                mytaskMapper.deleteByPrimaryKey(d.getId());

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 该方法用来更新TOKEN的
     */
    @Scheduled(cron = "0 0 0/2 * * ? ")/*每2小时更新一次*/
    public void refreshToken() {
        //在用的 String re= HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token","grant_type=client_credential&appid=wx45b6ea3d9f9eb681&secret=87ea9c1da65bb7518a809ad473b6445d");
        String re = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", "grant_type=client_credential&appid=" + OAuthAPI.APP_ID + "&secret=" + OAuthAPI.APP_SECRET);
        // String re= HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token","grant_type=client_credential&appid=wxc62d0e8e8641f451&secret=91ed691438fc5e61ad6c1b291d239791");
        System.out.println(re);
        MyToken.getInstance().setToken(JSONObject.fromObject(re.substring(re.indexOf("{"))).getString("access_token"));


    }


    /**
     * 该方法用来更新TOKEN的
     */
    @Scheduled(cron = "0 0 1 * * ? ")/*每天1点生成EXCEL*/
    //@Scheduled(cron = "0 53 15 * * ? ")
    public void generateExcel() {
        // 测试学生
        ExportExcel<JobBackup> ex = new ExportExcel<JobBackup>();
        String[] headers =
                {"姓名", "任务类型", "任务说明", "完成情况", "反馈", "是否月度关注", "日期", "任务来源"};
        List<JobBackup> dataset = ((JobBackupMapper) BeanGetter.getBean("jobBackupMapper")).selectByBackupTime(DateHelper.parseDateYMD(DateHelper.parseSimpleString(DayHelper.getToDay())), DateHelper.parseDateYMD(DateHelper.parseSimpleString(DayHelper.getTomorrowDay())));


        try {

            OutputStream out = new FileOutputStream("/var/lib/tomcat7/webapps/riqin2/" + DateHelper.getCurrentYYYYMMDD() + ".xls");

            ex.exportExcel(headers, dataset, out);

            out.close();


            System.out.println("excel导出成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 该方法用来更新TOKEN的
     */

    // @Scheduled(cron = "1 05 10 ? * TUE-SAT")
    @Scheduled(cron = "1 45 8 ? * TUE-SAT")
    // @Scheduled(cron = "1 55 15 ? * TUE-SAT")
    public void sendMail() {
        try {
            System.out.println("sent at" + DateHelper.getCurrentYYYYMMDDHHMMSS());
            AttachedFileMail am = (AttachedFileMail) BeanGetter.getBean("attachedFileMail");

            // 设定mail server
            //senderImpl.setHost("smtp.163.com");
            // 建立邮件消息,发送简单邮件和html邮件的区别
            MimeMessage mailMessage = am.getSenderImpl().createMimeMessage();
            // 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
            // multipart模式 为true时发送附件 可以设置html格式
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
                    true, "utf-8");

            // 设置收件人，寄件人

            messageHelper.setTo(new String[]{"sunzhiqiang.nj@jsoa.net", "scottsun@189.cn"});
            //      messageHelper.setTo(new String[]{ "sunzhiqiang.nj@jsoa.net", "scottsun@189.cn"});

            messageHelper.setFrom("scottsun.nj@gmail.com");
            messageHelper.setSubject("日清统计（" + DateHelper.parseSimpleString(DayHelper.getYesDay()) + ")");
            // true 表示启动HTML格式的邮件
            messageHelper.setText(
                    " 昨日日清,请查收！",
                    false);
            File f = new File("/var/lib/tomcat7/webapps/riqin2/" + DateHelper.getCurrentYYYYMMDD() + ".xls");
            // File f= new File("F:\\apache-tomcat-8.0.18\\webapps\\riqin\\2016-08-18.xls");

            FileSystemResource file = new FileSystemResource(f);
            // 这里的方法调用和插入图片是不同的。
            messageHelper.addAttachment(MimeUtility.encodeWord(new String(("riqin(" + DateHelper.parseSimpleString(DayHelper.getYesDay()) + ").xls").getBytes(), "GBK")), file);


            // 发送邮件
            am.getSenderImpl().send(mailMessage);

            System.out.println("mail sent!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Scheduled(cron = "0 0/2 * * * ?")
    public void tipsSent() {
        // System.out.println("调用时间:"+DateHelper.getCurrentYYYYMMDDHHMMSS());

        try {

            List<Tips> myTipsList = tipsMapper.selectToBeNotifyTask();
            Iterator<Tips> mytaskIterator = myTipsList.iterator();
            Tips d;
            /**
             *日清助手的 template_id=5zGQ_h0n-7yJl4RCuj_Ixti-1z0_NE8LCZTTjEeTKM0
             原来在用的  template_id=wbnqU4WexSEF3dZYs5NdlSV-d6MnlFQpu8GqtXOaUO0
             * 详细内容
             {{first.DATA}}
             员工姓名：{{keyword1.DATA}}
             提醒时间：{{keyword2.DATA}}
             任务内容：{{keyword3.DATA}}
             {{remark.DATA}}
             在发送时，需要将内容中的参数（{{.DATA}}内为参数）赋值替换为需要的信息  ​
             内容示例 OA系统之邮件发送失败BUG修复任务
             员工姓名：徐小明
             提醒时间：2015年4月23日 15:58
             任务内容：目前公司OA系统中邮件发送失败，请尽快安排时间查询问题并解决邮件发送失败的问题！
             请尽快解决，以免影响公司业务正常运作！


             */
            while (mytaskIterator.hasNext()) {
                d = mytaskIterator.next();
                MyTemplate myt = new MyTemplate();
                myt.setFirst("");
                myt.setTouser(d.getIuser().getWxId());
                myt.setTemplate_id("vMyYp-Ef7EDsRKIRxDmSoZe0EEHwNkb6ohO4aTuSp00");
                myt.setUrl("");
                myt.setRemark("");
                myt.setKeyword1(d.getIcomment());
                myt.setKeyword2(DateHelper.parseStringCN(d.getAlertTime()));
                String c = myt.toString();
                //  System.out.println(myt.toString());
                // System.out.println(MyToken.getInstance().getToken()+"uuu");
                HttpRequest.sendPost2("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + MyToken.getInstance().getToken(), c);
                d.setAlerted(true);
                tipsMapper.updateByPrimaryKey(d);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}