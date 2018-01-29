package com.booking.support.dtos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DeviceInfo implements Serializable {

	private String uuid;
	private String region;
	private String assetId;
	private String message;
	private String longitude;
	private String latitude;
	private String locationDetails;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
