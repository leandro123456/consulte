package com.lgg.nticxs.web.model.simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;


public class SimpleTimerString implements MqttCallback{
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
	static final public String SWITH_ALL = "A";
	static final public String SWITH_ONE = "1";
	static final public String SWITH_TWO = "2";
	static final public String SWITH_THREE = "3";
	public static final String TOPIC = "engine/temperature";
		
    private IMqttClient client;
    
    public SimpleTimerString(IMqttClient client) {
        this.client = client;
    }
	
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
			result = "off";
			break;
		case "1":
			result = "on";
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
			result = "monday";
			break;
		case "B":
			result = "tuesday";
			break;
		case "C":
			result = "wednesday";
			break;
		case "D":
			result = "thursday";
			break;
		case "E":
			result = "friday";
			break;
		case "F":
			result = "saturday";
			break;
		case "G":
			result = "sunday";
			break;
		case "H":
			result = "monday-tuesday-wednesday-thursday-friday-saturday-sunday";
			break;
		case "I":
			result = "monday-tuesday-wednesday-thursday-friday";
			break;
		case "J":
			result = "saturday-sunday";
			break;
		case "K":
			result = "monday-tuesday-wednesday-thursday-friday-saturday";
			break;
		case "L":
			result = "monday-wednesday-friday";
			break;
		case "M":
			result = "tuesday-thursday-saturday";
			break;
		case "N":
			result = "monday-tuesday-wednesday";
			break;
		case "O":
			result = "thursday-friday-saturday";
			break;
		case "P":
			result = "monday-wednesday-friday-saturday";
			break;
		default:
			result= "fallo parseo";
			break;
		}
		return result;
	}

	public static String checkDaysRollback(String day) {
		String result="";
		switch (day) {
		case "monday":
			result = "A";
			break;
		case "tuesday":
			result = "B";
			break;
		case "wednesday":
			result = "C";
			break;
		case "thursday":
			result = "D";
			break;
		case "friday":
			result = "E";
			break;
		case "saturday":
			result = "F";
			break;
		case "sunday":
			result = "G";
			break;
		case "monday-tuesday-wednesday-thursday-friday-saturday-sunday":
			result = "H";
			break;
		case "monday-tuesday-wednesday-thursday-friday":
			result = "I";
			break;
		case "saturday-sunday":
			result = "J";
			break;
		case "monday-tuesday-wednesday-thursday-friday-saturday":
			result = "K";
			break;
		case "monday-wednesday-friday":
			result = "L";
			break;
		case "tuesday-thursday-saturday":
			result = "M";
			break;
		case "monday-tuesday-wednesday":
			result = "N";
			break;
		case "thursday-friday-saturday":
			result = "O";
			break;
		case "monday-wednesday-friday-saturday":
			result = "P";
			break;
		default:
			result = "fallo";
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

	public static String checkAction(String action) {
		String result="";
		switch (action) {
		case "on":
			result = POWER_ON;
			break;
		case "off":
			result =POWER_OFF;
			break;
		default:
			result ="error acciones";
			break;
		}
		return result;
	}

	public static String checkSwith(String interruptor) {
		String result="";
		switch (interruptor) {
		case "All":
			result = SWITH_ALL;
			break;
		case "1":
			result = SWITH_ONE;
			break;
		case "2":
			result = SWITH_TWO;
			break;
		case "3":
			result = SWITH_THREE;
			break;
		default:
			result ="error interruptores";
			break;
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

	public static String maketimerStringFormat(String timerstringsonoff) {
		System.out.println("llego a la generacion del timerString format: "+ timerstringsonoff);
		String[] listtimerstring = timerstringsonoff.split("&@");
		System.out.println(listtimerstring.length);
		String timerresult= "";
		for(int i=0; i<listtimerstring.length; i++) {
			System.out.println(listtimerstring[i]);
			String[] timerstring = listtimerstring[i].split("&");
			System.out.println("elemento "+i+" tamano: "+ timerstring.length);
			System.out.println(timerstring[0]);
			System.out.println(timerstring[1]);
			System.out.println(timerstring[2]);
			System.out.println(timerstring[3]);
			String day = SimpleTimerString.checkDaysRollback(timerstring[0]);
			String hour = timerstring[1].replaceAll(":", "");
			String action = SimpleTimerString.checkAction(timerstring[2]);
			String cualswith = SimpleTimerString.checkSwith(timerstring[3]);
			timerresult=timerresult+day+hour+action+cualswith;
		}
		System.out.println("termino: "+ timerresult);
		return timerresult;
		
	}

	public static void sendtimerString(String timerstringvalue,String serverUri, String port, String topic,
			String userName, String password) {
		String publisherId = UUID.randomUUID().toString();
		try {
			IMqttClient publisher = new MqttClient(serverUri+":"+port,publisherId);
			
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);
			options.setUserName(userName);
			options.setPassword(password.toCharArray());
			publisher.connect(options);		
			if ( !publisher.isConnected()) {
	           	System.out.println("fallo la conexion");
	        } 
		   	 JSONObject json = new JSONObject();
		   	 json.put("pwd", password);
		   	 json.put("command", "timerString");
		   	 json.put("param1", "SW1");
		   	 json.put("param2", timerstringvalue);
		   	 System.out.println("el json: ");
	        MqttMessage msg = makemqttmessage(json);
	        msg.setQos(0);
	        msg.setRetained(true);
	        publisher.publish(topic,msg); 
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	public void sendmessageMQTT(JSONObject message,String serverUri, String port, String topic,
			String userName, String password) {
		String publisherId = UUID.randomUUID().toString();
		try {
			MqttClient publisher = new MqttClient("tcp://"+serverUri+":"+port,publisherId);
			publisher.setCallback(this);
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);
			options.setUserName(userName);
			options.setPassword(password.toCharArray());
			publisher.connect(options);
		
			if ( !publisher.isConnected()) {
	           	System.out.println("fallo la conexion");
	        }else {
	        	System.out.println("conecto a :" + publisher);
	        }
			int qos = 1;
			publisher.subscribe("RMgmt/debug", qos);
			
			CountDownLatch receivedSignal = new CountDownLatch(10);
			try {
				System.in.read();
				System.out.println("obtuvo");
			} catch (IOException e) {
				System.out.println("fallo");
			}
			receivedSignal.await(1, TimeUnit.MINUTES);
			
			//publisher.subscribe("RMgmt/debug");
//			CountDownLatch receivedSignal = new CountDownLatch(10);
//			publisher.subscribe("RMgmt/debug"
//					, (topic, msg) -> {
//			    byte[] payload = msg.getPayload();
//			    // ... payload handling omitted
//			    receivedSignal.countDown();
//			}
//					);    
//			 System.out.println(String.format("[%s] %s", topic, new String(message.getPayload())));
//			receivedSignal.await(1, TimeUnit.MINUTES);
			
			
//	        MqttMessage msg = makemqttmessage(message);
//	      //  msg.setQos(0);
//	      //  msg.setRetained(true);
//	        publisher.publish(topic,msg); 
				
		} catch (Exception e) {
			System.out.println("mensaje: "+ e.getMessage());
			e.printStackTrace();
		}	
	}
	
	
    public static MqttMessage makemqttmessage(JSONObject message1) {                    
//        byte[] payload = message.getBytes();        
//        return new MqttMessage(payload);    
    	 MqttMessage message = new MqttMessage();
    	 message.setPayload(message1.toString().getBytes());
    	 return message;
    	 
    }	
    
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
            System.out.println("message is : "+message);
        }

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}
}
