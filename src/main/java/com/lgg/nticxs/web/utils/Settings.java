package com.lgg.nticxs.web.utils;

import java.io.FileInputStream;
import java.util.Properties;


public class Settings {
	private Properties properties;
    private static Settings instance = null;
	private String URIBackend;
	private boolean TLSenable;


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
        properties = new Properties();
        try {
            properties.load(new FileInputStream("/var/cdash/properties/cdash.properties"));
            this.TLSenable = Boolean.parseBoolean(properties.getProperty("TLSenable"));
			this.URIBackend = properties.getProperty("URIBackend");;
        } catch (Exception e) {
        	System.out.println("Error... no se puede leer el archivo de propiedades");
            e.printStackTrace();
        }
    }

	public String getURIBackend() {
		return URIBackend;
	}

	public boolean isTLSenable() {
		return TLSenable;
	}

    
}
