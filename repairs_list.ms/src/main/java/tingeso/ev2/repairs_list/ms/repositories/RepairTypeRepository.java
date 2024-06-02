package tingeso.ev2.repairs_list.ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.ev2.repairs_list.ms.entities.RepairTypeEntity;

import java.util.List;

@Repository
public interface RepairTypeRepository extends JpaRepository<RepairTypeEntity,Long> {
    //Para el manejo de las entidades acerca del tipo de reparacion
    @Query("SELECT rt FROM RepairTypeEntity  rt")
    List<RepairTypeEntity> findAllRepairTypes();

    //encontrar por ID
    @Query("SELECT rt FROM RepairTypeEntity rt WHERE rt.repair_type_id =:id")
    RepairTypeEntity findRepairTypeById(@Param("id") Long id);

    //Delete a types of repair

}
