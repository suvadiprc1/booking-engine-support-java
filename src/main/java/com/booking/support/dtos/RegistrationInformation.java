package com.booking.support.dtos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RegistrationInformation implements Serializable {

	private boolean isRegistertered;
	private AssetInfo assetInfo;

	public boolean isRegistertered() {
		return isRegistertered;
	}

	public void setRegistertered(boolean isRegistertered) {
		this.isRegistertered = isRegistertered;
	}

	public AssetInfo getAssetInfo() {
		return assetInfo;
	}

	public void setAssetInfo(AssetInfo assetInfo) {
		this.assetInfo = assetInfo;
	}
}
