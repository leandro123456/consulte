package com.lgg.nticxs.web.controller;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgg.nticxs.web.utils.Settings;

@Controller
public class PorteroController {
	private static final String URI_BACKEND=Settings.getInstance().getURIBackend();
	
	@GetMapping("doorman/{user}") 
	public void getDoorman(Model model, @PathVariable String user) {
		try {
			//redigir peticion al backend
			//usuario en base64
			URL url = new URL(URI_BACKEND+"/envio/"+user);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int cod_status = con.getResponseCode();
			String status = con.getResponseMessage();
			System.out.println("RESPUESTA: "+ status+": "+cod_status);
		} catch (Exception e) {
			System.out.println("fallo en el envio del mensaje!!!! ");
		}
	}
}
