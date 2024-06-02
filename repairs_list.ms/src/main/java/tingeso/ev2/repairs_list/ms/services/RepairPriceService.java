package tingeso.ev2.repairs_list.ms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.ev2.repairs_list.ms.DTO.Vehicle_TypeDTO;
import tingeso.ev2.repairs_list.ms.clients.Vehicle_TypeClient;
import tingeso.ev2.repairs_list.ms.entities.RepairPriceEntity;
import tingeso.ev2.repairs_list.ms.repositories.RepairPriceRepository;

import java.util.List;

@Service
public class RepairPriceService {
    Vehicle_TypeClient vehicleTypeClient;
    //Crud for Repair price entities
    @Autowired
    RepairPriceRepository repairPriceRepository;

    public List<Vehicle_TypeDTO> getVehicleTypes() {
        return vehicleTypeClient.get_Vehicle_Types();
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
