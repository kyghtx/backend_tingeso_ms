package tingeso.ev2.reports_uh.ms.entities;

import lombok.Getter;
import lombok.Setter;

public class VehicleFeign {
    @Getter
    @Setter
    private Long vehicle_id;
    @Getter @Setter
    private int num_of_seats;
    @Getter @Setter
    private String patent;
    @Getter @Setter
    private String model;
    //FK----------------------------
    //fk brand
    @Getter @Setter
    private Long brand_id;
    @Getter @Setter
    //motor type fk
    private Long motor_type_id;
    //fk vehicle type
    @Getter @Setter
    private Long vehicle_type_id;
    @Getter @Setter
    private int year_of_manufacturing;
    @Getter @Setter
    private Long km_vehicle;
}
