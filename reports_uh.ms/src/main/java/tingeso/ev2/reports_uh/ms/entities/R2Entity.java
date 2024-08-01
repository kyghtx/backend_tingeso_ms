package tingeso.ev2.reports_uh.ms.entities;

import lombok.Data;

@Data
public class R2Entity {
    private String repair_type_name;
    private int Month;
    private Long repair_total_quantity;
    private Long repair_type_total_mount;
}
