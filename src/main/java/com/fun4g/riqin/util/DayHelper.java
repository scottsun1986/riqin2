package com.fun4g.riqin.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/29.
 */
public class DayHelper {
    /**
     * 周日是1，周一是2
     *
     * @param currentDate
     * @param wantDay
     * @return
     */
    public static Date getNextWeek(Date currentDate, int wantDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.set(Calendar.DAY_OF_WEEK, wantDay);
        return cal.getTime();
    }

    /**
     * @param currentDate
     * @param wantDay
     * @return
     */
    public static Date getNextMonth(Date currentDate, int wantDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        if (cal.get(Calendar.DAY_OF_MONTH) >= wantDay) {
            cal.add(Calendar.MONTH, 1);
        }
        cal.set(Calendar.DAY_OF_MONTH, wantDay);
        System.out.println(cal.getTime());
        return cal.getTime();
    }

    public static Date getNextDay(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        cal.add(Calendar.DATE, 1);
        System.out.println(cal.getTime());
        return cal.getTime();
    }
    public static Date getThisMonthSecondDay(){

        Calendar cal=Calendar.getInstance();//获取当前日期
        cal.set(Calendar.DAY_OF_MONTH,2);//设置为1号,当前日期既为本月第一天
      return cal.getTime();
    }
    public static Date getTomorrowDay(){
        Calendar cal=Calendar.getInstance();//获取当前日期
        cal.add(Calendar.DATE,1);
        return cal.getTime();
    }
    public static Date getToDay(){
        Calendar cal=Calendar.getInstance();//获取当前日期
        System.out.println(cal.getTime());
        return cal.getTime();
    }
    public static Date getYesDay(){
        Calendar cal=Calendar.getInstance();//获取当前日期
        cal.add(Calendar.DATE,-1);
        return cal.getTime();
    }
    public static void main(String[] args) {
        System.out.println(DayHelper.getTomorrowDay());
    }
}
