package com.lgg.nticxs.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.Device;
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

	
}
