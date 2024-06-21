package tingeso.ev2.repairs_vehicle.ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tingeso.ev2.repairs_vehicle.ms.entities.RepairPriceFeign;
import tingeso.ev2.repairs_vehicle.ms.entities.RepairTypeFeign;

import java.util.List;

@FeignClient(value="repairs-list.ms",path = "/api/repairs_types")
public interface RepairTypesFeign {
    @GetMapping("/repairs_prices/{motor_type_id}/{repair_type_id}")
    ResponseEntity<RepairPriceFeign> getRepairPrice(@PathVariable("motor_type_id") Long motorId, @PathVariable("repair_type_id") Long repairTypeId);
    @GetMapping("")
    ResponseEntity<List<RepairTypeFeign>> getRepairTypes();
}
