package jun.weather.domain.dto;

import jun.weather.domain.weather.util.TimeUtil;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class Record<T> {

    public Record(T value, LocalDateTime recordTime) {
        this.value = value;
        this.recordTime = TimeUtil.txtDate(Date.from(recordTime.atZone(ZoneId.systemDefault()).toInstant()));
    }

    private final T value;
    private final String recordTime;

}
