package tingeso.ev2.vehicle.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.ev2.vehicle.ms.entity.Vehicle_TypeEntity;
import tingeso.ev2.vehicle.ms.service.Vehicle_TypeService;

@RestController
@RequestMapping("api/vehicles")
public class Vehicle_TypeController {
    @Autowired
    Vehicle_TypeService vehicle_TypeService;

    @GetMapping("/types")
    ResponseEntity<?> getAllVehicleTypes() {
        return ResponseEntity.ok(vehicle_TypeService.getAllVehicleTypes());
    }

    @GetMapping("/types/{id}")
    ResponseEntity<?> getVehicleTypeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(vehicle_TypeService.getVehicleTypeById(id));
    }

    //CREATE
    @PostMapping("/types")
    ResponseEntity<?> saveType(@RequestBody Vehicle_TypeEntity newType){
        return ResponseEntity.ok(vehicle_TypeService.create(newType));
    }
}
