package br.com.panan.domain.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface SurveyRepository extends JpaRepository<Survey, Long> {

    //@Query("SELECT DISTINCT ic FROM ItemCardapio ic WHERE ic.restaurante.id = ?1 AND ic.ativo = false" )

    @Query("SELECT DISTINCT sur from Survey sur where sur.date >= ?1 AND sur.date <= ?2")
    public List<Survey> listSurveyForDates(LocalDate localDateSmaller, LocalDate localDateBigger);

    @Query("SELECT DISTINCT sur from Survey sur where month(sur.date) = ?1")
    public List<Survey> listSurveyForMonth(Integer mes);

    @Query("SELECT DISTINCT sur from Survey sur where year(sur.date) = ?1")
    public List<Survey> listSurveyForYear(Integer year);

    @Query("SELECT DISTINCT sur from Survey sur where sur.date >= ?1 AND sur.date <= ?2 AND sur.employee.id = ?3")
    public List<Survey> listSurveyForDatesEmployee(LocalDate localDateSmaller, LocalDate localDateBigger, Long idEmployee);

    @Query("SELECT DISTINCT sur from Survey sur where month(sur.date) = ?1 AND sur.employee.id = ?2")
    public List<Survey> listSurveyForMonthEmployee(Integer mes , Long idEmployee);

    @Query("SELECT DISTINCT sur from Survey sur where year(sur.date) = ?1 AND sur.employee.id = ?2")
    public List<Survey> listSurveyForYearEmployee(Integer year , Long idEmployee);





}
