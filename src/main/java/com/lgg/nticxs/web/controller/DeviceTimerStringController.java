package com.lgg.nticxs.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
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
import com.lgg.nticxs.web.model.simple.SimpleTimerString;

@Controller
public class DeviceTimerStringController {
	private UserDAO userdao = new UserDAO();
	private DeviceDAO devicedao = new DeviceDAO();
	
	
	@GetMapping("home/timerstring")
	public String moreInfoDevice1(Model model, HttpServletRequest request,
			@RequestParam("serialnumber") String serialnumber) {
		String nombre = request.getUserPrincipal().getName();
		User user = userdao.retrieveByMail(nombre);
		model.addAttribute("user", user);
		model.addAttribute("deviceserial", serialnumber);
		Device device= devicedao.retrieveBySerialNumber(serialnumber);
		List<SimpleTimerString> timerstringconfig = SimpleTimerString.obtainTimerString(device.getTimerString());
		model.addAttribute("timerstringconfig", timerstringconfig);
		return "device_timerString";
	}

	@GetMapping("home/settimerString/{deviceserial}")
	public String viewTimerString(Model model, @PathVariable String deviceserial) {
		System.out.println("entro al Timer String Edit");
		Device device = devicedao.retrieveBySerialNumber(deviceserial);
		model.addAttribute("deviceserial", deviceserial);
		if(device != null) {
			String timerString =device.getTimerString();
			List<SimpleTimerString> timer= SimpleTimerString.obtainTimerString(timerString);
			if(timer!=null) {
				for(SimpleTimerString tim : timer) {
					System.out.println(tim);
				}
			}
		}else
			System.out.println("el device es null");
		return "timerstringedit";
	}
	
	@PostMapping("home/settimerString/{deviceserial}")
	public String updateTimerString(Model model, @PathVariable String deviceserial,
			@RequestParam(name="timerstringsonoff", required=false) String timerstringsonoff) {
		Device device = devicedao.retrieveBySerialNumber(deviceserial);
		String message = SimpleTimerString.maketimerStringFormat(timerstringsonoff);
		if(message.contains("fallo")) {
			model.addAttribute("msg1", "la combinacion de dias es incorrecta");
			return "timerstringedit";
		}
		device.setTimerString(message);
		devicedao.update(device);
		DeviceConfiguration conf = null;
		if(device.getUsedefaultbrocker()) 
			conf = device.getDeviceconfiguration().get(0);
		else
			conf = device.getDeviceconfiguration().get(1);
		JSONObject json = new JSONObject();
	   	json.put("pwd", "mqttmng");
	   	json.put("command", "timerString");
	   	json.put("value",message);
		SimpleTimerString.sendmessageMQTT(json, conf.getIphostescuchar(), conf.getPortescuchar(), conf.getTopicescribir(), conf.getUserescuchar(), conf.getPassescuchar());
		return "origin";
	}
	
}
