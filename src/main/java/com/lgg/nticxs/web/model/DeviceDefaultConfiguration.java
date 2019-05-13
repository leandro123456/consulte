package com.lgg.nticxs.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class DeviceDefaultConfiguration extends DeviceConfiguration {
	@Id
	@GeneratedValue
	@Field(name = "_id")
	private String id;
	
	@Field (name = "name")
	private String name;
	
	@Field (name = "userescuchar")
	private String userescuchar;
	
	@Field (name = "passescuchar")
	private String passescuchar;
	
	@Field (name = "iphostescuchar")
	private String iphostescuchar;
	
	@Field (name = "portescuchar")
	private String portescuchar;
	
	@Field (name = "usesslescuchar")
	private Boolean usesslescuchar;
	
	@Field (name = "topicescuchar")
	private String topicescuchar;
	
	@Field (name = "userescribir")
	private String userescribir;
	
	@Field (name = "passescribir")
	private String passescribir;
	
	@Field (name = "iphostescribir")
	private String iphostescribir;
	
	@Field (name = "portescribir")
	private String portescribir;
	
	@Field (name = "usesslescribir")
	private Boolean usesslescribir;
	
	@Field (name = "topicescribir")
	private String topicescribir;
	
	@Field (name = "userescucharremote")
	private String userescucharremote;
	
	@Field (name = "passescucharremote")
	private String passescucharremote;
	
	@Field (name = "iphostescucharremote")
	private String iphostescucharremote;
	
	@Field (name = "portescucharremote")
	private String portescucharremote;
	
	@Field (name = "usesslescucharremote")
	private Boolean usesslescucharremote;
	
	@Field (name = "topicescucharremote")
	private String topicescucharremote;
	
	@Field (name = "userescribirremote")
	private String userescribirremote;
	
	@Field (name = "passescribirremote")
	private String passescribirremote;
	
	@Field (name = "iphostescribirremote")
	private String iphostescribirremote;
	
	@Field (name = "portescribirremote")
	private String portescribirremote;
	
	@Field (name = "usesslescribirremote")
	private Boolean usesslescribirremote;
	
	@Field (name = "topicescribirremote")
	private String topicescribirremote;

	
	
	public DeviceDefaultConfiguration() {
	}

	public DeviceDefaultConfiguration(String serial) {
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

	@Override
	public void setTopicescuchar(String topicescuchar) {
		this.topicescuchar = topicescuchar+"/state";
	}

	public String getTopicescribir() {
		return topicescribir;
	}

	public void setTopicescribir(String topicescribir) {
		this.topicescribir = topicescribir+"/write";
	}

	public String getTopicescucharremote() {
		return topicescucharremote;
	}

	public void setTopicescucharremote(String topicescucharremote) {
		this.topicescucharremote = topicescucharremote+"/state";
	}

	public String getTopicescribirremote() {
		return topicescribirremote;
	}

	public void setTopicescribirremote(String topicescribirremote) {
		this.topicescribirremote = topicescribirremote+"/write";
	}


}
