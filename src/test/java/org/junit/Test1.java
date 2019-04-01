package org.junit;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.lgg.nticxs.web.utils.Utils;

//import org.junit.jupiter.api.Test;

import com.lgg.nticxs.web.DAO.AlumnoDAO;
import com.lgg.nticxs.web.DAO.AsistenciaDAO;
import com.lgg.nticxs.web.DAO.CiclolectivoDAO;
import com.lgg.nticxs.web.DAO.DocenteDAO;
import com.lgg.nticxs.web.DAO.DocumentoDAO;
import com.lgg.nticxs.web.DAO.NotaDAO;
import com.lgg.nticxs.web.model.Alumno;
import com.lgg.nticxs.web.model.Asistencia;
import com.lgg.nticxs.web.model.Ciclolectivo;
import com.lgg.nticxs.web.model.Docente;
import com.lgg.nticxs.web.model.Documento;
import com.lgg.nticxs.web.model.Materia;
import com.lgg.nticxs.web.model.Materia.materia;
import com.lgg.nticxs.web.model.Nota;

public class Test1 {
	
//	@Test
//	public void testCreateCiclolectivo(){
//		CiclolectivoDAO ciclodao = new CiclolectivoDAO();
//		Ciclolectivo ciclo = new Ciclolectivo();
//		ciclo.setAnio(2019);
//		
//		Materia materia = new Materia();
//		List<Materia.materia> listMaterias = new ArrayList<>();
//		
//		Materia.materia matParticular = new materia();
//		matParticular.setAnio(2019);
//		matParticular.setIdentifier("01MAT");	
//		listMaterias.add(matParticular);
//		
//		matParticular.setAnio(2019);
//		matParticular.setIdentifier("01LENG");
//		listMaterias.add(matParticular);
//
//		materia.setMateria(listMaterias);
//		
//		ciclo.setMaterias(materia);
//		ciclodao.create(ciclo);
//		System.out.println("termino bien");
//	}
	
	
	//@Test
	public void testMakeAlumno(){
		AlumnoDAO alumdao = new AlumnoDAO();
		Alumno alumno = new Alumno();
		alumno.setName("pablo");

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
		
		
		alumno.setCiclolectivo(ciclo);
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
