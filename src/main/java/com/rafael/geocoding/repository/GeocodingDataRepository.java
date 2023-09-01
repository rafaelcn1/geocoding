package com.rafael.geocoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.geocoding.model.GeocodingData;

public interface GeocodingDataRepository extends JpaRepository<GeocodingData, Long> {

}
