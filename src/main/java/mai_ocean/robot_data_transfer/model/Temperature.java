package mai_ocean.robot_data_transfer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_maiocean_temperature")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Temperature {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_temperature")
    private String id;

    @Column(name = "vl_temperature")
    @NotNull(message = "The vl_temperature can't be null")
    private Integer temperature;

    @Column(name = "dt_date")
    private LocalDate date;

    @Column(name = "hr_time")
    private LocalTime time;

    @Column(name = "ds_depth")
    @NotNull(message = "The ds_depth can't be null")
    private Integer depth;

    @Column(name = "ds_longitude")
    @NotNull(message = "The ds_longitude column can't be null")
    private double longitude;

    @Column(name = "ds_latitude")
    @NotNull(message = "The ds_latitude column can't be null")
    private double latitude;

    @ManyToOne
    @JoinColumn(name = "id_temp_fk")
    @JsonIgnoreProperties(value = {"temperatures"})
    private Robot robot;

}
