package br.com.panan.mapper;

import br.com.panan.domain.employee.Employee;
import br.com.panan.domain.survey.Survey;
import br.com.panan.requests.EmployeePostRequestBody;
import br.com.panan.requests.SurveyPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    public static final EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    public abstract Employee toEmployee(EmployeePostRequestBody employeePostRequestBody);
}
