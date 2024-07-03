package tingeso.ev2.reports_uh.ms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.ev2.reports_uh.ms.client.RepairDetailClient;
import tingeso.ev2.reports_uh.ms.client.RepairTypeClient;
import tingeso.ev2.reports_uh.ms.client.VehiclesClient;
import tingeso.ev2.reports_uh.ms.entities.*;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class R1Service {
    RepairDetailClient repairDetailClient;
    RepairTypeClient repairTypeClient;
    VehiclesClient vehiclesClient;
    @Autowired
    public R1Service(RepairDetailClient repairDetailClient, RepairTypeClient repairTypeClient, VehiclesClient vehiclesClient ){
        this.repairDetailClient = repairDetailClient;
        this.repairTypeClient = repairTypeClient;
        this.vehiclesClient = vehiclesClient;

    }
    public List<RepairVehicleFeign> getAllRepair() {
        return repairDetailClient.getAllVehicleRepairs();
    }
    public List<RepairTypeFeign> getAllRepairTypes() {
        return repairTypeClient.getRepairTypes();
    }
    /*Now i build the entities to show in frontend... */
    public List<R1Entity> buildEntitiesR1(int month, int year){
        List<RepairTypeFeign> repairTypes = repairTypeClient.getRepairTypes();
        List<RepairVehicleFeign> repairVehicles=repairDetailClient.getAllVehicleRepairs();
        List<RepairDetailFeign> repairDetails=repairDetailClient.getAllRepairDetails();
        /*vehicle*/
        List<VehicleFeign> vehicles= vehiclesClient.getAllVehicles();
        List<VehicleTypeFeign> vehicleTypes=vehiclesClient.getAllVehicleTypes();

        List<R1Entity> entitiesR1=new ArrayList<>();

        /*now i iterate to get the logic of application*/
        for(RepairTypeFeign repairType:repairTypes){

            for (VehicleTypeFeign vehicleType:vehicleTypes){
                /*entity*/
                R1Entity r1Entity=new R1Entity();
                r1Entity.setRepair_type_name(repairType.getRepair_type_name());


                /*set the vehicle type_name*/

                r1Entity.setVehicle_type_name(vehicleType.getVehicle_type_name());
                Long total_repairs_type=0L;
                for(VehicleFeign vehicle:vehicles){
                    if (Objects.equals(vehicle.getVehicle_type_id(), vehicleType.getVehicle_type_id())){
                    total_repairs_type +=repairDetailClient.countAllRepairType(repairType.getRepair_type_id(),vehicle.getPatent(), month, year);
                        Long TotalMountOfRepair=repairDetailClient.getTotalMountOfARepairType(repairType.getRepair_type_id(), month, year);
                        r1Entity.setTotal_mount_repairs(TotalMountOfRepair);
                    }
                    else{
                    Long TotalMountOfRepair=0L;
                    r1Entity.setTotal_mount_repairs(TotalMountOfRepair);}
                }

                r1Entity.setVehicle_type_quantity(total_repairs_type);


                /*add to the array*/
                entitiesR1.add(r1Entity);



            }

        }
        //TODO: evaluar la posibilidad de guardar la informacion.
        return  entitiesR1;
    }



}