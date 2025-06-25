import axios from "axios";
const GATEWAY_URL= "gateway:8080";

const VEHICLES_API_URL = `http://${GATEWAY_URL}/api/vehicles`;
const BRAND_API_URL= `http://${GATEWAY_URL}/api/vehicles/brands`
const VEHICLE_TYPES_API_URL=`http://${GATEWAY_URL}/api/vehicles/types`
const VEHICLE_MOTORS_API_URL=`"http://${GATEWAY_URL}/api/vehicles/motor_types"`
//lo siguiente queda a revision por tema de configuracion con el Gateway!!
const REPAIRS_LIST_API_URL=`http://${GATEWAY_URL}/api/repairs_types`
const REPAIRS_LIST_MOTOR_TYPES=`http://${GATEWAY_URL}/api/repairs_types/repairs_prices/motor_types`
const REPAIRS_PRICES_LIST =`http://${GATEWAY_URL}/api/repairs_types/repairs_prices`
const VEHICLES_FROM_REPAIR=`http://${GATEWAY_URL}/api/repairs_vehicles/vehicles`
const RL_FROM_REPAIR_VEHICLES=`http://${GATEWAY_URL}/api/repairs_vehicles/repair_types`
const REPAIRS_VEHICLES=`http://${GATEWAY_URL}/api/repairs_vehicles`
/*referente a la gestion de vehiculos*/
function createVehicle(vehicle){
    return axios.post(VEHICLES_API_URL,vehicle);

}

function getVehicles(){
    return axios.get(VEHICLES_API_URL);
}

function createBrand(brand){
    return axios.post(BRAND_API_URL,brand);

}

function getBrands(){
    return axios.get(BRAND_API_URL);
}

function getVehicleTypes(){
    return axios.get(VEHICLE_TYPES_API_URL);
}
function createVehicleType(type){
    return axios.post(VEHICLE_TYPES_API_URL,type);
}
function getMotorTypes(){
    return axios.get(VEHICLE_MOTORS_API_URL);
}
function createMotorType(motorType){
    return axios.post(VEHICLE_MOTORS_API_URL,motorType);
}

/*Ahora lo de las lista de reparaciones*/
function getRepairList(){
    return axios.get(REPAIRS_LIST_API_URL);
}
function createRepairType(repair){
    return axios.post(REPAIRS_LIST_API_URL,repair);
}

function getMotorTypesFromRepairListMs(){
    return axios.get(REPAIRS_LIST_MOTOR_TYPES);

}
function getRepairPrices(){
    return axios.get(REPAIRS_PRICES_LIST);
}

function createRepairPrices(repairs){
    return axios.post(REPAIRS_PRICES_LIST,repairs);
}

function getVehiclesFromRepairVehicles(){
    return axios.get(VEHICLES_FROM_REPAIR);
}

function getRepairListFromRepairVehicles(){
    return axios.get(RL_FROM_REPAIR_VEHICLES);
}
function createRepairVehicles(repairsVehicles){
    console.table(repairsVehicles);
    return axios.post(REPAIRS_VEHICLES,repairsVehicles);
}
function getRepairsOfAVehicle(vehicle_id){
    const REPAIRS_VEHICLE=`http://${GATEWAY_URL}/api/repairs_vehicles/${vehicle_id}`;

    return axios.get(REPAIRS_VEHICLE);
}
function getRepairDetailsVehicle(vehicle_id){
    const REPAIRS_DETAILS=`http://${GATEWAY_URL}/api/repairs_vehicles/repair_details/${vehicle_id}`
    
    return axios.get(REPAIRS_DETAILS);
}
function updateRepairsState(repairs){
    return axios.put(REPAIRS_VEHICLES,repairs);
}
/*get method for report 1*/
function getReport1(month,year){
    const REPORT1_URL = `http://${GATEWAY_URL}/api/reports/report_1?month=${month}&year=${year}`;
    return axios.get(REPORT1_URL);
}
function getReport2(month){
    const REPORT2_URL = `http://${GATEWAY_URL}/api/reports/report_2?month=${month}`;
    return axios.get(REPORT2_URL);
}
export default {getVehicles,createBrand,getBrands,getVehicleTypes,
    createVehicleType,getMotorTypes,createMotorType,createVehicle,
    getRepairList,createRepairType,getMotorTypesFromRepairListMs,
getRepairPrices,createRepairPrices,getVehiclesFromRepairVehicles,getRepairListFromRepairVehicles,
createRepairVehicles, getRepairsOfAVehicle,getRepairDetailsVehicle,updateRepairsState, getReport1 , getReport2}