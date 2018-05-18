package com.fun4g.riqin.util;

import org.apache.poi.util.SystemOutLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scottsun on 2016/10/29.
 */
public class Look {
    public static void main(String []args){
        System.out.println("请输入报价：");
        InputStreamReader isr = new InputStreamReader(System.in);

        BufferedReader br = new BufferedReader(isr);
        String s = null;
        try {
            s = br.readLine();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String [] fro=s.split(",");
        double [] source=new double[fro.length];
        for(int i=0;i<fro.length;i++){
            source[i]=Double.parseDouble(fro[i]);
        }

        double mid=getAverage(source);
        System.out.println("第一次平均价："+mid);
        ArrayList<Double> source2=new ArrayList<Double>();


        for(int j=0;j<source.length;j++)
        {
            //System.out.println(Math.abs(mid - source[j]));
         //   System.out.println(Math.abs(mid - source[j])/mid);
                if((Math.abs(mid - source[j])/mid)<0.1){

                    source2.add(source[j]);
                }

        }
        DecimalFormat df   = new DecimalFormat("######0.00");
        System.out.println(source2.size());
        double last=getAverage2(source2);
        System.out.println("最优价："+df.format(last));



        for(int k=0;k<source.length;k++){

            if(source[k]>last){
                System.out.print(df.format(40-(source[k]-last)*30));
                System.out.print(",");
            }else if(source[k]<last){
                System.out.print(df.format(40-(last-source[k])*20));
                System.out.print(",");
            }else if(source[k]==last){
                System.out.print("40");
                System.out.print(",");
            }
        }


    }

    public static double getAverage(double[] list) {
        double sum = 0;
        for(int i=0;i<list.length;i++){
            sum += list[i];
        }
        return sum / list.length;
    }

    public static  double getAverage2(List<Double> list) {
        double sum = 0;
        for(int i=0;i<list.size();i++){
            sum += list.get(i).doubleValue();
        }
        return sum / list.size();
    }
}
