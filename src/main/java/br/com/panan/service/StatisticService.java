package br.com.panan.service;

import br.com.panan.domain.employee.Employee;
import br.com.panan.domain.employee.EmployeeRepository;
import br.com.panan.domain.survey.Survey;
import br.com.panan.domain.survey.SurveyRepository;
import br.com.panan.requests.GeneralStatisticsGetRequestBody;
import br.com.panan.requests.GeneralStatisticsGetRequestBodyYear;
import br.com.panan.requests.StatisticsMonthAndYear;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    @Autowired
    private SurveyRepository surveyRepository;


    public Page<Survey> listSurveySuggestion(Pageable pageable) {
        return surveyRepository.listSurveySuggestion(pageable);
    }

    public Page<Survey> listSurveySuggestionSearch(String search,Pageable pageable) {
        return surveyRepository.findByEmployee_NameIgnoreCaseContainingOrderByDateDesc(search ,pageable);
    }


    public GeneralStatisticsGetRequestBody getStatisticForDates(LocalDate localDateSmaller, LocalDate localDateBigger) {
        List<Survey> listSurveyForDates = surveyRepository.listSurveyForDates(localDateSmaller, localDateBigger);
        return processStatistics(listSurveyForDates);

    }

    public GeneralStatisticsGetRequestBody getStatisticForMonth(Integer month, Integer year) {
        List<Survey> listSurveyForMonth = surveyRepository.listSurveyForMonth(month, year);
        return processStatistics(listSurveyForMonth);
    }

    public GeneralStatisticsGetRequestBodyYear getStatisticForYear(Integer year) {
        List<Survey> listSurveyForYear = surveyRepository.listSurveyForYear(year);
        GeneralStatisticsGetRequestBody generalStatisticsGetRequestBody = processStatistics(listSurveyForYear);
        StatisticsMonthAndYear statisticsMonthAndYear = processStatisticsMonthYear(listSurveyForYear);
        return new GeneralStatisticsGetRequestBodyYear(generalStatisticsGetRequestBody, statisticsMonthAndYear);
    }

    public GeneralStatisticsGetRequestBody getStatisticForDatesEmployee(LocalDate localDateSmaller, LocalDate localDateBigger, Long idEmployee) {
        List<Survey> listSurveyForDatesEmployee = surveyRepository.listSurveyForDatesEmployee(localDateSmaller, localDateBigger, idEmployee);
        return processStatistics(listSurveyForDatesEmployee);

    }

    public GeneralStatisticsGetRequestBody getStatisticForMonthEmployee(Integer month, Integer year, Long idEmployee) {
        List<Survey> listSurveyForMonthEmployee = surveyRepository.listSurveyForMonthEmployee(month, year, idEmployee);
        return processStatistics(listSurveyForMonthEmployee);
    }

    public GeneralStatisticsGetRequestBodyYear getStatisticForYearEmployee(Integer year, Long idEmployee) {
        List<Survey> listSurveyForYearEmployee = surveyRepository.listSurveyForYearEmployee(year, idEmployee);
        GeneralStatisticsGetRequestBody generalStatisticsGetRequestBody = processStatistics(listSurveyForYearEmployee);
        StatisticsMonthAndYear statisticsMonthAndYear = processStatisticsMonthYear(listSurveyForYearEmployee);
        return new GeneralStatisticsGetRequestBodyYear(generalStatisticsGetRequestBody, statisticsMonthAndYear);
    }


    private GeneralStatisticsGetRequestBody processStatistics(List<Survey> listSurvey) {
        GeneralStatisticsGetRequestBody generalStatisticsGetRequestBody = new GeneralStatisticsGetRequestBody(0, 0, 0, 0, 0);
        for (Survey list : listSurvey) {
            if (list.getNote() == 5) {
                generalStatisticsGetRequestBody.setExcellent(generalStatisticsGetRequestBody.getExcellent() + 1);
            } else if (list.getNote() == 4) {
                generalStatisticsGetRequestBody.setVeryGood(generalStatisticsGetRequestBody.getVeryGood() + 1);
            } else if (list.getNote() == 3) {
                generalStatisticsGetRequestBody.setGood(generalStatisticsGetRequestBody.getGood() + 1);
            } else if (list.getNote() == 2) {
                generalStatisticsGetRequestBody.setReasonable(generalStatisticsGetRequestBody.getReasonable() + 1);
            } else if (list.getNote() == 1) {
                generalStatisticsGetRequestBody.setTerrible(generalStatisticsGetRequestBody.getTerrible() + 1);
            }
        }
        return generalStatisticsGetRequestBody;
    }

    private StatisticsMonthAndYear processStatisticsMonthYear(List<Survey> listSurvey) {
        StatisticsMonthAndYear statisticsMonthAndYearProcess = new StatisticsMonthAndYear(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        double noteJanuary = 0.0;
        double noteFebruary = 0.0;
        double noteMarch = 0.0;
        double noteApril = 0.0;
        double noteMay = 0.0;
        double noteJune = 0.0;
        double noteJuly = 0.0;
        double noteAugust = 0.0;
        double noteSeptember = 0.0;
        double noteOctober = 0.0;
        double noteNovember = 0.0;
        double noteDecember = 0.0;
        int sizeJanuary = 0;
        int sizeFebruary = 0;
        int sizeMarch = 0;
        int sizeApril = 0;
        int sizeMay = 0;
        int sizeJune = 0;
        int sizeJuly = 0;
        int sizeAugust = 0;
        int sizeSeptember = 0;
        int sizeOctober = 0;
        int sizeNovember = 0;
        int sizeDecember = 0;
        for (Survey list : listSurvey) {
            if (list.getDate().getMonth().getValue() == 1) {
                noteJanuary = noteJanuary + list.getNote();
                sizeJanuary = sizeJanuary + 1;
            } else if (list.getDate().getMonth().getValue() == 2) {
                noteFebruary = noteFebruary + list.getNote();
                sizeFebruary = sizeFebruary + 1;
            } else if (list.getDate().getMonth().getValue() == 3) {
                noteMarch = noteMarch + list.getNote();
                sizeMarch = sizeMarch + 1;
            } else if (list.getDate().getMonth().getValue() == 4) {
                noteApril = noteApril + list.getNote();
                sizeApril = sizeApril + 1;
            } else if (list.getDate().getMonth().getValue() == 5) {
                noteMay = noteMay + list.getNote();
                sizeMay = sizeMay + 1;
            } else if (list.getDate().getMonth().getValue() == 6) {
                noteJune = noteJune + list.getNote();
                sizeJune = sizeJune + 1;
            } else if (list.getDate().getMonth().getValue() == 7) {
                noteJuly = noteJuly + list.getNote();
                sizeJuly = sizeJuly + 1;
            } else if (list.getDate().getMonth().getValue() == 8) {
                noteAugust = noteAugust + list.getNote();
                sizeAugust = sizeAugust + 1;
            } else if (list.getDate().getMonth().getValue() == 9) {
                noteSeptember = noteSeptember + list.getNote();
                sizeSeptember = sizeSeptember + 1;
            } else if (list.getDate().getMonth().getValue() == 10) {
                noteOctober = noteOctober + list.getNote();
                sizeOctober = sizeOctober + 1;
            } else if (list.getDate().getMonth().getValue() == 11) {
                noteNovember = noteNovember + list.getNote();
                sizeNovember = sizeNovember + 1;
            } else if (list.getDate().getMonth().getValue() == 12) {
                noteDecember = noteDecember + list.getNote();
                sizeDecember = sizeDecember + 1;
            }
        }

        statisticsMonthAndYearProcess.setJanuary(averageMonth(noteJanuary, sizeJanuary));
        statisticsMonthAndYearProcess.setFebruary(averageMonth(noteFebruary, sizeFebruary));
        statisticsMonthAndYearProcess.setMarch(averageMonth(noteMarch, sizeMarch));
        statisticsMonthAndYearProcess.setApril(averageMonth(noteApril, sizeApril));
        statisticsMonthAndYearProcess.setMay(averageMonth(noteMay, sizeMay));
        statisticsMonthAndYearProcess.setJune(averageMonth(noteJune, sizeJune));
        statisticsMonthAndYearProcess.setJuly(averageMonth(noteJuly, sizeJuly));
        statisticsMonthAndYearProcess.setAugust(averageMonth(noteAugust, sizeAugust));
        statisticsMonthAndYearProcess.setSeptember(averageMonth(noteSeptember, sizeSeptember));
        statisticsMonthAndYearProcess.setOctober(averageMonth(noteOctober, sizeOctober));
        statisticsMonthAndYearProcess.setNovember(averageMonth(noteNovember, sizeNovember));
        statisticsMonthAndYearProcess.setDecember(averageMonth(noteDecember, sizeDecember));

        return statisticsMonthAndYearProcess;

    }

    private Double averageMonth(Double total, Integer size) {

        Double result = total / size;
        if (result.isNaN()) {
            return 0.0;
        }
        return result;

    }

}
