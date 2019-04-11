package com.lgg.nticxs.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		User user = userdao.retrieveByMail(usermail);
        JSONObject json = new JSONObject();
        List<String> vistascompletas = new ArrayList<>();
        int j=0;
		for(String deviceseria : user.getDeviceserialnumber()){
			System.out.println("serialnmber: "+ deviceseria);
			Device device = devicedao.retrieveBySerialNumber(deviceseria);
			if(device!= null){
				vistascompletas.add(device.getVista().get(usermail));
				json.put("devicename", vistascompletas);
				json.put("device"+j, device.getSerialnumber());
				json.put("role"+device.getSerialnumber(), device.getUserRole(usermail));
			}
			j+=j;
		}
		json.put("quantity", user.getDeviceserialnumber().size());
		System.out.println("salio");
        return json.toString();
	}

}
