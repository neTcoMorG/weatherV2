package jun.weather.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegionData {
    private Long id;
    private String name;
    private Integer humidity;
    private Double temp;
    private Double wind;
}
