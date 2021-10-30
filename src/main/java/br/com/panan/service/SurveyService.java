package br.com.panan.service;

import br.com.panan.domain.employee.EmployeeRepository;
import br.com.panan.domain.survey.Survey;
import br.com.panan.domain.survey.SurveyRepository;
import br.com.panan.mapper.SurvayMapper;
import br.com.panan.requests.SurveyPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        survey.setEmployee(employeeRepository.findById(surveyPostRequestBody.getIdEmployee()).orElseThrow());

        return surveyRepository.save(survey);
    }

}
