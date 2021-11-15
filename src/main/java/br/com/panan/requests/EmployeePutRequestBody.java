package br.com.panan.requests;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
public class EmployeePutRequestBody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "the name is mandatory")
    private String name;

    @NotEmpty(message = "the code is mandatory")
    private String code;
}
