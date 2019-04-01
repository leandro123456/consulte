package com.lgg.nticxs.web.utils;

/**
 * Created by movasim on 09/09/16.
 */
import java.io.FileInputStream;
import java.util.Properties;


public class Settings {
	private WSLogger logger = new WSLogger();
	
	private Properties propertiesWEB;
    private static Settings instance = null;

    private String inicio_primer_timestre;
    private String inicio_segundo_timestre;
    private String inicio_tercer_timestre;
    private String fin_tercer_timestre;
    private String DP_URL;
    private String SR_URL;
    private String usValid;
    private String passValid;
    private String usMovasim;
    private String passMovasim;
    private String usTenant;
    private String passTenant;
    private String iccidName;
    private String imsiName;
    private String msisdnName;
	private String parameterToDecrypted;
	private String keyMovasim;
	private String path_logCcm;
	private String path_logDp;
	private String path_logSr;
	private boolean mutualAuth;
	private String keystore;
	private String keystoreAlias;
	private String keystorePass;
	private String queueName;
	private String movasimDBName;
	private String movasimPathCurl;

	private Settings() {
        this.load();
    }

    public static Settings getInstance() {
        if (instance==null) {
            instance = new Settings();
        }
        return instance;
    }


    public void load() {
        propertiesWEB = new Properties();
        try {
            propertiesWEB.load(new FileInputStream("/var/serpierodavinci/configuracion.properties"));
            this.inicio_primer_timestre = propertiesWEB.getProperty("inicio_primer_timestre");
            this.inicio_segundo_timestre = propertiesWEB.getProperty("inicio_segundo_timestre");
            this.inicio_tercer_timestre = propertiesWEB.getProperty("inicio_tercer_timestre");
            this.fin_tercer_timestre = propertiesWEB.getProperty("fin_tercer_timestre");
            this.SR_URL = propertiesWEB.getProperty("urlSr");
            this.usValid = propertiesWEB.getProperty("usValid");
            this.passValid = propertiesWEB.getProperty("passValid");
            this.usMovasim = propertiesWEB.getProperty("usMovasim");
            this.passMovasim = propertiesWEB.getProperty("passMovasim");
            this.usTenant = propertiesWEB.getProperty("usTenant");
            this.passTenant = propertiesWEB.getProperty("passTenant");
            this.iccidName = propertiesWEB.getProperty("iccidName");
            this.imsiName = propertiesWEB.getProperty("imsiName");
            this.msisdnName = propertiesWEB.getProperty("msisdnName");
			this.parameterToDecrypted=propertiesWEB.getProperty("parameterToDecrypted");
			this.keyMovasim = propertiesWEB.getProperty("keyMovasim");
			this.path_logCcm = propertiesWEB.getProperty("path_logCcm");
			this.path_logDp = propertiesWEB.getProperty("path_logDp");
			this.path_logSr = propertiesWEB.getProperty("path_logSr");
			this.mutualAuth = Boolean.parseBoolean(propertiesWEB.getProperty("mutualauth"));
			this.keystore = propertiesWEB.getProperty("keystore");
			this.keystoreAlias = propertiesWEB.getProperty("keystorealias");
			this.keystorePass = propertiesWEB.getProperty("keystorepass");
			this.queueName = propertiesWEB.getProperty("queueName");
			this.movasimDBName = propertiesWEB.getProperty("movasimDBName");
			this.movasimPathCurl = propertiesWEB.getProperty("movasimPathCurl");
        } catch (Exception e) {

            logger.logger("ERROR", "SM-WEB", "Persistence", "", "", "load()", "", "", "", "Cannot load the properties file");
            
            e.printStackTrace();
        }
    }

	public String getInicio_primer_timestre() {
		return inicio_primer_timestre;
	}

	public void setInicio_primer_timestre(String inicio_primer_timestre) {
		this.inicio_primer_timestre = inicio_primer_timestre;
	}

	public String getInicio_segundo_timestre() {
		return inicio_segundo_timestre;
	}

	public void setInicio_segundo_timestre(String inicio_segundo_timestre) {
		this.inicio_segundo_timestre = inicio_segundo_timestre;
	}

	public String getInicio_tercer_timestre() {
		return inicio_tercer_timestre;
	}

	public void setInicio_tercer_timestre(String inicio_tercer_timestre) {
		this.inicio_tercer_timestre = inicio_tercer_timestre;
	}

	public String getFin_tercer_timestre() {
		return fin_tercer_timestre;
	}

	public void setFin_tercer_timestre(String fin_tercer_timestre) {
		this.fin_tercer_timestre = fin_tercer_timestre;
	}

}
