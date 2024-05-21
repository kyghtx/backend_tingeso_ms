package tingeso.ev2.vehicle.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.ev2.vehicle.ms.entity.VehicleEntity;
import tingeso.ev2.vehicle.ms.repository.VehicleRepository;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    /*select all vehicles*/
    public List<VehicleEntity> get_all_vehicles(){
        return vehicleRepository.findAllVehicles();
    }
    /*get vehicle by patent*/
    public VehicleEntity find_by_patent(String patent){
        return vehicleRepository.findByPatent(patent);
    }
    //CREATE
    //TODO: VER EL MANEJO DE EXCEPCIONES
    public VehicleEntity create(VehicleEntity vehicle){
        //IF doesnt exists
        VehicleEntity aux = vehicleRepository.findByPatent(vehicle.getPatent());
        if (aux == null){
        vehicleRepository.save(vehicle);
        return vehicle;
        }
        return null;
    }

}
