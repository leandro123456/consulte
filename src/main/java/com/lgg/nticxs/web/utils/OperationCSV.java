package com.lgg.nticxs.web.utils;

import org.springframework.web.multipart.MultipartFile;

import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.User;


public class OperationCSV {
	

	public static Boolean uploadAllEstudents(MultipartFile file) {
    	try {
        	byte[] bytes = file.getBytes();
            String completeData = new String(bytes);
            String[] lineas = completeData.split("\n");
            System.out.println("cantidad de alumnos a cargar: "+ lineas.length);
            int valor = 0;
            UserDAO alumdao = new UserDAO();
            for(String linea: lineas) {
            	User alumno = new User();
            	alumdao.create(alumno);
            	valor +=1;
            }
            System.out.println("cantidad de alumnos a cargados: "+ valor);
            return true;
        } catch (Exception e) {
        	System.out.println("error al cargar el archivo csv");
        	e.printStackTrace();
        	return false;
        }
	}
}
