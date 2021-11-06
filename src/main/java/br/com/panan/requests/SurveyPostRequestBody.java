package br.com.panan.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SurveyPostRequestBody {

    @NotNull(message = "the note is mandatory")
    private Integer note;

    @NotNull(message = "the idEmployee is mandatory")
    private String code;

}
