package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.RepairVehicleRepository;
import service.RepairVehicleService;

@RestController
@RequestMapping("/api/repairs_vehicles")
public class RepairVehicleController {
    @Autowired
    private RepairVehicleService repairVehicleService;
    @GetMapping("/{vehicle_id}")
    ResponseEntity<?> getAllRepairsOfAVehicle(@PathVariable("vehicle_id") Long vehicle_id){
        return ResponseEntity.ok(repairVehicleService.getAllRepairsOfAVehicle(vehicle_id));
    }

}
