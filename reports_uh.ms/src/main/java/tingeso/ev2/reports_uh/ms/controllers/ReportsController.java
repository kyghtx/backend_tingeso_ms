package tingeso.ev2.reports_uh.ms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tingeso.ev2.reports_uh.ms.services.R1Service;

@RestController
@RequestMapping("api/reports")
public class ReportsController {
    @Autowired
    R1Service r1Service;
    @GetMapping("/1")
    ResponseEntity<?> getAllRepairsForReport(@PathVariable Long report_id) {
        return ResponseEntity.ok(r1Service.getAllRepair());
    }
}
