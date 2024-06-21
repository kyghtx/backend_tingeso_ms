package tingeso.ev2.repairs_vehicle.ms.entities;

import lombok.Getter;
import lombok.Setter;

public class RepairPriceFeign {
    @Getter @Setter
    private Long repair_price_id;
    //Segun el modelo descrito
    //Esto serian las fk que en si deben ser conectadas con el microservicio de los vehiculos
    @Getter
    @Setter
    private Long motor_type_id; //this is a request os vehicle.MS
    @Getter @Setter
    private Long repair_type_id; //this is present in this microservice
    @Getter @Setter
    private Long price; //price of repair, depends of motor_type and repair_type
}
