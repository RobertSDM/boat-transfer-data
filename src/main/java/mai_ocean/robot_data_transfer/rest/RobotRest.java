package mai_ocean.robot_data_transfer.rest;

import mai_ocean.robot_data_transfer.model.Robot;
import mai_ocean.robot_data_transfer.model.dto.RobotDTO;
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

    public RobotRest(RobotDTOMapper RobotDTOMapper) {
        this.RobotDTOMapper = RobotDTOMapper;
    }

    @GetMapping(value = "/find/all")
    private ResponseEntity<List<RobotDTO>> findAll(){
        List<RobotDTO> robots = robotRep.findAll()
                .stream().map(RobotDTOMapper).collect(Collectors.toList());
        if(robots.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(robots);
    }

    @GetMapping(value = "/find/{id}")
    private ResponseEntity<RobotDTO> findById(@PathVariable String id){
        Optional<Robot> OptRobot = robotRep.findById(id);
        if(OptRobot.isPresent()){
            RobotDTO robot = RobotDTOMapper.apply(OptRobot.get());
            return ResponseEntity.ok(robot);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/find/model/{model}")
    private ResponseEntity<List<RobotDTO>> findByModel(@PathVariable String model){
        List<RobotDTO> robots = robotRep.findByModel(model);
        if(robots.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(robots);
    }

    @GetMapping(value = "/find/location")
    private ResponseEntity<List<RobotDTO>> findByLocation(@RequestParam Double latitudeStart,
                                                          @RequestParam Double latitudeEnd,
                                                          @RequestParam Double longitudeStart,
                                                          @RequestParam Double longitudeEnd){
        List<RobotDTO> robots = robotRep.findByLatitudeBetweenAndLongitudeBetween(latitudeStart,
                latitudeEnd, longitudeStart, longitudeEnd);
        if(robots.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(robots);
    }
}
