package tingeso.ev2.vehicle.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.ev2.vehicle.ms.entity.Vehicle_MotorTypeEntity;
import tingeso.ev2.vehicle.ms.entity.Vehicle_TypeEntity;
import tingeso.ev2.vehicle.ms.service.Vehicle_MotorTypeService;

@RestController
@RequestMapping("api/vehicles")
public class Vehicle_MotorTypeController {

    @Autowired
    Vehicle_MotorTypeService vehicle_MotorTypeService;
    @GetMapping("/motor_types")
    ResponseEntity<?> getAllMotorTypes() {
        return ResponseEntity.ok(vehicle_MotorTypeService.getAllMotorTypes());
    }
    @GetMapping("/motor_types/{id}")
    ResponseEntity<?> getMotorTypeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(vehicle_MotorTypeService.getMotorTypeById(id));
    }

    //CREATE

    @PostMapping("/motor_types")
    ResponseEntity<?> saveMotorType(@RequestBody Vehicle_MotorTypeEntity newMotorType){
        return ResponseEntity.ok(vehicle_MotorTypeService.create(newMotorType));
    }
}
