package com.lgg.nticxs.web.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FirebaseController {
	
	@RequestMapping(value = "home/enviartoken" , method = RequestMethod.POST)
	public void actualizarToken(@RequestBody String jsonString) {
		JSONObject message = new JSONObject(jsonString);
		System.out.println(message.toString());
	}

}
