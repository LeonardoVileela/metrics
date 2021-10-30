package br.com.panan.infrastructure.web.controller;

import br.com.panan.domain.survey.Survey;
import br.com.panan.requests.SurveyPostRequestBody;
import br.com.panan.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/survey")
@Log4j2 //anotação do lombot para logs
@RequiredArgsConstructor
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    // @Valid serve pra ativar a validação dos campos
    @PostMapping
    public ResponseEntity<Survey> save(@RequestBody @Valid SurveyPostRequestBody surveyPostRequestBody){ //para cada post usar uma classa auxiliar para não poluir e bloquear por exemplo o id
        return new ResponseEntity<>(surveyService.save(surveyPostRequestBody), HttpStatus.CREATED);
    }

}
