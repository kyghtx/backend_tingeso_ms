package tingeso.ev2.repairs_vehicle.ms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class BonusEntity {
    //One bonus is aplicable is user want
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long bonus_id;
    @Getter @Setter
    private String brand_name;
    @Getter @Setter
    private int bonus_quantity;
    @Getter @Setter
    private int bonus_value;
}
