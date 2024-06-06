package mai_ocean.robot_data_transfer.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class ImageDTO extends RepresentationModel<ImageDTO> {
    private LocalTime time;
    private LocalDate date;
    private Integer depth;
    private Double latitude;
    private Double longitude;
}
