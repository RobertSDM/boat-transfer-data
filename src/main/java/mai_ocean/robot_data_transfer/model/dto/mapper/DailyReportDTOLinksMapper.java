package mai_ocean.robot_data_transfer.model.dto.mapper;

import mai_ocean.robot_data_transfer.model.DailyReport;
import mai_ocean.robot_data_transfer.model.dto.DailyReportDTO;
import mai_ocean.robot_data_transfer.rest.DailyReportRest;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DailyReportDTOLinksMapper implements Function<DailyReport, DailyReportDTO> {
    @Override
    public DailyReportDTO apply(DailyReport dr) {
        DailyReportDTO dailyReport = new DailyReportDTO(dr.getDtDay(), dr.getStatus(), dr.getBateryCapacity(), dr.getLongitude(), dr.getLatitude(), dr.getAvgTemp());

        dailyReport.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DailyReportRest.class).findAll()).withRel("Find all Daily Reports"));
        dailyReport.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DailyReportRest.class).findById(dr.getId())).withRel("Find avDaily Report by id"));
        dailyReport.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DailyReportRest.class).create(null)).withRel("Create a Daily Reports"));
        dailyReport.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DailyReportRest.class).delete(dr.getId())).withRel("Delete a Daily Reports"));

        return dailyReport;
    }
}
