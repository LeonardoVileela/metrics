package br.com.panan.infrastructure.web.controller;


import br.com.panan.domain.employee.Employee;
import br.com.panan.requests.EmployeeAllActiveGetRequest;
import br.com.panan.requests.EmployeePostRequestBody;
import br.com.panan.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/employee")
@Log4j2 //anotação do lombot para logs
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // @Valid serve pra ativar a validação dos campos
    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody @Valid EmployeePostRequestBody employeePostRequestBody) { //para cada post usar uma classa auxiliar para não poluir e bloquear por exemplo o id
        return new ResponseEntity<>(employeeService.save(employeePostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping(path = "/disabled")
    public ResponseEntity<Page<Employee>> listEmployeesAllDisabled(Pageable pageable){
        return ResponseEntity.ok(employeeService.employeesAllDisabled(pageable));
    }

    @GetMapping(path = "/active")
    public ResponseEntity<List<EmployeeAllActiveGetRequest>> listEmployeesAllActive(){
        return ResponseEntity.ok(employeeService.employeesAllActive());
    }


}
