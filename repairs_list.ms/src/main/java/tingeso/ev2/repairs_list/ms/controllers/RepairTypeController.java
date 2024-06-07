package tingeso.ev2.repairs_list.ms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.ev2.repairs_list.ms.entities.RepairTypeEntity;
import tingeso.ev2.repairs_list.ms.services.RepairTypeService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class RepairTypeController {
    @Autowired
    RepairTypeService repairTypeService;

    //ENDPOINTS
    @GetMapping("/repairs_types")
    public ResponseEntity<?> getRepairTypes() {
        return ResponseEntity.ok(repairTypeService.get_repair_list());
    }
    //create
    @PostMapping("/repairs_types")
    public ResponseEntity<?> create(@RequestBody RepairTypeEntity newRepairType){
        return ResponseEntity.ok(repairTypeService.create(newRepairType));
    }

}
