package com.lgg.nticxs.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.User;

@Controller
public class FirebaseController {
	
	@GetMapping(value = "home/enviartoken/{token}")
	@ResponseBody
	public String actualizarToken(HttpServletRequest request,@PathVariable String token) {
		UserDAO userdao=new UserDAO();
		if(request == null && request.getUserPrincipal()==null){
			String nombre = request.getUserPrincipal().getName();
			User user = userdao.retrieveByMail(nombre);
			if(user!= null) {
				if(user.getFirebasetoken()==null) {
					ArrayList<String> tokens= new ArrayList<String>();
					tokens.add(token);
					user.setFirebasetoken(tokens);
					userdao.update(user);
					return "creacion de token y actualizacion de los valores";
				}else {
					System.out.println("la lista de firebase token es distinta de null");
					if(!user.getFirebasetoken().contains(token)) {
						user.getFirebasetoken().add(token);
						userdao.update(user);
						System.out.println("se agrego token");
						return "se agrego token";
					}else {
						System.out.println("ya tenia el token");
						return "ya tenia el token";
					}
				}
			}else {
				System.out.println("el usuario es null");
				return "el usuario es null";
			}
		}else {
			System.out.println("el valor de http es null");
			return "el valor de http es null";
		}
	}
}
