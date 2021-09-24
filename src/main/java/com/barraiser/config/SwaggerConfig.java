package com.barraiser.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.barraiser.BarraiserApplication;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(
            RequestHandlerSelectors.basePackage(BarraiserApplication.class.getPackage().getName()))
        .paths(PathSelectors.any()).build().apiInfo(generateApiInfo());
  }

  private ApiInfo generateApiInfo() {
    ApiInfo apiInfo = new ApiInfo("barraiser", "barraiser", "1.0.0-RC1", "toc",
        new Contact("team", "", "bla@bla.com"), "Apache 2.0",
        "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());

    return apiInfo;
  }


}
