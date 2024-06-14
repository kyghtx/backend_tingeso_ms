package service;

import entities.RepairDetailEntity;
import entities.RepairVehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.RepairDetailRepository;
import repositories.RepairVehicleRepository;

import java.util.List;

@Service
public class RepairVehicleService {
    @Autowired
    private RepairVehicleRepository repairVehicleRepository;
    private RepairDetailRepository repairDetailRepository;
    /*to get repairs of a vehicle*/
    public List<RepairVehicleEntity> getAllRepairsOfAVehicle(Long vehicleId){
        return repairVehicleRepository.findRepairsByVehicleId(vehicleId);
    }
    /*Patent*/
    //get details of a repair
    public List<RepairDetailEntity> getAllRepairDetailsOfRepair(Long repair_vehicle_id){
        return repairDetailRepository.findAllDetailsOfRepairVehicle(repair_vehicle_id);
    }



}
