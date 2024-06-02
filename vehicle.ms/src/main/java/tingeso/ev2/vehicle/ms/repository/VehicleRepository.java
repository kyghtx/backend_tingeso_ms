package tingeso.ev2.vehicle.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.ev2.vehicle.ms.DTO.VehicleDTO;
import tingeso.ev2.vehicle.ms.entity.VehicleEntity;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity,Long> {

    //Select all vehicles.
    @Query("SELECT v FROM VehicleEntity v")
    List<VehicleEntity> findAllVehicles();

    //Select vehicle by patent.
    @Query("SELECT v FROM VehicleEntity v WHERE v.patent =:patent")
    VehicleEntity findByPatent(@Param("patent") String patent);

    //Select all vehicles from a type.
    @Query("SELECT v FROM VehicleEntity v WHERE v.vehicle_type_id =:type_vehicle")
    List<VehicleEntity> getAllVehiclesFromAType(@Param("type_vehicle") Long type_vehicle);

    //Select all vehicles with a motor type
    @Query("SELECT v FROM VehicleEntity v WHERE v.motor_type_id =:motor_type")
    List<VehicleEntity> getAllVehiclesFromAMotorType(@Param("motor_type") Long motor_type);

    //delete all Vehicles
    @Query("DELETE FROM VehicleEntity")
    void deleteAll();

    @Query(value = "SELECT new tingeso.ev2.vehicle.ms.DTO.VehicleDTO(v.patent, vb.brand_name, vt.vehicle_type_name, vm.motor_type_name) " +
            " FROM vehicle_entity v JOIN vehicle_type_entity vt ON v.vehicle_type_id = vt.vehicle_type_id " +
            "JOIN v ON v.brand_id = vehicle_brand_entity.brand_id " +
            "JOIN vehicle_motor_type_entity ON vehicle_entity.motor_type_id = vehicle_motor_type_entity.motor_type_id",nativeQuery = true)
    List<VehicleDTO> findCustomVehicleData();

}
