package com.lgg.nticxs.web.controller;

import org.ietf.tools.TOTPCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.lgg.nticxs.web.DAO.RolesDAO;
import com.lgg.nticxs.web.utils.EncryptorPassword;
import com.lgg.nticxs.web.utils.WSLogger;

import com.lgg.nticxs.web.model.Role;
import com.lgg.nticxs.web.model.SimpleCard;
import com.lgg.nticxs.web.model.SimpleIpp;



import nl.flotsam.xeger.Xeger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by movasim on 05/09/16.
 */
@Controller
public class GeneralConfigurationController {
	
	/*				WEB DB					*/
    RolesDAO rolesdao = new RolesDAO();
    
    
    /*				DP DB					*/
//    TemplateIppDAO templatedao = new TemplateIppDAO();
//    PresetCommandDAO presetdao = new PresetCommandDAO();
//    
//    /*				SR DB					*/
//    CardDAO carddao = new CardDAO();
    
    private static WSLogger logger = new WSLogger();

    @GetMapping("home/general/configuration")
    public String index(Model model) {
    	loadPage(model);
    	
    	logger.logger("INFO", "SM-WEB", "General Configuration", "", "", "index()", "", "", "", "The screen of General Configuration opens");
    	
        return "general_configuration";
    }

    				/*                   	USER           				*/
    
//   @GetMapping("home/general/configuration/user/edit/{userid}")
//   public String editUserGet(Model model, @PathVariable String userid) {
//		User user = userdao.retrieveById(userid);
//		List<Role> roles = addRoles();
//		model.addAttribute("user", user);
//		model.addAttribute("roleFound", roles);
//		
//		logger.logger("INFO", "SM-WEB", "General Configuration", "", "Edit User", "editUserGet()", "", "", "ID User: " + userid, "A screen opens where can edit a user");
//		
//		return "user_edit";
//   }
   
   @PostMapping("home/general/configuration/user/edit/{userid}")
   public String editUserPost(Model model,
        @PathVariable String userid, 
        @RequestParam("action") String action,
        @RequestParam("name") String name, 
        @RequestParam("role") String role,
        @RequestParam("passwordChangeorKeep") Boolean passwordChangeorKeep,
        @RequestParam(name="passOld", required=false) String passOld, 
        @RequestParam(name="passNew1", required=false) String passNew1,
        @RequestParam(name="passNew2", required=false) String passNew2) throws Exception{
    
//	   if (action.compareTo("save") == 0) {
//            User user=userdao.retrieveById(userid);
//            
//            User verifyUser = userdao.retrieveByName(name);
//            
//            //Compruebo que el nombre de usuario no lo tenga otro usuario
//            if(verifyUser != null && !(verifyUser.getId().equals(user.getId()))) {
//            	model.addAttribute("msg1", "Error name already used by another user");
//            	
//            	logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Edit user", "editUserPost()", "", "", 
//            	"ID User: " + userid + ", Name: " + name + ", Role: " + role + "Change password: " + passwordChangeorKeep, 
//            	"Error name already used by another user");
//            	
//                loadPage(model);
//                return "general_configuration";
//            }
//            
//            //Compruebo si se va a cambiar de clave o no
//            //si se cambia se continua con el proceso de verificacion para la nueva clave
//            //si no se cambia se mantiene la vieja clave
//            if(passwordChangeorKeep) {
//            	user.setName(name);
//                user.setRole(role);
//                
//                //Guardo en DB
//                userdao.update(user);
//                
//                model.addAttribute("msg1", "Correctly update user, without changing the password");
//                
//                logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Edit user", "editUserPost()", "", "", 
//                "ID User: " + userid + ", Name: " + name + ", Role: " + role + "Change password: " + passwordChangeorKeep,
//                "Correctly update user, without changing the password");
//                
//                loadPage(model);
//                return "general_configuration";
//            }  
//            
//            //CONTINUO PROCESO DE VERIFICACION
//            //Compruebo que la clave ingresadas sea igual a la anterior y que la nueva clave
//            //sea igual a la clave repetida
//            if(EncryptorPassword.decrypt(user.getPassword()).equals(passOld) && passNew1.equals(passNew2)) {
//
//            	boolean passwordRepeated = false;
//            	
//            	//Comprobacion de password nueva en el historial de passwords
//            	for (int i = 0; i < user.getHistoryPassword().size(); i++) {
//            		String decrypt = EncryptorPassword.decrypt(user.getHistoryPassword().get(i)); 
//            		
//            		if (decrypt.equals(passNew1)) {
//            			passwordRepeated = true;
//            		}
//            	}
//            	
//            	//Si en la anterior comprobacion la password
//            	//se repetia se impedira crear el nuevo usuario
//            	if(passwordRepeated) {
//            		model.addAttribute("msg1", "Error in the change of the password, password used previously");
//            		
//            		logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Edit user", "editUserPost()", "", "", 
//                    "ID User: " + userid + ", Name: " + name + ", Role: " + role + "Change password: " + passwordChangeorKeep,
//            		"Error in the change of the password, password used previously");
//            		
//                    loadPage(model);
//                    return "general_configuration";
//            	} 
//            	
//            	//Match usado para la comprobacion
//            	String match = "^(?!.*([A-Za-z0-9])\\1{1})(?=.*[A-Z].*[A-Z].*[A-Z])(?=.*[!@#$&;.,*].*[!@#$&;.,*].*[!@#$&;.,*])(?=.*[0-9].*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{15}$";
//            	
//            	//Compruebo la seguridad de la clave
//            	if(passNew1.matches(match)) {
//            		byte[] newPassword = EncryptorPassword.encrypt(passNew1);
//            		
//	                user.setName(name);
//	                user.setRole(role);
//	            	user.setPassword(newPassword);
//	            	
//	            	//Agrego la nueva clave al historial de claves
//	            	user.getHistoryPassword().add(newPassword);
//	            	
//	            	//Guardo en DB
//	            	userdao.update(user);
//	                
//	                model.addAttribute("msg1", "Correctly update user");
//	                
//	                logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Edit user", "editUserPost()", "", "", 
//	                "ID User: " + userid + ", Name: " + name + ", Role: " + role + "Change password: " + passwordChangeorKeep,
//	                "Correctly update user");
//	                
//	                loadPage(model);
//	                return "general_configuration";
//	                
//            	} else {
//            		model.addAttribute("msg1", "Error ... Password must meet security requirements");
//            		
//            		logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Edit user", "editUserPost()", "", "", 
//                    "ID User: " + userid + ", Name: " + name + ", Role: " + role + "Change password: " + passwordChangeorKeep,
//                    "Password don't musts meet security requirements");
//            		
//            		loadPage(model);
//                    return "general_configuration";
//            	}
//            	
//            } else {
//            	
//                model.addAttribute("msg1", "Error in the change of the password");
//                
//                logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Edit user", "editUserPost()", "", "", 
//                "ID User: " + userid + ", Name: " + name + ", Role: " + role + "Change password: " + passwordChangeorKeep,
//                "Error in the change of the password");
//                
//                loadPage(model);
//                return "general_configuration";
//            }
//    }
    
    loadPage(model);
    return "general_configuration";
   }
   
   @GetMapping("/home/general/configuration/user/delete/{userid}")
   public String deleteUserGet(Model model,@PathVariable String userid){
	
	//Borro el usuario de la DB
	//Aclaracion:
	//El usuario no se puede borrar "realmente" de la DB,
	//solo se le setea el parametro delete como true
	
    model.addAttribute("msg1", "Correct Deletion");
    
    logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete user", "deleteUserGet()", "", "", 
    "ID User: " + userid, 
    "Correct Deletion of the user");
    
    loadPage(model);
    return "general_configuration";
   }
   
//   @GetMapping("/home/general/configuration/user/createnewkey/{userid}")
//   public String createNewKeyTSAGet(Model model,@PathVariable String userid){
//	
//	   User user = userdao.retrieveById(userid);
//	
//	   if (user != null) {
//		   
//			// Creo la nueva key TSA y la seteo al usuario
////			user.setKeyTSA(TOTPCode.getRandomSecretKey());
//
//			// Guardo en DB
//			userdao.update(user);
//
//			// Genero y muestro el codigo QR
////			String barCodeData = TOTPCode.getGoogleAuthenticatorBarCode(user.getKeyTSA(), user.getName(), "eReach");
////			model.addAttribute("imgQR", barCodeData);
//
//			model.addAttribute("msgQR", "Correct create new key");
//			
//			logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Create key for TSA", "createNewKeyTSAGet()", "", "", 
//			"ID User: " + userid, 
//			"Correct create new key for TSA");
//			
//			loadPage(model);
//			return "general_configuration";
//	}
//	
//	model.addAttribute("msg1", "Error create new key");
//	
//	logger.logger("ERROR", "SM-WEB", "General Configuration", "", "Create key for TSA", "createNewKeyTSAGet()", "", "",
//	"ID User: " + userid,
//	"Error create new key for TSA");
//	
//    loadPage(model);
//    return "general_configuration";
//   }
   
   @GetMapping("/home/general/configuration/user/randompassword")
   public void randompassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	   	String result = null;
    	Boolean complete = true;
    	
    	while(complete) {
    		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        	String upperCase = lowerCase.toUpperCase();
        	String regex = "[" + lowerCase + "]{5}[!@#$&*]{3}[" + upperCase + "]{4}[123456789]{3}";
        	Xeger generator = new Xeger(regex);
        	result = generator.generate();
        	assert result.matches(regex);
    		
    		String match = "^(?!.*([A-Za-z0-9])\\1{1})(?=.*[A-Z].*[A-Z].*[A-Z])(?=.*[!@#$&;.,*].*[!@#$&;.,*].*[!@#$&;.,*])(?=.*[0-9].*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{15}$";
    		if(result.matches(match)) {
    			complete = false;
    		}
    	}

	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(result);
	}
   
   @GetMapping("home/general/configuration/user/lots/{action}/{idUser}")
   public String actionLotsGet(Model model,
		   @PathVariable String action,
		   @PathVariable String idUser) {
	   
//	   if(action.equals("add")) {
//		   User user = userdao.retrieveById(idUser);
//		   SimpleUser simpleUser = new SimpleUser(user);
//		   
//		   model.addAttribute("user", simpleUser);
//		   model.addAttribute("nameAction", "Add");
//		   model.addAttribute("action", "add");
//		   
//		   logger.logger("INFO", "SM-WEB", "General Configuration", "", "Add lot", "actionLotsGet()", "", "", 
//		   "ID User: " + idUser, 
//		   "A screen opens where shows all lots for add them to a user specific");
//		   
//		   return "user_action_lot";
//	   }
	   
//	   if(action.equals("delete")) {
//		   User user = userdao.retrieveById(idUser);
//		   SimpleUser simpleUser = new SimpleUser(user);
//		   
//		   model.addAttribute("user", simpleUser);
//		   model.addAttribute("nameAction", "Delete");
//		   model.addAttribute("action", "delete");
//		   
//		   logger.logger("INFO", "SM-WEB", "General Configuration", "", "Delete lot", "actionLotsGet()", "", "", 
//		   "ID User: " + idUser, 
//		   "A screen opens where shows all lots for delete them to a user specific");
//		   
//		   return "user_action_lot";
//	   }
	   
	   loadPage(model);
	   return "general_configuration";
   }
   
   @PostMapping("home/general/configuration/user/lots/add/{idUser}")
   public String addLotsPost (Model model, HttpServletRequest request,
		   @PathVariable String idUser,
		   @RequestParam (name = "action") String action) {
	   
		List<String> listOfMsgEdit = new ArrayList<>();
		List<String> listOfMsgView = new ArrayList<>();

		List<String> listOfMsgEditRepeated = new ArrayList<>();
		List<String> listOfMsgViewRepeated = new ArrayList<>();
	   
//	   if(action.compareTo("save") == 0) {
//		   User user = userdao.retrieveById(idUser);
//		   
////		   for (Lot lot : listLot) {
////			   
////			   if(request.getParameter("edit_" + lot.getNameOfLote()).equals("true")) {
////				   
////				   Boolean repeated = false;
////				   Boolean repeatedInView = false;
////				   
//////				   for (int i = 0; i < user.getListLots().getListEdit().size(); i++) {
//////					   if(user.getListLots().getListEdit().get(i).equals(lot.getNameOfLote())) {
//////						   repeated = true;
//////						   listOfMsgEditRepeated.add("The lot " + lot.getNameOfLote() + " for edit already this has the user " + user.getName());
//////						   
//////						   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Lot", "addLotsPost()", "", "", 
//////						   "ID User: " + idUser + ", Name lot: " + lot.getNameOfLote(),
//////						   "This user has the lot what are you trying to add");
//////						   
//////						   break;
//////					   }
//////				   }
//////				   
//////				   for (int i = 0; i < user.getListLots().getListView().size(); i++) {
//////					   if(user.getListLots().getListView().get(i).equals(lot.getNameOfLote())) {
//////						   repeatedInView = true;
//////					   }
//////				   }
//////				   
//////				   if(!repeated) {
//////					   user.getListLots().getListEdit().add(lot.getNameOfLote());
//////					   
//////					   if(!repeatedInView) {
//////						   user.getListLots().getListView().add(lot.getNameOfLote());
//////					   }
//////					   
//////					   userdao.update(user);
//////					   listOfMsgEdit.add("Add lot " + lot.getNameOfLote() + " for edit to user " + user.getName());
//////					   
//////					   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Lot", "addLotsPost()", "", "", 
//////					   "ID User: " + idUser + ", Name lot: " + lot.getNameOfLote(), 
//////					   "Successfully added the lot for edit to user");
//////				   }
////			   }
////			   
////			   if(request.getParameter("view_" + lot.getNameOfLote()).equals("true")) {
////				   
////				   Boolean repeated = false;
////				   
//////				   for (int i = 0; i < user.getListLots().getListView().size(); i++) {
//////					   if(user.getListLots().getListView().get(i).equals(lot.getNameOfLote())) {
//////						   repeated = true;
//////						   listOfMsgViewRepeated.add("The lot " + lot.getNameOfLote() + " for view already this has the user " + user.getName());
//////						   
//////						   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Lot", "addLotsPost()", "", "", 
//////						   "ID User: " + idUser + ", Name lot: " + lot.getNameOfLote(), 
//////						   "This user has the lot what are you trying to add");
//////						   
//////						   break;
//////					   }
//////				   }
////				   
//////				   if(!repeated) {
//////					   user.getListLots().getListView().add(lot.getNameOfLote());
//////					   userdao.update(user);
//////					   listOfMsgView.add("Add lot " + lot.getNameOfLote() + " for view to user " + user.getName());
//////					   
//////					   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Lot", "addLotsPost()", "", "", 
//////					   "ID User: " + idUser + ", Name lot: " + lot.getNameOfLote(),
//////					   "Successfully added the lot for view to template");
//////				   }
////			   }
////		   }
//		   
//			model.addAttribute("msgEdit", listOfMsgEdit);
//			model.addAttribute("msgView", listOfMsgView);
//
//			model.addAttribute("msgEditRepeated", listOfMsgEditRepeated);
//			model.addAttribute("msgViewRepeated", listOfMsgViewRepeated);
//
//			loadPage(model);
//			return "general_configuration";
//	   }
	   
	   loadPage(model);
	   return "general_configuration";
   }
   
//   @PostMapping("home/general/configuration/user/lots/delete/{idUser}")
//   public String deleteLotsPost(Model model, HttpServletRequest request,
//		   @RequestParam (name = "action") String action,
//		   @PathVariable String idUser) {
//	   
//		List<String> deleteLotsEdit = new ArrayList<>();
//		List<String> deleteLotsView = new ArrayList<>();
//
//		List<String> neverDeleteEdit = new ArrayList<>();
//		List<String> neverDeleteView = new ArrayList<>();
//	   
//	   if(action.compareTo("save") == 0) {
//		   User user = userdao.retrieveById(idUser);
//		   List<Lot> listLot = lotdao.retrieveAll();
//		   
//		   for (Lot lot : listLot) {
//			   
//			   if(request.getParameter("edit_" + lot.getNameOfLote()).equals("true")) {
//				   
//				   Boolean delete = false;
				   
//				   for (int i = 0; i < user.getListLots().getListEdit().size(); i++) {
//					   if(user.getListLots().getListEdit().get(i).equals(lot.getNameOfLote())) {
//						   user.getListLots().getListEdit().remove(i);
//						   deleteLotsEdit.add("Delete lot " + lot.getNameOfLote() + " for edit in the user " + user.getName());
//						   
//						   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Lot", "deleteLotsPost()", "", "", 
//						   "ID User: " + idUser + ", Name lot: " + lot.getNameOfLote(), 
//						   "Successfully deleted the lot for edit in the user");
//						   
//						   delete = true;
//						   break;
//					   }
//				   }
				   
//				   for (int i = 0; i < user.getListLots().getListView().size(); i++) {
//					   if(user.getListLots().getListView().get(i).equals(lot.getNameOfLote()) && delete) {
//						   user.getListLots().getListView().remove(i);
//						   deleteLotsView.add("Delete lot " + lot.getNameOfLote() + " for view in the user " + user.getName());
//						   
//						   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Lot", "deleteLotsPost()", "", "", 
//						   "ID User: " + idUser + ", Name lot: " + lot.getNameOfLote(), 
//						   "Successfully deleted the lot for view in the user");
//						   
//						   break;
//					   }
//				   }
				   
//				   if(delete) {
//					   userdao.update(user);
//				   } else {
//					   neverDeleteEdit.add("Impossible delete lot " + lot.getNameOfLote() + " for edit, the user " + user.getName() + " dont have this lot");
//				   
//					   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "deleteLotsPost()", "", "", 
//					   "ID User: " + idUser + ", Name lot: " + lot.getNameOfLote(), 
//					   "Could not delete the lot for edit because the user hasn't this lot");
//				   
//				   }
//			   }
//			   
//			   if(request.getParameter("view_" + lot.getNameOfLote()).equals("true")) {
//				   
//				   Boolean delete = false;
//				   Boolean inEdit = false;
				   
//				   for (int i = 0; i < user.getListLots().getListEdit().size(); i++) {
//					   if(user.getListLots().getListEdit().get(i).equals(lot.getNameOfLote())) {
//						   inEdit = true;
//						   neverDeleteView.add("Impossible delete lot " + lot.getNameOfLote() + " for view, the user " + user.getName() + " have this lot for edit");
//						   
//						   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "deleteLotsPost()", "", "", 
//						   "ID User: " + idUser + ", Name lot: " + lot.getNameOfLote(), 
//						   "Could not delete the lot for view because the user has this lot for edit");
//						   
//						   break;
//					   }
//				   }
//				   
//				   if(!inEdit) {
//					   for (int i = 0; i < user.getListLots().getListView().size(); i++) {
//						   if (user.getListLots().getListView().get(i).equals(lot.getNameOfLote())) {
//							   user.getListLots().getListView().remove(i);
//							   deleteLotsView.add("Delete lot " + lot.getNameOfLote() + " for view in the user " + user.getName());
//							   
//							   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Lot", "deleteLotsPost()", "", "", 
//							   "ID User: " + idUser + ", Name lot: " + lot.getNameOfLote(), 
//							   "Successfully delete the lot for view in the user");
//							   
//							   delete = true;
//							   break;
//						   }
//					   }
//					   
//					   if(delete) {
//						   userdao.update(user);
//					   } else {
//						   neverDeleteView.add("Impossible delete lot " + lot.getNameOfLote() + " for view, the user " + user.getName() + " dont have this lot");
//						   
//						   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "deleteLotsPost()", "", "", 
//						   "ID User: " + idUser + ", Name lot: " + lot.getNameOfLote(), 
//						   "Could not delete the lot for view because the user hasn't this lot");
//					   }
//				   }
//			   }
//		   }
//		   
//			model.addAttribute("deleteLotsEdit", deleteLotsEdit);
//			model.addAttribute("deleteLotsView", deleteLotsView);
//
//			model.addAttribute("neverDeleteEdit", neverDeleteEdit);
//			model.addAttribute("neverDeleteView", neverDeleteView);
//
//			loadPage(model);
//			return "general_configuration";
//	   }
//	   
	 //  loadPage(model);
//	   return "general_configuration";
//   }
   
   @GetMapping("home/general/configuration/user/lots/view/{userId}")
   public String viewLotsGet(Model model,
		   @PathVariable String userId) {
	   
//	   ListLotsUser listOfLots = userdao.retrieveById(userId).getListLots();
//	   List<String> listOfView = listOfLots.getListView();
//	   List<String> listOfEdit = listOfLots.getListEdit();
//	   
//	   if(listOfView.isEmpty()) {
//		   listOfView.add("Empty");
//	   }
//	   
//	   if(listOfEdit.isEmpty()) {
//		   listOfEdit.add("Empty");
//	   }
	   
//	   model.addAttribute("listOfView", listOfView);
//	   model.addAttribute("listOfEdit", listOfEdit);
//	   model.addAttribute("nameUser", userdao.retrieveById(userId).getName());
	   
//	   logger.logger("INFO", "SM-WEB", "General Configuration", "", "View Lots", "viewLotsGet()", "", "", 
//	   "Name user: " + userdao.retrieveById(userId).getName(), 
//	   "A screen opens where shows all lots of a specific user");
	   
	   return "user_view_lots";
   }
   
   @GetMapping("home/general/configuration/user/lots/card/{action}/{idUser}")
   public String actionCardLotsGet(Model model,
		   @PathVariable String action,
		   @PathVariable String idUser) {
	   
//	   if (action.equals("add")) {
//	   
//		   User user = userdao.retrieveById(idUser);
//		   SimpleUser simpleUser = new SimpleUser(user);
//		   model.addAttribute("user", simpleUser);
//		   
//		   model.addAttribute("nameAction", "Add");
//		   model.addAttribute("action", "add");
//		   
//		   logger.logger("INFO", "SM-WEB", "General Configuration", "", "Add Card Lot", "actionCardLotsGet()", "", "", 
//				   "ID User: " + idUser, 
//				   "A screen opens where shows all card lots for add them to a user specific");
//		   
//		   return "user_action_card_lot";
//	   }
	   
//	   if (action.equals("delete")) {
//		   List<LotCard> listCardLot = lotcarddao.retrieveAll();
//		   model.addAttribute("listCardLot", listCardLot);
//		   
//		   User user = userdao.retrieveById(idUser);
//		   SimpleUser simpleUser = new SimpleUser(user);
//		   model.addAttribute("user", simpleUser);
//		   
//		   model.addAttribute("nameAction", "Delete");
//		   model.addAttribute("action", "delete");
//		   
//		   logger.logger("INFO", "SM-WEB", "General Configuration", "", "Delete Card Lot", "actionCardLotsGet()", "", "", 
//				   "ID User: " + idUser, 
//				   "A screen opens where shows all card lots for delete them to a user specific");
//		   
//		   return "user_action_card_lot";
//	   }
	   
	   loadPage(model);
	   return "general_configuration";
   }
   
	@PostMapping("home/general/configuration/user/lots/card/add/{idUser}")
	public String addCardLotsPost(Model model, HttpServletRequest request, 
			@PathVariable String idUser,
			@RequestParam(name = "action") String action) {

		List<String> listOfMsgEdit = new ArrayList<>();
		List<String> listOfMsgView = new ArrayList<>();

		List<String> listOfMsgEditRepeated = new ArrayList<>();
		List<String> listOfMsgViewRepeated = new ArrayList<>();


//		if (action.compareTo("save") == 0) {
//			User user = userdao.retrieveById(idUser);
//
//			List<LotCard> listLotCard = lotcarddao.retrieveAll();
//
//			for (LotCard lotCard : listLotCard) {
//
////				if (request.getParameter("edit_" + lotCard.getNameLot()).equals("true")) {
////					List<String> newListCardLotEdit = user.getListCardLots().getListLotsCardsEdit();
////					List<String> newListCardLotView = user.getListCardLots().getListLotsCardsView();
////
////					Boolean lotCardEditRepeated = false;
////					Boolean lotCardViewRepeated = false;
////
////					for (int i = 0; i < newListCardLotEdit.size(); i++) {
////						if (newListCardLotEdit.get(i).equals(lotCard.getNameLot())) {
////							lotCardEditRepeated = true;
////
////							listOfMsgEditRepeated.add("The card lot " + lotCard.getNameLot() + " for edit already this has the user " + user.getName());
////							
////							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Lot", "addCardLotsPost()", "", "", 
////									   "ID User: " + idUser + ", Name lot: " + lotCard.getNameLot(),
////									   "This user has the card lot what are you trying to add");
////						}
////					}
////
////					for (int i = 0; i < newListCardLotView.size(); i++) {
////						if (newListCardLotView.get(i).equals(lotCard.getNameLot())) {
////							lotCardViewRepeated = true;
////						}
////					}
////
////					if (!lotCardEditRepeated) {
////						ListCardLotsUser newList = new ListCardLotsUser();
////
////						newListCardLotEdit.add(lotCard.getNameLot());
////
////						if (!lotCardViewRepeated) {
////							newListCardLotView.add(lotCard.getNameLot());
////						}
////
////						newList.setListLotsCardsEdit(newListCardLotEdit);
////						newList.setListLotsCardsView(newListCardLotView);
////
////						user.setListCardLots(newList);
////						userdao.update(user);
////						
////						logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Card Lot", "addCardLotsPost()", "", "", 
////								   "ID User: " + idUser + ", Name lot: " + lotCard.getNameLot(), 
////								   "Successfully added the lot for edit to user");
////
////						listOfMsgEdit.add("Add card lot " + lotCard.getNameLot() + " for edit to user " + user.getName());
////					}
////				}
//
////				if (request.getParameter("view_" + lotCard.getNameLot()).equals("true")) {
////					List<String> newListCardLotView = user.getListCardLots().getListLotsCardsView();
////
////					Boolean lotCardViewRepeated = false;
////
////					for (int i = 0; i < newListCardLotView.size(); i++) {
////						if (newListCardLotView.get(i).equals(lotCard.getNameLot())) {
////							lotCardViewRepeated = true;
////							listOfMsgViewRepeated.add("The card lot " + lotCard.getNameLot() + " for view already this has the user " + user.getName());
////							
////							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Card Lot", "addCardLotsPost()", "", "", 
////									   "ID User: " + idUser + ", Name lot: " + lotCard.getNameLot(), 
////									   "This user has the card lot what are you trying to add");
////							
////							break;
////						}
////					}
////
////					if (!lotCardViewRepeated) {
////						newListCardLotView.add(lotCard.getNameLot());
////						user.getListCardLots().setListLotsCardsView(newListCardLotView);
////						userdao.update(user);
////						
////						logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Card Lot", "addCardLotsPost()", "", "", 
////								   "ID User: " + idUser + ", Name lot: " + lotCard.getNameLot(),
////								   "Successfully added the lot for view to template");
////						
////						listOfMsgView.add("Add card lot " + lotCard.getNameLot() + " for view to user " + user.getName());
////					}
////				}
//			}
//			
//			model.addAttribute("msgEdit", listOfMsgEdit);
//        	model.addAttribute("msgView", listOfMsgView);
//        	
//			model.addAttribute("msgEditRepeated", listOfMsgEditRepeated);
//			model.addAttribute("msgViewRepeated", listOfMsgViewRepeated);
//			
//			loadPage(model);
//			return "general_configuration";
//		}

		loadPage(model);
		return "general_configuration";
	}
	
//	@PostMapping("home/general/configuration/user/lots/card/delete/{idUser}")
//	public String deleteCardLotsPost(Model model, HttpServletRequest request,
//			@PathVariable String idUser,
//			@RequestParam (name = "action") String action) {
//		
//		User user = userdao.retrieveById(idUser);
//		List<LotCard> listLotCard = lotcarddao.retrieveAll();
//		
//		List<String> deleteLotsEdit = new ArrayList<>();
//		List<String> deleteLotsView = new ArrayList<>();
//
//		List<String> neverDeleteEdit = new ArrayList<>();
//		List<String> neverDeleteView = new ArrayList<>();
//		
//		if(action.compareTo("save") == 0) {
//			
//			for (LotCard lotCard : listLotCard) {
//				
////				if(request.getParameter("edit_" + lotCard.getNameLot()).equals("true")) {
////					
////					Boolean delete = false;
////					
////					for (int i = 0; i < user.getListCardLots().getListLotsCardsEdit().size(); i++) {
////						if(user.getListCardLots().getListLotsCardsEdit().get(i).equals(lotCard.getNameLot())) {
////							user.getListCardLots().getListLotsCardsEdit().remove(i);
////							deleteLotsEdit.add("Delete card lot " + lotCard.getNameLot() + " for edit in the user " + user.getName());
////							
////							logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Card Lot", "deleteCardLotsPost()", "", "", 
////									   "ID User: " + idUser + ", Name lot: " + lotCard.getNameLot(), 
////									   "Successfully deleted the card lot for edit in the user");
////							
////							delete = true;
////							break;
////						}
////					}
////					
////					for (int i = 0; i < user.getListCardLots().getListLotsCardsView().size(); i++) {
////						if(user.getListCardLots().getListLotsCardsView().get(i).equals(lotCard.getNameLot()) && delete) {
////							user.getListCardLots().getListLotsCardsView().remove(i);
////							deleteLotsView.add("Delete card lot " + lotCard.getNameLot() + " for view in the user " + user.getName());
////							
////							logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Card Lot", "deleteCardLotsPost()", "", "", 
////									   "ID User: " + idUser + ", Name lot: " + lotCard.getNameLot(), 
////									   "Successfully deleted the card lot for view in the user");
////							
////							break;
////						}
////					}
////					
////					if(delete) {
////						userdao.update(user);
////					} else {
////						neverDeleteEdit.add("Impossible delete card lot " + lotCard.getNameLot() + " for edit, the user " + user.getName() + " dont have this card lot");
////					
////						logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Card Lot", "deleteCardLotsPost()", "", "", 
////								   "ID User: " + idUser + ", Name lot: " + lotCard.getNameLot(), 
////								   "Could not delete the lot for edit because the user hasn't this card lot");
////					}
////				}
//				
////				if(request.getParameter("view_" + lotCard.getNameLot()).equals("true")) {
////					
////					Boolean delete = false;
////					Boolean inEdit = false;
////					
////					for (int i = 0; i < user.getListCardLots().getListLotsCardsEdit().size(); i++) {
////						if(user.getListCardLots().getListLotsCardsEdit().get(i).equals(lotCard.getNameLot())) {
////							inEdit = true;
////							neverDeleteView.add("Impossible delete card lot " + lotCard.getNameLot() + " for view, the user " + user.getName() + " have this card lot for edit");
////							
////							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Card Lot", "deleteCardLotsPost()", "", "", 
////									   "ID User: " + idUser + ", Name lot: " + lotCard.getNameLot(), 
////									   "Could not delete the card lot for view because the user has this card lot for edit");
////							
////							break;
////						}
////					}
////					
////					if (!inEdit) {
////						
////						for (int i = 0; i < user.getListCardLots().getListLotsCardsView().size(); i++) {
////							if (user.getListCardLots().getListLotsCardsView().get(i).equals(lotCard.getNameLot())) {
////								user.getListCardLots().getListLotsCardsView().remove(i);
////								deleteLotsView.add("Delete card lot " + lotCard.getNameLot() + " for view in the user " + user.getName());
////								
////								logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Card Lot", "deleteCardLotsPost()", "", "", 
////										   "ID User: " + idUser + ", Name lot: " + lotCard.getNameLot(), 
////										   "Successfully delete the card lot for view in the user");
////								
////								delete = true;
////								break;
////							}
////						}
////						
////						if (delete) {
////							userdao.update(user);
////						} else {
////							neverDeleteView.add("Impossible delete card lot " + lotCard.getNameLot() + " for view, the user " + user.getName() + " dont have this card lot");
////						
////							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Card Lot", "deleteCardLotsPost()", "", "", 
////									   "ID User: " + idUser + ", Name lot: " + lotCard.getNameLot(), 
////									   "Could not delete the card lot for view because the user hasn't this card lot");
////						}
////					}
////				}
//			}
//		
//			model.addAttribute("deleteLotsEdit", deleteLotsEdit);
//    		model.addAttribute("deleteLotsView", deleteLotsView);
//    		
//    		model.addAttribute("neverDeleteEdit", neverDeleteEdit);
//    		model.addAttribute("neverDeleteView", neverDeleteView);
//			
//			loadPage(model);
//			return "general_configuration";
//		}
//
//		loadPage(model);
//		return "general_configuration";
//	}
	
	@GetMapping("home/general/configuration/user/lots/card/view/{userId}")
	public String viewCardLotsGet(Model model,
			   @PathVariable String userId) {
		   
//		   ListCardLotsUser listOfLots = userdao.retrieveById(userId).getListCardLots();
//		   List<String> listOfEdit = listOfLots.getListLotsCardsEdit();
//		   List<String> listOfView = listOfLots.getListLotsCardsView();
//		   
//		   if(listOfEdit.isEmpty()) {
//			   listOfEdit.add("Empty");
//		   }
//		   
//		   if(listOfView.isEmpty()) {
//			   listOfView.add("Empty");
//		   }
//		   
//		   model.addAttribute("listOfView", listOfView);
//		   model.addAttribute("listOfEdit", listOfEdit);
//		   model.addAttribute("nameUser", userdao.retrieveById(userId).getName());

		   
		   return "user_view_lots_card";
	   }
	
   					/*						ROLE						*/
   
   @GetMapping("home/general/configuration/role/add")
   public String roleAddGet(Model model) {
	   
	   logger.logger("INFO", "SM-WEB", "General Configuration", "", "Role creation", "roleAddGet()", "", "", "", "A screen opens where can add a new role");
	   
	   return "role_add";
   }
   
   @PostMapping("home/general/configuration/role/add")
   public String roleAddPost(Model model,
		   @RequestParam("action") String action,
	       @RequestParam(name="name", required=true) String name) {
	   
	   name = name.toUpperCase();
	   
	   if (action.compareTo("save") == 0) {
		   if (!(name.equals(""))) {
			   
			   if(name.equals("VISITOR") || name.equals("SUPERADMIN") || name.equals("PRE-AUTHENTICATION")) {
				   model.addAttribute("msg1", "This name is reserved for a special role");
				   
				   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Role creation", "roleAddPost()", "", "", 
						   "Name role: " + name, 
						   "It is trying to use a name reserved");
				   
				   return "role_add";
			   }
			   
			   Role nameRole = rolesdao.retrieveByNameRole(name);
			   
			   if (nameRole == null) {
				   
				   if (action.compareTo("save") == 0) {
					   Role role = new Role();
					   role.setNameRole(name);
					   rolesdao.create(role);
					   
					   model.addAttribute("msg1", "Role created successfully");
					   
					   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Role creation", "roleAddPost()", "", "", 
							   "Name role: " + name, 
							   "Role created successfully");
					   
					   loadPage(model);
					   return "general_configuration";
				   }
				   
			   } else {
				   model.addAttribute("msg1", "Role name already exists");
				   
				   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Role creation", "roleAddPost()", "", "", 
						   "Name role: " + name, 
						   "Role name already exists");
				   
				   return "role_add";
			   }
			   
		   } else {
			   model.addAttribute("msg1", "Role name is empty");
			   
			   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Role creation", "roleAddPost()", "", "", 
					   "Name role: " + name, 
					   "Role name is empty");
			   
			   return "role_add";
		   }
	   }
	   
	   loadPage(model);
	   return "general_configuration";
   }
   
   					/*						LOTS						*/
   
   @GetMapping("home/general/configuration/lot/add")
   public String lotAddGet(Model model) {
	   
	   logger.logger("INFO", "SM-WEB", "General Configuration", "", "Lot creation", "lotAddGet()", "", "", "", "A screen opens where can add a new lot");
	   
	   return "lot_add";
   }
   
   @PostMapping("home/general/configuration/lot/add")
   public String lotAddPost(Model model,
		   @RequestParam("action") String action,
	       @RequestParam(name="name", required=true) String name) {
	   
	   name = name.toUpperCase();
	   
	   if (action.compareTo("save") == 0) {
		   if (!(name.equals(""))) {
			   
			   
//			   if (nameLote == null) {
//				   if (action.compareTo("save") == 0) {
//					   Lot lote = new Lot();
//					   lote.setNameOfLote(name);
//					   lotdao.create(lote);
//					   
//					   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Lot creation", "lotAddPost()", "", "", 
//							   "Name lot: " + name, 
//							   "Lot created successfully");
//					   
//					   model.addAttribute("msg1", "Lot created successfully");
//					   loadPage(model);
//					   return "general_configuration";
//				   }
//			   } else {
				   model.addAttribute("msg1", "Lot name already exists");
				   
				   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Lot creation", "lotAddPost()", "", "", 
						   "Name lot: " + name, 
						   "Lot name already exists");
				   
				   return "lote_add";
			   }
		   } else {
			   model.addAttribute("msg1", "Lot name is empty");
			   
			   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Lot creation", "lotAddPost()", "", "", 
					   "Name lot: " + name, 
					   "Lot name is empty");
			   
			   return "lote_add";
		   }
//	   }
	   return "general_configuration";
   }
   
   					/*						CARD LOTS				*/
   
   @GetMapping("home/general/configuration/lot/card/add")
   public String lotCardAddGet(Model model) {
	   
	   logger.logger("INFO", "SM-WEB", "General Configuration", "", "Card lot creation", "lotCardAddGet()", "", "", "", "A screen opens where can add a new card lot");
	   
	   return "lot_card_add";
   }
   
   @PostMapping("home/general/configuration/lot/card/add")
   public String lotCardAddPost(Model model, 
		   @RequestParam("action") String action,
	       @RequestParam(name="name", required=true) String name) {
	   
	   name = name.toUpperCase();
	   
//	   if (action.compareTo("save") == 0) {
//		   
//		   LotCard verifyNameLot = lotcarddao.retrieveByNameLotCard(name);
//		   
//		   if(name.equals("")) {
//			   model.addAttribute("msg1", "Card lot name is empty");
//			   
//			   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Card lot creation", "lotCardAddGet()", "", "", 
//					   "Name card lot: " + name, 
//					   "Card lot name is empty");
//			   
//			   return "lot_card_add";
//		   }
//		   
//		   if (verifyNameLot == null) {
//			   LotCard lotCard = new LotCard();
//			   
//			   lotCard.setNameLot(name);
//			   lotcarddao.create(lotCard);
//			   
//			   model.addAttribute("msg1", "Card lot created successfully");
//			   
//			   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Card lot creation", "lotCardAddGet()", "", "", 
//					   "Name card lot: " + name, 
//					   "Card lot created successfully");
//			   
//			   loadPage(model);
//			   return "general_configuration";
//		   } else {
//			   model.addAttribute("msg1", "Card lot name already exists");
//			   
//			   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Card lot creation", "lotCardAddGet()", "", "", 
//					   "Name card lot: " + name, 
//					   "Card lot name already exists");
//			   
//			   return "lot_card_add";
//		   }
//	   }
	   
	   loadPage(model);
	   return "general_configuration";
   }
   
   					/*						LOTS OF USER				*/
   
   @GetMapping("home/general/configuration/lot/users/{action}/{nameLote}")
   public String lotActionToUsersGet(Model model,
		   @PathVariable String nameLote,
		   @PathVariable String action) {
	   
//	   if(action.equals("add")) {
//		   List<SimpleUser> user= addUser();
//	       model.addAttribute("user", user);
//	       model.addAttribute("lote", nameLote);
//	       model.addAttribute("action", "add");
//	       model.addAttribute("nameAction", "Add");
//	       
//	       logger.logger("INFO", "SM-WEB", "General Configuration", "", "Add Lot", "lotActionToUsersGet()", "", "", "Name Lot: " + nameLote, "A screen opens where show all user for add lot");
//	       
//	       return "lot_action_users";
//	   }
	   
//	   if(action.equals("delete")) {
//		   List<SimpleUser> user= addUser();
//	       model.addAttribute("user", user);
//	       model.addAttribute("lote", nameLote);
//	       model.addAttribute("action", "delete");
//	       model.addAttribute("nameAction", "Delete");
//	       
//	       logger.logger("INFO", "SM-WEB", "General Configuration", "", "Delete Lot", "lotActionToUsersGet()", "", "", "Name Lot: " + nameLote, "A screen opens where show all user for delete lot");
//	       
//	       return "lot_action_users";
//	   }
	   
	   return "general_configuration";
   }
   
   @PostMapping("home/general/configuration/lot/users/add/{nameLote}")
   public String lotAddToUsersPost(Model model, HttpServletRequest request, 
			@PathVariable String nameLote,
			@RequestParam("action") String action) {

//		if (action.compareTo("save") == 0) {
//
//			List<User> listOfUser = userdao.retrieveAll();
//
//			List<String> listOfMsgEdit = new ArrayList<>();
//			List<String> listOfMsgView = new ArrayList<>();
//
//			List<String> listOfMsgEditRepeated = new ArrayList<>();
//			List<String> listOfMsgViewRepeated = new ArrayList<>();
//
//			for (User user : listOfUser) {
//
////				if (request.getParameter("edit_" + user.getId()).equals("true")) {
////					Boolean lotRepeated = false;
////					Boolean lotRepeatedInView = false; 
////
////					for (int i = 0; i < user.getListLots().getListEdit().size(); i++) {
////						if (user.getListLots().getListEdit().get(i).equals(lot.getNameOfLote())) {
////							lotRepeated = true;
////							listOfMsgEditRepeated.add("The lot " + lot.getNameOfLote() + " for edit already this has the user " + user.getName());
////							
////							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Lot", "lotAddToUsersPost()", "", "", 
////									"Name lot: " + nameLote + ", ID User: " + user.getId(), 
////									"This user has the lot what are you trying to add");
////							
////							break;
////						}
////					}
////					
////					for (int i = 0; i < user.getListLots().getListView().size(); i++) {
////						if (user.getListLots().getListView().get(i).equals(lot.getNameOfLote())) {
////							lotRepeatedInView = true;
////							break;
////						}
////					}
////
////					if (!lotRepeated) {
////						
////						user.getListLots().getListEdit().add(lot.getNameOfLote());
////						
////						if(!lotRepeatedInView) {
////							user.getListLots().getListView().add(lot.getNameOfLote());
////						}
////						
////						userdao.update(user);
////						
////						listOfMsgEdit.add("Add lot " + lot.getNameOfLote() + " for edit to user " + user.getName());
////						
////						logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Lot", "lotAddToUsersPost()", "", "", 
////								"Name lot: " + nameLote + ", ID User: " + user.getId(),
////								"Successfully added the lot for edit to user");
////					}
////				}
//
////				if (request.getParameter("view_" + user.getId()).equals("true")) {
////					
////					Boolean lotRepeated = false;
////
////					for (int i = 0; i < user.getListLots().getListView().size(); i++) {
////						if (user.getListLots().getListView().get(i).equals(lot.getNameOfLote())) {
////							lotRepeated = true;
////							listOfMsgViewRepeated.add("The lot " + lot.getNameOfLote() + " for view already this has the user " + user.getName());
////							
////							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Lot", "lotAddToUsersPost()", "", "", 
////									"Name lot: " + nameLote + ", ID User: " + user.getId(), 
////									"This user has the lot what are you trying to add");
////							
////							break;
////						}
////					}
////
////					if (!lotRepeated) {
////						user.getListLots().getListView().add(lot.getNameOfLote());
////						
////						userdao.update(user);
////						
////						listOfMsgView.add("Add lot " + lot.getNameOfLote() + " for view to user " + user.getName());
////						
////						logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Lot", "lotAddToUsersPost()", "", "", 
////								"Name lot: " + nameLote + ", ID User: " + user.getId(),
////								"Successfully added the lot for view to user");
////					}
////				}
//			}
//			
//			model.addAttribute("msgEdit", listOfMsgEdit);
//        	model.addAttribute("msgView", listOfMsgView);
//        	
//			model.addAttribute("msgEditRepeated", listOfMsgEditRepeated);
//			model.addAttribute("msgViewRepeated", listOfMsgViewRepeated);
//			
//			loadPage(model);
//			return "general_configuration";
//		}

		loadPage(model);
		return "general_configuration";
	}
   
   @PostMapping("home/general/configuration/lot/users/delete/{nameLote}")
   public String lotDeleteToUserPost(Model model, HttpServletRequest request,
		   @PathVariable String nameLote,
		   @RequestParam("action") String action) {
	   	   
	   List<String> deleteLotsEdit = new ArrayList<>();
		List<String> deleteLotsView = new ArrayList<>();

		List<String> neverDeleteEdit = new ArrayList<>();
		List<String> neverDeleteView = new ArrayList<>();
	   
//	   if (action.compareTo("save") == 0) {
//		   
//		   List<User> listOfUser = userdao.retrieveAll();
//		   
//		   for (User user : listOfUser) {
//			   
////			   if(request.getParameter("edit_" + user.getId()).equals("true")) {
////
////				   Boolean delete = false;
////				   
////				   for (int i = 0; i < user.getListLots().getListEdit().size(); i++) {
////					   if (user.getListLots().getListEdit().get(i).equals(lot.getNameOfLote())) {
////						   user.getListLots().getListEdit().remove(i);
////						   delete = true;
////						   deleteLotsEdit.add("Delete lot " + lot.getNameOfLote() + " for edit in the user " + user.getName());
////						   
////						   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToUserPost()", "", "", 
////								   "Name lot: " + nameLote + ", ID User: " + user.getId(), 
////								   "Successfully deleted the lot for edit in the user");
////						   
////						   break;
////					   }
////				   }
////				   
////				   for (int i = 0; i < user.getListLots().getListView().size(); i++) {
////					   if(user.getListLots().getListView().get(i).equals(lot.getNameOfLote()) && delete) {
////						   user.getListLots().getListView().remove(i);
////						   deleteLotsView.add("Delete lot " + lot.getNameOfLote() + " for view in the user " + user.getName());
////						   
////						   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToUserPost()", "", "", 
////								   "Name lot: " + nameLote + ", ID User: " + user.getId(), 
////								   "Successfully deleted the lot for view in the user");
////						   
////						   break;
////					   }
////				   }
////				   
////				   if (delete) {
////					   userdao.update(user);
////				   } else {
////					   neverDeleteEdit.add("Impossible delete lot " + lot.getNameOfLote() + " for edit, the user " + user.getName() + " dont have this lot");
////					   
////					   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToUserPost()", "", "", 
////							   "Name lot: " + nameLote + ", ID User: " + user.getId(),
////							   "Could not delete the lot for edit because the user hasn't this lot");
////				   }
////			   }
//			   
////			   if(request.getParameter("view_" + user.getId()).equals("true")) {
////
////				   Boolean delete = false;
////				   Boolean inEdit = false;
////				   
////				   for (int i = 0; i < user.getListLots().getListEdit().size(); i++) {
////					   if (user.getListLots().getListEdit().get(i).equals(lot.getNameOfLote())) {
////						   inEdit = true;
////						   neverDeleteEdit.add("Impossible delete lot " + lot.getNameOfLote() + " for view, the user " + user.getName() + " have this lot for edit");
////						   
////						   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToUserPost()", "", "", 
////								   "Name lot: " + nameLote + ", ID User: " + user.getId(), 
////								   "Could not delete the lot for view because the user has this lot for edit");
////						   
////						   break;
////					   }
////				   }
////				   
////				   if (!inEdit) {
////					   for (int i = 0; i < user.getListLots().getListView().size(); i++) {
////						   if (user.getListLots().getListView().get(i).equals(lot.getNameOfLote())) {
////							   inEdit = true;
////							   user.getListLots().getListView().remove(i);
////							   deleteLotsView.add("Delete lot " + lot.getNameOfLote() + " for view in the user " + user.getName());
////							   
////							   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToUserPost()", "", "", 
////									   "Name lot: " + nameLote + ", ID User: " + user.getId(), 
////									   "Successfully deleted the lot for view in the user");
////							   
////							   break;
////						   }
////					   }
////					   
////					   if (delete) {
////						   userdao.update(user);
////					   } else {
////						   neverDeleteView.add("Impossible delete lot " + lot.getNameOfLote() + " for edit, the user " + user.getName() + " dont have this lot");
////						   
////						   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToUserPost()", "", "", 
////								   "Name lot: " + nameLote + ", ID User: " + user.getId(),
////								   "Could not delete the lot for view because the user hasn't this lot");
////					   }
////				   }
////			   }
//		   }
//		   
//			model.addAttribute("deleteLotsEdit", deleteLotsEdit);
//			model.addAttribute("deleteLotsView", deleteLotsView);
//
//			model.addAttribute("neverDeleteEdit", neverDeleteEdit);
//			model.addAttribute("neverDeleteView", neverDeleteView);
//
//			loadPage(model);
//			return "general_configuration";
//	   }
	   
	   loadPage(model);
	   return "general_configuration";
   }
   
   					/*					LOTS OF TEMPLATE					*/
   
   @GetMapping("home/general/configuration/lot/templates/{action}/{nameLot}")
   public String lotActionToTemplatesGet(Model model,
		   @PathVariable String action,
		   @PathVariable String nameLot) {
	   
	   if (action.equals("add")) {
//		   Lot lot = lotdao.retrieveByNameLote(nameLot);
//		   List<TemplateIpp> listTemplate = templatedao.retrieve();
//		   
//		   model.addAttribute("lot", lot);
//		   model.addAttribute("listTemplate", listTemplate);
//		   model.addAttribute("nameAction", "Add");
//		   model.addAttribute("action", "add");
//		   
//		   logger.logger("INFO", "SM-WEB", "General Configuration", "", "Add Lot", "lotActionToTemplatesGet()", "", "", "Name Lot: " + nameLot, "A screen opens where show all template for add lot");
//		   
		   return "lot_action_templates";
	   }
	   
	   if (action.equals("delete")) {
//		   Lot lot = lotdao.retrieveByNameLote(nameLot);
//		   List<TemplateIpp> listTemplate = templatedao.retrieve();
//		   
//		   model.addAttribute("lot", lot);
//		   model.addAttribute("listTemplate", listTemplate);
//		   model.addAttribute("nameAction", "Delete");
//		   model.addAttribute("action", "delete");
//		   
//		   logger.logger("INFO", "SM-WEB", "General Configuration", "", "Delete Lot", "lotActionToTemplatesGet()", "", "", "Name Lot: " + nameLot, "A screen opens where show all template for delete lot");
//		   
		   return "lot_action_templates";
	   }
	   
	   loadPage(model);
	   return "general_configuration";
   }
   
   @PostMapping("home/general/configuration/lot/templates/add/{nameLot}")
   public String lotAddToTemplatesPost(Model model, HttpServletRequest request,
		   @PathVariable String nameLot,
		   @RequestParam (name = "action") String action) {
	   
		List<String> listOfMsgEdit = new ArrayList<>();
		List<String> listOfMsgView = new ArrayList<>();

		List<String> listOfMsgEditRepeated = new ArrayList<>();
		List<String> listOfMsgViewRepeated = new ArrayList<>();
	   
//	   if(action.compareTo("save") == 0) {
//		   Lot lot = lotdao.retrieveByNameLote(nameLot);
//		   List<TemplateIpp> listTemplate = templatedao.retrieve();
//		   
//		   for (TemplateIpp template : listTemplate) {
//			   
//			   if(request.getParameter("edit_" + template.getId()).equals("true")) {
//				   
//				   Boolean repeated = false;
//				   Boolean repeatedInView = false;
//				   
//				   for (int i = 0; i < template.getListOfEdit().size(); i++) {
//					   if(template.getListOfEdit().get(i).equals(lot.getNameOfLote())) {
//						   repeated = true;
//						   listOfMsgEditRepeated.add("The lot " + lot.getNameOfLote() + " for edit already this has the template with ID: " + template.getId());
//						   
//						   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Lot", "lotAddToTemplatesPost()", "", "", 
//									"Name lot: " + nameLot + ", ID Template: " + template.getId(), 
//									"This template has the lot what are you trying to add");
//						   
//						   break;
//					   }
//				   }
//				   
//				   for (int i = 0; i < template.getListOfView().size(); i++) {
//					   if(template.getListOfView().get(i).equals(lot.getNameOfLote())) {
//						   repeatedInView = true;
//						   break;
//					   }
//				   }
//				   
//				   if(!repeated) {
//					   template.getListOfEdit().add(lot.getNameOfLote());
//					   
//					   if(!repeatedInView) {
//						   template.getListOfView().add(lot.getNameOfLote());
//					   }
//					   
//					   templatedao.update(template);
//					   
//					   listOfMsgEdit.add("Add lot " + lot.getNameOfLote() + " for edit to template with ID: " + template.getId());
//					   
//					   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Lot", "lotAddToTemplatesPost()", "", "", 
//								"Name lot: " + nameLot + ", ID Template: " + template.getId(),
//								"Successfully added the lot for edit to template");
//				   }
//			   }
//			   
//			   if(request.getParameter("view_" + template.getId()).equals("true")) {
//				   
//				   Boolean repeated = false;
//				   
//				   for (int i = 0; i < template.getListOfView().size(); i++) {
//					   if(template.getListOfView().get(i).equals(lot.getNameOfLote())) {
//						   listOfMsgViewRepeated.add("The lot " + lot.getNameOfLote() + " for view already this has the template with ID: " + template.getId());
//						   
//						   logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Lot", "lotAddToTemplatesPost()", "", "", 
//									"Name lot: " + nameLot + ", ID Template: " + template.getId(), 
//									"This template has the lot what are you trying to add");
//						   
//						   repeated = true;
//						   break;
//					   }
//				   }
//				   
//				   if(!repeated) {
//					   template.getListOfView().add(lot.getNameOfLote());
//					   templatedao.update(template);
//					   listOfMsgView.add("Add lot " + lot.getNameOfLote() + " for view to template with ID: " + template.getId());
//					   
//					   logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Lot", "lotAddToTemplatesPost()", "", "", 
//								"Name lot: " + nameLot + ", ID Template: " + template.getId(),
//								"Successfully added the lot for view to template");
//				   }
//			   }
//		   }
//		   
//			model.addAttribute("msgEdit", listOfMsgEdit);
//			model.addAttribute("msgView", listOfMsgView);
//
//			model.addAttribute("msgEditRepeated", listOfMsgEditRepeated);
//			model.addAttribute("msgViewRepeated", listOfMsgViewRepeated);
//
//			loadPage(model);
//			return "general_configuration";
//	   }
	   
	   loadPage(model);
	   return "general_configuration";
   }
   
	@PostMapping("home/general/configuration/lot/templates/delete/{nameLot}")
	public String lotDeleteToTemplatesPost(Model model, HttpServletRequest request, 
			@PathVariable String nameLot,
			@RequestParam(name = "action") String action) {

		List<String> deleteLotsEdit = new ArrayList<>();
		List<String> deleteLotsView = new ArrayList<>();

		List<String> neverDeleteEdit = new ArrayList<>();
		List<String> neverDeleteView = new ArrayList<>();

//		if (action.compareTo("save") == 0) {
//
//			Lot lot = lotdao.retrieveByNameLote(nameLot);
//			List<TemplateIpp> listTemplate = templatedao.retrieve();
//
//			for (TemplateIpp template : listTemplate) {
//
//				if (request.getParameter("edit_" + template.getId()).equals("true")) {
//
//					Boolean delete = false;
//
//					for (int i = 0; i < template.getListOfEdit().size(); i++) {
//						if (template.getListOfEdit().get(i).equals(lot.getNameOfLote())) {
//							template.getListOfEdit().remove(i);
//							delete = true;
//							deleteLotsEdit.add("Delete lot " + lot.getNameOfLote() + " for edit in the template with ID " + template.getId());
//							
//							logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToTemplatesPost()", "", "", 
//									   "Name lot: " + nameLot + ", ID Template: " + template.getId(), 
//									   "Successfully deleted the lot for edit in the template");
//							
//							break;
//						}
//					}
//
//					for (int i = 0; i < template.getListOfView().size(); i++) {
//						if (template.getListOfView().get(i).equals(lot.getNameOfLote()) && delete) {
//							template.getListOfView().remove(i);
//							deleteLotsView.add("Delete lot " + lot.getNameOfLote() + " for view in the template with ID " + template.getId());
//							
//							logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToTemplatesPost()", "", "", 
//									   "Name lot: " + nameLot + ", ID Template: " + template.getId(), 
//									   "Successfully deleted the lot for view in the template");
//							
//							break;
//						}
//					}
//
//					if (delete) {
//						templatedao.update(template);
//					} else {
//						neverDeleteView.add("Impossible delete lot " + lot.getNameOfLote() + " for edit, the template with ID " + template.getId() + " dont have this lot");
//						
//						logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToUserPost()", "", "", 
//								   "Name lot: " + nameLot + ", ID Template: " + template.getId(),
//								   "Could not delete the lot for edit because the template hasn't this lot");
//					}
//				}
//
//				if (request.getParameter("view_" + template.getId()).equals("true")) {
//
//					Boolean delete = false;
//					Boolean inEdit = false;
//
//					for (int i = 0; i < template.getListOfEdit().size(); i++) {
//						if (template.getListOfEdit().get(i).equals(lot.getNameOfLote())) {
//							inEdit = true;
//							neverDeleteEdit.add("Impossible delete lot " + lot.getNameOfLote() + " for view, the template wit ID " + template.getId() + " have this lot for edit");
//							
//							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToUserPost()", "", "", 
//									   "Name lot: " + nameLot + ", ID Template: " + template.getId(), 
//									   "Could not delete the lot for view because the template has this lot for edit");
//							
//							break;
//						}
//					}
//
//					if (!inEdit) {
//
//						for (int i = 0; i < template.getListOfView().size(); i++) {
//							if (template.getListOfView().get(i).equals(lot.getNameOfLote())) {
//								template.getListOfView().remove(i);
//								delete = true;
//								deleteLotsView.add("Delete lot " + lot.getNameOfLote() + " for view in the template with ID " + template.getId());
//								
//								logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToTemplatesPost()", "", "", 
//										   "Name lot: " + nameLot + ", ID Template: " + template.getId(), 
//										   "Successfully deleted the lot for view in the template");
//								
//								break;
//							}
//						}
//
//						if (delete) {
//							templatedao.update(template);
//						} else {
//							neverDeleteView.add("Impossible delete lot " + lot.getNameOfLote() + " for view, the template with ID " + template.getId() + " dont have this lot");
//							
//							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToUserPost()", "", "", 
//									   "Name lot: " + nameLot + ", ID Template: " + template.getId(),
//									   "Could not delete the lot for view because the template hasn't this lot");
//						}
//					}
//				}
//			}
//
//			model.addAttribute("deleteLotsEdit", deleteLotsEdit);
//			model.addAttribute("deleteLotsView", deleteLotsView);
//
//			model.addAttribute("neverDeleteEdit", neverDeleteEdit);
//			model.addAttribute("neverDeleteView", neverDeleteView);
//
//			loadPage(model);
//			return "general_configuration";
//		}

		loadPage(model);
		return "general_configuration";
	}
   
   					/*					LOTS OF IPP						*/
	
	@GetMapping("home/general/configuration/lot/ipps/{action}/{nameLot}")
	public String lotActionToIppsGet(Model model,
			@PathVariable String nameLot,
			@PathVariable String action) {
//		
//		Lot lot = lotdao.retrieveByNameLote(nameLot);
//    	List<PresetCommand> preset = presetdao.retrieve();
//        List<SimpleIpp> ipps=new ArrayList<>();
//        
//        for(PresetCommand aux: preset){
//            SimpleIpp simpleIpp= new SimpleIpp(aux);
//            ipps.add(simpleIpp);
//        }
//		
//		if(action.equals("add")) {
//			model.addAttribute("ippList", ipps);
//			model.addAttribute("lot", lot);
//	        model.addAttribute("action", "add");
//	        model.addAttribute("nameAction", "Add");
//	        
//	        logger.logger("INFO", "SM-WEB", "General Configuration", "", "Add Lot", "lotActionToIppsGet()", "", "", "Name Lot: " + nameLot, "A screen opens where show all IPP for add lot");
//	        
//	        return "lot_action_ipps";
//		}
//		
//		if(action.equals("delete")) {
//			model.addAttribute("ippList", ipps);
//			model.addAttribute("lot", lot);
//	        model.addAttribute("action", "delete");
//	        model.addAttribute("nameAction", "Delete");
//	        
//	        logger.logger("INFO", "SM-WEB", "General Configuration", "", "Delete Lot", "lotActionToIppsGet()", "", "", "Name Lot: " + nameLot, "A screen opens where show all IPP for delete lot");
//	        
//	        return "lot_action_ipps";
//		}
//		
		loadPage(model);
		return "general_configuration";
	}
	
	@PostMapping("home/general/configuration/lot/ipps/add/{nameLot}")
	public String lotAddToIppsPost(Model model, HttpServletRequest request,
			@PathVariable String nameLot,
			@RequestParam (name = "action") String action) {
		
		List<String> listOfMsgEdit = new ArrayList<>();
		List<String> listOfMsgView = new ArrayList<>();

		List<String> listOfMsgEditRepeated = new ArrayList<>();
		List<String> listOfMsgViewRepeated = new ArrayList<>();
		
//		if (action.compareTo("save") == 0) {
//			
//			Lot lot = lotdao.retrieveByNameLote(nameLot);
//			List<PresetCommand> presetList = presetdao.retrieve();
//			
//			for (PresetCommand preset: presetList) {
//				
//				if(request.getParameter("edit_" + preset.getId()).equals("true")) {
//					
//					Boolean repeated = false;
//					Boolean repeatedInView = false;
//					
//					for (int i = 0; i < preset.getListOfEditLots().size(); i++) {
//						if(preset.getListOfEditLots().get(i).equals(lot.getNameOfLote())) {
//							listOfMsgEditRepeated.add("The lot " + lot.getNameOfLote() + " for edit already this has the IPP with ID: " + preset.getId());
//							
//							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Lot", "lotAddToIppsPost()", "", "", 
//									"Name lot: " + nameLot + ", ID IPP: " + preset.getId(), 
//									"This IPP has the lot what are you trying to add");
//							
//							repeated = true;
//							break;
//						}
//					}
//					
//					for (int i = 0; i < preset.getListOfViewLots().size(); i++) {
//						if(preset.getListOfViewLots().get(i).equals(lot.getNameOfLote())) {
//							repeatedInView = true;
//							break;
//						}
//					}
//					
//					if (!repeated) {
//						preset.getListOfEditLots().add(lot.getNameOfLote());
//						
//						if(!repeatedInView) {
//							preset.getListOfViewLots().add(lot.getNameOfLote());
//						}
//						
//						presetdao.update(preset);
//						listOfMsgEdit.add("Add lot " + lot.getNameOfLote() + " for edit to IPP with ID: " + preset.getId());
//						
//						logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Lot", "lotAddToIppsPost()", "", "", 
//								"Name lot: " + nameLot + ", ID IPP: " + preset.getId(),
//								"Successfully added the lot for edit to IPP");
//					}
//				}
//				
//				if(request.getParameter("view_" + preset.getId()).equals("true")) {
//					
//					Boolean repeated = false;
//					
//					for (int i = 0; i < preset.getListOfViewLots().size(); i++) {
//						if(preset.getListOfViewLots().get(i).equals(lot.getNameOfLote())) {
//							repeated = true;
//							listOfMsgViewRepeated.add("The lot " + lot.getNameOfLote() + " for view already this has the IPP with ID: " + preset.getId());
//							
//							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Lot", "lotAddToIppsPost()", "", "", 
//									"Name lot: " + nameLot + ", ID IPP: " + preset.getId(), 
//									"This IPP has the lot what are you trying to add");
//							
//							break;
//						}
//					}
//					
//					if(!repeated) {
//						preset.getListOfViewLots().add(lot.getNameOfLote());
//						presetdao.update(preset);
//						listOfMsgView.add("Add lot " + lot.getNameOfLote() + " for view to IPP with ID: " + preset.getId());
//						
//						logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Lot", "lotAddToIppsPost()", "", "", 
//								"Name lot: " + nameLot + ", ID IPP: " + preset.getId(),
//								"Successfully added the lot for view to IPP");
//					}
//				}
//			}
//			
//			model.addAttribute("msgEdit", listOfMsgEdit);
//			model.addAttribute("msgView", listOfMsgView);
//
//			model.addAttribute("msgEditRepeated", listOfMsgEditRepeated);
//			model.addAttribute("msgViewRepeated", listOfMsgViewRepeated);
//
//			loadPage(model);
//			return "general_configuration";
//		}
		
		loadPage(model);
		return "general_configuration";
	}
	
	@PostMapping("home/general/configuration/lot/ipps/delete/{nameLot}")
	public String lotDeleteToIppsPost(Model model, HttpServletRequest request,
			@PathVariable String nameLot,
			@RequestParam (name = "action") String action) {
		
		List<String> deleteLotsEdit = new ArrayList<>();
		List<String> deleteLotsView = new ArrayList<>();

		List<String> neverDeleteEdit = new ArrayList<>();
		List<String> neverDeleteView = new ArrayList<>();
		
//		if(action.compareTo("save") == 0) {
//			Lot lot = lotdao.retrieveByNameLote(nameLot);
//			List<PresetCommand> listPreset = presetdao.retrieve();
//			
//			for (PresetCommand preset : listPreset) {
//				if (request.getParameter("edit_" + preset.getId()).equals("true")) {
//					Boolean delete = false;
//					
//					for (int i = 0; i < preset.getListOfEditLots().size(); i++) {
//						if(preset.getListOfEditLots().get(i).equals(lot.getNameOfLote())) {
//							preset.getListOfEditLots().remove(i);
//							delete = true;
//							deleteLotsEdit.add("Delete lot " + lot.getNameOfLote() + " for edit in the IPP with ID " + preset.getId());
//							
//							logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToIppsPost()", "", "", 
//									   "Name lot: " + nameLot + ", ID IPP: " + preset.getId(), 
//									   "Successfully deleted the lot for edit in the IPP");
//							
//							break;
//						}
//					}
//					
//					for (int i = 0; i < preset.getListOfViewLots().size(); i++) {
//						if(preset.getListOfViewLots().get(i).equals(lot.getNameOfLote()) && delete) {
//							preset.getListOfViewLots().remove(i);
//							deleteLotsView.add("Delete lot " + lot.getNameOfLote() + " for view in the IPP with ID " + preset.getId());
//							
//							logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToIppsPost()", "", "", 
//									   "Name lot: " + nameLot + ", ID IPP: " + preset.getId(), 
//									   "Successfully deleted the lot for view in the IPP");
//							
//							break;
//						}
//					}
//					
//					if (delete) {
//						presetdao.update(preset);
//					} else {
//						neverDeleteView.add("Impossible delete lot " + lot.getNameOfLote() + " for edit, the IPP with ID " + preset.getId() + " dont have this lot");
//						
//						logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToIppsPost()", "", "", 
//								   "Name lot: " + nameLot + ", ID IPP: " + preset.getId(),
//								   "Could not delete the lot for edit because the IPP hasn't this lot");
//					}
//				}
//				
//				if (request.getParameter("view_" + preset.getId()).equals("true")) {
//					Boolean delete = false;
//					Boolean inEdit = false;
//					
//					for (int i = 0; i < preset.getListOfEditLots().size(); i++) {
//						if(preset.getListOfEditLots().get(i).equals(lot.getNameOfLote())) {
//							inEdit = true;
//							neverDeleteEdit.add("Impossible delete lot " + lot.getNameOfLote() + " for view, the IPP wit ID " + preset.getId() + " have this lot for edit");
//							
//							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToIppsPost()", "", "", 
//									   "Name lot: " + nameLot + ", ID IPP: " + preset.getId(), 
//									   "Could not delete the lot for view because the IPP has this lot for edit");
//							
//							break;
//						}
//					}
//					
//					if(!inEdit) {
//						
//						for (int i = 0; i < preset.getListOfViewLots().size(); i++) {
//							if(preset.getListOfViewLots().get(i).equals(lot.getNameOfLote())) {
//								preset.getListOfViewLots().remove(i);
//								delete = true;
//								deleteLotsView.add("Delete lot " + lot.getNameOfLote() + " for view in the IPP with ID " + preset.getId());
//								
//								logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToIppsPost()", "", "", 
//										   "Name lot: " + nameLot + ", ID IPP: " + preset.getId(), 
//										   "Successfully deleted the lot for view in the IPP");
//								
//								break;
//							}
//						}
//						
//						if (delete) {
//							presetdao.update(preset);
//						} else {
//							neverDeleteView.add("Impossible delete lot " + lot.getNameOfLote() + " for view, the IPP with ID " + preset.getId() + " dont have this lot");
//							
//							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "lotDeleteToIppsPost()", "", "", 
//									   "Name lot: " + nameLot + ", ID IPP: " + preset.getId(),
//									   "Could not delete the lot for view because the IPP hasn't this lot");
//						}
//					}
//				}
//			}
//			
//			model.addAttribute("deleteLotsEdit", deleteLotsEdit);
//			model.addAttribute("deleteLotsView", deleteLotsView);
//
//			model.addAttribute("neverDeleteEdit", neverDeleteEdit);
//			model.addAttribute("neverDeleteView", neverDeleteView);
//
//			loadPage(model);
//			return "general_configuration";
//		}
		
		loadPage(model);
		return "general_configuration";
	}
   					
					/*					CARD LOTS OF USER				*/
   
   @GetMapping("home/general/configuration/lot/card/user/{action}/{nameLotCard}")
   public String lotCardActionToUserGet(Model model,
		   @PathVariable String nameLotCard,
		   @PathVariable String action) {
	   
//	   if (action.equals("add")) {
//		   List<SimpleUser> user = addUser();
//		   model.addAttribute("user", user);
//		   model.addAttribute("lotCard", nameLotCard);
//		   model.addAttribute("action", "add");
//		   model.addAttribute("nameAction", "Add");
//		   
//		   logger.logger("INFO", "SM-WEB", "General Configuration", "", "Add Card Lot", "lotCardActionToUserGet()", "", "", "Name Card lot: " + nameLotCard, "A screen opens where show all user for add card lot");
//		   
//		   return "lot_card_action_users";
//	   }
	   
//	   if (action.equals("delete")) {
//		   List<SimpleUser> user = addUser();
//		   model.addAttribute("user", user);
//		   model.addAttribute("lotCard", nameLotCard);
//		   model.addAttribute("action", "delete");
//		   model.addAttribute("nameAction", "Delete");
//		   
//		   logger.logger("INFO", "SM-WEB", "General Configuration", "", "Delete Card Lot", "lotCardActionToUserGet()", "", "", "Name Card lot: " + nameLotCard, "A screen opens where show all user for delete card lot");
//		   
//		   return "lot_card_action_users";
//	   }
	   
	   return "general_configuration";
   }
   
   @PostMapping("home/general/configuration/lot/card/user/add/{nameLotCard}")
   public String lotCardAddToUserPost(Model model, HttpServletRequest request,
		   @PathVariable String nameLotCard,
		   @RequestParam (name = "action") String action) {
	   
//		if (action.compareTo("save") == 0) {
//			List<User> listUser = userdao.retrieveAll();
//
//			List<String> listOfMsgEdit = new ArrayList<>();
//			List<String> listOfMsgView = new ArrayList<>();
//
//			List<String> listOfMsgEditRepeated = new ArrayList<>();
//			List<String> listOfMsgViewRepeated = new ArrayList<>();
//
//			for (User user : listUser) {
//
////				if (request.getParameter("edit_" + user.getId()).equals("true")) {
//////					ListCardLotsUser listLots = user.getListCardLots();
//////
//////					Boolean lotRepeated = false;
//////					Boolean lotRepeatedInView = false;
////
//////					for (int i = 0; i < listLots.getListLotsCardsEdit().size(); i++) {
//////						if (listLots.getListLotsCardsEdit().get(i).equals(lotCard.getNameLot())) {
//////							lotRepeated = true;
//////							listOfMsgEditRepeated.add("The card lot " + lotCard.getNameLot() + " for edit already this has the user " + user.getName());
//////							
//////							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Card lot", "lotCardAddToUserPost()", "", "", 
//////									"Name card lot: " + nameLotCard + ", ID User: " + user.getId(), 
//////									"This user has the card lot what are you trying to add");
//////						}
//////					}
////					
////					for (int i = 0; i < listLots.getListLotsCardsView().size(); i++) {
////						if (listLots.getListLotsCardsView().get(i).equals(lotCard.getNameLot())) {
////							lotRepeatedInView = true;
////						}
////					}
////
////					if (!lotRepeated) {
////						List<String> newListLotsEdit = listLots.getListLotsCardsEdit();
////												
////
////						newListLotsEdit.add(lotCard.getNameLot());
////						
////						if (!lotRepeatedInView) {
////							List<String> newListLotsView = listLots.getListLotsCardsView();
////							newListLotsView.add(lotCard.getNameLot());
////							listLots.setListLotsCardsView(newListLotsView);
////							
////						}
////						
////						listLots.setListLotsCardsEdit(newListLotsEdit);
////						
////						userdao.update(user);
////
////						listOfMsgEdit.add("Add card lot " + lotCard.getNameLot() + " for edit to user " + user.getName());
////						
////						logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Card lot", "lotCardAddToUserPost()", "", "", 
////								"Name card lot: " + nameLotCard + ", ID User: " + user.getId(),
////								"Successfully added the card lot for edit to user");
////					}
////				}
//
////				if (request.getParameter("view_" + user.getId()).equals("true")) {
////					ListCardLotsUser listLots = user.getListCardLots();
////
////					Boolean lotRepeated = false;
////
////					for (int i = 0; i < listLots.getListLotsCardsView().size(); i++) {
////						if (listLots.getListLotsCardsView().get(i).equals(lotCard.getNameLot())) {
////							lotRepeated = true;
////							listOfMsgViewRepeated.add("The card lot " + lotCard.getNameLot() + " for view already this has the user " + user.getName());
////							
////							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Card lot", "lotCardAddToUserPost()", "", "", 
////									"Name card lot: " + nameLotCard + ", ID User: " + user.getId(), 
////									"This user has the card lot what are you trying to add");
////						}
////					}
////
////					if (!lotRepeated) {
////
////						List<String> newListLotsCardsView = listLots.getListLotsCardsView();
////
////						newListLotsCardsView.add(lotCard.getNameLot());
////						listLots.setListLotsCardsView(newListLotsCardsView);
////
////						userdao.update(user);
////
////						listOfMsgView.add("Add card lot " + lotCard.getNameLot() + " for view to user " + user.getName());
////						
////						logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Card lot", "lotCardAddToUserPost()", "", "", 
////								"Name card lot: " + nameLotCard + ", ID User: " + user.getId(),
////								"Successfully added the card lot for view to user");
////					}
////				}
//			}
//			
//			model.addAttribute("msgEdit", listOfMsgEdit);
//        	model.addAttribute("msgView", listOfMsgView);
//        	
//			model.addAttribute("msgEditRepeated", listOfMsgEditRepeated);
//			model.addAttribute("msgViewRepeated", listOfMsgViewRepeated);
//			
//			loadPage(model);
//			return "general_configuration";
//		}
	   
		loadPage(model);
		return "general_configuration";
   }

   @PostMapping("home/general/configuration/lot/card/user/delete/{nameLotCard}")
   public String lotCardDeleteToUserPost(Model model, HttpServletRequest request,
		   @PathVariable String nameLotCard,
		   @RequestParam (name = "action") String action) {
	   
		List<String> deleteLotsEdit = new ArrayList<>();
		List<String> deleteLotsView = new ArrayList<>();

		List<String> neverDeleteEdit = new ArrayList<>();
		List<String> neverDeleteView = new ArrayList<>();
	   
//		if (action.compareTo("save") == 0) {
//
//			List<User> listUsers = userdao.retrieveAll();
//
//			for (User user : listUsers) {
//
////				if (request.getParameter("edit_" + user.getId()).equals("true")) {
////					ListCardLotsUser listLots = user.getListCardLots();
////					List<String> newListEdit = listLots.getListLotsCardsEdit();
////					List<String> newListView = listLots.getListLotsCardsView();
////					LotCard lotCard = lotcarddao.retrieveByNameLotCard(nameLotCard);
////
////					Boolean deleted = false;
////
////					for (int i = 0; i < newListEdit.size(); i++) {
////						if (newListEdit.get(i).equals(lotCard.getNameLot())) {
////							newListEdit.remove(i);
////							deleted = true;
////							deleteLotsEdit.add("Delete card lot " + lotCard.getNameLot() + " for edit in the user " + user.getName());
////							
////							logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Card lot", "lotCardDeleteToUserPost()", "", "", 
////									   "Name card lot: " + nameLotCard + ", ID User: " + user.getId(), 
////									   "Successfully deleted the card lot for edit in the user");
////							
////							break;
////						}
////					}
////					
////					for (int i = 0; i < newListView.size(); i++) {
////						if(newListView.get(i).equals(lotCard.getNameLot()) && deleted) {
////							newListView.remove(i);
////							deleteLotsView.add("Delete card lot " + lotCard.getNameLot() + " for view in the user " + user.getName());
////							
////							logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Card lot", "lotCardDeleteToUserPost()", "", "", 
////									   "Name card lot: " + nameLotCard + ", ID User: " + user.getId(), 
////									   "Successfully deleted the card lot for view in the user");
////							
////							break;
////						}
////					}
////
////					if (deleted) {
////						listLots.setListLotsCardsEdit(newListEdit);
////						userdao.update(user);
////					} else {
////						neverDeleteEdit.add("Impossible delete card lot " + lotCard.getNameLot() + " for edit, the user " + user.getName() + " dont have this lot");
////						
////						logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Card Lot", "lotCardDeleteToUserPost()", "", "", 
////								   "Name card lot: " + nameLotCard + ", ID User: " + user.getId(),
////								   "Could not delete the card lot for edit because the user hasn't this card lot");
////					}
////				}
//				
////				if(request.getParameter("view_" + user.getId()).equals("true")) {
////					ListCardLotsUser listLots = user.getListCardLots();
////					List<String> newListView = listLots.getListLotsCardsView();
////					List<String> verifyListEdit = listLots.getListLotsCardsEdit();
////					LotCard lotCard = lotcarddao.retrieveByNameLotCard(nameLotCard);
////					
////					Boolean deleted = false;
////					Boolean inEdit = false;
////					
////					for (int i = 0; i < verifyListEdit.size(); i++) {
////						if(verifyListEdit.get(i).equals(lotCard.getNameLot())) {
////							inEdit = true;
////							
////							neverDeleteEdit.add("Impossible delete card lot " + lotCard.getNameLot() + " for view, the user " + user.getName() + " have this lot for edit");
////							
////							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "lotCardDeleteToUserPost()", "", "", 
////									   "Name card lot: " + nameLotCard + ", ID IPP: " + user.getId(), 
////									   "Could not delete the card lot for view because the user has this card lot for edit");
////						}
////					}
////					
////					if (!inEdit) {
////						for (int i = 0; i < newListView.size(); i++) {
////							if (newListView.get(i).equals(lotCard.getNameLot())) {
////								newListView.remove(i);
////								deleted = true;
////								
////								deleteLotsView.add("Delete card lot " + lotCard.getNameLot() + " for view in the user " + user.getName());
////								
////								logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Card lot", "lotCardDeleteToUserPost()", "", "", 
////										   "Name card lot: " + nameLotCard + ", ID User: " + user.getId(), 
////										   "Successfully deleted the lot for view in the user");
////							}
////						}
////						
////						if (deleted) {
////							listLots.setListLotsCardsView(newListView);
////							userdao.update(user);
////						} else {
////							neverDeleteView.add("Impossible delete card lot " + lotCard.getNameLot() + " for view, the user " + user.getName() + " dont have this lot");
////							
////							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Card Lot", "lotCardDeleteToUserPost()", "", "", 
////									   "Name card lot: " + nameLotCard + ", ID User: " + user.getId(),
////									   "Could not delete the card lot for view because the user hasn't this card lot");
////						}
////					}
////				}
//			}
//			
//			model.addAttribute("deleteLotsEdit", deleteLotsEdit);
//    		model.addAttribute("deleteLotsView", deleteLotsView);
//    		
//    		model.addAttribute("neverDeleteEdit", neverDeleteEdit);
//    		model.addAttribute("neverDeleteView", neverDeleteView);
//    		
//    		loadPage(model);
//    		return "general_configuration";
//		}

		loadPage(model);
		return "general_configuration";
   }
   
					/*				CARD LOTS OF eUICC					*/
	
	@GetMapping("home/general/configuration/lot/card/euicc/{action}/{nameCardLot}")
	public String lotCardActionToCardsGet(Model model,
			@PathVariable ("action") String action,
			@PathVariable ("nameCardLot") String nameCardLot) {
		
		
//		if(action.equals("add")) {
//			List<Card> listCard = carddao.retrieve();
//			List<SimpleCard> listSimpleCard = new ArrayList<>();
//			
//			for (Card card : listCard) {
//				listSimpleCard.add(new SimpleCard(card));
//			}
//			
//			model.addAttribute("cardLot", lotCard);
//			model.addAttribute("cardsFound", listSimpleCard);
//			model.addAttribute("action", "add");
//			model.addAttribute("nameAction", "Add");
//			
//			logger.logger("INFO", "SM-WEB", "General Configuration", "", "Add Card Lot", "lotCardActionToCardsGet()", "", "", "Name Card lot: " + nameCardLot, "A screen opens where show all eUICC for add card lot");
//			
//			return "lot_card_action_euiccs";
//		}
		
//		if(action.equals("delete")) {
//			List<Card> listCard = carddao.retrieve();
//			List<SimpleCard> listSimpleCard = new ArrayList<>();
//			
//			for (Card card : listCard) {
//				listSimpleCard.add(new SimpleCard(card));
//			}
//			
//			model.addAttribute("cardLot", lotCard);
//			model.addAttribute("cardsFound", listSimpleCard);
//			model.addAttribute("action", "delete");
//			model.addAttribute("nameAction", "Delete");
//			
//			logger.logger("INFO", "SM-WEB", "General Configuration", "", "Delete Card Lot", "lotCardActionToCardsGet()", "", "", "Name Card lot: " + nameCardLot, "A screen opens where show all eUICC for delete card lot");
//			
//			return "lot_card_action_euiccs";
//		}
		
		loadPage(model);
		return "general_configuration";
	}
	
	@PostMapping("home/general/configuration/lot/card/euicc/add/{nameCardLot}")
	public String lotCardAddToCardPost (Model model, HttpServletRequest request,
			@PathVariable String nameCardLot,
			@RequestParam (name = "action") String action) {
		
		List<String> listOfMsgEdit = new ArrayList<>();
		List<String> listOfMsgView = new ArrayList<>();

		List<String> listOfMsgEditRepeated = new ArrayList<>();
		List<String> listOfMsgViewRepeated = new ArrayList<>();
		
//		if(action.compareTo("save") == 0) {
//			LotCard lotCard = lotcarddao.retrieveByNameLotCard(nameCardLot);
//			List<Card> listCard = carddao.retrieve();
//			
//			for (Card card : listCard) {
//				
//				if(request.getParameter("edit_" + bytesToHex(card.getEumSignedInfo().getEid())).equals("true")) {
//					Boolean repeated = false;
//					Boolean repeatedInView = false;
//					
//					for (int i = 0; i < card.getListLotsEdit().size(); i++) {
//						if(card.getListLotsEdit().get(i).equals(lotCard.getNameLot())) {
//							repeated = true;
//							listOfMsgEditRepeated.add("The card lot " + lotCard.getNameLot() + " for edit already this has the card " + bytesToHex(card.getEumSignedInfo().getEid()));
//							
//							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Card lot", "lotCardAddToCardPost()", "", "", 
//									"Name card lot: " + nameCardLot + ", ID eUICC: " + card.getId(), 
//									"This eUICC has the card lot what are you trying to add");
//							
//							break;
//						}
//					}
//					
//					for (int i = 0; i < card.getListLotsView().size(); i++) {
//						if(card.getListLotsView().get(i).equals(lotCard.getNameLot())) {
//							repeatedInView = true;
//							break;
//						}
//					}
//					
//					if (!repeated) {
//						card.getListLotsEdit().add(lotCard.getNameLot());
//						
//						if(!repeatedInView) {
//							card.getListLotsView().add(lotCard.getNameLot());
//						}
//						
//						carddao.update(card);
//						listOfMsgEdit.add("Add card lot " + lotCard.getNameLot() + " for edit to card " + bytesToHex(card.getEumSignedInfo().getEid()));
//						
//						logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Card lot", "lotCardAddToCardPost()", "", "", 
//								"Name card lot: " + nameCardLot + ", ID eUICC: " + card.getId(),
//								"Successfully added the card lot for edit to eUICC");
//					}
//				}
//				
//				if(request.getParameter("view_" + bytesToHex(card.getEumSignedInfo().getEid())).equals("true")) {
//					Boolean repeated = false;
//					
//					for (int i = 0; i < card.getListLotsView().size(); i++) {
//						if(card.getListLotsView().get(i).equals(lotCard.getNameLot())) {
//							repeated = true;
//							listOfMsgViewRepeated.add("The card lot " + lotCard.getNameLot() + " for view already this has the card " + bytesToHex(card.getEumSignedInfo().getEid()));
//							
//							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Add Card lot", "lotCardAddToCardPost()", "", "", 
//									"Name card lot: " + nameCardLot + ", ID eUICC: " + card.getId(), 
//									"This eUICC has the card lot what are you trying to add");
//							
//							break;
//						}
//					}
//					
//					if(!repeated) {
//						card.getListLotsView().add(lotCard.getNameLot());
//						carddao.update(card);
//						listOfMsgView.add("Add card lot " + lotCard.getNameLot() + " for view to card " + bytesToHex(card.getEumSignedInfo().getEid()));
//						
//						logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Add Card lot", "lotCardAddToCardPost()", "", "", 
//								"Name card lot: " + nameCardLot + ", ID eUICC: " + card.getId(),
//								"Successfully added the card lot for view to eUICC");
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
//			loadPage(model);
//			return "general_configuration";
//		}
		
		loadPage(model);
		return "general_configuration";
	}
	
	@PostMapping("home/general/configuration/lot/card/euicc/delete/{nameCardLot}")
	public String lotCardDeleteToCardPost (Model model, HttpServletRequest request,
			@PathVariable String nameCardLot,
			@RequestParam (name = "action") String action) {
		
		List<String> deleteLotsEdit = new ArrayList<>();
		List<String> deleteLotsView = new ArrayList<>();

		List<String> neverDeleteEdit = new ArrayList<>();
		List<String> neverDeleteView = new ArrayList<>();
	
//		if(action.compareTo("save") == 0) {
//			
//			LotCard lotCard = lotcarddao.retrieveByNameLotCard(nameCardLot);
//			List<Card> listCard = carddao.retrieve();
//			
//			for (Card card : listCard) {
//				
//				if(request.getParameter("edit_" + bytesToHex(card.getEumSignedInfo().getEid())).equals("true")) {
//					Boolean delete = false;
//					
//					for (int i = 0; i < card.getListLotsEdit().size(); i++) {
//						if(card.getListLotsEdit().get(i).equals(lotCard.getNameLot())) {
//							card.getListLotsEdit().remove(i);
//							deleteLotsEdit.add("Delete card lot " + lotCard.getNameLot() + " for edit in the card " + bytesToHex(card.getEumSignedInfo().getEid()));
//							
//							logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Card lot", "lotCardDeleteToCardPost()", "", "", 
//									   "Name card lot: " + nameCardLot + ", ID eUICC: " + card.getId(), 
//									   "Successfully deleted the card lot for edit in the eUICC");
//							
//							delete = true;
//							break;
//						}
//					}
//					
//					for (int i = 0; i < card.getListLotsView().size(); i++) {
//						if(card.getListLotsView().get(i).equals(lotCard.getNameLot()) && delete) {
//							card.getListLotsView().remove(i);
//							deleteLotsView.add("Delete card lot " + lotCard.getNameLot() + " for view in the card " + bytesToHex(card.getEumSignedInfo().getEid()));
//							
//							logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Card lot", "lotCardDeleteToCardPost()", "", "", 
//									   "Name card lot: " + nameCardLot + ", ID eUICC: " + card.getId(), 
//									   "Successfully deleted the card lot for view in the eUICC");
//							
//							break;
//						}
//					}
//					
//					if(delete) {
//						carddao.update(card);
//					} else {
//						neverDeleteView.add("Impossible delete card lot " + lotCard.getNameLot() + " for edit, the card " + bytesToHex(card.getEumSignedInfo().getEid()) + " dont have this card lot");
//					
//						logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Card Lot", "lotCardDeleteToCardPost()", "", "", 
//								   "Name card lot: " + nameCardLot + ", ID eUICC: " + card.getId(),
//								   "Could not delete the card lot for edit because the eUICC hasn't this card lot");
//					}
//				}
//				
//				if(request.getParameter("view_" + bytesToHex(card.getEumSignedInfo().getEid())).equals("true")) {
//					Boolean delete = false;
//					Boolean inEdit = false;
//					
//					for (int i = 0; i < card.getListLotsEdit().size(); i++) {
//						if(card.getListLotsEdit().get(i).equals(lotCard.getNameLot())) {
//							neverDeleteEdit.add("Impossible delete card lot " + lotCard.getNameLot() + " for view, the card " + bytesToHex(card.getEumSignedInfo().getEid()) + " have this card lot for edit");
//							
//							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Lot", "lotCardDeleteToCardPost()", "", "", 
//									   "Name card lot: " + nameCardLot + ", ID eUICC: " + card.getId(), 
//									   "Could not delete the card lot for view because the eUICC has this card lot for edit");
//							
//							inEdit = true;
//							break;
//						}
//					}
//					
//					if(!inEdit) {
//						
//						for (int i = 0; i < card.getListLotsView().size(); i++) {
//							if(card.getListLotsView().get(i).equals(lotCard.getNameLot())) {
//								card.getListLotsView().remove(i);
//								deleteLotsView.add("Delete card lot " + lotCard.getNameLot() + " for view in the card " + bytesToHex(card.getEumSignedInfo().getEid()));
//								
//								logger.logger("DEBUG", "SM-WEB", "General Configuration", "", "Delete Card lot", "lotCardDeleteToCardPost()", "", "", 
//										   "Name card lot: " + nameCardLot + ", ID eUICC: " + card.getId(), 
//										   "Successfully deleted the card lot for view in the eUICC");
//								
//								delete = true;
//								break;
//							}
//						}
//						
//						if(delete) {
//							carddao.update(card);
//						} else {
//							neverDeleteView.add("Impossible delete card lot " + lotCard.getNameLot() + " for view, the card " + bytesToHex(card.getEumSignedInfo().getEid()) + " dont have this card lot");
//							
//							logger.logger("WARNING", "SM-WEB", "General Configuration", "", "Delete Card Lot", "lotCardDeleteToCardPost()", "", "", 
//									   "Name card lot: " + nameCardLot + ", ID eUICC: " + card.getId(),
//									   "Could not delete the card lot for view because the eUICC hasn't this card lot");
//						}
//					}
//				}
//			}
//			
//			model.addAttribute("deleteLotsEdit", deleteLotsEdit);
//    		model.addAttribute("deleteLotsView", deleteLotsView);
//    		
//    		model.addAttribute("neverDeleteEdit", neverDeleteEdit);
//    		model.addAttribute("neverDeleteView", neverDeleteView);
//			
//			loadPage(model);
//			return "general_configuration";
//		}
		
//		loadPage(model);
		return "general_configuration";
	}
	
					/*					REQUEST							*/
	
	@GetMapping("home/nameuser")
	private String getNameUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
	
                    /*         			UTILS							*/
 
   	private List<Role> addRoles(){
        List<Role> listRoles = rolesdao.retrieveAll();
        return listRoles;
    }
    
//    public List<Lot> addLots(){
//        List<Lot> listLotes = lotdao.retrieveAll();
//        return listLotes;
//    }
//    
//    public List<LotCard> addCardLots() {
//    	List<LotCard> listLotsCards = lotcarddao.retrieveAll();
//    	return listLotsCards;
//    }
    
//    private List<SimpleUser> addUser() {
//        List<SimpleUser> listUsers = new ArrayList<>();
//        List<User> listUserFull = userdao.retrieveAll();
//        for (User user: listUserFull) {
//        	SimpleUser simple = new SimpleUser(user);
//        	listUsers.add(simple);
//        }
//        return listUsers;
//    }
    
    private void loadPage(Model model){
//        List<SimpleUser> user= addUser();
//        model.addAttribute("user", user);
        List<Role> roles = addRoles();
//        model.addAttribute("roleFound",roles);
    }
    
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}