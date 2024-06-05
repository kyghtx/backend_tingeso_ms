import './App.css'
import { Route,BrowserRouter,Routes } from 'react-router-dom'
import VehiclesList from './components/VehiclesList'
import CreateBrand from "./components/CreateBrand"
import BrandList from './components/BrandList'
import CreateVehicleType from './components/CreateVehicleType'
import CreateMotorType from './components/CreateMotorType'
import NavBar from './components/NavBar'
import Layout from './components/Layout'
import CreateVehicle from './components/CreateVehicle'

function App() {
  return (
    
    <BrowserRouter>
    <Routes>
      <Route path='/' element={<Layout/>}>
      <Route path='/api/vehicles' element={<CreateVehicle/>}/>
      <Route path='/api/vehicles/Brands' element={<CreateBrand/>}/>
      <Route path='/api/vehicles/Types' element={<CreateVehicleType/>}/>
      <Route path='/api/vehicles/MotorTypes' element={<CreateMotorType/>}/>
      </Route>
 
 
    </Routes>

    </BrowserRouter>
  )
}

export default App
