package tingeso.ev2.repairs_list.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RepairsListApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepairsListApplication.class, args);
	}

}
