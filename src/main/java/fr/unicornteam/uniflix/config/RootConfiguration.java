package fr.unicornteam.uniflix.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:webSecurityConfig.xml"})
public class RootConfiguration {
    public RootConfiguration() {
        super();
    }
}
