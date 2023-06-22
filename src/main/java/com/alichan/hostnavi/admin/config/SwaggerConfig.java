package com.alichan.hostnavi.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
  @Bean
  public Docket createAPIDocket() {
    return new Docket(DocumentationType.OAS_30).useDefaultResponseMessages(false).apiInfo(apiInfo())
        .select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("HostNaviAPI")
        .description("HostNavi内で使用されるデータのCRUD処理ができるAPI").version("1.0").build();
  }
}
