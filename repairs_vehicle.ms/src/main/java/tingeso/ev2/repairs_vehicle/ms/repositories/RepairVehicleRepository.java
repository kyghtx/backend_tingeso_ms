package tingeso.ev2.repairs_vehicle.ms.repositories;

import tingeso.ev2.repairs_vehicle.ms.entities.RepairVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepairVehicleRepository extends JpaRepository<RepairVehicleEntity,Long> {
    //query for get all repairs of a vehicle
    @Query("SELECT rp FROM RepairVehicleEntity rp WHERE rp.vehicle_id =:vehicle_id")
    List<RepairVehicleEntity> findRepairsByVehicleId(@Param("vehicle_id") Long vehicle_id);




}
