package mai_ocean.robot_data_transfer.rest;

import jakarta.validation.Valid;
import mai_ocean.robot_data_transfer.model.Image;
import mai_ocean.robot_data_transfer.model.dto.ImageDTO;
import mai_ocean.robot_data_transfer.model.dto.mapper.ImageDTOMapper;
import mai_ocean.robot_data_transfer.repository.ImageRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/image")
@RestController
public class ImageRest {

    @Autowired
    private ImageRep imageRep;
    private final ImageDTOMapper imageDTOMapper;

    public ImageRest(ImageDTOMapper imageDTOMapper) {
        this.imageDTOMapper = imageDTOMapper;
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<ImageDTO>> findAll() {
        List<Image> images = imageRep.findAll();
        if(images.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<ImageDTO> imagesdto = images.stream().map(img -> {
            ImageDTO imgDTO = new ImageDTO(img.getTime(), img.getDepth(), img.getLatitude(), img.getLongitude());

            imgDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageRest.class).findById(img.getId())).withRel("Find image by id"));
            imgDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageRest.class).findTime(img.getTime().toLocalDate(), img.getTime().toLocalDate())).withRel("Find all images between a date"));
            imgDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageRest.class).create(null)).withRel("Create a image"));
            imgDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageRest.class).update(null, null)).withRel("Update a image"));
            imgDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageRest.class).delete(null)).withRel("Delete a image"));

            return imgDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(imagesdto);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<ImageDTO> findById(@PathVariable String id) {
        Optional<Image> image = imageRep.findById(id);
        if(image.isPresent()){
            return ResponseEntity.ok(imageDTOMapper.apply(image.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/find/time")
    public ResponseEntity<List<ImageDTO>> findTime(@RequestParam LocalDate start, @RequestParam LocalDate end){
        List<ImageDTO> images = imageRep.findByTimeBetween(start, end);
        if(images.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(images);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Image> create(@Valid @RequestBody Image image){
        try{
            image.setTime(LocalDateTime.now());
            imageRep.save(image);
            return ResponseEntity.ok(image);
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
            return ResponseEntity.ok(imageDTOMapper.apply(image.get()));
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
