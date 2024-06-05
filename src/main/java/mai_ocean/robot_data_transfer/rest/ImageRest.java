package mai_ocean.robot_data_transfer.rest;

import jakarta.validation.Valid;
import mai_ocean.robot_data_transfer.model.Image;
import mai_ocean.robot_data_transfer.repository.ImageRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequestMapping("/image")
@RestController
public class ImageRest {

    @Autowired
    private ImageRep imageRep;

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Image>> findAll() {
        List<Image> images = imageRep.findAll();
        if(images.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(images);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Image> findById(@PathVariable String id) {
        Optional<Image> image = imageRep.findById(id);
        if(image.isPresent()){
            return ResponseEntity.ok(image.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/find/time")
    public ResponseEntity<List<Image>> findTime(@RequestParam LocalDate start, @RequestParam LocalDate end){
        List<Image> images = imageRep.findByTimeBetween(start, end);
        if(images.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(images);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Image> create(@Valid @RequestBody Image image){
        try{
            imageRep.save(image);
            return ResponseEntity.ok(image);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Image> update(@Valid @RequestBody Image imagep, @RequestParam String id){
        Optional<Image> image = imageRep.findById(id);
        if(image.isPresent()){
            image.get().setImageContent(imagep.getImageContent());
            image.get().setLongitude(imagep.getLongitude());
            image.get().setLatitude(imagep.getLatitude());
            image.get().setDepth(imagep.getDepth());

            imageRep.save(image.get());
            return ResponseEntity.ok(image.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        try{
            imageRep.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
