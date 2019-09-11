package org.junit;

import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.DAO.VistaDAO;
import com.lgg.nticxs.web.DAO.Mongo.MongoDBRemove;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.DeviceDefaultConfigurationDAO;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.Vista;
import com.lgg.nticxs.web.model.simple.SimpleDefaultConfiguration;
import com.lgg.nticxs.web.model.simple.SimpleTimerString;
import com.lgg.nticxs.web.utils.Utils;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceConfiguration;
import com.lgg.nticxs.web.model.DeviceDefaultConfiguration;


public class TestUtilities {
	
	//@Test
	public void testSearchDefaultConfiguration(){
		DeviceDefaultConfigurationDAO deviceconfigdao = new DeviceDefaultConfigurationDAO();
		DeviceDefaultConfiguration deviceConfig= deviceconfigdao.retrieveByName("default");
		if(deviceConfig == null)
			System.out.println("es nulo");
		else
			System.out.println("tiene contenido");
	}
	
	//@Test
	public void testenviarmail() {
		String cabecera = "<HTML><BODY><br/> <br/>";
		String body= "<h1>Muchas Gracias por crear su cuenta </h1> <br/> <h3>Para finalizar el proceso de activacion ingrese el siguiente valor en el inicio de Sesion:</h3> <br/> <h3>"+"11111222121"+"</h3>";
		String pie = "<br/> <br/> <footer><p> 2019 - cDash</p></footer></BODY></HTML>";
		 
		String formulario = String.format("%s%s%s%s", cabecera, body, "<br/> <br/>", pie);		
		Utils.sendMail(formulario, "leandrogguzman@hotmail.com");
		System.out.println("termino");
	}
	
	//@Test
	public void testCreacionderandom() {
		SecureRandom sr = null;
		try {
		    sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
		    int val = sr.nextInt(1134449112);
		    System.out.println(val);
		} catch (NoSuchAlgorithmException e) {
		    e.printStackTrace();
		} catch (NoSuchProviderException e) {
		    e.printStackTrace();
		} 
	}
	
	//@Test
	public void testMakeTimerString(){
		String init ="monday-wednesday-friday&12:45&on&All&@monday-wednesday-friday&23:52&off&All&@";
		
		String[] listtimerstring = init.split("&@");
		System.out.println(listtimerstring.length);
		String timerresult= "";
		for(int i=0; i<listtimerstring.length; i++) {
			System.out.println(listtimerstring[i]);
			String[] timerstring = listtimerstring[i].split("&");
			System.out.println("elemento "+i+" tamano: "+ timerstring.length);
			System.out.println(timerstring[0]);
			System.out.println(timerstring[1]);
			System.out.println(timerstring[2]);
			System.out.println(timerstring[3]);
			
			
			String day = SimpleTimerString.checkDaysRollback(timerstring[0]);
			String hour = timerstring[1].replaceAll(":", "");
			String action = SimpleTimerString.checkAction(timerstring[2]);
			String cualswith = SimpleTimerString.checkSwith(timerstring[3]);
			timerresult=timerresult+day+hour+action+cualswith;
		}
		System.out.println("termino: "+ timerresult);
	}
	
	//@Test
	public void createUser(){
		UserDAO userdao =new UserDAO();
		User user = new User();
		user.setEmail("leandrogabrielguzman@gmail.com");
		user.setFirstname("leo");
		user.setDelete(false);
		List<String> listadeequipos = new ArrayList<>();
		listadeequipos.add("1122122");
		listadeequipos.add("dsdasdsd");
		user.setDeviceserialnumber(listadeequipos);
		user.setLastname("sapo");
		user.setPassCuenta("pass");
		user.setPassword("password".getBytes());
		user.setRole(User.ROLE_ADMIN);
		userdao.create(user);
		System.out.println("termino");
	}
	
	
	@Test
	public void searchDefaultConfig(){
		DeviceDefaultConfigurationDAO deviceconfigdao = new DeviceDefaultConfigurationDAO();
		System.out.println("EMPEZO");
		DeviceDefaultConfiguration elem =deviceconfigdao.retrieveByName("defaultalarma");
		System.out.println("elemento: "+ elem);
		SimpleDefaultConfiguration confi2 = new SimpleDefaultConfiguration(elem);
		SimpleDefaultConfiguration confi = new SimpleDefaultConfiguration(deviceconfigdao.retrieveByName("default"));
		System.out.println("va: "+ confi);
		System.out.println("TERMINO");
	}
	
	//@Test
	public void searchUsuario(){
		UserDAO userdao = new UserDAO();
		//User usuario = userdao.retrieveByMail("leandrogabrielguzman@gmail.com");
		User usuario = userdao.retrieveByMail("leandrogabrielguzman@gmail.com");
		System.out.println("nombre			"+usuario.getFirstname());;
		usuario.setFirstname("leandrogdsadasdasdasdost!!!!!");
		System.out.println("este es el mail: "+ usuario.getEmail());
		System.out.println("este es el ID: "+ usuario.getId());
		userdao.update(usuario);
		System.out.println("termino");
		
	}
	
	//@Test
	public void testSearchVista(){
		DeviceDAO devicedao= new DeviceDAO();
		VistaDAO vistadao= new VistaDAO();

		UserDAO userdao = new UserDAO();
		User user = userdao.retrieveByMail("jaha@gmail.com");
		System.out.println("encontro la papa: "+ user);

		for(String deviceserial : user.getDeviceserialnumber()){
			System.out.println("serialnumber: "+ deviceserial);
			Device device = devicedao.retrieveBySerialNumber(deviceserial);
			String valor = (String) device.getVista().get("jaha@gmail.com");
			System.out.println(valor);
			String[] a = valor.split(";");
			System.out.println(a.length);
			for(int i=0; i<a.length; i++) {
				System.out.println(a[i]);
			}
			Vista vista = vistadao.retrieveByName("sonoff");
			String contenidototal="";
			for(int i=1; i<a.length; i++) {
				String value = vista.getContenido().get(a[i]);
				contenidototal=contenidototal+value;
			}
			String vistatotal = vista.getInicio()+contenidototal+vista.getFin();
			System.out.println(vistatotal);
		}
	}
	

	//@Test
	public void testDelete(){
		try {
			MongoDBRemove.removeSingleDoc("MQTT-Manager", "DEVICE", "serialnumber", "4564dsd");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
//		DeviceDAO devicedao = new DeviceDAO();
//		Device device = devicedao.retrieveBySerialNumber("4564dsd");
//		devicedao.delete(device);
		System.out.println("borro");
	}
	
	//@Test
	public void testMakeDevicesOnUser(){
		try {
			DeviceDAO devicedao = new DeviceDAO();
			Device device = devicedao.retrieveBySerialNumber("111FFFR");
			if(device == null){
				device = new Device();
				device.setDescription("description");
				device.setName("nombre");
				device.setSerialnumber("111FFFR");
				device.setUserowner("t@tes");	
				DeviceConfiguration deviceconfiguration = new DeviceConfiguration();
				deviceconfiguration.setIphostescuchar("ip");
				deviceconfiguration.setName("default");
				deviceconfiguration.setPassescuchar("pass");
				deviceconfiguration.setPortescuchar("port");
				deviceconfiguration.setTopicescribir("topicescribir");
				deviceconfiguration.setTopicescribirremote("topicescribirremote");
				deviceconfiguration.setTopicescuchar("topicescuchar");
				deviceconfiguration.setTopicescucharremote("topicescucharremote");
				deviceconfiguration.setUserescuchar("user");
				deviceconfiguration.setUsesslescuchar(false);
				device.getDeviceconfiguration().add(deviceconfiguration);
				device.getAdmins().add("pepe@test");
				device.getUsers().add("juli@test");
				devicedao.create(device);
				
				//String vista = "<div class=\"col-lg-6 mb-4\"><div class=\"card shadow mb-4\"><div class=\"card-header py-3\"><h6 class=\"m-0 font-weight-bold text-primary\">Sensor de Temperatura Y Humedad</h6></div><div class=\"card-body\"><h4 id=\"humedads\" class=\"small font-weight-bold\"></h4><div class=\"progress mb-4\"><div class=\"progress-bar\" id=\"humedad\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div></div><h4 id=\"temperaturacs\" class=\"small font-weight-bold\">Temperatura °C</h4><div class=\"progress mb-4\"><div id=\"temperaturac\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div><h4 id=\"sensacioncs\" class=\"small font-weight-bold\">Sensacion Termica °C</h4><div class=\"progress mb-4\"><div id=\"sensacionc\" class=\"progress-bar  bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div><h4 id=\"temperaturafs\" class=\"small font-weight-bold\">Temperatura °F</h4><div class=\"progress mb-4\"><div id=\"temperaturaf\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div><h4 id=\"sensacionfs\" class=\"small font-weight-bold\">Sensacion Termica °F</h4><div class=\"progress\"><div id=\"sensacionf\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div></div></div></div>";
				String indicadores2 = "<div class=\"col-lg-6 mb-4\"><div class=\"card shadow mb-4\"><div class=\"card-header py-3\">        <h6 class=\"m-0 font-weight-bold text-primary\">Widget of Status</h6></div><div class=\"card-body\"><form class=\"user\" id=\"connection-information-form\">      <div class=\"form-group row\"><b>Hostname or IP Address</b> 	<input type=\"text\" class=\"form-control form-control-user\" id=\"host\" value=\"gw001.iotek.space\" placeholder=\"Hostname\"></div><div class=\"form-group row\">	<b>Port</b>  	<input type=\"text\" class=\"form-control form-control-user\" id=\"port\" value=\"8883\" placeholder=\"Port\">      </div><div class=\"form-group row\"><b>Topic:</b><input id=\"topic\" type=\"text\" class=\"form-control form-control-user\" name=\"topic\" value=\"WTHUSB000000001/state\" placeholder=\"Topic\"></div><hr><input type=\"button\" class=\"btn btn-primary btn-user btn-block\" onclick=\"startConnect()\" value=\"Connect\"><input type=\"button\" class=\"btn btn-primary btn-user btn-block\" onclick=\"startDisconnect()\" value=\"Disconnect\">    </form><div id=\"messages\"></div></div></div></div>";
				
				
				String vistareloj="temperatura_reloj;Hum;indiceTemp;tempC;sensC;finTemp";
				
				device.getVista().put(device.getUserowner(), vistareloj);
				device.getVista().put("pepe@test", indicadores2);			
				devicedao.update(device);
			}
			
			UserDAO userdao = new UserDAO();
			User user =userdao.retrieveByMail("t@tes");
			if(user != null && !user.getDeviceserialnumber().contains(device.getSerialnumber())){
			user.getDeviceserialnumber().add(device.getSerialnumber());
			userdao.update(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void createVista(){
		VistaDAO vistadao = new VistaDAO();
		Vista vista = new Vista();
		vista.setFin("fin");
		vista.setInicio("inicio");
		vista.setName("elNuevo");
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("uno", "va");
		mapa.put("dos", "dosppa");
		vista.setContenido(mapa);
		vistadao.create(vista);
	}
	
	//@Test
	public void testUpdateVista(){
		DeviceDAO devicedao = new DeviceDAO();
		Device device = devicedao.retrieveBySerialNumber("PS3S1P120190323");
		//String vista = "<div class=\"col-lg-6 mb-4\"><div class=\"card shadow mb-4\"><div class=\"card-header py-3\"><h6 class=\"m-0 font-weight-bold text-primary\">Sensor de Temperatura Y Humedad</h6></div><div class=\"card-body\"><h4 id=\"humedads\" class=\"small font-weight-bold\"></h4><div class=\"progress mb-4\"><div class=\"progress-bar\" id=\"humedad\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div></div><h4 id=\"temperaturacs\" class=\"small font-weight-bold\">Temperatura °C</h4><div class=\"progress mb-4\"><div id=\"temperaturac\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div><h4 id=\"sensacioncs\" class=\"small font-weight-bold\">Sensacion Termica °C</h4><div class=\"progress mb-4\"><div id=\"sensacionc\" class=\"progress-bar  bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div><h4 id=\"temperaturafs\" class=\"small font-weight-bold\">Temperatura °F</h4><div class=\"progress mb-4\"><div id=\"temperaturaf\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div><h4 id=\"sensacionfs\" class=\"small font-weight-bold\">Sensacion Termica °F</h4><div class=\"progress\"><div id=\"sensacionf\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div></div></div></div>";
		String vista="sonoff;sonoffbody";
//		device.getVista().clear();
		device.getVista().put("jaha@gmail.com", vista);
		devicedao.update(device);
		System.out.println("esta vacio? "+ device.getVista().isEmpty());
	}
	
	//@Test
	public void searchVistas(){
		UserDAO userdao = new UserDAO();
		DeviceDAO devicedao = new DeviceDAO();
		User user = userdao.retrieveByMail("jaha@gmail.com");
		Device device = devicedao.retrieveBySerialNumber(user.getDeviceserialnumber().get(0));
//		System.out.println(device.getDescription());
//		System.out.println("esta es la vista: "+ device.getVista().get("t@tes"));
		
		String allenvelope=(String) device.getVista().get("jaha@gmail.com");
		System.out.println("original: "+allenvelope);
		String pattern = "parametername";
		String[] vector = allenvelope.split(pattern);
		System.out.println(vector.length);
		for(int i=1; i<vector.length; i++){
			String json = "{parametername"+vector[i].substring(0, vector[i].length()-1)+"}";
			JSONObject jsonRequest = new JSONObject(json);
			String campo= jsonRequest.getString("parametername");
			System.out.println("valor de "+i+" : "+campo);
		}
		
	}
	
	
	/*				Encrypt of password					*/
//	@Test
//    public void encrypt() throws Exception {
//		String message = "";
//        MessageDigest md = MessageDigest.getInstance("md5");
//        byte[] digestOfPassword = md.digest("ABGELDPGOQWZX"
//                        .getBytes("utf-8"));
//        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
//        for (int j = 0, k = 16; j < 8;) {
//                keyBytes[k++] = keyBytes[j++];
//        }
//
//        SecretKey key = new SecretKeySpec(keyBytes, "DESede");
//        IvParameterSpec iv = new IvParameterSpec(new byte[8]);
//        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
//
//        byte[] plainTextBytes = message.getBytes("utf-8");
//        byte[] cipherText = cipher.doFinal(plainTextBytes);
//        // String encodedCipherText = new sun.misc.BASE64Encoder()
//        // .encode(cipherText);
//
//        System.out.println(Utils.toUnformattedHexArray(cipherText));;
//    }
    
    /*				Decrypt of password					*/
//    @Test
//    public void decrypt() throws Exception {
//    	
//    	byte[] message = "".getBytes();
//        MessageDigest md = MessageDigest.getInstance("md5");
//        byte[] digestOfPassword = md.digest("ABGELDPGOQWZX"
//                        .getBytes("utf-8"));
//        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
//        for (int j = 0, k = 16; j < 8;) {
//                keyBytes[k++] = keyBytes[j++];
//        }
//
//        SecretKey key = new SecretKeySpec(keyBytes, "DESede");
//        IvParameterSpec iv = new IvParameterSpec(new byte[8]);
//        Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
//        decipher.init(Cipher.DECRYPT_MODE, key, iv);
//
//        byte[] plainText = decipher.doFinal(message);
//
//        System.out.println(new String(plainText, "UTF-8"));
//    }
    
    
}
