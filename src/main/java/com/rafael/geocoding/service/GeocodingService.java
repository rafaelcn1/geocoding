package com.rafael.geocoding.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.rafael.geocoding.model.GeocodingData;
import com.rafael.geocoding.repository.GeocodingDataRepository;

@Service
public class GeocodingService {

	//private String googleMapsApiKey = "AIzaSyCKa5m3XdAH15ovfFcpAct73DGv-hGwffM";
	
	@Value("${google.maps.api.key}")
	private String googleMapsApiKey;

	@Autowired
	private GeocodingDataRepository geocodingDataRepository;

	public GeocodingService(GeocodingDataRepository geocodingDataRepository) {
		this.geocodingDataRepository = geocodingDataRepository;
	}

	/**
	 * Realiza a geocodificação (conversão de coordenadas para endereço) e armazena
	 * os resultados no banco de dados.
	 *
	 * @param latitude  Latitude da localização.
	 * @param longitude Longitude da localização.
	 * @return Um objeto GeocodingData contendo informações do endereço.
	 * @throws Exception Se não for possível encontrar um endereço para as
	 *                   coordenadas fornecidas.
	 */
	public GeocodingData geocode(double latitude, double longitude) throws Exception {
		// Cria um contexto de API com sua chave do Google Maps
		GeoApiContext context = new GeoApiContext.Builder().apiKey(googleMapsApiKey).build();

		// Realiza a consulta à Geocoding API para obter resultados com base nas
		// coordenadas de latitude e longitude
		GeocodingResult[] results = GeocodingApi.newRequest(context)
				.latlng(new com.google.maps.model.LatLng(latitude, longitude)).await();

		// Inicializa o objeto Gson para serializar objetos em JSON
		Gson gson = new Gson();

		if (results != null && results.length > 0) {
			// Obtem o primeiro resultado (o resultado mais relevante)
			GeocodingResult geocodingResult = results[0];

			// Cria uma instância de GeocodingData para armazenar os resultados
			GeocodingData geocodingData = new GeocodingData();
			geocodingData.setFullAddress(geocodingResult.formattedAddress);
			geocodingData.setLatitude(latitude);
			geocodingData.setLongitude(longitude);
			geocodingData.setApiResponseJson(gson.toJson(geocodingResult));
			geocodingData.setTimestamp(LocalDateTime.now());

			// Salva o objeto GeocodingData no banco de dados
			return geocodingDataRepository.save(geocodingData);
		} else {
			throw new Exception("Não foi possível encontrar o endereço para as coordenadas fornecidas.");
		}
	}

	/**
	 * Busca uma geolocalização por ID no banco de dados.
	 *
	 * @param id O ID da geolocalização a ser buscada.
	 * @return A geolocalização encontrada.
	 * @throws ResourceNotFoundException Se a geolocalização não for encontrada.
	 */
	public GeocodingData findById(Long id) {
		Optional<GeocodingData> findById = geocodingDataRepository.findById(id);
		return findById.orElseThrow(() -> new RuntimeException("Geolocalização com ID: " + id + " não encontrada!"));
	}

	/**
	 * Retorna todas as geolocalizações cadastradas no banco de dados.
	 *
	 * @return Uma lista de todas as geolocalizações.
	 */
	public List<GeocodingData> findAll() {
		return geocodingDataRepository.findAll();
	}

	/**
	 * Deleta uma geolocalização por ID do banco de dados.
	 *
	 * @param id O ID da geolocalização a ser deletada.
	 * @throws ResourceNotFoundException Se a geolocalização não for encontrada.
	 */
	public void delete(Long id) {
		GeocodingData geocode = findById(id);
		geocodingDataRepository.deleteById(geocode.getId());
	}
}
