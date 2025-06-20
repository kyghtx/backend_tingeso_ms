package tingeso.ev2.repairs_vehicle.ms.entities;

import lombok.Getter;
import lombok.Setter;

public class RepairTypeFeign {
    @Getter
    @Setter
    private Long repair_type_id;
    @Getter @Setter
    private String repair_type_name; //tipo de reparacion
    @Getter @Setter
    private String description;
}
