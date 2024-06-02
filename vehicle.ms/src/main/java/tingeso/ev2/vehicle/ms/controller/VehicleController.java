package tingeso.ev2.vehicle.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.ev2.vehicle.ms.entity.VehicleEntity;
import tingeso.ev2.vehicle.ms.service.VehicleService;

@RestController
@RequestMapping("/api")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

   // @GetMapping("/vehicles")
    //ResponseEntity<?> getAllVehicles() {
     //   return ResponseEntity.ok(vehicleService.get_all_vehicles());
    //}

    @GetMapping("/vehicles/typeº{type}")
    ResponseEntity<?> getVehiclesByType(@PathVariable("type") Long type) {
        return ResponseEntity.ok(vehicleService.get_all_vehicles_by_type(type));
    }

    @GetMapping("/vehicles/{patent}")
    ResponseEntity<?> getVehicleByPatent(@PathVariable("patent") String patent){
        return ResponseEntity.ok(vehicleService.find_by_patent(patent));
    }

    //CREATE
    @PostMapping("/vehicles")
    ResponseEntity<?> saveVehicle(@RequestBody VehicleEntity newVehicle){
        return ResponseEntity.ok(vehicleService.create(newVehicle));
    }
    //DELETE ALL
    @DeleteMapping("/vehicles")
    void deleteAllVehicles(){
        vehicleService.DeleteAllVehicles();

    }
    //Get Custom Data from vehicle
    @GetMapping("/vehicles")
    ResponseEntity<?> getAllCustomVehicles(){
        return ResponseEntity.ok(vehicleService.getCustomVehicles());
    }


}
