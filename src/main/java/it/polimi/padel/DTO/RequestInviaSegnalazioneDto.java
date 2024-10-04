package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestInviaSegnalazioneDto {
    @NotBlank
    @NotNull
    private String idChat;

    @NotBlank
    @NotNull
    private String idMessaggio;
}
