package tingeso.ev2.reports_uh.ms.services;

import org.springframework.stereotype.Service;
import tingeso.ev2.reports_uh.ms.client.RepairDetailClient;
import tingeso.ev2.reports_uh.ms.entities.RepairDetailFeign;

import java.util.List;

@Service
public class R1Service {
    RepairDetailClient repairDetailClient;
    public List<RepairDetailFeign> getAllRepair() {
        return repairDetailClient.getAllVehicleRepairs();
    }
}
