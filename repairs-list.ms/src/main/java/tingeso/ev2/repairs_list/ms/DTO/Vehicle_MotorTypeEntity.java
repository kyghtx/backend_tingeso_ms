package tingeso.ev2.repairs_list.ms.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle_MotorTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long motor_type_id;
    private String motor_type_name;

    public String getMotor_type_name() {
        return motor_type_name;
    }

    public void setMotor_type_name(String motor_type_name) {
        this.motor_type_name = motor_type_name;
    }

    public Long getMotor_type_id() {
        return motor_type_id;
    }

    public void setMotor_type_id(Long motor_type_id) {
        this.motor_type_id = motor_type_id;
    }
}
