package br.com.panan.domain.survey;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
