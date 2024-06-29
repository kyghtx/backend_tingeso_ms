package tingeso.ev2.reports_uh.ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import tingeso.ev2.reports_uh.ms.entities.RepairDetailFeign;

import java.util.List;

@FeignClient(value="repairs-vehicle.ms",path = "/api/repairs_vehicles")
public interface RepairDetailClient {
    @GetMapping("")
    List<RepairDetailFeign> getAllVehicleRepairs();
}