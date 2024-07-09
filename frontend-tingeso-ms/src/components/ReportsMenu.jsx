import { Button } from "@mui/material";
import { Link } from "react-router-dom";
function ReportsMenu() {

    return ( 
        <nav>
            <Button className="nav-item">
            <Link className="nav-link" to="/api/reports/report_1">
              Reporte 1
            </Link>
          </Button>
        </nav>
     );
}

export default ReportsMenu;