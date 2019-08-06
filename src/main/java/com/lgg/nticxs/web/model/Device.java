package com.lgg.nticxs.web.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Device {
	static final public String TERMOMETRO = "termometro";
	static final public String SONOFF = "sonoff";
	static final public String ALARMA = "alarma";
	
	@Id
	@GeneratedValue
	@Field(name = "_id")
	private String id;
	
	@Field(name = "name")
	private String name;
	
	@Field (name = "userowner")
	private String userowner;
	
	@Field (name = "serialnumber")
	private String serialnumber;
	
	@Field (name = "tipo")
	private String tipo;
	
	@Field (name = "description")
	private String description;
	
	@Field (name = "password")
	private byte[] password;
	
	@Field (name = "historyPassword")
	private List<byte[]> historyPassword;
	
	@Field (name = "delete")
	private Boolean delete;

	@Field (name = "role")
	private String role;
	
	@Field (name = "timerString")
	private String timerString;
	
	@Field (name = "lastnotification")
	private DeviceNotification lastnotification;
	
	@Field (name = "particiones")
	private Integer particiones;
	
	@Field (name = "zonas")
	private HashMap<Integer, Integer> zonas;
	
	@ElementCollection
	@Field (name = "alarms")
	private List<DeviceAlarm> alarms;
	
	@Field (name = "usedefaultbrocker")
	private Boolean usedefaultbrocker;
	
	@ElementCollection //es una lista, en la posicion 0 esta el default
	@Field(name="deviceconfiguration")
	private List<DeviceConfiguration> deviceconfiguration;
	
	@Field (name = "vista")
	private HashMap<String, String> vista;
	
//	@Field (name = "vistaporusuario")
//	private HashMap<String, String> vistaporusuario;
	
	@ElementCollection 
	@Field(name="users")
	private List<String> users;
	
	@ElementCollection 
	@Field(name="admins")
	private List<String> admins;

	
	public Device() {
		usedefaultbrocker = true;
		delete = false;
		deviceconfiguration = new ArrayList<>();
		users = new  ArrayList<>();
		admins = new ArrayList<>();
		vista = new HashMap<>();
//		vistaporusuario = new HashMap<>();
	}

	public String getUserowner() {
		return userowner;
	}

	public void setUserowner(String userowner) {
		this.userowner = userowner;
	}

	public Boolean getUsedefaultbrocker() {
		return usedefaultbrocker;
	}

	public void setUsedefaultbrocker(Boolean usedefaultbrocker) {
		this.usedefaultbrocker = usedefaultbrocker;
	}

	public List<DeviceConfiguration> getDeviceconfiguration() {
		return deviceconfiguration;
	}

	public void setDeviceconfiguration(List<DeviceConfiguration> deviceconfiguration) {
		this.deviceconfiguration = deviceconfiguration;
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

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<byte[]> getHistoryPassword() {
		return historyPassword;
	}

	public void setHistoryPassword(List<byte[]> historyPassword) {
		this.historyPassword = historyPassword;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HashMap<String, String> getVista() {
		return vista;
	}

	public void setVista(HashMap<String, String> vista) {
		this.vista = vista;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public List<String> getAdmins() {
		return admins;
	}

	public void setAdmins(List<String> admins) {
		this.admins = admins;
	}


	public String getTimerString() {
		return timerString;
	}

	public void setTimerString(String timerString) {
		this.timerString = timerString;
	}
	
	public DeviceNotification getLastnotification() {
		return lastnotification;
	}

	public void setLastnotification(DeviceNotification lastnotification) {
		this.lastnotification = lastnotification;
	}

	public List<DeviceAlarm> getAlarms() {
		return alarms;
	}

	public void setAlarms(List<DeviceAlarm> alarms) {
		this.alarms = alarms;
	}

	public String getUserRole(String userTarget) {
		if(userowner.equals(userTarget))
			return User.ROLE_SUPERADMIN;
		if(users.contains(userTarget))
			return User.ROLE_USER;
		if(admins.contains(userTarget))
			return User.ROLE_ADMIN;
		else
			return "fallo";
	}

	public Integer getParticiones() {
		return particiones;
	}

	public void setParticiones(Integer particiones) {
		this.particiones = particiones;
	}

	public HashMap<Integer, Integer> getZonas() {
		return zonas;
	}

	public void setZonas(HashMap<Integer, Integer> zonas) {
		this.zonas = zonas;
	}
	
}
