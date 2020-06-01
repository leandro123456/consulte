package com.lgg.nticxs.web.controller;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.lgg.nticxs.web.DAO.DeviceDAO;
import com.lgg.nticxs.web.DAO.DeviceDefaultConfigurationDAO;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.DeviceDefaultConfiguration;
import com.lgg.nticxs.web.utils.Settings;
import com.lgg.nticxs.web.utils.Utils;

@Controller
public class PorteroController {
	
	 @Autowired
	   private ServletContext servletContext;
	 
	private static final String URI_BACKEND=Settings.getInstance().getURIBackend();
	private DeviceDAO devicedao= new DeviceDAO();
	
	@GetMapping("doorman/{user}") 
	public void getDoorman(Model model, @PathVariable String user) {
		try {
			//redigir peticion al backend
			//usuario en base64
			URL url = new URL(URI_BACKEND+"/envio/"+user);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int cod_status = con.getResponseCode();
			String status = con.getResponseMessage();
			System.out.println("RESPUESTA: "+ status+": "+cod_status);
		} catch (Exception e) {
			System.out.println("fallo en el envio del mensaje!!!! ");
		}
	}
	
	
	//Genera el QR, lo codifica en base 64 para enviarlo a la vista
	@GetMapping("home/doorman/generateqr/{serial}")
	@ResponseBody
	public String getRunningOperations(Model model, @PathVariable String serial) {
		System.out.println("Llego a la peticion de ajax");
		JSONObject json = new JSONObject();
		Device device= devicedao.retrieveBySerialNumber(serial);
		
		String imagenenbase64=Utils.generarImagenEnBase64(device.getUridoorman());
		
		json.put("nombre", device.getCalle()+"-"+device.getNumero());
		json.put("result", imagenenbase64);

		return json.toString();
	}
	
	
	
	@GetMapping(value="home/doorman/downloadqr89/{deviceid}"
	,produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[]  downloadImageQr(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String deviceid) throws IOException {
		System.out.println("llego la peticion");
		Device device = devicedao.retrieveBySerialNumber("sanbenitodepalermo1618896992221");//deviceid);
		System.out.println("uri: "+ device.getUridoorman());
 
        InputStream in = getClass().getResourceAsStream("/home/steven/Desktop/qrt.jpg");
        System.out.println("in: "+ in);
        return IOUtils.toByteArray(in);
//        System.out.println("Termino la descarga de la imagen");
	}
	
	
	@GetMapping( value= "home/doorman/downloadqr/{deviceid}")
//			,produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadDocumentQR(@PathVariable String deviceid, HttpServletResponse response) 
			throws IOException {
		File file = new File("/home/steven/Desktop/foto.jpeg");
		System.out.println("info: "+ file.canRead());//true
		System.out.println("info: "+ file.canExecute()); //false
		System.out.println("info: "+ file.canWrite());//true
		System.out.println("info: "+ file.isFile());//true
		System.out.println("info: "+ file.exists());//true
		
		System.out.println("esta es la uri: "+ 	file.toURI());
		 
		BufferedImage imagen= ImageIO.read(file);
		byte[] imageBytes = ((DataBufferByte) imagen.getData().getDataBuffer()).getData();
        response.setContentLength(imageBytes.length);
        response.setHeader("Content-Disposition","attachment; filename=\"" +"archivodeTcto.png" +"\"");
        FileCopyUtils.copy(imageBytes, response.getOutputStream());
     
        System.out.println("QR Code image created successfully!");
        
     try {
    	   String qrCodeData = "www.chillyfacts.com";
           String filePath = "/home/steven/Desktop/qrt1.jpg";
           String charset = "UTF-8"; // or "ISO-8859-1"
           Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
           hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
           BitMatrix matrix = new MultiFormatWriter().encode(
               new String(qrCodeData.getBytes(charset), charset),
               BarcodeFormat.QR_CODE, 200, 200, hintMap);
           
           InputStream in = servletContext.getResourceAsStream("/home/steven/Desktop/foto.jpeg");
           int va = in.read();
           response.setContentType(MediaType.IMAGE_JPEG_VALUE);
           response.setContentLength(va);
           IOUtils.copy(in, response.getOutputStream());
	} catch (Exception e) {
		System.out.println("fallo");
		e.printStackTrace();
	}


	}
	
}
