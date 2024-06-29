package tingeso.ev2.repairs_vehicle.ms.repositories;

import tingeso.ev2.repairs_vehicle.ms.entities.RepairDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.ev2.repairs_vehicle.ms.entities.RepairVehicleEntity;

import java.util.List;

@Repository
public interface RepairDetailRepository extends JpaRepository<RepairDetailEntity,Long> {
    //this is a repository for the details of repairs of the vehicles.
    //select all detail of repairs for a vehicle
    /*select repair details of a repair*/
    @Query("SELECT rd FROM RepairDetailEntity rd WHERE rd.vehicle_id =:vehicle_id")
    List<RepairDetailEntity> findAllDetailsOfRepairVehicle(@Param("vehicle_id") Long vehicle_id);



}
