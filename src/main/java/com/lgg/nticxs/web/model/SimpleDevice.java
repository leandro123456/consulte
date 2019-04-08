package com.lgg.nticxs.web.model;

import javax.persistence.Embeddable;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;


@Embeddable
@NoSql(dataFormat=DataFormatType.MAPPED)
public class SimpleDevice {

	
	@Field (name = "serialnumber")
	private String serialnumber;
	
	@Field (name = "owner")
	private String owner;
	
	@Field (name = "vista")
	private String vista;

    

    public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getVista() {
		return vista;
	}
	public void setVista(String vista) {
		this.vista = vista;
	}

}
