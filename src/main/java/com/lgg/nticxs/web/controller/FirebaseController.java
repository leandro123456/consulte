package com.lgg.nticxs.web.controller;

import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.Notificacion;
import com.lgg.nticxs.web.model.User;

@Controller
public class FirebaseController {
	private UserDAO userdao = new UserDAO();
	
	/**
	 * 
	 * @apiNote Este metodo se usa para actulizar los tokens usados por Firebase
	 * @param token
	 * @param username
	 * @return
	 */
	@GetMapping(value = "home/enviartoken/{token}/{username}")
	@ResponseBody
	public String actualizarToken(@PathVariable String token,@PathVariable String username) {
		String nombre= new String(Base64.getDecoder().decode(username.getBytes()));
		System.out.println("username: "+ username);
		System.out.println("decodificado: "+ nombre);
		UserDAO userdao=new UserDAO();
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
	}
	
	
	
	/**
	 * 
	 * @apiNote Este metodo se usa para actulizar los tokens usados por Firebase
	 * @param token
	 * @param username
	 * @return
	 */
	@GetMapping(value = "profileuser/notificaciones/enviartoken/{token}/{username}")
	@ResponseBody
	public String actualizarTokenReplica(@PathVariable String token,@PathVariable String username) {
		String nombre= new String(Base64.getDecoder().decode(username.getBytes()));
		System.out.println("username: "+ username);
		System.out.println("decodificado: "+ nombre);
		UserDAO userdao=new UserDAO();
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
	}
	
	
	
	
    @GetMapping("/profileuser/notificaciones")
    public String editProfileNotifications(Model model) {
    	System.out.println("llego al controlador de configuracion de Notificacione");
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println("Edit profile - busco el usuario: "+ authentication.getName());
        User user = userdao.retrieveByMail(authentication.getName());
    	model.addAttribute("user", user);
    	model.addAttribute("armar", user.getNotificaciones().get(Notificacion.CONDICION_ARMADO));
    	model.addAttribute("disparar", user.getNotificaciones().get(Notificacion.CONDICION_DISPARADO));
    	return "user_edit_notications.jsp";
    }
    
    
	
    /**
     * @apiNote Este metodo actualiza en la base de datos, las preferencias del usuario a recibir notificaciones via Firebase
     * @param model
     * @param notdesarmado
     * @param notactivacion
     * @param action
     * @return
     */
    @PostMapping("/profileuser/notificaciones")
    public String editProfileNotificationsResponse(Model model,
    		@RequestParam(name="armed", required=false) Boolean notdesarmado,
    		@RequestParam(name="trigered", required=false) Boolean notactivacion,
    		@RequestParam(name="action", required=true) String action) {
    	System.out.println("***********llego al controlador de configuracion de Not Response");
    	if(action.equals("save")) {
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	System.out.println("busco el usuario: "+ authentication.getName());
	        System.out.println("notificacion de armado: "+ notdesarmado);
	        System.out.println("notificacion de disparada: "+notactivacion);
	        if(notdesarmado!= null)
	        	ActualizarNotificacion(notdesarmado,Notificacion.CONDICION_ARMADO,authentication.getName());
	        else{
	        	ActualizarNotificacion(false,Notificacion.CONDICION_ARMADO,authentication.getName());
	        }
	        if(notactivacion != null)
	        	ActualizarNotificacion(notactivacion,Notificacion.CONDICION_DISPARADO, authentication.getName());
	        else
	        	ActualizarNotificacion(false,Notificacion.CONDICION_DISPARADO, authentication.getName());
	        model.addAttribute("msg", "Proceso de actualizacion de notificaciones completa");
    	}else
    		System.out.println("NO ES SABE");
    	System.out.println("--------- salio, vuelve al home");
    	return "redirect:/home";
    }
    
    
    
    /**                    UTILS */
    
	private void ActualizarNotificacion(Boolean notificacion, String tiponotificacion, String username) {
		User user =userdao.retrieveByMail(username);
		if(user !=null) {
			user.getNotificaciones().put(tiponotificacion,notificacion);
			userdao.update(user);
			System.out.println("Actualizacion de usuarios completa");
		}
		else {
			System.out.println("usuario para carga de notificaciones es NULL!!");
		}

		
	}
    
}
