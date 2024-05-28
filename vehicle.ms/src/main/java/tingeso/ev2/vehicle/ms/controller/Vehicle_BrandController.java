package tingeso.ev2.vehicle.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.ev2.vehicle.ms.entity.Vehicle_BrandEntity;
import tingeso.ev2.vehicle.ms.service.Vehicle_BrandService;

@RestController
@RequestMapping("/api/vehicles")
public class Vehicle_BrandController {
    @Autowired
    Vehicle_BrandService vehicle_brandService;

    @GetMapping("/brands")
    ResponseEntity<?> getAllBrands() {
        return ResponseEntity.ok(vehicle_brandService.getAllBrands());
    }

    @GetMapping("/brands/{id}")
    ResponseEntity<?> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicle_brandService.getBrandByID(id));
    }

    /*CREATE*/

    @PostMapping("/brands")
    ResponseEntity<?> saveBrand(@RequestBody Vehicle_BrandEntity newBrand){
        return ResponseEntity.ok(vehicle_brandService.create(newBrand));
    }

    /*UPDATE*/

    /*DELETE*/
    @DeleteMapping("/brands")
    void deleteAllBrands(){
        vehicle_brandService.deleteAllBrands();
    }

}
