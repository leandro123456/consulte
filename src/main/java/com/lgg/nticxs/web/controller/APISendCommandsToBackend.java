package com.lgg.nticxs.web.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.utils.Settings;


@Controller
public class APISendCommandsToBackend {

	private DeviceDAO devicedao = new DeviceDAO();
	private static final String URI_BACKEND=Settings.getInstance().getURIBackend();
	private UserDAO userdao= new UserDAO();
	
	/**
	 * Esta clase se usa para enviar los controles de armado y desarmado de la alarma al backend
	 */
	@GetMapping("/home/ejecutaraccion/{accion}")
	@ResponseBody
	public String enviarPeticionAlarma(Model model,HttpServletRequest request,			
			@PathVariable(name="accion") String accion){
		
		JSONObject json = new JSONObject();
		try {			
			String[] vector = accion.split(Pattern.quote("-"));
			String serial = vector[0];
			String command=vector[1];
			System.out.println("part1: "+ command);
			System.out.println("part2: "+ serial);
			Device device= devicedao.retrieveBySerialNumber(serial);
			String username = request.getUserPrincipal().getName();
			System.out.println("usuarioSolicitante de accion: "+ username);
			String usuarioSolicitante= new String(Base64.getEncoder().encode(username.getBytes()));
			
			if(command.equals("desarmar")) {
				if (elUsuariopuedeSolicitarLaAccion(username,device)){
					System.out.println("URL  a USAR: "+ URI_BACKEND+"/ejecutaraccion/"+usuarioSolicitante+"/"+accion);
					URL url = new URL(URI_BACKEND+"/ejecutaraccion/"+usuarioSolicitante+"/"+accion);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					int cod_status = con.getResponseCode();
					String status = con.getResponseMessage();
					System.out.println("RESPUESTA: "+ status+": "+cod_status);
					json.put("status", "exitoso");
					json.put("fallo", false);
					return json.toString();
				}
				else{
					json.put("status", "fallido - Usuario no valido");
					json.put("fallo", true);
					return json.toString();
				}
			}else {
				System.out.println("URL  a USAR: "+ URI_BACKEND+"/ejecutaraccion/"+usuarioSolicitante+"/"+accion);
				URL url = new URL(URI_BACKEND+"/ejecutaraccion/"+usuarioSolicitante+"/"+accion);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				int cod_status = con.getResponseCode();
				String status = con.getResponseMessage();
				System.out.println("RESPUESTA: "+ status+": "+cod_status);
				json.put("status", "exitoso");
				json.put("fallo", false);
				return json.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", "fallido");
			json.put("fallo", true);
			return json.toString();
		}
	}
	
	
	/**
	 * @description Esta funcion es para ver que acciones ejecuta cada Vista
	 */
	@GetMapping("/home/tiposdeVista")
	@ResponseBody
	public String cargarTiposDeAlarma(Model model,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			String nombre = request.getUserPrincipal().getName();
			String usuarioSolicitante= new String(Base64.getEncoder().encode(nombre.getBytes()));
			User user = userdao.retrieveByMail(nombre);
			Map<String,String> serials=user.getDeviceserialnumber();
			String alarma="";
			String alarmav2="";
			String otros="";
			for (String serial : serials.keySet()) {
				Device device= devicedao.retrieveBySerialNumber(serial);
				if(device!=null) {
					String vista=device.getVista().get(usuarioSolicitante);
					vista= vista.split(";")[0];
					if(vista.equals("alarmav2")) {
						if(alarmav2.equals(""))	alarmav2=alarmav2+serial;
						else	alarmav2=alarmav2+"-"+serial;
					}else if(vista.equals("alarma")) {
						if(alarma.equals(""))	alarma=alarma+serial;
						else	alarma=alarma+"-"+serial;
					}
					else {
						if(otros.equals(""))	otros=otros+serial;
						else	otros=otros+"-"+serial;
					}
				}
			}
			json.put("alarmav1", alarma);
			json.put("alarmav2", alarmav2);
			json.put("otros", otros);
			json.put("success", true);
			System.out.println(json.toString());
			return json.toString();
		} catch (Exception e) {
			json.put("alarmav1","");
			json.put("alarmav2", "");
			json.put("otros", "");
			json.put("success", false);
			return json.toString();
		}
	}
	
	
	/**
	 * @description Responder tipo de alarma si la conoce para el usuario
	 */
	@GetMapping("/home/evaluaralarmareportada/{topico}/{contenido}")
	@ResponseBody
	public String evaluarAlarmaReportada(Model model,HttpServletRequest request,
			@PathVariable(name="topico") String topico,@PathVariable(name="contenido") String contenido){
		System.out.println("LLEGO  MENSAJE A EVALUAR!!"+ contenido);
		JSONObject json = new JSONObject();
		try {
			String nombre = request.getUserPrincipal().getName();
			User user = userdao.retrieveByMail(nombre);
			String usuarioSolicitante= new String(Base64.getEncoder().encode(nombre.getBytes()));
			topico=new String(Base64.getDecoder().decode(topico.getBytes()));
			contenido=new String(Base64.getDecoder().decode(contenido.getBytes()));
			String[] direcciones=topico.split("/");
			String serial=direcciones[0];
			String accion= direcciones[1];
			if(user.getDeviceserialnumber().containsKey(serial)) {
				json.put("conocido",true);
				json.put("serial", serial);
				json.put("tipovista", tipovista(serial,usuarioSolicitante));
				switch (accion) {
					case "keepAlive":
						JSONObject jsonreported= new JSONObject(contenido); 
						json.put("dateTime", jsonreported.get("dateTime"));
						json.put("dBm", jsonreported.get("dBm"));
						json.put("accion", accion);
						break;
					case "activePartition":
						json.put("activo", contenido);
						json.put("accion", accion);
						json.put("statuspartition",estadoParticion(serial,contenido));
						break;
					case "Status":
						json.put("status", contenido);
						json.put("accion", accion);
						break;
					default:
						break;
				}
				if (accion.startsWith("Zone")) {
					json.put("zone", contenido);
					json.put("numzone",accion.replace("Zone", ""));
					json.put("accion", "Zone");
					
				}
				if(accion.startsWith("Partition")) {
					json.put("numpartition", accion.replace("Partition", ""));
					json.put("statuspartition", contenido);
					json.put("accion", "Partition");
				}
				return json.toString();
			}else {	
				System.err.println("FALLO!! el parseo");
				json.put("conocido", false); 
				return json.toString(); 
				}		
		} catch (Exception e) {
			System.err.println("otro fallo!!");
			e.printStackTrace();
			json.put("conocido", false);
			return json.toString();
		}
	}
	
	
	private String estadoParticion(String serial, String contenido) {
		try {
			Device device=devicedao.retrieveBySerialNumber(serial);
			String estadoParticion=device.getParticiones().get(contenido);
			if(estadoParticion!=null)
				return estadoParticion;
			else
				return "Unknown";
		} catch (Exception e) {
			System.out.println("Fallo en la busqueda del contenido de la particion");
			return "Unknown";
		}
	}


	@GetMapping("/home/changepartition/{serial}/{particion}")
	@ResponseBody
	public String cambiarDeParticionAlarma(Model model,HttpServletRequest request,			
			@PathVariable(name="particion", required=true) String numparticion,
			@PathVariable(name="serial", required=true) String serial){
		System.out.println("llego a la particion!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+ numparticion);
		JSONObject json = new JSONObject();
		try {
			System.out.println("llego a la particion: "+ numparticion);
			if(!numparticion.equals("none")) {				
				System.out.println("URL  a USAR: "+ URI_BACKEND+"/cambioparticion/"+serial+"/"+numparticion);
				URL url = new URL(URI_BACKEND+"/cambioparticion/"+serial+"/"+numparticion);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				int cod_status = con.getResponseCode();
				String status = con.getResponseMessage();
				System.out.println("RESPUESTA: "+ status+": "+cod_status);
				json.put("status", "exitoso");
				json.put("fallo", false);
				return json.toString();
			}
			json.put("status", "fallido");
			json.put("fallo", true);
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", "fallido");
			json.put("fallo", true);
			return json.toString();
		}

	}
		
	
	@GetMapping("/home/configuracionalarma/{serial}/updatepass/{pass}/{repeatpass}")
	@ResponseBody
	public String updatePassAlarma(Model model,HttpServletRequest request,			
			@PathVariable(name="pass", required=true) String pass,
			@PathVariable(name="repeatpass", required=true) String repeatpass,
			@PathVariable(name="serial", required=true) String serial){
		System.out.println("llego a la particion!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+ pass+" - "+repeatpass);
		JSONObject json = new JSONObject();
		try {
			String nombre = request.getUserPrincipal().getName();
			String usuarioSolicitante= new String(Base64.getEncoder().encode(nombre.getBytes()));
			if(pass.equals(repeatpass)) {				
				System.out.println("URL  a USAR: "+ URI_BACKEND+"/updatepass/"+usuarioSolicitante+"/"+serial+"/"+pass);
				URL url = new URL(URI_BACKEND+"/updatepass/"+usuarioSolicitante+"/"+serial+"/"+pass);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				int cod_status = con.getResponseCode();
				String status = con.getResponseMessage();
				System.out.println("RESPUESTA: "+ status+": "+cod_status);
				json.put("status", "successful - password updated");
				json.put("fallo", false);
				return json.toString();
			}
			json.put("status", "failed - the passwords are not the same");
			json.put("fallo", true);
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", "failed - iternal error");
			json.put("fallo", true);
			return json.toString();
		}

	}
	


	/**						UTILS				 */
	
	private String tipovista(String serial,String usuario) {
		String vista="";
		Device device= devicedao.retrieveBySerialNumber(serial);
		if(device!=null) {
			vista=device.getVista().get(usuario);
			vista= vista.split(";")[0];
		}
		return vista;
	}
	

	private boolean elUsuariopuedeSolicitarLaAccion(String username, Device device) {
		System.out.println("Usuario puede solicitar la accion"+ username+" - "+ device.getSerialnumber());
		username =Base64.getEncoder().encodeToString(username.getBytes());
		if (device.getUserowner().equals(username))
			return true;
		for(String useradmin: device.getAdmins()){
			if(useradmin.equals(username))
				return true;
		}
		for(String useronly: device.getUsers()){
			if(useronly.equals(username))
				return true;
		}
		return false;
	}
	
	

	
}
