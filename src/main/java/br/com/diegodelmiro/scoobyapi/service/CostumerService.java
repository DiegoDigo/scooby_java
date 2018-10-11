package br.com.diegodelmiro.scoobyapi.service;

import br.com.diegodelmiro.scoobyapi.models.Costumer;
import br.com.diegodelmiro.scoobyapi.repository.CostumerReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CostumerService {

    @Autowired
    CostumerReposity costumerReposity;

    public Mono<Costumer> findByEmail(String email) {
        return costumerReposity.findByEmail(email);
    }

    public Mono<Void> deleteAll() {
        return costumerReposity.deleteAll();
    }


    public Mono<Costumer> save(Costumer costumer) {
        return costumerReposity.save(costumer);
    }
}
