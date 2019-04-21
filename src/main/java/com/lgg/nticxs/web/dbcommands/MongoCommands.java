package com.lgg.nticxs.web.dbcommands;

import java.net.UnknownHostException;

import com.lgg.nticxs.web.DAO.helper.MongoDBRemove;

public class MongoCommands {
	static final private String DATABASE= "MQTT-Manager";

	public static void Delete(String collection, String field, String value){
		try {
			MongoDBRemove.removeSingleDoc(DATABASE, collection, field, value);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("delete successfully");
	}
}
