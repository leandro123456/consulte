package com.lgg.nticxs.web.controller;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.DeviceDefaultConfigurationDAO;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceConfiguration;
import com.lgg.nticxs.web.model.DeviceDefaultConfiguration;

@Controller
public class MqttController {
	DeviceDAO devado= new DeviceDAO();

	@GetMapping("home/configuraciondefault/{tipodevice}/{serial}")
	@ResponseBody
	public String getRunningOperations(Model model, @PathVariable String tipodevice
			, @PathVariable String serial) {
		System.out.println("Llego a la peticion de ajax");
		JSONObject json = new JSONObject();
		DeviceDefaultConfigurationDAO devdefdao= new DeviceDefaultConfigurationDAO();
		DeviceDefaultConfiguration dev = null;
		if(tipodevice.equals("alarma"))
			dev = devdefdao.retrieveByName("defaultalarma");
		else
			dev = devdefdao.retrieveByName("default");
		json.put("iphostescuchar", dev.getIphostescuchar());
		json.put("portescuchar", dev.getPortescuchar());
		json.put("userescuchar", dev.getUserescuchar());
		json.put("iphostescucharremote", dev.getIphostescucharremote());
		json.put("portescucharremote", dev.getPortescucharremote());
		json.put("userescucharremote", dev.getUserescucharremote());
		
		HashMap<String, String> topicos= establecerTopicosDefault(serial,dev.getTopicescuchar(),dev.getTopicescribir()
				,dev.getTopicescucharremote(),dev.getTopicescribirremote());
		json.put("topicescuchar",topicos.get("escuchar"));
		json.put("topicescribir", topicos.get("escribir"));
		json.put("topicescucharremote", topicos.get("escucharremoto"));
		json.put("topicescribirremote", topicos.get("escribirremoto"));

		return json.toString();
	}

	
	private HashMap<String, String> establecerTopicosDefault(String serial, String topicescuchar, String topicescribir,
			String topicescucharremote, String topicescribirremote) {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("escuchar", topicescuchar.replace("serial", serial));
		result.put("escribir", topicescribir.replace("serial", serial));
		result.put("escucharremoto", topicescucharremote.replace("serial", serial));
		result.put("escribirremoto", topicescribirremote.replace("serial", serial));	
		return result;
	}
	
	
	@GetMapping("home/sendCommand/{tipo}/{serial}/{message}/{swith}")
	@ResponseBody
	public String SendMQTTMessage(Model model, @PathVariable String message,
			 @PathVariable String tipo, @PathVariable String serial, @PathVariable String swith) {
		String result="";
		if(tipo.equals("alarma")){
			result =EnviarMensajeAlarma(serial, message,swith);
		}
		else{
			result =EnviarMensajeSonoff(serial,message,swith);
		}
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}


	private String EnviarMensajeSonoff(String serial, String mensaje, String swith) {
		String publisherId = UUID.randomUUID().toString();
		Device device = devado.retrieveBySerialNumber(serial);
		if(device!= null){
			DeviceConfiguration conf = null;
			if(device.getUsedefaultbrocker())
				conf=device.getDeviceconfiguration().get(0);
			else
				conf=device.getDeviceconfiguration().get(1);
		try {
			IMqttClient publisher = new MqttClient("ws://"+conf.getIphostescuchar()+":"+conf.getPortescuchar(),publisherId);
			
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);
			options.setUserName(conf.getUserescuchar());
			options.setPassword(conf.getPassescuchar().toCharArray());
			
			if ( !publisher.isConnected()) {
				publisher.connect(options);
	           	System.out.println("fallo la conexion");
	           	//return "fallo la conexion";
	        }else {
	        	System.out.println("ya esta conectado :" + publisher);
	        }
			JSONObject message = ArmarMensajeSonoff(mensaje, swith,conf.getPassescuchar());
	        MqttMessage msg = makemqttmessageJson(message);
	       //msg.setQos(0);
	       //msg.setRetained(true);
	        System.out.println("este es el Json: "+ message.toString());
	        if(message.toString().contains("command"))
	        	publisher.publish(conf.getTopicescribirremote(),msg);
	        else
	        	publisher.publish(conf.getTopicescribir(),msg);
		} catch (Exception e) {
			System.out.println("mensaje: "+ e.getMessage());
			e.printStackTrace();
			return "ERROR CATCH: "+e.getMessage();
		}	
		return "envio exitoso";
		}
		return "error - device null";
		
	}


	private JSONObject ArmarMensajeSonoff(String mensaje, String swith, String pass) {
		JSONObject result= new JSONObject();
  	  
		switch (mensaje) {
		case "enviaron":
			if(swith.equals("switchone"))
				result.put("SW1", "ON");
			else
				result.put("SW2", "ON");
			break;
		case "enviaroff":
			if(swith.equals("switchone"))
				result.put("SW1", "OFF");
			else
				result.put("SW2", "OFF");
			break;
		case "simulatepushbutton":
			if(swith.equals("switchone")){
				result.put("command","simulateButtonPush");
				result.put("pwd","coiaca");
				result.put("param1","PB1");
			}else{
				result.put("command","simulateButtonPush");
				result.put("pwd","coiaca");
				result.put("param1","PB1");
			}
			break;
		default:
			System.out.println("no se encontro la informacion para armar el comando");
			break;
		}
		return result;		
	}

	public static MqttMessage makemqttmessageJson(JSONObject message1) {                       
	  	 MqttMessage message = new MqttMessage();
	  	 message.setPayload(message1.toString().getBytes());
	  	 return message;
	  	 
	  }	
	

	private String EnviarMensajeAlarma(String serial, String mensaje, String particion) {
		System.out.println("LLEGO al envio de la alarma, particion: " + particion);
		
		try {
			if(mensaje.contains("alarm-"))
				mensaje=mensaje.replace("alarm-", "");
			if(mensaje.contains("armarzona"))
				mensaje=particion+"S";
			if(mensaje.contains("armartotal"))
				mensaje=particion+"A";
			URL url = new URL("http://localhost:8080/envio/"+serial+"/"+mensaje);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int cod_status = con.getResponseCode();
			String status = con.getResponseMessage();
			System.out.println("RESPUESTA: "+ status+": "+cod_status);
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return "fallo_envio";
		}
		
//		String publisherId = UUID.randomUUID().toString();
//		Device device = devado.retrieveBySerialNumber(serial);
//		if(device!= null){
//			DeviceConfiguration conf = null;
//			if(device.getUsedefaultbrocker())
//				conf=device.getDeviceconfiguration().get(0);
//			else
//				conf=device.getDeviceconfiguration().get(1);
//		try {
//			
//			IMqttClient publisher = new MqttClient("ws://"+conf.getIphostescuchar()+":"+conf.getPortescuchar(),publisherId);
//						
//			MqttConnectOptions options = new MqttConnectOptions();
//			options.setAutomaticReconnect(false);
//			options.setCleanSession(false);
//			options.setConnectionTimeout(25);
//			options.setUserName(conf.getUserescuchar());
//			options.setPassword(conf.getPassescuchar().toCharArray());			
//			if ( !publisher.isConnected()) {
//	           	System.out.println("+++++NO estaba conectada");
//	           	publisher.connect(options);
//	           	//return "fallo la conexion";
//	        }else {
//	        	System.out.println("YA esta conectado a :" + publisher);
//	        }
//			System.out.println("ESTE ES EL MENSAJE: "+mensaje);
//			String message = ArmarMensajeAlarma(mensaje, particion);
//	        MqttMessage msg = makemqttmessageString(message);
//	        msg.setQos(0);
//	        //msg.setRetained(true);
//	        publisher.publish(conf.getTopicescribir(),msg); 
//	        System.out.println("esta es la URL: "+ publisher.getServerURI());
//	        publisher.disconnect();
//	        publisher.close();
//		} catch (Exception e) {
//			System.out.println("mensaje: "+ e.getMessage());
//			e.printStackTrace();
//			return "ERROR CATCH: "+e.getMessage();
//		}	
//		return "envio exitoso";
//		}
//		return "error - device null";	
	}
	
    private String ArmarMensajeAlarma(String mensaje, String particion) {
    	String result="";
    	if(mensaje.contains("armarzona"))
    		result=particion+"S";
    	else if(mensaje.contains("armartotal"))
    			result=particion+"A"; 
    	else
    		result=mensaje.replace("alarm-", "");
		return result;
	}


	public static MqttMessage makemqttmessageString(String message1) {                       
	  	 MqttMessage message = new MqttMessage();
	  	 message.setPayload(message1.getBytes());
	  	 return message;
	  	 
	  }	
	
	
	/****            zona de la alarmas            ****/
	@GetMapping("home/obtainmaxzone/{serial}")
	@ResponseBody
	public String ObtenerMaximaZonaAlarma(Model model, @PathVariable String serial) {
		Device device = devado.retrieveBySerialNumber(serial);
		Integer result = device.getMayorZonaInformada();
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
	
	
	@GetMapping("home/obtainzone/{serial}/{zona}/{contenido}")
	@ResponseBody
	public String ActulizarZonasAlarma(Model model, @PathVariable String serial,
			@PathVariable String zona, @PathVariable String contenido) {
		JSONObject json = new JSONObject();
		Device device = devado.retrieveBySerialNumber(serial);
		zona=zona.replace("zona", "");
		if(device.getZonasObtenidas()==null){
			device.setZonasObtenidas(new HashMap<>());
			devado.update(device);
		}
		if(device.getZonasObtenidas().containsKey(zona))
			device.getZonasObtenidas().remove(zona);
		device.getZonasObtenidas().put(zona, contenido);
		devado.update(device);
		
		for (Map.Entry<String, String> entry : device.getZonasObtenidas()) {
		    System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
		}
		
		if(device.getMayorZonaInformada()<Integer.parseInt(zona)) {
			json.put("inicio", device.getMayorZonaInformada());
			json.put("fin",zona);
			json.put("fueactualizado", true);
			device.setMayorZonaInformada(Integer.parseInt(zona));
			devado.update(device);
			
		}else
			json.put("fueactualizado", false);
		return json.toString();
	}

	
//	@PostMapping("home/parsingmessage/{serial}")
//	@ResponseBody
//	public String ParsearMensaje(Model model, @PathVariable String serial, @PathVariable String zona) {
//		JSONObject json = new JSONObject();
//		Device device = devado.retrieveBySerialNumber(serial);
//		zona=zona.replace("zona", "");
//		if(device.getMayorZonaInformada()<Integer.parseInt(zona)) {
//			json.put("inicio", device.getMayorZonaInformada());
//			json.put("fin",zona);
//			json.put("fueactualizado", true);
//			device.setMayorZonaInformada(Integer.parseInt(zona));
//			devado.update(device);
//			
//		}else
//			json.put("fueactualizado", false);
//		return json.toString();
//	}

	
}
