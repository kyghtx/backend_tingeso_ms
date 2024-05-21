package tingeso.ev2.vehicle.ms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Data

public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicle_id;
    private int num_of_seats;
    private String patent;
    private String model;
    //FK----------------------------
    //fk brand
    private Long brand_id;
    //motor type fk
    private Long motor_type_id;
    //fk vehicle type
    private Long vehicle_type_id;

    public Long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getNum_of_seats() {
        return num_of_seats;
    }

    public void setNum_of_seats(int num_of_seats) {
        this.num_of_seats = num_of_seats;
    }

    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getVehicle_type_id() {
        return vehicle_type_id;
    }

    public void setVehicle_type_id(Long vehicle_type_id) {
        this.vehicle_type_id = vehicle_type_id;
    }

    public Long getMotor_type_id() {
        return motor_type_id;
    }

    public void setMotor_type_id(Long motor_type_id) {
        this.motor_type_id = motor_type_id;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }
}
