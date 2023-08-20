package jun.weather.domain.repository;

import jun.weather.domain.dto.RegionData;
import jun.weather.domain.entity.Region;
import jun.weather.domain.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    /*
        지역별 최근 날씨 정보 Query
        SELECT * FROM weather w WHERE w.id IN (SELECT MAX(id) FROM weather GROUP BY region_id);
     */
    @Query(value = "SELECT NEW jun.weather.domain.dto.RegionData(w.region.id, w.region.name, w.humidity, w.temp, w.wind_speed, w.status) " +
                    "FROM Weather w " +
                    "WHERE (w.region.id, w.id) IN (SELECT w.region.id, MAX(id) FROM Weather w GROUP BY w.region.id)")
    List<RegionData> findRecentRegionGroupBy ();

    List<Weather> findTop1000ByRegionOrderByIdDesc(Region region);
}
