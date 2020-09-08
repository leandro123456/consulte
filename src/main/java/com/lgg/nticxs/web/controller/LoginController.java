package com.lgg.nticxs.web.controller;


import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lgg.nticxs.web.utils.EncryptorPassword;
import com.lgg.nticxs.web.utils.Utils;
import com.lgg.nticxs.web.DAO.NotificacionDAO;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.Notificacion;
import com.lgg.nticxs.web.model.User;

import nl.flotsam.xeger.Xeger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
//@RestController
//@RequestMapping(value="/cDash")
public class LoginController implements LogoutSuccessHandler{
	private UserDAO userdao  = new UserDAO();
	

	@GetMapping("/")
    public String redirect(
    		Model model,HttpServletRequest request, HttpServletResponse response) {   
        return "redirect: home";
    }
	
	
	@GetMapping("/firebase-messaging-sw.js")
	public String redirectfirebase(
    		Model model,HttpServletRequest request, HttpServletResponse response) {   
        return "firebase-messaging-sw.js";
    }
	
	@GetMapping("login")
	public ModelAndView logini1(
			 ModelMap model
			) {
		System.out.println("llego al LOGIN nuevo");
		return new ModelAndView("login.jsp", model);
	}
    
	   
    @GetMapping("/register")
    public String signupRegister(Model model) {
        return "register.jsp";
    }
    
    @GetMapping("/forgot-password")
    public String forgot(Model model) {
    	return "forgot-password.jsp";
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
        return "register.jsp";
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
    			return "register.jsp";
    		}
    		else    				
    			returnValue = createUser(model, email, role,pass, pass2,firsName,lastName);
    		return returnValue;
    	} 
    	return "login.jsp";
    }

	@GetMapping("/signupPass")
    public String signupPass(Model model) {
        return "twoAuthentication.jsp";//"signup2"
    }
        
    @GetMapping("/signupPass2")
    public String signupPass2(Model model) {
        return "signup2.jsp";
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
			return "signup2.jsp";
		}
		else{
			model.addAttribute("msg1", "Error ... contraseña incorrecta");
			return "redirect: /";
		}
    	
    }

    
    @GetMapping("/logoutsession")
    public String logout(HttpServletRequest request, HttpServletResponse response,
    		ModelMap model) {
        try {
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
                	cookie.setMaxAge(0);
                	response.addCookie(cookie);
                }
//                try {
//                	request.logout();
//                	onLogoutSuccess(request, response, auth);
//    			} catch (Exception e) {
//    				System.out.println("=============== ERROR ===============");
//    				e.printStackTrace();
//    				return "login.jsp";
//    			}
            //model.addAttribute("msg1", "Error ... el usuario ingresado no existe, verifiquelo e intente nuevamente");
            return "redirect: login";
		} catch (Exception e) {
			return "login.jsp";
		}
    }
    
    @GetMapping("/profileuser/{userId}")
    public String editProfile(Model model, @PathVariable String userId) {
    	System.out.println("llego al controlador de configuracion usuario");
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println("Edit profile - busco el usuario: "+ authentication.getName());
        User user = userdao.retrieveByMail(authentication.getName());
    	
    	//User user= userdao.retrieveById(userId);
    	model.addAttribute("user", user);
    	return "user_edit_profile.jsp";
    }
    
    @PostMapping("/profileuser/{userId}")
    public String editProfilePost(Model model, 
    		@PathVariable String userId,
    		@RequestParam(name="firsName", required=true) String firstname,
    		@RequestParam(name="lastName", required=true) String lastname,
    		@RequestParam(name="email", required=true) String email,
    		@RequestParam(name="newPass", required=true) String pass,
    		@RequestParam(name="newPass2", required=true) String pass2,
    		@RequestParam(name="action", required=true) String action) {
    	System.out.println("este es el USERID: "+ userId);
    	if(action.equals("save")) {
    		if(pass.equals(pass2)){
    			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    			System.out.println("Edit profile - busco el usuario: "+ authentication.getName());
    			User user = userdao.retrieveByMail(authentication.getName());
    			user.setFirstname(firstname);
    			user.setLastname(lastname);
    			user.setEmail(email);
    			try {
    				byte[] password = EncryptorPassword.encrypt(pass);
    				user.setPassword(password);
    				List<byte[]> list = new ArrayList<byte[]>();
    				list.add(password);
    			} catch (Exception e) {
    				model.addAttribute("msg", "Error durante la generacion de la contraseña. Por favor vuelva a intentarlo");
    				e.printStackTrace();
    			}
    			userdao.update(user);
    			model.addAttribute("msg", "Proceso de actualizacion de usuario Completa");

    		}else {
    			model.addAttribute("msg", "Error ... Contraseña Incorrecta. Por favor vuelva a itentarlo");
    			return "user_edit_profile";
    		}
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
					user.setFechaCreacion(Utils.getFechaYHora());
					userdao.create(user);
					String cabecera = "<HTML><BODY><br/> <br/>";
					String body= "<h3>Hola,</h3>"
							+ "<h3>Hemos creado tu cuenta en cDash.</h3>"
							+ "<h3>Para activarla, por favor inicia sesión en <a href=\"https://cdash.space\">https://cdash.space</a> https://cdash.space con la dirección de correo electrónico que utilizaste para crear la cuenta y tu contraseña. Cuando lo hagas, se te pedirá por única vez que ingreses el siguiente código:</h3>"
							+ "<br/><h3>"+ran+"</h3>"
							+ "<br><h3>A partir de ese momento podrás hacer uso de la aplicación y configurar tus dispositivos.</h3>"
							+ "<h3>Para agregar un dispositivo, deberás tocar el signo + que se encuentra en la parte superior derecha de la pantalla.</h3>"
							+ "<br/><h3>Podrás usar la aplicación iniciando sesión en cualquier computadora o dispositivos móviles, como tablets o smartphones. Recibirás notificaciones en todos los dispositivos en los que inicies sesión. Recuerda cerrar sesión si usas dispositivos públicos o prestados para evitar que otros puedan acceder a tu cuenta.</h3>"
							+ "<br/><h3>Este correo electrónico se envía de forma automática. No lo contestes. Si tienes dudas, ponte en contacto con el equipo de soporte escribiendo a support@coiaca.com.</h3>"
							+ "<br/><h3>Saludos cordiales.</h3>";
					String pie = "";//"<br/> <br/> <footer><p> 2019 - cDash</p></footer></BODY></HTML>";
					String formulario = String.format("%s%s%s%s", cabecera, body, "<br/> <br/>", pie);
					Utils.sendMail(formulario, email);
					model.addAttribute("msg2", "Su usuario se creo exitosamente. Se le envio un mail para finalizar el proceso de activacion. Por favor, verifique su cuenta de correo");
					return "login.jsp";
				}else{
					model.addAttribute("msg1", "Error ... El usuario solicitado ya exite... si olvido la contraseña recuperela en con su email");
					return "register.jsp";
				}
				
		} else {
			model.addAttribute("msg1", "Error ... Contraseña Incorrecta. Por favor vuelva a itentarlo");
			return "register.jsp";
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


	@Override
	public void onLogoutSuccess(HttpServletRequest arg0, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if(authentication != null) {
			System.out.println(authentication.getName());
		}
		//perform other required operation		
		response.setStatus(HttpStatus.OK.value());
		response.getWriter().flush();
		
	}
}
