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

   @GetMapping("/vehicles")
    ResponseEntity<?> getAllVehicles() {
       return ResponseEntity.ok(vehicleService.get_all_vehicles());
    }
    @GetMapping("/vehicles/{vehicle_id}")
    ResponseEntity<?> getVehicleById(@PathVariable("vehicle_id") Long vehicle_id) {
       return ResponseEntity.ok(vehicleService.findById(vehicle_id));
    }

    @GetMapping("/vehicles/typeº{type}")
    ResponseEntity<?> getVehiclesByType(@PathVariable("type") Long type) {
        return ResponseEntity.ok(vehicleService.get_all_vehicles_by_type(type));
    }
    /*TODO: CAMBIAR A ID.*/
    @PutMapping("/vehicles/{patent}")
    ResponseEntity<?> updateKmVehicle(@PathVariable("patent") String patent, @RequestParam("KM") Long newKm){
       return ResponseEntity.ok(vehicleService.updateKmVehicle(newKm, patent));
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


}
