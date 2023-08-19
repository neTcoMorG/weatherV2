package jun.weather.domain.weather;

import jun.weather.domain.RegionType;
import jun.weather.domain.entity.Region;
import jun.weather.domain.entity.Weather;
import jun.weather.domain.repository.RegionRepository;
import jun.weather.domain.repository.WeatherRepository;
import jun.weather.domain.weather.api.API;
import jun.weather.domain.weather.api.mapping.WeatherData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherScheduler {

    private final WeatherRepository weatherRepository;
    private final RegionRepository regionRepository;

    @Scheduled(fixedRate = 300000)
    public void processor() throws Exception {
        collect();
    }

    private void collect () throws Exception {
        for (int i = 0; i< RegionType.REGIONS.length; i++) {
            WeatherData data = API.getWeather(RegionType.REGIONS[i]);
            weatherRepository.save(createWeatherEntity(RegionType.KOREAN[i], data));
        }
    }

    private Weather createWeatherEntity (String region, WeatherData data) {
        Region findRegion = regionRepository.findByName(region).orElseThrow();
        return new Weather(findRegion, data);
    }
}
