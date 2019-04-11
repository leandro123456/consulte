package org.junit;

import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.DAO.helper.MongoDBRemove;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.lgg.nticxs.web.DAO.AsistenciaDAO;
import com.lgg.nticxs.web.DAO.CiclolectivoDAO;
import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.DocenteDAO;
import com.lgg.nticxs.web.DAO.DocumentoDAO;
import com.lgg.nticxs.web.DAO.NotaDAO;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.Asistencia;
import com.lgg.nticxs.web.model.Ciclolectivo;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceConfiguration;
import com.lgg.nticxs.web.model.Docente;
import com.lgg.nticxs.web.model.Documento;
import com.lgg.nticxs.web.model.Materia;
import com.lgg.nticxs.web.model.Materia.materia;
import com.lgg.nticxs.web.model.Nota;

public class Test1 {
	
	
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
			Device device = devicedao.retrieveBySerialNumber("4564dsd");
			if(device == null){
				device = new Device();
				device.setDescription("description");
				device.setName("nombre");
				device.setSerialnumber("4564dsd");
				device.setUserowner("t@tes");	
				DeviceConfiguration deviceconfiguration = new DeviceConfiguration();
				deviceconfiguration.setIphost("ip");
				deviceconfiguration.setName("default");
				deviceconfiguration.setPass("pass");
				deviceconfiguration.setPort("port");
				deviceconfiguration.setTopicescribir("topicescribir");
				deviceconfiguration.setTopicescribirremote("topicescribirremote");
				deviceconfiguration.setTopicescuchar("topicescuchar");
				deviceconfiguration.setTopicescucharremote("topicescucharremote");
				deviceconfiguration.setUser("user");
				deviceconfiguration.setUsessl(false);
				device.getDeviceconfiguration().add(deviceconfiguration);
				device.getAdmins().add("pepe@test");
				device.getUsers().add("juli@test");
				devicedao.create(device);
				
				String vista = "<div class=\"col-lg-6 mb-4\"><div class=\"card shadow mb-4\"><div class=\"card-header py-3\"><h6 class=\"m-0 font-weight-bold text-primary\">Sensor de Temperatura Y Humedad</h6></div><div class=\"card-body\"><h4 id=\"humedads\" class=\"small font-weight-bold\"></h4><div class=\"progress mb-4\"><div class=\"progress-bar\" id=\"humedad\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div></div><h4 id=\"temperaturacs\" class=\"small font-weight-bold\">Temperatura °C</h4><div class=\"progress mb-4\"><div id=\"temperaturac\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div><h4 id=\"sensacioncs\" class=\"small font-weight-bold\">Sensacion Termica °C</h4><div class=\"progress mb-4\"><div id=\"sensacionc\" class=\"progress-bar  bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"70\"></div></div><h4 id=\"temperaturafs\" class=\"small font-weight-bold\">Temperatura °F</h4><div class=\"progress mb-4\"><div id=\"temperaturaf\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div><h4 id=\"sensacionfs\" class=\"small font-weight-bold\">Sensacion Termica °F</h4><div class=\"progress\"><div id=\"sensacionf\" class=\"progress-bar bg-info\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"140\"></div></div></div></div></div>";
				String indicadores2 = "<div class=\"col-lg-6 mb-4\"><div class=\"card shadow mb-4\"><div class=\"card-header py-3\">        <h6 class=\"m-0 font-weight-bold text-primary\">Widget of Status</h6></div><div class=\"card-body\"><form class=\"user\" id=\"connection-information-form\">      <div class=\"form-group row\"><b>Hostname or IP Address</b> 	<input type=\"text\" class=\"form-control form-control-user\" id=\"host\" value=\"gw001.iotek.space\" placeholder=\"Hostname\"></div><div class=\"form-group row\">	<b>Port</b>  	<input type=\"text\" class=\"form-control form-control-user\" id=\"port\" value=\"8883\" placeholder=\"Port\">      </div><div class=\"form-group row\"><b>Topic:</b><input id=\"topic\" type=\"text\" class=\"form-control form-control-user\" name=\"topic\" value=\"WTHUSB000000001/state\" placeholder=\"Topic\"></div><hr><input type=\"button\" class=\"btn btn-primary btn-user btn-block\" onclick=\"startConnect()\" value=\"Connect\"><input type=\"button\" class=\"btn btn-primary btn-user btn-block\" onclick=\"startDisconnect()\" value=\"Disconnect\">    </form><div id=\"messages\"></div></div></div></div>";
				device.getVista().put(device.getUserowner(), vista);
				device.getVista().put("pepe@test", indicadores2);
				
				String jsonstring = "{"
						+ "parametername:"+"'TempC'"+","
						+ "tipodedato:"+"'Number'"+","
						+ "vista:"+"'barrahorizontal'"+","
						+ "parametername:"+"'Hum'"+","
						+ "tipodedato:"+"'Number'"+","
						+ "vista:"+"'barrahorizontal'"
						+ "}";
				
				device.getVistaporusuario().put("t@tes", jsonstring);
				
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
		Device device = devicedao.retrieveBySerialNumber("111FFF");
		//device.getVista().clear();
		String indicadores2 = "<div class=\"col-lg-6 mb-4\"><div class=\"card shadow mb-4\"><div class=\"card-header py-3\">        <h6 class=\"m-0 font-weight-bold text-primary\">Widget of Status</h6></div><div class=\"card-body\"><form class=\"user\" id=\"connection-information-form\">      <div class=\"form-group row\"><b>Hostname or IP Address</b> 	<input type=\"text\" class=\"form-control form-control-user\" id=\"host\" value=\"gw001.iotek.space\" placeholder=\"Hostname\"></div><div class=\"form-group row\">	<b>Port</b>  	<input type=\"text\" class=\"form-control form-control-user\" id=\"port\" value=\"8883\" placeholder=\"Port\">      </div><div class=\"form-group row\"><b>Topic:</b><input id=\"topic\" type=\"text\" class=\"form-control form-control-user\" name=\"topic\" value=\"WTHUSB000000001/state\" placeholder=\"Topic\"></div><hr><input type=\"button\" class=\"btn btn-primary btn-user btn-block\" onclick=\"startConnect()\" value=\"Connect\"><input type=\"button\" class=\"btn btn-primary btn-user btn-block\" onclick=\"startDisconnect()\" value=\"Disconnect\">    </form><div id=\"messages\"></div></div></div></div>";
		device.getVista().put("t@tes", indicadores2);
		devicedao.update(device);
		
	}
	
	//@Test
	public void searchVistas(){
		UserDAO userdao = new UserDAO();
		DeviceDAO devicedao = new DeviceDAO();
		User user = userdao.retrieveByMail("t@tes");
		Device device = devicedao.retrieveBySerialNumber(user.getDeviceserialnumber().get(0));
//		System.out.println(device.getDescription());
//		System.out.println("esta es la vista: "+ device.getVista().get("t@tes"));
		
		String allenvelope=device.getVistaporusuario().get("t@tes");
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
