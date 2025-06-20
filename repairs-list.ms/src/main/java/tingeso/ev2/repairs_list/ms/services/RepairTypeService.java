package tingeso.ev2.repairs_list.ms.services;

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
    public List<RepairTypeEntity> get_repair_list(){
        return repairTypeRepository.findAllRepairTypes();
    }

    public RepairTypeEntity get_repair_type_by_id(Long id){
        return repairTypeRepository.findById(id).orElse(null);
    }

    //Create
     public  RepairTypeEntity create(RepairTypeEntity newRepairType){
        //SI es que el tipo de reparacion no existe
        return repairTypeRepository.save(newRepairType);
    }

    //update (al parecer se usa save tambien)
}

