package tingeso.ev2.repairs_vehicle.ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.ev2.repairs_vehicle.ms.entities.RepairsDiscountEntity;

@Repository
public interface RepairDiscountRepository extends JpaRepository<RepairsDiscountEntity,Long> {
    @Query("SELECT rpd FROM RepairsDiscountEntity rpd WHERE rpd.motor_type_id=:type_id AND rpd.numbers_of_repairs =:repairs")
    RepairsDiscountEntity getRepairDiscount(@Param("type_id") Long type_id, @Param("repairs") String repairs);
}
