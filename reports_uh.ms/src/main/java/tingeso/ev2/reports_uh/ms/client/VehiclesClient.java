package tingeso.ev2.reports_uh.ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tingeso.ev2.reports_uh.ms.entities.VehicleFeign;
import tingeso.ev2.reports_uh.ms.entities.VehicleTypeFeign;

import java.util.List;

@FeignClient(value="vehicles.ms",path = "/api/vehicles")
public interface VehiclesClient {
    @GetMapping("")
    List<VehicleFeign> getAllVehicles();
    @GetMapping("/types")
    List<VehicleTypeFeign> getAllVehicleTypes();
    @GetMapping("/typeº{type}")
    List<VehicleFeign> getVehiclesByType(@PathVariable("type") Long type);
    @GetMapping("/count")
    Long countAllVehiclesByType(@RequestParam Long type);



}
