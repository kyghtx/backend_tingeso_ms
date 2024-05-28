package tingeso.ev2.vehicle.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.ev2.vehicle.ms.entity.Vehicle_MotorTypeEntity;
import tingeso.ev2.vehicle.ms.repository.Vehicle_MotorTypeRepository;

import java.util.List;

@Service
public class Vehicle_MotorTypeService {
    @Autowired
    Vehicle_MotorTypeRepository vehicle_motorTypeRepository;
    //Get al motor types
    public List<Vehicle_MotorTypeEntity> getAllMotorTypes(){
        return vehicle_motorTypeRepository.findAll();
    }

    //Get motor type by id
    public Vehicle_MotorTypeEntity getMotorTypeById(Long id){
        return vehicle_motorTypeRepository.findMotorTypeById(id);
    }

    //Only create if doesn exist

    public Vehicle_MotorTypeEntity create(Vehicle_MotorTypeEntity motorType){
        Vehicle_MotorTypeEntity existMotor = vehicle_motorTypeRepository.findMotorTypeByName(motorType.getMotor_type_name());
        if(existMotor == null){
            return vehicle_motorTypeRepository.save(motorType);
        }
        return null;
    }
}
