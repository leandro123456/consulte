package com.lgg.nticxs.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.model.Device;

@Controller
public class ApiProvisioningController {
	DeviceDAO devicedao = new DeviceDAO();

	@GetMapping("{deviceid}")
	public ModelAndView showMyComponents(HttpServletRequest request,ModelMap model, @PathVariable String deviceid) {
		System.out.println("Lego peticion para generar Device");
		if(request == null || request.getUserPrincipal() ==null){
			System.out.println("Session no Iniciada primero loguearse!!");
			return new ModelAndView("/loginInit", model); 
		}
		System.out.println("continua la session ya estaba iniciada");
		Device device = devicedao.retrieveBySerialNumber(deviceid);
		if(device != null) {
			System.out.println("El dispositivo no existe");
			System.out.println("Busco toda la informacion");			
			final String uri = "http://device.coiaca.com/api/product/?id="+deviceid;
		    RestTemplate restTemplate = new RestTemplate();
		    JSONObject result = new JSONObject(restTemplate.getForObject(uri, String.class));
		    System.out.println("La api me respondio: "+result.toString());
		    if(deviceid.contains("")) {
		    	
		    }
		    	
//		    ManagementDevice.createDevice(
//		    		request, deviceid, deviceid+"-name", deviceid+"-descripcion", tipodevice, defaultconfiguration,
//		    		iphostescuchar, portescuchar, deviceid+"/#", userescuchar, passescuchar, 
//		    		iphostescribir, portescribir, result.getString("mqttCommandTopic"), userescribir, passescribir, 
//		    		iphostescucharremote, portescucharremote, deviceid+"/state", userescucharremote, passescucharremote, 
//		    		iphostescribirremote, portescribirremote, result.getString("remoteConfigTopic"), userescribirremote, passescribirremote, 
//		    		
//		    		timerstringsonoff, cantidadswiths, 
//		    		tipovistatermometro, humedadtermometro, tempctermometro, sensacionctermometro, tempftermometro, sensacionftermometro);
		    
		}else {
			System.out.println("El dispositivo ya existe lo agrego como usuario");
		}
		
		
		return new ModelAndView("/home", model);
	}
	
	
	
	
}
