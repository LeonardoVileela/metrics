package br.com.panan.domain.survey;

import br.com.panan.domain.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    @Max(5)
    @Min(1)
    private Integer note;

    @ManyToOne
    private Employee employee;

    @NotNull(message = "the dateTime is mandatory")
    private LocalDate date;

    @NotNull(message = "the hour is mandatory")
    private String hour;

    @Column(name = "suggestion", columnDefinition = "LONGTEXT")
    private String suggestion;
}
