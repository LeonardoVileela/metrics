package br.com.panan.domain.employee;

import br.com.panan.domain.survey.Survey;
import br.com.panan.requests.EmployeeAllActiveGetRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Optional<Employee> findByCode(String code);

    @Query(value = "SELECT * from Employee where active = false", countQuery = "SELECT count(*) from Employee where active = false", nativeQuery = true)
    public Page<Employee> listAllDisabled(Pageable pageable);

    @Query(value = "SELECT new br.com.panan.requests.EmployeeAllActiveGetRequest(emp.id, emp.name , emp.code , AVG(sur.note)) FROM Employee emp INNER JOIN Survey sur ON emp.id = sur.employee.id AND emp.active = true GROUP BY emp.id", countQuery = "SELECT count(*) from Employee emp where emp.active = true")
    public Page<EmployeeAllActiveGetRequest> listAllActive(Pageable pageable);


}
