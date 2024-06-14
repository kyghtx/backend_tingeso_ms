package tingeso.ev2.repairs_vehicle.ms.repositories;

import tingeso.ev2.repairs_vehicle.ms.entities.RepairDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairDetailRepository extends JpaRepository<RepairDetailEntity,Long> {
    //this is a repository for the details of repairs of the vehicles.
    //select all detail of repairs for a vehicle
    @Query("SELECT rd FROM RepairDetailEntity rd WHERE rd.patent =:patent")
    List<RepairDetailEntity> findAllRepairDetailsFromAVehicle(@Param("patent") String patent);

    /*select repair details of a repair*/
    @Query("SELECT rd FROM RepairDetailEntity rd WHERE rd.repair_vehicle_id =:repair_vehicle_id")
    List<RepairDetailEntity> findAllDetailsOfRepairVehicle(@Param("repair_vehicle_id") Long repair_vehicle_id);

}
