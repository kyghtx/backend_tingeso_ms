package tingeso.ev2.repairs_vehicle.ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tingeso.ev2.repairs_vehicle.ms.entities.VehicleFeign;

import java.util.List;

@FeignClient(value="vehicles.ms",path = "/api/vehicles")
public interface VehiclesClient {
    @GetMapping("/{vehicle_id}")
    ResponseEntity<VehicleFeign> getVehicle(@PathVariable("vehicle_id") Long vehicle_id);

    @GetMapping("")
    ResponseEntity<List<VehicleFeign>> getAllVehicles();

}
