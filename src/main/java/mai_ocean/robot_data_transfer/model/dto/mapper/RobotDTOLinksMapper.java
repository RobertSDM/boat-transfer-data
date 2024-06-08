package mai_ocean.robot_data_transfer.model.dto.mapper;

import mai_ocean.robot_data_transfer.model.Robot;
import mai_ocean.robot_data_transfer.model.dto.ImageDTO;
import mai_ocean.robot_data_transfer.model.dto.RobotDTO;
import mai_ocean.robot_data_transfer.model.dto.TemperatureDTO;
import mai_ocean.robot_data_transfer.rest.RobotRest;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class RobotDTOLinksMapper implements Function<Robot, RobotDTO> {
    private final ImageDTOLinksMapper imageDTOLinksMapper;
    private final TemperatureDTOLinksMapper temperatureDTOLinksMapper;
    public RobotDTOLinksMapper(ImageDTOLinksMapper imageDTOLinksMapper, TemperatureDTOLinksMapper temperatureDTOLinksMapper) {
        this.temperatureDTOLinksMapper = temperatureDTOLinksMapper;
        this.imageDTOLinksMapper = imageDTOLinksMapper;
    }

    @Override
    public RobotDTO apply(Robot robot) {

        List<ImageDTO> imageDTO = robot.getImages().stream().map(this.imageDTOLinksMapper).toList();
        List<TemperatureDTO> tempDTO = robot.getTemperatures().stream().map(this.temperatureDTOLinksMapper).toList();

        RobotDTO robotDTO = new RobotDTO(robot.getStatus(), robot.getLongitude(), robot.getLatitude(), robot.getBateryCapacity(), robot.getModel(), imageDTO, tempDTO);

        robotDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RobotRest.class).findAll()).withRel("Find all robots"));
        robotDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RobotRest.class).findById(robot.getId())).withRel("Find a robot by id"));
        robotDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RobotRest.class).findByLocation(robot.getLatitude(), robot.getLatitude(), robot.getLongitude(), robot.getLongitude())).withRel("Find all robots between latitude and longitude"));
        robotDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RobotRest.class).findByModel(robot.getModel())).withRel("Find all robots"));


        return robotDTO;
    }
}
