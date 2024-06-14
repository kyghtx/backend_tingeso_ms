package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor

public class RepairVehicleDTO {
    @Getter @Setter
    private String patent;
    @Getter @Setter
    private Long repair_type_id;
}
