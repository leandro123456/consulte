package com.lgg.nticxs.web.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.utils.Settings;


@Controller
public class APISendCommandsToBackend {

	private DeviceDAO devicedao = new DeviceDAO();
	private static final String URI_BACKEND=Settings.getInstance().getURIBackend();
	
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
			
			if (elUsuariopuedeSolicitarLaAccion(username,device)){
				String cod_desarmado=device.getCoddesarmado();
				System.out.println("URL  a USAR: "+ URI_BACKEND+"/ejecutaraccion/"+serial+"-"+accion+"-"+cod_desarmado);
				URL url = new URL(URI_BACKEND+"/ejecutaraccion/"+accion+"-"+cod_desarmado);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				int cod_status = con.getResponseCode();
				String status = con.getResponseMessage();
				System.out.println("RESPUESTA: "+ status+": "+cod_status);
				json.put("status", "exitoso");
				return json.toString();
			}
			else{
				json.put("status", "fallido - usuario no valido");
				return json.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", "fallido");
			return json.toString();
		}
	}


	private boolean elUsuariopuedeSolicitarLaAccion(String username, Device device) {
		System.out.println("Usuario puede solicitar la accion"+ username+" - "+ device.getSerialnumber());
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
