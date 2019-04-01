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
import com.lgg.nticxs.web.model.SimpleGroups;
import com.lgg.nticxs.web.model.SimpleMNODP;
import com.lgg.nticxs.web.model.SimpleSr;

@Controller
public class AmdDPController {
	
//	private KeysDAO dpdao= new KeysDAO();
//	
//	private MNODAO mnodaoDp=new MNODAO();
//	private com.movasim.sr.DAO.MNODAO mnodaoSr= new com.movasim.sr.DAO.MNODAO();
	
	private WSLogger logger = new WSLogger();
	
	/**------------------------------------------------ AMD DP ---------------------------------**/
	
	@GetMapping("home/amddp")
    public String search(Model model) {
//    	loadPage(model);
    	
    	logger.logger("INFO", "SM-WEB", "AMD DP", "", "", "search()", "", "", "", "The screen of AMD DP opens");
    	
        return "amddp";
    }
	
	
	/**					DPs AMD			**/
	
	@GetMapping("home/amddp/dp/edit/{dpid}")
    public String editDP(Model model, @PathVariable String dpid) {
//    	Keys dp= dpdao.retrieveById(dpid);
    	//model.addAttribute("dp",dp);
    	
    	logger.logger("INFO", "SM-WEB", "AMD-DP", "", "Edit SM-DP", "editDP()", "", "", "ID DP: " + dpid, "A screen opens for edit the SM-DP");
    	
    	return "dp_edit";
    }
    
    @PostMapping("home/amddp/dp/edit/{dpid}")
    public String editDPSave(Model model,
    		@PathVariable String dpid,
    		@RequestParam("action") String action,
    		@RequestParam("name") String name,
    		@RequestParam(name="description", required=false) String description
    		//@RequestParam("certificate") String certificate,
			//@RequestParam("privateKey") String privateKey,
			//@RequestParam("publicKey") String publicKey
    		)  {
//    	if (action.compareTo("save") == 0) {
//    		Keys dp=dpdao.retrieveById(dpid);
//    		if(dp!=null){
//    			
//    			Keys dpName=dpdao.retrieveByName(name);
//    			
//    			if(dpName!=null && !dpName.getId().equals(dpid)){
//    				model.addAttribute("msg1", "SM-DP name already exists");
//    				loadPage(model);
//    				
//    				logger.logger("WARNING", "SM-WEB", "AMD-DP", "", "Edit SM-DP Certificate", "editDPSave()", "", "", 
//    				"ID DP: " + dpid + ", Name DP: " + name + ", Description: " + description, 
//    				"The name you are trying to use is already in another SM-DP Certificate");
//    				
//    		    	return "amddp";
//    			}
//    			
//    			//dp.setCertificate(certificate);
//    			dp.setDescription(description);
//    			dp.setName(name);
//    			//dp.setPrivateKey(privateKey);
//    			//dp.setPublicKey(publicKey);
//    			dpdao.update(dp);
//    			model.addAttribute("msg1", "DP certificate update successfully completed");
//    			
//    			logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Edit SM-DP Certificate", "editDPSave()", "", "", 
//    			"ID DP: " + dpid + ", Name DP: " + name + ", Description: " + description,
//    			"Update SM-DP Certificate successfully");
//    		}
//    	}
//    	loadPage(model);
    	return "amddp";
    }
    
    @GetMapping("home/amddp/dp/add")
    public String addDP(Model model){
    	
    	logger.logger("INFO", "SM-WEB", "AMD-DP", "", "Add SM-DP certificate", "addDP()", "", "", "", "A screen opens where can add a new SM-DP certificate");
    	
    	return "dp_add";
    }    
    
    @PostMapping("home/amddp/dp/add")
    public String addDP2(Model model,@RequestParam("action") String action,
    		@RequestParam(name="name") String name,
    		@RequestParam("certificate") String certificate,
			@RequestParam("privateKey") String privateKey,
			@RequestParam("publicKey") String publicKey,
       		@RequestParam(name="description", required=false) String description){
//    	if (action.compareTo("save") == 0) {
//    		if(dpdao.existDp(name)){
//    			model.addAttribute("msg1", "SM-DP name already exists");
//    			
//    			logger.logger("WARNING", "SM-WEB", "AMD-DP", "", "Add SM-DP Certificate", "addDP2()", "", "", 
//        		"Name DP: " + name + ", Certificate: " + certificate + ", Private Key: " + privateKey + ", Public Key: " + publicKey + ", Description: " + description, 
//        		"The name you are trying to use is already in another SM-DP Certificate");
//    			
//    			return "dp_add";
//    		}
//    		Keys dp=new Keys();
//    		dp.setCertificate(certificate);
//			dp.setDescription(description);
//			dp.setName(name);
//			dp.setPrivateKey(privateKey);
//			dp.setPublicKey(publicKey);
//			dp.setDeleted(false);
//			dpdao.create(dp);
//			
//			logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Add SM-DP Certificate", "addDP2()", "", "", 
//			"Name DP: " + name + ", Certificate: " + certificate + ", Private Key: " + privateKey + ", Public Key: " + publicKey + ", Description: " + description, 
//			"Created SM-DP certificate successfully");
//			
//    		model.addAttribute("msg1", "SM-DP created successfully");
//    	}
//    	loadPage(model);
    	return "amddp";
    } 
    
    @GetMapping("/home/amddp/dp/delete/{dpid}")
    public String deleteDP(Model model,@PathVariable String dpid){
//    	dpdao.deleteDp(dpid);
//    	model.addAttribute("msg1", "Correct Deletion");
//    	
//    	logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Delete DP", "deleteDP()", "", "", "ID DP: " + dpid, "The SM-DP certificate was successfully deleted");
//    	
//    	loadPage(model);
    	return "amddp";
    }
    
    /**					End DPs AMD					**/
    
    /**					SRs of DP					**/
	
 	@GetMapping("home/amddp/sr/edit/{srid}")
    public String editSR(Model model,@PathVariable String srid) {
//    	SRDAO srdao=new SRDAO();
//    	SimpleSr srsimp;
//    	SR sr= srdao.retrieveById(srid);
//    	
//    	if(sr!=null){
//    		srsimp= new SimpleSr(sr);
//    		model.addAttribute("sr",srsimp);
//    	}   
    	
    	logger.logger("INFO", "SM-WEB", "AMD-DP", "", "Edit SM-SR", "editSR()", "", "",
    	"ID SM-SR: " + srid,
    	"A screen opens where can edit the SM-SR");
    	
    	return "sr_editDP";
    }
    
    @PostMapping("home/amddp/sr/edit/{srid}")
    public String editSRSave(Model model,
    		@PathVariable String srid,
    		@RequestParam("action") String action,
    		@RequestParam("name") String name,
    		@RequestParam("identifier") String identifier,
    		@RequestParam("url") String url,
    		@RequestParam("owner") String owner,
    		@RequestParam("vendor") String vendor,
    		@RequestParam("active") Boolean active){
//    	
//    	SRDAO srdao=new SRDAO();
//    	
//    	if (action.compareTo("save") == 0) {
//    		SR sr=srdao.retrieveById(srid);
//    		SR srVerified = srdao.retrieveByName(name);
//    		
//    		if (srVerified == null) { 
//    		
//	    		if(sr!=null){
//	    			sr.setActive(active);
//	    			sr.setName(name);
//	    			sr.setOwner(owner);
//	    			sr.setSmsrId(identifier);
//	    			Date date= new Date();
//	    			sr.setUpdatedAt(date);
//	    			sr.setUrl(url);
//	    			sr.setVendor(vendor);
//	    			srdao.update(sr);
//	    			
//	    			model.addAttribute("msg1", "SM-SR update successfully completed");
//	    			
//	    			logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Edit SM-SR", "editSRSave()", "", "", 
//	    			"ID SM-SR: " + srid + ", Name: " + name + ", Identifier: " + identifier + ", URL: " + url + ", Owner: " + owner + ", Vendor: " + vendor + ", Active: " + active, 
//	    			"Updated SM-SR successfully");
//	    		}
//	    		
//    		} else {
//    			model.addAttribute("msg1", "SM-SR name already exists");
//    			
//    			logger.logger("WARNING", "SM-WEB", "AMD-DP", "", "Edit SM-SR", "editSRSave()", "", "", 
//    			"ID SM-SR: " + srid + ", Name: " + name + ", Identifier: " + identifier + ", URL: " + url + ", Owner: " + owner + ", Vendor: " + vendor + ", Active: " + active,
//    			"The name you are trying to use is already in another SM-SR");
//    		}
//    	}
//    	
//    	loadPage(model);
    	return "amddp";
    }
    
    @GetMapping("home/amddp/sr/add")
    public String addSr(Model model){
    	
    	logger.logger("INFO", "SM-WEB", "AMD-DP", "", "Add SM-SR", "addSr()", "", "", "", "A screen opens where can add a new SM-SR");
    	
    	return "sr_addDP";
    }    
    
    @PostMapping("home/amddp/sr/add")
    public String addSrPost(Model model,@RequestParam("action") String action,
    		@RequestParam(name="name", required=false) String name,
    		@RequestParam(name="smsrid", required=false) String smsrid,
    		@RequestParam(name="url", required=false) String url,
    		@RequestParam(name="owner", required=false) String owner,
    		@RequestParam(name="vendor", required=false) String vendor,
    		@RequestParam(name="active", required=false) Boolean active){
    	
//    	SRDAO srdao=new SRDAO();
//    	
//    	if (action.compareTo("save") == 0) {
//    		
//    		SR srVerified = srdao.retrieveByName(name);
//    		
//    		if (srVerified == null) {
//	    		SR sr= new SR();
//	    		sr.setActive(active);
//	    		Date date=new Date();
//	    		sr.setCreatedAt(date);
//	    		sr.setDeleted(false);
//	    		sr.setName(name);
//	    		sr.setOwner(owner);
//	    		sr.setSmsrId(smsrid);
//	    		sr.setUpdatedAt(date);
//	    		sr.setUrl(url);
//	    		sr.setVendor(vendor);
//	    		srdao.create(sr);
//	    		model.addAttribute("msg1", "SR created successfully");
//	    		
//	    		logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Add SM-SR", "addSrPost()", "", "", 
//	    		"SR Name: " + name + "SM-SR ID: " + smsrid + ", URL: " + url + ", Owner: " + owner + ", Vendor: " + vendor + ", Active: " + active, 
//	    		"Created SM-SR successfully");
//	    		
//    		} else {
//    			model.addAttribute("msg1", "SM-SR name already exists");
//    			
//    			logger.logger("WARNING", "SM-WEB", "AMD-DP", "", "Add SM-SR", "addSrPost()", "", "", 
//    			"SR Name: " + name + "SM-SR ID: " + smsrid + ", URL: " + url + ", Owner: " + owner + ", Vendor: " + vendor + ", Active: " + active, 
//    			"The name you are trying to use is already in another SM-SR");
//    		}
//    	}
//    	
//    	loadPage(model);
    	return "amddp";
    } 
    
    @GetMapping("/home/amddp/sr/delete/{srid}")
    public String deleteSr(Model model,@PathVariable String srid){
//    	SRDAO srdao=new SRDAO();
//    	srdao.deleteSr(srid);
//    	model.addAttribute("msg1", "Correct Deletion");
//    	
//    	logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Delete SM-SR", "deleteSr()", "", "", "ID SM-SR: " + srid, "Deleted the SM-SR suceessfully");
//    	
//    	loadPage(model);
    	return "amddp";
    }
    
    		/**					end SR of DP		**/
    
    		/**			Template Classification			**/
    
    @PostMapping("home/amddp/template/category/add")
    public String addCategory(Model model, @RequestParam(name="namecat", required=false) String name){
//    	ClasificationIppDAO clasdao=new ClasificationIppDAO();    	
//    	List<ClasificationIpp> clasif= clasdao.retrieveByType("Category");
//    	if(!thisName(clasif, name)){
//    		if(name.equals("")){
//    			model.addAttribute("msg", "Error... Category already exists");
//    			
//    			logger.logger("WARNING", "SM-WEB", "AMD-DP", "", "Add category", "addCategory()", "", "", 
//    			"Name: " + name, 
//    			"Category already exists");
//    			
//    			loadPage(model);
//        		return "amddp";
//    		}
//    		ClasificationIpp cat=new ClasificationIpp();
//    		cat.setDeleted(false);
//    		cat.setName(name);
//    		cat.setType("Category");
//    		clasdao.create(cat);
//    	}else{
//    		model.addAttribute("msg1", "Error... Category already exists");
//    		
//    		logger.logger("WARNING", "SM-WEB", "AMD-DP", "", "Add category", "addCategory()", "", "", 
//        			"Name: " + name, 
//        			"Category already exists");
//    		
//    		return "amddp";
//    	}
//    	model.addAttribute("msg1", "Successful create of the category");
//    	
//    	logger.logger("DEBUG", "SM-WEB", "AMD_DP", "", "Add ", "addCategory()", "", "", 
//    	"Name: " + name, 
//    	"Successful create of the category");
//    	
//    	loadPage(model);
    	return "amddp";
    }
    
    @PostMapping("home/amddp/template/clase/add")
    public String addClass(Model model, @RequestParam(name="namecla", required=false) String name ){
//    	ClasificationIppDAO clasdao=new ClasificationIppDAO();
//    	List<ClasificationIpp> clasif= clasdao.retrieveByType("Class");
//    	if(!thisName(clasif, name)){
//    		ClasificationIpp cat=new ClasificationIpp();
//    		cat.setDeleted(false);
//    		cat.setName(name);
//    		cat.setType("Class");
//    		clasdao.create(cat);
//    	}else{
//    		model.addAttribute("msg1", "Error... Class already exists");
//    		
//    		logger.logger("WARNING", "SM-WEB", "AMD-DP", "", "Add class", "addClass()", "", "", 
//    		"Name: " + name,
//    		"Class already exists");
//    		
//    		loadPage(model);
//    		return "amddp";
//    	}
//    	model.addAttribute("msg1", "Successful create of the class");
//    	
//    	logger.logger("DEBUG", "SM-WEB", "AMD_DP", "", "Add ", "addClass()", "", "", 
//    	"Name: " + name, 
//    	"Successful create of the class");
//    	
//    	loadPage(model);
    	return "amddp";
    }
    
    @PostMapping("home/amddp/template/type/add")
    public String addType(Model model, @RequestParam(name="nametyp",required=false) String name){
//    	ClasificationIppDAO clasdao=new ClasificationIppDAO();
//    	List<ClasificationIpp> clasif= clasdao.retrieveByType("Type");
//    	if(!thisName(clasif, name)){
//    		ClasificationIpp cat=new ClasificationIpp();
//    		cat.setDeleted(false);
//    		cat.setName(name);
//    		cat.setType("Type");
//    		clasdao.create(cat);
//    	}else{
//    		model.addAttribute("msg1", "Error... Type already exists");
//    		
//    		logger.logger("WARNING", "SM-WEB", "AMD-DP", "", "Add type", "addType()", "", "", 
//    		"Name: " + name, 
//    		"Type already exists");
//    		
//    		loadPage(model);
//    		return "amddp";
//    	}
//    	model.addAttribute("msg1", "Successful create of the type");
//    	
//    	logger.logger("DEBUG", "SM-WEB", "AMD_DP", "", "Add ", "addType()", "", "", 
//    	"Name: " + name, 
//    	"Successful create of the type");
//    	
//    	loadPage(model);
    	return "amddp";
    }
    
    @PostMapping("home/amddp/template/clase/delete")
    public String deleteClass(Model model, @RequestParam(name="clases", required=false) String clase){
//    	ClasificationIppDAO clasdao=new ClasificationIppDAO();
//    	if(clase!=null){
//	    	ClasificationIpp clasif= clasdao.retrieveByName(clase, "Class");
//	    	clasdao.deleteClasificationIpp(clasif.getId());
//	    	model.addAttribute("msg1", "Successful delete of the class");
//	    	
//	    	logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Delete class", "deleteClass()", "", "", 
//	    	    	"Class: " + clase, 
//	    	    	"Successful delete of the class");
//    	}else{
//    		model.addAttribute("msg1", "Error... Did not choose an option");
//    		
//    		logger.logger("WARNING", "SM-WEB", "AMD-DP", "", "Delete class", "deleteCategory()", "", "", 
//    	    		"Class: " + clase, 
//    	    		"Error delete of the class, did not choose an option");
//    	}
//    	loadPage(model);
    	return "amddp";
    }
    
    @PostMapping("home/amddp/template/type/delete")
    public String deleteType(Model model, @RequestParam(name="types", required=false) String type){
//    	if(type!=null){
//	    	ClasificationIppDAO clasdao=new ClasificationIppDAO();
//	    	ClasificationIpp clasif= clasdao.retrieveByName(type,"Type");
//	    	clasdao.deleteClasificationIpp(clasif.getId());
//	    	model.addAttribute("msg1", "Successful delete of the type");
//	    	
//	    	logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Delete type", "deleteType()", "", "", 
//	    	    	"Type: " + type, 
//	    	    	"Successful delete of the type");
//    	}else{
//    		model.addAttribute("msg1", "Error... Did not choose an option");
//    		
//    		logger.logger("WARNING", "SM-WEB", "AMD-DP", "", "Delete type", "deleteType()", "", "", 
//    	    		"Type: " + type, 
//    	    		"Error delete of the type, did not choose an option");
//    	}
//    	loadPage(model);
    	return "amddp";
    }
    
    @PostMapping("home/amddp/template/category/delete")
    public String deleteCategory(Model model, @RequestParam(name="categorys", required=false) String category){
//    	if(category!=null){
//    	ClasificationIppDAO clasdao=new ClasificationIppDAO();
//    	ClasificationIpp clasif= clasdao.retrieveByName(category,"Category");
//    	clasdao.deleteClasificationIpp(clasif.getId());
//    	model.addAttribute("msg1", "Successful delete of the category");
//    	
//    	logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Delete category", "deleteCategory()", "", "", 
//    	"Category: " + category, 
//    	"Successful delete of the category");
//    	
//    	}else{
//    		model.addAttribute("msg1", "Error... Did not choose an option");
//    		
//    		logger.logger("WARNING", "SM-WEB", "AMD-DP", "", "Delete clasification IPP", "deleteCategory()", "", "", 
//    		"Category: " + category, 
//    		"Error delete of the category, did not choose an option");
//    	}
//    	loadPage(model);
    	return "amddp";
    }
    
/**			IPP Groups management			**/
    
    @GetMapping("home/amddp/group/edit/{groupid}")
    public String editGroup(Model model,@PathVariable String groupid){
//    	ClasificationIppDAO clasdao=new ClasificationIppDAO();
//    	ClasificationIpp groups=clasdao.retrieveById(groupid);
//    	SimpleGroups group=new SimpleGroups(groups);
//    	model.addAttribute("group", group);
//    	
//    	logger.logger("INFO", "SM-WEB", "AMD-DP", "", "Edit IPP Group", "editGroup", "", "", 
//    	"ID IPP group: " + groupid, 
//    	"A screen opens where can edit the IPP group");
//    	
    	return "group_edit";
    }
    			  
    @PostMapping("home/amddp/group/edit/{groupid}")
    public String editGroup2(Model model,
    		@PathVariable String groupid, 
    		@RequestParam("action") String action,
    		@RequestParam("name") String name,
    		@RequestParam("active") Boolean active,
    		@RequestParam(name="description", required=false) String description){
//    	
//    	ClasificationIppDAO clasdao=new ClasificationIppDAO();
//    	if (action.compareTo("save") == 0) {
//	    	ClasificationIpp group= clasdao.retrieveById(groupid);
//	    	group.setDescription(description);
//	    	group.setEnabled(active);
//	    	group.setName(name);
//    		model.addAttribute("msg1", "Correctly Update Group");
//    		
//    		logger.logger("INFO", "SM-WEB", "AMD-DP", "", "Edit IPP Group", "editGroup", "", "", 
//    		"ID IPP group: " + groupid + ", Name: " + name + ", Active: " + active + ", Description: " + description, 
//    		"Updated IPP group successfully");
//    		
//    		clasdao.update(group);
//	    	}
//    	loadPage(model);
    	return "amddp";
    }
    
    @GetMapping("home/amddp/group/delete/{groupid}")
    public String deleteGroup(Model model,@PathVariable String groupid){
//    	ClasificationIppDAO clasdao=new ClasificationIppDAO();
//    	clasdao.deleteClasificationIpp(groupid);
//    	model.addAttribute("msg1", "Correct Deletion");
//    	
//    	logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Delete IPP Group", "deleteGroup()", "", "", "ID IPP group: " + groupid, "Successfully deleted IPP Group");
//    	
//    	loadPage(model);
    	return "amddp";
    }
    
    @GetMapping("home/amddp/group/add")
    public String addGroup(Model model){
    	
    	logger.logger("INFO", "SM-WEB", "AMD-DP", "", "Add IPP Group", "deleteGroup()", "", "", "", "A screen opens where can add a new IPP group");
    	
    	return "group_add";
    }
    
    @PostMapping("home/amddp/group/add")
    public String addGroup2(Model model,
    		@RequestParam("action") String action,
    		@RequestParam(name="name", required=false) String name,
    		@RequestParam(name="description", required=false) String description,
    		@RequestParam(name="active", required=false) Boolean active){
//    	ClasificationIppDAO clasdao=new ClasificationIppDAO();
//    	if (action.compareTo("save") == 0) {
//    	ClasificationIpp group = new ClasificationIpp();
//    	group.setDeleted(false);
//    	group.setDescription(description);
//    	group.setEnabled(active);
//    	group.setName(name);
//    	group.setType("Group");
//    	clasdao.create(group);
//    	model.addAttribute("msg1", "Group created successfully");
//    	
//    	logger.logger("INFO", "SM-WEB", "AMD-DP", "", "Add IPP Group", "addGroup2()", "", "", 
//    	"IPP group name: " + name + ", Description: " + description + ", Active: " + active, 
//    	"IPP group created successfully");
//    	
//    	}
//    	loadPage(model);
    	return "amddp";
    }
    
    /**					end DPs AMD			**/
    
/**	   --------------------------   	MNO DP init	------------------------------		**/
    
    @GetMapping("home/amddp/mno/edit/{mnoid}")
    public String editMNO(Model model,@PathVariable String mnoid) {
//    	MNO mnoDp = mnodaoDp.retrieveById(mnoid);
//    	
//    	if(mnoDp != null){
//    		SimpleMNODP mno = new SimpleMNODP(mnoDp);
//    		model.addAttribute("mno",mno);
//    	}
//    	
//    	logger.logger("INFO", "SM-WEB", "AMD-DP", "", "Edit MNO", "editMNO()", "", "", 
//    			"ID MNO: " + mnoid, 
//    			"A screen opens where can edit the MNO");
//    	
    	return "mno_editDP";
    }
    
    @PostMapping("home/amddp/mno/edit/{mnoid}")
    public String editMNOSave(Model model,
    		@PathVariable String mnoid,
    		@RequestParam("action") String action,
    		@RequestParam(name="name", required=true) String name,
    		@RequestParam(name="url", required=false) String url,
    		@RequestParam(name="endpoint", required=false) String endpoint,
    		@RequestParam(name="smsc", required=false) String smsc){
//    	
//    	if (action.compareTo("save") == 0) {
//    		MNO mnoDp = mnodaoDp.retrieveById(mnoid);
//    		com.movasim.sr.model.MNO mnoSr = mnodaoSr.retrieveById(mnoDp.getId());
//    		
//    		if(mnoDp != null && mnoSr != null){
//    			mnoDp.setName(name);
//    			mnoDp.setSmsc(smsc);
//    			mnoDp.setUrl(url);
//    			mnoDp.setUrlEndpoint(endpoint);
//    			mnodaoDp.update(mnoDp);
//    			
//    			mnoSr.setName(name);
//    			mnoSr.setSmsc(smsc);
//    			mnoSr.setUrl(url);
//    			mnoSr.setUrlEndpoint(endpoint);
//    			mnodaoSr.update(mnoSr);
//    			
//    			model.addAttribute("msg1", "MNO edit successfully");
//    			
//    			logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Edit MNO", "editMNOSave()", "", "", 
//    			"ID MNO: " + mnoid + ", Name MNO: " + name + ", URL: " + url + ", Endpoint: " + endpoint + ", SMSC: " + smsc, 
//    			"MNO edit successfully");
//    		} else {
//    			
//    			model.addAttribute("msg1", "MNO edit error");
//    			
//    			logger.logger("WARNING", "SM-WEB", "AMD-DP", "", "Edit MNO", "editMNOSave()", "", "", 
//    			"ID MNO: " + mnoid + ", Name MNO: " + name + ", URL: " + url + ", Endpoint: " + endpoint + ", SMSC: " + smsc, 
//    			"MNO edit error");
//    		}
//    			
//    	}
//    	loadPage(model);
    	return "amddp";
    }
    
    @GetMapping("home/amddp/mno/delete/{mnoid}")
    public String deleteMNO(Model model,@PathVariable String mnoid) {
//    	MNO mnoDp= mnodaoDp.retrieveById(mnoid);
//    	com.movasim.sr.model.MNO mnoSr=mnodaoSr.retrieveByMnoId(mnoDp.getMnoid());
//    	
//    	if(mnoDp != null && mnoSr != null){
//    		mnoDp.setDeleted(true);
//    		mnodaoDp.update(mnoDp);
//    		
//    		mnoSr.setDeleted(true);
//    		mnodaoSr.update(mnoSr);
//    		
//        	model.addAttribute("msg1", "Correct Deletion");
//        	
//        	logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Delete MNO", "deleteMNO()", "", "", 
//        	"ID MNO: " + mnoid, 
//        	"Deleted MNO successfully");
//    	}else{
//        	model.addAttribute("msg1", "ERROR Deletion");
//        	
//        	logger.logger("ERROR", "SM-WEB", "AMD-DP", "", "Delete MNO", "deleteMNO()", "", "", 
//        	"ID MNO: " + mnoid, 
//        	"Error delete the MNO");
//    	}
//    	loadPage(model);
    	return "amddp";
    }
    
    @GetMapping("home/amddp/mno/add")
    public String addMNO(Model model){
    	
    	logger.logger("INFO", "SM-WEB", "AMD-DP", "", "Add MNO", "addMNO()", "", "", "", "A screen opens where can add a new MNO");
    	
    	return "mno_addDP";
    }  
    
    @PostMapping("home/amddp/mno/add")
    public String addMno(Model model,@RequestParam("action") String action,
    		@RequestParam(name="name", required=true) String name,
    		@RequestParam(name="mnoid", required=true) String mnoid,
    		@RequestParam(name="url", required=false) String url,
    		@RequestParam(name="endpoint", required=false) String endpoint,
    		@RequestParam(name="smsc", required=false) String smsc){
    	
//    	if (action.compareTo("save") == 0) {
//    		MNO mnoDp = new MNO();
//    		mnoDp.setDeleted(false);
//    		mnoDp.setMnoid(mnoid);
//    		mnoDp.setName(name);
//    		mnoDp.setSmsc(smsc);
//    		mnoDp.setUrl(url);
//    		mnoDp.setUrlEndpoint(endpoint);
//    		mnodaoDp.create(mnoDp);
//    		
//    		com.movasim.sr.model.MNO mnoSr = new com.movasim.sr.model.MNO();
//    		mnoSr.setId(mnoDp.getId());
//    		mnoSr.setDeleted(false);
//    		mnoSr.setMnoid(mnoid);
//    		mnoSr.setName(name);
//    		mnoSr.setSmsc(smsc);
//    		mnoSr.setUrl(url);
//    		mnoSr.setUrlEndpoint(endpoint);
//    		mnodaoSr.create(mnoSr);
//    		
//    		logger.logger("DEBUG", "SM-WEB", "AMD-DP", "", "Add MNO", "addMno()", "", "", 
//    		"ID MNO: " + mnoid + ", Name: " + name + ", URL: " + url + ", Endpoint: " + endpoint + ", SMSC" + smsc, 
//    		"MNO created successfully");
//    		
//    		model.addAttribute("msg1", "MNO created successfully");
//    	}
//    	
//    	loadPage(model);
    	return "amddp";
    } 
    
    /**	   --------------------------   	MNOS end	------------------------------		**/
    
    
//    /**			UTILS		**/
//    
//    
//    private void loadPage(Model model){
//   	 	List<SimpleSr> sr=loadPageSr();
//	 	model.addAttribute("sr", sr);
//	 	List<Keys> dps= loadDP();
//	 	model.addAttribute("dps",dps);
//	 	List<SimpleMNODP> mnos= loadPageMno();
//	 	model.addAttribute("mnos",mnos);
//	 	loadPageGC(model);
//    }
//    
//    private List<SimpleSr> loadPageSr(){
//    	SRDAO srdao=new SRDAO();
//    	List<SR> srall= srdao.retrieveAll();
//    	List<SimpleSr> sr= new ArrayList<>();
//    	for (SR srAux : srall){
//    		SimpleSr simplesr= new SimpleSr(srAux);
//    		sr.add(simplesr);
//    	}
//    	return sr;
//    }
//    
//    private List<Keys> loadDP(){
//    	KeysDAO dpdao= new KeysDAO();
//    	List<Keys> dps= dpdao.retrieveAll();
//    	return dps;
//    }
//    
//    private List<SimpleMNODP> loadPageMno(){
//    	List<SimpleMNODP> list=new ArrayList<>();
//    	List<MNO> mnos=mnodaoDp.retrieveAll();
//    	for(MNO mno: mnos){
//    		SimpleMNODP simple=new SimpleMNODP(mno);
//    		list.add(simple);
//    	}
//    	return list;
//    }
//    
//    public boolean thisName(List<ClasificationIpp> clasification, String name){
//    	for(ClasificationIpp clasif : clasification){
//    		if(clasif.getName().equals(name)){
//    			return true;
//    		}
//    	}
//    	return false;
//    }
//    
//    public void loadPageGC(Model model){
//    	KeysDAO dpdao = new KeysDAO();
//        ClasificationIppDAO cladao= new ClasificationIppDAO();
//        Keys dp = dpdao.retrieveFirst();
//        List<ClasificationIpp> category= cladao.retrieveByType("Category");
//        List<ClasificationIpp> type= cladao.retrieveByType("Type");
//        List<ClasificationIpp> clase= cladao.retrieveByType("Class");
//        List<ClasificationIpp> group= cladao.retrieveByType("Group");
//        List<SimpleGroups> groups = new ArrayList<>();
//        for(ClasificationIpp aux: group){
//        	SimpleGroups grou= new SimpleGroups(aux);
//        	if(!grou.getDeleted())
//        		groups.add(grou);
//        }
//        model.addAttribute("category", category);
//        model.addAttribute("clase", clase);
//        model.addAttribute("type", type);
//        model.addAttribute("dp", dp);
//        model.addAttribute("group", groups);
//    }
}
