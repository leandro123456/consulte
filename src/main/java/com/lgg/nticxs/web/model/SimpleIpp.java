
package com.lgg.nticxs.web.model;


/**
 * Created by movasim on 07/09/16.
 */
public class SimpleIpp {
    private String id;
    private String imsi;
    private String iccid;
    private String msisdn;
    private String eid;
    private String category;
    private String clase;
    private String description;
    private String type;
    private String group;
    private Boolean deleted;
    private Boolean available;

//
//    public SimpleIpp(PresetCommand presetCommand) {
//        this.id = presetCommand.getId();
//        this.imsi = presetCommand.getImsi();
//        this.iccid = presetCommand.getIccid();
//        this.msisdn = presetCommand.getMsisdn();
//        this.category = presetCommand.getCategory();
//        this.clase = presetCommand.getClase();
//        this.type = presetCommand.getType();
//        this.group = presetCommand.getGroup();
//        this.deleted=presetCommand.isDelete();
//        this.description=presetCommand.getDescription();
//        this.available=presetCommand.isAvailable();
//    }

    public String getId() {
        return id;
    }

    public String getImsi() {
        return imsi;
    }

    public String getIccid() {
        return iccid;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public String getEid() {
        return eid;
    }

    public String getCategory() {
        return category;
    }

    public String getClase() {
        return clase;
    }

    public String getType() {
        return type;
    }

    public String getGroup() {
        return group;
    }

 
    public Boolean getAvailable() {
		return available;
	}

    public void setEid(String eid) {
        this.eid = eid;
    }

    public void setGroup(String group){
        this.group=group;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDeleted() {
		return deleted;
	}
    
}
