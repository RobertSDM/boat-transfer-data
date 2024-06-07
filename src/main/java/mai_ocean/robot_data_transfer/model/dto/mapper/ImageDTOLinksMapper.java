package mai_ocean.robot_data_transfer.model.dto.mapper;

import mai_ocean.robot_data_transfer.model.Image;
import mai_ocean.robot_data_transfer.model.dto.ImageDTO;
import mai_ocean.robot_data_transfer.rest.ImageRest;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class ImageDTOLinksMapper implements Function<Image, ImageDTO> {

    @Override
    public ImageDTO apply(Image img) {

        ImageDTO imgDTO = new ImageDTO(img.getTime(),img.getDate(), img.getDepth(), img.getLatitude(), img.getLongitude());

        imgDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageRest.class).findAll()).withRel("Find all images"));
        imgDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageRest.class).findById(img.getId())).withRel("Find image by id"));
        imgDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageRest.class).findTime(img.getDate(), img.getDate())).withRel("Find all images between a date period"));
        imgDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageRest.class).create(null)).withRel("Create a image"));
        imgDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageRest.class).update(null, img.getId())).withRel("Update a image"));
        imgDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageRest.class).delete(img.getId())).withRel("Delete a image"));

        return imgDTO;
    }
}
