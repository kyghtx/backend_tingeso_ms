package tingeso.ev2.repairs_vehicle.ms.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

/*Esta entidad e spara generar el "Reporte" que lista todos los vehiculos con todos los datos.*/
public class ListEntity {
/*Esta entidad es la union ede todos los datos existentes apra generar dicho reporte solicitado*/
    @Getter @Setter
    private String brand_name;
    @Getter @Setter
    private String patent;
    @Getter @Setter
    private String model;
    @Getter @Setter
    private String vehicle_type_name;
    @Getter @Setter
    private int year_of_manufacturing;
    @Getter @Setter
    private String motor_type_name;
    @Getter @Setter
    private LocalDate vehicle_income;
    @Getter @Setter
    private LocalTime vehicle_income_hour;


}
