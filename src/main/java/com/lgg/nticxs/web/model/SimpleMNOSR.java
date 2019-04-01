package com.lgg.nticxs.web.model;


/**
 * Created by movasim on 07/09/16.
 */
public class SimpleMNOSR {
	private String id;
    private String mnoid;
    private String name;
    private String url;
    private String endpoint;
    private String smsc;
    private Boolean deleted;

//    public SimpleMNOSR(MNO mno) {
//    	this.id=mno.getId();
//        this.mnoid = mno.getMnoid();
//        this.name = mno.getName();
//        this.url = mno.getUrl();
//        this.endpoint= mno.getUrlEndpoint();
//        this.smsc= mno.getSmsc();
//        this.deleted=mno.getDeleted();
//    }

	public String getMnoid() {
		return mnoid;
	}

	public void setMnoid(String mnoid) {
		this.mnoid = mnoid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getSmsc() {
		return smsc;
	}

	public void setSmsc(String smsc) {
		this.smsc = smsc;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}