package mai_ocean.robot_data_transfer.system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI customAPI(){
        return new OpenAPI()
                .info(new Info().title("Robot Data Transfer")
                    .version("1.0")
                    .description("Api responsable to manage the boat comunication and persistence with the command central")
                    .license(new License()
                            .name("License 1.0")
                            .url("license_1.0")));
    }
}
