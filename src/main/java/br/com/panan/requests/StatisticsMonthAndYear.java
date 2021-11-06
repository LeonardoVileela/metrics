package br.com.panan.requests;

import lombok.Data;

@Data
public class StatisticsMonthAndYear {

    public StatisticsMonthAndYear(Double january, Double february, Double march, Double april, Double may, Double june, Double july, Double august, Double september, Double october, Double november, Double december) {
        this.january = january;
        this.february = february;
        this.march = march;
        this.april = april;
        this.may = may;
        this.june = june;
        this.july = july;
        this.august = august;
        this.september = september;
        this.october = october;
        this.november = november;
        this.december = december;
    }

    private Double january;
    private Double february;
    private Double march;
    private Double april;
    private Double may;
    private Double june;
    private Double july;
    private Double august;
    private Double september;
    private Double october;
    private Double november;
    private Double december;

}
