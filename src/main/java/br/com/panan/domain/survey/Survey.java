package br.com.panan.domain.survey;

import br.com.panan.domain.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor //cria um construtor sem argumentos
@Builder
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "the note is mandatory")
    private Integer note;

    @ManyToOne
    private Employee employee;

}
