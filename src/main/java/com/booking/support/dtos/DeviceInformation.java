package com.booking.support.dtos;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class DeviceInformation implements Serializable {
	private List<AssetInfo> devices;
	private String appName;

	public List<AssetInfo> getDevices() {
		return devices;
	}

	public void setDevices(List<AssetInfo> devices) {
		this.devices = devices;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
