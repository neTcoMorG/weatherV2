package jun.weather.domain.weather;

import jun.weather.domain.dto.Record;
import jun.weather.domain.dto.RegionData;
import jun.weather.domain.dto.WeatherInformation;
import jun.weather.domain.entity.Region;
import jun.weather.domain.entity.Weather;
import jun.weather.domain.repository.RegionRepository;
import jun.weather.domain.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final RegionRepository regionRepository;

    @Transactional
    public List<WeatherInformation> getAll (int limit) {
        List<WeatherInformation> result = new ArrayList<>();
        List<Region> regions = regionRepository.findAll();

        regions.forEach(region -> {
            WeatherInformation info = new WeatherInformation(region.getName(),
                    getHumRecords(region.getId()), getTempRecords(region.getId()), getWindRecords(region.getId()));
            result.add(info);
        });

        return result;
    }

    public WeatherInformation getWeather (Long id) {
        String regionName = regionRepository.findById(id).orElseThrow().getName();
        return new WeatherInformation(regionName, getHumRecords(id), getTempRecords(id), getWindRecords(id));
    }

    public List<RegionData> getAllRegionRecentValue () {
        return weatherRepository.findRecentRegionGroupBy();
    }

    public List<Record<Integer>> getHumRecords (Long regionId) {
        return getWeatherRecords(regionId, Weather::getHumidity);
    }

    public List<Record<Double>> getTempRecords (Long regionId) {
        return getWeatherRecords(regionId, Weather::getTemp);
    }

    public List<Record<Double>> getWindRecords (Long regionId) {
        return getWeatherRecords(regionId, Weather::getWind_speed);
    }

    private <T> List<Record<T>> getWeatherRecords (Long regionId, Function<Weather, T> getter) {
        Region findRegion = getRegion(regionId);

        List<Weather> records = weatherRepository.findTop1000ByRegionOrderByIdDesc(findRegion);
        List<Record<T>> result = new ArrayList<>();

        records.forEach(r -> {
            result.add(new Record<>(getter.apply(r), r.getCreatedDate()));
        });

        return result;
    }

    private Region getRegion (Long id) {
        return regionRepository.findById(id).orElseThrow();
    }
}
