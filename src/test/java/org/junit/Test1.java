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
//		String topic="PS3S1P120190323/swcmd";
		String topic= "RConfig/PS3S1P120190323";
		String user="mqttusr";
		String pass="mqttpwd";
//		String message ="\"pwd\":\"mqttpwd\", \"command\":\"switchAction\",\"SW1\":\"ON\"";
		String message ="{'pwd':'mqttpwd', 'command':'switchAction','param1':'SW1','param2':'turnOn'}";
//		String message = "{\"pwd\":\"mqttpwd\", \"command\":\"simulateButtonPush\",\"param1\":\"BTN1\"}";
//		String message = "\"pwd\":\"mqttpwd\", \"command\":\"switchAction\",\"param1\":\"SW1\",\"param2\":\"turnOn\"";
//		String message = "{\"pwd\":\"mqttpwd\", \"command\":\"getStatus\"}";
		
		//{"pwd":"coiaca", "command":"switchAction","param1":"SW1","param2":"turnOff"}
	   	 JSONObject json = new JSONObject();
	   	 json.put("pwd", "coiaca");
	   	 json.put("command", "switchAction");
	   	 json.put("param1", "SW1");
	   	 json.put("param2", "turnOff");
//	   	 json.put("SW1", "OFF");
	   	 System.out.println("el json: "+ json);
	   	SimpleTimerString tim = new SimpleTimerString(timerstringsonoff);
		tim.sendmessageMQTT(json,host,port,topic,user,pass);
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
		String inicio= "<div class=\"col-lg-6 mb-4\"><div class=\"card shadow mb-4\"><div class=\"card-header py-3\"><h6 class=\"m-0 font-weight-bold text-primary\">Sensor CAMBIARSENSOR</h6></div>";
		String humedad = "<div class=\"card-body\"><h4 class=\"small font-weight-bold\">Humedad<span id=\"humedadCAMBIARSENSOR\" class=\"float-right\">0</span></h4><div class=\"progress mb-4\"><div class=\"progress-bar\" id=\"barrahumCAMBIARSENSOR\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div></div>";
		String tempC= "<h4 class=\"small font-weight-bold\">Temperatura °C<span id=\"temperaturacCAMBIARSENSOR\" class=\"float-right\">0</span></h4><div class=\"progress mb-4\"><div id=\"barratempcCAMBIARSENSOR\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div>";
		String sensC= "<h4 class=\"small font-weight-bold\">Sensacion Termica °C<span id=\"sensacioncCAMBIARSENSOR\" class=\"float-right\">0</span></h4><div class=\"progress mb-4\"><div id=\"barrasenscCAMBIARSENSOR\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div>";
		String tempF= "<h4 class=\"small font-weight-bold\">Temperatura °F<span id=\"temperaturafCAMBIARSENSOR\" class=\"float-right\">0</span></h4><div class=\"progress mb-4\"><div id=\"barratempfCAMBIARSENSOR\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div>";
		String sensF= "<h4 class=\"small font-weight-bold\">Sensacion Termica °F<span id=\"sensacionfCAMBIARSENSOR\" class=\"float-right\">0</span></h4><div class=\"progress mb-4\"><div id=\"barrasensfCAMBIARSENSOR\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div>";
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
		

		//de un solo boton
		inicio="<div class=\"col-lg-6 mb-4\"> <div class=\"card shadow mb-4\"> <div class=\"card-header py-3\">	<h6 class=\"m-0 font-weight-bold text-primary\">Sonoff CAMBIARSONOFF</h6></div>";
		fin="</div> </div>";
		String sonoffbody="<div class=\"card-body\">"
				+ "<div> <span class=\"font-weight-bold\">Estado del Switch</span> <span id=\"spanestadoCAMBIARSONOFF\" class=\"float-right font-weight\">online</span></div>"
				
				+"<hr></hr>"
				+ "<h6 class=\"font-weight-bold\">Cambiar modo del Switch</h6> <button type=\"button\" id=\"boton1CAMBIARSONOFF\" onclick=\"EnviarSonoff('HOSTSONOFF','PORTSONOFF','USERSONOFF','PASSSONOFF','TOPICSONOFF','switchone','CAMBIARSONOFF','boton1CAMBIARSONOFF')\" class=\"btn btn-primary btn-sm btn-block\">Apagado</button> <p> </p>"
				+ "<h4 id=\"sonoffname\" class=\"small font-weight-bold\">Timer<span id=\"span1CAMBIARSONOFF\" class=\"float-right\">0</span></h4><div class=\"progress mb-4\">	<div class=\"progress-bar\" id=\"sonofftimer1CAMBIARSONOFF\" role=\"progressbar\"	aria-valuemin=\"0\" aria-valuemax=\"60\" style=\"width: 0%;\">	</div></div> <p> </p>"
				+ "</b><input type=\"hidden\" name=\"sonoffpower\" id=\"sonoffpower1\" /><p></p>	<button type=\"submit\" class=\"btn btn-primary btn-sm btn-block\" onclick=\"EnviarSonoffSimulatePushbutton('HOSTSONOFF','PORTSONOFF','USERSONOFF','PASSSONOFF','TOPICSONOFF','switchone','CAMBIARSONOFF')\">Simular pulsado del Boton</button> <p> </p>"
				+ "<form role=\"form\" action=\"/mqttmanagment/home/settimerString/CAMBIARSONOFF\" method=\"get\" enctype=\"multipart/form-data\"><input type=\"hidden\" name=\"sonoffTimerString\" value=\"one\"/><button type=\"submit\" class=\"btn btn-primary btn-sm btn-block\">Cambiar Funcion de Encendido/Apagado automatico</button></form>"
				+ "</div>";
		
		
		//style=\"background: transparent;  border: transparent;\"><i class=\"fas fa-stopwatch\" title=\"Set Timer String\"></i>
		
		
//		vistadao = new VistaDAO();
//		vista= new Vista();
//		vista.setName("sonoff");
//		vista.setInicio(inicio);
//		vista.setFin(fin);
//		elem= new HashMap<>();
//		elem.put("sonoffbody",sonoffbody);
//		vista.setContenido(elem);
//		vistadao.create(vista);
//		System.out.println("termino vista de 1 boton");
		
		//de dos botones
		inicio="<div class=\"col-lg-6 mb-4\"> <div class=\"card shadow mb-4\"> <div class=\"card-header py-3\">	<h6 class=\"m-0 font-weight-bold text-primary\">Sonoff CAMBIARSONOFF</h6></div>";
		fin="</div> </div>";
		
		sonoffbody="<div class=\"card-body\">"
				+ "<div> <span class=\"font-weight-bold\">Estado del Switch</span> <span id=\"spanestadoCAMBIARSONOFF\" class=\"float-right font-weight\">online</span></div>"
				
				+"<hr></hr>"
				+ "<h6 class=\"font-weight-bold\">Cambiar modo del Switch 1</h6> <button type=\"button\" id=\"boton1CAMBIARSONOFF\" onclick=\"EnviarSonoff('HOSTSONOFF','PORTSONOFF','USERSONOFF','PASSSONOFF','TOPICSONOFF','switchone','CAMBIARSONOFF','boton1CAMBIARSONOFF')\" class=\"btn btn-primary btn-sm btn-block\">Apagado</button> <p> </p>"
				+ "<h4 id=\"sonoffname\" class=\"small font-weight-bold\">Timer<span id=\"span1CAMBIARSONOFF\" class=\"float-right\">0</span></h4><div class=\"progress mb-4\">	<div class=\"progress-bar\" id=\"sonofftimer1CAMBIARSONOFF\" role=\"progressbar\"	aria-valuemin=\"0\" aria-valuemax=\"60\" style=\"width: 0%;\">	</div></div> <p> </p>"
				+ "</b><input type=\"hidden\" name=\"sonoffpower\" id=\"sonoffpower1\" /><p></p>	<button type=\"submit\" class=\"btn btn-primary btn-sm btn-block\" onclick=\"EnviarSonoffSimulatePushbutton('HOSTSONOFF','PORTSONOFF','USERSONOFF','PASSSONOFF','TOPICSONOFF','switchone','CAMBIARSONOFF')\">Simular pulsado del Boton</button> <p> </p>"
				+ "<form role=\"form\" action=\"/mqttmanagment/home/settimerString/CAMBIARSONOFF\" method=\"get\" enctype=\"multipart/form-data\"><input type=\"hidden\" name=\"sonoffTimerString\" value=\"one\"/><button type=\"submit\" class=\"btn btn-primary btn-sm btn-block\">Cambiar Funcion de Encendido/Apagado automatico</button></form>"
				
				+"<hr></hr>"
				+ "<h6 class=\"font-weight-bold\">Cambiar modo del Switch 2</h6> <button type=\"button\" id=\"boton2CAMBIARSONOFF\" onclick=\"EnviarSonoff('HOSTSONOFF','PORTSONOFF','USERSONOFF','PASSSONOFF','TOPICSONOFF','switchtwo','CAMBIARSONOFF','boton2CAMBIARSONOFF')\" class=\"btn btn-primary btn-sm btn-block\">Apagado</button> <p> </p>"
				+ "<h4 id=\"sonoffname\" class=\"small font-weight-bold\">Timer<span id=\"span2CAMBIARSONOFF\" class=\"float-right\">0</span></h4><div class=\"progress mb-4\">	<div class=\"progress-bar\" id=\"sonofftimer2CAMBIARSONOFF\" role=\"progressbar\"	aria-valuemin=\"0\" aria-valuemax=\"60\" style=\"width: 0%;\">	</div></div> <p> </p>"
				+ "</b><input type=\"hidden\" name=\"sonoffpower\" id=\"sonoffpower2\" /><p></p>	<button type=\"submit\" class=\"btn btn-primary btn-sm btn-block\" onclick=\"EnviarSonoffSimulatePushbutton('HOSTSONOFF','PORTSONOFF','USERSONOFF','PASSSONOFF','TOPICSONOFF','switchtwo','CAMBIARSONOFF')\">Simular pulsado del Boton 2</button> <p> </p>"
				+ "<form role=\"form\" action=\"/mqttmanagment/home/settimerString/CAMBIARSONOFF\" method=\"get\" enctype=\"multipart/form-data\"><input type=\"hidden\" name=\"sonoffTimerString\" value=\"two\"/> <button type=\"submit\" class=\"btn btn-primary btn-sm btn-block\">Cambiar Funcion de Encendido/Apagado automatico</button></form>"
								
				+ "</div>";
		
		
//		vistadao = new VistaDAO();
//		vista= new Vista();
//		vista.setName("sonofftwo");
//		vista.setInicio(inicio);
//		vista.setFin(fin);
//		elem= new HashMap<>();
//		elem.put("sonoffbody",sonoffbody);
//		vista.setContenido(elem);
//		vistadao.create(vista);
//		System.out.println("termino vista de 2 boton");
		
		
		
		//alarma
		inicio="";
		fin="";
		String alarmabody="<div class=\"col-lg-6 mb-4\">" + 
				"	<div class=\"card shadow mb-4\">" + 
				"		<div class=\"card-header py-3\">" + 
				"			<h6 class=\"m-0 font-weight-bold text-primary\">Alarma CAMBIARALARMA</h6>" + 
				"		</div>" + 
				"		<div class=\"card-body\">" + 
				"							<div class=\"col-xs-12\">" + 
				
				"								<div id=\"lcd_container\">" + 
				"									<div class=\"virtual_lcd\">" + 
				"										<div id=\"first_line\">&nbsp;</div>" + 
				"										<div id=\"second_line\">&nbsp;</div>" + 
				"									</div>" + 
				"									<div class=\"status_icons\">" + 
				"										<i class=\"dsc-icon icon-check\" id=\"ready_iconCAMBIARALARMA\" title=\"Lista\"></i>" + 
				"										<i class=\"dsc-icon icon-armed\" id=\"armed_iconCAMBIARALARMA\" title=\"Armada\"></i>" + 
				"										<i class=\"dsc-icon icon-trouble\" id=\"trouble_iconCAMBIARALARMA\"" + 
				"											title=\"Problema en el Sistema\"></i> <i class=\"dsc-icon icon-ac\"" + 
				"											id=\"ac_iconCAMBIARALARMA\" title=\"Alarma Activada\"></i>" + 
				"									</div>" + 
				"								</div>" + 

				"								<div class=\"col-xs-12\" id=\"buttons_area\">" + 
				"									<div id=\"left_buttons\">" + 
				"										<div class=\"centrarbonotesalarma\">" + 
				"											<button type=\"button\" id=\"btn_f\"" + 
				"												class=\"btn btn-outline-dark keypad_button keypad_button_control\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonfuego','CAMBIARALARMA','fuego')\">" + 
				"												<i class=\"dsc-icon icon-flame\" title=\"Fuego\"></i>" + 
				"											</button>" + 
				"										</div>" + 
				"										<p></p>" + 
				"										<div class=\"centrarbonotesalarma\">" + 
				"											<button type=\"button\" id=\"btn_a\"" + 
				"												class=\"btn btn-outline-dark keypad_button keypad_button_control\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonpolicia','CAMBIARALARMA','policia')\">" + 
				"												<i class=\"dsc-icon icon-alert\" title=\"Alerta\"></i>" + 
				"											</button>" + 
				"										</div>" + 
				"										<p></p>" + 
				"										<div class=\"centrarbonotesalarma\">" + 
				"											<button type=\"button\" id=\"btn_p\"" + 
				"												class=\"btn btn-outline-dark keypad_button keypad_button_control\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonmedico','CAMBIARALARMA','medico')\">" + 
				"												<i class=\"dsc-icon icon-thief\" title=\"Panico\"></i>" + 
				"											</button>" + 
				"										</div>" + 
				"									</div>" + 
				
				"									<div id=\"keypad_container\">" + 
				"										<div class=\"keypad_button_row\">" + 
				"											<button type=\"button\" id=\"btn_1\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','1')\">1</button>" + 
				"											<button type=\"button\" id=\"btn_2\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','2')\">2</button>" + 
				"											<button type=\"button\" id=\"btn_3\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','3')\">3</button>" + 
				"										</div>" + 
				"										<div class=\"keypad_button_row\">" + 
				"											<button type=\"button\" id=\"btn_4\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','4')\">4</button>" + 
				"											<button type=\"button\" id=\"btn_5\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','5')\">5</button>" + 
				"											<button type=\"button\" id=\"btn_6\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','6')\">6</button>" + 
				"										</div>" + 
				"										<div class=\"keypad_button_row\">" + 
				"											<button type=\"button\" id=\"btn_7\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','7')\">7</button>" + 
				"											<button type=\"button\" id=\"btn_8\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','8')\">8</button>" + 
				"											<button type=\"button\" id=\"btn_9\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','9')\">9</button>" + 
				"										</div>" + 
				"										<div class=\"keypad_button_row\">" + 
				"											<button type=\"button\" id=\"btn_*\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','*')\">*</button>" + 
				"											<button type=\"button\" id=\"btn_0\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','0')\">0</button>" + 
				"											<button type=\"button\" id=\"btn_#\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','#')\">#</button>" + 
				"										</div>" + 
				"									</div>" + 
				
				"									<div id=\"right_buttons\">" + 
				"										<div class=\"centrarbonotesalarma\">" + 
				"											<button type=\"button\" id=\"btn_sCAMBIARALARMA\"" + 
				"												class=\"btn btn-outline-dark keypad_button keypad_button_control\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonarmadozona','CAMBIARALARMA','armarzona')\">" + 
				"												<i class=\"dsc-icon icon-stay_away \" title=\"Armar en Casa\"></i>" + 
				"											</button>" + 
				"										</div>" + 
				"										<p></p>" + 
				"										<div class=\"centrarbonotesalarma\">" + 
				"											<button type=\"button\" id=\"btn_wCAMBIARALARMA\"" + 
				"												class=\"btn btn-outline-dark keypad_button keypad_button_control\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonarmadocompleto','CAMBIARALARMA','armartotal')\">" + 
				"												<i class=\"dsc-icon icon-stay_empty\" title=\"Armar Completa\"></i>" + 
				"											</button>" + 
				"										</div>" + 
				"										<p></p>" + 
				"										<div class=\"centrarbonotesalarma\">" + 
				"											<button type=\"button\" id=\"btn_cCAMBIARALARMA\"" + 
				"												class=\"btn btn-outline-dark keypad_button keypad_button_control\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','botonteclado','CAMBIARALARMA','*')\">" + 
				"												<i class=\"dsc-icon icon-bell\"></i>" + 
				"											</button>" + 
				"										</div>" + 
				"									</div>" + 
				"								</div>" + 
				"							</div>" + 
				"						</div>" + 
				"					</div>" + 
				"				</div>";
		
		vistadao = new VistaDAO();
		vista= new Vista();
		vista.setName("alarma");
		vista.setInicio(inicio);
		vista.setFin(fin);
		elem= new HashMap<>();
		elem.put("alarmabody",alarmabody);
		vista.setContenido(elem);
		vistadao.create(vista);
		System.out.println("termino vista de alarma");
	}
	
}
