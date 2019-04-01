package com.lgg.nticxs.web.helper;

import com.lgg.nticxs.web.utils.WSLogger;

/**
 * Created by movasim on 19/09/16.
 */
public class ES3Helper {
	
	private static WSLogger logger = new WSLogger();
  
    public static void setFallbackAttribute(String eid, String iccid) {
    	//Send message to core
  //  			CommandDTO dataFromPush= new CommandDTO(Utils.hexStringToByteArray(eid),iccid, "fallbackAtribute");
//    			final Gson gson = new Gson();
//    			final String message = gson.toJson(dataFromPush);
//    			try {
//    				Queue.getInstance().send(Settings.getInstance().getQueueName(), message);
//    			} catch (IOException e) {
//    				logger.logger("ERROR", "SM-WEB", "", "", "", "setFallbackAttribute()", eid, iccid, "EID: " + eid + ", ICCID: " + iccid, "Error in sending the message");
//    				
//    				e.printStackTrace();
//    			}
    }
    
    public static void setEmergencyProfile(String eid, String iccid) {
    	//Send message to core
//    			CommandDTO dataFromPush= new CommandDTO(Utils.hexStringToByteArray(eid),iccid, "SetEmergencyProfile");
//    			final Gson gson = new Gson();
//    			final String message = gson.toJson(dataFromPush);
//    			try {
//    				Queue.getInstance().send(Settings.getInstance().getQueueName(), message);
//    			} catch (IOException e) {
//    				logger.logger("ERROR", "SM-WEB", "", "", "", "setEmergencyProfile()", eid, iccid, "EID: " + eid + ", ICCID: " + iccid, "Error in sending the message");
//    				
//    				e.printStackTrace();
//    			}
    }
    
    public static void enableFallback(String eid, Boolean available) {
    	//Send message to core
//    			CardDAO carddao = new CardDAO();
//    			Card card= carddao.retrieveByEid(eid);
//    			String iccid=card.getEnableProfile().getIccid();
//    			CommandDTO dataFromPush;
//    			if(available)
//    				dataFromPush= new CommandDTO(Utils.hexStringToByteArray(eid),iccid, "enableFallbackAtribute");
//    			else
//    				dataFromPush= new CommandDTO(Utils.hexStringToByteArray(eid),iccid, "disableFallbackAtribute");
//    			final Gson gson = new Gson();
//    			final String message = gson.toJson(dataFromPush);
//    			try {
//    				Queue.getInstance().send(Settings.getInstance().getQueueName(), message);
//    			} catch (IOException e) {
//    				logger.logger("ERROR", "SM-WEB", "", "", "", "enableFallback()", eid, iccid, "EID: " + eid + ", ICCID: " + iccid, "Error in sending the message");
//    				
//    				e.printStackTrace();
//    			}
    }
    
    public static void statusNumberFallback(String eid, String iccid) {
    	//Send message to core
//    			CommandDTO dataFromPush= new CommandDTO(Utils.hexStringToByteArray(eid),iccid, "setStatusNumber");
//    			final Gson gson = new Gson();
//    			final String message = gson.toJson(dataFromPush);
//    			try {
//    				Queue.getInstance().send(Settings.getInstance().getQueueName(), message);
//    			} catch (IOException e) {
//    				logger.logger("ERROR", "SM-WEB", "", "", "", "statusNumberFallback()", eid, iccid, "EID: " + eid + ", ICCID: " + iccid, "Error in sending the message");
//    				
//    				e.printStackTrace();
//    			}
    }
}
