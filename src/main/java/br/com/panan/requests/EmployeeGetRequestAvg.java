package br.com.panan.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeGetRequestAvg {

    public EmployeeGetRequestAvg(Double avg) {
        this.avg = avg;
    }

    private Double avg;

}
