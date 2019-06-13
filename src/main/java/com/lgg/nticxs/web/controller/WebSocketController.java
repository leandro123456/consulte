package com.lgg.nticxs.web.controller;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import com.lgg.nticxs.web.model.*;


public class WebSocketController {
//	//We're using eclipse paho library  so we've to go with MqttCallback 
//	 MqttClient client = new MqttClient("tcp://localhost:1883","clientid");
//	     client.setCallback(this);
//	MqttConnectOptions mqOptions=new MqttConnectOptions();
//	     mqOptions.setCleanSession(true);
//	     client.connect(mqOptions);      //connecting to broker 
//	        client.subscribe("test/topic"); //subscribing to the topic name  test/topic
//
//	//Override methods from MqttCallback interface
//	@Override
//	public void messageArrived(String topic, MqttMessage message) throws Exception {
//	        System.out.println("message is : "+message);
//	    }
//	
	
	
}

