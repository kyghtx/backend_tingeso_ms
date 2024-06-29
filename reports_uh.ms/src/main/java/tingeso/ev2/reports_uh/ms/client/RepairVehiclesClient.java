package tingeso.ev2.reports_uh.ms.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="repairs-vehicle.ms",path = "/api/repairs_vehicles")
public interface RepairVehiclesClient {

}
