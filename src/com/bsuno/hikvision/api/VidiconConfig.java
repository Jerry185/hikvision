package com.bsuno.hikvision.api;

import java.io.Serializable;

/**
 * 摄像机的配置信息
 * @author bsuno
 *
 */
public class VidiconConfig implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String addr;
	private int port;
	private String username;
	private String password;
	private int channel;
	
	private String modelcode;
	
	/**重连间隔时间*/
	private long reconnectTimeout=60000;
	/**
	 * 重试次数，-1-无限次，0-不重试
	 * 谨慎使用-1
	 */
	private int maxReconnect= -1;
	
	/**品牌*/
	private String brand;
	/**型号*/
	private String model;
	/**版本*/
	private String version;
	
	private String serialNo;

	public VidiconConfig() {
		super();
	}

	public VidiconConfig(String id, String name, String addr, int port, String username,String password,String modelcode,int channel) {
		this();
		this.id = id;
		this.name = name;
		this.addr = addr;
		this.port = port;
		this.username = username;
		this.password = password;
		this.modelcode = modelcode;
		this.channel = channel;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getModelcode() {
		return modelcode;
	}

	public void setModelcode(String modelcode) {
		this.modelcode = modelcode;
	}

	public long getReconnectTimeout() {
		if(reconnectTimeout<1000) reconnectTimeout=1000;  //至少1秒
		return reconnectTimeout;
	}

	public void setReconnectTimeout(long reconnectTimeout) {
		this.reconnectTimeout = reconnectTimeout;
	}

	public int getMaxReconnect() {
		return maxReconnect;
	}

	public void setMaxReconnect(int maxReconnect) {
		this.maxReconnect = maxReconnect;
	}

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}
}
