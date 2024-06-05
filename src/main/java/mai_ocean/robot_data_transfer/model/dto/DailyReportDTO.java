package mai_ocean.robot_data_transfer.model.dto;

import java.time.LocalDate;

public record DailyReportDTO(
        LocalDate dtDay,
        String status,
        Integer bateryCapacity,
        Double longitude,
        Double latitude,
        Integer avgTemp
) {
}
