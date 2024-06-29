package tingeso.ev2.reports_uh.ms.configurations;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() { return Logger.Level.FULL; }
}
