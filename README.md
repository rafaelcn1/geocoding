# Serviço de Geolocalização

O serviço de Geolocalização é uma parte essencial deste projeto Spring Boot. Ele permite que você converta coordenadas de latitude e longitude em um endereço completo usando a API Geocoding do Google Maps. Além disso, ele armazena os resultados no banco de dados H2 para referência futura.

## Uso

### Endpoint

O serviço é acessado por meio do seguinte endpoint:

```
GET /api/geocoding?latitude={latitude}&longitude={longitude}
```

- `latitude` (obrigatório): A latitude da localização que você deseja geocodificar.
- `longitude` (obrigatório): A longitude da localização que você deseja geocodificar.

### Exemplo de Requisição

Para geocodificar uma localização, você pode fazer uma solicitação HTTP GET para o endpoint fornecendo as coordenadas de latitude e longitude:

```
GET /api/geocoding?latitude=40.7128&longitude=-74.0060
```

### Resposta

A resposta será um objeto JSON contendo informações sobre o endereço da localização geocodificada. Aqui está um exemplo de resposta:

```json
{
    "id": 1,
    "latitude": 40.7128,
    "longitude": -74.0060,
    "fullAddress": "1600 Amphitheatre Parkway, Mountain View, CA 94043, USA",
    "timestamp": "2023-08-25T14:30:00",
    "apiResponseJson": "{...}" // JSON completo da resposta da API Geocoding do Google
}
```

## Configuração

Para usar o serviço, você precisa configurar a chave da API do Google Maps em seu arquivo `application.properties`. Certifique-se de que a chave esteja válida e configurada corretamente.

```properties
google.maps.api.key=YOUR_API_KEY_HERE
```

## Erros

Se o serviço não conseguir encontrar um endereço para as coordenadas fornecidas, ele retornará uma resposta de erro com o status HTTP 500 e uma mensagem de erro correspondente.

## Autor

Este serviço foi desenvolvido por Rafael Cunha.
