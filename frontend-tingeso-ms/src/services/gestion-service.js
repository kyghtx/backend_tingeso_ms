import axios from "axios";
const VEHICLES_API_URL = "http://localhost:8080/api/vehicles"
const BRAND_API_URL= "http://localhost:8080/api/vehicles/brands"
const VEHICLE_TYPES_API_URL="http://localhost:8080/api/vehicles/types"
const VEHICLE_MOTORS_API_URL="http://localhost:8080/api/vehicles/motor_types"
//lo siguiente queda a revision por tema de configuracion con el Gateway!!
const REPAIRS_LIST_API_URL="http://localhost:8091/api/repairs_types"

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

export default {getVehicles,createBrand,getBrands,getVehicleTypes,
    createVehicleType,getMotorTypes,createMotorType,createVehicle,
    getRepairList,createRepairType}