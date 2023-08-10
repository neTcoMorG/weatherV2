package jun.weather.domain.repository;

import jun.weather.domain.entity.Region;
import jun.weather.domain.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

// 사용 O
//    @Query("SELECT temp AS value, created_date AS recordTime " +
//                "FROM Weather w WHERE w.region_id = :id " +
//                "ORDER BY w.id DESC " +
//                "LIMIT :limit")
//    List<Record<Double>> getTempRecords (Long id, Integer limit);

    // 지역별 최근 날씨 정보 Query
    //SELECT * FROM weather w WHERE w.id IN (SELECT MAX(id) FROM weather GROUP BY region_id);

    List<Weather> findTop5ByRegionOrderById (Region region);
}
