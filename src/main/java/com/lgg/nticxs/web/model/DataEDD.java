package com.lgg.nticxs.web.model;

public class DataEDD {
	private byte[] eid ;
	private String iccid;
	private String type;
	
	public DataEDD(byte[] eid, String iccid, String type){
		this.eid=eid;
		this.iccid=iccid;
		this.type=type;
	}

	public byte[] getEid() {
		return eid;
	}

	public void setEid(byte[] eid) {
		this.eid = eid;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
