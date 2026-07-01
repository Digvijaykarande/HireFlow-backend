package HireFlow.hireFlowProject.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI hireFlowAPI() {

		return new OpenAPI()

				.info(new Info()

						.title("HireFlow API")

						.description("Modern Job Portal Backend built with Spring Boot")

						.version("1.0.0")

						.contact(new Contact()

								.name("Digvijay Karande")

								.email("karandedigvijay2@gmail.com"))

						.license(new License()

								.name("MIT License")))

				.externalDocs(new ExternalDocumentation()

						.description("HireFlow Documentation"));
	}
}