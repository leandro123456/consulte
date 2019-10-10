package com.lgg.nticxs.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.DeviceDefaultConfigurationDAO;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.dbcommands.MongoCommands;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceConfiguration;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.simple.SimpleDefaultConfiguration;
import com.lgg.nticxs.web.model.simple.SimpleDevice;
import com.lgg.nticxs.web.utils.ManagementDevice;

@Controller
public class DeviceController {
	private UserDAO userdao = new UserDAO();
	private DeviceDAO devicedao = new DeviceDAO();
	private DeviceDefaultConfigurationDAO deviceconfigdao = new DeviceDefaultConfigurationDAO();
	private static final String COLLECTION_DEVICE="DEVICE";
	
	
	@GetMapping("home/componentmyown")
	public String showMyComponents(HttpServletRequest request,Model model) {
		CargarDevices(model, request);
		return "device_show_my";
	}
	
	@PostMapping("home/componentmyown")
	public String showMyComponentsPost(HttpServletRequest request,Model model) {
		CargarDevices(model, request);
		return "device_show_my";
	}
	
	

	@PostMapping("home/remove/{deviceserial}")
	public String removeDevice(Model model, @PathVariable String deviceserial,HttpServletRequest request) {
		System.out.println("llego al remove!!");
		System.out.println("serial: "+ deviceserial);
		CargarDevices(model, request);
		Device device = devicedao.retrieveBySerialNumber(deviceserial);
		String nombredelejecutor = request.getUserPrincipal().getName();
		if(device.getUserowner().equals(nombredelejecutor)){
		try {
			MongoCommands.Delete(COLLECTION_DEVICE, "serialnumber", deviceserial);
			for(User user: userdao.retrieveAllUsers()) {
				int index = user.getDeviceserialnumber().indexOf(deviceserial);
				if (index != -1) {
					user.getDeviceserialnumber().remove(index);
					userdao.update(user);
				}
			}
			model.addAttribute("msg", "Borrado exitoso");
		} catch (Exception e) {
			model.addAttribute("msg1", "Error durante el proceso de borrado: "+ e.getMessage());
		}}else{
			//borro la vista asociada al usuario y el nombre del dispositivo
			device = devicedao.retrieveBySerialNumber(deviceserial);
			device.getAdmins().remove(nombredelejecutor);
			device.getVista().remove(nombredelejecutor);
			devicedao.update(device);
			//Actualizo el usuario quitandole el dispositivo
			User userComun = userdao.retrieveByMail(nombredelejecutor);
			userComun.getDeviceserialnumber().remove(deviceserial);
			userdao.update(userComun);
			model.addAttribute("msg", "Como usted no es el dueño del dispositivo, unicamente se ha quitado su relacion con el mismo");
		}
				
		return "device_show_my";
	}
	
	@GetMapping("home/info/{deviceserial}")
	public String moreInfoDevice(Model model, @PathVariable String deviceserial, HttpServletRequest request) {
		Device device= devicedao.retrieveBySerialNumber(deviceserial);
		DeviceConfiguration configuration = null;
		if(device.getUsedefaultbrocker())
			configuration= device.getDeviceconfiguration().get(0);
		else
			configuration= device.getDeviceconfiguration().get(1);
		List<String> admins = device.getAdmins();
		List<String> users = device.getUsers();
		System.out.println("llego aca info");
		model.addAttribute("configuration", configuration);
		model.addAttribute("admins", admins);
		model.addAttribute("users", users);
		model.addAttribute("device", device);

		//informacion de usuario
		String nombre = request.getUserPrincipal().getName();
		User user = userdao.retrieveByMail(nombre);
		model.addAttribute("user", user);
		System.out.println("id del usuario: "+ user.getId());
		System.out.println("device: "+ device.getSerialnumber());
		return "device_more_info";
	}
	
	
	@GetMapping("home/componentshared")
	public String showComponentsShared(HttpServletRequest request, Model model) {
		String name = request.getUserPrincipal().getName();
		User usuario = userdao.retrieveByMail(name);
		List<SimpleDevice> devices = new ArrayList<>();
		for(String namedevice : usuario.getDeviceserialnumber()) {
			Device device = devicedao.retrieveBySerialNumber(namedevice);
			devices.add(new SimpleDevice(device));
		}
		System.out.println("compartido: "+devices);
		model.addAttribute("devices", devices);
		return "device_show_my";
	}
	
	@GetMapping("home/newdevice")
	public String newDevice(HttpServletRequest request, Model model) {
		String nombre = request.getUserPrincipal().getName();
		User user = userdao.retrieveByMail(nombre);
		model.addAttribute("users", user);
		//hoy tengo solo dos configuraciones por default
		
		SimpleDefaultConfiguration confi2 = new SimpleDefaultConfiguration(deviceconfigdao.retrieveByName("defaultalarma"));
		SimpleDefaultConfiguration confi = new SimpleDefaultConfiguration(deviceconfigdao.retrieveByName("default"));
		
		System.out.println("configuracion default: "+confi.getName());
		System.out.println("configuacion defautl alarma: "+ confi2.getName());
		//model.addAttribute("configdeflocal", confi);
		//model.addAttribute("configdef", confi2);
		return "device_new";
	}
	
	
	
	
	@PostMapping("home/create")
	public String createDeviceNew(Model model,HttpServletRequest request,			
			@RequestParam(name="serialnumber", required=true) String serialnumber,
			@RequestParam(name="namedevice", required=false) String namedevice,
			@RequestParam(name="descriptiondevice", required=false) String descriptiondevice,
			@RequestParam(name="tipodevice", required=false) String tipodevice,
			
			//vista sonoff
			@RequestParam(name="timerstringsonoff", required=false) String timerstringsonoff,
			@RequestParam(name="cantidadswiths", required=false) String cantidadswiths,
			
			//termometro
			@RequestParam(name="tipovistatermometro", required=false) String tipovistatermometro,
			@RequestParam(name="humedadtermometro", required=false) String humedadtermometro,
			@RequestParam(name="tempctermometro", required=false) String tempctermometro,
			@RequestParam(name="sensacionctermometro", required=false) String sensacionctermometro,
			@RequestParam(name="tempftermometro", required=false) String tempftermometro,
			@RequestParam(name="sensacionftermometro", required=false) String sensacionftermometro,
			
			//configuracion de topicos
			@RequestParam(name="iphostescuchar", required=false) String iphostescuchar,
			@RequestParam(name="portescuchar", required=false) String portescuchar,
			@RequestParam(name="topiclisten", required=false) String topiclisten,
			@RequestParam(name="userescuchar", required=false) String userescuchar,
			@RequestParam(name="passescuchar", required=false) String passescuchar,
			@RequestParam(name="topicwrite", required=false) String topicwrite,
			@RequestParam(name="iphostescucharremote", required=false) String iphostescucharremote,
			@RequestParam(name="portescucharremote", required=false) String portescucharremote,
			@RequestParam(name="topiclistenremote", required=false) String topiclistenremote,
			@RequestParam(name="userescucharremote", required=false) String userescucharremote,
			@RequestParam(name="passescucharremote", required=false) String passescucharremote,
			@RequestParam(name="topicwriteremote", required=false) String topicwriteremote
			) {
		System.out.println("PARAMETROS DE ENTRADA serialnumber: "+ serialnumber);
		System.out.println("PARAMETROS DE ENTRADA namedevice: "+ namedevice);
		System.out.println("PARAMETROS DE ENTRADA descriptiondevice: "+ descriptiondevice);
		System.out.println("PARAMETROS DE ENTRADA tipodevice: "+tipodevice);
		System.out.println("PARAMETROS DE ENTRADA timerstringsonoff: "+ timerstringsonoff);
		System.out.println("PARAMETROS DE ENTRADA cantidadswiths: "+ cantidadswiths);
		System.out.println("PARAMETROS DE ENTRADA tipovistatermometro: "+tipovistatermometro);
		System.out.println("PARAMETROS DE ENTRADA humedadtermometro: "+ humedadtermometro);
		System.out.println("PARAMETROS DE ENTRADA tempctermometro: "+ tempctermometro);
		System.out.println("PARAMETROS DE ENTRADA sensacionctermometro: "+ sensacionctermometro);
		System.out.println("PARAMETROS DE ENTRADA tempftermometro: "+ tempftermometro);
		System.out.println("PARAMETROS DE ENTRADA sensacionftermometro: "+ sensacionftermometro);
		System.out.println("PARAMETROS DE ENTRADA iphostescuchar: "+ iphostescuchar);
		System.out.println("PARAMETROS DE ENTRADA portescuchar: "+ portescuchar);
		System.out.println("PARAMETROS DE ENTRADA topiclisten: "+ topiclisten);
		System.out.println("PARAMETROS DE ENTRADA userescuchar: "+ userescuchar);
		System.out.println("PARAMETROS DE ENTRADA passescuchar: "+ passescuchar);
		System.out.println("PARAMETROS DE ENTRADA topicwrite: "+ topicwrite);
		System.out.println("PARAMETROS DE ENTRADA iphostescucharremote: "+ iphostescucharremote);
		System.out.println("PARAMETROS DE ENTRADA portescucharremote: "+ portescucharremote);
		System.out.println("PARAMETROS DE ENTRADA topiclistenremote: "+ topiclistenremote);
		System.out.println("PARAMETROS DE ENTRADA userescucharremote: "+ userescucharremote);
		System.out.println("PARAMETROS DE ENTRADA passescucharremote: "+ passescucharremote);
		System.out.println("PARAMETROS DE ENTRADA topicwriteremote: "+topicwriteremote );
		System.out.println("ES NULL SERIAL: "+ devicedao.retrieveBySerialNumber(serialnumber) ==null);
		if(devicedao.retrieveBySerialNumber(serialnumber) ==null){
			ManagementDevice.createDevice(
					request,serialnumber, namedevice, descriptiondevice, tipodevice, 
					//propoio configuracion
					iphostescuchar, portescuchar, 
					topiclisten, userescuchar,passescuchar,
					topicwrite,	iphostescucharremote, portescucharremote,
					topiclistenremote, userescucharremote,
					passescucharremote,topicwriteremote,
					//propio de la GUI Vistas
					timerstringsonoff, cantidadswiths, tipovistatermometro, humedadtermometro,
					tempctermometro,sensacionctermometro, tempftermometro,sensacionftermometro);
			model.addAttribute("msg", "Se creo exitosamente el dispositivo");
		}else{
			System.out.println("TIPO DE DEVICE NO es NULL: "+tipodevice);
			System.out.println("TIPO DE TERMOMETRO NO es NULL: "+tipovistatermometro);
			ManagementDevice.updateDevice(request,serialnumber);
			model.addAttribute("msg", "El dispositivo ya estaba creado, usted fue agregado como usuario Administrador");
		}
		return "origin";
	}


	private void CargarDevices(Model model, HttpServletRequest request) {
		String nombre = request.getUserPrincipal().getName();
		User user = userdao.retrieveByMail(nombre);
		model.addAttribute("user", user);
		String name = request.getUserPrincipal().getName();
		User usuario = userdao.retrieveByMail(name);
		List<SimpleDevice> devices = new ArrayList<>();
		for(String namedevice : usuario.getDeviceserialnumber()) {
			Device device = devicedao.retrieveBySerialNumber(namedevice);
			devices.add(new SimpleDevice(device));
		}
		model.addAttribute("devices", devices);
	}
	
	
//	@PostMapping("home/create/{deviceserial}")
//	public String createDevice(Model model, @PathVariable String deviceserial,HttpServletRequest request,			
//			@RequestParam(name="serialnumber", required=true) String serialnumber,
//			@RequestParam(name="namedevice", required=false) String namedevice,
//			@RequestParam(name="descriptiondevice", required=false) String descriptiondevice,
//			@RequestParam(name="tipodevice", required=false) String tipodevice,
//			
//			//vista sonoff
//			@RequestParam(name="timerstringsonoff", required=false) String timerstringsonoff,
//			@RequestParam(name="cantidadswiths", required=false) String cantidadswiths,
//			
//			//termometro
//			@RequestParam(name="tipovistatermometro", required=false) String tipovistatermometro,
//			@RequestParam(name="humedadtermometro", required=false) String humedadtermometro,
//			@RequestParam(name="tempctermometro", required=false) String tempctermometro,
//			@RequestParam(name="sensacionctermometro", required=false) String sensacionctermometro,
//			@RequestParam(name="tempftermometro", required=false) String tempftermometro,
//			@RequestParam(name="sensacionftermometro", required=false) String sensacionftermometro,
//			
//			//configuracion de topicos
//			@RequestParam(name="defaultconfiguration", required=true) Boolean defaultconfiguration,
//			@RequestParam(name="iphostescuchar", required=false) String iphostescuchar,
//			@RequestParam(name="portescuchar", required=false) String portescuchar,
//			@RequestParam(name="topiclisten", required=false) String topiclisten,
//			@RequestParam(name="userescuchar", required=false) String userescuchar,
//			@RequestParam(name="passescuchar", required=false) String passescuchar,
//			@RequestParam(name="iphostescribir", required=false) String iphostescribir,
//			@RequestParam(name="portescribir", required=false) String portescribir,
//			@RequestParam(name="topicwrite", required=false) String topicwrite,
//			@RequestParam(name="userescribir", required=false) String userescribir,
//			@RequestParam(name="passescribir", required=false) String passescribir,
//			@RequestParam(name="iphostescucharremote", required=false) String iphostescucharremote,
//			@RequestParam(name="portescucharremote", required=false) String portescucharremote,
//			@RequestParam(name="topiclistenremote", required=false) String topiclistenremote,
//			@RequestParam(name="userescucharremote", required=false) String userescucharremote,
//			@RequestParam(name="passescucharremote", required=false) String passescucharremote,
//			@RequestParam(name="iphostescribirremote", required=false) String iphostescribirremote,
//			@RequestParam(name="portescribirremote", required=false) String portescribirremote,
//			@RequestParam(name="topicwriteremote", required=false) String topicwriteremote,
//			@RequestParam(name="userescribirremote", required=false) String userescribirremote,
//			@RequestParam(name="passescribirremote", required=false) String passescribirremote
//			) {
//		
//		if(devicedao.retrieveBySerialNumber(deviceserial) ==null){
//			
//			ManagementDevice.createDevice(
//					request,deviceserial, namedevice, descriptiondevice, tipodevice, 
//					//propoio configuracion
//					defaultconfiguration,
//					iphostescuchar, portescuchar, topiclisten, userescuchar,passescuchar,
//					iphostescribir, portescribir, topicwrite, userescribir, passescribir,
//					iphostescucharremote, portescucharremote, topiclistenremote, userescucharremote, passescucharremote,
//					iphostescribirremote, portescribirremote, topicwriteremote,userescribirremote,passescribirremote,
//					//propio de la GUI Vistas
//					timerstringsonoff, cantidadswiths, tipovistatermometro, humedadtermometro,
//					tempctermometro,sensacionctermometro, tempftermometro,sensacionftermometro);
//		}else{
//			
//			ManagementDevice.updateDevice(request,deviceserial);
//		}
//		return "redirect:/home";
//	}
	

}
