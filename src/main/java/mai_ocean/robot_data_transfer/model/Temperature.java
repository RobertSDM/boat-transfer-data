package mai_ocean.robot_data_transfer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_maiocean_temperature")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Temperature {
    @Id
    @Column(name = "vl_temperature")
    @NotNull(message = "The vl_temperature can't be null")
    private Integer temperature;

    @Column(name = "dt_time")
    private LocalDateTime time;

    @Column(name = "ds_depth")
    @NotNull(message = "The ds_depth can't be null")
    private Integer depth;

    @Column(name = "ds_longitude")
    @NotNull(message = "The ds_longitude column can't be null")
    private double longitude;

    @Column(name = "ds_latitude")
    @NotNull(message = "The ds_latitude column can't be null")
    private double latitude;

}
