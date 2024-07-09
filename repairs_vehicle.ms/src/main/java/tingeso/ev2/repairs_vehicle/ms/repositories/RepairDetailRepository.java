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
    @Query("SELECT rd FROM RepairDetailEntity rd")
    List<RepairDetailEntity> findAllRepairDetails();
    @Query("SELECT SUM(rd.price) FROM RepairDetailEntity rd WHERE rd.repair_type_id =:repair_type_id AND MONTH(rd.repair_date) = :month AND YEAR(rd.repair_date) = :year AND rd.patent =:patent")
    Long sumAllPricesOfARepairType(@Param("repair_type_id") Long repair_type_id,@Param("month") int month, @Param("year") int year, @Param("patent") String patent);
    @Query("SELECT COUNT(rd) FROM RepairDetailEntity  rd WHERE rd.repair_type_id =:repair_type_id AND rd.patent =:patent " +
            "AND MONTH(rd.repair_date) = :month AND YEAR(rd.repair_date) = :year")
    Long sumAllRepairsOfATypOfAVehicle(@Param("repair_type_id") Long repair_type_id, @Param("patent") String patent,@Param("month") int month, @Param("year") int year);



}
