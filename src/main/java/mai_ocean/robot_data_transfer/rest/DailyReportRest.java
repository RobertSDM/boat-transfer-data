package mai_ocean.robot_data_transfer.rest;

import jakarta.validation.Valid;
import mai_ocean.robot_data_transfer.model.DailyReport;
import mai_ocean.robot_data_transfer.model.Temperature;
import mai_ocean.robot_data_transfer.model.dto.DailyReportDTO;
import mai_ocean.robot_data_transfer.model.dto.mapper.DailyReportDTOLinksMapper;
import mai_ocean.robot_data_transfer.model.dto.mapper.DailyReportDTOMapper;
import mai_ocean.robot_data_transfer.repository.DailyReportRep;
import mai_ocean.robot_data_transfer.repository.TemperatureRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/dailyReport")
@RestController
public class DailyReportRest {
    @Autowired
    private DailyReportRep dailyRep;
    @Autowired
    private TemperatureRep tempRep;
    private final DailyReportDTOLinksMapper ailyReportDTOLinksMapper;
    @Autowired
    private DailyReportDTOLinksMapper dailyReportDTOLinksMapper;

    public DailyReportRest(DailyReportDTOLinksMapper dailyReportDTOLinksMapper) {
        this.ailyReportDTOLinksMapper = dailyReportDTOLinksMapper;
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<DailyReportDTO>> findAll() {
        List<DailyReport> dailyReports = dailyRep.findAll();
        if(dailyReports.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dailyReports.stream().map(dailyReportDTOLinksMapper).toList());
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<DailyReportDTO> findById(@PathVariable String id) {
        Optional<DailyReport> dailyReport = dailyRep.findById(id);
        if(dailyReport.isPresent()) {
            return ResponseEntity.ok(dailyReportDTOLinksMapper.apply(dailyReport.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public  ResponseEntity<DailyReportDTO> create(@Valid @RequestBody DailyReport dailyReport) {
        try{
            dailyReport.setDtDay(LocalDate.now());
            dailyReport.setAvgTemp(dailyReport.getDailyAvarageTemp(tempRep, dailyReport.getDtDay()));
            dailyRep.save(dailyReport);
            return ResponseEntity.ok(dailyReportDTOLinksMapper.apply(dailyReport));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public  ResponseEntity<DailyReportDTO> delete(@PathVariable String id) {
        try{
            dailyRep.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
