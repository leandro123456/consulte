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
	   	SimpleTimerString tim = new SimpleTimerString(timerstringsonoff);
		//tim.sendmessageMQTT(json,host,port,topic,user,pass);
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
//		vista.setName("temperatura_horizontal");
//		vista.setInicio(inicio);
//		vista.setFin(fin);
		Map<String, String> elem= new HashMap<>();
//		elem.put("Hum",humedad);
//		elem.put("tempC", tempC);
//		elem.put("sensC", sensC);
//		elem.put("tempF", tempF);
//		elem.put("sensF", sensF);
//		vista.setContenido(elem);
//		vistadao.create(vista);
//		System.out.println("termino vista horizontal");
//		
//		inicio= "<div class=\"col-12\"> <div class=\"card shadow mb-4\"><div class=\"card-header py-3\"><h6 class=\"m-0 font-weight-bold text-primary\">Sensor de Temperatura Y Humedad</h6></div>";
//		humedad= "<div class=\"card border-left-primary shadow h-20 py-2\"> <div class=\"card-body\"> <div class=\"row no-gutters align-items-center\"> <div class=\"col-12\"><div class=\"text-xs font-weight-bold text-primary text-uppercase mb-1\">Humedad</div> </div><div class=\"col-12\" id=\"chartHum\"> <i class=\"fas fa-calendar fa-2x text-gray-300\"></i> </div> </div> </div></div>";
//		String indiceTemp="<div class=\"card border-left-primary shadow h-70 py-2\"><div class=\"card-body\"><div class=\"row no-gutters align-items-center\"><div class=\"col-12\"><div class=\"text-xs font-weight-bold text-primary text-uppercase mb-1\">Temperatura</div></div>";
//		tempC= "<div class=\"col-6\" id=\"chartTempC\"></div>";
//		sensC= "<div class=\"col-6\" id=\"chartHiC\"></div>";
//		tempF= "<div class=\"col-6\" id=\"chartTempF\"></div>";
//		sensF= "<div class=\"col-6\" id=\"chartHiF\"></div>";
//		String finTemp="</div></div></div>";
//		fin= "</div></div>";
//		
//		vistadao = new VistaDAO();
//		vista= new Vista();
//		vista.setName("temperatura_reloj");
//		vista.setInicio(inicio);
//		vista.setFin(fin);
//		elem= new HashMap<>();
//		elem.put("Hum",humedad);
//		elem.put("tempC", tempC);
//		elem.put("sensC", sensC);
//		elem.put("tempF", tempF);
//		elem.put("sensF", sensF);
//		elem.put("indiceTemp", indiceTemp);
//		elem.put("finTemp", finTemp);
//		vista.setContenido(elem);
//		vistadao.create(vista);
//		System.out.println("termino vista reloj");
		
		
		inicio="<div class=\"col-lg-6 mb-4\"> <div class=\"card shadow mb-4\"> <div class=\"card-header py-3\">	<h6 class=\"m-0 font-weight-bold text-primary\">Sonoff CAMBIARSONOFF</h6></div>";
		fin="</div> </div>";
		String sonoffbody="<div class=\"card-body\"> <h4 id=\"sonoffname\" class=\"small font-weight-bold\">Timer<span class=\"float-right\">0</span></h4><div class=\"progress mb-4\">	<div class=\"progress-bar\" id=\"sonofftimer\" role=\"progressbar\"	aria-valuemin=\"0\" aria-valuemax=\"60\" style=\"width: 50%;\">	</div></div><p></p>	<b> Status Power<div class=\"float-right\">	<input id=\"sonoffpowerCAMBIARSONOFF\" type=\"checkbox\"	data-toggle=\"toggle\" data-style=\"slow\" onchange=\"EnviarSonoff('mqtt.coiaca.com','8080','mqttusr','mqttpwd','CAMBIARSONOFF')\"></div></b><input type=\"hidden\" name=\"sonoffpower\" id=\"sonoffpower1\" />	<p></p>	<button type=\"submit\" class=\"btn btn-primary btn-sm float-left\" onclick=\"EnviarSonoffSimulatePushbutton('mqtt.coiaca.com','8080','mqttusr','mqttpwd','CAMBIARSONOFF')\">Simulated push button</button><form role=\"form\" action=\"/mqttmanagment/home/settimerString/CAMBIARSONOFF\" method=\"get\" enctype=\"multipart/form-data\"><button type=\"submit\" class=\"btn btn-lg float-right\" style=\"background: transparent;  border: transparent;\"><i class=\"fas fa-stopwatch\" title=\"Set Timer String\"></i></button>	</form></div>";
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
	
}
