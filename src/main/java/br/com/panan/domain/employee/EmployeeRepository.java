package br.com.panan.domain.employee;

import br.com.panan.domain.survey.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Optional<Employee> findByCode(String code);

    @Query("SELECT DISTINCT emp from Employee emp where emp.active = false")
    public Page<Employee> listAllDisabled(Pageable pageable);


    @Query("SELECT DISTINCT emp from Employee emp where emp.active = true")
    public List<Employee> listAllActive();

}
