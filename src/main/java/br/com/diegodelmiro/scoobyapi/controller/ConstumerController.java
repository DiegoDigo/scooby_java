package br.com.diegodelmiro.scoobyapi.controller;

        import br.com.diegodelmiro.scoobyapi.models.reponse.CostumerResponse;
        import br.com.diegodelmiro.scoobyapi.models.request.CostumerRequest;
        import br.com.diegodelmiro.scoobyapi.service.CostumerService;
        import br.com.diegodelmiro.scoobyapi.util.JWTutil;
        import br.com.diegodelmiro.scoobyapi.util.PBKDF2Encoder;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class ConstumerController {

    @Autowired
    JWTutil jwTutil;

    @Autowired
    CostumerService costumerService;

    @Autowired
    PBKDF2Encoder passwordEncoder;

    @PostMapping("login")
    public Mono<ResponseEntity<CostumerResponse>> login(@RequestBody CostumerRequest costumer) {
        return costumerService.findByEmail(costumer.getEmail()).map((userDetails) -> {
            if (passwordEncoder.encode(costumer.getPassword()).equals(userDetails.getPassword())) {
                return ResponseEntity.ok(new CostumerResponse(jwTutil.generateToken(userDetails)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        });
    }
}
