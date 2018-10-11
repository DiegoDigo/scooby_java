package br.com.diegodelmiro.scoobyapi.repository;

import br.com.diegodelmiro.scoobyapi.models.Costumer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CostumerReposity extends ReactiveMongoRepository<Costumer, String> {

    Mono<Costumer> findByEmail(String email);
}
