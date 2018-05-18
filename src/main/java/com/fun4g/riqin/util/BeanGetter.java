package com.fun4g.riqin.util;


import com.fun4g.riqin.iDao.*;
import com.fun4g.riqin.model.Iuser;
import com.fun4g.riqin.model.Job;
import com.fun4g.riqin.model.JobBackup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeanGetter {
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");

    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

    public static boolean isTimeLegal(String patternString) {

        Pattern a = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher b = a.matcher(patternString);
        if (b.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, SQLException {
        //System.out.println(MyConfig.getManagersWithoutNull().size());



//
   TipsMapper fm = (TipsMapper) BeanGetter.getBean("tipsMapper");

        System.out.println(fm.selectByPrimaryKey(1).getIcomment());


        //   System.out.println(fm.selectNext7DayJobsByHandlerId("15335178231").size());
//        Job d=new Job();
//        d.setJobComment("我爱中国");
//        System.out.println(fm.insert(d));

    //    IuserMapper iuserMapper=(IuserMapper) BeanGetter.getBean("iuserMapper");
     //   System.out.println(iuserMapper.selectAllValid().get(0).getName());
//        System.out.println(tempUser.getName());

        //   String xx=HttpRequest.sendPost("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx45b6ea3d9f9eb681&secret=87ea9c1da65bb7518a809ad473b6445d&code=041jks470McG5u1XlL170uZq470jks4L&grant_type=authorization_code","");
        //  System.out.println(xx);
// /	System.out.println(fm.selectByAll(null, null, null, null, null,Boolean.parseBoolean(""), null, null, "电路路由问", null, null, null, null, null, "nj4690").get(0).getDispatcher().getUname());
        //	PageHelper.startPage(2,20);

//		Gridbuilding gb=new Gridbuilding();
//				gb.setAcceptTime(DateHelper.getCurrentYYYYMMDDHHMMSS());
//		gb.setHasAssess(true);
//		gb.setName("测试区域2");
//		fm.insert(gb);

//	System.out.println(fm.selectByAll(null, "苏宁环球", null, null, null, null, null, null, null, null, null, null).size());
//
//	List<Gridbuilding> t=fm.selectByAll(null, "苏宁环球", null, null, null, null, null, null, null, null, null, null);
//	System.out.println(t.get(0).getName());
//	System.out.println(t.get(1).getId());
//
//	System.out.println(isTimeLegal("2015-10-31"));
//
//	IuserMapper im=(IuserMapper)BeanGetter.getBean("iuserMapper");
//	System.out.println(im.hasAuthOf("nj4690", 10209));


        //	System.out.println(((Page)fm.selectAll()).getPageSize());

//List<VItem> news = im.getLatestItems(new Integer(101), 0, 10);
//System.out.println(news.size());
//		OmOrderManager f=(OmOrderManager)BeanGetter.getBean("OmOrderManager");
//		OmOrder s=(OmOrder)f.findByOrderCode("030").get(0);
//	System.out.println(s.getOmSoSla());
//	 ConfDAO sss=(ConfDAO)BeanGetter.getBean("ConfDAO");
//	 System.out.println(sss.findAll().size())
//	;

    }

}
