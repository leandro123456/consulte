package com.lgg.nticxs.web.utils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.DeviceDefaultConfigurationDAO;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceConfiguration;
import com.lgg.nticxs.web.model.DeviceDefaultConfiguration;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.Vista;
import com.lgg.nticxs.web.model.simple.SimpleTimerString;

public class ManagementDevice {

	public static void createDevice(HttpServletRequest request, String deviceserial,
			String namedevice, String descriptiondevice, String tipodevice, 
			String iphostescuchar, String portescuchar, String topiclisten, 
			String userescuchar, String passescuchar, String topicwrite,
			String iphostescucharremote, String portescucharremote, 
			String topiclistenremote, String userescucharremote,
			String passescucharremote, String topicwriteremote,
			String timerstringsonoff, String cantidadswiths,
			String tipovistatermometro, String humedadtermometro, 
			String tempctermometro, String sensacionctermometro,
			String tempftermometro, String sensacionftermometro) {
		
		UserDAO userdao = new UserDAO();
		DeviceDAO devicedao = new DeviceDAO();
		DeviceDefaultConfigurationDAO deviceconfigdao = new DeviceDefaultConfigurationDAO();
		String name = request.getUserPrincipal().getName();
		try {
			System.out.println("tipo de dispositivo: "+ tipodevice);
			Device device = new Device();
			device.setSerialnumber(deviceserial);
			device.setName(namedevice);
			device.setDescription(descriptiondevice);
			name = request.getUserPrincipal().getName();
			System.out.println("nombre del dueño del dispositivo: "+ name);
			device.setUserowner(Base64.getEncoder().encodeToString(name.getBytes()));
			
			//seteo configuracion de topicos
			DeviceConfiguration configuracionConexion= establecerParametrosDeConeccion(device,tipodevice,iphostescuchar,
					portescuchar,userescuchar,passescuchar,topiclisten, topicwrite,
					iphostescucharremote, portescucharremote,
					userescucharremote,passescucharremote,topiclistenremote, topicwriteremote);
			List<DeviceConfiguration> configs = new ArrayList<DeviceConfiguration>();
			configs.add(configuracionConexion);
			device.setDeviceconfiguration(configs);
			
			
			//seteo la vista del dispositivo
			HashMap<String, String> vista =new HashMap<>();
			switch (tipodevice) {
			case "termometro":
				String termometrovista = armarVistaTermometro(tipovistatermometro,humedadtermometro,tempctermometro,
						tempftermometro,sensacionctermometro,sensacionftermometro);
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
				String timerstringvalue="";
				System.out.println("timer-string-recibido: "+ timerstringsonoff);
				if(timerstringsonoff != null && !timerstringsonoff.equals("") && !timerstringsonoff.equals(" "))
					timerstringvalue=SimpleTimerString.maketimerStringFormat(timerstringsonoff);
				device.setTimerString(timerstringvalue);
				String host="";
				String port="";
				String topic="";
				String user="";
				String pass="";
				
				if(device.getDeviceconfiguration().size()==1) {
					DeviceDefaultConfiguration defaultConfig=deviceconfigdao.retrieveByName("default");
					host= defaultConfig.getIphostescucharremote();
					port=defaultConfig.getPortescucharremote();
					topic=defaultConfig.getTopicescribirremote().replace("serial", deviceserial);
					user=defaultConfig.getUserescucharremote();
					pass=defaultConfig.getPassescucharremote();
				}else {
					host= iphostescucharremote;
					port= portescucharremote;
					topic=topiclistenremote;
					user=userescucharremote;
					pass=passescucharremote;
				}
				//no envio el TimerString - lo guardo en base y evaluo desde otro modulo
				//SimpleTimerString.sendtimerString(timerstringvalue,host,port,topic,user,pass);
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
	}


	public static void updateDevice(HttpServletRequest request, String deviceserial) {
		UserDAO userdao = new UserDAO();
		DeviceDAO devicedao = new DeviceDAO();
		//actualizo el usuario en el dispositivo
		Device device = devicedao.retrieveBySerialNumber(deviceserial);
		String administrador = request.getUserPrincipal().getName();
		System.out.println("nombre del administrador: "+ administrador);
		device.getAdmins().add(administrador);
		
		//agrego la misma vista que el dueño en el nuevo usuario
		String vistaprincipal = (String) device.getVista().get(device.getUserowner());
		System.out.println("ESTA ES LA VISTA PRINCIPAL: " +vistaprincipal);
		device.getVista().put(Base64.getEncoder().encodeToString(administrador.getBytes()), vistaprincipal);
		devicedao.update(device);
		//actualizo el usuario
		User user1 = userdao.retrieveByMail(administrador);
		user1.getDeviceserialnumber().add(deviceserial);
		userdao.update(user1);
		System.out.println("Actualizo el dispositivo agregando un nuevo admin");
		System.out.println("El dispositivo, ya existe usted se agrego como administrador, pero no como propietario del producto");
		
	}	
	
	
	private static String armarVistaTermometro(String tipovistatermometro, String humedadtermometro, String tempctermometro,
			String tempftermometro, String sensacionctermometro, String sensacionftermometro) {
		String result=tipovistatermometro;
		if(humedadtermometro!=null && !humedadtermometro.equals(""))
			result=result+";Hum";
		if(tempctermometro!=null && !tempctermometro.equals(""))
			result=result+";tempC";
		if(tempftermometro!=null && !tempftermometro.equals(""))
			result=result+";tempF";
		if(sensacionctermometro!=null && !sensacionctermometro.equals(""))
			result=result+";sensC";
		if(sensacionftermometro!=null && !sensacionftermometro.equals(""))
			result=result+";sensF";
		return result;
	}
	
	

	private static DeviceConfiguration establecerParametrosDeConeccion(Device device, String tipodevice,
			String iphostescuchar,String portescuchar, String userescuchar, 
			String passescuchar, String topiclisten, String topicwrite,
			String iphostescucharremote, String portescucharremote, String userescucharremote,
			String passescucharremote, String topiclistenremote, String topicwriteremote) {
		DeviceDefaultConfigurationDAO devdefdao = new DeviceDefaultConfigurationDAO();
		DeviceConfiguration dconfirguration  = new DeviceConfiguration();
		DeviceDefaultConfiguration deviceConfig= null;
		if(!tipodevice.equals("alarma"))
			deviceConfig=devdefdao.retrieveByName("default");
		else 
			deviceConfig=devdefdao.retrieveByName("defaultalarma");
		
		if(userescuchar.equals("") &&  userescucharremote.equals("") && iphostescuchar.equals("")
				&& topiclisten.equals("") && topicwrite.equals("")) {
			System.out.println("------ Va ausar confirguracion por default");
			dconfirguration.setName("default");
			dconfirguration.setUserescuchar(deviceConfig.getUserescuchar());
			dconfirguration.setPassescuchar(deviceConfig.getPassescuchar());
			dconfirguration.setUserescucharremote(deviceConfig.getUserescucharremote());
			dconfirguration.setPassescucharremote(deviceConfig.getPassescucharremote());
			dconfirguration.setIphostescuchar(deviceConfig.getIphostescuchar());
			dconfirguration.setIphostescucharremote(deviceConfig.getIphostescucharremote());				
			dconfirguration.setPortescuchar(deviceConfig.getPortescuchar());
			dconfirguration.setPortescucharremote(deviceConfig.getPortescucharremote());
			dconfirguration.setTopicescribir(deviceConfig.getTopicescribir().replace("serial", device.getSerialnumber()));
			dconfirguration.setTopicescribirremote(deviceConfig.getTopicescribirremote().replace("serial", device.getSerialnumber()));
			dconfirguration.setTopicescuchar(deviceConfig.getTopicescuchar().replace("serial", device.getSerialnumber()));
			dconfirguration.setTopicescucharremote(deviceConfig.getTopicescribirremote().replace("serial", device.getSerialnumber()));
			dconfirguration.setUsesslescuchar(deviceConfig.getUsesslescuchar());
			dconfirguration.setUsesslescucharremote(deviceConfig.getUsesslescucharremote());
			return dconfirguration;
		}
		
		if(deviceConfig.getUserescuchar().equals(userescuchar)) {
			dconfirguration.setPassescuchar(deviceConfig.getPassescuchar());
			dconfirguration.setName("default");
		}else {
			dconfirguration.setPassescuchar(passescuchar);
			dconfirguration.setName("personalized");
		}if(deviceConfig.getUserescucharremote().equals(userescucharremote)) {
			dconfirguration.setPassescucharremote(deviceConfig.getPassescucharremote());
			dconfirguration.setName("default");
		}else {
			dconfirguration.setPassescucharremote(passescucharremote);
			dconfirguration.setName("personalized");
		}
		dconfirguration.setIphostescuchar(iphostescuchar);
		dconfirguration.setIphostescucharremote(iphostescucharremote);				
		dconfirguration.setPortescuchar(portescuchar);
		dconfirguration.setPortescucharremote(portescucharremote);
		dconfirguration.setTopicescribir(topicwrite.replace("serial", device.getSerialnumber()));
		dconfirguration.setTopicescribirremote(topicwriteremote.replace("serial", device.getSerialnumber()));
		dconfirguration.setTopicescuchar(topiclisten.replace("serial", device.getSerialnumber()));
		dconfirguration.setTopicescucharremote(topiclistenremote.replace("serial", device.getSerialnumber()));
		dconfirguration.setUserescuchar(userescuchar);
		dconfirguration.setUserescucharremote(userescucharremote);
		dconfirguration.setUsesslescuchar(false);
		dconfirguration.setUsesslescucharremote(false);
		System.out.println("PASO EL ESTABLECIMIENTO DE LOS LOS VALORES DE CONFIGURACIONS");

		return dconfirguration;
	}


}
