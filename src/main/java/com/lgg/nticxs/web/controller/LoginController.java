package com.lgg.nticxs.web.controller;


import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lgg.nticxs.web.utils.EncryptorPassword;
import com.lgg.nticxs.web.utils.Utils;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.User;

import nl.flotsam.xeger.Xeger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController{
	private UserDAO userdao  = new UserDAO();
	
//    @Resource(name="authenticationManager")
//    private AuthenticationManager authManager;


	@GetMapping("/")
    public String redirect(
    		Model model,HttpServletRequest request, HttpServletResponse response) {   
        return "redirect:/home";
    }

		
	@GetMapping("/login")
	public ModelAndView logini(@RequestParam(value="incorrectcredentials", required=false) boolean incorrectcredentials,
			@RequestParam(value="incorrecttoken", required=false) boolean incorrecttoken,
			 ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) {
    	
		if(incorrectcredentials) {
			model.addAttribute("msg1", "Error ... el usuario o la contraseña son incorrectas. Por favor verifiquelo e intente nuevamente");
			model.addAttribute("incorrectcredentials", true);}
		if(incorrecttoken) {model.addAttribute("incorrecttoken", true);}
		
		return new ModelAndView("login", model);
	}
    
    @GetMapping("/register")
    public String signupRegister(Model model) {
        return "register";
    }
    
    @GetMapping("/forgot-password")
    public String forgot(Model model) {
    	return "forgot-password";
    }
    
    @PostMapping("/forgotpass")
    public String forgotpass(Model model, @RequestParam(name="email", required=false) String email) {
    	User user = userdao.retrieveByMail(email);
    	if(user ==null)
    		model.addAttribute("msg1", "Error ... el usuario ingresado no existe, verifiquelo e intente nuevamente");
    	else{
    		String ran= Utils.generarRandom();
			user.setPassCuenta(ran);
			try {
				byte[] password = EncryptorPassword.encrypt("cambiame"+ran);
				user.setPassword(password);
				List<byte[]> list = new ArrayList<byte[]>();
				list.add(password);
			} catch (Exception e) {
				model.addAttribute("msg1", "Error durante la generacion de la contraseña. Por favor vuelva a intentarlo");
				e.printStackTrace();
			}
			user.setCuenta_iniciada(false);
			userdao.update(user);
			
			//envio del mail
			String cabecera = "<HTML><BODY><br/> <br/>";
			String body= "<h1>Usted solicito el blanqueo de su clave </h1> <br/> <h3>A continuacion se agrega su nueva contraseña y tambien su numero de activacion:</h3> <br/>" +"<h3> Contraseña: "+"cambiame"+ran+"</h3>"+"<br/> <h3> Clave de activacion: "+ran+"</h3>";
			String pie = "<br/> <br/> <footer><p> 2019 - cDash</p></footer></BODY></HTML>";
			String formulario = String.format("%s%s%s%s", cabecera, body, "<br/> <br/>", pie);
			Utils.sendMail(formulario, email);
    	}
    	return "redirect:/login";
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
        	System.out.println("la autenticacion no es null entonces la borro");
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        System.out.println("VA A SALIR!!!");
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
             session= request.getSession(false);
            if(session != null) {
                session.invalidate();
            }
            System.out.println("CANTIDAD DE COOKIES: "+ request.getCookies().length);
            for(Cookie cookie : request.getCookies()) {
                System.out.println("tiempo de vida de la cookie: "+ cookie.getMaxAge());
            	cookie.setMaxAge(0);
            	response.addCookie(cookie);
            }
            
        System.out.println("SE BORRO EL CONTEXTO");
        return "login";
    }
    
    @GetMapping("/profileuser/{userId}")
    public String editProfile(Model model, @PathVariable String userId) {
    	System.out.println("llego al controlador");
    	User user= userdao.retrieveById(userId);
    	model.addAttribute("user", user);
    	return "user_edit_profile";
    }
    
    @PostMapping("/profileuser/{userId}")
    public String editProfilePost(Model model, 
    		@PathVariable String userId,
    		@RequestParam(name="firsName", required=true) String firstname,
    		@RequestParam(name="lastName", required=true) String lastname,
    		@RequestParam(name="email", required=true) String email,
    		@RequestParam(name="newPass", required=true) String pass,
    		@RequestParam(name="newPass2", required=true) String pass2) {
    	if(pass.equals(pass2)){
    		User user= userdao.retrieveById(userId);
    		user.setFirstname(firstname);
    		user.setLastname(lastname);
    		user.setEmail(email);
    		try {
				byte[] password = EncryptorPassword.encrypt(pass);
				user.setPassword(password);
				List<byte[]> list = new ArrayList<byte[]>();
				list.add(password);
			} catch (Exception e) {
				model.addAttribute("msg1", "Error durante la generacion de la contraseña. Por favor vuelva a intentarlo");
				e.printStackTrace();
			}
    		userdao.update(user);
    		model.addAttribute("msg1", "Porceso de actualizacion de usuario Completa");
    		
    	}else {
			model.addAttribute("msg1", "Error ... Contraseña Incorrecta. Por favor vuelva a itentarlo");
			return "user_edit_profile";
		}
    	
    	return "redirect:/home";
    }

	private String createUser(Model model, String email, String role, String pass, String pass2, String firstName, String lastName) {
		if(pass.equals(pass2)){
				User user = userdao.retrieveByMail(email);
				System.out.println("el user es null??: "+ user);
				if(user== null) {
					user = new User();
					try {
						System.out.println("cifrado de la contraseña");
						byte[] password = EncryptorPassword.encrypt(pass);
						user.setPassword(password);
						List<byte[]> list = new ArrayList<byte[]>();
						list.add(password);
					} catch (Exception e) {
						model.addAttribute("msg1", "Error durante la generacion de la contraseña. Por favor vuelva a intentarlo");
						e.printStackTrace();
					}
					System.out.println("este es el nuevo rol: "+ role);
					user.setRole(role);
					user.setFirstname(firstName);
					user.setLastname(lastName);
					user.setEmail(email);
					String ran= Utils.generarRandom();
					user.setPassCuenta(ran);
					userdao.create(user);
					String cabecera = "<HTML><BODY><br/> <br/>";
					String body= "<h1>Muchas Gracias por crear su cuenta </h1> <br/> <h3>Para finalizar el proceso de activacion ingrese el siguiente valor en el inicio de Sesion:</h3> <br/> <h3>"+ran+"</h3>";
					String pie = "<br/> <br/> <footer><p> 2019 - cDash</p></footer></BODY></HTML>";
					String formulario = String.format("%s%s%s%s", cabecera, body, "<br/> <br/>", pie);
					Utils.sendMail(formulario, email);
					model.addAttribute("msg2", "Su usuario se creo exitosamente. Se le envio un mail para finalizar el proceso de activacion. Por favor, verifique su cuenta de correo");
					return "login";
				}else{
					model.addAttribute("msg1", "Error ... El usuario solicitado ya exite... si olvido la contraseña recuperela en con su email");
					return "register";
				}
				
		} else {
			model.addAttribute("msg1", "Error ... Contraseña Incorrecta. Por favor vuelva a itentarlo");
			return "register";
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
