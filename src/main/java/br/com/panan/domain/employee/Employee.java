package br.com.panan.domain.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor //cria um construtor sem argumentos
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "the name is mandatory")
    private String name;

    @NotEmpty(message = "the code is mandatory")
    private String code;

    private Boolean active;

    private Integer noteTotal;

    private Integer surveyTotal;

}
