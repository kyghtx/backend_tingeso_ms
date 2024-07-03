package tingeso.ev2.reports_uh.ms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.ev2.reports_uh.ms.services.R1Service;

import java.time.Month;
import java.time.Year;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {
    @Autowired
    R1Service r1Service;
    @GetMapping("/report_1/repairs")
    ResponseEntity<?> getAllRepairsForReport() {
        return ResponseEntity.ok(r1Service.getAllRepair());
    }
    @GetMapping("/report_1")
    ResponseEntity<?> getReport1(@RequestParam int month, @RequestParam int year) {
        return ResponseEntity.ok(r1Service.buildEntitiesR1(month,year));
    }
    @GetMapping("/report_1/repair_types")
    ResponseEntity<?> getAllRepairTypes() {
        return ResponseEntity.ok(r1Service.getAllRepairTypes());
    }
}
