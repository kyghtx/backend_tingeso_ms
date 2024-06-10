package tingeso.ev2.repairs_list.ms.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class RepairPriceEntity {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repair_price_id;
    //Segun el modelo descrito
    //Esto serian las fk que en si deben ser conectadas con el microservicio de los vehiculos
    @Getter @Setter
    private Long motor_type_id; //this is a request os vehicle.MS
    private Long repair_type_id; //this is present in this microservice
    private Double price; //price of repair, depends of motor_type and repair_type


}
