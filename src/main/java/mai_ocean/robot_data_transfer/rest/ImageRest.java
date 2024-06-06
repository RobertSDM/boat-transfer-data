package mai_ocean.robot_data_transfer.rest;

import jakarta.validation.Valid;
import mai_ocean.robot_data_transfer.model.Image;
import mai_ocean.robot_data_transfer.model.dto.ImageDTO;
import mai_ocean.robot_data_transfer.model.dto.mapper.ImageDTOLinksMapper;
import mai_ocean.robot_data_transfer.model.dto.mapper.ImageDTOMapper;
import mai_ocean.robot_data_transfer.repository.ImageRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/image")
@RestController
public class ImageRest {

    @Autowired
    private ImageRep imageRep;
    private final ImageDTOLinksMapper imageDTOLinksMapper;

    public ImageRest(ImageDTOLinksMapper imageDTOLinksMapper) {
        this.imageDTOLinksMapper = imageDTOLinksMapper;
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<ImageDTO>> findAll() {
        List<Image> images = imageRep.findAll();

        if(images.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<ImageDTO> imagesdto = images.stream().map(imageDTOLinksMapper).collect(Collectors.toList());

        return ResponseEntity.ok(imagesdto);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<ImageDTO> findById(@PathVariable String id) {
        Optional<Image> image = imageRep.findById(id);
        if(image.isPresent()){
            return ResponseEntity.ok(imageDTOLinksMapper.apply(image.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/find/date")
    public ResponseEntity<List<ImageDTO>> findTime(@RequestParam LocalDate start, @RequestParam LocalDate end){
        System.out.println(start);
        System.out.println(end);
        List<Image> images = imageRep.findByDateBetween(start, end);
        if(images.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<ImageDTO> imagesdto = images.stream().map(imageDTOLinksMapper).collect(Collectors.toList());

        return ResponseEntity.ok(imagesdto);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ImageDTO> create(@Valid @RequestBody Image image){
        try{
            image.setDate(LocalDate.now());
            image.setTime(LocalTime.now());
            imageRep.save(image);

            return ResponseEntity.ok(imageDTOLinksMapper.apply(image));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ImageDTO> update(@Valid @RequestBody Image imagep, @RequestParam String id){
        Optional<Image> image = imageRep.findById(id);
        if(image.isPresent()){
            image.get().setImageContent(imagep.getImageContent());
            image.get().setLongitude(imagep.getLongitude());
            image.get().setLatitude(imagep.getLatitude());
            image.get().setDepth(imagep.getDepth());

            imageRep.save(image.get());

            return ResponseEntity.ok(imageDTOLinksMapper.apply(image.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        try{
            imageRep.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
