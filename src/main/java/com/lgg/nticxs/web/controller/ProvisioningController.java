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
import org.springframework.web.bind.annotation.RequestMapping;
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
public class ProvisioningController {
	 SRHelper srHelper= new SRHelper();
	 DocumentoDAO docdao = new DocumentoDAO();
	 DocenteDAO docentedao = new DocenteDAO();
	 AlumnoDAO alumdao = new AlumnoDAO();
	
	 @RequestMapping("/home/provisioning")
		public String books(@RequestParam("role") String role,@RequestParam("usuario") String usuario, Model model){

		    model.addAttribute("usuario", usuario);
		    model.addAttribute("role", role);
		    return "home2";
		}
	 
    @GetMapping("home/{materia}/provisioning")
    public String  provisioning(Model model,
    		@PathVariable String materia){
    	model.addAttribute("materia", materia);
        return "provisioning";
    }

    @GetMapping("home/{materia}/provisioning/document")
    public String  documentGet(Model model,
    		@PathVariable String materia){
    	model.addAttribute("materia", materia);
        return "provisioning";
    }
    @PostMapping("home/{materia}/provisioning/document")
    public String documentUpload(Model model,
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

    @GetMapping("home/{materia}/provisioning/allstudents")
    public String  uploaAllEstudentsGet(Model model,
    		@PathVariable String materia){
    	model.addAttribute("materia", materia);
        return "provisioning";
    }
    @PostMapping("home/{materia}/provisioning/allstudents")
    public String uploaAllEstudents(Model model,
    		@PathVariable String materia, 
    		@RequestParam("file_template") MultipartFile file) {
        if (!file.isEmpty()) {
        	Boolean result = OperationCSV.uploadAllEstudents(file);
        	if(result)
        		model.addAttribute("msg", "Carga exitosa de los alumnos.");
        	else
        		model.addAttribute("msg", "Fallo al cargar los alumnos.");
            return "provisioning";
        } else {
            model.addAttribute("msg", "Carga fallida. Archivo vacio.");
            return "provisioning";
        }
    }

    @GetMapping("home/{materia}/provisioning/onestudent")
    public String  uploadOneEstudentGet(Model model,
    		@PathVariable String materia){
    	model.addAttribute("materia", materia);
        return "provisioning";
    }
    @PostMapping("home/{materia}/provisioning/onestudent")
    public String uploadOneEstudent(Model model,
    		@PathVariable String materia,
    		@RequestParam("name") String name) {
        try {
        	Alumno alumno = new Alumno();
        	alumno.setName(name);
            alumdao.create(alumno);
            model.addAttribute("msg", "Carga exitosa del alumno.");
		} catch (Exception e) {
			model.addAttribute("msg", "Fallo al cargar el alumno.");
		}
       // return "redirect:/home/"+materia+"/provisioning";
        return "provisioning";
    }
    
    @GetMapping("home/{idmateria}/provisioning/allnotes")
    public String  uploaAllNotesGet(Model model,
    		@PathVariable String materia){
    	model.addAttribute("materia", materia);
        return "provisioning";
    }
    @PostMapping("home/{idmateria}/provisioning/allnotes")
    public String uploaAllNotes(Model model,
    		@PathVariable String idmateria,@RequestParam("file_notas") MultipartFile file, 
    		@RequestParam("fecha") String fecha, @RequestParam("tipo") String tipo,
    		@RequestParam("description") String description) {
        if (!file.isEmpty()) {
        	Boolean result = OperationCSV.uploadAllNotes(file,fecha, tipo, description, idmateria);
        	if(result) {
        		model.addAttribute("msg", "Carga exitosa de notas.");
        	}
        	else
        		model.addAttribute("msg", "Fallo al cargar las notas.");
            return "provisioning";
        } else {
            model.addAttribute("msg", "Carga fallida. Archivo vacio.");
            return "provisioning";
        }
    }
    
    @GetMapping("home/{idmateria}/provisioning/allassistance")
    public String  uploaAllAssistanceGet(Model model,
    		@PathVariable String materia){
    	model.addAttribute("materia", materia);
        return "provisioning";
    }
    @PostMapping("home/{idmateria}/provisioning/allassistance")
    public String uploaAllAssistance(Model model,
    		@PathVariable String idmateria,@RequestParam("file_assistance") MultipartFile file, 
    		@RequestParam("fecha") String fecha, @RequestParam("description") String description) {
        if (!file.isEmpty()) {
        	Boolean result = OperationCSV.uploadAllAssistance(file,fecha, description, idmateria);
        	if(result) {
        		model.addAttribute("msg", "Carga exitosa de toda la asistencia.");
        		model.addAttribute("materia", idmateria);
        		return "provisioning";
        	}
        	else
        		model.addAttribute("msg", "Fallo al cargar la asistencia.");
            return "provisioning";
        } else {
            model.addAttribute("msg", "Carga fallida. Archivo vacio.");
            return "provisioning";
        }
    }
    
    
    @PostMapping("home/{materia}/provisioning/all")
    public String doProfileTemplateUploadAll(Model model, 
    		@PathVariable String materia,
    		@RequestParam("file_template") MultipartFile file, 
    		@RequestParam("template") String template,
    		@RequestParam("iccid") String iccid,
    		@RequestParam("imsi") String imsi,
    		@RequestParam("listMno") String listMno,
    		@RequestParam("msisdn") String msisdn, 
    		@RequestParam(name="active", required=false, defaultValue = "false") Boolean available) {
//        String fileName = null;
//        PresetCommandDAO presetCommandDAO = new PresetCommandDAO();
        if (!file.isEmpty()) {
            try {
//                fileName = file.getOriginalFilename();
//                byte[] bytes = file.getBytes();
//                BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(Settings.getInstance().getFILE_UPLOAD_PATH_IPP() + fileName)));
//                buffStream.write(bytes);
//                buffStream.close();
                
                // Update or create
//                PresetCommand presetCommand = presetCommandDAO.retrieveByICCID(iccid);

//                if (presetCommand == null) {
//                    presetCommand = new PresetCommand();
//                    presetCommand=SettingsValuesDefaultIPP(template, iccid,imsi, msisdn,listMno, fileName);
//                    presetCommandDAO.create(presetCommand);
//                } else {
//                	presetCommand=SettingsValuesDefaultIPP(template, iccid,imsi, msisdn,listMno, fileName);
//                    presetCommandDAO.update(presetCommand);
//                }
                
//                loadPageProvisioning(model);
                model.addAttribute("msg", "You have successfully uploaded");
                return "provisioning";
            } catch (Exception e) {
            	StringWriter errors = new StringWriter();
            	e.printStackTrace(new PrintWriter(errors));
                model.addAttribute("msg", "You failed to upload"+ errors.toString());               
                return "provisioning";
            }
        } else {
            model.addAttribute("msg", "Unable to upload. File is empty.");
            return "provisioning";
        }
    }
    
    @PostMapping("home/{materia}/provisioning/euicc")
    public String doEuiccInfoUpload(Model model, 
    		@PathVariable String materia,
    		@RequestParam("file") MultipartFile file,
    		@RequestParam(name="dpname", required=false) String dpname,
    		@RequestParam(name="srname", required=false) String srname) {
        String fileName = null;
        
        if (!file.isEmpty()) {
        	try {
//    			JAXBContext context = JAXBContext.newInstance(EISType.class);
//    			Unmarshaller unmarshaller = context.createUnmarshaller();
    			
    			fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream =new BufferedOutputStream(new FileOutputStream(new File(fileName)));
                buffStream.write(bytes);
                buffStream.close();
//        		String dir = "";//Settings.getInstance().getFILE_UPLOAD_PATH_CARDS()+fileName;
        		
//        		@SuppressWarnings("resource")
//				ZipFile zip = new ZipFile(dir);
//    			Enumeration<? extends ZipEntry> entries = zip.entries();
//    			HashMap<String,String> valuesMsisdn= Utils.takeMsisdnCardProfile("card");
                
//    			while(entries.hasMoreElements()) {
//                	ZipEntry entry = entries.nextElement();
//                    com.movasim.sr.ws.generated.EISType eis = (com.movasim.sr.ws.generated.EISType) unmarshaller.unmarshal(zip.getInputStream(entry));
//                    for(com.movasim.sr.ws.generated.EISType.ProfileInfo profile : eis.getProfileInfo()){
//                    	String valueMsisdn= valuesMsisdn.get(profile.getIccid());
//                    	if(valueMsisdn!=null)
//                    		profile.getSubscriptionAddress().setMsisdn(valueMsisdn);
//                    }
//						loadSr(srname,eis);
//						loadDp(dpname,eis);
//                }
//    			
//                loadPageProvisioning(model);
//                model.addAttribute("msg4", "You have successfully uploaded");
//                
//                logger.logger("DEBUG", "SM-WEB", "Provisioning", "", "Upload eUICC", "doEuiccInfoUpload()", "", "", "DP name: " + dpname + ", " + "SR name: " + srname, "Successfull upload");
//                
//                return "provisioning";
//    		} catch (Exception e) {
//    			 StringWriter errors = new StringWriter();
//                 e.printStackTrace(new PrintWriter(errors));
//                 
//                 logger.logger("ERROR", "SM-WEB", "Provisioning", "", "Upload eUICC", "doEuiccInfoUpload()", "", "", "DP name: " + dpname + ", " + "SR name: " + srname, "Failed to upload. " + e);
//                 
//                 model.addAttribute("msg4", "You failed to upload "+ errors.toString());
//                 
//    			 e.printStackTrace();
//    			 loadPageProvisioning(model);
//                 return "provisioning";
//    		}
//        } else {
//        	loadPageProvisioning(model);
//        	
//        	logger.logger("WARNING", "SM-WEB", "Provisioning", "", "Upload eUICC", "doEuiccInfoUpload()", "", "", "DP name: " + dpname + ", " + "SR name: " + srname, "Unable to upload. File is empty.");
//        	
//            model.addAttribute("msg4", "Unable to upload. File is empty.");
            return "provisioning";
        }catch (Exception e) {
			// TODO: handle exception
		}
    }
        return "provisioning";
    }
    
    /*								UTILS								*/

//    private void loadPageProvisioning(Model model) {
//    List<ClasificationIpp> clasidications= clasifDao.retrieve();
//    List<SimpleClasificationIPP> categorys= new ArrayList<>();
//    List<SimpleClasificationIPP> clases= new ArrayList<>();
//    List<SimpleClasificationIPP> types= new ArrayList<>();
//    if(clasidications!=null){
//	    for (ClasificationIpp clasif: clasidications) {
//	        SimpleClasificationIPP element=new SimpleClasificationIPP(clasif);
//	        if(element.getType().equals(ClasificationIpp.IPP_CATEGORY))
//	            categorys.add(element);
//	        else if(element.getType().equals(ClasificationIpp.IPP_CLASS))
//	            clases.add(element);
//	        else if(element.getType().equals(ClasificationIpp.IPP_TYPE))
//	            types.add(element);
//	    }
//    }
//	List<com.movasim.dp.model.Keys> dps=(List<com.movasim.dp.model.Keys>) dpdao.retrieveAll();
//	ArrayList<String> dpname= new ArrayList<>();
//	if(dps!=null){
//		for(com.movasim.dp.model.Keys dp :dps)
//			dpname.add(dp.getName());
//	}
//    List<String> listMnoid= new ArrayList<>();
//    List<MNO> mnos=mnodao.retrieveAll();
//    for(MNO mno : mnos){
//    	listMnoid.add(mno.getName());
//    }
//    model.addAttribute("mnoid",listMnoid);
//	model.addAttribute("dp",dpname);
//    model.addAttribute("categoryFound", categorys);
//    model.addAttribute("claseFound", clases);
//    model.addAttribute("typeFound", types);
//	model.addAttribute("sr", srHelper.SrName());
//    }
//   
    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	    File convFile = new File( multipart.getOriginalFilename());
	    multipart.transferTo(convFile);
	    return convFile;
	}
    
//    private void loadSr(String srname,com.movasim.sr.ws.generated.EISType eis){
//        if(srname!=null){
//        PropertyType proper= new PropertyType();
//        proper.setKey("srname");
//        proper.setValue(srname);
//        com.movasim.sr.ws.generated.EISType.AdditionalProperties prop= new AdditionalProperties();
//        prop.getProperty().add(proper);
//        eis.setAdditionalProperties(prop);
//        }
//        ES1Helper.registerEis(eis);
//    }
//    
//    private void loadDp(String dpname, com.movasim.sr.ws.generated.EISType eis){
//    	Card card = cardao.retrieveByEid(Utils.toUnformattedHexArray(eis.getEumSignedInfo().getEid()));
//        if (card == null) {
//        	Card newCard = new Card(eis);
//            newCard.getEumSignedInfo().setPlatformType(dpname);
//            cardao.create(newCard);
//        } else {
//        	Card newCard = new Card(eis);
//            newCard.setId(card.getId());
//            newCard.getEumSignedInfo().setPlatformType(dpname);
//            cardao.update(newCard);
//        }
//    }
//    
//    private PresetCommand SettingsValuesDefaultIPP(String template,String iccid, String imsi, String msisdn,String mnoid, String fileName){
//    	PresetCommand presetCommand=new PresetCommand();
//    	presetCommand.setDescription(template);
//        presetCommand.setInstance("MOVES");
//        presetCommand.setMode("SCP81_SCP03");
//        presetCommand.setIccid(iccid);
//        presetCommand.setImsi(imsi);
//        presetCommand.setMsisdn(msisdn);
//		MNODAO mnodao=new MNODAO();
//		MNO mno=mnodao.retrieveByName(mnoid);
//		if(mno!=null)
//			presetCommand.setMnoid(mno.getMnoid());
//        //presetCommand.setAvailable(available);  con checkbox
//        presetCommand.setAvailable(true);		// sin checkbox
//        presetCommand.setGroup(" ");
//        presetCommand.setCommand(Utils.getFileContent(Settings.getInstance().getFILE_UPLOAD_PATH_IPP() + fileName, true));
//        return presetCommand;
//    }
//    
//    private TemplateIpp SettingsValuesDefaultTemplate(String category, String clase,String type,
//    		String template, Boolean available, String fileName){
//    	TemplateIpp temp= new TemplateIpp();
//    	temp.setCategory(category);
//        temp.setClase(clase);
//        temp.setName(template);
//        temp.setType(type);
//        temp.setDeleted(false);
//        temp.setActive(available);
//        temp.setTemplate(Utils.getFileContent(Settings.getInstance().getFILE_UPLOAD_PATH_TEMPLATE_IPP() + fileName, true));
//    	return temp;
//    }
    
}
