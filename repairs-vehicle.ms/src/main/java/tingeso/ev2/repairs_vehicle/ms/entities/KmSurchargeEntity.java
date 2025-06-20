package tingeso.ev2.repairs_vehicle.ms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class KmSurchargeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long km_surcharge_id;
    @Getter @Setter
    private String km_surcharge_range;
    @Getter @Setter
    private Long vehicle_type_id;
    @Getter @Setter
    private float km_surcharge_value;


}
