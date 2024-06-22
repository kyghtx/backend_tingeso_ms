package tingeso.ev2.repairs_vehicle.ms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

//This class is for a repair of a vehicle.
@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor

public class RepairVehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long repair_vehicle_id;
    //
    @Getter @Setter
    private Long vehicle_id;
    @Getter @Setter
    private LocalDate vehicle_income;
    @Getter @Setter
    private LocalTime vehicle_income_hour;
    //price of repairs
    @Getter @Setter
    private Long total_price_repairs;//possibly without discounts
    @Getter @Setter
    private double surcharges_mount;
    @Getter @Setter
    private double discounts_mount;
    @Getter @Setter
    private double iva_mount;
    @Getter @Setter
    //mount with discounts, surcharges and bonus
    private double total_cost;
    @Getter @Setter
    private LocalDate vehicle_outcome;
    @Getter @Setter
    private LocalTime vehicle_outcome_hour;
    @Getter @Setter
    /*date and hour when the client retire his vehicle*/
    private LocalDate vehicle_client_retire;
    @Getter @Setter
    private LocalTime vehicle_client_retire_hour;
    @Getter @Setter
    private int state;







}
