package tingeso.ev2.reports_uh.ms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.ev2.reports_uh.ms.client.RepairDetailClient;
import tingeso.ev2.reports_uh.ms.entities.RepairDetailFeign;

import java.util.List;

@Service
public class R1Service {
    RepairDetailClient repairDetailClient;
    @Autowired
    public R1Service(RepairDetailClient repairDetailClient){
        this.repairDetailClient = repairDetailClient;

    }
    public List<RepairDetailFeign> getAllRepair() {
        return repairDetailClient.getAllVehicleRepairs();
    }
}
