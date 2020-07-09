package com.lgg.nticxs.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.Notificacion;
import com.lgg.nticxs.web.model.User;

@Controller
public class NotificacionesController {
	
	   @GetMapping("home/notificacionesalarma/{serial}")
	    public String editProfileNotifications(Model model,@PathVariable String serial) {
	    	System.out.println("llego al controlador de configuracion de Notificacione");
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	System.out.println("Edit profile - busco el usuario: "+ authentication.getName());
	        UserDAO userdao = new UserDAO();
	    	User user = userdao.retrieveByMail(authentication.getName());
	    	model.addAttribute("user", user);
	    	model.addAttribute("armarcloud", user.getNotificaciones().get(Notificacion.CONDICION_ARMADO+"-"+serial));
	    	model.addAttribute("dispararcloud", user.getNotificaciones().get(Notificacion.CONDICION_DISPARADO+"-"+serial));
	    	model.addAttribute("armedmail", user.getNotificaciones().get(Notificacion.CONDICION_ARMADO_MAIL+"-"+serial));
	    	model.addAttribute("dispararmail", user.getNotificaciones().get(Notificacion.CONDICION_DISPARADO_MAIL+"-"+serial));
	    	model.addAttribute("serial", serial);
	    	return "user_notications_alarma.jsp";
	    }
	   
	   @PostMapping("/notificacionesalarma")
	    public String editProfileNotificationsAlarma(Model model,
	    		@RequestParam(name="serial", required=true) String serial,
	    		@RequestParam(name="armarcloud", required=false) Boolean armadocloud,
	    		@RequestParam(name="dispararcloud", required=false) Boolean triggercloud,
	    		@RequestParam(name="armedmail", required=false) Boolean armadomail,
	    		@RequestParam(name="dispararmail", required=false) Boolean triggermail,
	    		@RequestParam(name="action", required=true) String action) {
		   System.out.println("llego al controlador de configuracion de Notificacione");
		   System.out.println("SERIAL: "+serial);
		   serial=serial.replace("/", "");
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	System.out.println("Edit profile - busco el usuario: "+ authentication.getName());
	        UserDAO userdao = new UserDAO();
	    	User user = userdao.retrieveByMail(authentication.getName());
	    	user.getNotificaciones().put(Notificacion.CONDICION_ARMADO+"-"+serial, armadocloud);
	    	user.getNotificaciones().put(Notificacion.CONDICION_DISPARADO+"-"+serial, triggercloud);
	    	user.getNotificaciones().put(Notificacion.CONDICION_ARMADO_MAIL+"-"+serial, armadomail);
	    	user.getNotificaciones().put(Notificacion.CONDICION_DISPARADO_MAIL+"-"+serial, triggermail);
	    	userdao.update(user);
	    	System.out.println("Termino de actualizar al usuario");
	    	return "redirect:/home";
	   }

}
