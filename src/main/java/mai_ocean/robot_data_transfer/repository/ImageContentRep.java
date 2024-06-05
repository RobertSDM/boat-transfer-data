package mai_ocean.robot_data_transfer.repository;

import mai_ocean.robot_data_transfer.model.ImageContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageContentRep extends JpaRepository<ImageContent, String> {
}
