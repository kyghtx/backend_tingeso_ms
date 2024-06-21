package tingeso.ev2.vehicle.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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



}
