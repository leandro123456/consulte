package com.lgg.nticxs.web.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.DeviceDefaultConfigurationDAO;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.dbcommands.MongoCommands;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceConfiguration;
import com.lgg.nticxs.web.model.DeviceDefaultConfiguration;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.Vista;
import com.lgg.nticxs.web.model.simple.SimpleDefaultConfiguration;
import com.lgg.nticxs.web.model.simple.SimpleDevice;
import com.lgg.nticxs.web.model.simple.SimpleTimerString;

@Controller
public class DeviceController {
	private UserDAO userdao = new UserDAO();
	private DeviceDAO devicedao = new DeviceDAO();
	private DeviceDefaultConfigurationDAO deviceconfigdao = new DeviceDefaultConfigurationDAO();
	private static final String COLLECTION_DEVICE="DEVICE";
	
	
	@GetMapping("home/componentmyown")
	public String showMyComponents(HttpServletRequest request,Model model) {
		CargarDevices(model, request);
		return "device_show_my";
	}
	
	@PostMapping("home/componentmyown")
	public String showMyComponentsPost(HttpServletRequest request,Model model) {
		CargarDevices(model, request);
		return "device_show_my";
	}
	
	

	@PostMapping("home/remove/{deviceserial}")
	public String removeDevice(Model model, @PathVariable String deviceserial,HttpServletRequest request) {
		System.out.println("llego al remove!!");
		System.out.println("serial: "+ deviceserial);
		CargarDevices(model, request);
		Device device = devicedao.retrieveBySerialNumber(deviceserial);
		String nombredelejecutor = request.getUserPrincipal().getName();
		if(device.getUserowner().equals(nombredelejecutor)){
		try {
			MongoCommands.Delete(COLLECTION_DEVICE, "serialnumber", deviceserial);
			for(User user: userdao.retrieveAllUsers()) {
				int index = user.getDeviceserialnumber().indexOf(deviceserial);
				if (index != -1) {
					user.getDeviceserialnumber().remove(index);
					userdao.update(user);
				}
			}
			model.addAttribute("msg", "Borrado exitoso");
		} catch (Exception e) {
			model.addAttribute("msg1", "Error durante el proceso de borrado: "+ e.getMessage());
		}}else{
			//borro la vista asociada al usuario y el nombre del dispositivo
			device = devicedao.retrieveBySerialNumber(deviceserial);
			device.getAdmins().remove(nombredelejecutor);
			device.getVista().remove(nombredelejecutor);
			devicedao.update(device);
			//Actualizo el usuario quitandole el dispositivo
			User userComun = userdao.retrieveByMail(nombredelejecutor);
			userComun.getDeviceserialnumber().remove(deviceserial);
			userdao.update(userComun);
			model.addAttribute("msg", "Como usted no es el dueño del dispositivo, unicamente se ha quitado su relacion con el mismo");
		}
				
		return "device_show_my";
	}
	
	@GetMapping("home/info/{deviceserial}")
	public String moreInfoDevice(Model model, @PathVariable String deviceserial, HttpServletRequest request) {
		Device device= devicedao.retrieveBySerialNumber(deviceserial);
		DeviceConfiguration configuration = null;
		if(device.getUsedefaultbrocker())
			configuration= device.getDeviceconfiguration().get(0);
		else
			configuration= device.getDeviceconfiguration().get(1);
		List<String> admins = device.getAdmins();
		List<String> users = device.getUsers();
		System.out.println("llego aca info");
		model.addAttribute("configuration", configuration);
		model.addAttribute("admins", admins);
		model.addAttribute("users", users);
		model.addAttribute("device", device);

		//informacion de usuario
		String nombre = request.getUserPrincipal().getName();
		User user = userdao.retrieveByMail(nombre);
		model.addAttribute("user", user);
		System.out.println("id del usuario: "+ user.getId());
		System.out.println("device: "+ device.getSerialnumber());
		return "device_more_info";
	}
	
	
	@GetMapping("home/componentshared")
	public String showComponentsShared(HttpServletRequest request, Model model) {
		String name = request.getUserPrincipal().getName();
		User usuario = userdao.retrieveByMail(name);
		List<SimpleDevice> devices = new ArrayList<>();
		for(String namedevice : usuario.getDeviceserialnumber()) {
			Device device = devicedao.retrieveBySerialNumber(namedevice);
			devices.add(new SimpleDevice(device));
		}
		System.out.println("compartido: "+devices);
		model.addAttribute("devices", devices);
		return "device_show_my";
	}
	
	@GetMapping("home/newdevice")
	public String newDevice(HttpServletRequest request, Model model) {
		String nombre = request.getUserPrincipal().getName();
		User user = userdao.retrieveByMail(nombre);
		model.addAttribute("users", user);
		//hoy tengo solo dos configuraciones por default
		
		SimpleDefaultConfiguration confi2 = new SimpleDefaultConfiguration(deviceconfigdao.retrieveByName("defaultalarma"));
		SimpleDefaultConfiguration confi = new SimpleDefaultConfiguration(deviceconfigdao.retrieveByName("default"));
		
		System.out.println("configuracion default: "+confi.getName());
		System.out.println("configuacion defautl alarma: "+ confi2.getName());
		//model.addAttribute("configdeflocal", confi);
		//model.addAttribute("configdef", confi2);
		return "device_new";
	}
	
	
	@PostMapping("home/create/{deviceserial}")
	public String createDevice(Model model, @PathVariable String deviceserial,HttpServletRequest request,			
			@RequestParam(name="serialnumber", required=true) String serialnumber,
			@RequestParam(name="namedevice", required=false) String namedevice,
			@RequestParam(name="descriptiondevice", required=false) String descriptiondevice,
			@RequestParam(name="tipodevice", required=false) String tipodevice,
			
			//vista sonoff
			@RequestParam(name="timerstringsonoff", required=false) String timerstringsonoff,
			@RequestParam(name="cantidadswiths", required=false) String cantidadswiths,
			
			//termometro
			@RequestParam(name="tipovistatermometro", required=false) String tipovistatermometro,
			@RequestParam(name="humedadtermometro", required=false) String humedadtermometro,
			@RequestParam(name="tempctermometro", required=false) String tempctermometro,
			@RequestParam(name="sensacionctermometro", required=false) String sensacionctermometro,
			@RequestParam(name="tempftermometro", required=false) String tempftermometro,
			@RequestParam(name="sensacionftermometro", required=false) String sensacionftermometro,
			
			//configuracion de topicos
			@RequestParam(name="defaultconfiguration", required=true) Boolean defaultconfiguration,
			@RequestParam(name="iphostescuchar", required=false) String iphostescuchar,
			@RequestParam(name="portescuchar", required=false) String portescuchar,
			@RequestParam(name="topiclisten", required=false) String topiclisten,
			@RequestParam(name="userescuchar", required=false) String userescuchar,
			@RequestParam(name="passescuchar", required=false) String passescuchar,
			@RequestParam(name="iphostescribir", required=false) String iphostescribir,
			@RequestParam(name="portescribir", required=false) String portescribir,
			@RequestParam(name="topicwrite", required=false) String topicwrite,
			@RequestParam(name="userescribir", required=false) String userescribir,
			@RequestParam(name="passescribir", required=false) String passescribir,
			@RequestParam(name="iphostescucharremote", required=false) String iphostescucharremote,
			@RequestParam(name="portescucharremote", required=false) String portescucharremote,
			@RequestParam(name="topiclistenremote", required=false) String topiclistenremote,
			@RequestParam(name="userescucharremote", required=false) String userescucharremote,
			@RequestParam(name="passescucharremote", required=false) String passescucharremote,
			@RequestParam(name="iphostescribirremote", required=false) String iphostescribirremote,
			@RequestParam(name="portescribirremote", required=false) String portescribirremote,
			@RequestParam(name="topicwriteremote", required=false) String topicwriteremote,
			@RequestParam(name="userescribirremote", required=false) String userescribirremote,
			@RequestParam(name="passescribirremote", required=false) String passescribirremote
			) {
		if(devicedao.retrieveBySerialNumber(deviceserial) ==null){
			String name = request.getUserPrincipal().getName();
			try {
				Device device = new Device();
				device.setSerialnumber(deviceserial);
				device.setName(namedevice);
				device.setDescription(descriptiondevice);
				
				name = request.getUserPrincipal().getName();
				System.out.println("nombre del dueño: "+ name);
				device.setUserowner(Base64.getEncoder().encodeToString(name.getBytes()));
				System.out.println("tiene configuracion por defaault: "+defaultconfiguration);
				if(defaultconfiguration) {
					DeviceDefaultConfiguration deviceConfig= null;
					if(!tipodevice.equals("alarma"))
						deviceConfig=deviceconfigdao.retrieveByName("default");
					else
						deviceConfig=deviceconfigdao.retrieveByName("defaultalarma");
					device.getDeviceconfiguration().add(establishTopic(deviceConfig,serialnumber));
				System.out.println("PASO EL ESTABLECIMIENTO DE LOS LOS VALORES DE CONFIGURACIONS");
				}else{
					DeviceConfiguration dconfirguration  = new DeviceConfiguration();
					dconfirguration.setIphostescribir(iphostescribir);
					dconfirguration.setIphostescribirremote(iphostescribirremote);
					dconfirguration.setIphostescuchar(iphostescuchar);
					dconfirguration.setIphostescucharremote(iphostescucharremote);
					dconfirguration.setName("personalized");
					dconfirguration.setPassescribir(passescribir);
					dconfirguration.setPassescribirremote(passescribirremote);
					dconfirguration.setPassescuchar(passescuchar);
					dconfirguration.setPassescucharremote(passescucharremote);
					dconfirguration.setPortescribir(portescribir);
					dconfirguration.setPortescribirremote(portescribirremote);
					dconfirguration.setPortescuchar(portescuchar);
					dconfirguration.setPortescucharremote(portescucharremote);
					dconfirguration.setTopicescribir(topicwrite);
					dconfirguration.setTopicescribirremote(topicwriteremote);
					dconfirguration.setTopicescuchar(topiclisten);
					dconfirguration.setTopicescucharremote(topiclistenremote);
					dconfirguration.setUserescribir(userescribir);
					dconfirguration.setUserescribirremote(userescribirremote);
					dconfirguration.setUserescuchar(userescuchar);
					dconfirguration.setUserescucharremote(userescucharremote);
					dconfirguration.setUsesslescribir(false);
					dconfirguration.setUsesslescribirremote(false);
					dconfirguration.setUsesslescuchar(false);
					dconfirguration.setUsesslescucharremote(false);
					device.getDeviceconfiguration().add(dconfirguration);
				}
				String timerstringvalue="";
				System.out.println("tipo de dispositivo: "+ tipodevice);
				HashMap<String, String> vista =new HashMap<>();
				switch (tipodevice) {
				case "termometro":
					String termometrovista = armarVistaTermometro(tipovistatermometro,humedadtermometro,tempctermometro,tempftermometro,sensacionctermometro,sensacionftermometro);
					System.out.println("termometro vista: "+ termometrovista);
					vista.put(Base64.getEncoder().encodeToString(name.getBytes()), termometrovista);
					device.setVista(vista);
					break;
				case "alarma":
					String alarmavista = Vista.ALARMA+";alarmabody";
					vista.put(Base64.getEncoder().encodeToString(name.getBytes()), alarmavista);
					device.setVista(vista);
					System.out.println("es una alarma");
					break;
				case "sonoff":
					System.out.println("timer-string-recibido: "+ timerstringsonoff);
					if(timerstringsonoff != null && !timerstringsonoff.equals("") && !timerstringsonoff.equals(" "))
						timerstringvalue=SimpleTimerString.maketimerStringFormat(timerstringsonoff);
					device.setTimerString(timerstringvalue);
					String host="";
					String port="";
					String topic="";
					String user="";
					String pass="";
					if(defaultconfiguration) {
						DeviceDefaultConfiguration defaultConfig=deviceconfigdao.retrieveByName("default");
						host= defaultConfig.getIphostescribirremote();
						port=defaultConfig.getPortescribirremote();
						topic=defaultConfig.getTopicescribirremote().replace("serial", serialnumber);
						user=defaultConfig.getUserescribirremote();
						pass=defaultConfig.getPassescribirremote();
					}else {
						host= iphostescribirremote;
						port= portescribirremote;
						topic=topiclistenremote;
						user=userescribirremote;
						pass=passescribirremote;
					}
					SimpleTimerString.sendtimerString(timerstringvalue,host,port,topic,user,pass);
					//vista sonoff
					String vistasonoff ="";
					System.out.println("cantidad de sonoff recibido: " + cantidadswiths);
					if(cantidadswiths.equals("one"))
						vistasonoff= Vista.SONOFF+";sonoffbody";
					else if(cantidadswiths.equals("two"))
						vistasonoff= Vista.SONOFF_DOS+";sonoffbody";
					vista.put(Base64.getEncoder().encodeToString(name.getBytes()), vistasonoff);
					device.setVista(vista);
					break;
				default:
					System.out.println("no encontro el tipo de device: "+ tipodevice);
					break;
				}
				device.setTipo(tipodevice);
				System.out.println("termino de setar todo, ahora lo guarda: *****************");
				System.out.println("a ver si muestra que onda el device "+device.toString());
				devicedao.create(device);
				System.out.println("YA CREO EL DECICE -**************************************");
			} catch (Exception e) {
				System.out.println("fallo, nose que onda: "+ e.getMessage());
				e.printStackTrace();
				
			}
			
			User user = userdao.retrieveByMail(name);
			user.getDeviceserialnumber().add(deviceserial);
			userdao.update(user);
			
		}else{
			//model.addAttribute("msg1", "This device / serial is already registered ... please check it and try again");
			//return "device_new";
			
			//actualizo el usuario en el dispositivo
			Device device = devicedao.retrieveBySerialNumber(deviceserial);
			String administrador = request.getUserPrincipal().getName();
			System.out.println("nombre del administrador: "+ administrador);
			device.getAdmins().add(administrador);
			
			//agrego la misma vista que el dueño en el nuevo usuario
			String vistaprincipal = (String) device.getVista().get(device.getUserowner());
			System.out.println("555555555555555555555 ESTA ES LA VISTA PRINCIPAL: " +vistaprincipal);
			device.getVista().put(Base64.getEncoder().encodeToString(administrador.getBytes()), vistaprincipal);
			devicedao.update(device);
			//actualizo el usuario
			User user1 = userdao.retrieveByMail(administrador);
			user1.getDeviceserialnumber().add(deviceserial);
			userdao.update(user1);
			System.out.println("Actualizo el dispositivo agregando un nuevo admin");
			System.out.println("El dispositivo, ya existe usted se agrego como administrador, pero no como propietario del producto");

		}
		return "redirect:/home";
	}
	

	private String armarVistaTermometro(String tipovistatermometro, String humedadtermometro, String tempctermometro,
			String tempftermometro, String sensacionctermometro, String sensacionftermometro) {
		String result=tipovistatermometro;
		if(!humedadtermometro.equals(""))
			result=result+";Hum";
		if(!tempctermometro.equals(""))
			result=result+";tempC";
		if(!tempftermometro.equals(""))
			result=result+";tempF";
		if(!sensacionctermometro.equals(""))
			result=result+";sensC";
		if(!sensacionftermometro.equals(""))
			result=result+";sensF";
		return result;
	}

	private DeviceConfiguration establishTopic(DeviceDefaultConfiguration deviceconfiguration, String serialnumber) {
//		System.out.println("se setearon los valores de topicos con el serial number: "+ serialnumber);
//		System.out.println("inicial: "+deviceconfiguration.getTopicescribir());
//		System.out.println("inicial: "+deviceconfiguration.getTopicescribirremote());
//		System.out.println("inicial: "+deviceconfiguration.getTopicescuchar());
//		System.out.println("inicial: "+deviceconfiguration.getTopicescucharremote());
		deviceconfiguration.setTopicescribir(deviceconfiguration.getTopicescribir().replace("serial", serialnumber));
		deviceconfiguration.setTopicescribirremote(deviceconfiguration.getTopicescribirremote().replace("serial", serialnumber));
		deviceconfiguration.setTopicescuchar(deviceconfiguration.getTopicescuchar().replace("serial", serialnumber));
		deviceconfiguration.setTopicescucharremote(deviceconfiguration.getTopicescucharremote().replace("serial", serialnumber));	
//		System.out.println("valores de los topicos: "+ deviceconfiguration.getTopicescribir());
//		System.out.println("valores de los topicos: "+ deviceconfiguration.getTopicescribirremote());
//		System.out.println("valores de los topicos: "+ deviceconfiguration.getTopicescuchar());
//		System.out.println("valores de los topicos: "+ deviceconfiguration.getTopicescucharremote());
		DeviceConfiguration deviceconf = new DeviceConfiguration(deviceconfiguration); 
		
		return deviceconf;
	}

	private void CargarDevices(Model model, HttpServletRequest request) {
		String nombre = request.getUserPrincipal().getName();
		User user = userdao.retrieveByMail(nombre);
		model.addAttribute("user", user);
		String name = request.getUserPrincipal().getName();
		User usuario = userdao.retrieveByMail(name);
		List<SimpleDevice> devices = new ArrayList<>();
		for(String namedevice : usuario.getDeviceserialnumber()) {
			Device device = devicedao.retrieveBySerialNumber(namedevice);
			devices.add(new SimpleDevice(device));
		}
		model.addAttribute("devices", devices);
	}

}
