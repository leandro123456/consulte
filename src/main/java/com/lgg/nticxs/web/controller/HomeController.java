package com.lgg.nticxs.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lgg.nticxs.web.utils.Utils;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.DAO.DeviceDAO;

import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.Device;


@Controller
public class HomeController {
	UserDAO userdao = new UserDAO();
	private DeviceDAO devicedao = new DeviceDAO();
	private List<String> deviceAsociadoAlarma = new ArrayList<>();
	private List<String> deviceAsociadoAlarmaV2 = new ArrayList<>();
	private List<String> deviceAsociadoTermomtro = new ArrayList<>();
	private List<String> deviceAsociadoSonoff = new ArrayList<>();
	private List<String> topicosdeAlarma = new ArrayList<>();
	private List<String> deviceAsociadoDoorman = new ArrayList<>();

	
	@RequestMapping("/home")
	public ModelAndView books( HttpServletRequest request, ModelMap model){
		String role= "";
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication==null) {
				return new ModelAndView("login.jsp", model);
			}
			@SuppressWarnings("unchecked")
			Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
		    for (GrantedAuthority grantedAuthority : authorities) {
		    	role=grantedAuthority.getAuthority();
		    	model.addAttribute("role", role);
		    }
	
		if(request == null ){
			System.out.println("SESION NO INICIADA!!");
			return new ModelAndView("login.jsp", model); 
		}
		else{
			if(request.getUserPrincipal()==null){
				return new ModelAndView("login.jsp", model);
			}
		}	
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
        	System.out.println(Arrays.stream(cookies)
                    .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", ")));
        	
        	for (Cookie coo: cookies){
                UserDAO userdao = new UserDAO();
                System.out.println("HOME CONTROLLER - busco el usuario: "+ authentication.getName());
                User user = userdao.retrieveByMail(authentication.getName());
                if (user != null && coo.getName().equals("JSESSIONID")){
                	user.setCookie(coo.getValue());
                	userdao.update(user);
                }else{
                	System.out.println("no se puede guardar la Cookie el usuario es null");
                }
            }
        	System.out.println("creo cookie asociada al usuario");
		}	    
 
		String nombre = request.getUserPrincipal().getName();
		User user = userdao.retrieveByMail(nombre);
		if(user!= null && !user.getCuenta_iniciada()) {
			System.out.println("cuenta no iniciada validarla con el mensaje enviado por mail");
			model.addAttribute("user", user.getFirstname());
			model.addAttribute("useremail", user.getEmail());
			return new ModelAndView("validate.jsp", model);
		}
		user.setFechaUltimoIngrego(Utils.getFechaYHora());
		userdao.update(user);
		clasificarSerialUsuario(user.getDeviceserialnumber());
		model.addAttribute("sonoffcantidad", deviceAsociadoSonoff.size());
		for(int i=0; i<deviceAsociadoSonoff.size();i++) {
			String variable = "sonoffserial"+i;
			model.addAttribute(variable, deviceAsociadoSonoff.get(i));
		}
		List<String> vistas = new ArrayList<>();
		List<String> aux = new  ArrayList<>();
		aux = Utils.vistas(Base64.getEncoder().encodeToString(user.getEmail().getBytes()),deviceAsociadoSonoff,Device.SONOFF);
		for(String vista1 : aux)
			vistas.add(vista1);
		aux =Utils.vistas(Base64.getEncoder().encodeToString(user.getEmail().getBytes()),deviceAsociadoTermomtro, Device.TERMOMETRO);
		for(String vista1 : aux)
			vistas.add(vista1);
		aux =Utils.vistas(Base64.getEncoder().encodeToString(user.getEmail().getBytes()),deviceAsociadoAlarma,Device.ALARMA);
		for(String vista1 : aux)
			vistas.add(vista1);
		aux =Utils.vistas(Base64.getEncoder().encodeToString(user.getEmail().getBytes()),deviceAsociadoAlarmaV2,Device.ALARMAV2);
		for(String vista1 : aux)
			vistas.add(vista1);
		aux = Utils.vistas(Base64.getEncoder().encodeToString(user.getEmail().getBytes()), deviceAsociadoDoorman, Device.DOORMAN);
		for(String vista1: aux) {
			System.out.println("Esta es la llamada del doorman");
			vistas.add(vista1);
		}
		
		System.out.println("cantidad de vistas a mostrar !!!!!: "+ vistas.size());
		model.addAttribute("user", user);
		model.addAttribute("vistas",vistas);
        List<String> topicos=obtenerTopicosDeTodosLosEndpoints(user.getDeviceserialnumber(),"noalarma");
        model.addAttribute("cantidadSensores", topicos.size());
        model.addAttribute("topicos", topicos);
        
        //para las alarmas
        List<String> topicosdeAlarma=obtenerTopicosDeTodosLosEndpoints(user.getDeviceserialnumber(),Device.ALARMA);
        System.out.println("cantidad de topicos a subscribirme alarma: "+ topicosdeAlarma.size());
        model.addAttribute("cantidadAlarma", topicosdeAlarma.size());
        model.addAttribute("hostalarma", "mqtt.coiaca.com");
        model.addAttribute("puertoalarma", "8081");
        model.addAttribute("sslalarma", true);
        model.addAttribute("usuarioalarma", "mqttusr");
        model.addAttribute("passalarma", "mqttpwd");
        model.addAttribute("topicosalarmas", topicosdeAlarma);
       // model.addAttribute("alarmaSerial", obtenerSerialAlarmas(user.getDeviceserialnumber()));
        //model.addAttribute("serialpulsador",obtenerPulsadores());
        model.addAttribute("tieneAlarma", deviceAsociadoAlarma.size()>0);
        
        //fin de alarmas
   		return new ModelAndView("origin.jsp", model);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR en el inicio de la sesion - AFUERA");
			return new ModelAndView("logoutsession.jsp", model);
		}
	}


	
	private Object obtenerPulsadores() {
		List<String> result = new ArrayList<>();
		for (String serial: deviceAsociadoSonoff){
				result.add("'"+serial+"'");
		}
		return result;
	}



	private List<String> obtenerSerialAlarmas(List<String> deviceserialnumber) {
		List<String> result = new ArrayList<>();
		for (String serial: deviceserialnumber){
			Device device = devicedao.retrieveBySerialNumber(serial);
			if(device.getTipo().equals(Device.ALARMA))
				result.add("'"+serial+"'");
		}
		return result;
	}



	@PostMapping("validate")
	public String validateMail(Model model,@RequestParam(name="code") String code,
			@RequestParam(name="user") String user) {
		System.out.println("este es el usuario que busca: " + user);
		System.out.println("este es el codigo que busca: "+ code);
		try {
			System.out.println("entro en el TRY");
			UserDAO userdao = new UserDAO();
		User usuario = userdao.retrieveByMail(user);
		System.out.println("ES IGUAL: "+ usuario.getPassCuenta().equals(code));
		System.out.println("el usuario es null: "+ usuario);
		if(usuario!= null && usuario.getPassCuenta().equals(code)) {
			System.out.println("entro en el if");
			usuario.setCuenta_iniciada(true);
			userdao.update(usuario);
			return "redirect:/home";
		}else {
			System.out.println("entro en el else");
			model.addAttribute("user", user);
			model.addAttribute("msg1", "The entered code is incorrect, try again");
			return "validate.jsp";
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("validate")
		public String validateMailMalo(Model model){
			model.addAttribute("msg1", "The entered code is incorrect, try again");
			return "validate.jsp";
	}
		


	
	private void clasificarSerialUsuario(Map<String, String> deviceserialnumbers) {
		System.out.println("cantidad de elementos: "+ deviceserialnumbers.size());
		deviceAsociadoAlarma = new ArrayList<>();
		deviceAsociadoAlarmaV2 = new ArrayList<>();
		deviceAsociadoSonoff = new ArrayList<>();
		deviceAsociadoTermomtro = new ArrayList<>();
		deviceAsociadoDoorman = new ArrayList<>();
		deviceserialnumbers.forEach((serial,v) -> {
			Device device = devicedao.retrieveBySerialNumber(serial);
			System.out.println("tipo del DEVICE en clasificarUsuario: "+ device.getTipo());
				if(device.getTipo()!=null) {
					if(device.getTipo().equals(Device.ALARMA))
						deviceAsociadoAlarma.add(serial);
					else if(device.getTipo().equals(Device.ALARMAV2))
						deviceAsociadoAlarmaV2.add(serial);
					else if (device.getTipo().equals(Device.SONOFF))
						deviceAsociadoSonoff.add(serial);
					else if (device.getTipo().equals(Device.TERMOMETRO))
						deviceAsociadoTermomtro.add(serial);
					else if (device.getTipo().equals(Device.DOORMAN)) {
						System.out.println("ESTE ES EL SERIAL: "+ serial);
						deviceAsociadoDoorman.add(serial);
					}
					else
						System.out.println("error tipo no reconocido: "+ device.getTipo());
				}else {
					
				}
			});
		}


	private List<String> obtenerTopicosDeTodosLosEndpoints(Map<String,String> devicesnumber, String tipoSolicitado) {
		List<String> topicos = new ArrayList<>(); 
		devicesnumber.forEach((serial,v) -> {
			System.out.println("llego a la carga de los topicos: "+serial);
			Device device=devicedao.retrieveBySerialNumber(serial);
			if(device.getTipo().equals(Device.DOORMAN)) {
				
			}
			else if((tipoSolicitado.equals(Device.ALARMA) || tipoSolicitado.equals(Device.ALARMAV2)) &&
					(device.getTipo().equals(Device.ALARMA) || device.getTipo().equals(Device.ALARMAV2))) {
					if(device.getUsedefaultbrocker()){
						topicos.add("'"+device.getDeviceconfiguration().get(0).getTopicescuchar()+"'");
					}
					else{
						topicos.add("'"+device.getDeviceconfiguration().get(1).getTopicescuchar()+"'");
					}

			}
			else if((!tipoSolicitado.equals(Device.ALARMA) || !tipoSolicitado.equals(Device.ALARMAV2)) && 
					(!device.getTipo().equals(Device.ALARMA) || !device.getTipo().equals(Device.ALARMAV2))) {
					if(device.getUsedefaultbrocker()){
						topicos.add("'"+device.getDeviceconfiguration().get(0).getTopicescuchar()+"'");
						System.out.println("VA A AGREGAR ESTE TOPICO LOCAL: "+ device.getDeviceconfiguration().get(0).getTopicescuchar());
					}
					else{
						topicos.add("'"+device.getDeviceconfiguration().get(1).getTopicescuchar()+"'");
						System.out.println("VA A AGREGAR TOPICO NO LOCAL: " +device.getDeviceconfiguration().get(1).getTopicescuchar());
					}
				}
		});
		
		return topicos;
	}
}
