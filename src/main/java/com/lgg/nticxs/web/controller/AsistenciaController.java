package com.lgg.nticxs.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lgg.nticxs.web.utils.OperationCSV;
import com.lgg.nticxs.web.utils.Utils;
import com.lgg.nticxs.web.DAO.AlumnoDAO;
import com.lgg.nticxs.web.DAO.DocenteDAO;
import com.lgg.nticxs.web.DAO.DocumentoDAO;
import com.lgg.nticxs.web.helper.SRHelper;
import com.lgg.nticxs.web.model.Alumno;
import com.lgg.nticxs.web.model.Docente;
import com.lgg.nticxs.web.model.Documento;


@Controller
public class AsistenciaController {
	 SRHelper srHelper= new SRHelper();
	 DocumentoDAO docdao = new DocumentoDAO();
	 DocenteDAO docentedao = new DocenteDAO();
	 AlumnoDAO alumdao = new AlumnoDAO();
	 	 
    @GetMapping("home/{materia}/explorer/assistence")
    public String  bprovisioning(Model model,
    		@PathVariable String materia){
    	model.addAttribute("materia", materia);
        return "documentos";
    }

    @GetMapping("home/{materia}/explorer/assistence/edit")
    public String  documentbGet(Model model,
    		@PathVariable String materia){
    	model.addAttribute("materia", materia);
        return "provisioning";
    }
    @PostMapping("home/{materia}/explorer/assistence/edit")
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
}
