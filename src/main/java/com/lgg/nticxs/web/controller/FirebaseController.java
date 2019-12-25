package com.lgg.nticxs.web.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirebaseController {
	
	@RequestMapping(value = "home/enviartoken/{token}")
	public void actualizarToken(@PathVariable String token) {
		System.out.println("este elemento se envio: "+token);
		//JSONObject message = new JSONObject(jsonString);
		//System.out.println(message.toString());
	}

}
