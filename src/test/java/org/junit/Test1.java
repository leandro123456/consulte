package org.junit;

import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.DAO.VistaDAO;
import com.lgg.nticxs.web.DAO.helper.MongoDBRemove;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.DeviceDefaultConfigurationDAO;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.Vista;
import com.lgg.nticxs.web.model.simple.SimpleTimerString;
import com.lgg.nticxs.web.model.Ciclolectivo;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceConfiguration;
import com.lgg.nticxs.web.model.DeviceDefaultConfiguration;
import com.lgg.nticxs.web.model.Materia;
import com.lgg.nticxs.web.model.Materia.materia;

public class Test1 {
	
	
	//@Test
	public void testSendMQTT() {
		String timerstringsonoff= "monday-wednesday-friday&12:45&on&All&@monday-wednesday-friday&23:52&off&All&@";
		//String timerstringvalue=SimpleTimerString.maketimerStringFormat(timerstringsonoff);
		String host= "mqtt.coiaca.com";
		String port= "1883";
		//RConfig/
		String topic="PS3S1P120190323/swcmd";
		String user="mqttusr";
		String pass="mqttpwd";
//		String message ="\"pwd\":\"mqttpwd\", \"command\":\"switchAction\",\"SW1\":\"ON\"";
		String message ="{'pwd':'mqttpwd', 'command':'switchAction','param1':'SW1','param2':'turnOn'}";
//		String message = "{\"pwd\":\"mqttpwd\", \"command\":\"simulateButtonPush\",\"param1\":\"BTN1\"}";
//		String message = "\"pwd\":\"mqttpwd\", \"command\":\"switchAction\",\"param1\":\"SW1\",\"param2\":\"turnOn\"";
//		String message = "{\"pwd\":\"mqttpwd\", \"command\":\"getStatus\"}";
		
	   	 JSONObject json = new JSONObject();
	   	 json.put("pwd", "sapo");
	   	 json.put("command", "switchAction");
//	   	 json.put("param1", "SW1");
//	   	 json.put("param2", "ON");
	   	 json.put("SW1", "OFF");
	   	 System.out.println("el json: "+ json);
		SimpleTimerString.sendmessageMQTT(json,host,port,topic,user,pass);
		System.out.println("termino");
		
		//"deviceId":"PS3S1P120190323","SW1":"OFF","PB1LS":1,"PB1TTO":0,"TS":0
		
		//serial: PS3S1P120190323
		//server: mqtt.coiaca.com
		//port: 1883
		//user: mqttusr
		//pass: mqttpwd
		//coiaca: Coiaca#serial
		//envio de comandos:  #serial/swcmd
		//envio de status: #serial/state
		
		//configuracion remota
		//server: mqtttest.qliq.com.ar
		//port:8883
		//user: mqttusr
		//pass: mqttpwd
		//envio de commandos: RConfig/#serial
		//recepcion de informacion: RConfig/#serial/result
		
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
	public void testCrearDeviceDefaultConfiguration(){
		DeviceDefaultConfigurationDAO dedao = new DeviceDefaultConfigurationDAO();
		DeviceDefaultConfiguration deviceconfiguration = new DeviceDefaultConfiguration();
		deviceconfiguration.setName("default");
//		deviceconfiguration.setIphostescuchar("gw001.iotek.space");
//		deviceconfiguration.setPortescuchar("8080");
//		deviceconfiguration.setTopicescuchar("serial/state");
//		deviceconfiguration.setPassescuchar("Sanbenit0");
//		deviceconfiguration.setUserescuchar("movasim");
//		deviceconfiguration.setUsesslescuchar(false);
		deviceconfiguration.setIphostescuchar("gw001.iotek.space");
		deviceconfiguration.setPortescuchar("8080");
		deviceconfiguration.setTopicescuchar("serial/state");
		deviceconfiguration.setPassescuchar("Sanbenit0");
		deviceconfiguration.setUserescuchar("movasim");
		deviceconfiguration.setUsesslescuchar(false);
		
		deviceconfiguration.setIphostescribir("");
		deviceconfiguration.setPortescribir("");
		deviceconfiguration.setTopicescribir("serial/swcmd");
		deviceconfiguration.setPassescribir("");
		deviceconfiguration.setUserescribir("");
		
//		deviceconfiguration.setIphostescucharremote("mqtt.coiaca.com");
//		deviceconfiguration.setPortescucharremote("8080");
//		deviceconfiguration.setTopicescucharremote("RConfig/debug");
//		deviceconfiguration.setUserescucharremote("cleocinc");
//		deviceconfiguration.setPassescucharremote("leandro1");
		
		deviceconfiguration.setIphostescucharremote("mqtttest.qliq.com.ar");
		deviceconfiguration.setPortescucharremote("8883");
		deviceconfiguration.setTopicescucharremote("RConfig/serial/result");
		deviceconfiguration.setUserescucharremote("mqttusr");
		deviceconfiguration.setPassescucharremote("mqttpwd");
		
		deviceconfiguration.setIphostescribirremote("mqtttest.qliq.com.ar");
		deviceconfiguration.setPortescribirremote("8883");
		deviceconfiguration.setTopicescribirremote("RConfig/serial");
		deviceconfiguration.setUserescribirremote("mqttusr");
		deviceconfiguration.setPassescribirremote("mqttpwd");
		
		dedao.create(deviceconfiguration);
		System.out.println("termino");
		//serial: PS3S1P120190323
		//user: mqttusr
		//pass: mqttpwd
		//coiaca: Coiaca#serial
		//envio de comandos:  #serial/swcmd
		//envio de status: #serial/state
		
		//configuracion remota
		//server: mqtttest.qliq.com.ar
		//port:8883
		//user: mqttusr
		//pass: mqttpwd
		//envio de commandos: RConfig/#serial
		//recepcion de informacion: RConfig/#serial/result
		
	}
	
	@Test
	public void testSearchVista(){
		DeviceDAO devicedao= new DeviceDAO();
		VistaDAO vistadao= new VistaDAO();

		UserDAO userdao = new UserDAO();
		User user = userdao.retrieveByMail("t@tes");

		for(String deviceserial : user.getDeviceserialnumber()){
			System.out.println("serialnumber: "+ deviceserial);
			Device device = devicedao.retrieveBySerialNumber(deviceserial);
			String valor = device.getVista().get("t@tes");
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
	public void testMakeAllViews() {
		String inicio= "<div class=\"col-lg-6 mb-4\"><div class=\"card shadow mb-4\"><div class=\"card-header py-3\"><h6 class=\"m-0 font-weight-bold text-primary\">Sensor de Temperatura Y Humedad</h6></div>";
		String humedad="<div class=\"card-body\"><h4 id=\"humedads\" class=\"small font-weight-bold\">Humedad</h4><div class=\"progress mb-4\"><div class=\"progress-bar\" id=\"humedad\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div></div>";
		String tempC= "<h4 id=\"temperaturacs\" class=\"small font-weight-bold\">Temperatura °C</h4><div class=\"progress mb-4\"><div id=\"temperaturac\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div>";
		String sensC= "<h4 id=\"sensacioncs\" class=\"small font-weight-bold\">Sensacion Termica °C</h4><div class=\"progress mb-4\"><div id=\"sensacionc\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div>";
		String tempF= "<h4 id=\"temperaturafs\" class=\"small font-weight-bold\">Temperatura °F</h4><div class=\"progress mb-4\"><div id=\"temperaturaf\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div>";
		String sensF= "<h4 id=\"sensacionfs\" class=\"small font-weight-bold\">Sensacion Termica °F</h4><div class=\"progress mb-4\"><div id=\"sensacionf\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div>";
		String fin= "</div></div></div>";
		
		VistaDAO vistadao = new VistaDAO();
		Vista vista= new Vista();
		vista.setName("temperatura_horizontal");
		vista.setInicio(inicio);
		vista.setFin(fin);
		Map<String, String> elem= new HashMap<>();
		elem.put("Hum",humedad);
		elem.put("tempC", tempC);
		elem.put("sensC", sensC);
		elem.put("tempF", tempF);
		elem.put("sensF", sensF);
		vista.setContenido(elem);
		vistadao.create(vista);
		System.out.println("termino vista horizontal");
		
		inicio= "<div class=\"col-12\"> <div class=\"card shadow mb-4\"><div class=\"card-header py-3\"><h6 class=\"m-0 font-weight-bold text-primary\">Sensor de Temperatura Y Humedad</h6></div>";
		humedad= "<div class=\"card border-left-primary shadow h-20 py-2\"> <div class=\"card-body\"> <div class=\"row no-gutters align-items-center\"> <div class=\"col-12\"><div class=\"text-xs font-weight-bold text-primary text-uppercase mb-1\">Humedad</div> </div><div class=\"col-12\" id=\"chartHum\"> <i class=\"fas fa-calendar fa-2x text-gray-300\"></i> </div> </div> </div></div>";
		String indiceTemp="<div class=\"card border-left-primary shadow h-70 py-2\"><div class=\"card-body\"><div class=\"row no-gutters align-items-center\"><div class=\"col-12\"><div class=\"text-xs font-weight-bold text-primary text-uppercase mb-1\">Temperatura</div></div>";
		tempC= "<div class=\"col-6\" id=\"chartTempC\"></div>";
		sensC= "<div class=\"col-6\" id=\"chartHiC\"></div>";
		tempF= "<div class=\"col-6\" id=\"chartTempF\"></div>";
		sensF= "<div class=\"col-6\" id=\"chartHiF\"></div>";
		String finTemp="</div></div></div>";
		fin= "</div></div>";
		
		vistadao = new VistaDAO();
		vista= new Vista();
		vista.setName("temperatura_reloj");
		vista.setInicio(inicio);
		vista.setFin(fin);
		elem= new HashMap<>();
		elem.put("Hum",humedad);
		elem.put("tempC", tempC);
		elem.put("sensC", sensC);
		elem.put("tempF", tempF);
		elem.put("sensF", sensF);
		elem.put("indiceTemp", indiceTemp);
		elem.put("finTemp", finTemp);
		vista.setContenido(elem);
		vistadao.create(vista);
		System.out.println("termino vista reloj");
		
		
		inicio="<div class=\"col-lg-6 mb-4\"> <div class=\"card shadow mb-4\"> <div class=\"card-header py-3\">	<h6 class=\"m-0 font-weight-bold text-primary\">Sonoff</h6></div>";
		fin="</div> </div>";
		String sonoffbody="<div class=\"card-body\"><form role=\"form\" action=\"<c:url value='/home/pushbutton/${sonoffserial}' />\" method=\"post\" id=\"push${sonoffserial}\" enctype=\"multipart/form-data\"><h4 id=\"sonoffname\" class=\"small font-weight-bold\">Timer<span class=\"float-right\">0</span></h4><div class=\"progress mb-4\">	<div class=\"progress-bar\" id=\"sonofftimer\" role=\"progressbar\"	aria-valuemin=\"0\" aria-valuemax=\"60\" style=\"width: 50%;\">	</div></div><p></p><b> Status Power<div class=\"float-right\"><input id=\"sonoffpower\" type=\"checkbox\"	data-toggle=\"toggle\" data-style=\"slow\" onchange=\"sendMQTTMessage('${sonoffserial}')\"></div></b><input type=\"hidden\" name=\"sonoffpower\" id=\"sonoffpower1\" />	<p></p></form><form role=\"form\" action=\"<c:url value='/home/simulatedpushbutton/${sonoffserial}' />\" method=\"post\" enctype=\"multipart/form-data\"><button type=\"submit\" class=\"btn btn-primary btn-sm\">Simulated push button</button>	</form></div>";
		vistadao = new VistaDAO();
		vista= new Vista();
		vista.setName("sonoff");
		vista.setInicio(inicio);
		vista.setFin(fin);
		elem= new HashMap<>();
		elem.put("sonoffbody",sonoffbody);
		vista.setContenido(elem);
		vistadao.create(vista);
		System.out.println("termino vista de boton");
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
				
				String jsonstring = "{"
						+ "parametername:"+"'TempC'"+","
						+ "tipodedato:"+"'Number'"+","
						+ "vista:"+"'barrahorizontal'"+","
						+ "parametername:"+"'Hum'"+","
						+ "tipodedato:"+"'Number'"+","
						+ "vista:"+"'barrahorizontal'"
						+ "}";				
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
	public void testUpdateVista(){
		DeviceDAO devicedao = new DeviceDAO();
		Device device = devicedao.retrieveBySerialNumber("PS3S1P120190323");
		//String vista = "<div class=\"col-lg-6 mb-4\"><div class=\"card shadow mb-4\"><div class=\"card-header py-3\"><h6 class=\"m-0 font-weight-bold text-primary\">Sensor de Temperatura Y Humedad</h6></div><div class=\"card-body\"><h4 id=\"humedads\" class=\"small font-weight-bold\"></h4><div class=\"progress mb-4\"><div class=\"progress-bar\" id=\"humedad\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div></div><h4 id=\"temperaturacs\" class=\"small font-weight-bold\">Temperatura °C</h4><div class=\"progress mb-4\"><div id=\"temperaturac\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div><h4 id=\"sensacioncs\" class=\"small font-weight-bold\">Sensacion Termica °C</h4><div class=\"progress mb-4\"><div id=\"sensacionc\" class=\"progress-bar  bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div><h4 id=\"temperaturafs\" class=\"small font-weight-bold\">Temperatura °F</h4><div class=\"progress mb-4\"><div id=\"temperaturaf\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div><h4 id=\"sensacionfs\" class=\"small font-weight-bold\">Sensacion Termica °F</h4><div class=\"progress\"><div id=\"sensacionf\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div></div></div></div>";
		String vista="sonoff;sonoffbody";
//		device.getVista().clear();
		device.getVista().put("t@tes", vista);
		devicedao.update(device);
		System.out.println("esta vacio? "+ device.getVista().isEmpty());
	}
	
	//@Test
	public void searchVistas(){
		UserDAO userdao = new UserDAO();
		DeviceDAO devicedao = new DeviceDAO();
		User user = userdao.retrieveByMail("t@tes");
		Device device = devicedao.retrieveBySerialNumber(user.getDeviceserialnumber().get(0));
//		System.out.println(device.getDescription());
//		System.out.println("esta es la vista: "+ device.getVista().get("t@tes"));
		
		String allenvelope=device.getVista().get("t@tes");
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
	
	//@Test
	public void testMakeAlumno(){
		UserDAO alumdao = new UserDAO();
		User alumno = new User();

//		alumno.setName("pablo");

		Ciclolectivo ciclo = new Ciclolectivo();
		ciclo.setAnio(2019);
		
		Materia materia = new Materia();
		List<Materia.materia> listMaterias = new ArrayList<>();
		
		Materia.materia matParticular = new materia();
		matParticular.setAnio(2019);
		matParticular.setIdentifier("01MAT");	
		listMaterias.add(matParticular);
		
		matParticular.setAnio(2019);
		matParticular.setIdentifier("01LENG");
		listMaterias.add(matParticular);

		materia.setMateria(listMaterias);
		
		ciclo.setMaterias(materia);
		
		
		alumno.setEmail("pablo.bilbao@hotmail.com");
		alumdao.create(alumno);
		System.out.println("termino");
	}
	
//	@Test
//	public void testCreateAsistencia() {
//		AsistenciaDAO asisdao = new AsistenciaDAO();
//		Asistencia asistencia = new Asistencia();
//		asistencia.setDescripcion("test");
//		asisdao.create(asistencia);
//	}
//	
//	@Test
//	public void testActual() {
//		System.out.println("fecha de hoy: "+Utils.fechaActual());
//	}
	
//	@Test
//	public void searchAsistencia(){
//		AsistenciaDAO asisdao = new AsistenciaDAO();
//		Asistencia asis = asisdao.retrieveByNameDateDescription("", "02/02/2018");
//		System.out.println("asis: "+asis);
//	}
	
	//@Test
//	public void testFechaActual() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
//		SimpleDateFormat formatoFecha = new SimpleDateFormat();
//		formatoFecha.setTimeZone(TimeZone.getTimeZone("GMT-6"));
//		Date fechaSum = calendar.getTime();
//		formatoFecha.applyPattern("dd/MM/yyyy");
//		String fechaRespuesta = formatoFecha.format(fechaSum);
//		System.out.println(fechaRespuesta);
//		Integer fechaActual = Utils.obtenerTrimestre(fechaRespuesta);
//		System.out.println("trimestre "+fechaActual);
//	}

	//@Test
//	public void testCreateNota() {
//		NotaDAO notadao = new NotaDAO();
//		Nota nota = new Nota();
//		nota.setDescripcion("test");
//		notadao.create(nota);
		
//		Nota nota = notadao.retrieveByNameDateDescription("", "", "");
//	}
	
//	@Test
//	public void createAlumno(){
//		AlumnoDAO alumdao = new AlumnoDAO();
//		Alumno alumno = new Alumno();
//		alumno.setName("pablo");
//		alumdao.create(alumno);
//		System.out.println("termino");
//	}
	
//	@Test
//	public void createDocente(){
//		DocenteDAO docentedao= new DocenteDAO();
//		Docente docente = new Docente();
//		docente.setCurso("4");
//		docente.setDelete(false);
////		docente.setMateria("NTICXs");
//		docente.setName("Leandroa Guzman");
//		docente.setPassword("leandro".getBytes());
//		docentedao.create(docente);
//		System.out.println("termino");
//	}
//	@Test
//	public void retrieveDocuments() {
//		DocumentoDAO docdao = new DocumentoDAO();
//		List<Documento> document = docdao.retrieveByMateria("nticxs");
//		System.out.println("lista de documentos: " +document.size());
//	}
	
//	@Test
//	public void retrieveDocuments() {
//		AlumnoDAO alumdao = new AlumnoDAO();
//		Alumno alumno = alumdao.retrieveByName("pablo da silva");
//		Nota nota = new Nota();
//		nota.setDescripcion("actividad de clase");
//		nota.setTipo(Nota.ACTIVIDADES);
//		nota.setFecha(new Date());
//		nota.setValor(10);
//		nota.setTrimestre(Nota.PRIMER_TRIMESTRE);
//		nota.setMateria("nticxs");
//		alumno.getNotas().add(nota);
//		alumdao.update(alumno);	
//		}
//	@Test
//	public void updateAsistencia() {
//		AlumnoDAO alumdao = new AlumnoDAO();
//		Alumno alumno = alumdao.retrieveByName("pablo da silva");
//		Asistencia asistencia = new Asistencia();
//		asistencia.setDescripcion("");
//		asistencia.setFecha(new Date());
//		asistencia.setMateria("nticxs");
//		asistencia.setTipo(Asistencia.PRSENTE);
//		alumno.getAsistencia().add(asistencia);
//		alumdao.update(alumno);
//	}
	
	
	
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
