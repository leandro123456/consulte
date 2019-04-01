package com.lgg.nticxs.web.model;



/**
 * Created by movasim on 07/09/16.
 */
public class SimpleProfileCard {
    private String ippId;
    private String ippDescription;
    private String iccid;
    private String imsi;
    private String msisdn;
    private String category;
    private String cclass;
    private String type;
    private String group;
    private String state;
    private Boolean falback;

    
//    public SimpleProfileCard(CardProfile cardProfile) {
//        this.ippId = "";
//        this.iccid = cardProfile.getIccid();
//        this.imsi = cardProfile.getImsi();
//        this.msisdn = cardProfile.getMsisdn();
//        this.type = cardProfile.getProfileType();
//        this.state = cardProfile.getState();
//        this.falback = cardProfile.isFallbackAttribute();
//        
//        PresetCommandDAO presetCommandDAO = new PresetCommandDAO();
//        PresetCommand presetCommand = presetCommandDAO.retrieveByICCID(this.iccid);
//
//        if (presetCommand != null)
//            this.ippDescription = presetCommand.getDescription();
//    }

    public String getIppId() {
        return ippId;
    }

    public String getIccid() {
        return iccid;
    }

    public String getImsi() {
        return imsi;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public String getCategory() {
        return category;
    }

    public String getCclass() {
        return cclass;
    }

    public String getType() {
        return type;
    }

    public String getGroup() {
        return group;
    }

    public String getState() {
        return state;
    }

    public String getIppDescription() {
        return ippDescription;
    }

	public Boolean getFalback() {
		return falback;
	}
    
}
