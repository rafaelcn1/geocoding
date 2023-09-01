package com.rafael.geocoding.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.geocoding.model.GeocodingData;
import com.rafael.geocoding.repository.GeocodingDataRepository;

@Service
public class GeocodingService {
	@Autowired
	private GeocodingDataRepository geocodingDataRepository;

	public GeocodingData performGeocoding(Double latitude, Double longitude) {
		// Use a API de Geocoding do Google para obter o endereço a partir da latitude e
		// longitude

		GeocodingData geocodingData = new GeocodingData();
		geocodingData.setLatitude(latitude);
		geocodingData.setLongitude(longitude);
		geocodingData.setFullAddress("Endereço retornado pela API");
		geocodingData.setTimestamp(LocalDateTime.now());
		geocodingData.setApiResponseJson("JSON retornado pela API");

		return geocodingDataRepository.save(geocodingData);
	}
}
