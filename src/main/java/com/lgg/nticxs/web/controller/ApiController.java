package com.lgg.nticxs.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lgg.nticxs.web.utils.WSLogger;
import com.lgg.nticxs.web.helper.ES3Helper;


@RestController
@RequestMapping("/api")
public class ApiController {

	private static WSLogger logger = new WSLogger();

	@RequestMapping("/setfallbackattribute/{eid}/{iccid}")
	@ResponseStatus(value = HttpStatus.OK)
	public void setFallbackAttribute(@PathVariable String eid, @PathVariable String iccid) {
		ES3Helper.setFallbackAttribute(eid, iccid);
	}
	
	@RequestMapping("/enablefallbackattribute/{eid}/{available}")
	@ResponseStatus(value = HttpStatus.OK)
	public void enableFallbackAttribute(@PathVariable String eid, @PathVariable Boolean available) {
		ES3Helper.enableFallback(eid, available);
	}
	
	@RequestMapping("/setNumberStatus/{eid}/{number}")
	@ResponseStatus(value = HttpStatus.OK)
	public void setStatusNuber(@PathVariable String eid, @PathVariable String number) {
		ES3Helper.statusNumberFallback(eid, number);
	}
	
	@RequestMapping("/updateEcasd")
	@ResponseStatus(value = HttpStatus.OK)
	public String UpdateEcasd(){
		String fileContent;
		try {
			fileContent = new String(Files.readAllBytes(Paths.get("/var/movasim/cubicb/files-web/personalisation/" + "0223.inp")));
			//fileContent = new String(Files.readAllBytes(Paths.get("/home/leandro/Desktop/tarjetas-cubic/tarjetas/inp-de-tarjetas/" + "0223.inp")));
			String[] headers = {};
			String[] dataLines = {};
//			PersonalisationIppDAO perDAO = new PersonalisationIppDAO();
			Pattern pattern = Pattern.compile("var_out:(.+?)\n(.+?)$", Pattern.DOTALL);
			Matcher matcher = pattern.matcher(fileContent);
			if (!matcher.find()) {
				logger.logger("ERROR", "SM-WEB", "", "", "", "UpdateEcasd()", "", "", "", "Failed");
			}
			headers = matcher.group(1).split("/");
			dataLines = matcher.group(2).split("\r\n");
			String text=dataLines[0];
			ArrayList<Integer> value=new ArrayList<>();
			value.add(0);
			for(int i=0; i<text.length();i++){
				if (text.charAt(i)=='\n')
					value.add(i);
			}
			dataLines=new String[value.size()-1];
			for(int j=0;j<value.size()-1;j++){
				if (j==0)
					dataLines[j]=text.substring(value.get(j), value.get(j+1));
				else
					dataLines[j]=text.substring(value.get(j)+1, value.get(j+1));
			}
			/** hasta aca*/	
			@SuppressWarnings("unused")
			Gson gson = new Gson();		
			for (String line : dataLines) {
//				Map<String, String> map = new HashMap<String, String>();
				String[] values = line.split(";");
				String eid="";
				String ecasd="";
				for (int i = 0; i < headers.length; i++) {
					if(headers[i].equals("CERT_ECASD")){
						try {
//						ecasd=Utils.decrypt3Des(Settings.getInstance().getKeyMovasim(), values[i]); //clave movasim
//						ecasd=ecasd.substring(0,CryptoTools.hexToDecimal(ecasd.substring(6,8))*2+8);
						} catch (Exception e) {
							logger.logger("ERROR", "SM-WEB", "", "", "", "UpdateEcasd()", "", "", "", "Failed to decrypt ECASD");
							
							e.printStackTrace();
						}
					}
					if(headers[i].equals("EID"))
						eid=values[i];
				}
//				replaceEcasd(eid,ecasd);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Update Ecasd Fallied";
		}
		return "Update Ecasd OK";
	}
	
//	private void replaceEcasd(String eid, String ecasd){
//		//update DP
//		CardDAO cardao=new CardDAO();
//		Card card= cardao.retrieveByEid(eid);
//		if(card!=null){
//			card.setPkEcasd(ecasd);
//			cardao.update(card);
//		}
//		//Update Sr
//		com.movasim.sr.DAO.CardDAO cardaoSR=new com.movasim.sr.DAO.CardDAO();
//		com.movasim.sr.model.Card cardSR= cardaoSR.retrieveByEid(eid);
//		if(cardSR!=null){
//			cardSR.setPkEcasd(ecasd);
//			cardaoSR.update(cardSR);
//		}
//		logger.logger("INFO", "SM-WEB", "", "", "", "replaceEcasd()", "", "", "", "I finalize well");
//	}
}
