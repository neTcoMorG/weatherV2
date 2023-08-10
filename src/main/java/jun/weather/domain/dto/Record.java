package jun.weather.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Record<T> {

    private final T value;
    private final LocalDateTime recordTime;

}
