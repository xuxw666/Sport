package com.sport.Tcp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class message implements Serializable {
	public static Calendar calendar = Calendar.getInstance();
	private String username = null;
	private String message = null;
	private String time = null;
	public message(String username,String message){
		this.username = username;
		this.message = message;
		this.time = this.toStringTime(new Date().getTime());
	}
	
	private String toStringTime(long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		calendar.setTimeInMillis(time);
		return sdf.format(calendar.getTime());
	}
	
	public String toString(){
		return username+","+time+"\r"+"message："+message;
	}
}
