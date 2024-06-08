package mai_ocean.robot_data_transfer.rest;

import mai_ocean.robot_data_transfer.model.Robot;
import mai_ocean.robot_data_transfer.model.dto.RobotDTO;
import mai_ocean.robot_data_transfer.model.dto.mapper.RobotDTOLinksMapper;
import mai_ocean.robot_data_transfer.model.dto.mapper.RobotDTOMapper;
import mai_ocean.robot_data_transfer.repository.RobotRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/robot")
@RestController
public class RobotRest {
    @Autowired
    private RobotRep robotRep;
    private final RobotDTOMapper RobotDTOMapper;
    private final RobotDTOLinksMapper robotDTOLinksMapper;

    public RobotRest(RobotDTOMapper RobotDTOMapper, RobotDTOLinksMapper robotDTOLinksMapper) {
        this.RobotDTOMapper = RobotDTOMapper;
        this.robotDTOLinksMapper = robotDTOLinksMapper;
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<RobotDTO>> findAll(){
        List<RobotDTO> robots = robotRep.findAll()
                .stream().map(this.robotDTOLinksMapper).collect(Collectors.toList());
        if(robots.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(robots);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<RobotDTO> findById(@PathVariable String id){
        Optional<Robot> OptRobot = robotRep.findById(id);
        if(OptRobot.isPresent()){
            RobotDTO robot = this.robotDTOLinksMapper.apply(OptRobot.get());
            return ResponseEntity.ok(robot);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/find/model/{model}")
    public ResponseEntity<List<RobotDTO>> findByModel(@PathVariable String model){
        List<Robot> robots = robotRep.findByModel(model);
        if(robots.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(robots.stream().map(this.robotDTOLinksMapper).toList());
    }

    @GetMapping(value = "/find/location")
    public ResponseEntity<List<RobotDTO>> findByLocation(@RequestParam Double latitudeStart,
                                                          @RequestParam Double latitudeEnd,
                                                          @RequestParam Double longitudeStart,
                                                          @RequestParam Double longitudeEnd){
        List<Robot> robots = robotRep.findByLatitudeBetweenAndLongitudeBetween(latitudeStart,
                latitudeEnd, longitudeStart, longitudeEnd);
        if(robots.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(robots.stream().map(this.robotDTOLinksMapper).toList());
    }
}
