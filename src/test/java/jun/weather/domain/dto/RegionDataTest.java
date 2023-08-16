package jun.weather.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegionDataTest {

    @Test
    void containsTest () {
        RegionData data1 = new RegionData(1L, "s", 0, (double) 0L, (double) 0L, "비");
        RegionData data2 = new RegionData(1L, "s", 0, (double) 0L, (double) 0L, "살짝비");

        assertEquals(data1.getStatus(), "비");
        assertEquals(data2.getStatus(), "비");
    }
}