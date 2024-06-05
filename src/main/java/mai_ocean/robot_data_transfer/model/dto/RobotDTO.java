package mai_ocean.robot_data_transfer.model.dto;

import mai_ocean.robot_data_transfer.model.Image;
import mai_ocean.robot_data_transfer.model.Temperature;
import mai_ocean.robot_data_transfer.model.enums.RobotStatus;

import java.util.List;

public record RobotDTO(
    RobotStatus status,
    Double longitude,
    Double latitude,
    Integer bateryCapacity,
    String model,
    List<ImageDTO> images,
    List<Temperature> temperatures
 ){}
