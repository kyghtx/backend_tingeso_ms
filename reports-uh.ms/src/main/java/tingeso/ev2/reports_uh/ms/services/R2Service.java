package tingeso.ev2.reports_uh.ms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.ev2.reports_uh.ms.client.RepairDetailClient;
import tingeso.ev2.reports_uh.ms.client.RepairTypeClient;
import tingeso.ev2.reports_uh.ms.client.VehiclesClient;
import tingeso.ev2.reports_uh.ms.entities.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class R2Service {
    RepairDetailClient repairDetailClient;
    RepairTypeClient repairTypeClient;
    VehiclesClient vehiclesClient;
    @Autowired
    public R2Service(RepairDetailClient repairDetailClient, RepairTypeClient repairTypeClient, VehiclesClient vehiclesClient ){
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
    public List<VehicleTypeFeign> getAllVehicleTypes(){
        return vehiclesClient.getAllVehicleTypes();
    }


    public List<R2Entity> buildR2Entities(int Month){
        List<RepairTypeFeign> repairTypes = repairTypeClient.getRepairTypes();
        /*define the months on String*/
        List<MonthEntity> months = new ArrayList<>();
       months.add(new MonthEntity(1,"Enero"));
       months.add(new MonthEntity(2,"Febrero"));
       months.add(new MonthEntity(3,"Marzo"));
       months.add(new MonthEntity(4,"Abril"));
       months.add(new MonthEntity(5,"Mayo"));
       months.add(new MonthEntity(6,"Junio"));
       months.add(new MonthEntity(7,"Julio"));
       months.add(new MonthEntity(8,"Agosto"));
       months.add(new MonthEntity(9,"Septiembre"));
       months.add(new MonthEntity(10,"Octubre"));
       months.add(new MonthEntity(11,"Noviembre"));
       months.add(new MonthEntity(12,"Diciembre"));


        List<R2Entity> r2Entities = new ArrayList<>();

        /*this method is for build the entities for the report 2*/
        for(RepairTypeFeign repairType : repairTypes){
            /*for each repairType*/


            for(MonthEntity month : months){
                R2Entity r2Entity = new R2Entity();
                r2Entity.setRepair_type_total_mount(0L);

                r2Entity.setRepair_total_quantity(0L);

                //Set repair name
                r2Entity.setRepair_type_name(repairType.getRepair_type_name());
                //Set month id
                r2Entity.setMonth(month.getMonth());

                /*Total mount of a repair type on a month on the actual year*/

                Long totalMountOfRepairType = 0L;
                if (repairDetailClient.sumAllRepairTypeOnAMonth(repairType.getRepair_type_id(), month.getMonth(), Year.now().getValue()) != null){
                    totalMountOfRepairType = repairDetailClient.sumAllRepairTypeOnAMonth(repairType.getRepair_type_id(), month.getMonth(), Year.now().getValue());
                    r2Entity.setRepair_type_total_mount(totalMountOfRepairType);

                }
                Long totalOfRepairType = 0L;
                if (repairDetailClient.countAllRepairTypeOnAMonth(repairType.getRepair_type_id(), month.getMonth(), Year.now().getValue()) != null){
                    totalOfRepairType = repairDetailClient.countAllRepairTypeOnAMonth(repairType.getRepair_type_id(), month.getMonth(), Year.now().getValue());
                    r2Entity.setRepair_total_quantity(totalOfRepairType);
                }

                //set the total
                r2Entity.setRepair_type_total_mount(totalMountOfRepairType);
                r2Entity.setMonth_name(month.getMonth_name());
                r2Entities.add(r2Entity);

                /*add to te array*/

                

            }


        }


        return r2Entities;

    }


}
