package tingeso.ev2.repairs_list.ms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tingeso.ev2.repairs_list.ms.services.RepairPriceService;

@RestController
@RequestMapping("api/repair_prices")
public class RepairPriceController {
    @Autowired
    RepairPriceService repairPriceService;
    @GetMapping("")
    ResponseEntity<?> getRepairPrices() {
        return ResponseEntity.ok(repairPriceService.getRepairPrices());
    }

    @GetMapping("/{vehicle_id}/{repair_type_id}")
    ResponseEntity<?> getRepairPrice(@PathVariable("vehicle_id") Long vehicleId,@PathVariable("repair_type_id") Long repairTypeId) {
        return ResponseEntity.ok(repairPriceService.getRepairPrice(vehicleId,repairTypeId));
    }

    @GetMapping("/repair_prices/vehicle_types")
    ResponseEntity<?> feignVehicleTypes() {
        return ResponseEntity.ok(repairPriceService.getVehicleTypes());
    }
}
