package com.lgg.nticxs.web.model.simple;

import java.util.ArrayList;
import java.util.List;

public class SimpleTimerString {
	private String days;
	private String hours;
	private String action;
	private String switchs;
	static final public String POWER_ON = "1";
	static final public String POWER_OFF = "0";
	static final public String DAY_MONDAY = "A";
	static final public String DAY_TUESDAY = "B";
	static final public String DAY_WEDNESDAY = "C";
	static final public String DAY_THURSDAY = "D";
	static final public String DAY_FRIDAY = "E";
	static final public String DAY_SATURDAY = "F";
	static final public String DAY_SUNDAY = "G";
	static final public String DAY_ALL = "H";
	static final public String DAY_LABORAL_WEEK = "I";
	static final public String DAY_WEEKEND = "J";
	static final public String DAY_LABORAL_WEEK_PLUS_SATURDAY = "K";
	static final public String DAY_MONDAY_WEDNESDAY_FRIDAY = "L";
	static final public String DAY_TUESDAY_THURSDAY_SATURDAY = "M";
	static final public String DAY_MONDAY_TUESDAY_WEDNESDAY = "N";
	static final public String DAY_THURSDAY_FRIDAY_SATURDAY = "O";
	static final public String DAY_MONDAY_WEDNESDAY_FRIDAY_SATURDAY = "P";
	
	
	
	public SimpleTimerString(String timerstring) {
		
		this.days = checkDays(timerstring.substring(0));
		this.hours = timerstring.substring(1,4);
		this.action = checkActions(timerstring.substring(5));
		this.switchs = checkSwitch(timerstring.substring(6));
		
	}




	private String checkSwitch(String swiths) {
		String result="";
		if(swiths.equals("A"))
			result="All";
		else
			result=swiths;
		return result;
	}




	private String checkActions(String action) {
		String result="";
		switch (action) {
		case "0":
			result = "poweroff";
			break;
		case "1":
			result = "poweron";
			break;
		default:
			break;
		}
		return result;
	}




	private String checkDays(String day) {
		String result="";
		switch (day) {
		case "A":
			result = "Monday";
			break;
		case "B":
			result = "Tuesday";
			break;
		case "C":
			result = "Wednesday";
			break;
		case "D":
			result = "Thursday";
			break;
		case "E":
			result = "Friday";
			break;
		case "F":
			result = "Saturday";
			break;
		case "G":
			result = "Sunday";
			break;
		case "H":
			result = "Monday-Tuesday-Wednesday-Thursday-Friday-Saturday-Sunday";
			break;
		case "I":
			result = "Monday-Tuesday-Wednesday-Thursday-Friday";
			break;
		case "J":
			result = "Saturday-Sunday";
			break;
		case "K":
			result = "Monday-Tuesday-Wednesday-Thursday-Friday-Saturday";
			break;
		case "L":
			result = "Monday-Wednesday-Friday";
			break;
		case "M":
			result = "Tuesday-Thursday-Saturday";
			break;
		case "N":
			result = "Monday-Tuesday-Wednesday";
			break;
		case "O":
			result = "Thursday-Friday-Saturday";
			break;
		case "P":
			result = "Monday-Wednesday-Friday-Saturday";
			break;
		default:
			break;
		}
		return result;
	}




	public static List<SimpleTimerString> obtainTimerString(String timerString) {
		List<SimpleTimerString> result = new ArrayList<>();
		if(timerString == null || timerString.length()<6)		
			return null;
		int tamanofijo = 6;
		while (timerString.length()>5) {
			String parcial = timerString.substring(0, tamanofijo);
			timerString=timerString.replace(parcial, "");
			SimpleTimerString simple= new SimpleTimerString(parcial);
			result.add(simple);
		}
		return result;
	}




	public String getDays() {
		return days;
	}




	public void setDays(String days) {
		this.days = days;
	}




	public String getHours() {
		return hours;
	}




	public void setHours(String hours) {
		this.hours = hours;
	}




	public String getAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}




	public String getSwitchs() {
		return switchs;
	}




	public void setSwitchs(String switchs) {
		this.switchs = switchs;
	}




	public static String getPowerOn() {
		return POWER_ON;
	}




	public static String getPowerOff() {
		return POWER_OFF;
	}




	public static String getDayMonday() {
		return DAY_MONDAY;
	}




	public static String getDayTuesday() {
		return DAY_TUESDAY;
	}




	public static String getDayWednesday() {
		return DAY_WEDNESDAY;
	}




	public static String getDayThursday() {
		return DAY_THURSDAY;
	}




	public static String getDayFriday() {
		return DAY_FRIDAY;
	}




	public static String getDaySaturday() {
		return DAY_SATURDAY;
	}




	public static String getDaySunday() {
		return DAY_SUNDAY;
	}




	public static String getDayAll() {
		return DAY_ALL;
	}




	public static String getDayLaboralWeek() {
		return DAY_LABORAL_WEEK;
	}




	public static String getDayWeekend() {
		return DAY_WEEKEND;
	}




	public static String getDayLaboralWeekPlusSaturday() {
		return DAY_LABORAL_WEEK_PLUS_SATURDAY;
	}




	public static String getDayMondayWednesdayFriday() {
		return DAY_MONDAY_WEDNESDAY_FRIDAY;
	}




	public static String getDayTuesdayThursdaySaturday() {
		return DAY_TUESDAY_THURSDAY_SATURDAY;
	}




	public static String getDayMondayTuesdayWednesday() {
		return DAY_MONDAY_TUESDAY_WEDNESDAY;
	}




	public static String getDayThursdayFridaySaturday() {
		return DAY_THURSDAY_FRIDAY_SATURDAY;
	}




	public static String getDayMondayWednesdayFridaySaturday() {
		return DAY_MONDAY_WEDNESDAY_FRIDAY_SATURDAY;
	}
	
	
}
