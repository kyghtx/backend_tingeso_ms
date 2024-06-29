package tingeso.ev2.repairs_vehicle.ms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tingeso.ev2.repairs_vehicle.ms.dto.RepairVehicleDTO;
import tingeso.ev2.repairs_vehicle.ms.entities.RepairVehicleEntity;
import tingeso.ev2.repairs_vehicle.ms.service.RepairVehicleService;

import java.util.List;

@RestController
@RequestMapping("/api/repairs_vehicles")
public class RepairVehicleController {
    @Autowired
    private RepairVehicleService repairVehicleService;
    @GetMapping("/{vehicle_id}")
    ResponseEntity<?> getUnfinishedRepairs(@PathVariable("vehicle_id") Long vehicle_id){
        return ResponseEntity.ok(repairVehicleService.getUnfinishedRepairsOfAVehicle(vehicle_id));
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

    @PostMapping("")
    ResponseEntity<?> createRepairVehicles(@RequestBody List<RepairVehicleDTO> repairsVehicle){
        return ResponseEntity.ok(repairVehicleService.saveRepairAndDetails(repairsVehicle));
    }

    @PutMapping("")
    ResponseEntity<?> updateRepairVehicles(@RequestBody List<RepairVehicleEntity> repairsVehicle){
        return ResponseEntity.ok(repairVehicleService.finishRepairVehicle(repairsVehicle));
    }

    @GetMapping("/repair_details/{repair_vehicle_id}")
    ResponseEntity<?> getDetailsOfARepairVehicle(@PathVariable("repair_vehicle_id") Long repair_vehicle_id){
        return ResponseEntity.ok(repairVehicleService.getDetailsOfARepair(repair_vehicle_id));
    }





}
