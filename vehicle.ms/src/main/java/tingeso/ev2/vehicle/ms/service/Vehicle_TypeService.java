package tingeso.ev2.vehicle.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.ev2.vehicle.ms.entity.Vehicle_TypeEntity;
import tingeso.ev2.vehicle.ms.repository.VehicleTypeRepository;

import java.util.List;

@Service
public class Vehicle_TypeService {
    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    public List<Vehicle_TypeEntity> getAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }

    public Vehicle_TypeEntity getVehicleTypeById(Long id) {
        return vehicleTypeRepository.findVehicleTypeById(id);
    }

    //CREATE
    //Only create if doesnt exist

    public Vehicle_TypeEntity create(Vehicle_TypeEntity newType) {
        Vehicle_TypeEntity existType= vehicleTypeRepository.findVehicleTypeByName(newType.getVehicle_type_name());
        if(existType==null){
            return vehicleTypeRepository.save(newType);
        }
        return null;
    }

}
