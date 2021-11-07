package com.lgg.nticxs.web.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.DeviceDefaultConfigurationDAO;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.dbcommands.MongoCommands;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceConfiguration;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.simple.SimpleDefaultConfiguration;
import com.lgg.nticxs.web.model.simple.SimpleDevice;
import com.lgg.nticxs.web.utils.ManagementDevice;
import com.lgg.nticxs.web.utils.Settings;

@Controller
public class DeviceController {
	private UserDAO userdao = new UserDAO();
	private DeviceDAO devicedao = new DeviceDAO();
	private DeviceDefaultConfigurationDAO deviceconfigdao = new DeviceDefaultConfigurationDAO();
	private static final String COLLECTION_DEVICE="Device";
	private static final String URI_BACKEND=Settings.getInstance().getURIBackend();
	
	
	@GetMapping("home/componentmyown")
	public String showMyComponents(HttpServletRequest request,Model model) {
		CargarDevices(model, request);
		return "device_show_my.jsp";
	}
	
	@PostMapping("home/componentmyown")
	public String showMyComponentsPost(HttpServletRequest request,Model model) {
		CargarDevices(model, request);
		return "device_show_my.jsp";
	}
	
	
	@PostMapping("home/remove/{deviceserial}")
	public String removeDevice(Model model, @PathVariable String deviceserial,HttpServletRequest request) {
		System.out.println("llego al remove!!");
		System.out.println("serial: "+ deviceserial);
		CargarDevices(model, request);
		Device device = devicedao.retrieveBySerialNumber(deviceserial);
		String nombredelejecutor = request.getUserPrincipal().getName();
		String propietario= new String(Base64.getDecoder().decode(device.getUserowner()));
		System.out.println("nombre del ejecutor: "+ nombredelejecutor);
		System.out.println("PROPPIETARIO: "+ propietario);
		if(propietario.equals(nombredelejecutor)){
			System.out.println("el ejecutor es el propietario");
		try {
			MongoCommands.Delete(COLLECTION_DEVICE, "serialnumber", deviceserial);
			for(User user: userdao.retrieveAllUsers()) {
				user.getDeviceserialnumber().remove(deviceserial);
				userdao.update(user);
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
				
		return "redirect: /cDash/home/componentmyown";
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
		return "device_more_info.jsp";
	}
	
	
	@GetMapping("home/componentshared")
	public String showComponentsShared(HttpServletRequest request, Model model) {
		String name = request.getUserPrincipal().getName();
		User usuario = userdao.retrieveByMail(name);
		List<SimpleDevice> devices = new ArrayList<>();
		Map<String,String> serials=usuario.getDeviceserialnumber();
		serials.forEach((serial,v) -> {
			Device device = devicedao.retrieveBySerialNumber(serial);
			devices.add(new SimpleDevice(device));
		});
		System.out.println("compartido: "+devices);
		model.addAttribute("devices", devices);
		return "device_show_my.jsp";
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
		return "device_new.jsp";
	}
	
	
	
	
	@PostMapping("home/create")
	public String createDeviceNew(Model model,HttpServletRequest request,
			@RequestParam(name="password", required=true) String password,
			//software
			@RequestParam(name="calle", required=false) String calle,
			@RequestParam(name="numero", required=false) String numero,
			@RequestParam(name="depto", required=false) String depto,
			@RequestParam(name="piso", required=false) String piso,
			@RequestParam(name="localidad", required=false) String localidad,
			@RequestParam(name="codpostal", required=false) String codpostal,
			@RequestParam(name="provincia", required=false) String provincia,
			@RequestParam(name="pais", required=false) String pais,
			@RequestParam(name="tipodireccion", required=false) String tipodireccion,
			
			//hardware
			@RequestParam(name="serialnumber", required=false) String serialnumber,
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
			@RequestParam(name="iphostescuchar", required=false) String iphostescuchar,
			@RequestParam(name="portescuchar", required=false) String portescuchar,
			@RequestParam(name="topiclisten", required=false) String topiclisten,
			@RequestParam(name="userescuchar", required=false) String userescuchar,
			@RequestParam(name="passescuchar", required=false) String passescuchar,
			@RequestParam(name="topicwrite", required=false) String topicwrite,
			@RequestParam(name="iphostescucharremote", required=false) String iphostescucharremote,
			@RequestParam(name="portescucharremote", required=false) String portescucharremote,
			@RequestParam(name="topiclistenremote", required=false) String topiclistenremote,
			@RequestParam(name="userescucharremote", required=false) String userescucharremote,
			@RequestParam(name="passescucharremote", required=false) String passescucharremote,
			@RequestParam(name="topicwriteremote", required=false) String topicwriteremote
			) {
		System.out.println("inicio: "+calle);
		System.out.println(localidad);
		System.out.println(codpostal);
		System.out.println(tipodireccion);
		System.out.println("------------------------------------------------------------------------");
		System.out.println("PARAMETROS DE ENTRADA serialnumber: "+ password);
		System.out.println("PARAMETROS DE ENTRADA serialnumber: "+ serialnumber);
		System.out.println("PARAMETROS DE ENTRADA namedevice: "+ namedevice);
		System.out.println("PARAMETROS DE ENTRADA descriptiondevice: "+ descriptiondevice);
		System.out.println("PARAMETROS DE ENTRADA tipodevice: "+tipodevice);
		System.out.println("PARAMETROS DE ENTRADA timerstringsonoff: "+ timerstringsonoff);
		System.out.println("PARAMETROS DE ENTRADA cantidadswiths: "+ cantidadswiths);
		System.out.println("PARAMETROS DE ENTRADA tipovistatermometro: "+tipovistatermometro);
		System.out.println("PARAMETROS DE ENTRADA humedadtermometro: "+ humedadtermometro);
		System.out.println("PARAMETROS DE ENTRADA tempctermometro: "+ tempctermometro);
		System.out.println("PARAMETROS DE ENTRADA sensacionctermometro: "+ sensacionctermometro);
		System.out.println("PARAMETROS DE ENTRADA tempftermometro: "+ tempftermometro);
		System.out.println("PARAMETROS DE ENTRADA sensacionftermometro: "+ sensacionftermometro);
		System.out.println("PARAMETROS DE ENTRADA iphostescuchar: "+ iphostescuchar);
		System.out.println("PARAMETROS DE ENTRADA portescuchar: "+ portescuchar);
		System.out.println("PARAMETROS DE ENTRADA topiclisten: "+ topiclisten);
		System.out.println("PARAMETROS DE ENTRADA userescuchar: "+ userescuchar);
		System.out.println("PARAMETROS DE ENTRADA passescuchar: "+ passescuchar);
		System.out.println("PARAMETROS DE ENTRADA topicwrite: "+ topicwrite);
		System.out.println("PARAMETROS DE ENTRADA iphostescucharremote: "+ iphostescucharremote);
		System.out.println("PARAMETROS DE ENTRADA portescucharremote: "+ portescucharremote);
		System.out.println("PARAMETROS DE ENTRADA topiclistenremote: "+ topiclistenremote);
		System.out.println("PARAMETROS DE ENTRADA userescucharremote: "+ userescucharremote);
		System.out.println("PARAMETROS DE ENTRADA passescucharremote: "+ passescucharremote);
		System.out.println("PARAMETROS DE ENTRADA topicwriteremote: "+topicwriteremote );
		System.out.println("ES NULL SERIAL: "+ devicedao.retrieveBySerialNumber(serialnumber) ==null);
		
		boolean esDoorman= isDoorman(calle,localidad, tipodireccion);
		System.out.println("----------------- ES DORMAN: "+ esDoorman);
		if(esDoorman) {
			String serialDoorman= crearSerial(calle,numero,depto,piso,localidad, codpostal,provincia);
			ManagementDevice.createDeviceDoorman(request,password,calle,numero,depto,piso,localidad, 
					codpostal,provincia,pais,serialDoorman,tipodireccion);
			model.addAttribute("msg", "Se creo exitosamente el dispositivo");
		}
		if(!esDoorman && devicedao.retrieveBySerialNumber(serialnumber) ==null){
			ManagementDevice.createDevice(
					request,serialnumber, namedevice,password, descriptiondevice, tipodevice, 
					//propoio configuracion
					iphostescuchar, portescuchar, 
					topiclisten, userescuchar,passescuchar,
					topicwrite,	iphostescucharremote, portescucharremote,
					topiclistenremote, userescucharremote,
					passescucharremote,topicwriteremote,
					//propio de la GUI Vistas
					timerstringsonoff, cantidadswiths, tipovistatermometro, humedadtermometro,
					tempctermometro,sensacionctermometro, tempftermometro,sensacionftermometro);
			enviarUpateTobakend(serialnumber);
			model.addAttribute("msg", "Se creo exitosamente el dispositivo");
		}else if (!esDoorman && devicedao.retrieveBySerialNumber(serialnumber) !=null){
			System.out.println("TIPO DE DEVICE NO es NULL: "+tipodevice);
			System.out.println("TIPO DE TERMOMETRO NO es NULL: "+tipovistatermometro);
			ManagementDevice.updateDevice(request,serialnumber,password);
			model.addAttribute("msg", "El dispositivo ya estaba creado, usted fue agregado como usuario Administrador");
		}
		return "redirect: /";
	}


	private String crearSerial(String calle,String numero,String depto, String piso,
			String localidad, String codpostal,String provincia) {
		String result="";
		if(calle!=null && !calle.equals(""))
			result=result+calle;
		if(numero!=null && !numero.equals(""))
			result=result+numero;
		
		//quitar caracteres raros
		result= result.replaceAll(" ", "");
		result = result.replaceAll("\t", "");
		result = result.replaceAll("º", "");
		result = result.replaceAll(",", "");
		result = result.replace(".", "");
		result = result.replace(";", "");
				
		result=result.toLowerCase();
		
		return result;
	}

	private boolean isDoorman(String direccion, String localidad, String codpostal) {
		if(direccion!=null && localidad!=null && codpostal!=null)
			return true;
		return false;
	}

	private void enviarUpateTobakend(String serial) {
		try {
			URL url = new URL(URI_BACKEND+"/nuevodevice/"+serial);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int cod_status = con.getResponseCode();
			String status = con.getResponseMessage();
			System.out.println("RESPUESTA: "+ status+": "+cod_status);
		} catch (Exception e) {
			System.out.println("ERROR en envio de mensaje a backend");
			e.printStackTrace();
		}	
	}

	private void CargarDevices(Model model, HttpServletRequest request) {
		String nombre = request.getUserPrincipal().getName();
		User user = userdao.retrieveByMail(nombre);
		model.addAttribute("user", user);
		String name = request.getUserPrincipal().getName();
		User usuario = userdao.retrieveByMail(name);
		List<SimpleDevice> devices = new ArrayList<>();
		Map<String,String> serials=usuario.getDeviceserialnumber();
		serials.forEach((serial,v) -> {
			Device device = devicedao.retrieveBySerialNumber(serial);
			devices.add(new SimpleDevice(device));
		});
		model.addAttribute("devices", devices);
	}
}
