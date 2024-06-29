package tingeso.ev2.reports_uh.ms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class R1Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long R1_value_id;
    private String vehicle_type_name;
    private Long total_repairs;
}
