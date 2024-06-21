package tingeso.ev2.repairs_list.ms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.ev2.repairs_list.ms.entities.RepairPriceEntity;
import tingeso.ev2.repairs_list.ms.services.RepairPriceService;

import java.util.List;

@RestController
@RequestMapping("/api/repairs_types")
public class RepairPriceController {
    @Autowired
    RepairPriceService repairPriceService;
    @GetMapping("/repairs_prices")
    ResponseEntity<?> getRepairPrices() {
        return ResponseEntity.ok(repairPriceService.getRepairPrices());
    }

    @GetMapping("/repairs_prices/{motor_type_id}/{repair_type_id}")
    ResponseEntity<?> getRepairPrice(@PathVariable("motor_type_id") Long motorId,@PathVariable("repair_type_id") Long repairTypeId) {
        return ResponseEntity.ok(repairPriceService.getRepairPrice(motorId,repairTypeId));
    }

    @GetMapping("/repairs_prices/motor_types")
    ResponseEntity<?> feignVehicleMotorTypes() {
        return ResponseEntity.ok(repairPriceService.getVehicleMotorTypes());
    }

    @PostMapping("/repairs_prices")
    ResponseEntity<?> savePrices(@RequestBody List<RepairPriceEntity> priceEntities){
        return ResponseEntity.ok(repairPriceService.create(priceEntities));
    }
}
