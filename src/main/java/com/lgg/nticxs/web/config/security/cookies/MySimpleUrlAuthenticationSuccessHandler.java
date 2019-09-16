package com.lgg.nticxs.web.config.security.cookies;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.groovy.transform.trait.Traits.Implemented;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.itextpdf.text.pdf.codec.Base64;
import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.User;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public MySimpleUrlAuthenticationSuccessHandler() {
        super();
    }

    // API

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    // IMPL

    protected void handle (final HttpServletRequest request, final HttpServletResponse response, 
    		final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            System.out.println("MI SIMPLE - Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        System.out.println("MI SIMPLE ...todas la cookies: "+ authentication.getName());
     // create a cookie
        //Cookie cookie = new Cookie(Base64.encodeBytes(authentication.getName().getBytes()), generateSessionKey());
//        Cookie cookie = new Cookie("JSESSIONID", value)
//        response.addCookie(cookie);
        System.out.println("MI SIMPLE - armo la primer cookie");
        
		Cookie[] cookies = request.getCookies();
    	for(Cookie coo : cookies){
    		System.out.println("valor de la cookies: "+coo.getName()+ " = "+coo.getValue());
    	}
        
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication) {
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
//            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
//                isUser = true;
//                break;
//            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
//                isAdmin = true;
//                break;
//            }
        	System.out.println("MI SIMPLE - rol: "+ grantedAuthority.getAuthority());
        }

        return "/home";
    }

    /**
     * Removes temporary authentication-related data which may have been stored in the session
     * during the authentication process.
     */
    protected final void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    
    
    public static String generateSessionKey(){
    	int length= 16;
    	String alphabet = 
    	        new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); //9
    	int n = alphabet.length(); //10

    	String result = new String(); 
    	Random r = new Random(); //11

    	for (int i=0; i<length; i++) //12
    	    result = result + alphabet.charAt(r.nextInt(n)); //13

    	return result;
    	}

}
