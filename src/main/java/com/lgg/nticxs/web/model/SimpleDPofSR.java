package com.lgg.nticxs.web.model;

import java.util.Date;


public class SimpleDPofSR {
	private String id;
	private String smdpid;
	private String url;
	private String endpoint;
	private String name;
	private Date createdAt;
	private Date updatedAt;
//	
//	public SimpleDPofSR(DP dp) {
//		this.id = dp.getId();
//		this.smdpid = dp.getSmdpId();
//		this.name = dp.getName();
//		this.url = dp.getUrl();
//		this.endpoint = dp.getUrlEndpoint();
//		this.createdAt = dp.getCreatedAt();
//		this.updatedAt = dp.getUpdatedAt();
//	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSmdpid() {
		return smdpid;
	}
	
	public void setSmdpid(String smdpid) {
		this.smdpid = smdpid;
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
