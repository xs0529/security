package com.xs.example.demo.system;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 *
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2019-01-28
 */
//swagger-bootstrap-ui默认访问地址是：http://${host}:${port}/doc.html
@Configuration
/*@ConditionalOnProperty(name ="enabled" ,prefix = "swagger",havingValue = "true")*/
@EnableSwaggerBootstrapUI
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createPlanApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.xs.example.demo.permission.user.rest"))
                .apis(RequestHandlerSelectors.basePackage("com.xs.example.demo.web_common.base"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("主要服务接口文档")
                .description("swagger-bootstrap-ui")
                .contact(new Contact("xxx","xxx","xxx"))
                .termsOfServiceUrl("http://localhost:8999/")
                .version("1.0")
                .build();
    }
}
