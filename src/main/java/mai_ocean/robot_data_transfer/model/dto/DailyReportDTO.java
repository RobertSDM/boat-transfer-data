package mai_ocean.robot_data_transfer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DailyReportDTO extends  RepresentationModel<DailyReportDTO>{
        private LocalDate dtDay;
        private String status;
        private Integer bateryCapacity;
        private Double longitude;
        private Double latitude;
        private Integer avgTemp;

}
