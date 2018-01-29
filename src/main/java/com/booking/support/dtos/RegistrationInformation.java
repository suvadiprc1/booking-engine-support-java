package com.booking.support.dtos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RegistrationInformation implements Serializable {

	private boolean isRegistertered;
	private DeviceInfo deviceInfo;

	public boolean isRegistertered() {
		return isRegistertered;
	}

	public void setRegistertered(boolean isRegistertered) {
		this.isRegistertered = isRegistertered;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
}
