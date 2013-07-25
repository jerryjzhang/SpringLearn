import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;


public class TimeStudy {
	public static void main(String [] args)throws Exception{
		CronExpression cronString = new CronExpression("0 0 6 ? 1/1 THU,FRI *");
		System.out.println(cronString.getExpressionSummary());
		CronTrigger trigger = new CronTrigger();
		
		timeZoneTest();
	}
	
	public static void timeZoneTest(){
		//Date defaults to use local time zone
		Date date = new Date();
	
		System.out.println(date);
		System.out.println(date.toGMTString());
		//You can add (date.getTimezoneOffset() * 60 * 1000) to convert local unix time to GMT unix time
		Timestamp time = new Timestamp(date.getTime() + date.getTimezoneOffset() * 60 * 1000);
		Timestamp time0 = new Timestamp(date.getTime() - TimeZone.getDefault().getRawOffset());
		
		System.out.println(time);
		System.out.println(time0);
		
		//Date defaults to use local time zone
		Calendar cal = Calendar.getInstance();
		Timestamp time1 = new Timestamp(cal.getTimeInMillis());
		System.out.println(time1);
		Timestamp time2 = new Timestamp(cal.getTimeInMillis() - (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET)));
		System.out.println(time2);
		//Timestamp time1 = new Timestamp(date.getTime() + (Calendar.get(Calendar.ZONE_OFFSET) + Calendar.get(Calendar.DST_OFFSET)));
		//System.out.println(time1);
	}
}
