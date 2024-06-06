package tingeso.ev2.repairs_list.ms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tingeso.ev2.repairs_list.ms.DTO.Vehicle_TypeEntity;
import tingeso.ev2.repairs_list.ms.clients.Vehicle_TypeClient;
import tingeso.ev2.repairs_list.ms.entities.RepairPriceEntity;
import tingeso.ev2.repairs_list.ms.repositories.RepairPriceRepository;

import java.util.List;

@Service
public class RepairPriceService {
    Vehicle_TypeClient vehicle_TypeClient;

    @Autowired
    public  RepairPriceService(Vehicle_TypeClient vehicle_TypeClient){
        this.vehicle_TypeClient = vehicle_TypeClient;
    }
    //Crud for Repair price entities
    @Autowired
    RepairPriceRepository repairPriceRepository;

    public List<Vehicle_TypeEntity> getVehicleTypes() {
        return vehicle_TypeClient.getAllVehicleTypes().getBody();
    }

    //Get all repair prices for vehicle types
    public List<RepairPriceEntity> getRepairPrices() {
        return repairPriceRepository.findAllRepairPrices();
    }

    //Get repair price for a type vehicle and a type of repair
    public RepairPriceEntity getRepairPrice(Long vehicle_type_id, Long repair_type_id) {
        return repairPriceRepository.findRepairPriceEntitiesByVehicleTypeIdAndRepairTypeId(vehicle_type_id,repair_type_id);
    }


}
