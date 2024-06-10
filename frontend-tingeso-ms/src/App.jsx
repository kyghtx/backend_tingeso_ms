import './App.css'
import { Route,BrowserRouter,Routes } from 'react-router-dom'
import CreateBrand from "./components/CreateBrand"
import CreateVehicleType from './components/CreateVehicleType'
import CreateMotorType from './components/CreateMotorType'
import Layout from './components/Layout'
import CreateVehicle from './components/CreateVehicle'
import CreateRepairType from './components/CreateRepairType'
import CreateRepairPrices from './components/CreateRepairPrices'

function App() {
  return (
    
    <BrowserRouter>
    <Routes>
      <Route path='/' element={<Layout/>}>
      <Route path='/api/vehicles' element={<CreateVehicle/>}/>
      <Route path='/api/vehicles/Brands' element={<CreateBrand/>}/>
      <Route path='/api/vehicles/Types' element={<CreateVehicleType/>}/>
      <Route path='/api/vehicles/MotorTypes' element={<CreateMotorType/>}/>
      <Route path='/api/repairs_types' element={<CreateRepairType/>}/>
      <Route path='/api/repairs_types/repairs_prices' element={<CreateRepairPrices/>}/>

      </Route>
 
 
    </Routes>

    </BrowserRouter>
  )
}

export default App
