package br.com.panan.service;

import br.com.panan.domain.employee.Employee;
import br.com.panan.domain.employee.EmployeeRepository;
import br.com.panan.domain.survey.SurveyRepository;
import br.com.panan.exception.BadRequestException;
import br.com.panan.mapper.EmployeeMapper;
import br.com.panan.requests.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private EmployeeMapper employeeMapper;


    public Page<Employee> employeesAllDisabled(Pageable pageable) {
        return employeeRepository.listAllDisabled(pageable);
    }

    public List<Employee> employeesAllActive() {
        return employeeRepository.listAllActive();
    }


    public List<EmployeeAllActiveGetRequest> employeeGetRequestAvg() {
        List<EmployeeAvg> employeeAvg = employeeRepository.listAllAvg();

        List<EmployeeAllActiveGetRequest> employeeAllActiveGetRequestList = new ArrayList<>();
        for (EmployeeAvg emp : employeeAvg) {
            if (emp.getSurveyTotal() != 0) {
                employeeAllActiveGetRequestList.add(new EmployeeAllActiveGetRequest(emp.getName(), emp.getCode(), Double.valueOf(emp.getNoteTotal()) / Double.valueOf(emp.getSurveyTotal())));
            }
        }

        Collections.sort(employeeAllActiveGetRequestList, Collections.reverseOrder());

        return employeeAllActiveGetRequestList;
    }

    @Transactional(rollbackFor = Exception.class)
    public Employee save(EmployeePostRequestBody employeePostRequestBody) {
        // faço o mapeamento apenas com essa linha AnimeMapper.INSTANCE.toAnime(animePostRequestBody)
        if (!employeeRepository.findByCode(employeePostRequestBody.getCode()).isEmpty()) {
            throw new BadRequestException("There is already another employee with this code");
        }
        Employee employee = EmployeeMapper.INSTANCE.toEmployee(employeePostRequestBody);
        employee.setActive(true);
        employee.setSurveyTotal(0);
        employee.setNoteTotal(0);
        return employeeRepository.save(employee);
    }

    @Transactional(rollbackFor = Exception.class)
    public void replace(EmployeePutRequestBody employeePutRequestBody) {
        //essas variaveis auxiliares são necessarias, pq é necessario que eu tenha
        //certeza que esse id vem do banco

        Employee employeeSaved = findByIdOrThrowBadRequestException(employeePutRequestBody.getId());
        Employee employee = employeeMapper.toEmployee(employeePutRequestBody);
        employee.setId(employeeSaved.getId());
        employee.setActive(employeeSaved.getActive());
        employeeRepository.save(employee);

    }

    public Employee findByIdOrThrowBadRequestException(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new BadRequestException("Employee not found")
        );
    }


    @Transactional(rollbackFor = Exception.class)
    public Employee activateEmployee(Long idEmployee) {
        // faço o mapeamento apenas com essa linha AnimeMapper.INSTANCE.toAnime(animePostRequestBody)
        Employee employee = employeeRepository.findById(idEmployee).orElseThrow();
        employee.setActive(true);
        return employeeRepository.save(employee);
    }

    @Transactional(rollbackFor = Exception.class)
    public Employee disableEmployee(Long idEmployee) {
        // faço o mapeamento apenas com essa linha AnimeMapper.INSTANCE.toAnime(animePostRequestBody)
        Employee employee = employeeRepository.findById(idEmployee).orElseThrow();
        employee.setActive(false);
        return employeeRepository.save(employee);
    }

    private Double averageEmployee(Long id) {
        double surveyList = surveyRepository.listSurveyWithIdEmployee(id);
        return surveyList;
    }

}
