package br.com.panan.domain.survey;

import br.com.panan.domain.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface SurveyRepository extends JpaRepository<Survey, Long> {

    //@Query("SELECT DISTINCT ic FROM ItemCardapio ic WHERE ic.restaurante.id = ?1 AND ic.ativo = false" )

    @Query(value = "SELECT * from Survey where date >= ?1 AND date <= ?2", nativeQuery = true)
    public List<Survey> listSurveyForDates(LocalDate localDateSmaller, LocalDate localDateBigger);

    @Query(value = "SELECT * from Survey where month(date) = ?1 AND year(date) = ?2", nativeQuery = true)
    public List<Survey> listSurveyForMonth(Integer mes, Integer year);

    @Query(value = "SELECT * from Survey where year(date) = ?1", nativeQuery = true)
    public List<Survey> listSurveyForYear(Integer year);

    @Query("SELECT DISTINCT sur from Survey sur where sur.date >= ?1 AND sur.date <= ?2 AND sur.employee.id = ?3 AND sur.employee.active = true")
    public List<Survey> listSurveyForDatesEmployee(LocalDate localDateSmaller, LocalDate localDateBigger, Long idEmployee);

    @Query("SELECT DISTINCT sur from Survey sur where month(sur.date) = ?1 AND year(sur.date) = ?2 AND sur.employee.id = ?3 AND sur.employee.active = true")
    public List<Survey> listSurveyForMonthEmployee(Integer mes , Integer year, Long idEmployee);

    @Query("SELECT DISTINCT sur from Survey sur where year(sur.date) = ?1 AND sur.employee.id = ?2 AND sur.employee.active = true")
    public List<Survey> listSurveyForYearEmployee(Integer year , Long idEmployee);

    @Query(value = "SELECT AVG(note) from Survey where employee_id = ?1", nativeQuery = true)
    public Double listSurveyWithIdEmployee(Long id);

    @Query(value = "SELECT * from Survey where suggestion <> '' order by date DESC, hour DESC", countQuery = "SELECT count(*) from Survey where suggestion <> '' order by hour desc", nativeQuery = true)
    public Page<Survey> listSurveySuggestion(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM survey WHERE survey.employee_id = :employeeId", nativeQuery = true)
    public Integer contSurvey(@Param("employeeId") Long employeeId);

    @Query(value = "SELECT SUM(note) FROM survey WHERE survey.employee_id = :employeeId", nativeQuery = true)
    public Integer sumSurvey(@Param("employeeId") Long employeeId);

    public Page<Survey> findByEmployee_NameIgnoreCaseContainingOrderByDateDesc(String suggestion, Pageable pageable);


}
