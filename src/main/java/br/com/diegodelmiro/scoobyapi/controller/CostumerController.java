package br.com.diegodelmiro.scoobyapi.controller;

import br.com.diegodelmiro.scoobyapi.models.Costumer;
import br.com.diegodelmiro.scoobyapi.models.enums.Role;
import br.com.diegodelmiro.scoobyapi.models.reponse.CostumerResponse;
import br.com.diegodelmiro.scoobyapi.models.reponse.MessageResponse;
import br.com.diegodelmiro.scoobyapi.models.request.CostumerLoginRequest;
import br.com.diegodelmiro.scoobyapi.models.request.CostumerRegisterRequest;
import br.com.diegodelmiro.scoobyapi.service.CostumerService;
import br.com.diegodelmiro.scoobyapi.util.JWTutil;
import br.com.diegodelmiro.scoobyapi.util.PBKDF2Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("user")
public class CostumerController {

    private final JWTutil jwTutil;

    private final CostumerService costumerService;

    private final PBKDF2Encoder passwordEncoder;

    @Autowired
    public CostumerController(JWTutil jwTutil, CostumerService costumerService, PBKDF2Encoder passwordEncoder) {
        this.jwTutil = jwTutil;
        this.costumerService = costumerService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("login")
    public Mono<ResponseEntity<CostumerResponse>> login(@RequestBody CostumerLoginRequest costumer) {
        return costumerService.findByEmail(costumer.getEmail()).map((userDetails) -> {
            if (passwordEncoder.encode(costumer.getPassword()).equals(userDetails.getPassword())) {
                return ResponseEntity.ok(new CostumerResponse(jwTutil.generateToken(userDetails)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        });
    }

    @PostMapping("register")
    public Mono<ResponseEntity<Costumer>> register(@RequestBody @Valid  CostumerRegisterRequest newCostumer) {

        return this.costumerService.save(new Costumer(newCostumer.getUsername(), passwordEncoder.encode(newCostumer.getPassword()),
                newCostumer.getEmail(), true, Collections.singletonList(Role.ROLE_COSTUMER)))
                .map(savedHotel -> new ResponseEntity<>(savedHotel, HttpStatus.CREATED));


    }
}

