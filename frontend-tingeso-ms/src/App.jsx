import "./App.css";
import { Route, BrowserRouter, Routes } from "react-router-dom";
import CreateBrand from "./components/CreateBrand";
import CreateVehicleType from "./components/CreateVehicleType";
import CreateMotorType from "./components/CreateMotorType";
import Layout from "./components/Layout";
import CreateVehicle from "./components/CreateVehicle";
import CreateRepairType from "./components/CreateRepairType";
import CreateRepairPrices from "./components/CreateRepairPrices";
import CreateRepairVehicle from "./components/CreateRepairVehicle";
import CompletedRepairsVehicles from "./components/CompletedRepairsVehicle";
import { useState } from "react";
import ReportsMenu from "./components/ReportsMenu";
import Report1 from "./components/Report1";
import Report2 from "./components/Report2";
function App() {
  return (
   
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="/api/vehicles" element={<CreateVehicle />} />
          <Route path="/api/vehicles/Brands" element={<CreateBrand />} />
          <Route path="/api/vehicles/Types" element={<CreateVehicleType />} />
          <Route
            path="/api/vehicles/MotorTypes"
            element={<CreateMotorType />}
          />
          <Route path="/api/repairs_types" element={<CreateRepairType />} />
          <Route
            path="/api/repairs_types/repairs_prices"
            element={<CreateRepairPrices />}
          />
          <Route
            path="/api/repair_vehicles/create"
            element={<CreateRepairVehicle />}
          />
          <Route
            path="/api/repair_vehicles/:vehicle_id"
            element={<CompletedRepairsVehicles/>}
          />
           <Route path="api/reports" element={<ReportsMenu/>}></Route>
           <Route path="api/reports/report_1" element={<Report1/>}></Route>
           <Route path="api/reports/report_2" element={<Report2/>}></Route>
        
        </Route>
       
      </Routes>
    </BrowserRouter>
  );
}

export default App;
