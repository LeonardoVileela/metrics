package br.com.panan.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GeneralStatisticsGetRequestBodyYear {

    public GeneralStatisticsGetRequestBodyYear(GeneralStatisticsGetRequestBody generalStatisticsGetRequestBody, StatisticsMonthAndYear statisticsMonthAndYear) {
        this.generalStatisticsGetRequestBody = generalStatisticsGetRequestBody;
        this.statisticsMonthAndYear = statisticsMonthAndYear;
    }

    @NotNull(message = "the GeneralStatisticsGetRequestBody is mandatory")
    private GeneralStatisticsGetRequestBody generalStatisticsGetRequestBody;

    @NotNull(message = "the statisticsMonthAndYear is mandatory")
    private StatisticsMonthAndYear statisticsMonthAndYear;


}
