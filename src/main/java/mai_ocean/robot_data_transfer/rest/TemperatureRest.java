package mai_ocean.robot_data_transfer.rest;

import jakarta.validation.Valid;
import mai_ocean.robot_data_transfer.model.Image;
import mai_ocean.robot_data_transfer.model.Robot;
import mai_ocean.robot_data_transfer.model.Temperature;
import mai_ocean.robot_data_transfer.model.dto.TemperatureDTO;
import mai_ocean.robot_data_transfer.model.dto.mapper.TemperatureDTOLinksMapper;
import mai_ocean.robot_data_transfer.repository.TemperatureRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequestMapping("/temp")
@RestController
public class TemperatureRest {
    @Autowired
    private TemperatureRep tempRep;
    private final TemperatureDTOLinksMapper tempDTOLinksMapper;

    public TemperatureRest(TemperatureDTOLinksMapper tempDTOLinksMapper) {
        this.tempDTOLinksMapper = tempDTOLinksMapper;
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<TemperatureDTO>> findAll() {
        List<Temperature> tempList = tempRep.findAll();
        if(tempList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<TemperatureDTO> tempDTO = tempList.stream().map(tempDTOLinksMapper).toList();

        return ResponseEntity.ok(tempDTO);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<TemperatureDTO> findById(@PathVariable String id) {
        Optional<Temperature> temp = tempRep.findById(id);
        if(temp.isPresent()){
            return ResponseEntity.ok(tempDTOLinksMapper.apply(temp.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/find/location")
    public ResponseEntity<List<Temperature>> findByLocation(@RequestParam Double latitudeStart,
                                                       @RequestParam Double latitudeEnd,
                                                       @RequestParam Double longitudeStart,
                                                       @RequestParam Double longitudeEnd){
        List<Temperature> temps = tempRep.findByLatitudeBetweenAndLongitudeBetween(latitudeStart,
                latitudeEnd, longitudeStart, longitudeEnd);
        if(temps.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(temps);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<TemperatureDTO> create(@RequestBody Temperature temp) {
        try{
            temp.setTime(LocalTime.now());
            temp.setDate(LocalDate.now());
            tempRep.save(temp);
            return ResponseEntity.ok(tempDTOLinksMapper.apply(temp));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<TemperatureDTO> update(@PathVariable String id, @Valid @RequestBody Temperature tempp) {
        Optional<Temperature> temp = tempRep.findById(id);
        if(temp.isPresent()){
            temp.get().setDepth(tempp.getDepth());
            temp.get().setTemperature(tempp.getTemperature());
            temp.get().setTime(tempp.getTime());
            temp.get().setLatitude(tempp.getLatitude());
            temp.get().setDepth(tempp.getDepth());

            tempRep.save(temp.get());
            return ResponseEntity.ok(tempDTOLinksMapper.apply(temp.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try{
            tempRep.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
