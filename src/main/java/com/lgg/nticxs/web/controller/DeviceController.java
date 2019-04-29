package com.lgg.nticxs.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.dbcommands.MongoCommands;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceConfiguration;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.simple.SimpleDevice;

@Controller
public class DeviceController {
	private UserDAO userdao = new UserDAO();
	private DeviceDAO devicedao = new DeviceDAO();
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
		try {
			MongoCommands.Delete(COLLECTION_DEVICE, "serialnumber", deviceserial);
			for(User user: userdao.retrieveAll()) {
				int index = user.getDeviceserialnumber().indexOf(deviceserial);
				if (index != -1) {
					user.getDeviceserialnumber().remove(index);
					userdao.update(user);
				}
			}
			model.addAttribute("msg", "Delete Successfully");
		} catch (Exception e) {
			model.addAttribute("msg1", "Error during the deletion process: "+ e.getMessage());
		}
		return "device_show_my";
	}
	
	@GetMapping("home/info/{deviceserial}")
	public String moreInfoDevice(Model model, @PathVariable String deviceserial) {
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
	

	private void CargarDevices(Model model, HttpServletRequest request) {
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
