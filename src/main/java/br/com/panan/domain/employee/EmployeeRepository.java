package br.com.panan.domain.employee;

import br.com.panan.requests.EmployeeAllActiveGetRequest;
import br.com.panan.requests.EmployeeAvg;
import br.com.panan.requests.EmployeeGetRequestAvg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Optional<Employee> findByCode(String code);

    @Query(value = "SELECT * from Employee where active = false", countQuery = "SELECT count(*) from Employee where active = false", nativeQuery = true)
    public Page<Employee> listAllDisabled(Pageable pageable);


    @Query(value = "SELECT * from Employee where active = true ORDER BY name", countQuery = "SELECT count(*) from Employee where active = false", nativeQuery = true)
    public List<Employee> listAllActive();

    @Query(value = "SELECT new br.com.panan.requests.EmployeeAvg(emp.name , emp.code , emp.noteTotal, emp.surveyTotal) FROM Employee emp where emp.active = true and emp.surveyTotal > 100")
    public List<EmployeeAvg> listAllAvg();


}
