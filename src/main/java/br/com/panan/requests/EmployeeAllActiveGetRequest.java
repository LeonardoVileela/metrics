package br.com.panan.requests;

import lombok.Data;

@Data
public class EmployeeAllActiveGetRequest implements Comparable<EmployeeAllActiveGetRequest> {
    public EmployeeAllActiveGetRequest(String name, String code, Double average) {

        this.name = name;
        this.code = code;
        this.average  = average ;
    }

    private String name;

    private String code;

    private Double average ;

    @Override
    public int compareTo(EmployeeAllActiveGetRequest e) {
        return this.getAverage().compareTo(e.getAverage());
    }
}
