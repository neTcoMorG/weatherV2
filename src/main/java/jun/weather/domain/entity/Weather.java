package jun.weather.domain.entity;

import jakarta.persistence.*;
import jun.weather.domain.weather.api.mapping.WeatherData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "weather")
public class Weather {

    public Weather (Region region, WeatherData data) {
        this.region = region;
        temp = Double.parseDouble(String.format("%.1f", data.getMain().getTemp() - 273.15));
        wind_speed = data.getWind().getSpeed();
        humidity = data.getMain().getHumidity();
        status = data.getWeather().get(0).getDescription();
        main = data.getWeather().get(0).getMain();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    private String status;
    private String main;
    private int humidity;
    private double temp;
    private double wind_speed;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
}
