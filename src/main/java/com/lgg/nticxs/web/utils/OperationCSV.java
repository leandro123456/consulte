package com.lgg.nticxs.web.utils;

import org.springframework.web.multipart.MultipartFile;

import com.lgg.nticxs.web.DAO.AlumnoDAO;
import com.lgg.nticxs.web.DAO.AsistenciaDAO;
import com.lgg.nticxs.web.DAO.NotaDAO;
import com.lgg.nticxs.web.model.Alumno;
import com.lgg.nticxs.web.model.Asistencia;
import com.lgg.nticxs.web.model.Nota;

public class OperationCSV {
	

	public static Boolean uploadAllEstudents(MultipartFile file) {
    	try {
        	byte[] bytes = file.getBytes();
            String completeData = new String(bytes);
            String[] lineas = completeData.split("\n");
            System.out.println("cantidad de alumnos a cargar: "+ lineas.length);
            int valor = 0;
            AlumnoDAO alumdao = new AlumnoDAO();
            for(String linea: lineas) {
            	Alumno alumno = new Alumno();
            	alumno.setName(linea);
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
	
	public static Boolean uploadAllNotes(MultipartFile file, String fecha, String tipo, 
			String description, String materia) {
		NotaDAO notadao = new NotaDAO();
		Integer trimestre = Utils.obtenerTrimestre(fecha);
		try {
			byte[] bytes = file.getBytes();
			String completeData = new String(bytes);
			String[] lineas = completeData.split("\n");
			System.out.println("cantidad de notas a cargar: "+ lineas.length);
			int valor = 0;
			for(String linea: lineas) {
				try {
					String[] parts = linea.split(";");
					Nota nota = notadao.retrieveByNameDateDescription(parts[0], fecha, description);
					if(nota != null) {
						nota.setDescripcion(description);
						nota.setFecha(fecha);
						nota.setIdalumno(parts[0]);
						nota.setIdmateria(materia);
						nota.setTipo(tipo);
						nota.setTrimestre(trimestre);
						nota.setValor(Double.parseDouble(parts[1]));
						notadao.update(nota);
					}else {
						nota = new Nota();
						nota.setDescripcion(description);
						nota.setFecha(fecha);
						nota.setIdalumno(parts[0]);
						nota.setIdmateria(materia);
						nota.setTipo(tipo);
						nota.setTrimestre(trimestre);
						nota.setValor(Double.parseDouble(parts[1]));
						notadao.create(nota);
					}
					valor +=1;
					System.out.println("se cargo correctamente el alumno: "+ linea);
				} catch (Exception e) {
					System.out.println("error al cargar el alumno: "+ linea);
				}
			}
			System.out.println("cantidad de alumnos a cargados: "+ valor);
			return true;
		} catch (Exception e) {
			System.out.println("error al cargar el archivo csv");
			e.printStackTrace();
			return false;
		}
	}

	public static Boolean uploadAllAssistance(MultipartFile file, String fecha,
			String description, String materia) {
		AsistenciaDAO asisdao = new AsistenciaDAO();
		Integer trimestre = Utils.obtenerTrimestre(fecha);
		try {
			byte[] bytes = file.getBytes();
			String completeData = new String(bytes);
			String[] lineas = completeData.split("\n");
			System.out.println("cantidad de asistencia a cargar: "+ lineas.length);
			int valor = 0;
			for(String linea: lineas) {
				try {
					String[] parts = linea.split(";");
					Asistencia asistencia = asisdao.retrieveByNameDateDescription(parts[0], fecha);
					if(asistencia != null) {
						asistencia.setDescripcion(description);
						asistencia.setFecha(fecha);
						asistencia.setIdalumno(parts[0]);
						asistencia.setIdmateria(materia);
						asistencia.setTipo(Integer.parseInt(parts[1]));
						asistencia.setTrimestre(trimestre);
						asisdao.update(asistencia);
					}else {
						asistencia = new Asistencia();
						asistencia.setDescripcion(description);
						asistencia.setFecha(fecha);
						asistencia.setIdalumno(parts[0]);
						asistencia.setIdmateria(materia);
						asistencia.setTipo(Integer.parseInt(parts[1]));
						asistencia.setTrimestre(trimestre);
						asisdao.create(asistencia);
					}
					valor +=1;
					System.out.println("se cargo correctamente el alumno: "+ linea);
				} catch (Exception e) {
					System.out.println("error al cargar la asistencia del alumno: "+ linea);
				}
			}
			System.out.println("cantidad de asistencia cargada: "+ valor);
			return true;
		} catch (Exception e) {
			System.out.println("error al cargar el archivo csv");
			e.printStackTrace();
			return false;
		}
	}
}
