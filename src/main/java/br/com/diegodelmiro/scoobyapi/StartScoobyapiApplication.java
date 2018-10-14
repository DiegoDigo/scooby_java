package br.com.diegodelmiro.scoobyapi;

import br.com.diegodelmiro.scoobyapi.models.Costumer;
import br.com.diegodelmiro.scoobyapi.models.enums.Role;
import br.com.diegodelmiro.scoobyapi.service.CostumerService;
import br.com.diegodelmiro.scoobyapi.util.PBKDF2Encoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class StartScoobyapiApplication {

    @Bean
    CommandLineRunner createUser(CostumerService costumerService, PBKDF2Encoder passwordEncoder) {
        return args -> costumerService.delete()
                .subscribe(null, null, () ->
                        costumerService.save(new Costumer("Admin", passwordEncoder.encode("admin"),
                                "admin@gmail.com", true, Collections.singletonList(Role.ROLE_ADMINISTRATOR)))
                                .subscribe(user -> System.out.println(user.getUsername())));
    }

    public static void main(String[] args) {
        SpringApplication.run(StartScoobyapiApplication.class, args);
    }
}
