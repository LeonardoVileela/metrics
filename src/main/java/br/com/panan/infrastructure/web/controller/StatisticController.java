package br.com.panan.infrastructure.web.controller;

import br.com.panan.requests.GeneralStatisticsGetRequestBody;
import br.com.panan.requests.GeneralStatisticsGetRequestBodyYear;
import br.com.panan.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/statistic")
@Log4j2 //anotação do lombot para logs
@RequiredArgsConstructor
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping(path = "/dates")
    public ResponseEntity<GeneralStatisticsGetRequestBody> findByDates(@RequestParam String localDateSmaller, @RequestParam String localDateBigger, @RequestParam(required = false) Long idEmployee) {
        if (idEmployee != null) {
            return ResponseEntity.ok(statisticService.getStatisticForDatesEmployee(LocalDate.parse(localDateSmaller), LocalDate.parse(localDateBigger), idEmployee));
        }
        return ResponseEntity.ok(statisticService.getStatisticForDates(LocalDate.parse(localDateSmaller), LocalDate.parse(localDateBigger)));
    }

    @GetMapping(path = "/month")
    public ResponseEntity<GeneralStatisticsGetRequestBody> findByMonth(@RequestParam Integer month, @RequestParam Integer year, @RequestParam(required = false) Long idEmployee) {
        if (idEmployee != null) {
            return ResponseEntity.ok(statisticService.getStatisticForMonthEmployee(month, year, idEmployee));
        }
        return ResponseEntity.ok(statisticService.getStatisticForMonth(month, year));
    }

    @GetMapping(path = "/year")
    public ResponseEntity<GeneralStatisticsGetRequestBodyYear> findByYear(@RequestParam Integer year, @RequestParam(required = false) Long idEmployee) {
        if (idEmployee != null) {
            return ResponseEntity.ok(statisticService.getStatisticForYearEmployee(year, idEmployee));
        }
        return ResponseEntity.ok(statisticService.getStatisticForYear(year));
    }

}
