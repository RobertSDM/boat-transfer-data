package mai_ocean.robot_data_transfer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class TemperatureDTO extends RepresentationModel<ImageDTO> {
    private Integer temperature;
    private LocalDate date;
    private LocalTime time;
    private Integer depth;
    private Double longitude;
    private Double latitude;
}
