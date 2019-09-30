package com.lgg.nticxs.web.utils;

import java.util.Base64;
import java.util.HashMap;

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
	private DeviceDefaultConfigurationDAO devdefdao = new DeviceDefaultConfigurationDAO();


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
			Device device = new Device();
			device.setSerialnumber(deviceserial);
			device.setName(namedevice);
			device.setDescription(descriptiondevice);
			
			name = request.getUserPrincipal().getName();
			System.out.println("nombre del dueño: "+ name);
			device.setUserowner(Base64.getEncoder().encodeToString(name.getBytes()));
			boolean configuracionExitosa= establecerParametrosDeConeccion(device,tipodevice,iphostescuchar,
					portescuchar,userescuchar,passescuchar,topiclisten, topicwrite,
					iphostescucharremote, portescucharremote,
					userescucharremote,passescucharremote,topiclistenremote, topicwriteremote);
			
			System.out.println("TIPO DE DEVICE: "+tipodevice);
			if(configuracionExitosa){
				System.out.println("la configuracion fue exitosa");
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

	private static DeviceConfiguration establishTopic(DeviceDefaultConfiguration deviceconfiguration, String serialnumber) {
		deviceconfiguration.setTopicescribir(deviceconfiguration.getTopicescribir().replace("serial", serialnumber));
		deviceconfiguration.setTopicescribirremote(deviceconfiguration.getTopicescribirremote().replace("serial", serialnumber));
		deviceconfiguration.setTopicescuchar(deviceconfiguration.getTopicescuchar().replace("serial", serialnumber));
		deviceconfiguration.setTopicescucharremote(deviceconfiguration.getTopicescucharremote().replace("serial", serialnumber));	
		DeviceConfiguration deviceconf = new DeviceConfiguration(deviceconfiguration); 
		
		return deviceconf;
	}
	
	
	

	private static boolean establecerParametrosDeConeccion(Device device, String tipodevice, String iphostescuchar,
			String portescuchar, String userescuchar, String passescuchar, String topiclisten, String topicwrite,
			String iphostescucharremote, String portescucharremote, String userescucharremote,
			String passescucharremote, String topiclistenremote, String topicwriteremote) {
		
		if(esConfiguracionporDefault(tipodevice, iphostescuchar, portescuchar, 
				userescuchar, passescuchar, topiclisten, topicwrite,
				iphostescucharremote, portescucharremote, userescucharremote,
				passescucharremote, topiclistenremote, topicwriteremote)) {
			DeviceDefaultConfiguration deviceConfig= null;
			if(!tipodevice.equals("alarma"))
				deviceConfig=deviceconfigdao.retrieveByName("default");
			else
				deviceConfig=deviceconfigdao.retrieveByName("defaultalarma");
			device.getDeviceconfiguration().add(establishTopic(deviceConfig,deviceserial));
		System.out.println("PASO EL ESTABLECIMIENTO DE LOS LOS VALORES DE CONFIGURACIONS");
		}else{
			DeviceConfiguration dconfirguration  = new DeviceConfiguration();
			dconfirguration.setIphostescuchar(iphostescuchar);
			dconfirguration.setIphostescucharremote(iphostescucharremote);
			dconfirguration.setName("personalized");
			dconfirguration.setPassescuchar(passescuchar);
			dconfirguration.setPassescucharremote(passescucharremote);
			dconfirguration.setPortescuchar(portescuchar);
			dconfirguration.setPortescucharremote(portescucharremote);
			dconfirguration.setTopicescribir(topicwrite);
			dconfirguration.setTopicescribirremote(topicwriteremote);
			dconfirguration.setTopicescuchar(topiclisten);
			dconfirguration.setTopicescucharremote(topiclistenremote);
			dconfirguration.setUserescuchar(userescuchar);
			dconfirguration.setUserescucharremote(userescucharremote);
			dconfirguration.setUsesslescuchar(false);
			dconfirguration.setUsesslescucharremote(false);
			device.getDeviceconfiguration().add(dconfirguration);
		}
		return false;
	}


	private boolean esConfiguracionporDefault(String tipodevice, String iphostescuchar, 
			String portescuchar,
			String userescuchar, String passescuchar, String topiclisten, String topicwrite,
			String iphostescucharremote, String portescucharremote, String userescucharremote,
			String passescucharremote, String topiclistenremote, String topicwriteremote) {
		DeviceDefaultConfiguration dev = null;
		if(tipodevice.equals("alarma")){
			dev= devdefdao.retrieveByName("defaultalarma");
			if(dev.getIphostescuchar().equals(iphostescuchar) &&
				dev.getPortescuchar().equals(portescuchar) &&
				dev.getUserescuchar().equals(userescuchar) &&
				dev.getPassescuchar().equals(passescuchar) &&
				dev.getTopicescuchar())
		}else{
			
		}
			
		return false;
	}

}
