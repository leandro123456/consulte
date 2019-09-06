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
		String inicio= "<div class=\"col-lg-6 mb-4\"><div class=\"card shadow mb-4\"><div class=\"card-header py-3\"><h6 class=\"m-0 font-weight-bold text-primary\">Sensor CAMBIARSENSOR</h6></div>"
					+"<div class=\"card-body\">"
                    +"<div class=\"row\"> <span class=\"col-xs-4 font-weight-bold\"><h6><small class=\"alinearspanstatus\">Estado</small></h6></span> <span class=\"col-xs-4 float-right font-weight\"><h6><small id=\"spanestadoCAMBIARSENSOR\">desconocido</small></h6></span></div>";
	    String humedad = "<h6 class=\"font-weight-bold\">Humedad<span id=\"humedadCAMBIARSENSOR\" class=\"float-right\">0</span></h6><div class=\"progress mb-4\"><div class=\"progress-bar\" id=\"barrahumCAMBIARSENSOR\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div></div>";
		String tempC= "<h6 class=\"font-weight-bold\">Temperatura °C<span id=\"temperaturacCAMBIARSENSOR\" class=\"float-right\">0</span></h6><div class=\"progress mb-4\"><div id=\"barratempcCAMBIARSENSOR\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div>";
		String sensC= "<h6 class=\" font-weight-bold\">Sensacion Termica °C<span id=\"sensacioncCAMBIARSENSOR\" class=\"float-right\">0</span></h6><div class=\"progress mb-4\"><div id=\"barrasenscCAMBIARSENSOR\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div>";
		String tempF= "<h6 class=\" font-weight-bold\">Temperatura °F<span id=\"temperaturafCAMBIARSENSOR\" class=\"float-right\">0</span></h6><div class=\"progress mb-4\"><div id=\"barratempfCAMBIARSENSOR\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div>";
		String sensF= "<h6 class=\" font-weight-bold\">Sensacion Termica °F<span id=\"sensacionfCAMBIARSENSOR\" class=\"float-right\">0</span></h6><div class=\"progress mb-4\"><div id=\"barrasensfCAMBIARSENSOR\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div>";
		String fin= "</div></div></div>";
		
		VistaDAO vistadao = new VistaDAO();
		Vista vista= new Vista();
		Map<String, String> elem= new HashMap<>();
		
//propio de la vista
//		vista.setName("temperatura_horizontal");
//		vista.setInicio(inicio);
//		vista.setFin(fin);
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
				+ "<div class=\"row\"> "
				+ "		<span class=\"col-xs-4 font-weight-bold\">"
				+ "			<h6>"
				+ "				<small class=\"alinearspanstatus\">Estado</small>"
				+ "			</h6>"
				+ "		</span> "
				+ "		<span class=\"col-xs-4 float-right font-weight\">"
				+ "			<h6>"
				+ "				<small id=\"spanestadoCAMBIARSONOFF\">desconocido</small>"
				+ "			</h6>"
				+ "		</span>"
				+ "</div>"
				
				+ "<h6 class=\"font-weight-bold\">Cambiar modo del Switch</h6> <button type=\"button\" id=\"boton1CAMBIARSONOFF\" onclick=\"EnviarSonoff('HOSTSONOFF','PORTSONOFF','USERSONOFF','PASSSONOFF','TOPICSONOFF','switchone','CAMBIARSONOFF','boton1CAMBIARSONOFF')\" class=\"btn btn-primary btn-sm btn-block\">Apagado</button> <p> </p>"
				+ "<h4 id=\"sonoffname\" class=\"small font-weight-bold\">Timer<span id=\"span1CAMBIARSONOFF\" class=\"float-right\">0</span></h4><div class=\"progress mb-4\">	<div class=\"progress-bar\" id=\"sonofftimer1CAMBIARSONOFF\" role=\"progressbar\"	aria-valuemin=\"0\" aria-valuemax=\"60\" style=\"width: 0%;\">	</div></div> <p> </p>"
				+ "</b><input type=\"hidden\" name=\"sonoffpower\" id=\"sonoffpower1\" /><p></p>	<button type=\"submit\" class=\"btn btn-primary btn-sm btn-block\" onclick=\"EnviarSonoffSimulatePushbutton('HOSTSONOFF','PORTSONOFF','USERSONOFF','PASSSONOFF','TOPICREMOTESONOFF','switchone','CAMBIARSONOFF')\">Simular pulsado del Boton</button> <p> </p>"
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
				+"<div class=\"row\"> "
				+ "		<span class=\"col-xs-4 font-weight-bold\">"
				+ "			<h6>"
				+ "				<small class=\"alinearspanstatus\">Estado</small>"
				+ "			</h6>"
				+ "		</span> "
				+ "		<span class=\"col-xs-4 float-right font-weight\">"
				+ "			<h6>"
				+ "				<small id=\"spanestadoCAMBIARSONOFF\">desconocido</small>"
				+ "			</h6>"
				+ "		</span>"
				+ "</div>"
				
				+ "<h6 class=\"font-weight-bold\">Cambiar modo del Switch 1</h6> <button type=\"button\" id=\"boton1CAMBIARSONOFF\" onclick=\"EnviarSonoff('HOSTSONOFF','PORTSONOFF','USERSONOFF','PASSSONOFF','TOPICSONOFF','switchone','CAMBIARSONOFF','boton1CAMBIARSONOFF')\" class=\"btn btn-primary btn-sm btn-block\">Apagado</button> <p> </p>"
				+ "<h4 id=\"sonoffname\" class=\"small font-weight-bold\">Timer<span id=\"span1CAMBIARSONOFF\" class=\"float-right\">0</span></h4><div class=\"progress mb-4\">	<div class=\"progress-bar\" id=\"sonofftimer1CAMBIARSONOFF\" role=\"progressbar\"	aria-valuemin=\"0\" aria-valuemax=\"60\" style=\"width: 0%;\">	</div></div> <p> </p>"
				+ "</b><input type=\"hidden\" name=\"sonoffpower\" id=\"sonoffpower1\" /><p></p>	<button type=\"submit\" class=\"btn btn-primary btn-sm btn-block\" onclick=\"EnviarSonoffSimulatePushbutton('HOSTSONOFF','PORTSONOFF','USERSONOFF','PASSSONOFF','TOPICREMOTESONOFF','switchone','CAMBIARSONOFF')\">Simular pulsado del Boton</button> <p> </p>"
				+ "<form role=\"form\" action=\"/mqttmanagment/home/settimerString/CAMBIARSONOFF\" method=\"get\" enctype=\"multipart/form-data\"><input type=\"hidden\" name=\"sonoffTimerString\" value=\"one\"/><button type=\"submit\" class=\"btn btn-primary btn-sm btn-block\">Cambiar Funcion de Encendido/Apagado automatico</button></form>"
				
				+"<hr></hr>"
				+ "<h6 class=\"font-weight-bold\">Cambiar modo del Switch 2</h6> <button type=\"button\" id=\"boton2CAMBIARSONOFF\" onclick=\"EnviarSonoff('HOSTSONOFF','PORTSONOFF','USERSONOFF','PASSSONOFF','TOPICSONOFF','switchtwo','CAMBIARSONOFF','boton2CAMBIARSONOFF')\" class=\"btn btn-primary btn-sm btn-block\">Apagado</button> <p> </p>"
				+ "<h4 id=\"sonoffname\" class=\"small font-weight-bold\">Timer<span id=\"span2CAMBIARSONOFF\" class=\"float-right\">0</span></h4><div class=\"progress mb-4\">	<div class=\"progress-bar\" id=\"sonofftimer2CAMBIARSONOFF\" role=\"progressbar\"	aria-valuemin=\"0\" aria-valuemax=\"60\" style=\"width: 0%;\">	</div></div> <p> </p>"
				+ "</b><input type=\"hidden\" name=\"sonoffpower\" id=\"sonoffpower2\" /><p></p>	<button type=\"submit\" class=\"btn btn-primary btn-sm btn-block\" onclick=\"EnviarSonoffSimulatePushbutton('HOSTSONOFF','PORTSONOFF','USERSONOFF','PASSSONOFF','TOPICREMOTESONOFF','switchtwo','CAMBIARSONOFF')\">Simular pulsado del Boton 2</button> <p> </p>"
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
				"										<div class=\"row\"> "
				+ "											<span class=\"col-xs-4 alinedisplay\">"
				+ "												<h6>"
				+ "													<small class=\"alinearspanstatus\">Estado</small>"
				+ "												</h6>"
				+ "											</span> "
				+ "											<span class=\"col-xs-4 float-right alinezones\">"
				+ "												<h6>"
				+ "													<small id=\"spanestadoCAMBIARALARMA\">desconocido</small>"
				+ "												</h6>"
				+ "											</span>"
				+ "										</div>"+
				
				"										<div id=\"first_lineCAMBIARALARMA\" class=\"row\">" + 
				"											<span class=\"col-xs-4 alinedisplay\" style=\"padding-left: 1em; padding-right: 0.5em;\">" + 
				"												<h6 class=\"alinearspanstatus\">Particion:</h6>" + 
				"											</span>" + 
				"											<span class=\"col-xs-4 float-right alinezones\">" + 
				"												<h6 id=\"particionesCAMBIARALARMA\"></h6>" + 
				"											</span>" + 
				"										</div>" + 
				"										<div id=\"second_lineCAMBIARALARMA\" class=\"row\" style=\"padding-left: 1em; padding-right: 0.5em;\">" + 
				"											<span class=\"col-xs-4 alinedisplay\" style=\"padding-left: 1em; padding-right: 0.5em;\">" + 
				"												<h6 class=\"alinearspanstatus\"></h6>" + 
				"											</span>" + 
				"											<span class=\"col-xs-4 float-right alinezones\" style=\"padding-left: 1em; padding-right: 0.5em;\">" + 
				"												<h6 id=\"segundalineaCAMBIARALARMA\"></h6>" + 
				"											</span>" + 
				"										</div>"+
				"									</div>" + 
				"									<div class=\"status_icons\">" + 
				"										<i class=\"dsc-icon icon-check\" id=\"ready_iconCAMBIARALARMA\" title=\"Lista\"></i>" + 
				"										<i class=\"dsc-icon icon-armed\" id=\"armed_iconCAMBIARALARMA\" title=\"Armada\"></i>" + 
				"										<i class=\"dsc-icon icon-trouble\" id=\"trouble_iconCAMBIARALARMA\" title=\"Pendiente\"></i> "
				+ "										<i class=\"dsc-icon icon-ac\" id=\"ac_iconCAMBIARALARMA\" title=\"Desarmada\"></i>" + 
				"									</div>" + 
				"								</div>" + 

				"								<div class=\"row\" id=\"buttons_area\">" + 
				"									<div class=\"bordecolumna\" id=\"columnaizquierdaCAMBIARALARMA\">" + 
				"									<p> </p>"+
				"										<div class=\"centrarbonotesalarma\">" + 
				"											<button type=\"button\" id=\"btn_f\"" + 
				"												class=\"btn btn-outline-dark keypad_button keypad_button_control\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-fuego')\">" + 
				"												<i class=\"dsc-icon icon-flame\" title=\"Fuego\"></i>" + 
				"											</button>" + 
				"										</div>" + 
				"										<p></p>" + 
				"										<div class=\"centrarbonotesalarma\">" + 
				"											<button type=\"button\" id=\"btn_a\"" + 
				"												class=\"btn btn-outline-dark keypad_button keypad_button_control\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-policia')\">" + 
				"												<i class=\"dsc-icon icon-alert\" title=\"Alerta\"></i>" + 
				"											</button>" + 
				"										</div>" + 
				"										<p></p>" + 
				"										<div class=\"centrarbonotesalarma\">" + 
				"											<button type=\"button\" id=\"btn_p\"" + 
				"												class=\"btn btn-outline-dark keypad_button keypad_button_control\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-medico')\">" + 
				"												<i class=\"dsc-icon icon-thief\" title=\"Panico\"></i>" + 
				"											</button>" + 
				"										</div>" + 
				"									</div>" + 
				
				"									<div class=\"bordecolumna\" id=\"columnaderechaCAMBIARALARMA\">" +
				"									<p> </p>"+
				"										<div class=\"centrarbonotesalarma\">" + 
				"											<button type=\"button\" id=\"btn_sCAMBIARALARMA\"" + 
				"												class=\"btn btn-outline-dark keypad_button keypad_button_control\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-armarzona')\">" + 
				"												<i class=\"dsc-icon icon-stay_away \" title=\"Armar en Casa\"></i>" + 
				"											</button>" + 
				"										</div>" + 
				"										<p></p>" + 
				"										<div class=\"centrarbonotesalarma\">" + 
				"											<button type=\"button\" id=\"btn_wCAMBIARALARMA\"" + 
				"												class=\"btn btn-outline-dark keypad_button keypad_button_control\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-armartotal')\">" + 
				"												<i class=\"dsc-icon icon-stay_empty\" title=\"Armar Completa\"></i>" + 
				"											</button>" + 
				"										</div>" + 
				"										<p></p>" + 
				"										<div class=\"centrarbonotesalarma\">" + 
				"											<button type=\"button\" id=\"btn_cCAMBIARALARMA\"" + 
				"												class=\"btn btn-outline-dark keypad_button keypad_button_control\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-campana')\">" + 
				"												<i class=\"dsc-icon icon-bell\"></i>" + 
				"											</button>" + 
				"										</div>" + 
				"									</div>" +
				"								<p> </p>		  " +
				
				
				"									<div class=\"centrarbonotesalarma bordecolumna\" id=\"tecladoCAMBIARALARMA\">" + 
				"										<div class=\"keypad_button_row\">" + 
				"											<button type=\"button\" id=\"btn_1\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-1')\">1</button>" + 
				"											<button type=\"button\" id=\"btn_2\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-2')\">2</button>" + 
				"											<button type=\"button\" id=\"btn_3\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-3')\">3</button>" + 
				"										</div>" + 
				"										<div class=\"keypad_button_row\">" + 
				"											<button type=\"button\" id=\"btn_4\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-4')\">4</button>" + 
				"											<button type=\"button\" id=\"btn_5\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-5')\">5</button>" + 
				"											<button type=\"button\" id=\"btn_6\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-6')\">6</button>" + 
				"										</div>" + 
				"										<div class=\"keypad_button_row\">" + 
				"											<button type=\"button\" id=\"btn_7\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-7')\">7</button>" + 
				"											<button type=\"button\" id=\"btn_8\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-8')\">8</button>" + 
				"											<button type=\"button\" id=\"btn_9\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-9')\">9</button>" + 
				"										</div>" + 
				"										<div class=\"keypad_button_row\">" + 
				"											<button type=\"button\" id=\"btn_*\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-*')\">*</button>" + 
				"											<button type=\"button\" id=\"btn_0\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-0')\">0</button>" + 
				"											<button type=\"button\" id=\"btn_#\"" + 
				"												class=\"btn btn-outline-dark keypad_button\" onclick=\"Connecttotal('HOSTALARMA','PORTALARMA','USERALARMA','PASSALARMA','TOPICOALARMA','particion','CAMBIARALARMA','alarm-#')\">#</button>" + 
				"										</div>" + 
				"									</div>" + 
				"								</div>" +
				

				"								<div class=\"row\" id=\"zones_listCAMBIARALARMA\">"+
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_1_CAMBIARALARMA\"><i class=\"far fa-circle\"></i><h9 > Zone 01</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_2_CAMBIARALARMA\"><i class=\"far fa-circle\"></i><h9 > Zone 02 </h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_3_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9> Zone 03 </h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_4_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 > Zone 04 </h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_5_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 > Zone 05 </h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_6_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 06</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_7_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 07</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_8_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 08</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_9_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 09</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_10_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9>Zone 10</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_11_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 11</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_12_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 12</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_13_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 13</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_14_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 14</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_15_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 15</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_16_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 16</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_17_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 17</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_18_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9>Zone 18</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_19_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 19</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_20_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 20</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_21_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 21</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_22_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 22</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_23_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 23</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_24_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 24</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_25_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 25</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_26_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 26</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_27_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 27</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_28_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 28</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_29_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 29</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_30_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 30</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_31_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 31</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_32_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 32</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_33_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 33</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_34_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 34</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_35_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 35</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_36_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 36</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_37_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 37</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_38_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 38</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_39_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 39</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_40_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 40</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_41_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 41</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_42_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 42</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_43_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 43</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_44_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 44</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_45_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 45</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_46_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 46</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_47_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 47</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_48_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 48</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_49_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 49</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_50_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 50</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_51_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 51</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_52_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 52</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_53_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 53</h9></div>" +				
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_54_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 54</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_55_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 55</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_56_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 56</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_57_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 57</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_58_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 58</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_59_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 59</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_60_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 60</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_61_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 61</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_62_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 62</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_63_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 63</h9></div>" +
				"										<div class=\"col-xs-6 small alinezones\" id=\"zone_64_CAMBIARALARMA\"><i class=\"far fa-circle\"></i> <h9 >Zone 64</h9></div>" +											
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

