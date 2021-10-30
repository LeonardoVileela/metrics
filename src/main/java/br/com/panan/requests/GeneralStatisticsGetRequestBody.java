package br.com.panan.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GeneralStatisticsGetRequestBody {

    public GeneralStatisticsGetRequestBody(Integer excellent, Integer veryGood, Integer good, Integer reasonable, Integer terrible) {
        this.excellent = excellent;
        this.veryGood = veryGood;
        this.good = good;
        this.reasonable = reasonable;
        this.terrible = terrible;
    }

    @NotNull(message = "the excellent is mandatory")
    private Integer excellent;

    @NotNull(message = "the excellent is mandatory")
    private Integer veryGood;

    @NotNull(message = "the excellent is mandatory")
    private Integer good;

    @NotNull(message = "the excellent is mandatory")
    private Integer reasonable;

    @NotNull(message = "the excellent is mandatory")
    private Integer terrible;


}
