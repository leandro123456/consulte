package com.lgg.nticxs.web.model;


public class DeviceConfiguration {
	

	private String name;
	private String userescuchar;
	private String passescuchar;
	private String iphostescuchar;
	private String portescuchar;
	private Boolean usesslescuchar;
	private String topicescuchar;
	private String userescribir;
	private String passescribir;
	private String iphostescribir;
	private String portescribir;
	private Boolean usesslescribir;
	private String topicescribir;
	private String userescucharremote;
	private String passescucharremote;
	private String iphostescucharremote;
	private String portescucharremote;
	private Boolean usesslescucharremote;
	private String topicescucharremote;
	private String userescribirremote;
	private String passescribirremote;
	private String iphostescribirremote;
	private String portescribirremote;
	private Boolean usesslescribirremote;
	private String topicescribirremote;

	
	
	public DeviceConfiguration() {
	}

	public DeviceConfiguration(DeviceDefaultConfiguration deviceconfiguration) {
		this.name= deviceconfiguration.getName();
		this.userescuchar=deviceconfiguration.getUserescuchar();
		this.passescuchar = deviceconfiguration.getPassescuchar();
		this.iphostescuchar = deviceconfiguration.getIphostescuchar();
		this.portescuchar = deviceconfiguration.getPortescuchar();
		this.usesslescuchar = deviceconfiguration.getUsesslescuchar();
		this.topicescuchar=deviceconfiguration.getTopicescuchar();
		this.userescribir=deviceconfiguration.getUserescribir();
		this.passescribir=deviceconfiguration.getPassescribir();
		this.iphostescribir=deviceconfiguration.getIphostescribir();
		this.portescribir=deviceconfiguration.getPortescribir();
		this.usesslescribir=deviceconfiguration.getUsesslescribir();
		this.topicescribir=deviceconfiguration.getTopicescribir();
		this.userescucharremote=deviceconfiguration.getUserescucharremote();
		this.passescucharremote=deviceconfiguration.getPassescucharremote();
		this.iphostescucharremote=deviceconfiguration.getIphostescucharremote();
		this.portescucharremote= deviceconfiguration.getPortescucharremote();
		this.usesslescucharremote=deviceconfiguration.getUsesslescucharremote();
		this.topicescucharremote=deviceconfiguration.getTopicescucharremote();
		this.userescribirremote=deviceconfiguration.getUserescribirremote();
		this.passescribirremote=deviceconfiguration.getPassescribirremote();
		this.iphostescribirremote=deviceconfiguration.getIphostescribirremote();
		this.portescribirremote=deviceconfiguration.getPortescribirremote();
		this.usesslescribirremote=deviceconfiguration.getUsesslescribirremote();
		this.topicescribirremote=deviceconfiguration.getTopicescribirremote();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserescuchar() {
		return userescuchar;
	}

	public void setUserescuchar(String userescuchar) {
		this.userescuchar = userescuchar;
	}

	public String getPassescuchar() {
		return passescuchar;
	}

	public void setPassescuchar(String passescuchar) {
		this.passescuchar = passescuchar;
	}

	public String getIphostescuchar() {
		return iphostescuchar;
	}

	public void setIphostescuchar(String iphostescuchar) {
		this.iphostescuchar = iphostescuchar;
	}

	public String getPortescuchar() {
		return portescuchar;
	}

	public void setPortescuchar(String portescuchar) {
		this.portescuchar = portescuchar;
	}

	public Boolean getUsesslescuchar() {
		return usesslescuchar;
	}

	public void setUsesslescuchar(Boolean usesslescuchar) {
		this.usesslescuchar = usesslescuchar;
	}

	public String getUserescribir() {
		return userescribir;
	}

	public void setUserescribir(String userescribir) {
		this.userescribir = userescribir;
	}

	public String getPassescribir() {
		return passescribir;
	}

	public void setPassescribir(String passescribir) {
		this.passescribir = passescribir;
	}

	public String getIphostescribir() {
		return iphostescribir;
	}

	public void setIphostescribir(String iphostescribir) {
		this.iphostescribir = iphostescribir;
	}

	public String getPortescribir() {
		return portescribir;
	}

	public void setPortescribir(String portescribir) {
		this.portescribir = portescribir;
	}

	public Boolean getUsesslescribir() {
		return usesslescribir;
	}

	public void setUsesslescribir(Boolean usesslescribir) {
		this.usesslescribir = usesslescribir;
	}

	public String getUserescucharremote() {
		return userescucharremote;
	}

	public void setUserescucharremote(String userescucharremote) {
		this.userescucharremote = userescucharremote;
	}

	public String getPassescucharremote() {
		return passescucharremote;
	}

	public void setPassescucharremote(String passescucharremote) {
		this.passescucharremote = passescucharremote;
	}

	public String getIphostescucharremote() {
		return iphostescucharremote;
	}

	public void setIphostescucharremote(String iphostescucharremote) {
		this.iphostescucharremote = iphostescucharremote;
	}

	public String getPortescucharremote() {
		return portescucharremote;
	}

	public void setPortescucharremote(String portescucharremote) {
		this.portescucharremote = portescucharremote;
	}

	public Boolean getUsesslescucharremote() {
		return usesslescucharremote;
	}

	public void setUsesslescucharremote(Boolean usesslescucharremote) {
		this.usesslescucharremote = usesslescucharremote;
	}

	public String getUserescribirremote() {
		return userescribirremote;
	}

	public void setUserescribirremote(String userescribirremote) {
		this.userescribirremote = userescribirremote;
	}

	public String getPassescribirremote() {
		return passescribirremote;
	}

	public void setPassescribirremote(String passescribirremote) {
		this.passescribirremote = passescribirremote;
	}

	public String getIphostescribirremote() {
		return iphostescribirremote;
	}

	public void setIphostescribirremote(String iphostescribirremote) {
		this.iphostescribirremote = iphostescribirremote;
	}

	public String getPortescribirremote() {
		return portescribirremote;
	}

	public void setPortescribirremote(String portescribirremote) {
		this.portescribirremote = portescribirremote;
	}

	public Boolean getUsesslescribirremote() {
		return usesslescribirremote;
	}

	public void setUsesslescribirremote(Boolean usesslescribirremote) {
		this.usesslescribirremote = usesslescribirremote;
	}

	public String getTopicescuchar() {
		return topicescuchar;
	}

	public void setTopicescuchar(String topicescuchar) {
		this.topicescuchar = topicescuchar;
	}

	public String getTopicescribir() {
		return topicescribir;
	}

	public void setTopicescribir(String topicescribir) {
		this.topicescribir = topicescribir;
	}

	public String getTopicescucharremote() {
		return topicescucharremote;
	}

	public void setTopicescucharremote(String topicescucharremote) {
		this.topicescucharremote = topicescucharremote;
	}

	public String getTopicescribirremote() {
		return topicescribirremote;
	}

	public void setTopicescribirremote(String topicescribirremote) {
		this.topicescribirremote = topicescribirremote;
	}


}
