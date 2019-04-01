package com.lgg.nticxs.web.model;


/**
 * Created by movasim on 07/09/16.
 */
public class SimpleTemplate {
    private String ippId;
    private String name;
    private String category;
    private String cclass;
    private String type;
    private Boolean active;

//    public SimpleTemplate(TemplateIpp templateIpp) {
//        this.ippId = templateIpp.getId();
//        this.name = templateIpp.getName();
//        this.category = templateIpp.getCategory();
//        this.cclass = templateIpp.getClase();
//        this.type = templateIpp.getType();
//        this.active = templateIpp.getActive();
//    }

    public String getIppId() {
        return ippId;
    }

    public String getName() {
        return name;
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

    public Boolean getActive() {
        return active;
    }
}
