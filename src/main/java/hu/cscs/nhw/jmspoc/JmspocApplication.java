package hu.cscs.nhw.jmspoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.EnableIntegrationManagement;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableJms
@EnableIntegrationManagement
@EnableIntegration
@EnableAsync
public class JmspocApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmspocApplication.class, args);
    }

}
