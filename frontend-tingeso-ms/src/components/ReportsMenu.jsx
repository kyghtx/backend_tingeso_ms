import { Box, Button } from "@mui/material";
import { Link } from "react-router-dom";
function ReportsMenu() {

    return ( 
        <Box sx={{justifyContent:"space-around",display:"flex", alignContent:"center", width:"100%", height:"50%"}}>
            <Button className="Box-item" variant="contained" color="secondary">
            <Link className="nav-link" to="/api/reports/report_1">
              Reporte 1
            </Link>
          </Button>
          <Button className="nav-item"  variant="contained" color="secondary">
            <Link className="nav-link" to="/api/reports/report_2">
              Reporte 2
            </Link>
          </Button>
        </Box>
     );
}

export default ReportsMenu;