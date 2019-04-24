package com.lgg.nticxs.web.model.simple;

import com.lgg.nticxs.web.model.Device;

public class SimpleDevice {
	private String serial;
	private String name;
	private String description;
	private Boolean defaultconfiguration;
	private Boolean sharedhowuser;
	private Boolean sharedhowadmin;
	
	
	public SimpleDevice(Device device) {
		this.serial = device.getSerialnumber();
		this.name = device.getName();
		this.description = device.getDescription();
		this.defaultconfiguration = device.getUsedefaultbrocker();
		this.sharedhowuser = device.getUsers().size()>0;
		this.sharedhowadmin = device.getAdmins().size()>0;
		
	}


	public String getSerial() {
		return serial;
	}


	public void setSerial(String serial) {
		this.serial = serial;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Boolean getDefaultconfiguration() {
		return defaultconfiguration;
	}


	public void setDefaultconfiguration(Boolean defaultconfiguration) {
		this.defaultconfiguration = defaultconfiguration;
	}


	public Boolean getSharedhowuser() {
		return sharedhowuser;
	}


	public void setSharedhowuser(Boolean sharedhowuser) {
		this.sharedhowuser = sharedhowuser;
	}


	public Boolean getSharedhowadmin() {
		return sharedhowadmin;
	}


	public void setSharedhowadmin(Boolean sharedhowadmin) {
		this.sharedhowadmin = sharedhowadmin;
	}
	
	
}
