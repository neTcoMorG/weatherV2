package jun.weather.web;

import jun.weather.domain.weather.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Controller {

    private final WeatherService weatherService;

    @GetMapping("/{regionId}")
    public HttpEntity<?> getTotalRecords (@PathVariable Long regionId) {
        return ResponseEntity.ok(weatherService.getWeather(regionId));
    }

    @GetMapping("/temp/{regionId}")
    public HttpEntity<?> getTempRecords (@PathVariable Long regionId) {
        return ResponseEntity.ok(weatherService.getTempRecords(regionId));
    }

    @GetMapping("/hum/{regionId}")
    public HttpEntity<?> getHumRecords (@PathVariable Long regionId) {
        return ResponseEntity.ok(weatherService.getHumRecords(regionId));
    }

    @GetMapping("/wind/{regionId}")
    public HttpEntity<?> getWindRecords (@PathVariable Long regionId) {
        return ResponseEntity.ok(weatherService.getWindRecords(regionId));
    }
}
