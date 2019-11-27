package com.lgg.nticxs.web.controller;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.DeviceDefaultConfigurationDAO;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceDefaultConfiguration;
import com.lgg.nticxs.web.utils.Settings;

@Controller
public class MqttController {
	DeviceDAO devado= new DeviceDAO();
	private static final String URI_BACKEND=Settings.getInstance().getURIBackend();

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
			System.out.println("llego al envio del mensaje");
			result =EnviarMensajeSonoff(serial,message,swith);
		}
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}


	private String EnviarMensajeSonoff(String serial, String mensaje, String swith) {
		try {
			JSONObject message = ArmarMensajeSonoff(mensaje, swith);
			String messageFinal = message.toString();
			URL url = new URL(URI_BACKEND+"/enviopulsador/"+serial);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setDoOutput(true);
			try(OutputStream os = con.getOutputStream()) {
			    byte[] input = messageFinal.getBytes("utf-8");
			    os.write(input, 0, input.length);           
			}
			int cod_status = con.getResponseCode();
			String status = con.getResponseMessage();
			System.out.println("RESPUESTA: "+ status+": "+cod_status);
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return "fallo_envio_pulsador";
		}
		
	}


	private JSONObject ArmarMensajeSonoff(String mensaje, String swith) {
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
			if(mensaje.contains("particion-")){
				System.out.println("busco esta uri: "+ URI_BACKEND+"/envio/"+serial+"/"+"barraparticion");
				URL url = new URL(URI_BACKEND+"/envio/"+serial+"/"+"barraparticion");
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				int cod_status = con.getResponseCode();
				String status = con.getResponseMessage();
				System.out.println("RESPUESTA CAMBIO DE PARTICION: "+ status+": "+cod_status);
				Integer part = Integer.parseInt(particion);
				if(mensaje.contains("anterior"))
					mensaje=(part-1)+"";
				else if(mensaje.contains("siguiente"))
					mensaje=(part+1)+"";
			}
			URL url = new URL(URI_BACKEND+"/envio/"+serial+"/"+mensaje);
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
	
	}


	public static MqttMessage makemqttmessageString(String message1) {                       
	  	 MqttMessage message = new MqttMessage();
	  	 message.setPayload(message1.getBytes());
	  	 return message;
	  	 
	  }	
	
	
	/****            informacion particiones activas            ****/
	@GetMapping("home/obtainpartition/{serial}/{particion}")
	@ResponseBody
	public String ObtenerParticionAlarma(Model model, @PathVariable String serial,
			 @PathVariable String particion) {
		JSONObject json = new JSONObject();
		Device device = devado.retrieveBySerialNumber(serial);
		if(particion.equals("inicio")){
			//esto es en la carga de la pagina
			System.out.println("llego a obtener informacion de particiones INICIO: "+device.getParticionactiva());
			json.put("particionactiva", device.getParticionactiva());
			if(device.getParticiones()==null){
				device.setParticiones(new HashMap<>());
				devado.update(device);
			}
			if(device.getParticiones().containsKey(device.getParticionactiva())){
				json.put("contenidoparticion", device.getParticiones().get(device.getParticionactiva()));
			}else{
				json.put("contenidoparticion", "Desconocido");
			}
		}else{
			//Esto es cuando se ejecuta luego de la primera vez
			if(device.getParticiones()==null){
				device.setParticiones(new HashMap<>());
				devado.update(device);
			}
			if(device.getParticiones().containsKey(particion)){
				json.put("contenidoparticion", device.getParticiones().get(particion));
			}else{
				json.put("contenidoparticion", "Desconocido");
			}
		}
			
		return json.toString();
	}
	
	
	
	/****            zona de la alarmas            ****/
	@GetMapping("home/obtainmaxzone/{serial}")
	@ResponseBody
	public String ObtenerMaximaZonaAlarma(Model model, @PathVariable String serial) {
		Device device = devado.retrieveBySerialNumber(serial);
		Integer result = device.getMayorZonaInformada();
		JSONObject json = new JSONObject();
		json.put("result", result);
		
		if(device.getZonasObtenidas()==null){
			device.setZonasObtenidas(new HashMap<>());
			devado.update(device);
		}
		Boolean zonasapagadas = true;
		for (Map.Entry<String, String> zonaint : device.getZonasObtenidas().entrySet()) {
		    if(zonaint.getValue()!=null && zonaint.getValue().equals("1")){
		    	zonasapagadas=false;
		    	break;
		    }
		}
		json.put("zonasapagadas", zonasapagadas);
		
		List<String> listazonasencendidas= new ArrayList<>();
		if(!zonasapagadas){
			for (Map.Entry<String, String> zonaint : device.getZonasObtenidas().entrySet()) {
			    if(zonaint.getValue()!=null && zonaint.getValue().equals("1")){
			    	listazonasencendidas.add(zonaint.getKey());
			    }
			}
		}
		
		json.put("listazonasencendidas", listazonasencendidas);
		return json.toString();
	}
	
	
	@GetMapping("home/updatetildezone/{serial}")
	@ResponseBody
	public String ActualizarZonaAlarma(Model model, @PathVariable String serial) {
		Device device = devado.retrieveBySerialNumber(serial);
		Boolean zonasapagadas = true;
		JSONObject json = new JSONObject();
		for (Map.Entry<String, String> zonaint : device.getZonasObtenidas().entrySet()) {
		    if(zonaint.getValue()!=null && zonaint.getValue().equals("1")){
		    	zonasapagadas=false;
		    	break;
		    }
		}
		json.put("zonasapagadas", zonasapagadas);
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
		
		Boolean zonasapagadas = true;
		for (Map.Entry<String, String> zonaint : device.getZonasObtenidas().entrySet()) {
		    if(zonaint.getValue().equals("1")){
		    	zonasapagadas=false;
		    	break;
		    }
		}
		json.put("zonasapagadas", zonasapagadas);
		
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
	
}
