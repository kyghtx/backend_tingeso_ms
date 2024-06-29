package tingeso.ev2.repairs_vehicle.ms.repositories;

import tingeso.ev2.repairs_vehicle.ms.entities.RepairVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface RepairVehicleRepository extends JpaRepository<RepairVehicleEntity,Long> {
    //query for get all repairs of a vehicle
    @Query("SELECT rp FROM RepairVehicleEntity rp WHERE rp.vehicle_id =:vehicle_id")
    List<RepairVehicleEntity> findRepairsByVehicleId(@Param("vehicle_id") Long vehicle_id);

    //Query to get numbers of reparations of a vehicle.
    @Query("SELECT COUNT(r) FROM RepairVehicleEntity r WHERE r.vehicle_id = :vehicle_id AND r.vehicle_outcome >= :fecha_limite")
    Long countRepairsFromAVehicleLast12Months(@Param("vehicle_id") Long vehicle_id, @Param("fecha_limite") LocalDate fecha_limite);
    //Query to get active repairs of a vehicle
    @Query("SELECT rp FROM RepairVehicleEntity rp WHERE (rp.state = 0 OR rp.vehicle_client_retire IS NULL)  AND rp.vehicle_id =:vehicle_id")
    List<RepairVehicleEntity> findUnfinishedRepairByVehicle(@Param("vehicle_id") Long vehicle_id);
    @Query("SELECT rd FROM RepairVehicleEntity rp")
    List<RepairVehicleEntity> findAllRepairVehicles();
}
