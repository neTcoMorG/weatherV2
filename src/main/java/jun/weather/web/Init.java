package jun.weather.web;

import jakarta.annotation.PostConstruct;
import jun.weather.domain.RegionType;
import jun.weather.domain.entity.Region;
import jun.weather.domain.entity.Weather;
import jun.weather.domain.repository.RegionRepository;
import jun.weather.domain.repository.WeatherRepository;
import jun.weather.domain.weather.api.API;
import jun.weather.domain.weather.api.mapping.WeatherData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class Init {

    private final RegionRepository regionRepository;
    private final WeatherRepository weatherRepository;

    @PostConstruct
    private void init () throws Exception {
        initRegion();
        initWeather();
    }

    private void initRegion () {
        for (String region : RegionType.KOREAN) {
            if (!isExistRegion(region))
                regionRepository.save(new Region(region));
        }
    }

    private void initWeather () throws Exception {
        for (int i=0; i < RegionType.REGIONS.length; i++) {
            WeatherData data = API.getWeather(RegionType.REGIONS[i]);
            weatherRepository.save(createWeatherEntity(RegionType.KOREAN[i], data));
        }
    }

    private Weather createWeatherEntity (String region, WeatherData data) {
        Region findRegion = regionRepository.findByName(region).orElseThrow();
        return new Weather(findRegion, data);
    }

    private boolean isExistRegion (String region) {
        Optional<Region> findRegion = regionRepository.findByName(region);
        return findRegion.isPresent();
    }
}
