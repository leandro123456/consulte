package com.lgg.nticxs.web.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirebaseController {
	
	@GetMapping(value = "home/enviartoken/{token}")
	@ResponseBody
	public String actualizarToken(@PathVariable String token) {
		System.out.println("este elemento se envio: "+token);
		//JSONObject message = new JSONObject(jsonString);
		//System.out.println(message.toString());
		return "exitoso";
		
	}

}
