package shop.household.createemployeeservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import shop.household.EmployeeCreateRequestDto;
import shop.household.EmployeeDto;
import shop.household.createemployeeservice.model.Employee;

@Mapper(componentModel = "spring")
@Component
public interface RequestMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastname", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telephone", target = "telephone")
    Employee mapDtoToModelEmployee(EmployeeCreateRequestDto employeeDto);
}
