package mai_ocean.robot_data_transfer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_maiocen_temperature")
@Data
@NoArgsConstructor
public class Temperature {
    @Id
    @Column(name = "vl_temperature")
    @NotBlank(message = "The vl_temperature can't be null or empty")
    private Integer temperature;

    @Column(name = "dt_time")
    private LocalDateTime time;

    @Column(name = "ds_depth")
    @NotBlank(message = "The ds_depth can't be null or empty")
    private Integer depth;

    @Column(name = "ds_longitude")
    @NotBlank(message = "The ds_longitude column can't be null or empty")
    private double longitude;

    @Column(name = "ds_latitude")
    @NotBlank(message = "The ds_latitude column can't be null or empty")
    private double latitude;

    @ManyToMany(mappedBy = "temperatures")
    @JsonIgnoreProperties("temperatures")
    private List<Robot> robots;

    public Temperature(LocalDateTime time, Integer depth, Integer temperature) {
        this.time = LocalDateTime.now();
        this.depth = depth;
        this.temperature = temperature;
    }
}
