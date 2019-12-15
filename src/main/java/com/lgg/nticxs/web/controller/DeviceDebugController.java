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
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceConfiguration;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.simple.SimpleDevice;

@Controller
public class DeviceDebugController {
	private UserDAO userdao = new UserDAO();
	private DeviceDAO devicedao = new DeviceDAO();
	
	
	@GetMapping("home/listdebugmyown")
	public String showMyComponents1(HttpServletRequest request,Model model) {
		CargarDevices(model, request);
		return "device_show_debug_list.jsp";
	}
	
	
	@GetMapping("home/debugview/{deviceserial}")
	public String moreInfoDevice1(Model model, @PathVariable String deviceserial, HttpServletRequest request) {
		cargarDeviceDebug(model,deviceserial,request);
		return "device_debug.jsp";
	}

	@PostMapping("home/debugview/{deviceserial}/save")
	public String moreInfoDevi(Model model, @PathVariable String deviceserial, HttpServletRequest request,
			@RequestParam(name="iphostescuchar",required=false) String iphostescuchar,
			@RequestParam(name="portescuchar", required=false) String portescuchar,
			@RequestParam(name="topiclisten", required=false) String topiclisten,
			@RequestParam(name="userescuchar", required=false) String userescuchar,
			@RequestParam(name="passescuchar", required=false) String passescuchar,
			@RequestParam(name="iphostescribir", required=false) String iphostescribir,
			@RequestParam(name="portescribir", required=false) String portescribir,
			@RequestParam(name="topicwrite", required=false) String topicwrite,
			@RequestParam(name="userescribir", required=false) String userescribir,
			@RequestParam(name="passescribir", required=false) String passescribir,
			@RequestParam(name="iphostescucharremote", required=false) String iphostescucharremote,
			@RequestParam(name="portescucharremote", required=false) String portescucharremote,
			@RequestParam(name="topiclistenremote", required=false) String topiclistenremote,
			@RequestParam(name="userescucharremote", required=false) String userescucharremote,
			@RequestParam(name="passescucharremote", required=false) String passescucharremote,
			@RequestParam(name="iphostescribirremote", required=false) String iphostescribirremote,
			@RequestParam(name="portescribirremote", required=false) String portescribirremote,
			@RequestParam(name="topicwriteremote", required=false) String topicwriteremote,
			@RequestParam(name="userescribirremote", required=false) String userescribirremote,
			@RequestParam(name="passescribirremote", required=false) String passescribirremote) {
		System.out.println("que pasa con esto: "+ topiclisten+ ": " + topiclistenremote +"dsa");
		Device device = devicedao.retrieveBySerialNumber(deviceserial);
		DeviceConfiguration newconfig = new DeviceConfiguration();
		if(!iphostescuchar.isEmpty())
			newconfig.setIphostescuchar(iphostescuchar);
		if(!portescuchar.isEmpty())
			newconfig.setPortescuchar(portescuchar);
		if(!topiclisten.isEmpty())
			newconfig.setTopicescuchar(topiclisten);
		if(!userescuchar.isEmpty())
			newconfig.setUserescuchar(userescuchar);
		if(!passescuchar.isEmpty())
			newconfig.setPassescuchar(passescuchar);
		if(!topicwrite.isEmpty())
			newconfig.setTopicescribir(topicwrite);
		if(!iphostescucharremote.isEmpty())
			newconfig.setIphostescucharremote(iphostescucharremote);
		if(!portescucharremote.isEmpty())
			newconfig.setPortescucharremote(portescucharremote);
		if(!topiclistenremote.isEmpty())
			newconfig.setTopicescucharremote(topiclistenremote);
		if(!userescucharremote.isEmpty())
			newconfig.setUserescucharremote(userescucharremote);
		if(!passescucharremote.isEmpty())
			newconfig.setPassescucharremote(passescucharremote);
		if(!topicwriteremote.isEmpty())
			newconfig.setTopicescribirremote(topicwriteremote);

		if(device.getDeviceconfiguration().size()==2)
			device.getDeviceconfiguration().remove(1);
		device.getDeviceconfiguration().add(newconfig);
		device.setName("customize");
		device.setUsedefaultbrocker(false);
		devicedao.update(device);
			
		cargarDeviceDebug(model,deviceserial,request);
		model.addAttribute("msg", "actualizo correctamente");
		return "device_debug.jsp";
	}

	@PostMapping("home/debugview/{deviceserial}/defaultconfiguration")
	public String moreInfoDevid(Model model, @PathVariable String deviceserial, HttpServletRequest request){

		Device device = devicedao.retrieveBySerialNumber(deviceserial);
		device.getDeviceconfiguration().remove(1);
		device.setUsedefaultbrocker(true);
		devicedao.update(device);
		
		cargarDeviceDebug(model,deviceserial,request);
		model.addAttribute("msg", "actualizo correctamente");
		return "device_debug.jsp";
	}
	
	
	@GetMapping("home/listdebugshared")
	public String showComponentsShared1(HttpServletRequest request, Model model) {
		String name = request.getUserPrincipal().getName();
		User usuario = userdao.retrieveByMail(name);
		List<SimpleDevice> devices = new ArrayList<>();
		for(String namedevice : usuario.getDeviceserialnumber()) {
			Device device = devicedao.retrieveBySerialNumber(namedevice);
			devices.add(new SimpleDevice(device));
		}
		System.out.println("compartido: "+devices);
		model.addAttribute("devices", devices);
		return "device_show_debug_list.jsp";
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
	
	private void cargarDeviceDebug(Model model, String deviceserial, HttpServletRequest request) {
		Device device= devicedao.retrieveBySerialNumber(deviceserial);
		DeviceConfiguration configuration = null;
		if(device.getUsedefaultbrocker())
			configuration= device.getDeviceconfiguration().get(0);
		else
			configuration= device.getDeviceconfiguration().get(1);
		String nombre = request.getUserPrincipal().getName();
		User user = userdao.retrieveByMail(nombre);
		model.addAttribute("user", user);
		model.addAttribute("configuration", configuration);
		model.addAttribute("deviceserial", deviceserial);
	}

}
