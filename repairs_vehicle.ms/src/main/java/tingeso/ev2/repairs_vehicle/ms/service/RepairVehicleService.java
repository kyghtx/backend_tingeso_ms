package tingeso.ev2.repairs_vehicle.ms.service;

import tingeso.ev2.repairs_vehicle.ms.client.RepairTypesFeign;
import tingeso.ev2.repairs_vehicle.ms.client.VehiclesClient;
import tingeso.ev2.repairs_vehicle.ms.dto.RepairVehicleDTO;
import tingeso.ev2.repairs_vehicle.ms.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.ev2.repairs_vehicle.ms.repositories.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RepairVehicleService {
    RepairTypesFeign repairTypesFeign;
    VehiclesClient vehicleClient;
    @Autowired
    public RepairVehicleService(VehiclesClient vehicleClient, RepairTypesFeign repairTypesFeign){
        this.vehicleClient = vehicleClient;
        this.repairTypesFeign = repairTypesFeign;
    }

    @Autowired
    RepairVehicleRepository repairVehicleRepository;
    @Autowired
    RepairDetailRepository repairDetailRepository;
    @Autowired
    RepairDiscountRepository repairDiscountRepository;
    @Autowired
    KmSurchargeRepository kmSurchargeRepository;
    @Autowired
    AntiquitySurchargeRepository antiquitySurchargeRepository;

    public List<RepairVehicleEntity> getAllRepairVehicles(){
        return repairVehicleRepository.findAllRepairVehicles();
    }
    public List<RepairDetailEntity> getAllRepairDetails(){
        return repairDetailRepository.findAllRepairDetails();
    }

    public List<VehicleFeign> getVehiclesForRepairVehiclesMS(){
        return vehicleClient.getAllVehicles().getBody();
    }
    public List<RepairTypeFeign> getRepairTypes(){
        return repairTypesFeign.getRepairTypes().getBody();
    }
    /*to get repairs of a vehicle*/
    public List<RepairVehicleEntity> getAllRepairsOfAVehicle(Long vehicleId){
        return repairVehicleRepository.findRepairsByVehicleId(vehicleId);
    }
    /*Patent*/
    //get details of a repair
    public List<RepairDetailEntity> getAllRepairDetailsOfRepair(Long repair_vehicle_id){
        return repairDetailRepository.findAllDetailsOfRepairVehicle(repair_vehicle_id);
    }
    public Long sumAllRepairOfAType(Long repair_type_id){
        return repairDetailRepository.sumAllRepairsOfAType(repair_type_id);
    }
    /*a method to compare strings, to get the kmSurchargeRange an AntiquitySurchargeRange.*/
    public String calculateKmRange(VehicleFeign vehicle) {
        Long km = vehicle.getKm_vehicle();
        if (km >= 0 && km <= 5000) {
            return "0-5000";
        }
        if (km >= 5001 && km <= 12000) {
            return "5001-12000";
        }
        if (km >= 12001 && km <= 25000) {
            return "12001-25000";

        }
        if (km >= 25001 && km < 40000) {
            return "25001-40000";
        }
        if (km >= 40000) {
            return "40000-mas";
        }
        return "error";

    }

    /*method to get te antiquity of a vehicle*/

    public String calculateAntiquityRange(VehicleFeign vehicle) {
        int year_of_manu = vehicle.getYear_of_manufacturing();
        LocalDate today = LocalDate.now();
        int actualYear = today.getYear();

        /* la antiguedad es en relación a la fecha actual */
        int antiquity = actualYear - year_of_manu;

        /* ahora calculo el string de antiguedad */
        if (antiquity >= 0 && antiquity <= 5) {
            return "0-5";
        }
        if (antiquity >= 6 && antiquity <= 10) {
            return "6-10";
        }
        if (antiquity >= 11 && antiquity <= 15) {
            return "11-15";
        }
        if (antiquity >= 16) {
            return "16-mas";
        }
        return "error";
    }

    public Long getTotalMountOfARepairType(Long repair_type_id, int mes, int year,String patent){
        return repairDetailRepository.sumAllPricesOfARepairType(repair_type_id, mes, year, patent);
    }
    public Long countAllRepairsOfAtype(Long repair_type_id,String patent, int month, int year){
        return repairDetailRepository.sumAllRepairsOfATypOfAVehicle(repair_type_id,patent, month, year);
    }
    public Long SumAllRepairsOfATypeFromAMonth(Long repair_type_id, int month, int year){
        return repairDetailRepository.sumAllRepairsOfATypeAndAMonth(repair_type_id, month, year);
    }
    public Long countAllRepairsOfATypeFromAMonth(Long repair_type_id, int month, int year){
        return repairDetailRepository.countAllRepairsOfATypeOfAVehicleForAMonth(repair_type_id, month, year);
    }



    public String calculateRepairDiscountRange(Long cantidadReparaciones){
        if (cantidadReparaciones == 0){return "0";}
        if (cantidadReparaciones >= 1 && cantidadReparaciones <= 2){ return "1-2";}
        if (cantidadReparaciones >= 3 && cantidadReparaciones <= 5){return "3-5";}
        if (cantidadReparaciones >= 6 && cantidadReparaciones <= 9){return  "6-9";}
        if (cantidadReparaciones >= 10){return "10-mas";}

        return "0";
    }
    public List<RepairVehicleEntity> getUnfinishedRepairsOfAVehicle(Long vehicleId){

        return repairVehicleRepository.findUnfinishedRepairByVehicle(vehicleId);
    }

    /*method to update to state=1 a repair (finish)*/
    public String finishRepairVehicle(List<RepairVehicleEntity> repairVehicleEntity){
        /*set the new values*/
        for (RepairVehicleEntity repairVehicle : repairVehicleEntity) {
            repairVehicle.setState(1);
            repairVehicle.setVehicle_outcome(LocalDate.now());
            repairVehicle.setVehicle_outcome_hour(LocalTime.now());
            repairVehicleRepository.save(repairVehicle);
        }
        /*this method will update a state of repairs.*/
        return "Se finalizan las reparaciones de forma exitosa";
    }

    /*methos to update the date when the client retire his vehicle*/
    public String clientRetiresVehicle(List<RepairVehicleEntity> repairVehicleEntity){
        for (RepairVehicleEntity repairVehicle : repairVehicleEntity) {
            repairVehicle.setVehicle_client_retire(LocalDate.now());
            repairVehicle.setVehicle_client_retire_hour(LocalTime.now());
            repairVehicleRepository.save(repairVehicle);
        }
        return "Cliente retira el vehiculo exitosamente";
    }
    //TODO: implements of bonus
    public String ExistBonus(VehicleFeign vehicle){
        /*method to know if exist a bonus for the brand of the vehicle.*/
        /*Brand of the vehicle*/
        Long id_brand = vehicle.getBrand_id();
       /*name of the brand of the brand entity repository of  vehicle.ms */
        return "Bono usado con éxito.";

    }

    public boolean discountDay(){
        // Obtener la fecha y hora actual
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        // Obtener el día de la semana
        DayOfWeek diaSemana = fechaActual.getDayOfWeek();

        // Verificar si es lunes o jueves y está entre las 09:00 y las 12:00 hrs
        if ((diaSemana == DayOfWeek.MONDAY || diaSemana == DayOfWeek.THURSDAY) &&
                horaActual.isAfter(LocalTime.of(9, 0)) &&
                horaActual.isBefore(LocalTime.of(12, 0))) {
            // Aplicar descuento del 10%
            return true;
        } else {
           return false;
        }

    }
    public String saveRepairAndDetails(List<RepairVehicleDTO> newsVehicleRepair) {

        RepairVehicleEntity newRepair = new RepairVehicleEntity();
        /*for*/
        /*I need multiply details... one for each repair?*/
        //fetch of vehicle to assign repair
        VehicleFeign vehicle = vehicleClient.getVehicle(newsVehicleRepair.get(0).getVehicle_id()).getBody();

        if (vehicle != null) {
            Long TotalPriceOfRepairs= 0L;
            /*Array of RepairDetailEntity*/
            ArrayList<RepairDetailEntity> RepairDetailsToSave = new ArrayList<>();

            /*Rango de Km del vehiculo*/
            String KmRange = calculateKmRange(vehicle);
            /*Antiguedad el vehiculo, rango de String*/
            String AntiquityRange = calculateAntiquityRange(vehicle);


            //set values for repair---------------------------------------
            newRepair.setVehicle_id(vehicle.getVehicle_id());
            newRepair.setVehicle_income(LocalDate.now());
            newRepair.setVehicle_income_hour(LocalTime.now());
            //"Cada vez que deseo ingresar una reparacion. esta debe estar con un estado no finalizado"
            newRepair.setState(0); // initially state 0
            /*RECARGOS PARA EL VEHICULO*/

            /*Recargo por kilometraje*/

            float recargo_km=kmSurchargeRepository.getKmSurchargeValue(vehicle.getVehicle_type_id(),KmRange).getKm_surcharge_value();
            /*Recargo por antiguedad*/

            float recargo_antiguedad=antiquitySurchargeRepository.getAntiquitySurcharge(vehicle.getVehicle_type_id(),AntiquityRange).getAntiquity_surcharge_value();

            /*Descuentos por reparaciones*/
            Long QuantityOfRepairs= repairVehicleRepository.countRepairsFromAVehicleLast12Months(vehicle.getVehicle_id(), LocalDate.now().minusMonths(12));
            String RepairDiscountRange=  calculateRepairDiscountRange(QuantityOfRepairs);
            double repairsDiscount;
            if(RepairDiscountRange.equals("0")){
                repairsDiscount = 0;
            }else{
            repairsDiscount= repairDiscountRepository.getRepairDiscount(QuantityOfRepairs,RepairDiscountRange).getRepair_discount();
            }

            /*descuento por bonos si aplica (solo un bono por reparacion.)*/

            /*descuento por dia de atencion.*/

            for (int i = 0; i < newsVehicleRepair.size(); i++) {
                /*for each DTO, i build a Details of repairs*/
                RepairDetailEntity repairDetail = new RepairDetailEntity();
                repairDetail.setPatent(vehicle.getPatent());
                repairDetail.setRepair_type_id(newsVehicleRepair.get(i).getRepair_type_id());
                repairDetail.setRepair_date(LocalDate.now());
                repairDetail.setRepair_time(LocalTime.now());
                /*Now i'll set the price of repair*/
                Long repairPrice= Objects.requireNonNull(repairTypesFeign.getRepairPrice(vehicle.getMotor_type_id(), newsVehicleRepair.get(i).getRepair_type_id()).getBody()).getPrice();
                /*increment the total cost of repairs*/
                TotalPriceOfRepairs = TotalPriceOfRepairs + repairPrice;

                /*now i instance*/
                repairDetail.setPrice(repairPrice);

                /*With that values, i can save in an arrayList previously to save in db*/
                RepairDetailsToSave.add(i,repairDetail);
            }
            /*i save all repairDetailEntities*/

            /*now i create te RepairVehicleEntity with all required data*/

            newRepair.setTotal_price_repairs(TotalPriceOfRepairs);

            /*now i calculate the surcharges mount*/
            double SurchargesMount = TotalPriceOfRepairs*recargo_km + TotalPriceOfRepairs*recargo_antiguedad;
            newRepair.setSurcharges_mount(SurchargesMount);

            /*DISCOUNTS*/
            double DiscountMount = TotalPriceOfRepairs * repairsDiscount;
            if (discountDay()){
                DiscountMount = DiscountMount + TotalPriceOfRepairs * 0.1;
            }

            /*TotalCost*/
            double totalCostWithoutIVA = TotalPriceOfRepairs + SurchargesMount - DiscountMount;
            double IVAmount=totalCostWithoutIVA*0.19;
            double totalCostWithIVA=IVAmount + totalCostWithoutIVA;


            newRepair.setTotal_cost(totalCostWithIVA);
            newRepair.setIva_mount(IVAmount);
            newRepair.setDiscounts_mount(DiscountMount);

            /*Si aplica bono */
            //TODO: ver implementacion del bono
            /*now i set the values...*/

            /*Finally, i can save the repair.*/
            repairVehicleRepository.save(newRepair);
            for (RepairDetailEntity repairDetail : RepairDetailsToSave) {
                repairDetail.setRepair_vehicle_id(newRepair.getRepair_vehicle_id());
                repairDetail.setVehicle_id(vehicle.getVehicle_id());

            }
            repairDetailRepository.saveAll(RepairDetailsToSave); /*All the details of the repair */

            return newRepair.toString();


        }

        return  "No se pudo crear la reparacion";


    }
    public List<RepairDetailEntity> getDetailsOfARepair(Long repair_vehicle_id){
        return repairDetailRepository.findAllDetailsOfRepairVehicle(repair_vehicle_id);

    }




}
