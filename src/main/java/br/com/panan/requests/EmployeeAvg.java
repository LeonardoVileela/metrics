package br.com.panan.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeAvg {

    public EmployeeAvg(String name, String code, Integer noteTotal, Integer surveyTotal) {
        this.name = name;
        this.code = code;
        this.noteTotal = noteTotal;
        this.surveyTotal = surveyTotal;
    }

    private String name;

    private String code;

    private Integer noteTotal;

    private Integer surveyTotal;
}
