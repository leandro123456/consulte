package com.lgg.nticxs.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.simple.SimpleDevice;

@Controller
public class DeviceController {
	private UserDAO userdao = new UserDAO();
	private DeviceDAO devicedao = new DeviceDAO();
	
	@GetMapping("home/componentmyown")
	public String showMyComponents(HttpServletRequest request,Model model) {
		String name = request.getUserPrincipal().getName();
		User usuario = userdao.retrieveByMail(name);
		List<SimpleDevice> devices = new ArrayList<>();
		for(String namedevice : usuario.getDeviceserialnumber()) {
			Device device = devicedao.retrieveBySerialNumber(namedevice);
			devices.add(new SimpleDevice(device));
		}
		System.out.println("DEvices: "+ devices);
		model.addAttribute("devices", devices);
		return "devices";
	}
	

	@PostMapping("home/remove/{deviceserial}")
	public String removeDevice(Model model, @PathVariable String deviceserial) {
		
		return "device_show_my";
	}
	
	
	@GetMapping("home/info/{deviceserial}")
	public String moreInfoDevice(Model model, @PathVariable String deviceserial) {
		
		
		return "device_more_info";
	}
	
	
	@GetMapping("home/componentshared")
	public String showComponentsShared(HttpServletRequest request, Model model) {
		
		return "device_show_shared";
	}
	


}
