package com.lgg.nticxs.web.sessionManager;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/contadorServlet" })
public class ContadorServlet  extends HttpServlet{
	
	private static final long serialVersionUID = -3450969163801147075L;

	protected static final String CONTADOR = "contador";
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		final Integer contador = getContador(request);
		System.out.println("Se envia como valor de contador {} en la session {} , contador: "+
		 		contador+"SESION id: "+ request.getSession().getId());
		try (ServletOutputStream out = response.getOutputStream()) {
			out.println(contador);
		} catch (IOException e) {
			System.out.println("Error read outputStram cause: "+ e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		final int value = getContador(request).intValue() + 1;
		request.getSession().setAttribute(CONTADOR, value);
		System.out.println("Se guarda como valor de contador {} en la session {}: "
		 		+ value +"session id: "+request.getSession().getId());
	}

	private Integer getContador(HttpServletRequest request) {
		return Optional.ofNullable(request.getSession())
				.map(session -> (Integer)session.getAttribute(CONTADOR)).orElse(0);
	}

}
