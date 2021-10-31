package br.com.panan.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeAllActiveGetRequest {

    @NotEmpty(message = "the name is mandatory")
    private String name;

    @NotEmpty(message = "the code is mandatory")
    private String code;

    @NotEmpty(message = "the average is mandatory")
    private Double average;
}
