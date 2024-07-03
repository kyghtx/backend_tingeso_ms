package tingeso.ev2.reports_uh.ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tingeso.ev2.reports_uh.ms.entities.RepairDetailFeign;
import tingeso.ev2.reports_uh.ms.entities.RepairVehicleFeign;

import java.util.List;

@FeignClient(value="repairs-vehicle.ms",path = "/api/repairs_vehicles")
public interface RepairDetailClient {
    @GetMapping("")
    List<RepairVehicleFeign> getAllVehicleRepairs();
    @GetMapping("/repair_details")
    List<RepairDetailFeign> getAllRepairDetails();
    @GetMapping("/repair_details/{repair_type_id}/price")
    Long getTotalMountOfARepairType(@PathVariable Long repair_type_id, @RequestParam int month, @RequestParam int year);
    @GetMapping("/repair_details/count/{repair_type_id}-{patent}")
    Long countAllRepairType(@PathVariable Long repair_type_id,@PathVariable String patent,@RequestParam int month, @RequestParam int year);
}