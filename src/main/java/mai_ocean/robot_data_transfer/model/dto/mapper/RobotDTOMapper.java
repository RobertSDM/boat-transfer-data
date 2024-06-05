package mai_ocean.robot_data_transfer.model.dto.mapper;

import mai_ocean.robot_data_transfer.model.Robot;
import mai_ocean.robot_data_transfer.model.dto.RobotDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RobotDTOMapper implements Function<Robot, RobotDTO> {
    private final ImageDTOMapper imageDTOMapper;

    public RobotDTOMapper(ImageDTOMapper imageDTOMapper) {
        this.imageDTOMapper = imageDTOMapper;
    }

    @Override
    public RobotDTO apply(Robot robot) {
        return new RobotDTO(
                robot.getStatus(),
                robot.getLongitude(),
                robot.getLatitude(),
                robot.getBateryCapacity(),
                robot.getModel(),
                robot.getImages().stream().map(imageDTOMapper).collect(Collectors.toList()),
                robot.getTemperatures());
    }
}
