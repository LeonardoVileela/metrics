package br.com.panan.mapper;

import br.com.panan.domain.survey.Survey;
import br.com.panan.requests.SurveyPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class SurvayMapper {

    public static final SurvayMapper INSTANCE = Mappers.getMapper(SurvayMapper.class);

    public abstract Survey toSurvey(SurveyPostRequestBody surveyPostRequestBody);
}
