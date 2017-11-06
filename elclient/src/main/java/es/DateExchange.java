package es;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hadoop on 2017/06/11.
 */
public class DateExchange {
    //日期格式转换，Weibo表中的时间格式比较奇怪，需要进行转换
    public static String dateExchange(String strdate) throws ParseException {
        String dateString = null;
        if(strdate.indexOf("年")!=-1){
            Date d =  new SimpleDateFormat("yyyy年MM月dd日 HH:mm").parse(strdate);
            System.out.println(d);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateString = formatter.format(d);
        }else if(strdate.indexOf("月")!=-1){
            Calendar now = Calendar.getInstance();
            String year = String.valueOf(now.get(Calendar.YEAR));
            System.out.println("年: " + year);
            Date d =  new SimpleDateFormat("MM月dd日 HH:mm").parse(strdate);
            System.out.println(d);
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
            dateString = year +"-" + formatter.format(d);
        }else{
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -1);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateString =formatter.format(c.getTime());
        }
        return dateString;
    }
}
