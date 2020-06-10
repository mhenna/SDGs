package com.techdev.sdg.Utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
class webConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            //TODO : Filter allowed origins [Security]
            registry.addMapping("/*/*")
                    .allowedOrigins("*")
                    .allowedMethods("PUT", "DELETE","POST","GET")
                    .allowCredentials(true).maxAge(3600);
        }
    }


