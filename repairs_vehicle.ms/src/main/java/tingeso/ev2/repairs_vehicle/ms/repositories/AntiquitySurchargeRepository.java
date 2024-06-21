package tingeso.ev2.repairs_vehicle.ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.ev2.repairs_vehicle.ms.entities.AntiquitySurchargeEntity;
import tingeso.ev2.repairs_vehicle.ms.entities.KmSurchargeEntity;

@Repository
public interface AntiquitySurchargeRepository extends JpaRepository<AntiquitySurchargeEntity,Long> {
    @Query("SELECT ats FROM AntiquitySurchargeEntity ats WHERE ats.vehicle_type_id =:type_id AND ats.antiquity_surcharge_range =:range")
    AntiquitySurchargeEntity getAntiquitySurcharge(@Param("type_id") Long type_id, @Param("range") String range);
}
