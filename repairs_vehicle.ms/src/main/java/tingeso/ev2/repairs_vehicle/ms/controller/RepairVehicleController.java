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
    @GetMapping("")
    ResponseEntity<?> getAllVehicleRepairs(){
        return  ResponseEntity.ok((repairVehicleService.getAllRepairVehicles()));
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
    @GetMapping("/repair_details")
    ResponseEntity<?> getAllRepairDetails(){
        return ResponseEntity.ok(repairVehicleService.getAllRepairDetails());
    }
    @GetMapping("/repair_details/{repair_type_id}/{patent}/price")
    ResponseEntity<Long> getTotalMountOfARepairType(@PathVariable Long repair_type_id, @RequestParam int month, @RequestParam int year,@PathVariable String patent){
        return ResponseEntity.ok(repairVehicleService.getTotalMountOfARepairType(repair_type_id,month,year,patent));
    }
    @GetMapping("/repair_details/count/{repair_type_id}-{patent}")
    ResponseEntity<Long> countAllRepairType(@PathVariable Long repair_type_id,@PathVariable String patent,@RequestParam int month, @RequestParam int year){
        return ResponseEntity.ok(repairVehicleService.countAllRepairsOfAtype(repair_type_id,patent, month, year));
    }
    @GetMapping("/repair_details/sum/{repair_type_id}-{year}")
    ResponseEntity<Long> SumAllRepairTypeOnAMonth(@PathVariable Long repair_type_id,@RequestParam int month,@PathVariable int year){
        return ResponseEntity.ok(repairVehicleService.SumAllRepairsOfATypeFromAMonth(repair_type_id,month,year));
    }
    @GetMapping("/repair_details/repair/{repair_type_id}")
    ResponseEntity<Long> getRepairTypeDetails(@PathVariable Long repair_type_id){
        return ResponseEntity.ok(repairVehicleService.sumAllRepairOfAType(repair_type_id));
    }
    @GetMapping("/repair_details/countRepairs/{repair_type_id}-{year}")
    ResponseEntity<Long> countAllRepairTypeOnAMonth(@PathVariable Long repair_type_id,@RequestParam int month,@PathVariable int year){
        return ResponseEntity.ok(repairVehicleService.countAllRepairsOfATypeFromAMonth(repair_type_id,month,year));

    }



}
