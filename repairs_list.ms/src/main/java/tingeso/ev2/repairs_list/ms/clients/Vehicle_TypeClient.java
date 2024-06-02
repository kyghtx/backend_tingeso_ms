package tingeso.ev2.repairs_list.ms.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tingeso.ev2.repairs_list.ms.DTO.Vehicle_TypeDTO;

import java.util.List;

@FeignClient(value = "vehicles.ms", path = "api/vehicles/types")
public interface Vehicle_TypeClient {
    @GetMapping
    List<Vehicle_TypeDTO> get_Vehicle_Types();

}
