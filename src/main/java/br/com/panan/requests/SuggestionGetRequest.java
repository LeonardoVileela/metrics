package br.com.panan.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SuggestionGetRequest {

    @NotNull(message = "the suggestion is mandatory")
    private String suggestion;

    @NotNull(message = "the name is mandatory")
    private String name;

    @NotNull(message = "the code is mandatory")
    private String code;

    @NotNull(message = "the dateTime is mandatory")
    private LocalDate date;
}
