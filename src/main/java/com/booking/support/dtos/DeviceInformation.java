package com.booking.support.dtos;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class DeviceInformation implements Serializable {
	private List<DeviceInfo> devices;
	private String appName;

	public List<DeviceInfo> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceInfo> devices) {
		this.devices = devices;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
