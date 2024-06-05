package mai_ocean.robot_data_transfer.rest;

import jakarta.validation.Valid;
import mai_ocean.robot_data_transfer.model.DailyReport;
import mai_ocean.robot_data_transfer.repository.DailyReportRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/dailyReport")
@RestController
public class DailyReportRest {
    @Autowired
    private DailyReportRep dailyRep;

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<DailyReport>> findAll() {
        List<DailyReport> dailyReports = dailyRep.findAll();
        if(dailyReports.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dailyReports);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<DailyReport> findById(@PathVariable String id) {
        Optional<DailyReport> dailyReport = dailyRep.findById(id);
        if(dailyReport.isPresent()) {
            return ResponseEntity.ok(dailyReport.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    private ResponseEntity<DailyReport> createDailyReport(@Valid @RequestBody DailyReport dailyReport) {
        try{
            dailyRep.save(dailyReport);
            return ResponseEntity.ok(dailyReport);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
