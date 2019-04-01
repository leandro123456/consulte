package com.lgg.nticxs.web.controller;


import com.lgg.nticxs.web.model.SimpleClasificationIPP;
import com.lgg.nticxs.web.model.SimpleTemplate;
import com.lgg.nticxs.web.model.TemplateSearch;
import com.lgg.nticxs.web.utils.Settings;
import com.lgg.nticxs.web.utils.WSLogger;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.StringWriter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by movasim on 12/12/16.
 */
@Controller
public class TemplateController {
	
//	TemplateIppDAO templatedao = new TemplateIppDAO();
	
	
	private static WSLogger logger = new WSLogger();

    @GetMapping("home/template/explorer")
    public String search(Model model) {
    	logger.logger("INFO", "SM-WEB", "Template", "", "", "search()", "", "", "", "The screen of template explorer opens");
    	
        return "template_explorer";
    }

    @GetMapping("home/template/results")
    public String results(Model model) {
//        TemplateIppDAO tempDAO = new TemplateIppDAO();
//        List<TemplateIpp> templates = null;
//        templates = tempDAO.retrieveActive();
//        model.addAttribute("templateFound", templates);
        return "template_results";
    }

    @PostMapping("home/template/results")
    public String results(Model model, Authentication auth, 
    		@RequestParam("searchText") String searchText, 
    		@RequestParam("searchBy") String searchBy) {
    	
        
//        List<TemplateIpp> templates = null;
//
//        if (searchBy.compareTo(TemplateSearch.SEARCH_BY_NAME) == 0) {
//            templates = new ArrayList<TemplateIpp>();
//            templates.add(templatedao.retrieveByName(searchText));
//        } else if (searchBy.compareTo(TemplateSearch.SEARCH_BY_DEFAULT) == 0) {
//            templates = templatedao.retrieveActive();
//        } else if (searchBy.compareTo(TemplateSearch.SEARCH_BY_CATEGORY) == 0) {
//            templates = templatedao.retrieveByCategory(searchText);
//        } else if (searchBy.compareTo(TemplateSearch.SEARCH_BY_CLASE) == 0) {
//            templates = templatedao.retrieveByClase(searchText);
//        } else if (searchBy.compareTo(TemplateSearch.SEARCH_BY_TYPE) == 0) {
//            templates = templatedao.retrieveByType(searchText);
//        } else {
//            templates = templatedao.retrieveActive();
//        }
//        
//        List<TemplateIpp> templatesFilter = new ArrayList<>();
//        loadTemplatesForLot(user, templates, templatesFilter);
//        model.addAttribute("templateFound", templatesFilter);
//        
//        Map<String, Boolean> mapEdit = loadCanEdit(user, templatesFilter);
//        model.addAttribute("listMapEdit", mapEdit);
//        
//        logger.logger("INFO", "SM-WEB", "Template", "", "Search Template", "results()", "", "", 
//        		"Text of search: " + searchText + ", Search by: " + searchBy, 
//        		"Search Template");
        
        return "template_results";
    }

    @GetMapping("home/template/edit/{templateId}")
    public String edit(Model model, @PathVariable String templateId) {
//        TemplateIppDAO templateIppDAO = new TemplateIppDAO();
//        TemplateIpp templateIpp = templateIppDAO.retrieveById(templateId);
//
//        SimpleTemplate simpleTemplate = new SimpleTemplate(templateIpp);
//        model.addAttribute("template", simpleTemplate);
//        loadPageProvisioning(model);
//        
//        logger.logger("DEBUG", "SM-WEB", "Template", "", "Edit template", "edit()", "", "", "ID template: " + templateId, "A screen opens for edit template");
//        
        return "template_edit";
    }

    @PostMapping("home/template/edit/{templateId}")
    public String executeDownloadIpp(Model model, 
    		@RequestParam(name="file_template", required=false) MultipartFile file,
    		@PathVariable String templateId, 
    		@RequestParam("action") String action, 
    		@RequestParam("name") String name,
    		@RequestParam("category") String category, 
    		@RequestParam("cclass") String cclass, 
    		@RequestParam("type") String type, 
    		@RequestParam("active") Boolean active) {
//        if (action.compareTo("save") == 0) {
//            String fileName = null;
//            TemplateIppDAO templateIppDAO = new TemplateIppDAO();
//            TemplateIpp templateIpp = templateIppDAO.retrieveById(templateId);
//            if(file!=null){
//	            if (!file.isEmpty()) {
//	                try {
//	                    fileName = file.getOriginalFilename();
//	                    byte[] bytes = file.getBytes();
//	                    BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(Settings.getInstance().getFILE_UPLOAD_PATH_IPP() + fileName)));
//	                    buffStream.write(bytes);
//	                    buffStream.close();
//	                    if (templateIpp != null) {
//	                        templateIpp.setTemplate(Utils.getFileContent(Settings.getInstance().getFILE_UPLOAD_PATH_TEMPLATE_IPP() + fileName, true));
//	                    }
//	                } catch (Exception e) {
//	                    StringWriter errors = new StringWriter();
//	                    e.printStackTrace(new PrintWriter(errors));
//	                }
//	            }
//            }
//            templateIpp.setName(name);
//            templateIpp.setCategory(category);
//            templateIpp.setClase(cclass);
//            templateIpp.setType(type);
//            templateIpp.setActive(active);
//            templateIppDAO.update(templateIpp);
//            
//            logger.logger("DEBUG", "SM-WEB", "Template", "", "Edit template", "executeDownloadIpp()", "", "", 
//            		"Name: " + name + ", Category: " + category + ", Class: " + cclass + ", Type: " + type + ", Active: " + active,
//            		"Edit successful template");
//            
//            return "template_explorer";
//        }
        return "template_explorer";
    }
    
    @GetMapping("home/template/delete/{templateId}")
    public String deleteTemplate(Model model,@PathVariable String templateId){
//    	TemplateIppDAO tempdao= new TemplateIppDAO();
//    	tempdao.deleteTemplate(templateId);
//    	model.addAttribute("msg1", "Correct Deletion");
//    	
//    	logger.logger("DEBUG", "SM-WEB", "Template", "", "Delete template", "deleteTemplate()", "", "", "ID template: " + templateId, "Successful delete template");
//    	
    	return "template_explorer";
    }
    
    @GetMapping("home/template/freeImsi/{ippId}")
    public String executeFreeImsi(Model model, @PathVariable String ippId) {
//    	PresetCommandDAO presetDao=new PresetCommandDAO();
//    	PresetCommand preset=presetDao.retrieveById(ippId);
//    	if(preset!=null){
//    		PersonalisationIppDAO perDao=new PersonalisationIppDAO();
//        	PersonalisationIpp data=perDao.retrieveByIccid(preset.getIccid());
//        	if(data!=null){
//        		data.setAvailable(true);
//            	perDao.update(data);
//        	}
//        	preset.setDelete(true);
//        	presetDao.update(preset);
//    	}
//    	
//    	logger.logger("DEBUG", "SM-WEB", "", "", "IPP", "executeFreeImsi()", "", "", "ID IPP: " + ippId, "Successfully deleted IPP");
    	
    	return "ipp_explorer";
    }
    
    /*					LOTS					*/
    
    @GetMapping("home/template/lots/{action}")
    public String loadTemplate(Model model,
    		@PathVariable String action) {
    	
//        List<TemplateIpp> templates = null;
//        templates = templatedao.retrieveActive();
//        
//        if (action.equals("add")) {
//        	model.addAttribute("action", "add");
//        	model.addAttribute("templateFound", templates);
//        	
//        	logger.logger("INFO", "SM-WEB", "Template", "", "Show template", "loadTemplate()", "", "", "", "Shows all templates for add lots");
//        	
//        	return "template_lot";
//        }
//        
//        if (action.equals("delete")) {
//        	model.addAttribute("action", "delete");
//        	model.addAttribute("templateFound", templates);
//        	
//        	logger.logger("INFO", "SM-WEB", "Template", "", "Show template", "loadTemplate()", "", "", "", "Shows all templates for delete their lots");
//        	
//        	return "template_lot";
//        }
//        
//        if (action.equals("view")) {
//        	model.addAttribute("action", "view");
//        	model.addAttribute("templateFound", templates);
//        	
//        	logger.logger("INFO", "SM-WEB", "Template", "", "Show template", "loadTemplate()", "", "", "", "Shows all templates for view their lots");
//        	
//        	return "template_lot";
//        }
//    	
    	
    	return "template_explorer";
    }
    
    @GetMapping("home/template/lots/add/{id}")
    public String addLotsTemplateGet(Model model,
    		@PathVariable String id) {
    	
    	
    	model.addAttribute("id", id);
    	
    	logger.logger("INFO", "SM-WEB", "Template", "", "Add lot", "addLotsTemplateGet()", "", "", 
    			"ID template: " + id, 
    			"A screen opens where shows all lots for add to a specific template");
    	
    	return "template_lot_add";
    }
    
    @GetMapping("home/template/lots/delete/{id}")
    public String deleteLotsTemplateGet(Model model,
    		@PathVariable String id) {
    	
    	
    	model.addAttribute("id", id);
    	
    	logger.logger("INFO", "SM-WEB", "Template", "", "Delete lot", "deleteLotsTemplateGet()", "", "", 
    			"ID template: " + id, 
    			"A screen opens where shows all lots for add to a specific template");
    	
    	return "template_lot_delete";
    }
    
    @PostMapping("home/template/lots/add/{id}")
    public String addLotsTemplatePost(Model model, HttpServletRequest request,
    		@PathVariable String id,
    		@RequestParam (name = "action") String action) {
    	
//    	if (action.compareTo("save") == 0) {
//    		TemplateIpp template = templatedao.retrieveById(id);
//    		
//    		List<String> listOfMsgEdit = new ArrayList<>();
//			List<String> listOfMsgView = new ArrayList<>();
//
//			List<String> listOfMsgEditRepeated = new ArrayList<>();
//			List<String> listOfMsgViewRepeated = new ArrayList<>();
//    		
//    		List<Lot> listLots = lotdao.retrieveAll();
//    		
//    		for (Lot lot : listLots) {
//    			
//    			if(request.getParameter("edit_" + lot.getNameOfLote()).equals("true")) {
//    				
//    				Boolean lotRepeated = false;
//    				Boolean repeatedInView = false;
//    				
//    				for (String lotList : template.getListOfEdit()) {
//    					if(lotList.equals(lot.getNameOfLote())) {
//    						lotRepeated = true;
//    						listOfMsgEditRepeated.add("The lot " + lot.getNameOfLote() + " for edit already this has the Template");
//    						
//    						logger.logger("WARNING", "SM-WEB", "Template", "", "Add lot", "addLotsTemplatePost()", "", "",
//    								"Id template: " + id + ", Name lot: " + lot.getNameOfLote(),
//    								"This template has the lot what are you trying to add");
//    						
//    						break;
//    					}
//    				}
//    				
//    				for (int i = 0; i < template.getListOfView().size(); i++) {
//    					if(template.getListOfView().get(i).equals(lot.getNameOfLote())) {
//    						repeatedInView = true;
//    						break;
//    					}
//    				}
//    				
//    				if(!lotRepeated) {
//    					
//    					template.getListOfEdit().add(lot.getNameOfLote());
//    					
//    					if(!repeatedInView) {
//    						template.getListOfView().add(lot.getNameOfLote());
//    					}
//    					
//    					logger.logger("DEBUG", "SM-WEB", "Template", "", "Add lot", "addLotsTemplatePost()", "", "",
//    							"Id template: " + id + ", Name lot: " + lot.getNameOfLote(),
//    							"Successfully added the lot for edit to template");
//    					
//    					templatedao.update(template);
//    					listOfMsgEdit.add("Add lot " + lot.getNameOfLote() + " for edit to the Template");
//    				}
//    			}
//    			
//    			if(request.getParameter("view_" + lot.getNameOfLote()).equals("true")) {
//    				
//    				Boolean lotRepeated = false;
//    					
//					for (String lotList : template.getListOfView()) {
//						if (lotList.equals(lot.getNameOfLote())) {
//							lotRepeated = true;
//							listOfMsgViewRepeated.add("The lot " + lot.getNameOfLote() + " for view already this has the Template");
//							
//							logger.logger("WARNING", "SM-WEB", "Template", "", "Add lot", "addLotsTemplatePost()", "", "",
//    								"Id template: " + id + ", Name lot: " + lot.getNameOfLote(),
//    								"This template has the lot what are you trying to add");
//							
//							break;
//						}
//					}
//
//					if (!lotRepeated) {
//
//						template.getListOfView().add(lot.getNameOfLote());
//						
//						logger.logger("DEBUG", "SM-WEB", "Template", "", "Add lot", "addLotsTemplatePost()", "", "",
//								"Id template: " + id + ", Name lot: " + lot.getNameOfLote(),
//								"Successfully added the lot for view to template");
//						
//						templatedao.update(template);
//						listOfMsgView.add("Add lot " + lot.getNameOfLote() + " for view to the Template");
//					}    				
//    			}
//    		}
//    		
//    		model.addAttribute("msgEdit", listOfMsgEdit);
//        	model.addAttribute("msgView", listOfMsgView);
//        	
//			model.addAttribute("msgEditRepeated", listOfMsgEditRepeated);
//			model.addAttribute("msgViewRepeated", listOfMsgViewRepeated);
//			
//			return "template_explorer";
//    	}
    	
    	return "redirect:/home/template/lots/add";
    }
    
    @PostMapping("home/template/lots/delete/{id}")
    public String deleteLotsTemplatePost(Model model, HttpServletRequest request,
    		@PathVariable String id, 
    		@RequestParam (name = "action") String action) {
    	
//    	if(action.compareTo("save") == 0) {
//    		TemplateIpp template = templatedao.retrieveById(id);
//    		
//    		List<Lot> listLot = lotdao.retrieveAll();
//    		
//    		List<String> deleteLotsEdit = new ArrayList<>();
//        	List<String> deleteLotsView = new ArrayList<>();
//        	
//        	List<String> neverDeleteEdit = new ArrayList<>();
//        	List<String> neverDeleteView = new ArrayList<>();
//    		
//    		for (Lot lot : listLot) {
//    			
//    			if(request.getParameter("edit_" + lot.getNameOfLote()).equals("true")) {
//    				
//    				Boolean deleted = false;
//    				
//    				for (int i = 0; i < template.getListOfEdit().size(); i++) {
//    					if(template.getListOfEdit().get(i).equals(lot.getNameOfLote())) {
//    						template.getListOfEdit().remove(i);
//    						deleteLotsEdit.add("Delete lot " + lot.getNameOfLote() + " for edit in the Template");
//    						deleted = true;
//    						
//    						logger.logger("DEBUG", "SM-WEB", "Template", "", "Delete lot", "deleteLotsTemplatePost()", "", "",
//    								"ID template: " + id + ", Name lot: " + lot.getNameOfLote(), 
//    								"Successfully deleted the lot for edit in the template");
//    						
//    						break;
//    					}
//    				}
//    				
//    				for (int i = 0; i < template.getListOfView().size(); i++) {
//    					if(template.getListOfView().get(i).equals(lot.getNameOfLote()) && deleted) {
//    						template.getListOfView().remove(i);
//    						deleteLotsView.add("Delete lot " + lot.getNameOfLote() + " for view in the Template");
//    						
//    						logger.logger("DEBUG", "SM-WEB", "Template", "", "Delete lot", "deleteLotsTemplatePost()", "", "",
//    								"ID template: " + id + ", Name lot: " + lot.getNameOfLote(), 
//    								"Successfully deleted the lot for view in the template");
//    						
//    						break;
//    					}
//    				}
//    				
//    				if(deleted) {
//    					templatedao.update(template);
//    				} else {
//    					neverDeleteEdit.add("Impossible delete lot " + lot.getNameOfLote() + " for edit, the Template dont have this lot");
//    					
//    					logger.logger("WARNING", "SM-WEB", "Template", "", "Delete lot", "deleteLotsTemplatePost()", "", "", 
//    							"ID template: " + id + ", Name lot: " + lot.getNameOfLote(),
//    							"Could not delete the lot for edit because the template hasn't this lot");
//    				}
//    			}
//    			
//    			if(request.getParameter("view_" + lot.getNameOfLote()).equals("true")) {
//    				
//    				Boolean inEdit = false;
//    				
//    				for (String lotsInEdit : template.getListOfEdit()) {
//    					if(lotsInEdit.equals(lot.getNameOfLote())) {
//    						inEdit = true;
//    					}
//    				}
//    				
//    				if (!inEdit) {
//	    				Boolean deleted = false;
//	    				
//	    				for (int i = 0; i < template.getListOfView().size(); i++) {
//	    					if(template.getListOfView().get(i).equals(lot.getNameOfLote())) {
//	    						template.getListOfView().remove(i);
//	    						deleteLotsView.add("Delete lot " + lot.getNameOfLote() + " for view in the Template");
//	    						deleted = true;
//	    						
//	    						logger.logger("DEBUG", "SM-WEB", "Template", "", "Delete lot", "deleteLotsTemplatePost()", "", "",
//	    								"ID template: " + id + ", Name lot: " + lot.getNameOfLote(), 
//	    								"Successfully delete the lot for view in the template");
//	    						
//	    						break;
//	    					}
//	    				}
//	    				
//	    				if(deleted) {
//	    					templatedao.update(template);
//	    				} else {
//	    					logger.logger("WARNING", "SM-WEB", "Template", "", "Delete lot", "deleteLotsTemplatePost()", "", "", 
//	    							"ID template: " + id + ", Name lot: " + lot.getNameOfLote(),
//	    							"Could not delete the lot for view because the template hasn't this lot");
//	    					
//	    					neverDeleteView.add("Impossible delete lot " + lot.getNameOfLote() + " for view, the Template dont have this lot");
//	    				}
//    				} else {
//    					neverDeleteView.add("Impossible delete lot " + lot.getNameOfLote() + " for view, the Template have this lot for edit");
//    					
//    					logger.logger("WARNING", "SM-WEB", "Template", "", "Delete lot", "deleteLotsTemplatePost()", "", "", 
//    							"ID template: " + id + ", Name lot: " + lot.getNameOfLote(),
//    							"Could not delete the lot for view because the template has this lot for edit");
//    				}
//    			}
//    		}
//    		
//    		model.addAttribute("deleteLotsEdit", deleteLotsEdit);
//    		model.addAttribute("deleteLotsView", deleteLotsView);
//    		
//    		model.addAttribute("neverDeleteEdit", neverDeleteEdit);
//    		model.addAttribute("neverDeleteView", neverDeleteView);
//    		
//    		return "template_explorer";
//    	}
    	
    	return "redirect:/home/template/lots/delete";
    }
    
    @GetMapping("home/template/lots/view/{id}")
    public String viewTemplate(Model model,
    		@PathVariable String id) {
//    	TemplateIppDAO tempDAO = new TemplateIppDAO();
//    	TemplateIpp  template = tempDAO.retrieveById(id);
//    	
//    	List<String> listOfEdit = template.getListOfEdit();
//    	List<String> listOfView = template.getListOfView();
//    	
//    	if(listOfEdit.isEmpty()) {
//    		listOfEdit.add("Empty");
//    	}
//    	
//    	if(listOfView.isEmpty()) {
//    		listOfView.add("Empty");
//    	}
//    	
//    	model.addAttribute("listOfEdit", listOfEdit);
//    	model.addAttribute("listOfView", listOfView);
//    	model.addAttribute("id", template.getId());
//    	
//    	logger.logger("INFO", "SM-WEB", "Template", "", "View lots", "viewTemplate()", "", "", "ID template: " + id, "A screen opens where shows all lots of a specific template");
//    	
    	return "template_lots_view";
    }
    
    /*					UTILS					*/
    
    private void loadPageProvisioning(Model model) {
    	
//	    ClasificationIppDAO clasifDao=new ClasificationIppDAO();
//	    List<ClasificationIpp> clasidications= clasifDao.retrieve();
//	    List<SimpleClasificationIPP> categorys= new ArrayList<>();
//	    List<SimpleClasificationIPP> clases= new ArrayList<>();
//	    List<SimpleClasificationIPP> types= new ArrayList<>();
//	    
//	    for (ClasificationIpp clasif: clasidications) {
//	        SimpleClasificationIPP element=new SimpleClasificationIPP(clasif);
//	        if(element.getType().equals(ClasificationIpp.IPP_CATEGORY))
//	            categorys.add(element);
//	        else if(element.getType().equals(ClasificationIpp.IPP_CLASS))
//	            clases.add(element);
//	        else if(element.getType().equals(ClasificationIpp.IPP_TYPE))
//	            types.add(element);
//	    }
//	    
//	    model.addAttribute("categoryFound", categorys);
//	    model.addAttribute("claseFound", clases);
//	    model.addAttribute("typeFound", types);
    }
    
    /**
     * 
     * @param user: Usuario a revisar
     * @param templates: Toda la lista de templates o una lista filtrada
     * @param templatesFilter: Lista de templates filtrada por los lotes del usuario y los templates
     */
//    private void loadTemplatesForLot(User user, List<TemplateIpp> templates, List<TemplateIpp> templatesFilter) {
//    	if(templates != null) {
//	    	if(!templates.contains(null)) {
//		    	for (int in = 0; in < templates.size() ; in++) {
//		    		
//		    		if(user.getRole().equals("SUPERADMIN") && templates.get(in).getListOfView().size() == 0 ) {
//		        		templatesFilter.add(templates.get(in));
//		        	}
//		    		
//		    		if(templates.get(in).getListOfView() != null) { 
//			        	for (int i = 0; i < templates.get(in).getListOfView().size(); i++) {
//			        		if(user.getListLots().getListView().contains(templates.get(in).getListOfView().get(i))) {
//			        			templatesFilter.add(templates.get(in));
//			        			break;
//			            	}
//			        	}
//		    		}
//		        }
//	    	}
//    	}
//    }
    
    /**
     * 
     * @param user: Usuario a revisar
     * @param templatesFilter: Lista de templates ya filtrados para ver
     */
//    private Map<String, Boolean> loadCanEdit(User user, List<TemplateIpp> templatesFilter) {
//    	
//    	Map<String, Boolean> mapEdit = new HashMap<String, Boolean>();
//    	
//    	for (int in = 0; in < templatesFilter.size(); in++) {
//    		
//    		if(user.getRole().equals("SUPERADMIN") && templatesFilter.get(in).getListOfEdit().isEmpty()) {
//    			mapEdit.put(templatesFilter.get(in).getId(), true);
//    		}
//    		
//    		if(templatesFilter.get(in).getListOfEdit() != null) {
//	    		for (int i = 0; i < templatesFilter.get(in).getListOfEdit().size(); i++) {
//	    			if (user.getListLots().getListEdit().contains(templatesFilter.get(in).getListOfEdit().get(i))) {
//	    				mapEdit.put(templatesFilter.get(in).getId(), true);
//	    			} else {
//	    				mapEdit.put(templatesFilter.get(in).getId(), false);
//	    			}
//	    		}
//    		}
//    	}
//    	
//    	return mapEdit;
//    }
//    
////    @Test
//    public void test() {
//    	List<TemplateIpp> list = templatedao.retrieve();
//    	for (TemplateIpp template : list) {	
//    		System.out.println(template.getListOfEdit().isEmpty());
//    	}
//    }

}
