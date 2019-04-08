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
<<<<<<< HEAD
import com.lgg.nticxs.web.DAO.AdminDAO;
import com.lgg.nticxs.web.DAO.AdministrativoDAO;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.DAO.DocenteDAO;
import com.lgg.nticxs.web.DAO.PadreDAO;
import com.lgg.nticxs.web.model.Admin;
import com.lgg.nticxs.web.model.Administrativo;
import com.lgg.nticxs.web.model.User;
import com.lgg.nticxs.web.model.Docente;
import com.lgg.nticxs.web.model.Padre;
=======
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.User;
>>>>>>> a35f14a60156c0b0821fb64ef9e45121ab839d5c

import nl.flotsam.xeger.Xeger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController{
<<<<<<< HEAD
	private UserDAO alumnodao  = new UserDAO();
=======
	private UserDAO userdao  = new UserDAO();
>>>>>>> a35f14a60156c0b0821fb64ef9e45121ab839d5c

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
<<<<<<< HEAD
    		@RequestParam("action") String action,
    		@RequestParam(name="insertName", required=false) String firstName,
    		@RequestParam(name="selectName", required=false) String lastName,
=======
    		@RequestParam(name="firsName", required=false) String firsName,
    		@RequestParam(name="lastName", required=false) String lastName,
>>>>>>> a35f14a60156c0b0821fb64ef9e45121ab839d5c
    		@RequestParam(name="email", required=false) String email,
    		@RequestParam(name="role", required=false) String role,
    		@RequestParam(name="newPass", required=false) String pass, 
    		@RequestParam(name="newPass2", required=false) String pass2) throws Exception{
<<<<<<< HEAD
		if (role == null)
			role="SUPERADMIN";
		String returnValue = null;
    	if (action.compareTo("save") == 0 && email.contains("@")) {   		
    		if(nameExist(email)) {
    			model.addAttribute("msg1", "Error ... incorrect name, already exists");
    			loadSingUp(model);
    			return "signup";
        	}
			returnValue = createUser(model, email, role,pass, pass2, firstName,lastName);
=======
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
>>>>>>> a35f14a60156c0b0821fb64ef9e45121ab839d5c
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
    
<<<<<<< HEAD
=======
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
>>>>>>> a35f14a60156c0b0821fb64ef9e45121ab839d5c

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
    
<<<<<<< HEAD
    
    private void loadSingUp(Model model) {
    	List<User> allAlumnos = alumnodao.retrieveAll();
    	List<String> alumnos =new ArrayList<>();
    	if(allAlumnos != null) {
	    	for(User alum: allAlumnos){
	    		if(!alum.getCuenta_iniciada())
	    			alumnos.add(alum.getName());
	    	}
    	}
    	model.addAttribute("alumnos",alumnos);
		
	}
    
	private String createUser(Model model, String name, String role,String pass, String pass2, String firsName,String lastName) {
			if(pass.equals(pass2)){
					User admin = new User();
	    			admin.setName(name);
	    			try {
	    				byte[] password = EncryptorPassword.encrypt(pass);
						admin.setPassword(password);
						List<byte[]> list = new ArrayList<byte[]>();
						list.add(password);
					} catch (Exception e) {
						System.out.println("error en la generacion del pass");
						e.printStackTrace();
					}
					admin.setRole(role);
					admin.setDelete(false);
					admin.setEmail(name);
					admin.setFirstName(firsName);
					admin.setLastName(lastName);
					alumnodao.create(admin);
					model.addAttribute("msg2", "User update successfully completed");
			    	return "login";
			} else {
				model.addAttribute("msg1", "Error ... password do not match");
				return "signup";
			}

		
	}

    
    
    private boolean alumnoIsActive(String selectName) {
		User alumno = alumnodao.retrieveByName(selectName);
		if(alumno.getCuenta_iniciada()) {
			System.out.println("el alumno tiene la cuenta iniciada");
			return true;
		}
		else {
			System.out.println("el alumno puede continuar");
			return false;
		}
		
	}


    private boolean nameExist(String email) {
    	User alumno = alumnodao.retrieveByName(email);
    	if(alumno == null)
    		return false;
    	else
    		return true;
    }
	
=======

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

>>>>>>> a35f14a60156c0b0821fb64ef9e45121ab839d5c
}
