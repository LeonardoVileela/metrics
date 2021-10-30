package br.com.panan.infrastructure.web.controller;


import br.com.panan.domain.employee.Employee;
import br.com.panan.domain.survey.Survey;
import br.com.panan.requests.EmployeePostRequestBody;
import br.com.panan.requests.SurveyPostRequestBody;
import br.com.panan.service.EmployeeService;
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
@RequestMapping("/employee")
@Log4j2 //anotação do lombot para logs
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // @Valid serve pra ativar a validação dos campos
    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody @Valid EmployeePostRequestBody employeePostRequestBody){ //para cada post usar uma classa auxiliar para não poluir e bloquear por exemplo o id
        return new ResponseEntity<>(employeeService.save(employeePostRequestBody), HttpStatus.CREATED);
    }
}
