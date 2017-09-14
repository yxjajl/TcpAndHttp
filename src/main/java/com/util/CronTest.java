package com.util;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronExpression;

//import org.quartz.CronExpression;

public class CronTest {  
  
    public static void main(String[] args) {  
        cronTest();  
    }  
  
    private static void cronTest() {  
        try {  
            CronExpression exp = new CronExpression("0 0/10 10,11 * * ? ");  
            SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");  
            Date d = new Date();  
            int i = 0;  
            // 循环得到接下来n此的触发时间点，供验证  
            while (i < 30) {  
                d = exp.getNextValidTimeAfter(d);  
                System.out.println(df.format(d));  
                ++i;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
}