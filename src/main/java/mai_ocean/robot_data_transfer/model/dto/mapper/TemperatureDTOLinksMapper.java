package mai_ocean.robot_data_transfer.model.dto.mapper;

import mai_ocean.robot_data_transfer.model.Temperature;
import mai_ocean.robot_data_transfer.model.dto.TemperatureDTO;
import mai_ocean.robot_data_transfer.rest.TemperatureRest;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TemperatureDTOLinksMapper implements Function<Temperature, TemperatureDTO> {


    @Override
    public TemperatureDTO apply(Temperature temp) {

        TemperatureDTO tempDTO = new TemperatureDTO(temp.getTemperature(), temp.getDate(),temp.getTime(), temp.getDepth(), temp.getLongitude(), temp.getLatitude());

        tempDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TemperatureRest.class).findAll()).withRel("Find all temperatures"));
        tempDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TemperatureRest.class).findById(temp.getId())).withRel("Find temperature by id"));
        tempDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TemperatureRest.class).findByLocation(temp.getLatitude(), temp.getLatitude(), temp.getLongitude(), temp.getLongitude())).withRel("Find temperature by id"));
        tempDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TemperatureRest.class).create(null)).withRel("Create a temperature"));
        tempDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TemperatureRest.class).update(temp.getId(), null)).withRel("Update a temperature"));
        tempDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TemperatureRest.class).delete(temp.getId())).withRel("Delete a temperature"));


        return tempDTO;
    }
}
