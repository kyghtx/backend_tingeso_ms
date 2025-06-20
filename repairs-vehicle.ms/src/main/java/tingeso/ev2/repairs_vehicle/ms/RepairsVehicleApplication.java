package tingeso.ev2.repairs_vehicle.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RepairsVehicleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepairsVehicleApplication.class, args);
	}

}
