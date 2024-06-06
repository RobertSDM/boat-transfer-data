package mai_ocean.robot_data_transfer.model.dto.mapper;

import mai_ocean.robot_data_transfer.model.Image;
import mai_ocean.robot_data_transfer.model.dto.ImageDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ImageDTOMapper implements Function<Image, ImageDTO> {

    @Override
    public ImageDTO apply(Image image) {
        return new ImageDTO(
                image.getTime(),
                image.getDate(),
                image.getDepth(),
                image.getLatitude(),
                image.getLongitude()
        );
    }
}
