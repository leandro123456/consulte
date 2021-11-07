package com.lgg.nticxs.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.DeviceNotification;
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
	    	model.addAttribute("signalwifi", user.getNotificaciones().get(Notificacion.CONDICION_BAJASIGNALWIFI+"-"+serial));
	    	model.addAttribute("signalwifimail", user.getNotificaciones().get(Notificacion.CONDICION_BAJASIGNALWIFI_MAIL+"-"+serial));
	    	model.addAttribute("esperanotifwifi", esperaWifiNotificacion(user,serial));
	    	model.addAttribute("serial", serial);
	    	return "user_notications_alarma.jsp";
	    }
	   
	   private String esperaWifiNotificacion(User user, String serial) {
		   if(user.getNotificacionSignalWifi()==null) {
				return "0";
	    	}
			else {
				for(DeviceNotification notificacion: user.getNotificacionSignalWifi()) {
					if(notificacion.getName()!=null && notificacion.getName().equals(serial)) {
						return notificacion.getContent();
					}				
				}
				return "0";
			}	
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
	   
		@GetMapping(value = "home/notificacionesalarma/{deviceid}/actualizarhoranotificacionwifi/{horaRegistrada}")
		@ResponseBody
		public String actualizarNotificacionEnUnDispositivo(@PathVariable String deviceid,
				@PathVariable String horaRegistrada) {
			System.out.println("device: "+ deviceid);
			System.out.println("Notificacion a actualizar: "+ horaRegistrada);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	System.out.println("Edit profile - busco el usuario: "+ authentication.getName());
	        UserDAO userdao = new UserDAO();
	    	User user = userdao.retrieveByMail(authentication.getName());
	    	DeviceNotification notif= new DeviceNotification();
	    	notif.setName(deviceid);
	    	notif.setContent(horaRegistrada);
	    	setearNotificacionWifiHora(user, deviceid, horaRegistrada);
	    	userdao.update(user);
	    	System.out.println("Termino de actualizar al usuario wifi");
			return "ok";
		}

		
		private void setearNotificacionWifiHora(User user, String deviceId, String horaRegistrada) {
			UserDAO userdao = new UserDAO();
			if(user.getNotificacionSignalWifi()==null) {
	    		user.setNotificacionSignalWifi(new ArrayList<DeviceNotification>());
	    		DeviceNotification notif = new DeviceNotification();
				notif.setContent(horaRegistrada);
				notif.setName(deviceId);
				user.getNotificacionSignalWifi().add(notif);
				userdao.update(user);
				return;
	    	}
			else {
				boolean exiteValor = false;
				for(DeviceNotification notificacion: user.getNotificacionSignalWifi()) {
					if(notificacion.getName()!=null && notificacion.getName().equals(deviceId)) {
						notificacion.setContent(horaRegistrada);
						userdao.update(user);
						return;
					}				
				}
				if(!exiteValor) {
					DeviceNotification notif = new DeviceNotification();
					notif.setContent(horaRegistrada);
					notif.setName(deviceId);
					user.getNotificacionSignalWifi().add(notif);
					userdao.update(user);
				}
			}			
		}

		@GetMapping(value="home/notificacionesalarma/{deviceid}/{notactualizar}/{status}")
		@ResponseBody
		public String actualizarNotificacionWifi(@PathVariable String deviceid,
				@PathVariable String notactualizar,@PathVariable String status) {
			System.out.println("device: "+ deviceid);
			System.out.println("Notificacion a actualizar: "+ notactualizar);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	System.out.println("Edit profile - busco el usuario: "+ authentication.getName());
	        UserDAO userdao = new UserDAO();
	    	User user = userdao.retrieveByMail(authentication.getName());
	    	user.getNotificaciones().put(notactualizar+"-"+deviceid, Boolean.parseBoolean(status));
	    	userdao.update(user);
	    	System.out.println("Termino de actualizar al usuario");
			return "ok";
		}
		
		
		
	   @GetMapping("home/configuracionalarma/{serial}")
	    public String editProfileConfigurations(Model model,@PathVariable String serial) {
	    	System.out.println("llego al controlador de Configuraciones");
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	System.out.println("Edit profile - busco el usuario: "+ authentication.getName());
	        UserDAO userdao = new UserDAO();
	    	User user = userdao.retrieveByMail(authentication.getName());
	    	model.addAttribute("user", user);
	    	model.addAttribute("serial", serial);
	    	return "user_configurations_alarma.jsp";
	    }
}
