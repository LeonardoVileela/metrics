package br.com.panan.infrastructure.web.controller;


import br.com.panan.domain.employee.Employee;
import br.com.panan.domain.survey.Survey;
import br.com.panan.requests.EmployeeAllActiveGetRequest;
import br.com.panan.requests.EmployeeGetRequestAvg;
import br.com.panan.requests.EmployeePostRequestBody;
import br.com.panan.requests.EmployeePutRequestBody;
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

    @GetMapping(path = "/disabled/{search}")
    public ResponseEntity<Page<Employee>> listEmployeSearchByName(@PathVariable String search, Pageable pageable){
        return ResponseEntity.ok(employeeService.listEmployeSearchByName(search, pageable));
    }

    @GetMapping(path = "/active")
    public ResponseEntity<List<Employee>> listEmployeesAllActive(){
        return ResponseEntity.ok(employeeService.employeesAllActive());
    }

    @GetMapping(path = "/average")
    public ResponseEntity<List<EmployeeAllActiveGetRequest>> listEmployeesAvg(){
        return ResponseEntity.ok(employeeService.employeeGetRequestAvg());
    }

    @PutMapping(path = "/activate/{id}")
    public ResponseEntity<Employee> activateEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.activateEmployee(id), HttpStatus.OK);
    }

    @PutMapping(path = "/disable/{id}")
    public ResponseEntity<Employee> disableEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.disableEmployee(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid EmployeePutRequestBody employeePutRequestBody){
        employeeService.replace(employeePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/code/{code}")
    public ResponseEntity<Employee> listEmployeesAllActive(@PathVariable String code){
        return ResponseEntity.ok(employeeService.findByCodeEmployee(code));
    }


}
