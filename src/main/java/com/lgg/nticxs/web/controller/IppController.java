package com.lgg.nticxs.web.controller;

import com.lgg.nticxs.web.utils.WSLogger;
import com.lgg.nticxs.web.model.*;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by movasim on 12/12/16.
 */
@Controller
public class IppController {
	private static WSLogger logger = new WSLogger();

    @GetMapping("home/ipp/generation")
    public String generation(Model model) {       
        logger.logger("INFO", "SM-WEB", "IPP", "", "", "generation()", "", "", "", "The screen of IPP generation opens");
        
        return "ipp_generation";
    }

    @SuppressWarnings("unlikely-arg-type")
	@PostMapping("home/ipp/generation")
    public String generateIpp(Model model, 
    		@RequestParam("listTemplate") String listTemplate,
    		@RequestParam("listMno") String listMno, 
    		@RequestParam("listGroup") String listGroup,  
    		@RequestParam("filter") String filter, 
    		@RequestParam(name="quantity", required=false) Integer quantity,
    		@RequestParam(name="available", required=false, defaultValue = "false") Boolean available,
    		@RequestParam(name="paramGenerateFor", required=false) String paramGenerateFor,
    		@RequestParam(name="searchTextStart", required=false) String searchTextStart,
    		@RequestParam(name="searchTextEnd", required=false) String searchTextEnd,
    		@RequestParam(name="paramGenerateOne", required=false) String paramGenerateOne,
    		@RequestParam(name="description", required=false) String description,
    		@RequestParam(name="searchTextParticular", required=false) String searchTextParticular) {
    	
        if(listTemplate.compareTo("none")!=0) {
//            TemplateIppDAO templateDao = new TemplateIppDAO();
//            TemplateIpp template = templateDao.retrieveByName(listTemplate);
//            PersonalisationIppDAO perDao = new PersonalisationIppDAO();
//            List<PersonalisationIpp> infoPersonalization = perDao.retrieveByState();
            
//            if (infoPersonalization != null) {
//                PresetCommandDAO presetDao = new PresetCommandDAO();
//                
//                if (filter.compareTo("generate") == 0) {
//                    Integer cant;
//                    
//                    if (available) {
//                        cant = infoPersonalization.size();
//                        
//                        for (int i = 0; i < cant; i++) {
//                            PersonalisationIpp info = infoPersonalization.get(i);
//                            info.setAvailable(false); 
//                            
//                            if (description!=null) {
//                            	info.setName(description);
//                            	perDao.update(info);
//                            }
//                            
//                            if(presetDao.retrieveByICCID(info.getIccid())==null){
//                            
//                            	if (listGroup.equals("none")) {
//                                presetDao.create(PresetCommand.generatePresetCommand(template, info, " ",listMno));
//                            	
//                            	} else {
//                            		presetDao.create(PresetCommand.generatePresetCommand(template, info, listGroup,listMno));
//                            	}
//                            	
//                            } else {
//                            	 
//                            	 if (listGroup.equals("none")) {
//                                     presetDao.update(PresetCommand.generatePresetCommand(template, info, " ",listMno));
//                            	 } else {
//                                     presetDao.update(PresetCommand.generatePresetCommand(template, info, listGroup,listMno));
//                            	 }
//                            }
//                        }
//                        
//                        loadPageIppGeneration(model);
//                        model.addAttribute("msg1", "Successful Profile Generation");
//                        
//                        logger.logger("DEBUG", "SM-WEB", "IPP", "", "Generation IPP", "generateIpp()", "", "",
//                        		"List template: " + listTemplate + ", List MNO: " + listMno + ", List Group: " + listGroup + ", Description: " + description
//                        		+ ", Filter: " + filter + ", Quantity: " + quantity + ", Available: " + available, 
//                        		"Successful profile generation");
//                        
//                        return "ipp_generation";
//                   
//                    } else {
//                        cant = quantity;
//                        
//                        if (quantity > infoPersonalization.size()) {
//                            model.addAttribute("msg1", "\n" +
//                                    "Error, the required value is greater than the amount of personalization information available,"
//                                    + " I could generate at most " + infoPersonalization.size() + " new profiles");
//                            
//                            logger.logger("ERROR", "SM-WEB", "IPP", "", "Generation IPP", "generateIpp()", "", "",
//                            		"List template: " + listTemplate + ", List MNO: " + listMno + ", List Group: " + listGroup + ", Description: " + description
//                            		+ ", Filter: " + filter + ", Quantity: " + quantity + ", Available: " + available, 
//                            		"Error, the required value is greater than the amount of personalization information available,"
//                                    + " I could generate at most " + infoPersonalization.size() + " new profiles");
//                            
//                            return "ipp_generation";
//                        } else {
//                            for (int i = 0; i < cant; i++) {
//                                PersonalisationIpp info = infoPersonalization.get(i);
//                                info.setAvailable(false);
//                                if (description!=null)
//                                	info.setName(description);
//                                perDao.update(info);
//                                if (listGroup.equals("none"))
//                                    presetDao.create(PresetCommand.generatePresetCommand(template, info, " ",listMno));
//                                else
//                                    presetDao.create(PresetCommand.generatePresetCommand(template, info, listGroup,listMno));
//                            }
//                            
//                            loadPageIppGeneration(model);
//                            model.addAttribute("msg1", "Successful Profile Generation " + quantity + " Ipp");
//                            
//                            logger.logger("DEBUG", "SM-WEB", "IPP", "", "Generation IPP", "generateIpp()", "", "",
//                            		"List template: " + listTemplate + ", List MNO: " + listMno + ", List Group: " + listGroup + ", Description: " + description
//                            		+ ", Filter: " + filter + ", Quantity: " + quantity + ", Available: " + available, 
//                            		"Successful Profile Generation " + quantity + " Ipp");
//                            
//                            return "ipp_generation";
//                        }
//                    }
//                    
//                } else if (filter.compareTo("generate-for") == 0) {
//                    loadPageIppGeneration(model);
//                    model.addAttribute("msg1", "Successful Profile Generation " + quantity + " Ipp");
//                    
//                    logger.logger("DEBUG", "SM-WEB", "IPP", "", "Generation IPP", "generateIpp()", "", "",
//                    		"List template: " + listTemplate + ", List MNO: " + listMno + ", List Group: " + listGroup + ", Description: " + description
//                    		+ ", Filter: " + filter + ", Quantity: " + quantity + ", Available: " + available, 
//                    		"Successful Profile Generation " + quantity + " Ipp");
//                    
//                    return "ipp_generation";
//                
//                } else if (filter.compareTo("generate-one") == 0) {
//                	PersonalisationIpp data=new PersonalisationIpp();
//                	
//                	if(paramGenerateOne.equals("iccid")) {
//                		PersonalisationIpp info= perDao.retrieveByIccid(searchTextParticular);
//                		if(info!=null){
//                			if(info.getAvailable().equals("true")){
//	                			info.setAvailable(false);
//	                			if (description!=null)
//	                            	info.setName(description);
//	                            perDao.update(info);
//	                            data=info;
//                			}else{
//                				loadPageIppGeneration(model);
//                                model.addAttribute("msg1", "Error personalization information not found");
//                                
//                                logger.logger("ERROR", "SM-WEB", "IPP", "", "Generation IPP", "generateIpp()", "", "",
//                                		"List template: " + listTemplate + ", List MNO: " + listMno + ", List Group: " + listGroup + ", Description: " + description
//                                		+ ", Filter: " + filter + ", Quantity: " + quantity + ", Available: " + available, 
//                                		"Error personalization information not found");
//                                
//                                return "ipp_generation";
//                			}
//                		}else{
//                			loadPageIppGeneration(model);
//                            model.addAttribute("msg1", "Error personalization information not found");
//                            
//                            logger.logger("ERROR", "SM-WEB", "IPP", "", "Generation IPP", "generateIpp()", "", "",
//                            		"List template: " + listTemplate + ", List MNO: " + listMno + ", List Group: " + listGroup + ", Description: " + description
//                            		+ ", Filter: " + filter + ", Quantity: " + quantity + ", Available: " + available, 
//                            		"Error personalization information not found");
//                            
//                            return "ipp_generation";
//                        }
//                	}
//                	
//                	if(paramGenerateOne.equals("imsi")) {
//                		PersonalisationIpp info= perDao.retrieveByIccid(searchTextParticular);
//                		if(info!=null ){
//                			if(info.getAvailable().equals("true")){
//	                			info.setAvailable(false);
//	                			if (description!=null)
//	                            	info.setName(description);
//	                            perDao.update(info);
//	                            data=info;
//                			}else{
//                				loadPageIppGeneration(model);
//                                model.addAttribute("msg1", "Error personalization information not found");
//                                
//                                logger.logger("ERROR", "SM-WEB", "IPP", "", "Generation IPP", "generateIpp()", "", "",
//                                		"List template: " + listTemplate + ", List MNO: " + listMno + ", List Group: " + listGroup + ", Description: " + description
//                                		+ ", Filter: " + filter + ", Quantity: " + quantity + ", Available: " + available, 
//                                		"Error personalization information not found");
//                                
//                                return "ipp_generation";
//                			}
//                		}else{
//                			loadPageIppGeneration(model);
//                            model.addAttribute("msg1", "Error personalization information not found");
//                            
//                            logger.logger("ERROR", "SM-WEB", "IPP", "", "Generation IPP", "generateIpp()", "", "",
//                            		"List template: " + listTemplate + ", List MNO: " + listMno + ", List Group: " + listGroup + ", Description: " + description
//                            		+ ", Filter: " + filter + ", Quantity: " + quantity + ", Available: " + available, 
//                            		"Error personalization information not found");
//                            
//                            return "ipp_generation";
//                		}
//                	}
//                	
//                	if (paramGenerateOne.equals("none")){
//                		loadPageIppGeneration(model);
//                        model.addAttribute("msg1", "Error list empty");
//                        
//                        logger.logger("ERROR", "SM-WEB", "IPP", "", "Generation IPP", "generateIpp()", "", "",
//                        		"List template: " + listTemplate + ", List MNO: " + listMno + ", List Group: " + listGroup + ", Description: " + description
//                        		+ ", Filter: " + filter + ", Quantity: " + quantity + ", Available: " + available, 
//                        		"Error list empty");
//                        
//                        return "ipp_generation";
//                	}
//                	
//					if (listGroup.equals("none")) {
//						presetDao.create(PresetCommand.generatePresetCommand(template, data, " ", listMno));
//
//					} else {
//						presetDao.create(PresetCommand.generatePresetCommand(template, data, listGroup, listMno));
//						loadPageIppGeneration(model);
//						model.addAttribute("msg1", "Successful Profile Generation ");
//						
//						logger.logger("DEBUG", "SM-WEB", "IPP", "", "Generation IPP", "generateIpp()", "", "",
//                        		"List template: " + listTemplate + ", List MNO: " + listMno + ", List Group: " + listGroup + ", Description: " + description
//                        		+ ", Filter: " + filter + ", Quantity: " + quantity + ", Available: " + available, 
//                        		"Successful Profile Generation");
//						
//						return "ipp_generation";
//					}
//                }
//            }
            
            //vuelva a cargar la web
//            loadPageIppGeneration(model);
            model.addAttribute("msg1", "There is no personalization information available" );
            
            logger.logger("ERROR", "SM-WEB", "IPP", "", "Generation IPP", "generateIpp()", "", "",
            		"List template: " + listTemplate + ", List MNO: " + listMno + ", List Group: " + listGroup + ", Description: " + description
            		+ ", Filter: " + filter + ", Quantity: " + quantity + ", Available: " + available, 
            		"There is no personalization information available");
            
            return "ipp_generation";
        }
        
        //vuelva a cargar la web
//        loadPageIppGeneration(model);
        model.addAttribute("msg1", "You failed to template empty" );
        
        logger.logger("ERROR", "SM-WEB", "IPP", "", "Generation IPP", "generateIpp()", "", "",
        		"List template: " + listTemplate + ", List MNO: " + listMno + ", List Group: " + listGroup + ", Description: " + description
        		+ ", Filter: " + filter + ", Quantity: " + quantity + ", Available: " + available, 
        		"You failed to template empty");
        
        return "ipp_generation";
    }


    @GetMapping("home/ipp/explorer")
    public String search(Model model) {
    	logger.logger("INFO", "SM-WEB", "IPP", "", "", "search()", "", "", "", "The screen of IPP explorer opens");
    	
        return "ipp_explorer";
    }

    @GetMapping("home/ipp/results")
    public String results() {
    	logger.logger("INFO", "SM-WEB", "IPP", "", "", "results()", "", "", "", "The screen of IPP explorer opens");
    	
        return "ipp_explorer";
    }

    @PostMapping("home/ipp/results")
    public String result(Model model, Authentication auth,
    		@RequestParam(name="category", required=false) String category, 
    		@RequestParam(name="clase", required=false) String clase, 
    		@RequestParam(name="type",required=false) String type,
    		@RequestParam(name="filter", required=false) String filter) {
        
//    	User user = userdao.retrieveByName(auth.getName());
//    	PresetCommandDAO presetDAO = new PresetCommandDAO();
//        List<PresetCommand> preset = null;
//        
//        if((category==null&&clase==null&type==null)||(category.compareTo(IppSearch.SEARCH_BY_CATEGORY)==0 && clase.compareTo(IppSearch.SEARCH_BY_CLASE)==0 
//        		&& type.compareTo(IppSearch.SEARCH_BY_TYPE)==0)){
//            //sin parametro solo filtrar por radio button
//        	preset=presetDAO.retrieve();
//        	
//        	logger.logger("DEBUG", "SM-WEB", "IPP", "", "Search filtered of IPP", "result()", "", "", 
//        			"Category: " + category + ", Class: " + clase + ", Type: " + type + ", Filter: " + filter,
//        			"Amount of values: " + preset.size());
//        	
//            HashMap<String,String> eids= presetCommandsInstalled(preset);
//            preset =filter(preset,filter);
//            
//            logger.logger("DEBUG", "SM-WEB", "IPP", "", "Search filtered of IPP", "result()", "", "", 
//            		"Category: " + category + ", Class: " + clase + ", Type: " + type + ", Filter: " + filter, 
//            		"Amount of values filtered");
//            
//            List<SimpleIpp> ipps=new ArrayList<>();
//            String eid="";
//            Map<String, Boolean> listMapEdit = new HashMap<String, Boolean>();
//            
//            for(PresetCommand aux: preset){
//                eid=eids.get(aux.getIccid());
//                SimpleIpp simpleIpp= new SimpleIpp(aux);
//                simpleIpp.setEid(eid);
//                
//                if (aux.getGroup()==null) {
//                    simpleIpp.setGroup("");
//                } else {
//                	simpleIpp.setGroup(aux.getGroup());
//                }
//                
//                if(!aux.isDelete()) {
//                	loadPresentCommands(user, aux, ipps, simpleIpp);
//                	loadEditParameterFilter(user, aux, listMapEdit);
//                }
//            }
//            
//            logger.logger("INFO", "SM-WEB", "IPP", "", "Search filtered of IPP", "result()", "", "", 
//            		"Category: " + category + ", Class: " + clase + ", Type: " + type + ", Filter: " + filter, 
//            		"Quantity of values to be put " + ipps.size());
//            
//            model.addAttribute("listMapEdit", listMapEdit);
//            model.addAttribute("ippFound", ipps);
//            
//            logger.logger("INFO", "SM-WEB", "IPP", "", "Search filtered of IPP", "result()", "", "", 
//            		"Category: " + category + ", Class: " + clase + ", Type: " + type + ", Filter: " + filter, 
//            		"A screen opens with results of the search");
//            
//            return "ipp_results";
//            
//        }else if (category.compareTo(IppSearch.SEARCH_BY_CATEGORY) == 0) {
            //caso en el que no se establezca la categoria
//            if(clase.compareTo(IppSearch.SEARCH_BY_CLASE)==0){
//                //caso en el que categoria, clase = 0  y tipo!=0
//                preset = presetDAO.retrieveByOneParam("type",type);
//            }else{
//                if (type.compareTo(IppSearch.SEARCH_BY_TYPE) == 0){
//                    //caso en el que el  categoria, tipo = 0  y clase!=0
//                    preset=presetDAO.retrieveByOneParam("clase",clase);
//                }else{
//                    //caso en el que la categoria = 0 y clase y tipo !=0
//                    preset=presetDAO.retrieveByTwoParam("clase",clase,"type",type);
//                }
//            }
//        } else {
//            if(clase.compareTo(IppSearch.SEARCH_BY_CLASE)==0){
//                if(type.compareTo(IppSearch.SEARCH_BY_TYPE) == 0){
//                    //caso clase, tipo = 0 y categoria !=0
//                    preset=presetDAO.retrieveByOneParam("category",category);
//                }
//                else{
//                    //caso clase = 0 y categoria, tipo !=0
//                    preset=presetDAO.retrieveByTwoParam("category",category,"type",type);
//                }
//            }
//            else{
//                if(type.compareTo(IppSearch.SEARCH_BY_TYPE) == 0){
//                    //caso tipo=0 y categoria, clase !=0
//                    preset=presetDAO.retrieveByTwoParam("category",category,"clase",clase);
//                }
//                else{
//                    //caso categoria, tipo, clase !=0
//                    preset=presetDAO.retrieveByAll("category",category,"clase",clase,"type",type);
//                }
//            }
//        }
//        
//        preset =filter(preset,filter);
//        
//        List<SimpleIpp> ipps = loadPresentCommandsFilter(user, preset);
//        model.addAttribute("ippFound", ipps);
//        
//        Map<String, Boolean> listMapEdit = loadEditParameter(user, preset);
//        model.addAttribute("listMapEdit", listMapEdit);
//        
//        logger.logger("INFO", "SM-WEB", "IPP", "", "Search filtered of IPP", "result()", "", "", 
//        		"Category: " + category + ", Class: " + clase + ", Type: " + type + ", Filter: " + filter, 
//        		"A screen opens with results of the search");
//        
        return "ipp_results";
    }

	@PostMapping("home/ipp/results/subscription")
    public String results(Model model, @RequestParam("searchText") String searchText, @RequestParam("searchBy") String searchBy) {
//        PresetCommandDAO presetCommandDAO=new PresetCommandDAO();
//        List<PresetCommand> presetCommands=presetCommandDAO.retrieve();
//        HashMap<String, String> presetCommandsInstalled = new HashMap<>();
//        presetCommandsInstalled= presetCommandsInstalled(presetCommands);
//
//        List<SimpleIpp> ipps=new ArrayList<>();
//        for(PresetCommand aux: presetCommands){
//            SimpleIpp simpleIpp= new SimpleIpp(aux);
//            if(presetCommandsInstalled.containsKey(aux.getIccid())) {
//                simpleIpp.setEid(presetCommandsInstalled.get(aux.getIccid()));
//            }
//
//			if (searchBy.equals("default")) {
//				ipps.add(simpleIpp);
//			}
//
//			if (searchBy.equals("name")) {
//				if (aux.getCardeid().contains(searchText) && !searchText.equals("")) {
//					ipps.add(simpleIpp);
//				}
//			}
//
//			if (searchBy.equals("category")) {
//				if (aux.getMsisdn().contains(searchText) && !searchText.equals("")) {
//					ipps.add(simpleIpp);
//				}
//			}
//        }
//        
//        model.addAttribute("ippFound", ipps);
//        
//        logger.logger("INFO", "SM-WEB", "IPP", "", "Subscription search of IPP", "results()", "", "", 
//        		"Search Text: " + searchText + ", Search by: " + searchBy, 
//        		"A screen opens with results of the search");
//        
        return "ipp_results";
    }

    @PostMapping("home/template/edit/")
    public String editIpp(Model model, @RequestParam("searchText") String searchText, @RequestParam("searchBy") String searchBy) {
//        PresetCommandDAO presetCommandDAO=new PresetCommandDAO();
//        List<PresetCommand> presetCommands=presetCommandDAO.retrieve();
//        HashMap<String, String> presetCommandsInstalled = new HashMap<>();
//        presetCommandsInstalled= presetCommandsInstalled(presetCommands);
//
//        List<SimpleIpp> ipps=new ArrayList<>();
//        for(PresetCommand aux: presetCommands){
//            SimpleIpp simpleIpp= new SimpleIpp(aux);
//            if(presetCommandsInstalled.containsKey(aux.getIccid()))
//                simpleIpp.setEid(presetCommandsInstalled.get(aux.getIccid()));
//            ipps.add(simpleIpp);
//        }
//        
//        model.addAttribute("ippFound", ipps);
        return "ipp_results";
    }

    				/*				LOTS				*/
    
    @GetMapping("home/ipp/{action}/lots")
    public String loadIPP(Model model,
    		@PathVariable String action) {
//    	
//    	PresetCommandDAO presetDAO = new PresetCommandDAO();
//    	List<PresetCommand> preset = presetDAO.retrieve();
//        List<SimpleIpp> ipps=new ArrayList<>();
//        for(PresetCommand aux: preset){
//            SimpleIpp simpleIpp= new SimpleIpp(aux);
//            ipps.add(simpleIpp);
//        }
//    	
//    	if (action.equals("add")) {
//    		model.addAttribute("action", "add");
//    		model.addAttribute("ippFound", ipps);
//    		
//    		logger.logger("INFO", "SM-WEB", "IPP", "", "Show IPPs", "loadIPP()", "", "", "", "Shows all IPPs for add lots");
//    		
//    		return "ipp_lot";
//    	}
//    	
//    	if (action.equals("delete")) {
//    		model.addAttribute("action", "delete");
//    		model.addAttribute("ippFound", ipps);
//    		
//    		logger.logger("INFO", "SM-WEB", "IPP", "", "Show IPPs", "loadIPP()", "", "", "", "Shows all IPPs for delete their lots");
//    		
//    		return "ipp_lot";
//    	}
//    	
//    	if (action.equals("view")) {
//    		model.addAttribute("action", "view");
//    		model.addAttribute("ippFound", ipps);
//    		
//    		logger.logger("INFO", "SM-WEB", "IPP", "", "Show IPPs", "loadIPP()", "", "", "", "Shows all IPPs for view their lots");
//    		
//    		return "ipp_lot";
//    	}
    	
    	return "ipp_explorer";
    }
    
    @GetMapping("home/ipp/add/lots/{id}")
    public String addLotsToIppGet(Model model, 
    		@PathVariable String id) {
    	    	model.addAttribute("id", id);
    	
    	logger.logger("INFO", "SM-WEB", "IPP", "", "Add lot", "addLotsToIppGet()", "", "", 
    			"ID IPP: " + id, 
    			"A screen opens where shows all lots for add to a specific IPP");
    	
    	return "ipp_lots_add";
    }
    
	@PostMapping("home/ipp/add/lots/{id}")
	public String addLotsToIppPost(Model model, HttpServletRequest request, 
			@PathVariable String id,
			@RequestParam(name = "action") String action) {

//		if (action.compareTo("save") == 0) {
//			PresetCommand presetCommand = presetcommanddao.retrieveById(id);
//
//			List<String> listOfMsgEdit = new ArrayList<>();
//			List<String> listOfMsgView = new ArrayList<>();
//
//			List<String> listOfMsgEditRepeated = new ArrayList<>();
//			List<String> listOfMsgViewRepeated = new ArrayList<>();
//
//			List<Lot> listLots = lotdao.retrieveAll();
//
//			for (Lot lot : listLots) {
//
//				if (request.getParameter("edit_" + lot.getNameOfLote()).equals("true")) {
//
//					Boolean repeated = false;
//					Boolean repeatedInView = false;
//					
//					for (int i = 0; i < presetCommand.getListOfEditLots().size(); i++) {
//						if (presetCommand.getListOfEditLots().get(i).equals(lot.getNameOfLote())) {
//							repeated = true;
//							listOfMsgEditRepeated.add("The lot " + lot.getNameOfLote() + " for edit already this has the IPP");
//							
//							logger.logger("WARNING", "SM-WEB", "IPP", "", "Add lot", "addLotsToIppPost()", "", "", 
//									"ID IPP: " + id + "Name Lot: " + lot.getNameOfLote(), 
//									"This IPP has the lot what are you trying to add");
//							
//							break;
//						}
//					}
//					
//					for (int i = 0; i < presetCommand.getListOfViewLots().size(); i++) {
//						if(presetCommand.getListOfViewLots().get(i).equals(lot.getNameOfLote())) {
//							repeatedInView = true;
//							break;
//						}
//					}
//
//					if (!repeated) {
//						presetCommand.getListOfEditLots().add(lot.getNameOfLote());
//						
//						if(!repeatedInView) {
//							presetCommand.getListOfViewLots().add(lot.getNameOfLote());
//						}
//						
//						presetcommanddao.update(presetCommand);
//						listOfMsgEdit.add("Add lot " + lot.getNameOfLote() + " for edit to the IPP");
//						
//						logger.logger("DEBUG", "SM-WEB", "IPP", "", "Add lot", "addLotsToIppPost()", "", "", 
//								"ID IPP: " + id + "Name Lot: " + lot.getNameOfLote(), 
//								"Successfully added the lot for edit to IPP");
//					}
//				}
//
//				if (request.getParameter("view_" + lot.getNameOfLote()).equals("true")) {
//
//					Boolean repeated = false;
//
//					for (int i = 0; i < presetCommand.getListOfViewLots().size(); i++) {
//						if (presetCommand.getListOfViewLots().get(i).equals(lot.getNameOfLote())) {
//							repeated = true;
//							listOfMsgViewRepeated.add("The lot " + lot.getNameOfLote() + " for view already this has the IPP");
//							
//							logger.logger("WARNING", "SM-WEB", "IPP", "", "Add lot", "addLotsToIppPost()", "", "", 
//									"ID IPP: " + id + "Name Lot: " + lot.getNameOfLote(), 
//									"This IPP has the lot what are you trying to add");
//							
//							break;
//						}
//					}
//
//					if (!repeated) {
//						presetCommand.getListOfViewLots().add(lot.getNameOfLote());
//						presetcommanddao.update(presetCommand);
//						listOfMsgView.add("Add lot " + lot.getNameOfLote() + " for view to the IPP");
//						
//						logger.logger("DEBUG", "SM-WEB", "IPP", "", "Add lot", "addLotsToIppPost()", "", "", 
//								"ID IPP: " + id + "Name Lot: " + lot.getNameOfLote(), 
//								"Successfully added the lot for view to IPP");
//					}
//				}
//			}
//			
//			model.addAttribute("msgEdit", listOfMsgEdit);
//        	model.addAttribute("msgView", listOfMsgView);
//        	
//			model.addAttribute("msgEditRepeated", listOfMsgEditRepeated);
//			model.addAttribute("msgViewRepeated", listOfMsgViewRepeated);
//			
//			return "ipp_explorer";
//		}

		return "redirect:/home/ipp/results";
	}
    
    @GetMapping("home/ipp/delete/lots/{id}")
    public String deleteLotsToIppGet(Model model, 
    		@PathVariable String id) {
    	
    	model.addAttribute("id", id);
    	
    	logger.logger("INFO", "SM-WEB", "IPP", "", "Delete lot", "deleteLotsToIppGet()", "", "", 
    			"ID IPP: " + id, 
    			"A screen opens where shows all lots for delete to a specific IPP");
    	
    	return "ipp_lots_delete";
    }
    
    @PostMapping("home/ipp/delete/lots/{id}")
    public String deleteLotsToIppPost(Model model, HttpServletRequest request,
    		@PathVariable String id,
    		@RequestParam (name = "action") String action) {
//    	if(action.compareTo("save") == 0) {
//    		PresetCommand presetCommand = presetcommanddao.retrieveById(id);
//    		
//    		List<Lot> listLots = lotdao.retrieveAll();
//    		
//    		List<String> deleteLotsEdit = new ArrayList<>();
//        	List<String> deleteLotsView = new ArrayList<>();
//        	
//        	List<String> neverDeleteEdit = new ArrayList<>();
//        	List<String> neverDeleteView = new ArrayList<>();
//        	
//        	for(Lot lot : listLots) {
//        		
//        		if(request.getParameter("edit_" + lot.getNameOfLote()).equals("true")) {
//        			Boolean delete = false;
//        			
//        			for (int i = 0; i < presetCommand.getListOfEditLots().size(); i++) {
//        				if (presetCommand.getListOfEditLots().get(i).equals(lot.getNameOfLote())) {
//        					presetCommand.getListOfEditLots().remove(i);
//        					deleteLotsEdit.add("Delete lot " + lot.getNameOfLote() + " for edit in the IPP");
//        					delete = true;
//        					
//        					logger.logger("DEBUG", "SM-WEB", "IPP", "", "Delete lot", "deleteLotsToIppPost()", "", "", 
//        							"ID IPP: " + id + ", Name Lot: " + lot.getNameOfLote(), 
//        							"Successfully deleted the lot for edit in the template");
//        					
//        					break;
//        				}
//        			}
//        			
//        			for (int i = 0; i < presetCommand.getListOfViewLots().size(); i++) {
//        				if(presetCommand.getListOfViewLots().get(i).equals(lot.getNameOfLote()) && delete) {
//        					presetCommand.getListOfViewLots().remove(i);
//        					deleteLotsView.add("Delete lot " + lot.getNameOfLote() + " for view in the IPP");
//        					
//        					logger.logger("DEBUG", "SM-WEB", "IPP", "", "Delete lot", "deleteLotsToIppPost()", "", "", 
//        							"ID IPP: " + id + ", Name Lot: " + lot.getNameOfLote(), 
//        							"Successfully deleted the lot for view in the template");
//        				}
//        			}
//        				
//					if (delete) {
//						presetcommanddao.update(presetCommand);
//					} else {
//						neverDeleteEdit.add("Impossible delete lot " + lot.getNameOfLote() + " for edit, the IPP dont have this lot");
//						
//						logger.logger("WARNING", "SM-WEB", "IPP", "", "Delete lot", "deleteLotsToIppPost()", "", "", 
//								"ID IPP: " + id + ", Name Lot: " + lot.getNameOfLote(), 
//								"Could not delete the lot for edit because the template hasn't this lot");
//					}
//        		}
//        		
//        		if(request.getParameter("view_" + lot.getNameOfLote()).equals("true")) {
//        			Boolean inEdit = false;
//        			
//        			for (int i = 0; i < presetCommand.getListOfEditLots().size(); i++) {
//        				if(presetCommand.getListOfEditLots().get(i).equals(lot.getNameOfLote())) {
//        					inEdit = true;
//        				}
//        			}
//        			
//        			if(!inEdit) {
//	        			Boolean delete = false;
//	        			
//	        			for (int i = 0; i < presetCommand.getListOfViewLots().size(); i++) {
//	        				if(presetCommand.getListOfViewLots().get(i).equals(lot.getNameOfLote())) {
//	        					presetCommand.getListOfViewLots().remove(i);
//	        					deleteLotsView.add("Delete lot " + lot.getNameOfLote() + " for view in the IPP");
//	        					delete = true;
//	        					
//	        					logger.logger("DEBUG", "SM-WEB", "IPP", "", "Delete lot", "deleteLotsToIppPost()", "", "", 
//	        							"ID IPP: " + id + ", Name Lot: " + lot.getNameOfLote(), 
//	        							"Successfully deleted the lot for view in the template");
//	        					
//	        					break;
//	        				}
//	        			}
//	        			
//	        			if (delete) {
//	        				presetcommanddao.update(presetCommand);
//	        			} else {
//	        				neverDeleteView.add("Impossible delete lot " + lot.getNameOfLote() + " for view, the IPP dont have this lot");
//	        				
//	        				logger.logger("WARNING", "SM-WEB", "IPP", "", "Delete lot", "deleteLotsToIppPost()", "", "", 
//									"ID IPP: " + id + ", Name Lot: " + lot.getNameOfLote(), 
//									"Could not delete the lot for view because the template hasn't this lot");
//	        			}
//	        			
//        			} else {
//        				neverDeleteView.add("Impossible delete lot " + lot.getNameOfLote() + " for view, the IPP have this lot for edit");
//        				
//        				logger.logger("WARNING", "SM-WEB", "IPP", "", "Delete Lot", "deleteLotsToIppPost()", "", "", 
//        						"ID IPP: " + id + ", Name Lot: " + lot.getNameOfLote(), 
//        						"Could not delete the lot for view because the IPP has this lot for edit");
//        			}
//        		}
//        	}
//        	
//        	model.addAttribute("deleteLotsEdit", deleteLotsEdit);
//    		model.addAttribute("deleteLotsView", deleteLotsView);
//    		
//    		model.addAttribute("neverDeleteEdit", neverDeleteEdit);
//    		model.addAttribute("neverDeleteView", neverDeleteView);
//    		
//    		return "ipp_explorer";
//    	}
    	
    	return "redirect:/home/ipp/results";
    }
    
    @GetMapping("home/ipp/view/lots/{id}")
    public String viewLotsToIppGet(Model model, 
    		@PathVariable String id) {
    	
//    	PresetCommand precom = presetcommanddao.retrieveById(id);
//    	 
//    	List<String> listForView = precom.getListOfViewLots();
//    	List<String> listForEdit = precom.getListOfEditLots();
//    	
//    	if (listForView.isEmpty()) {
//    		listForView.add("Empty");
//    	}
//    	
//    	if (listForEdit.isEmpty()) {
//    		listForEdit.add("Empty");
//    	}
//    	
//    	model.addAttribute("listForView", listForView);
//    	model.addAttribute("listForEdit", listForEdit);
//    	model.addAttribute("id", id);
//    	
//    	logger.logger("INFO", "SM-WEB", "IPP", "", "View Lots", "viewLotsToIppGet()", "", "", "ID IPP: " + id, "A screen opens where shows all lots of a specific IPP");
//    	
    	return "ipp_lots_view";
    }
    	
    				/*				UTILS				*/
//    
//    private List<PresetCommand> filter(List<PresetCommand> presets,String filter){
//        List<PresetCommand> result= new ArrayList<PresetCommand>();
//        if(filter==null||(filter.compareTo("none")==0)){
//            return presets;
//        }else{
//            for(PresetCommand preset: presets){
//                	//if(preset.getCondition().equals(filter))
//            		//ESTA CONDICION SE VA A USAR PARA FILTAR LOS IPP EN LA BUSQUEDA
//                		result.add(preset);
//            }
//            return result;
//        }
//    }
//
//    private String findProfileInAnotherCard(String iccid) {
//        CardDAO cardDAO = new CardDAO();
//        List<Card> cards = cardDAO.searchByIccid(iccid);
//        if (cards.isEmpty())
//            return null;
//        return Utils.toUnformattedHexArray(cards.get(0).getEumSignedInfo().getEid());
//    }
//
//    private HashMap<String,String> presetCommandsInstalled(List<PresetCommand> presetCommands){
//        String cardWithProfileEnabled;
//        HashMap<String,String> result=new HashMap<>();
//        for (PresetCommand pc : presetCommands) {
//            if ( (cardWithProfileEnabled = findProfileInAnotherCard(pc.getIccid())) != null) {
//                result.put(pc.getIccid(), cardWithProfileEnabled);
//            }
//        }
//        return  result;
//    }
//
//    private void loadPageIppGeneration(Model model){
//        TemplateIppDAO templateIppDAO=new TemplateIppDAO();
//        List<TemplateIpp> temp=templateIppDAO.retrieve();
//        List<SimpleTemplate> templates = new ArrayList<>();
//        if (temp!= null){
//	        for (TemplateIpp template: temp ) {
//	            if(template.getActive()) {
//	                SimpleTemplate simp = new SimpleTemplate(template);
//	                templates.add(simp);
//	            }
//	        }
//        }
//        ClasificationIppDAO clasificationIppDAO=new ClasificationIppDAO();
//        List<ClasificationIpp> clas= clasificationIppDAO.retrieveByType(ClasificationIpp.IPP_GROUP);
//        List<SimpleClasificationIPP> groups= new ArrayList<>();
//        for (ClasificationIpp clasif: clas) {
//            SimpleClasificationIPP group=new SimpleClasificationIPP(clasif);
//            groups.add(group);
//        }
//        List<String> listMnoid= new ArrayList<>();
//        List<MNO> mnos=mnodao.retrieveAll();
//        for(MNO mno : mnos){
//        	listMnoid.add(mno.getName());
//        }
//        model.addAttribute("groupFound", groups);
//        model.addAttribute("templateFound", templates);
//        model.addAttribute("mnoid",listMnoid);
//    }
    
    /**
     * 
     * @param user: Usuario al cual revisar
     * @param listPresetCommands: Lista de todos los PresetCommand
     * @return: La lista de IPPs cargada segun los lotes del usuario y los IPPs
     */
//    private List<SimpleIpp> loadPresentCommandsFilter(User user, List<PresetCommand> listPresetCommands) {
//    	
//    	List<SimpleIpp> simpleIpp = new ArrayList<>();
//    	
//    	for (PresetCommand preset : listPresetCommands) {
//    		
//    		for (int i = 0; i < preset.getListOfViewLots().size(); i++) {
//    			if(user.getListLots().getListView().contains(preset.getListOfViewLots().get(i))) {
//    				simpleIpp.add(new SimpleIpp(preset));
//    				break;
//    			}
//    		}
//    		
//    		if(user.getRole().equals("SUPERADMIN") && preset.getListOfViewLots().size() == 0) {
//    			simpleIpp.add(new SimpleIpp(preset));
//    		}
//    	}
//    	
//    	return simpleIpp; 
//    }
//    
    /**
     * 
     * @param user: Usuario al cual revisar
     * @param preset: PresetCommand del cual ver los lotes
     * @param listSimpleIpps: Lista a la cual se le van a cargar los PresetComman para mostrar en la WEB
     * @param simpleIpp: PresetCommand el cual se va a agregar a la lista listSimpleIpps si se encuentra en los lots del usuario y PresetCommand
     */
//    private void loadPresentCommands(User user, PresetCommand preset, List<SimpleIpp> listSimpleIpps, SimpleIpp simpleIpp) {   
//    		
//		for (int i = 0; i < preset.getListOfViewLots().size(); i++) {
//			if (user.getListLots().getListView().contains(preset.getListOfViewLots().get(i))) {
//				listSimpleIpps.add(simpleIpp);
//			}
//		}
//		
//		if(user.getRole().equals("SUPERADMIN") && preset.getListOfViewLots().size() == 0) {
//			listSimpleIpps.add(simpleIpp);
//		}
//    }
//    
//    private void loadEditParameterFilter(User user, PresetCommand preset, Map<String, Boolean> listMapEdit) {
//    	
//    	for(int i = 0; i < preset.getListOfEditLots().size(); i++) {
//    	
//	    	if(user.getListLots().getListEdit().contains(preset.getListOfEditLots().get(i))) {
//				listMapEdit.put(preset.getId(), true);
//			} else {
//				listMapEdit.put(preset.getId(), false);
//			}
//    	}
//    	
//    	if(user.getRole().equals("SUPERADMIN") && preset.getListOfEditLots().size() == 0) {
//    		listMapEdit.put(preset.getId(), true);
//    	}
//    }
//    
//    private Map<String, Boolean> loadEditParameter(User user, List<PresetCommand> listPresetCommand) {
//    	
//    	Map<String, Boolean> listMapEdit = new HashMap<String, Boolean>();
//    	
//    	for (PresetCommand preset : listPresetCommand) {
//    		for (int i = 0; i < preset.getListOfEditLots().size(); i++) {
//    			if(user.getListLots().getListEdit().contains(preset.getListOfEditLots().get(i))) {
//    				listMapEdit.put(preset.getId(), true);
//    			} else {
//    				listMapEdit.put(preset.getId(), false);
//    			}
//    		}
//    		
//    		if(user.getRole().equals("SUPERADMIN") && preset.getListOfEditLots().size() == 0) {
//        		listMapEdit.put(preset.getId(), true);
//        	}
//    	}
//    	
//    	return listMapEdit;
//    }

//    public void  generatePresetCommand(TemplateIpp template, PersonalisationIpp info, String group){
//        PresetCommandDAO presetDao=new PresetCommandDAO();
//        presetDao.create(PresetCommand.generatePresetCommand(template, info, group,listMno));
//    }
}
