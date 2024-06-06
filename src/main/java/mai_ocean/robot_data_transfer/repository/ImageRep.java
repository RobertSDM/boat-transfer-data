package mai_ocean.robot_data_transfer.repository;

import mai_ocean.robot_data_transfer.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ImageRep extends JpaRepository<Image, String> {
    List<Image> findByDateBetween(LocalDate start, LocalDate end);
}
