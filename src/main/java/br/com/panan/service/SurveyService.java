package br.com.panan.service;

import br.com.panan.domain.employee.EmployeeRepository;
import br.com.panan.domain.survey.Survey;
import br.com.panan.domain.survey.SurveyRepository;
import br.com.panan.requests.SurveyPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;


@Service
@RequiredArgsConstructor
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Transactional(rollbackFor = Exception.class)
    public Survey save(SurveyPostRequestBody surveyPostRequestBody) {
        // faço o mapeamento apenas com essa linha AnimeMapper.INSTANCE.toAnime(animePostRequestBody)
        Survey survey = new Survey();
        survey.setNote(surveyPostRequestBody.getNote());
        survey.setEmployee(employeeRepository.findByCode(surveyPostRequestBody.getCode()).orElseThrow());

        surveyPostRequestBody.setSuggestion(surveyPostRequestBody.getSuggestion().replace("'",""));
        surveyPostRequestBody.setSuggestion(surveyPostRequestBody.getSuggestion().replace("\""," "));
        surveyPostRequestBody.setSuggestion(surveyPostRequestBody.getSuggestion().replace("\n", " ").replace("\r", ""));

        survey.setSuggestion(surveyPostRequestBody.getSuggestion());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime date = LocalDateTime.now(ZoneId.of("America/Cuiaba"));
        long minDay = LocalDate.of(2018, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2026, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        survey.setDate(randomDate);
        //survey.setDate(date.toLocalDate());
        survey.setHour(date.format(formatter));


        return surveyRepository.save(survey);
    }

}
