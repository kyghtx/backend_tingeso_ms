package tingeso.ev2.repairs_vehicle.ms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tingeso.ev2.repairs_vehicle.ms.service.RepairVehicleService;

@RestController
@RequestMapping("/api/repairs_vehicles")
@CrossOrigin("*")
public class RepairVehicleController {
    @Autowired
    private RepairVehicleService repairVehicleService;
    @GetMapping("/{vehicle_id}")
    ResponseEntity<?> getAllRepairsOfAVehicle(@PathVariable("vehicle_id") Long vehicle_id){
        return ResponseEntity.ok(repairVehicleService.getAllRepairsOfAVehicle(vehicle_id));
    }
    /*method to get vehicles, and repair types from this ms*/
    @GetMapping("/vehicles")
    ResponseEntity<?> getVehiclesFeign(){
        return ResponseEntity.ok(repairVehicleService.getVehiclesForRepairVehiclesMS());
    }
    @GetMapping("/repair_types")
    ResponseEntity<?> getRepairTypesFeign(){
        return ResponseEntity.ok(repairVehicleService.getRepairTypes());
    }


}
