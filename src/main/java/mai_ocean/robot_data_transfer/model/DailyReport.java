package mai_ocean.robot_data_transfer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import mai_ocean.robot_data_transfer.repository.TemperatureRep;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tb_maiocean_daily_report")
@Data
@NoArgsConstructor
public class DailyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_daily_report")
    private String id;

    @Column(name = "ds_batery_capacity")
    @NotNull(message = "The ds_batery_capacity column can't be null")
    private Integer bateryCapacity;

    @Column(name = "dt_day")
    private LocalDate dtDay;

    @Column(name = "ds_status")
    @NotBlank(message = "The ds_status column can't be null or empty")
    private String status;

    @Column(name = "ds_latitude")
    @NotNull(message = "The ds_latitude column can't be null")
    private Double latitude;

    @Column(name = "ds_longitude")
    @NotNull(message = "The ds_longitude column can't be null")
    private Double longitude;

    @Column(name = "ds_avg_temp")
    private Integer avgTemp;

    @ManyToOne
    @JoinColumn(name = "id_dailyReport_fk")
    @JsonIgnoreProperties(value = {"dailyReports"})
    private Robot robot;

    public Integer getDailyAvarageTemp(TemperatureRep tempRep, LocalDate date){
        List<Temperature> temperatures = tempRep.findAllByDateBetween(date, date);
        Integer total = 0;
        for (Temperature temperature : temperatures) {
            total += temperature.getTemperature();
        }
        return total > 0 ? total / temperatures.size() : 0;
    }

}
