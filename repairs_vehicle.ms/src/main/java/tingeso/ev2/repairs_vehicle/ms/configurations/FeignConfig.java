package tingeso.ev2.repairs_vehicle.ms.configurations;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() { return Logger.Level.FULL; }
}
