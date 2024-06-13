package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class RepairsDiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long repair_discount_id;
    @Getter @Setter
    private String numbers_of_repairs;
    @Getter @Setter
    private int motor_type_id;
    @Getter @Setter
    /*percent of discount*/
    private float repair_discount;
}
