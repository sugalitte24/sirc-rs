package co.gov.movilidadbogota.sircrs.configuration;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    public Contract useFeignAnnotations() {
        return new Contract.Default();
    }
}
