package com.lgg.nticxs.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		System.out.println("empezo");
		User user = userdao.retrieveByMail(usermail);
        JSONObject json = new JSONObject();
        System.out.println("llego a dashboard document");
        System.out.println("user.getDeviceserialnumber()"+ user.getDeviceserialnumber().size());
        user.getDeviceserialnumber().forEach((deviceserial,v) -> {
			System.out.println("serialnumber: "+ deviceserial);
			Device device = devicedao.retrieveBySerialNumber(deviceserial);
			if(device!= null){
				List<String> mymap = new ArrayList<>();
				mymap.add(device.getUserRole(usermail));
				mymap.add((String) device.getVista().get(usermail));
				json.put(deviceserial, mymap);
			}
		});
   		json.put("deviceserial", user.getDeviceserialnumber());
		System.out.println("salio");
        return json.toString();
	}

}
