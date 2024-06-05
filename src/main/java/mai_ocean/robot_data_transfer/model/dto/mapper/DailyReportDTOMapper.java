package mai_ocean.robot_data_transfer.model.dto.mapper;

import mai_ocean.robot_data_transfer.model.DailyReport;
import mai_ocean.robot_data_transfer.model.dto.DailyReportDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DailyReportDTOMapper implements Function<DailyReport, DailyReportDTO> {
    @Override
    public DailyReportDTO apply(DailyReport dailyReport) {
        return new DailyReportDTO(
                dailyReport.getDtDay(),
                dailyReport.getStatus(),
                dailyReport.getBateryCapacity(),
                dailyReport.getLongitude(),
                dailyReport.getLatitude(),
                dailyReport.getAvgTemp()
        );
    }
}
