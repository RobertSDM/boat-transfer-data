package mai_ocean.robot_data_transfer.repository;

import mai_ocean.robot_data_transfer.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureRep extends JpaRepository<Temperature, String> {
    List<Temperature> findByLatitudeBetweenAndLongitudeBetween(Double latitudeStart, Double latitudeEnd, Double longitudeStart, Double longitudeEnd);
    List<Temperature> findAllByTimeBetween(LocalDateTime timeStart, LocalDateTime timeEnd);

    @Query("select temp.time from Temperature as temp where temp.time between :timeStart and :timeEnd")
    List<Temperature> findAllByTimeBetweenOnlyDate(LocalDateTime timeStart, LocalDateTime timeEnd);
}
