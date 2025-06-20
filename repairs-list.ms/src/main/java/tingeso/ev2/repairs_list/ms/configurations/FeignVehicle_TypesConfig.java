package tingeso.ev2.repairs_list.ms.configurations;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class FeignVehicle_TypesConfig {
    @Bean
    Logger.Level feignLoggerLevel() { return Logger.Level.FULL; }

}
