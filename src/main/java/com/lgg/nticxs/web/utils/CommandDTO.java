package com.lgg.nticxs.web.utils;


public class CommandDTO {
	private byte[] eid ;
	private String iccid;
	private String command;
	private Boolean moreToDo;
	

	public CommandDTO(byte[] eid,String iccid, String command) {
		this.eid=eid;
		this.iccid=iccid;
		this.command = command;
		this.moreToDo = false;
		
	}

	public byte[] getEid() {
		return eid;
	}

	public String getIccid() {
		return iccid;
	}

	public String getCommand() {
		return command;
	}

	public Boolean getMoreToDo() {
		return moreToDo;
	}
	
}
