package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class AntiquitySurchargeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long antiquity_surcharge_id;
    @Getter @Setter
    private String antiquity_surcharge_range;
    @Getter @Setter
    private Long vehicle_type_id;
    @Getter @Setter
    private float antiquity_surcharge_value;
}
