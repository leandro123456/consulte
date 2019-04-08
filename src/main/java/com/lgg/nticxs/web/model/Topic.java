package com.lgg.nticxs.web.model;

import javax.persistence.Embeddable;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Embeddable
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Topic {
	
	@Field (name = "name")
	private String name;
	
	@Field (name = "user")
	private String user;
	
	@Field (name = "pass")
	private String pass;
	
	@Field (name = "iphost")
	private String iphost;
	
	@Field (name = "port")
	private String port;
	
	@Field (name = "usessl")
	private Boolean usessl;
	
	@Field (name = "topicescuchar")
	private String topicescuchar;
	
	@Field (name = "topicescribir")
	private String topicescribir;
	
	@Field (name = "topicescucharremote")
	private String topicescucharremote;
	
	@Field (name = "topicescribirremote")
	private String topicescribirremote;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getIphost() {
		return iphost;
	}

	public void setIphost(String iphost) {
		this.iphost = iphost;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Boolean getUsessl() {
		return usessl;
	}

	public void setUsessl(Boolean usessl) {
		this.usessl = usessl;
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
