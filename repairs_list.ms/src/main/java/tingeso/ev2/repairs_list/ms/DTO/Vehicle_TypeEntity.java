package tingeso.ev2.repairs_list.ms.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Vehicle_TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicle_type_id;
    private String vehicle_type_name;

    public String getVehicle_type_name() {
        return vehicle_type_name;
    }

    public void setVehicle_type_name(String vehicle_type_name) {
        this.vehicle_type_name = vehicle_type_name;
    }

    public Long getVehicle_type_id() {
        return vehicle_type_id;
    }

    public void setVehicle_type_id(Long vehicle_type_id) {
        this.vehicle_type_id = vehicle_type_id;
    }
}
