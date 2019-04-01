package com.lgg.nticxs.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lgg.nticxs.web.utils.Utils;
import com.lgg.nticxs.web.DAO.AlumnoDAO;
import com.lgg.nticxs.web.DAO.DocenteDAO;
import com.lgg.nticxs.web.DAO.DocumentoDAO;
import com.lgg.nticxs.web.helper.SRHelper;
import com.lgg.nticxs.web.model.Docente;
import com.lgg.nticxs.web.model.Documento;


@Controller
public class NotasController {
	 SRHelper srHelper= new SRHelper();
	 DocumentoDAO docdao = new DocumentoDAO();
	 DocenteDAO docentedao = new DocenteDAO();
	 AlumnoDAO alumdao = new AlumnoDAO();
	 	 
    @GetMapping("home/{materia}/explorer/notes")
    public String  provisioningb(Model model,
    		@PathVariable String materia){
    	model.addAttribute("materia", materia);
        return "documentos";
    }

    @GetMapping("home/{materia}/explorer/notes/edit")
    public String  documentbGet(Model model,
    		@PathVariable String materia){
    	model.addAttribute("materia", materia);
        return "provisioning";
    }
    @PostMapping("home/{materia}/explorer/notes/edit")
    public String documentbUpload(Model model,
    		HttpServletRequest request,
    		@RequestParam("document") MultipartFile documento,
    		@PathVariable String materia,
    		@RequestParam("name") String nombre, 
    		@RequestParam("description") String descripcion) {
        if (!documento.isEmpty()) {
            try {
            	Documento document = new Documento();
                Docente docente = docentedao.retrieveByName(request.getUserPrincipal().getName());
                System.out.println("docente nombre: "+ request.getUserPrincipal().getName());
                System.out.println("docente: "+ docente);
//                if(docente != null)
//                	document.setMateria(docente.getMateria());
                byte[] bytes = documento.getBytes();
                document.setIddocente(docente.getId());
                document.setAvailable(true);
                document.setDescripcion(descripcion);
                document.setFecha(Utils.fechaActual());
                document.setName(nombre);
                document.setDocumento(bytes);
                docdao.create(document);
                model.addAttribute("msg", "Su documento fue cargado exitosamente");
                return "provisioning";
            } catch (Exception e) {
                model.addAttribute("msg", "You failed to upload");
                e.printStackTrace();
                return "provisioning";
            }
        } else {
            model.addAttribute("msg", "Unable to upload. File is empty.");
            return "provisioning";
        }
    }

//    @GetMapping("home/{materia}/provisioning/allstudents")
//    public String  uploaAllEstudentsGet(Model model,
//    		@PathVariable String materia){
//    	model.addAttribute("materia", materia);
//        return "provisioning";
//    }
//    @PostMapping("home/{materia}/provisioning/allstudents")
//    public String uploaAllEstudents(Model model,
//    		@PathVariable String materia, 
//    		@RequestParam("file_template") MultipartFile file) {
//        if (!file.isEmpty()) {
//        	Boolean result = OperationCSV.uploadAllEstudents(file);
//        	if(result)
//        		model.addAttribute("msg", "Carga exitosa de los alumnos.");
//        	else
//        		model.addAttribute("msg", "Fallo al cargar los alumnos.");
//            return "provisioning";
//        } else {
//            model.addAttribute("msg", "Carga fallida. Archivo vacio.");
//            return "provisioning";
//        }
//    }
//
//    @GetMapping("home/{materia}/provisioning/onestudent")
//    public String  uploadOneEstudentGet(Model model,
//    		@PathVariable String materia){
//    	model.addAttribute("materia", materia);
//        return "provisioning";
//    }
//    @PostMapping("home/{materia}/provisioning/onestudent")
//    public String uploadOneEstudent(Model model,
//    		@PathVariable String materia,
//    		@RequestParam("name") String name) {
//        try {
//        	Alumno alumno = new Alumno();
//        	alumno.setName(name);
//            alumdao.create(alumno);
//            model.addAttribute("msg", "Carga exitosa del alumno.");
//		} catch (Exception e) {
//			model.addAttribute("msg", "Fallo al cargar el alumno.");
//		}
//       // return "redirect:/home/"+materia+"/provisioning";
//        return "provisioning";
//    }
//    
//    @GetMapping("home/{idmateria}/provisioning/allnotes")
//    public String  uploaAllNotesGet(Model model,
//    		@PathVariable String materia){
//    	model.addAttribute("materia", materia);
//        return "provisioning";
//    }
//    @PostMapping("home/{idmateria}/provisioning/allnotes")
//    public String uploaAllNotes(Model model,
//    		@PathVariable String idmateria,@RequestParam("file_notas") MultipartFile file, 
//    		@RequestParam("fecha") String fecha, @RequestParam("tipo") String tipo,
//    		@RequestParam("description") String description) {
//        if (!file.isEmpty()) {
//        	Boolean result = OperationCSV.uploadAllNotes(file,fecha, tipo, description, idmateria);
//        	if(result) {
//        		model.addAttribute("msg", "Carga exitosa de notas.");
//        	}
//        	else
//        		model.addAttribute("msg", "Fallo al cargar las notas.");
//            return "provisioning";
//        } else {
//            model.addAttribute("msg", "Carga fallida. Archivo vacio.");
//            return "provisioning";
//        }
//    }
//    
//    @GetMapping("home/{idmateria}/provisioning/allassistance")
//    public String  uploaAllAssistanceGet(Model model,
//    		@PathVariable String materia){
//    	model.addAttribute("materia", materia);
//        return "provisioning";
//    }
//    @PostMapping("home/{idmateria}/provisioning/allassistance")
//    public String uploaAllAssistance(Model model,
//    		@PathVariable String idmateria,@RequestParam("file_assistance") MultipartFile file, 
//    		@RequestParam("fecha") String fecha, @RequestParam("description") String description) {
//        if (!file.isEmpty()) {
//        	Boolean result = OperationCSV.uploadAllAssistance(file,fecha, description, idmateria);
//        	if(result) {
//        		model.addAttribute("msg", "Carga exitosa de toda la asistencia.");
//        		model.addAttribute("materia", idmateria);
//        		return "provisioning";
//        	}
//        	else
//        		model.addAttribute("msg", "Fallo al cargar la asistencia.");
//            return "provisioning";
//        } else {
//            model.addAttribute("msg", "Carga fallida. Archivo vacio.");
//            return "provisioning";
//        }
//    }
//     
}
