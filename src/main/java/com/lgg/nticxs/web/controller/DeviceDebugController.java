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
		return "device_show_debug_list";
	}
	
	
	@GetMapping("home/debugview/{deviceserial}")
	public String moreInfoDevice1(Model model, @PathVariable String deviceserial, HttpServletRequest request) {
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
		return "device_debug";
	}
	
	@PostMapping("home/debugview/{deviceserial}/save")
	public String moreInfoDevi(Model model, @PathVariable String deviceserial, HttpServletRequest request,
			@RequestParam("iphostescuchar") String iphostescuchar) {
		System.out.println("llego!!!!!!");
		return "device_debug";
	}
	
	@PostMapping("home/debugview/{deviceserial}/defaultconfiguration")
	public String moreInfoDevid(Model model, @PathVariable String deviceserial, HttpServletRequest request,
			@RequestParam("iphostescuchar") String iphostescuchar) {
		System.out.println("llego!!!!!!");
		return "device_debug";
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
		return "device_show_debug_list";
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

}
