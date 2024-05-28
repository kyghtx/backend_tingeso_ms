package tingeso.ev2.vehicle.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.ev2.vehicle.ms.entity.Vehicle_BrandEntity;
import tingeso.ev2.vehicle.ms.repository.Vehicle_BrandRepository;

import java.util.List;

@Service
public class Vehicle_BrandService {
    @Autowired
    Vehicle_BrandRepository vehicle_brandRepository;

    //get all brands
   public List<Vehicle_BrandEntity> getAllBrands(){
        /*return of all brands*/
        return vehicle_brandRepository.findAllBrands();
    }

    /*Get brand by id*/
    public Vehicle_BrandEntity getBrandByID(Long id){
        return vehicle_brandRepository.findBrandById(id);
    }

    //CREATE

    //only creates if brand_id doesnt exist
    public Vehicle_BrandEntity create(Vehicle_BrandEntity newBrand){
        String copyNameAux=newBrand.getBrand_name();
        Vehicle_BrandEntity existBrand=vehicle_brandRepository.findBrandByName(newBrand.getBrand_name());
        if (existBrand == null){
            return vehicle_brandRepository.save(newBrand);
        }
        return null;
    }

    //DELETE ALL BRANDS
    public void deleteAllBrands(){
        vehicle_brandRepository.deleteAll();
    }
}
