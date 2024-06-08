package mai_ocean.robot_data_transfer.repository;

import mai_ocean.robot_data_transfer.model.Robot;
import mai_ocean.robot_data_transfer.model.dto.RobotDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RobotRep extends JpaRepository<Robot, String> {
    List<Robot> findByModel(String model);

    List<Robot> findByLatitudeBetweenAndLongitudeBetween(Double latitudeStart, Double latitudeEnd, Double longitudeStart, Double longitudeEnd);
}
