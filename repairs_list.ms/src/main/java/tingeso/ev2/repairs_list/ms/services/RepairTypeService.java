package tingeso.ev2.repairs_list.ms.services;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.ev2.repairs_list.ms.entities.RepairTypeEntity;
import tingeso.ev2.repairs_list.ms.repositories.RepairTypeRepository;

import java.util.List;
//Posiblemente en esta capa deba ver el tema de un manejo correcto.
@Service
public class RepairTypeService {
    @Autowired
    RepairTypeRepository repairTypeRepository;

    //Find all repairs types.
    List<RepairTypeEntity> get_repair_list(){
        return repairTypeRepository.findAllRepairTypes();
    }

    RepairTypeEntity get_repair_type_by_id(Long id){
        return repairTypeRepository.findById(id).orElse(null);
    }
}
