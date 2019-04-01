package com.lgg.nticxs.web.model;



/**
 * Created by movasim on 07/09/16.
 */
public class SimpleSr {
    private String smsrid;
    private String name;
    private String url;
    private String id;
    private String owner;
    private String vendor;
    private Boolean active;
    private Boolean deleted;

//    public SimpleSr(SR sr) {
//        this.smsrid = sr.getSmsrId();
//        this.name = sr.getName();
//        this.url = sr.getUrl();
//        this.id = sr.getId();
//        this.owner= sr.getOwner();
//        this.vendor=sr.getVendor();
//        this.active=sr.getActive();
//        this.deleted=sr.getDeleted();
//    }

	public String getSmsrid() {
		return smsrid;
	}

	public void setSmsrid(String smsrid) {
		this.smsrid = smsrid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	

}
