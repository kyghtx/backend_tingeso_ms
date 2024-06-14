package tingeso.ev2.repairs_vehicle.ms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class RepairDetailEntity {
    //Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long repair_detail_id;
    //type of repair
    @Getter @Setter
    private String patent;
    @Getter @Setter
    private Long repair_type_id;
    @Getter @Setter
    private LocalDate repair_date;
    //hour of repair
    @Getter @Setter
    private LocalTime repair_time;
    //mount of repair
    @Getter @Setter
    private Long price;
    @Getter @Setter
    //This is the foreign key, a detail for a repair of a vehicle
    private Long repair_vehicle_id;
}
