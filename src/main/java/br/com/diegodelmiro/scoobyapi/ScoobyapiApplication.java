package br.com.diegodelmiro.scoobyapi;

import br.com.diegodelmiro.scoobyapi.models.Costumer;
import br.com.diegodelmiro.scoobyapi.models.enums.Role;
import br.com.diegodelmiro.scoobyapi.repository.CostumerReposity;
import br.com.diegodelmiro.scoobyapi.service.CostumerService;
import br.com.diegodelmiro.scoobyapi.util.JWTutil;
import br.com.diegodelmiro.scoobyapi.util.PBKDF2Encoder;
import com.mongodb.connection.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.function.Function;

@SpringBootApplication
public class ScoobyapiApplication {

    @Bean
    CommandLineRunner createUser(CostumerService costumerService, PBKDF2Encoder passwordEncoder) {
        return args -> costumerService.deleteAll()
                .subscribe(null, null, () ->
                        costumerService.save(new Costumer("Admin", passwordEncoder.encode("admin"),
                                "admin@gmail.com", true, Collections.singletonList(Role.ROLE_ADMINISTRATOR)))
                                .subscribe(user -> System.out.println(user.getUsername())));
    }

    public static void main(String[] args) {
        SpringApplication.run(ScoobyapiApplication.class, args);
    }
}
