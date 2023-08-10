package jun.weather.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WeatherInformation {
    private final String name;
    private final List<Record<Integer>> humidity;       // 습도
    private final List<Record<Double>> temp;            // 온도
    private final List<Record<Double>> wind;            // 풍속
}
