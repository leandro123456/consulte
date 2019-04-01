package com.lgg.nticxs.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgg.nticxs.web.utils.WSLogger;
import com.lgg.nticxs.web.model.SimpleDPofSR;
import com.lgg.nticxs.web.model.SimpleMNOSR;



@Controller
public class AmdSRController {
	
//	private com.movasim.dp.DAO.MNODAO mnodaoDp = new com.movasim.dp.DAO.MNODAO();
//	private MNODAO mnodaoSr = new com.movasim.sr.DAO.MNODAO();
//	private KeysDAO srdao = new KeysDAO();
//	private DPDAO srdpdao = new DPDAO();
	
	private static WSLogger logger = new WSLogger();
	
	/**------------------------------------------------ AMD SR ---------------------------------**/
	
	@GetMapping("home/amdsr")
    public String ampsrload(Model model) {
//    	loadPage(model);
    	
    	logger.logger("INFO", "SM-WEB", "AMD SR", "", "", "ampsrload()", "", "", "", "The screen of AMD SR opens");
    	
        return "amdsr";
    }
			/**					SRs					**/
//	
//	 	@GetMapping("home/amdsr/sr/edit/{srid}")
//	    public String editSR(Model model,@PathVariable String srid) {
//	    	SRDAO srdao=new SRDAO();
//	    	SR sr= srdao.retrieveById(srid);
//	    	model.addAttribute("srDate", sr);
//	    	
//	    	logger.logger("INFO", "SM-WEB", "AMD-SR", "", "Edit SM-SR", "editSR()", "", "", 
//	    	"ID SM-SR: " + srid,
//	    	"A screen opens where can edit the SM-SR");
//	    	
//	    	return "sr_editSR";
//	    }
//	    
//	    @PostMapping("home/amdsr/sr/edit/{srid}")
//	    public String editSRSave(Model model,
//	    		@PathVariable String srid,
//	    		@RequestParam("action") String action,
//	    		@RequestParam("smsrid") String smsrid,
//	    		@RequestParam("name") String name,
//	    		@RequestParam("url") String url,
//	    		@RequestParam("urlEs4") String urlEs4,
//	    		@RequestParam("enable") Boolean enable){
//	    	
//	    	SRDAO srdao=new SRDAO();
//	    	
//	    	if (action.compareTo("save") == 0) {
//	    		SR sr=srdao.retrieveById(srid);
//	    		
//	    		if(sr!=null){
//	    			sr.setSmsrId(smsrid);
//	    			sr.setName(name);
//	    			sr.setUrl(url);
//	    			sr.setUrlEs4(urlEs4);
//	    			
//	    			Date date= new Date();
//	    			sr.setUpdatedAt(date);
//	    			sr.setEnable(enable);
//
//	    			srdao.update(sr);
//	    			model.addAttribute("msg1", "SR update successfully completed");
//	    			
//	    			logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Edit SM-SR", "editSRSave()", "", "", 
//	    			"ID SM-SR: " + srid + ", SM-SR ID: " + smsrid + ", Name: " + name + ", URL: " + url + ", URL ES4: " + urlEs4 + ", Enable: " + enable, 
//	    			"SR update successfully completed");
//	    		}
//	    		loadPage(model);
//	    	}
//	    	loadPage(model);
//	    	return "amdsr";
//	    }
//	    
//	    @GetMapping("home/amdsr/sr/add")
//	    public String addSr(Model model){
//	    	
//	    	logger.logger("INFO", "SM-WEB", "AMD-SR", "", "Add SM-SR", "addSr()", "", "", "", "A screen opens where can add a new SM-SR");
//	    	
//	    	return "sr_addSR";
//	    }    
//	    
//	    @PostMapping("home/amdsr/sr/add")
//	    public String addSrPost(Model model,
//	    		@RequestParam("action") String action,
//	    		@RequestParam(name="name") String name,
//	    		@RequestParam(name="smsrid") String smsrid,
//	    		@RequestParam(name="url") String url,
//	    		@RequestParam(name="urlEs4") String urlEs4,
//	    		@RequestParam(name="enable") Boolean enable){
//	    	
//	    	SRDAO srdao=new SRDAO();
//	    	if (action.compareTo("save") == 0) {
//	    		SR sr= new SR();
//	    		Date date=new Date();
//	    		
//	    		sr.setSmsrId(smsrid);
//	    		sr.setUrl(url);
//	    		sr.setUrlEs4(urlEs4);
//	    		sr.setName(name);
//	    		sr.setCreatedAt(date);
//	    		sr.setUpdatedAt(date);
//	    		sr.setEnable(enable);
//	    		sr.setDeteted(false);
//	    		
//	    		srdao.create(sr);
//	    		model.addAttribute("msg1", "SR created successfully");
//	    		
//	    		logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Add SM-SR", "addSrPost()", "", "", 
//	    		"SM-SR ID: " + smsrid + ", Name: " + name + ", URL: " + url + ", URL ES4: " + urlEs4 + ", Enable: " + enable, 
//	    		"SM-SR created successfully");
//	    	}
//	    	loadPage(model);
//	    	return "amdsr";
//	    }
//	    
//	    @GetMapping("home/amdsr/sr/delete/{srid}")
//	    public String deletedSR(Model model, @PathVariable String srid) {
//	    	SRDAO srdao=new SRDAO();
//	    	SR sr = srdao.retrieveById(srid);
//	    	sr.setDeteted(true);
//	    	srdao.update(sr);
//	    	model.addAttribute("msg1", "SR deleted successfully");
//	    	
//	    	logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Delete SM-SR", "deletedSR()", "", "", "ID SM-SR: " + srid, "SM-SR deleted successfully");
//	    	
//	    	loadPage(model);
//	    	return "amdsr";
//	    }
//	    
//	    							/**			SR Certificate			**/
//	    
//	    @GetMapping("home/amdsr/srcertificate/edit/{srid}")
//	    public String editSRCertificate(Model model, @PathVariable String srid) {
//	    	Keys sr= srdao.retrieveById(srid);
//	    	model.addAttribute("sr", sr);
//	    	
//	    	logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Edit SM-SR certificate", "editSRCertificate()", "", "", 
//	    	"ID SM-SR: " + srid,
//	    	"A screen opens where can edit certificate of the SM-SR");
//	    	
//	    	return "sr_edit";
//	    }
//	    
//	    @PostMapping("home/amdsr/srcertificate/edit/{srid}")
//	    public String editSRSave(Model model,
//	    		@PathVariable String srid,
//	    		@RequestParam("action") String action,
//	    		@RequestParam("name") String name,
//	    		@RequestParam(name="description", required=false) String description
//	    		//@RequestParam("certificate") String certificate,
//				//@RequestParam("privateKey") String privateKey,
//				//@RequestParam("publicKey") String publicKey
//	    		)  {
//	    	if (action.compareTo("save") == 0) {
//	    		Keys sr = srdao.retrieveById(srid);
//	    		if(sr!=null){
//	    			
//	    			Keys srName = srdao.retrieveByName(name);
//	    			
//	    			if(srName!=null && !srName.getId().equals(srid)){
//	    				model.addAttribute("msg1", "SM-SR certificate name already exists");
//	    				
//	    				logger.logger("WARNING", "SM-WEB", "AMD-SR", "", "Edit SM-SR certificate", "editSRSave()", "", "", 
//	    				"ID SM-SR: " + srid + ", Name: " + name + ", Description: " + description, 
//	    				"SM-SR certificate name already exists");
//	    				
//	    				loadPage(model);
//	    		    	return "amddp";
//	    			}
//	    			
//	    			//dp.setCertificate(certificate);
//	    			sr.setDescription(description);
//	    			sr.setName(name);
//	    			//dp.setPrivateKey(privateKey);
//	    			//dp.setPublicKey(publicKey);
//	    			srdao.update(sr);
//	    			model.addAttribute("msg1", "SM-SR certificate update successfully completed");
//	    			
//	    			logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Edit SM-SR certificate", "editSRSave()", "", "", 
//	    			"ID SM-SR: " + srid + ", Name: " + name + ", Description: " + description, 
//	    			"SM-SR certificate update successfully completed");
//	    		}
//	    	}
//	    	loadPage(model);
//	    	return "amdsr";
//	    }
//	    
//	    @GetMapping("home/amdsr/srcertificate/add")
//	    public String addSRCertificateGet(Model model){
//	    	
//	    	logger.logger("INFO", "SM-WEB", "AMD-SR", "", "Add SM-SR certificate", "addSRCertificateGet()", "", "", "", "A screen opens where can add a new SM-SR certificate");
//	    	
//	    	return "sr_add";
//	    }
//	    
//	    @PostMapping("home/amdsr/srcertificate/add")
//	    public String addSRCertificatePost(Model model,
//	    		@RequestParam("action") String action,
//	    		@RequestParam(name="name") String name,
//	    		@RequestParam("certificate") String certificate,
//				@RequestParam("privateKey") String privateKey,
//				@RequestParam("publicKey") String publicKey,
//	       		@RequestParam(name="description", required=false) String description){
//	    	
//	    	if (action.compareTo("save") == 0) {
//	    		if(srdao.existSr(name)){
//	    			model.addAttribute("msg1", "SM-SR certificate name already exists");
//	    			
//	    			logger.logger("WARNING", "SM-WEB", "AMD-SR", "", "Add SM-SR certificate", "addSRCertificatePost()", "", "", 
//	    			"Name: " + name + ", Certificate: " + certificate + ", Private Key: " + privateKey + ", Public Key: " + publicKey + ", Description: " + description,
//	    			"SM-SR certificate name already exists");
//	    			
//	    			return "sr_add";
//	    		}
//	    		Keys sr=new Keys();
//	    		sr.setCertificate(certificate);
//				sr.setDescription(description);
//				sr.setName(name);
//				sr.setPrivateKey(privateKey);
//				sr.setPublicKey(publicKey);
//				sr.setDeleted(false);
//				srdao.create(sr);
//	    		model.addAttribute("msg1", "SR created successfully");
//	    		
//	    		logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Add SM-SR certificate", "addSRCertificatePost()", "", "", 
//	    		"Name: " + name + ", Certificate: " + certificate + ", Private Key: " + privateKey + ", Public Key: " + publicKey + ", Description: " + description, 
//	    		"SM-SR certificate created successfully");
//	    	}
//	    	loadPage(model);
//	    	return "amdsr";
//	    }
//	    
//	    @GetMapping("/home/amdsr/srcertificate/delete/{srid}")
//	    public String deleteSRCertificate(Model model,@PathVariable String srid){
//	    	srdao.deleteSr(srid);
//	    	model.addAttribute("msg1", "Correct Deletion");
//	    	
//	    	logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Delete SM-SR", "deleteSRCertificate()", "", "",
//	    	"ID SM-SR: " + srid,
//	    	"Correct Deletion of SM-SR certificate");
//	    	
//	    	loadPage(model);
//	    	return "amdsr";
//	    }
//	    
//	    		/**					end SR			**/
//	    
//	    /**					Dps of SR		 			**/
//	    
//	    @GetMapping("/home/amdsr/dp/edit/{srdpid}")
//	    public String editDPofSR(Model model, @PathVariable String srdpid) {
//	    	DP dpOfSR = srdpdao.retrieveByDpId(srdpid);
//	    	model.addAttribute("dpsr", dpOfSR);
//	    	
//	    	logger.logger("INFO", "SM-WEB", "AMD-SR", "", "Edit SM-DP", "editDPofSR()", "", "", 
//	    	"ID SM-SR: " + srdpid, 
//	    	"A screen opens where can edit of the SM-DP");
//	    	
//	    	return "dpOfSR_edit";
//	   		}
//	    
//	    @PostMapping("/home/amdsr/dp/edit/{srdpid}")
//	    public String editDPofSRSave(Model model,
//	    		@PathVariable String srdpid,
//	    		@RequestParam("action") String action,
//	    		@RequestParam("name") String name,
//	    		@RequestParam("smdpid") String smdpid,
//	    		@RequestParam("url") String url,
//	    		@RequestParam("endpoint") String endpoint) {
//	    	
//	    	if(action.compareTo("save") == 0) {
//	    		DP dpOfSR = srdpdao.retrieveByDpId(srdpid);
//	    		if (dpOfSR != null) {
//	    			DP dpOfSRName = srdpdao.retrieveByName(name);
//	    			if (dpOfSRName != null && dpOfSRName.getId().equals(srdpid)) {
//	    				model.addAttribute("msg1", "DP name already exists");
//	    				
//	    				logger.logger("WARNING", "SM-WEB", "AMD-SR", "", "Edit SM-DP", "editDPofSRSave()", "", "", 
//	    				"Name: " + name + ", ID SM-DP: " + smdpid + ", URL: " + url + ", Endpoint: " + endpoint, 
//	    				"SM-DP name already exists");
//	    				
//	    				loadPage(model);
//	    		    	return "dpOfSR_edit";
//	    			}
//	    			
//	    			dpOfSR.setName(name);
//	    			dpOfSR.setSmdpId(smdpid);
//	    			dpOfSR.setUrl(url);
//	    			dpOfSR.setUrlEndpoint(endpoint);
//	    			srdpdao.update(dpOfSR);
//	    			model.addAttribute("msg1", "DP update successfully completed");
//	    			
//	    			logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Edit SM-DP", "editDPofSRSave()", "", "", 
//	    			"Name: " + name + ", ID SM-DP: " + smdpid + ", URL: " + url + ", Endpoint: " + endpoint, 
//	    			"SM-DP update successfully completed");
//	    		}
//	    	}
//	    	
//	    	loadPage(model);
//	    	return "amdsr";
//	    }
//	    
//	    @GetMapping("home/amdsr/dp/add")
//	    public String addDPofSR(Model model){
//	    	
//	    	logger.logger("INFO", "SM-WEB", "AMD-SR", "", "Add SM-DP", "addDPofSR()", "", "", "", "A screen opens where can add a new SM-DP");
//	    	
//	    	return "dpOfSR_add";
//	    }  
//	    
//	    @PostMapping("home/amdsr/dp/add")
//	    public String addDPofSRPost(Model model,
//	    		@RequestParam("action") String action,
//	    		@RequestParam("name") String name,
//	    		@RequestParam("smdpid") String smdpid,
//	    		@RequestParam("url") String url,
//	    		@RequestParam("endpoint") String endpoint) {
//	    	
//	    	if (action.compareTo("save") == 0) {
//	    		if(srdpdao.existDp(name)) {
//	    			model.addAttribute("msg1", "DP name already exists");
//	    			
//	    			logger.logger("WARNING", "SM-WEB", "AMD-SR", "", "Add SM-DP", "addDPofSRPost()", "", "", 
//	    			"SM-DP ID: " + smdpid + ", Name: " + name +  ", URL: " + url + ", Endpoint: " + endpoint,
//	    			"SM-DP name already exists");
//	    			
//	    			return "dpOfSR_add";
//	    		}
//	    		
//	    		DP dp = new DP();
//	    		dp.setName(name);
//	    		dp.setSmdpId(smdpid);
//	    		dp.setUrl(url);
//	    		dp.setUrlEndpoint(endpoint);
//	    		dp.setDeleted(false);
//	    		srdpdao.create(dp);
//	    		model.addAttribute("msg1", "DP created successfully");
//	    		
//	    		logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Add SM-DP", "addDPofSRPost()", "", "", 
//	    		"SM-DP ID: " + smdpid + ", Name: " + name +  ", URL: " + url + ", Endpoint: " + endpoint, 
//	    		"SM-DP created successfully");
//	    	}
//	    	
//	    	loadPage(model);
//	    	return "amdsr";
//	    }
//	    
//	    @GetMapping("home/amdsr/dp/delete/{srdpid}")
//	    public String deleteDPofSR(Model model,
//	    		@PathVariable String srdpid) {
//	    	srdpdao.deleteDp(srdpid);
//	    	model.addAttribute("msg1", "Correct Deletion");
//	    	
//	    	logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Delete SM-DP", "deleteDPofSR()", "", "", "ID SM-DP: " + srdpid, "Correct Deletion of the SM-DP");
//	    	
//	    	loadPage(model);
//	    	return "amdsr";
//	    }
//	    /**					end Dps of SR					**/
//	    
//	    /**	   --------------------------   	MNO SR init	------------------------------		**/
//	    
//	    @GetMapping("home/amdsr/mno/edit/{mnoid}")
//	    public String editMNO(Model model,@PathVariable String mnoid) {
//	    	MNO mnoSr = mnodaoSr.retrieveById(mnoid);
//	    	if(mnoSr != null){
//	    		SimpleMNOSR mno=new SimpleMNOSR(mnoSr);
//	    		model.addAttribute("mno",mno);
//	    	}
//	    	
//	    	logger.logger("INFO", "SM-WEB", "AMD-SR", "", "Edit MNO", "editMNO()", "", "", 
//	    	"ID MNO: " + mnoid, 
//	    	"A screen opens where can edit of the MNO");
//	    	
//	    	return "mno_editSR";
//	    }
//	    
//	    @PostMapping("home/amdsr/mno/edit/{mnoid}")
//	    public String editMNOSave(Model model,
//	    		@PathVariable String mnoid,
//	    		@RequestParam("action") String action,
//	    		@RequestParam(name="name", required=true) String name,
//	    		@RequestParam(name="url", required=false) String url,
//	    		@RequestParam(name="endpoint", required=false) String endpoint,
//	    		@RequestParam(name="smsc", required=false) String smsc){
//	    	
//	    	if (action.compareTo("save") == 0) {
//	    		MNO mnoSr = mnodaoSr.retrieveById(mnoid);
//	    		com.movasim.dp.model.MNO mnoDp = mnodaoDp.retrieveById(mnoSr.getId());
//	    		
//	    		if(mnoDp != null && mnoSr != null){
//	    			mnoDp.setName(name);
//	    			mnoDp.setSmsc(smsc);
//	    			mnoDp.setUrl(url);
//	    			mnoDp.setUrlEndpoint(endpoint);
//	    			mnodaoDp.update(mnoDp);
//	    			
//	    			mnoSr.setName(name);
//	    			mnoSr.setSmsc(smsc);
//	    			mnoSr.setUrl(url);
//	    			mnoSr.setUrlEndpoint(endpoint);
//	    			mnodaoSr.update(mnoSr);
//	    			
//	    			model.addAttribute("msg1", "MNO edit successfully");
//	    			
//	    			logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Edit MNO", "editMNOSave()", "", "", 
//	    			"ID MNO: " + mnoid + ", Name: " + name + ", URL: " + url + ", Endpoint: "+ endpoint + ", SMSC: " + smsc, 
//	    			"MNO edit successfully");
//	    		} else {
//	    			model.addAttribute("msg1", "MNO error");
//	    		}
//	    			
//	    	}
//	    	loadPage(model);
//	    	return "amdsr";
//	    }
//	    
//	    @GetMapping("home/amdsr/mno/delete/{mnoid}")
//	    public String deleteMNO(Model model,@PathVariable String mnoid) {
//	    	MNO mnoSr = mnodaoSr.retrieveById(mnoid);
//	    	com.movasim.dp.model.MNO mnoDp =mnodaoDp.retrieveByMnoId(mnoSr.getMnoid());
//	    	
//	    	if(mnoSr != null && mnoDp != null ){
//	    		mnoDp.setDeleted(true);
//	    		mnodaoDp.update(mnoDp);
//	    		
//	    		mnoSr.setDeleted(true);
//        		mnodaoSr.update(mnoSr);
//        		
//	        	model.addAttribute("msg1", "Correct Deletion");
//	        	
//	        	logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Delete MNO", "deleteMNO()", "", "", 
//	        	"ID MNO: " + mnoid,
//	        	"Correct Deletion of the MNO");
//	    	}else{
//	        	model.addAttribute("msg1", "Error Deletion");
//	        	
//	        	logger.logger("WARNING", "SM-WEB", "AMD-SR", "", "Delete MNO", "deleteMNO()", "", "", 
//	        	"ID MNO: " + mnoid, 
//	        	"Error Deletion the MNO");
//	    	}
//	    	loadPage(model);
//	    	return "amdsr";
//	    }
//	    
//	    @GetMapping("home/amdsr/mno/add")
//	    public String addMNO(Model model){
//	    	logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Add MNO", "addMNO()", "", "", "", "A screen opens where can add a new MNO");
//	    	
//	    	return "mno_addSR";
//	    }  
//	    
//	    @PostMapping("home/amdsr/mno/add")
//	    public String addMno(Model model,@RequestParam("action") String action,
//	    		@RequestParam(name="name", required=true) String name,
//	    		@RequestParam(name="mnoid", required=true) String mnoid,
//	    		@RequestParam(name="url", required=false) String url,
//	    		@RequestParam(name="endpoint", required=false) String endpoint,
//	    		@RequestParam(name="smsc", required=false) String smsc){
//	    	
//	    	if (action.compareTo("save") == 0) {
//	    		MNO mnoSr=new MNO();
//	    		mnoSr.setDeleted(false);
//	    		mnoSr.setMnoid(mnoid);
//	    		mnoSr.setName(name);
//	    		mnoSr.setSmsc(smsc);
//	    		mnoSr.setUrl(url);
//	    		mnoSr.setUrlEndpoint(endpoint);
//	    		mnodaoSr.create(mnoSr);
//	    		
//	    		com.movasim.dp.model.MNO mnoDp=new com.movasim.dp.model.MNO();
//	    		mnoDp.setId(mnoSr.getId());
//	    		mnoDp.setDeleted(false);
//	    		mnoDp.setMnoid(mnoid);
//	    		mnoDp.setName(name);
//	    		mnoDp.setSmsc(smsc);
//	    		mnoDp.setUrl(url);
//	    		mnoDp.setUrlEndpoint(endpoint);
//	    		mnodaoDp.create(mnoDp);
//	    		
//	    		logger.logger("DEBUG", "SM-WEB", "AMD-SR", "", "Add MNO", "addMno()", "", "", 
//	    		"ID MNO: " + mnoid +", Name: " + name + ", URL: " + url + ", Endpoint: " + endpoint + ", SMSC: " + smsc,
//	    		"MNO created successfully");
//	    		
//	    		model.addAttribute("msg1", "MNO created successfully");
//	    	}
//	    	loadPage(model);
//	    	return "amdsr";
//	    } 
//	    
//	    /**	   --------------------------   	MNOS end	------------------------------		**/
//	    
//	    /**			UTILS		**/
//	    
//	    private List<SR> loadPageSR(){
//	    	SRDAO srdao = new SRDAO();
//	    	
////	    	List<SimpleSrofSR> Sr = new ArrayList<>();
//	    	List<SR> srAll= srdao.retrieveAll();
//	    	
////	    	for (SR srs : srAll){
////	    		SimpleSrofSR simple = new SimpleSrofSR(srs);
////	    		Sr.add(simple);
////	    	}
//	    	
//	    	return srAll;
//	    }
//	    
//	    private List<SimpleDPofSR> loadDP() {
//	    	List<SimpleDPofSR> list = new ArrayList<>();
//	    	List<DP> dps = srdpdao.retrieveAll();
//	    	for (DP dp : dps) {
//	    		SimpleDPofSR simple = new SimpleDPofSR(dp);
//	    		list.add(simple);
//	    	}
//	    	return list;
//	    }
//	    
//	    private List<SimpleMNOSR> loadPageMno(){
//	    	List<SimpleMNOSR> list=new ArrayList<>();
//	    	List<MNO> mnos=mnodaoSr.retrieveAll();
//	    	for(MNO mno: mnos){
//	    		SimpleMNOSR simple=new SimpleMNOSR(mno);
//	    		list.add(simple);
//	    	}
//	    	return list;
//	    }
//	    
//	    private List<Keys> loadSR(){
//	    	KeysDAO srdao= new KeysDAO();
//	    	List<Keys> srs= srdao.retrieveAll();
//	    	return srs;
//	    }
//	    
//	    private void loadPage(Model model){
//	   	 	List<SR> knowSr =loadPageSR();
//		 	model.addAttribute("z", knowSr);
//		 	
//		 	List<Keys> srs= loadSR();
//		 	model.addAttribute("srs", srs);
//		 	
//		 	List<SimpleMNOSR> mnos= loadPageMno();
//		 	model.addAttribute("mnos",mnos);
//		 	
//		 	List<SimpleDPofSR> dps = loadDP();
//		 	model.addAttribute("dp", dps);
//	    }
}
