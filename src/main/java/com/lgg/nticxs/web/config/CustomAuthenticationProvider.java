package com.lgg.nticxs.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.lgg.nticxs.web.utils.EncryptorPassword;
import com.lgg.nticxs.web.DAO.AdminDAO;
import com.lgg.nticxs.web.DAO.AdministrativoDAO;
import com.lgg.nticxs.web.DAO.AlumnoDAO;
import com.lgg.nticxs.web.DAO.DocenteDAO;
import com.lgg.nticxs.web.DAO.PadreDAO;
import com.lgg.nticxs.web.model.Admin;
import com.lgg.nticxs.web.model.Administrativo;
import com.lgg.nticxs.web.model.Alumno;
import com.lgg.nticxs.web.model.Docente;
import com.lgg.nticxs.web.model.Padre;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		AlumnoDAO alumdao = new AlumnoDAO();
		PadreDAO padredao = new PadreDAO();
		AdminDAO admindao = new AdminDAO();
		AdministrativoDAO administrativodao = new AdministrativoDAO();
		DocenteDAO docentedao = new DocenteDAO();

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		Alumno alumno = alumdao.retrieveByName(name);
		if(alumno != null){
			try {
				if (name.equals(alumno.getName()) && password.equals(EncryptorPassword.decrypt(alumno.getPassword())) && alumno.getRole().equals(alumno.ROLE_ALUMNO)) {
					List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
					authorities.add(new SimpleGrantedAuthority(alumno.getRole()));
					return new UsernamePasswordAuthenticationToken(alumno.getName(),alumno.getPassword(), authorities);
				} else {
					System.out.println("Login failed"+ "Invalid date. User name = " + alumno.getName());
				}
			} catch (Exception e) {
				System.out.println(" Error authenticate()"+ "Error to decrypt the password");
				e.printStackTrace();
			}
		}
		Padre padre = padredao.retrieveByName(name);
		if(padre != null){
			try {
				if (name.equals(padre.getName()) && password.equals(EncryptorPassword.decrypt(padre.getPassword()))) {
					List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
					authorities.add(new SimpleGrantedAuthority(padre.getRole()));
					return new UsernamePasswordAuthenticationToken(padre.getName(),padre.getPassword(), authorities);
				} else {
					System.out.println("Login failed"+ "Invalid date. User name = " + padre.getName());
				}
			} catch (Exception e) {
				System.out.println(" Error authenticate()"+ "Error to decrypt the password");
				e.printStackTrace();
			}
		}
		Admin admin = admindao.retrieveByName(name);
		if(admin != null){
			try {
				if (name.equals(admin.getName()) && password.equals(EncryptorPassword.decrypt(admin.getPassword()))) {
					List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
					authorities.add(new SimpleGrantedAuthority(admin.getRole()));
					return new UsernamePasswordAuthenticationToken(admin.getName(),admin.getPassword(), authorities);
				} else {
					System.out.println("Login failed"+ "Invalid date. User name = " + admin.getName());
				}
			} catch (Exception e) {
				System.out.println(" Error authenticate()"+ "Error to decrypt the password");
				e.printStackTrace();
			}
		}
		Administrativo administrativo = administrativodao.retrieveByName(name);
		if (administrativo != null){
			try {
				if (name.equals(administrativo.getName()) && password.equals(EncryptorPassword.decrypt(administrativo.getPassword()))) {
					List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
					authorities.add(new SimpleGrantedAuthority(administrativo.getRole()));
					return new UsernamePasswordAuthenticationToken(administrativo.getName(),administrativo.getPassword(), authorities);
				} else {
					System.out.println("Login failed"+ "Invalid date. User name = " + administrativo.getName());
				}
			} catch (Exception e) {
				System.out.println(" Error authenticate()"+ "Error to decrypt the password");
				e.printStackTrace();
			}
		}
		Docente docente = docentedao.retrieveByName(name);
		if (docente != null){
			try {
				if (name.equals(docente.getName()) && password.equals(EncryptorPassword.decrypt(docente.getPassword()))) {
					List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
					authorities.add(new SimpleGrantedAuthority(docente.getRole()));
					return new UsernamePasswordAuthenticationToken(docente.getName(),docente.getPassword(), authorities);
				} else {
					System.out.println("Login failed"+ "Invalid date. User name = " + docente.getName());
				}
			} catch (Exception e) {
				System.out.println(" Error authenticate()"+ "Error to decrypt the password");
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(
				UsernamePasswordAuthenticationToken.class);
	}
}
