package mai_ocean.robot_data_transfer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mai_ocean.robot_data_transfer.model.enums.RobotStatus;

import java.util.List;

@Entity
@Table(name = "tb_maiocean_robot")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Robot {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_robot")
    private String id;

    @Column(name = "ds_status")
    @NotBlank(message = "The ds_status column can't be null or empty")
    private RobotStatus status;

    @Column(name = "ds_longitude")
    @NotBlank(message = "The ds_longitude column can't be null or empty")
    private Double longitude;

    @Column(name = "ds_latitude")
    @NotBlank(message = "The ds_latitude column can't be null or empty")
    private Double latitude;

    @Column(name = "ds_model")
    @NotBlank(message = "The ds_model column can't be null or empty")
    private String model;

    @Column(name = "ds_batery_capacity")
    @NotBlank(message = "The ds_model column can't be null or empty")
    private Integer bateryCapacity;

    @OneToMany(mappedBy = "robot")
    @JsonIgnoreProperties(value = "robot")
    private List<Image> images;

    @OneToMany(mappedBy = "robot")
    @JsonIgnoreProperties(value = "robot")
    private List<Temperature> temperatures;

    @OneToMany(mappedBy = "robot")
    @JsonIgnoreProperties(value = "robot")
    private List<DailyReport> dailyReports;

}
