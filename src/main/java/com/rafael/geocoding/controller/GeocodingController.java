package com.rafael.geocoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.geocoding.model.GeocodingData;
import com.rafael.geocoding.service.GeocodingService;

@RestController
@RequestMapping(value = "/api")
public class GeocodingController {

	@Autowired
	private GeocodingService geocodingService;

	// Rota para realizar geocodificação com base em latitude e longitude
	@GetMapping("/buscar")
	public ResponseEntity<GeocodingData> geocode(@RequestParam("latitude") Double latitude,
			@RequestParam("longitude") Double longitude) throws Exception {

		// Chama o serviço para realizar a geocodificação
		GeocodingData geocode = geocodingService.geocode(latitude, longitude);

		// Retorna a resposta com os dados geocodificados
		return ResponseEntity.ok(geocode);
	}

	// Rota para buscar um resultado de uma geocodificação por ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<GeocodingData> findById(@PathVariable Long id) {

		// Chama o serviço para buscar o resultado por ID
		GeocodingData geocode = geocodingService.findById(id);

		// Retorna a resposta com o resultado encontrado
		return ResponseEntity.ok().body(geocode);
	}

	// Rota para buscar todos os resultados geocodificados
	@GetMapping
	public ResponseEntity<List<GeocodingData>> findAll() {

		// Chama o serviço para buscar todos os resultados, salvando em uma Lista de
		// GeocodingData
		List<GeocodingData> findAll = geocodingService.findAll();

		// Retorna a resposta com a lista de resultados encontrados
		return ResponseEntity.ok().body(findAll);
	}

	// Rota para excluir um resultado geocodificado por ID
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<GeocodingData> delete(@PathVariable Long id) {
		geocodingService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
