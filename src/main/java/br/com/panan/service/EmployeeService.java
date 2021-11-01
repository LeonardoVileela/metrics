package br.com.panan.service;

import br.com.panan.domain.employee.Employee;
import br.com.panan.domain.employee.EmployeeRepository;
import br.com.panan.domain.survey.Survey;
import br.com.panan.domain.survey.SurveyRepository;
import br.com.panan.exception.BadRequestException;
import br.com.panan.mapper.EmployeeMapper;
import br.com.panan.requests.EmployeeAllActiveGetRequest;
import br.com.panan.requests.EmployeePostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SurveyRepository surveyRepository;


    public Page<Employee> employeesAllDisabled(Pageable pageable) {
        return employeeRepository.listAllDisabled(pageable);
    }

    public List<EmployeeAllActiveGetRequest> employeesAllActive() {
        List<Employee> employees = employeeRepository.listAllActive();
        List<EmployeeAllActiveGetRequest> employeeAllActiveGetRequests = new ArrayList<>();
        for (Employee emp : employees) {
            employeeAllActiveGetRequests.add(new EmployeeAllActiveGetRequest(emp.getName(), emp.getCode(), averageEmployee(emp.getId())));
        }
        return employeeAllActiveGetRequests;
    }


    @Transactional(rollbackFor = Exception.class)
    public Employee save(EmployeePostRequestBody employeePostRequestBody) {
        // faço o mapeamento apenas com essa linha AnimeMapper.INSTANCE.toAnime(animePostRequestBody)
        if (!employeeRepository.findByCode(employeePostRequestBody.getCode()).isEmpty()) {
            throw new BadRequestException("There is already another employee with this code");
        }
        Employee employee = EmployeeMapper.INSTANCE.toEmployee(employeePostRequestBody);
        employee.setActive(true);
        return employeeRepository.save(employee);
    }

    private Double averageEmployee(Long id) {
        List<Survey> surveyList = surveyRepository.listSurveyWithIdEmployee(id);
        Integer total = 0;
        for (Survey survey : surveyList) {
            total = total + survey.getNote();
        }

        Double result = Double.valueOf(total) / surveyList.size();
        if (result.isNaN()){
            return 0.0;
        }
        return result;

    }

}
