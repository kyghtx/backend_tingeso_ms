package tingeso.ev2.repairs_vehicle.ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.ev2.repairs_vehicle.ms.entities.KmSurchargeEntity;

@Repository
public interface KmSurchargeRepository extends JpaRepository<KmSurchargeEntity,Long> {
    @Query("SELECT km FROM KmSurchargeEntity  km WHERE km.vehicle_type_id =:type_id AND km.km_surcharge_range =:range")
    KmSurchargeEntity getKmSurchargeValue(@Param("type_id") Long type_id, @Param("range") String range);

}
