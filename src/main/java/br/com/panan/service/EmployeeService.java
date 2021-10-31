package br.com.panan.service;

import br.com.panan.domain.employee.Employee;
import br.com.panan.domain.employee.EmployeeRepository;
import br.com.panan.exception.BadRequestException;
import br.com.panan.mapper.EmployeeMapper;
import br.com.panan.requests.EmployeePostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Page<Employee> employeesAllDisabled(Pageable pageable) {
        return employeeRepository.listAllDisabled(pageable);
    }

    public List<Employee> employeesAllActive() {
        return employeeRepository.listAllActive();
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

}
