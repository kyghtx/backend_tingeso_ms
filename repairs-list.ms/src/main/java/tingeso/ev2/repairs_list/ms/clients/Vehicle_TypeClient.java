package tingeso.ev2.repairs_list.ms.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import tingeso.ev2.repairs_list.ms.DTO.Vehicle_MotorTypeEntity;

import java.util.List;

@FeignClient(value = "vehicles.ms", path = "/api/vehicles/motor_types")
public interface Vehicle_TypeClient {
    @GetMapping("")
   ResponseEntity<List<Vehicle_MotorTypeEntity>> getAllVehicleMotorTypes();

}
