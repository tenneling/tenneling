package com.tenneling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //配置类
@EnableSwagger2// 开启Swagger2的自动配置
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("tenneling APIs")
                        .description("我束缚自己，就像别人放纵自己一样正常，对于这种自我的严厉要求，并没有让我觉得厌烦，反而感到高兴。我对以后的追求，其实不是幸福本身，而是在赢取幸福的过程中我所花费的无限努力")
                        .version("1.0")
                        .build())
                .groupName("v1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tenneling.controller"))
                .build() ;
    }

}