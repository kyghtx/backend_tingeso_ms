package tingeso.ev2.repairs_list.ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.ev2.repairs_list.ms.entities.RepairPriceEntity;

import java.util.List;

//TODO: Ver la comunicacion con vehicle.ms para las consultas de creacion.
@Repository
public interface RepairPriceRepository  extends JpaRepository<RepairPriceEntity,Long>{

    //Get all repair prices
    @Query("SELECT rp FROM RepairPriceEntity rp")
    List<RepairPriceEntity> findAllRepairPrices();

    @Query("SELECT rp FROM RepairPriceEntity rp WHERE rp.motor_type_id =:motor_type_id AND rp.repair_type_id =:repair_type_id")
    RepairPriceEntity findRepairPriceEntitiesByVehicleTypeIdAndRepairTypeId(@Param("motor_type_id") Long vehicle_type_id, @Param("repair_type_id") Long repair_type_id);


}
