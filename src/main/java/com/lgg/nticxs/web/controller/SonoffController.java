package com.lgg.nticxs.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.DeviceDefaultConfigurationDAO;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.dbcommands.MongoCommands;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceConfiguration;
import com.lgg.nticxs.web.model.DeviceDefaultConfiguration;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.simple.SimpleDevice;
import com.lgg.nticxs.web.model.simple.SimpleTimerString;

@Controller
public class SonoffController {
	private UserDAO userdao = new UserDAO();
	private DeviceDAO devicedao = new DeviceDAO();
	private DeviceDefaultConfigurationDAO deviceconfigdao = new DeviceDefaultConfigurationDAO();
	private static final String COLLECTION_DEVICE="DEVICE";
	
	@PostMapping("/home/pushbutton/{sonoffserial}")
	public String sendPushButton(HttpServletRequest request,Model model,@PathVariable String sonoffserial
			,@RequestParam String sonoffpower) {
		System.out.println("trae: "+ sonoffpower);
		String action="";
		if(sonoffpower.equals("true"))
			action="ON";
		else
			action="OFF";
		Device device= devicedao.retrieveBySerialNumber(sonoffserial);
		DeviceConfiguration deviceconfig = null;
		if(device.getUsedefaultbrocker()) {
			deviceconfig= device.getDeviceconfiguration().get(0);
		}else {
			deviceconfig= device.getDeviceconfiguration().get(1);
		}
		String serverUri = deviceconfig.getIphostescuchar();
		String port = deviceconfig.getPortescuchar();
		String topic = deviceconfig.getTopicescribir();
		String userName = deviceconfig.getUserescuchar();
		String password = deviceconfig.getPassescuchar();
		JSONObject json = new JSONObject();
		json.put("pwd", password);
		json.put("command", "switchAction");
		json.put("SW1", action);
		System.out.println("el json: "+json);
		SimpleTimerString.sendmessageMQTT(json, serverUri, port, topic, userName, password);
		return "origin";
	}

	
	@PostMapping("/home/simulatedpushbutton/{sonoffserial}")
	public String showsimlatedPushButton(HttpServletRequest request,Model model,@PathVariable String sonoffserial) {
		Device device= devicedao.retrieveBySerialNumber(sonoffserial);
		if(device.getUsedefaultbrocker()) {
			DeviceConfiguration deviceconfig = null;
			if(device.getUsedefaultbrocker()) {
				deviceconfig= device.getDeviceconfiguration().get(0);
			}else {
				deviceconfig= device.getDeviceconfiguration().get(1);
			}
			String serverUri = deviceconfig.getIphostescuchar();
			String port = deviceconfig.getPortescuchar();
			String topic = deviceconfig.getTopicescribir();
			String userName = deviceconfig.getUserescuchar();
			String password = deviceconfig.getPassescuchar();
			JSONObject json = new JSONObject();
			json.put("pwd", password);
			json.put("command", "simulateButtonPush");
			json.put("SW1", "BTN1");
			System.out.println("el json: "+json);
			SimpleTimerString.sendmessageMQTT(json, serverUri, port, topic, userName, password);
		}else {
			
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

}
