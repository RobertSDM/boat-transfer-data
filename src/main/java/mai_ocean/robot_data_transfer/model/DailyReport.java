package mai_ocean.robot_data_transfer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
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
    @NotBlank(message = "The ds_batery_capacity column can't be null or empty")
    private Integer bateryCapacity;

    @Column(name = "dt_day")
    @NotBlank(message = "The dt_day column can't be null or empty")
    private LocalDate dtDay;

    @Column(name = "ds_status")
    @NotBlank(message = "The ds_status column can't be null or empty")
    private String status;

    @Column(name = "ds_latitude")
    @NotBlank(message = "The ds_latitude column can't be null or empty")
    private Double latitude;

    @Column(name = "ds_longitude")
    @NotBlank(message = "The ds_longitude column can't be null or empty")
    private Double longitude;

    @Column(name = "ds_avg_temp")
    @NotBlank(message = "The ds_avg_temp column can't be null or empty")
    private Integer avgTemp;

    public DailyReport(Double longitude, Double latitude, String status, LocalDate dtDay, Integer bateryCapacity, TemperatureRep tempRep) {
        this.avgTemp = getDailyAvarageTemp(tempRep, dtDay);
        this.longitude = longitude;
        this.latitude = latitude;
        this.status = status;
        this.dtDay = dtDay;
        this.bateryCapacity = bateryCapacity;
    }

    private Integer getDailyAvarageTemp(TemperatureRep tempRep, LocalDate date){
        List<Temperature> temperatures = tempRep.findAllByTimeBetweenOnlyDate(LocalDateTime.of(date, LocalTime.of(0, 0, 0)), LocalDateTime.of(date, LocalTime.of(23, 59, 59)));
        Integer total = 0;
        for (Temperature temperature : temperatures) {
            total += temperature.getTemperature();
        }
        return temperatures.size() / total;
    }

}
