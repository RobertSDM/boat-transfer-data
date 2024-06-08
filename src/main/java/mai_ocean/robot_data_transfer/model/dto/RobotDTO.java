package mai_ocean.robot_data_transfer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import mai_ocean.robot_data_transfer.model.enums.RobotStatus;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
public class RobotDTO extends RepresentationModel<RobotDTO> {
    private RobotStatus status;
    private Double longitude;
    private Double latitude;
    private Integer bateryCapacity;
    private String model;
    private List<ImageDTO> images;
    private List<TemperatureDTO> temperatures;
}
