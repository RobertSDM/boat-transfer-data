package mai_ocean.robot_data_transfer.model.dto;

import java.time.LocalDateTime;

public record ImageDTO(
        LocalDateTime time,
        Integer depth,
        Double latitude,
        Double longitude
){}
