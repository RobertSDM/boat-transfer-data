package mai_ocean.robot_data_transfer.rest;

import mai_ocean.robot_data_transfer.model.Robot;
import mai_ocean.robot_data_transfer.repository.RobotRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/robot")
@RestController
public class RobotRest {
    @Autowired
    private RobotRep robotRep;

    @GetMapping(value = "/find/all")
    private ResponseEntity<List<Robot>> findAll(){
        List<Robot> robots = robotRep.findAll();
        if(robots.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(robots);
    }

    @GetMapping(value = "/find/{id}")
    private ResponseEntity<Robot> findById(@PathVariable String id){
        Optional<Robot> robot = robotRep.findById(id);
        if(robot.isPresent()){
            return ResponseEntity.ok(robot.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/find/model/{model}")
    private ResponseEntity<List<Robot>> findByModel(@PathVariable String model){
        List<Robot> robots = robotRep.findByModel(model);
        if(robots.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(robots);
    }

    @GetMapping(value = "/find/location")
    private ResponseEntity<List<Robot>> findByLocation(@RequestParam Double latitudeStart,
                                                       @RequestParam Double latitudeEnd,
                                                       @RequestParam Double longitudeStart,
                                                       @RequestParam Double longitudeEnd){
        List<Robot> robots = robotRep.findByLatitudeBetweenAndLongitudeBetween(latitudeStart,
                latitudeEnd, longitudeStart, longitudeEnd);
        if(robots.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(robots);
    }
}
