package com.rafael.geocoding.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class GeocodingData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double latitude;
	private Double longitude;
	private String fullAddress;
	private LocalDateTime timestamp;
	@Lob
	private String apiResponseJson;

	public GeocodingData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GeocodingData(Long id, Double latitude, Double longitude, String fullAddress, LocalDateTime timestamp,
			String apiResponseJson) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.fullAddress = fullAddress;
		this.timestamp = timestamp;
		this.apiResponseJson = apiResponseJson;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiResponseJson() {
		return apiResponseJson;
	}

	public void setApiResponseJson(String apiResponseJson) {
		this.apiResponseJson = apiResponseJson;
	}

}
