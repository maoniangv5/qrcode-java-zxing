package com.max4a.common;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author Think
 *
 */
public class DateUtil {

	/**
	 * 获取今天00:00:00的时间
	 * @return
	 */
	public static Date getTodayZero(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0); 
		return cal.getTime();		
	}
	
	/**
	 * 
	 *
	 * <p>Description:获取某年某季度第一天和最后一天 </p>
	 *
	 * Created by [dy] [2014-4-30]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param date 季度（“YYYY.Q”）第一季度Q为1
	 * @return String[] months 时间字符串数组 格式：["2014-01-01 00:00:00","2014-03-31 23:59:59"]
	 */
	public static Date[] getStAndEdDayByQuarter(String date) {
		Date[] months = new Date[2];
		int year = Integer.parseInt(date.substring(0, 4));
		char quarter = date.charAt(date.length() - 1);
		int firstMonth = 0;//起始月份
		int endMonth = 0;//结束月份
		switch (quarter) {
			case '1':
				// 第一季度
				firstMonth = 0;
				endMonth = 2;
				break;
				
			case '2':
				// 第二季度
				firstMonth = 3;
				endMonth = 5;
				break;
				
			case '3':
				// 第三季度
				firstMonth = 6;
				endMonth = 8;
				break;

			case '4':
				// 第四季度
				firstMonth = 9;
				endMonth = 11;
				break;

			default:break;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, firstMonth, 1, 0, 0, 0);
		months[0] = calendar.getTime();
		
		calendar.set(year, endMonth, 1);
		int maxDay = calendar.getActualMaximum(Calendar.DATE);
		calendar.set(year, endMonth, maxDay, 23, 59, 59);
		months[1] = calendar.getTime();
		
		return months;
	}
	
	/**
	 * <p>
	 * Description:[时间转为字符串]
	 * </p>
	 * 
	 * @author Dy
	 * @param date 日期
	 * @param format 转化格式
	 * @return
	 */
	public static String DateToString(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat();
		if(StringUtils.isNotBlank(format)){
			sdf = new SimpleDateFormat(format);
		}else{
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		
		return sdf.format(date);
	}
	
	/**
	 * <p>
	 * Description:[字符串转化为时间]
	 * </p>
	 * 
	 * @author Dy
	 * @param date 日期字符串
	 * @param format 转化格式
	 * @return
	 * @throws ParseException 
	 */
	public static Date StringToDate(String date,String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat();
		if(StringUtils.isNotBlank(format)){
			sdf = new SimpleDateFormat(format);
		}else{
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		
		return sdf.parse(date);
	} 
	
	/**
	 * <p>
	 * Description:[获取两个时间之间相差多少天]
	 * </p>
	 * 
	 * Created by [duanying] [2015年3月6日]
	 * Midified by [修改人] [修改时间]
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(sdf.format(smdate)));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(sdf.format(bdate)));
		long time2 = cal.getTimeInMillis();
		long days = Math.abs(time2 - time1) / (1000 * 3600 * 24) + 1;

		return Integer.parseInt(String.valueOf(days));
	}
}
