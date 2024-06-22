package tingeso.ev2.vehicle.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.ev2.vehicle.ms.DTO.VehicleDTO;
import tingeso.ev2.vehicle.ms.entity.VehicleEntity;
import tingeso.ev2.vehicle.ms.entity.Vehicle_BrandEntity;
import tingeso.ev2.vehicle.ms.entity.Vehicle_MotorTypeEntity;
import tingeso.ev2.vehicle.ms.entity.Vehicle_TypeEntity;
import tingeso.ev2.vehicle.ms.repository.VehicleRepository;
import tingeso.ev2.vehicle.ms.repository.VehicleTypeRepository;
import tingeso.ev2.vehicle.ms.repository.Vehicle_BrandRepository;
import tingeso.ev2.vehicle.ms.repository.Vehicle_MotorTypeRepository;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    Vehicle_MotorTypeRepository vehicle_motorTypeRepository;
    @Autowired
    Vehicle_BrandRepository vehicleBrandRepository;
    //for validations


    /*select all vehicles*/
    public List<VehicleEntity> get_all_vehicles(){
        return vehicleRepository.findAllVehicles();
    }
    /*get vehicle by patent*/
    public VehicleEntity find_by_patent(String patent){
        return vehicleRepository.findByPatent(patent);
    }
    //CREATE
    public VehicleEntity findById(Long id){
        return vehicleRepository.findByVehicleId(id);
        
    }
    public VehicleEntity create(VehicleEntity vehicle){


       //case when vehicle list isnt empty.

        //IF doesnt exists
        VehicleEntity exist_patent = vehicleRepository.findByPatent(vehicle.getPatent());
        //brand existence
        Vehicle_BrandEntity exist_brand = vehicleBrandRepository.findBrandById(vehicle.getBrand_id());
        //type motor type existence
        Vehicle_MotorTypeEntity exist_motor_type = vehicle_motorTypeRepository.findMotorTypeById(vehicle.getMotor_type_id());
        //type vehicle existence
        Vehicle_TypeEntity exist_type = vehicleTypeRepository.findVehicleTypeById(vehicle.getVehicle_type_id());



        if (exist_patent == null && exist_brand != null && exist_motor_type != null && exist_type != null){
            vehicle.setKm_vehicle(0L);
            vehicleRepository.save(vehicle);
        return vehicle;
        }
        return null;
    }

    //Get all vehicles from a type.

    public List<VehicleEntity> get_all_vehicles_by_type(Long type){
        return vehicleRepository.getAllVehiclesFromAType(type);
    }

    //Delete all vehicles
    public void DeleteAllVehicles(){
        vehicleRepository.deleteAll();
    }

   public VehicleEntity updateKmVehicle(Long newKm, String patent){
        VehicleEntity v = vehicleRepository.findByPatent(patent);
        if (v != null){
            v.setKm_vehicle(newKm);
            return vehicleRepository.save(v);
        }
        return null;

   }
}
