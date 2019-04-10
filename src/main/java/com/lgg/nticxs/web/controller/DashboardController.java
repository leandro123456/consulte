package com.lgg.nticxs.web.controller;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.User;

@Controller
public class DashboardController {
	private UserDAO userdao= new UserDAO();
	private DeviceDAO devicedao = new DeviceDAO();
	
	@GetMapping( "home/{usermail}/elements")
	@ResponseBody
	public String downloadDocument(@PathVariable String usermail)throws IOException {
		System.out.println("llego");
		User user = userdao.retrieveByMail(usermail);
        JSONObject json = new JSONObject();
		for(String deviceseria : user.getDeviceserialnumber()){
			Device device = devicedao.retrieveBySerialNumber(deviceseria);
			json.put("divvalue", device.getVista().get(usermail));
		}
		System.out.println("salio");
        return json.toString();
	}

}
