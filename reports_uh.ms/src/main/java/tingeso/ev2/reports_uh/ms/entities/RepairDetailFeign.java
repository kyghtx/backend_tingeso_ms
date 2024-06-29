package tingeso.ev2.reports_uh.ms.entities;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

public class RepairDetailFeign {
    @Getter
    @Setter
    private Long repair_detail_id;
    //type of repair
    @Getter @Setter
    private String patent;
    @Getter @Setter
    private Long vehicle_id;
    @Getter @Setter
    private Long repair_type_id;
    @Getter @Setter
    private LocalDate repair_date;
    //hour of repair
    @Getter @Setter
    private LocalTime repair_time;
    //mount of repair
    @Getter @Setter
    private Long price;
    @Getter @Setter
    //This is the foreign key, a detail for a repair of a vehicle
    private Long repair_vehicle_id;
}
