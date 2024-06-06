package mai_ocean.robot_data_transfer.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ImageDTO extends RepresentationModel<ImageDTO> {
    private LocalDateTime time;
    private Integer depth;
    private Double latitude;
    private Double longitude;
}
