package tingeso.ev2.vehicle.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.ev2.vehicle.ms.entity.Vehicle_MotorTypeEntity;

import java.util.List;

@Repository
public interface Vehicle_MotorTypeRepository extends JpaRepository<Vehicle_MotorTypeEntity,Long> {
    @Query("SELECT vmt FROM Vehicle_MotorTypeEntity vmt")
    List<Vehicle_MotorTypeEntity> findAll();

    //Select Motor type by id
    @Query("SELECT vmt FROM Vehicle_MotorTypeEntity vmt WHERE vmt.motor_type_id =:id")
    Vehicle_MotorTypeEntity findMotorTypeById(@Param("id") Long id);



}