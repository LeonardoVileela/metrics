package br.com.panan.service;

import br.com.panan.domain.appuser.AppUser;
import br.com.panan.domain.appuser.AppUserRepository;
import br.com.panan.domain.employee.Employee;
import br.com.panan.domain.employee.EmployeeRepository;
import br.com.panan.domain.survey.Survey;
import br.com.panan.domain.survey.SurveyRepository;
import br.com.panan.requests.EmployeePutRequestBody;
import br.com.panan.requests.SurveyPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private AppUserRepository appUserRepository;


    @Transactional(rollbackFor = Exception.class)
    public Survey save(SurveyPostRequestBody surveyPostRequestBody) {
        // faço o mapeamento apenas com essa linha AnimeMapper.INSTANCE.toAnime(animePostRequestBody)
        Survey survey = new Survey();
        survey.setNote(surveyPostRequestBody.getNote());
        Employee employee = employeeRepository.findByCode(surveyPostRequestBody.getCode()).orElseThrow();
        survey.setEmployee(employee);
        survey.setSuggestionFavorite(false);

        surveyPostRequestBody.setSuggestion(surveyPostRequestBody.getSuggestion().replace("'",""));
        surveyPostRequestBody.setSuggestion(surveyPostRequestBody.getSuggestion().replace("\""," "));
        surveyPostRequestBody.setSuggestion(surveyPostRequestBody.getSuggestion().replace("\n", " ").replace("\r", ""));

        survey.setSuggestion(surveyPostRequestBody.getSuggestion());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime date = LocalDateTime.now(ZoneId.of("America/Cuiaba"));

        // gerando data random pro teste
        //long minDay = LocalDate.of(2020, 1, 1).toEpochDay();
        //long maxDay = LocalDate.of(2026, 12, 31).toEpochDay();
        //long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        //LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        //survey.setDate(randomDate);

        survey.setDate(date.toLocalDate());
        survey.setHour(date.format(formatter));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = appUserRepository.findByUsername(username);
        survey.setAppUser(appUser);

        Survey survey1 = surveyRepository.save(survey);
        statisticEmployee(employee);
        return survey1;
    }

    private void statisticEmployee(Employee employee){
        Integer contSurvey = surveyRepository.contSurvey(employee.getId());
        Integer sumSurvey = surveyRepository.sumSurvey(employee.getId());
        employee.setNoteTotal(sumSurvey);
        employee.setSurveyTotal(contSurvey);
        employeeRepository.save(employee);
    }
    @Transactional(rollbackFor = Exception.class)
    public Survey replaceFavorite(Long idSurvey) {
        //essas variaveis auxiliares são necessarias, pq é necessario que eu tenha
        //certeza que esse id vem do banco
        Survey survey = surveyRepository.findById(idSurvey).orElseThrow();
        survey.setSuggestionFavorite(!survey.getSuggestionFavorite());
        return surveyRepository.save(survey);
    }
}
