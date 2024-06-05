package mai_ocean.robot_data_transfer.repository;

import mai_ocean.robot_data_transfer.model.DailyReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyReportRep extends JpaRepository<DailyReport, String> {}
