package com.lgg.nticxs.web.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lgg.nticxs.web.DAO.Mongo.MongoDBObject;


public class Device extends MongoDBObject{

	private static final long serialVersionUID = -4346222511562336633L;
	static final public String TERMOMETRO = "termometro";
	static final public String SONOFF = "sonoff";
	static final public String ALARMA = "alarma";
	static final public String DOORMAN = "doorman";
	
	private String name;
	private String userowner;
	private String serialnumber;
	private String tipo;
	private String description;
	private byte[] password;
	private List<byte[]> historyPassword;
	private Boolean delete;
	private String role;
	private String timerString;
	private DeviceNotification lastnotification;
	private Map<String,String> particiones;
	private Integer mayorZonaInformada;
	private Map<String,String> zonasObtenidas;
	private List<DeviceAlarm> alarms;
	private Boolean usedefaultbrocker;
	private List<DeviceConfiguration> deviceconfiguration;
	private Map<String,String> vista;
	private List<String> users;
	private List<String> admins;
	private String particionactiva;
	private String calle;
	private String numero;
	private String depto;
	private String piso;
	private String localidad;
	private String codpostal;
	private String provincia;
	private String pais;
	private String tipodireccion;
	private String codigouri;
	private String uridoorman;
	private String coddesarmado;
	

	
	public Device() {
		usedefaultbrocker = true;
		delete = false;
		deviceconfiguration = new ArrayList<>();
		users = new  ArrayList<>();
		admins = new ArrayList<>();
		vista = new HashMap<String,String>();
	}
	
	
	
	public String getCoddesarmado() {
		return coddesarmado;
	}



	public void setCoddesarmado(String coddesarmado) {
		this.coddesarmado = coddesarmado;
	}



	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public void setCodpostal(String codpostal) {
		this.codpostal = codpostal;
	}

	public void setTipodireccion(String tipodireccion) {
		this.tipodireccion = tipodireccion;
	}

	public String getUserowner() {
		return userowner;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getCodpostal() {
		return codpostal;
	}

	public String getTipodireccion() {
		return tipodireccion;
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

	public String getCalle() {
		return calle;
	}


	public String getCodigouri() {
		return codigouri;
	}


	public void setCodigouri(String codigouri) {
		this.codigouri = codigouri;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getDepto() {
		return depto;
	}


	public void setDepto(String depto) {
		this.depto = depto;
	}


	public String getPiso() {
		return piso;
	}


	public void setPiso(String piso) {
		this.piso = piso;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getUridoorman() {
		return uridoorman;
	}


	public void setUridoorman(String uridoorman) {
		this.uridoorman = uridoorman;
	}


	public void setDeviceconfiguration(List<DeviceConfiguration> deviceconfiguration) {
		this.deviceconfiguration = deviceconfiguration;
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



	public String getParticionactiva() {
		return particionactiva;
	}

	public void setParticionactiva(String particionactiva) {
		this.particionactiva = particionactiva;
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

	public Map<String, String> getZonasObtenidas() {
		return zonasObtenidas;
	}

	public void setZonasObtenidas(Map<String, String> zonasObtenidas) {
		this.zonasObtenidas = zonasObtenidas;
	}

	public Map<String, String> getVista() {
		return vista;
	}

	public void setVista(Map<String, String> vistaPorUsuario) {
		this.vista = vistaPorUsuario;
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



	public Map<String, String> getParticiones() {
		return particiones;
	}

	public void setParticiones(Map<String, String> particiones) {
		this.particiones = particiones;
	}

	public Integer getMayorZonaInformada() {
		return mayorZonaInformada;
	}

	public void setMayorZonaInformada(Integer mayorZonaInformada) {
		this.mayorZonaInformada = mayorZonaInformada;
	}
	

//	public HashMap<Integer, Integer> getZonas() {
//		return zonas;
//	}
//
//	public void setZonas(HashMap<Integer, Integer> zonas) {
//		this.zonas = zonas;
//	}
	
}
