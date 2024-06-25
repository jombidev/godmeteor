package dev.jombi.godmeteor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GodMeteorApplication {

    public static void main(String[] args) { SpringApplication.run(GodMeteorApplication.class, args);
    }

}
