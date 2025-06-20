package tingeso.ev2.repairs_vehicle.ms.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

public class VehicleTypeFeign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long vehicle_type_id;
    @Getter @Setter
    private String vehicle_type_name;
}
