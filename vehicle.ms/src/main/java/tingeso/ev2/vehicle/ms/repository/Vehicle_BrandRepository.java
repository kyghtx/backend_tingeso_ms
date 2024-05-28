package tingeso.ev2.vehicle.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.ev2.vehicle.ms.entity.Vehicle_BrandEntity;

import java.util.List;

@Repository
public interface Vehicle_BrandRepository extends JpaRepository<Vehicle_BrandEntity,Long> {
    @Query("SELECT br FROM Vehicle_BrandEntity br")
    List<Vehicle_BrandEntity> findAllBrands();
    //getBrandById
    @Query("SELECT br FROM Vehicle_BrandEntity br WHERE br.brand_id =:brand_id")
    Vehicle_BrandEntity findBrandById(@Param("brand_id") Long brand_id);

    //Get brand by name
    @Query("SELECT br FROM Vehicle_BrandEntity br WHERE br.brand_name =:brand_name")
    Vehicle_BrandEntity findBrandByName(@Param("brand_name") String brand_name);
}
