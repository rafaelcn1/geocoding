package com.rafael.geocoding.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rafael.geocoding.controller.GeocodingController;
import com.rafael.geocoding.model.GeocodingData;
import com.rafael.geocoding.service.GeocodingService;

//Declaração de uma classe de teste JUnit para testar a classe GeocodingController
public class GeocodingControllerTest {

	// Declaração de uma instância do MockMvc para simular as solicitações HTTP
	private MockMvc mockMvc;

	// Injeção de dependências do controlador (GeocodingController) e do serviço
	// (GeocodingService)
	@InjectMocks
	private GeocodingController geocodingController;

	@Mock
	private GeocodingService geocodingService;

	// Método executado antes de cada teste
	@BeforeEach
	public void setup() {
		// Inicializa as anotações Mockito
		MockitoAnnotations.initMocks(this);

		// Configura o MockMvc para o controlador
		mockMvc = MockMvcBuilders.standaloneSetup(geocodingController).build();
	}

	// Teste para o método 'geocode' do controlador
	@Test
	public void testGeocode() throws Exception {
		// Dados de exemplo para os parâmetros da solicitação
		Double latitude = 123.456;
		Double longitude = 789.012;

		// Cria um objeto GeocodingData de exemplo que você espera que o serviço retorne
		GeocodingData expectedGeocode = new GeocodingData();
		expectedGeocode.setLatitude(latitude);
		expectedGeocode.setLongitude(longitude);

		// Configura o comportamento do serviço mock para retornar o objeto esperado
		when(geocodingService.geocode(latitude, longitude)).thenReturn(expectedGeocode);

		// Executa uma solicitação GET simulada usando o MockMvc
		mockMvc.perform(get("/api/buscar").param("latitude", latitude.toString())
				.param("longitude", longitude.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // Verifica se a resposta tem status 200 (OK)
				.andExpect(jsonPath("$.latitude").value(latitude)) // Verifica se o valor da latitude na resposta está
																	// correto
				.andExpect(jsonPath("$.longitude").value(longitude)); // Verifica se o valor da longitude na resposta
																		// está correto

		// Verifica se o método do serviço foi chamado com os parâmetros corretos
		verify(geocodingService, times(1)).geocode(latitude, longitude);

		// Verifica se não houve mais interações com o serviço mock
		verifyNoMoreInteractions(geocodingService);
	}
}
