package br.com.diegodelmiro.scoobyapi.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CostumerRequest {
    private String email;
    private String password;
}
