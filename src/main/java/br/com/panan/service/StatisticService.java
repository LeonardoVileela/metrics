package br.com.panan.service;

import br.com.panan.domain.employee.EmployeeRepository;
import br.com.panan.domain.survey.Survey;
import br.com.panan.domain.survey.SurveyRepository;
import br.com.panan.requests.GeneralStatisticsGetRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public GeneralStatisticsGetRequestBody getStatisticForDates(LocalDate localDateSmaller, LocalDate localDateBigger) {
        List<Survey> listSurveyForDates = surveyRepository.listSurveyForDates(localDateSmaller, localDateBigger);
        return processStatistics(listSurveyForDates);

    }

    public GeneralStatisticsGetRequestBody getStatisticForMonth(Integer month) {
        List<Survey> listSurveyForMonth = surveyRepository.listSurveyForMonth(month);
        return processStatistics(listSurveyForMonth);
    }

    public GeneralStatisticsGetRequestBody getStatisticForYear(Integer year) {
        List<Survey> listSurveyForYear = surveyRepository.listSurveyForYear(year);
        return processStatistics(listSurveyForYear);
    }

    public GeneralStatisticsGetRequestBody getStatisticForDatesEmployee(LocalDate localDateSmaller, LocalDate localDateBigger, Long idEmployee) {
        List<Survey> listSurveyForDatesEmployee = surveyRepository.listSurveyForDatesEmployee(localDateSmaller, localDateBigger, idEmployee);
        return processStatistics(listSurveyForDatesEmployee);

    }

    public GeneralStatisticsGetRequestBody getStatisticForMonthEmployee(Integer month, Long idEmployee) {
        List<Survey> listSurveyForMonthEmployee = surveyRepository.listSurveyForMonthEmployee(month, idEmployee);
        return processStatistics(listSurveyForMonthEmployee);
    }

    public GeneralStatisticsGetRequestBody getStatisticForYearEmployee(Integer year, Long idEmployee) {
        List<Survey> listSurveyForYearEmployee = surveyRepository.listSurveyForYearEmployee(year, idEmployee);
        return processStatistics(listSurveyForYearEmployee);
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

}
