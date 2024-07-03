package tingeso.ev2.reports_uh.ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tingeso.ev2.reports_uh.ms.entities.RepairTypeFeign;

import java.util.List;

@FeignClient(value="repairs-list.ms",path = "/api/repairs_types")
public interface RepairTypeClient {
    @GetMapping("")
    List<RepairTypeFeign> getRepairTypes();
}
