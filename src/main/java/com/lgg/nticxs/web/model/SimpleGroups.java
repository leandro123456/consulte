package com.lgg.nticxs.web.model;


/**
 * Created by movasim on 07/09/16.
 */
public class SimpleGroups {
	private String id;
	private String name;
    private String type;
    private String description;
    private Boolean enabled;
    private Boolean deleted;

//    public SimpleGroups(ClasificationIpp aux) {        
//        this.id = aux.getId();
//        this.name = aux.getName();
//        this.type = aux.getType();
//        this.description= aux.getDescription();
//        this.enabled = aux.getEnabled();
//        this.deleted=aux.getDeleted();
//    }

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
