package jun.weather.domain.weather.api;

import jun.weather.domain.weather.api.mapping.WeatherData;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class API {

    private API() {}

    public static WeatherData getWeather (String region) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.openweathermap.org")
                .path("/data/2.5/weather")
                .queryParam("q", region + ",kr")
                .queryParam("appid", "0bca0313b02e0b99e35433afd9f4721b")
                .queryParam("lang", "kr")
                .encode()
                .build()
                .toUri();

        return new RestTemplate().getForEntity(uri, WeatherData.class).getBody();
    }
}
