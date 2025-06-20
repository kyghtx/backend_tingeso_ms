package tingeso.ev2.repairs_vehicle.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor

public class RepairVehicleDTO {
    @Getter @Setter
    private Long vehicle_id;
    @Getter @Setter
    private Long repair_type_id;
}
