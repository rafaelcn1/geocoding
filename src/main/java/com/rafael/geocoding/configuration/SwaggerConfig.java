package com.rafael.geocoding.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {// Este método cria e configura um bean Docket que controla a geração da
							// documentação Swagger.
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()

				// Define os controladores e pacotes a serem incluídos na documentação.
				.apis(RequestHandlerSelectors.basePackage("com.rafael.geocoding"))
				// Define quais caminhos de URL devem ser incluídos na documentação.
				.paths(PathSelectors.any())
				// Constrói e retorna o objeto Docket configurado.
				.build()
				// Chama o método apiInfo() para definir informações gerais sobre a API.
				.apiInfo(apiInfo());
	}

	/*
	 * Este método cria e retorna um objeto ApiInfo que contém informações sobre a
	 * API.
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Serviço de Geolocalização!") // Título da API
				.description("Geolocalização através da latitude e longitude, usando a API do Google")
				// Descrição da Versão da API // API
				.version("1.0")
				// Cria o objeto ApiInfo e o retorna.
				.build();
	}
}
