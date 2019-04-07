package com.lgg.nticxs.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgg.nticxs.web.utils.EncryptorPassword;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.User;

import nl.flotsam.xeger.Xeger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController{
	private UserDAO userdao  = new UserDAO();

    @GetMapping("/")
    public String redirect(Model model) {
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @GetMapping("/register")
    public String signupRegister(Model model) {
        return "register";
    }
    
    @GetMapping("/forgot-password")
    public String forgot(Model model) {
    	return "forgot-password";
    }
    
    @GetMapping("/signup")
    public String signup(Model model) {
        return "register";
    }
    
    @PostMapping("/signup")
    public String addNewUser(Model model,
    		@RequestParam(name="firsName", required=false) String firsName,
    		@RequestParam(name="lastName", required=false) String lastName,
    		@RequestParam(name="email", required=false) String email,
    		@RequestParam(name="role", required=false) String role,
    		@RequestParam(name="newPass", required=false) String pass, 
    		@RequestParam(name="newPass2", required=false) String pass2) throws Exception{
    	String returnValue = null;
    	System.out.println("firsName: "+ firsName);
    	System.out.println("last name: "+  lastName);
    	System.out.println("email: "+ email);
    	System.out.println("role: "+ role);
    	System.out.println("pass: "+pass);
    	System.out.println("pass2: "+ pass2);
    	
    	if(role==null)
    		role=User.ROLE_SUPERADMIN;
    	if (email.contains("@")) {   		
    		if(alumnoIsActive(email)){
    			model.addAttribute("msg1", "Error ... la direccion de mail ya existe, seleccione otra diferente");
    			return "register";
    		}
    		else    				
    			returnValue = createUser(model, email, role,pass, pass2,firsName,lastName);
    		return returnValue;
    	} 
    	return "login";
    }

	@GetMapping("/signupPass")
    public String signupPass(Model model) {
        return "twoAuthentication";//"signup2"
    }
        
    @GetMapping("/signupPass2")
    public String signupPass2(Model model) {
        return "signup2";
    }
    
    @PostMapping("/signupPass2")
    public String signupPass2(Model model,
    		@RequestParam("action") String action,
    		@RequestParam(name="newName", required=false) String name,
    		@RequestParam(name="role", required=false) String role,
    		@RequestParam(name="newPass", required=false) String pass, 
    		@RequestParam(name="newPass2", required=false) String pass2) throws Exception{
    	String returnValue = null;
    	if (action.compareTo("save") == 0) {
    		if(name.isEmpty()) {
    			model.addAttribute("msg1", "Error ... incorrect name, is empty");
    			return "signup";
        	}

    	}else{
    		return "login";
    	}
    
    	return returnValue;
    }

	@GetMapping("/randompassword")
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
    
    @PostMapping("/twoauthentication")
    public String twoAuthentication(Model model, @RequestParam("pass") String pass) {
    	if(pass.equals("KFsck32/dF$5sd8")){
			System.out.println("el pass fue bueno");
			return "signup2";
		}
		else{
			model.addAttribute("msg1", "Error ... contraseña incorrecta");
			return "login";
		}
    	
    }

    @GetMapping("/logoutsession")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
    

	private String createUser(Model model, String email, String role, String pass, String pass2, String firstName, String lastName) {
		if(pass.equals(pass2)){
				User user = userdao.retrieveByMail(email);
				if(user== null) {
					user = new User();
					try {
						byte[] password = EncryptorPassword.encrypt(pass);
						user.setPassword(password);
						List<byte[]> list = new ArrayList<byte[]>();
						list.add(password);
					} catch (Exception e) {
						model.addAttribute("msg1", "Error in the generation of the password");
						e.printStackTrace();
					}
					user.setRole(role);
					user.setFirstname(firstName);
					user.setLastname(lastName);
					user.setEmail(email);
					userdao.update(user);
				}
				model.addAttribute("msg2", "The User was created successfully");
				return "login";
		} else {
			model.addAttribute("msg1", "Error ... Incorrect password");
			return "signup";
		}
	}


    private boolean alumnoIsActive(String selectName) {
		User alumno = userdao.retrieveByMail(selectName);
		if (alumno != null) {
			if(alumno.getCuenta_iniciada()) {
				System.out.println("el alumno tiene la cuenta iniciada");
				return true;
			}
			else {
				System.out.println("el alumno puede continuar");
				return false;
			}
		}
		return false;
	}

}
