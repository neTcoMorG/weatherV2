package jun.weather.domain.dto;

import lombok.Getter;

@Getter
public class RegionData {

    public RegionData (Long id, String name, Integer humidity, Double temp, Double wind, String status) {
        this.id = id;
        this.humidity = humidity;
        this.temp = temp;
        this.wind = wind;
        this.status = status.contains("비") ? "비" : "";
        this.name = name;
    }

    private final Long id;
    private final String name;
    private final Integer humidity;
    private final Double temp;
    private final Double wind;
    private final String status;
}
