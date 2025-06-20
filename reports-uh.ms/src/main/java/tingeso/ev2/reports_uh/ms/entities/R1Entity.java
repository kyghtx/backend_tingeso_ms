package tingeso.ev2.reports_uh.ms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

@Entity
@Data
public class R1Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long R1_value_id;
    private String vehicle_type_name;
    private String repair_type_name;
    private Long vehicle_type_quantity;
    private Long total_mount_repairs;
    private Month month;
    private Year year;


}
