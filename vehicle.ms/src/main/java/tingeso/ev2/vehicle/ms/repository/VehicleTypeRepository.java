package tingeso.ev2.vehicle.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.ev2.vehicle.ms.entity.Vehicle_TypeEntity;

import java.util.List;

@Repository
public interface VehicleTypeRepository extends JpaRepository<Vehicle_TypeEntity,Long> {
    //Tipo de vehiculo
    @Query("SELECT vt FROM Vehicle_TypeEntity vt")
    List<Vehicle_TypeEntity> findAllVehicleTypes();

    //Con un ID determinado
    @Query("SELECT vt FROM Vehicle_TypeEntity  vt WHERE vt.vehicle_type_id =:id")
    Vehicle_TypeEntity findVehicleTypeById(@Param("id") Long id);

    //get vehicle type by name
    @Query("SELECT vt FROM Vehicle_TypeEntity  vt WHERE vt.vehicle_type_name =:name")
    Vehicle_TypeEntity findVehicleTypeByName(@Param("name") String name);



}
