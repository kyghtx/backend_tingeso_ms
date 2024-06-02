package tingeso.ev2.repairs_list.ms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tingeso.ev2.repairs_list.ms.services.RepairTypeService;

@RestController
@RequestMapping("api/repair_types")
public class RepairTypeController {
    @Autowired
    RepairTypeService repairTypeService;


    //ENDPOINTS
}
