package br.com.panan.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeAllActiveGetRequest {

    public EmployeeAllActiveGetRequest(String name, String code, Double average) {
        this.name = name;
        this.code = code;
        this.average = average;
    }

    private String name;

    private String code;

    private Double average;
}
