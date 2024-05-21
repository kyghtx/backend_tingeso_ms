package tingeso.ev2.vehicle.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.ev2.vehicle.ms.entity.VehicleEntity;
import tingeso.ev2.vehicle.ms.service.VehicleService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @GetMapping("/vehicles")
    ResponseEntity<?> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.get_all_vehicles());
    }

    @PostMapping("/vehicles")
    ResponseEntity<?> saveVehicle(VehicleEntity newVehicle){
        return ResponseEntity.ok(vehicleService.create(newVehicle));
    }
}
