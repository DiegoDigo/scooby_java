package br.com.diegodelmiro.scoobyapi.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostumerRegisterRequest {

    @NotNull
    private String email;
    @Null
    private String username;
    @NotNull
    @Size(min = 8, max = 15)
    private String password;

}
