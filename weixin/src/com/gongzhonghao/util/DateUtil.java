package com.gongzhonghao.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String currentDate(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	public static Date getDate(int minute){
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(new Date());
		startDT.add(Calendar.MINUTE, minute);
		return startDT.getTime();
	}
	public static String format(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	public static void main(String[] args) {
		System.out.println(format(getDate(-5)));
	}
}
