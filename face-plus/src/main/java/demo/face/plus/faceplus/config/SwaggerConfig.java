package demo.face.plus.faceplus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@Profile({"default", "dev", "test"})
public class SwaggerConfig {
}
