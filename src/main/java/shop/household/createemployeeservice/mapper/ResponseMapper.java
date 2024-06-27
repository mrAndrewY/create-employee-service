package shop.household.createemployeeservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import shop.household.EmployeeCreateResponseDto;
import shop.household.EmployeeDto;
import shop.household.createemployeeservice.model.Employee;

@Mapper(componentModel = "spring")
@Component
public interface ResponseMapper  {

    @Mapping(expression = "java(String.valueOf(employee.getId()))", target = "employeeId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telephone", target = "telephone")
    EmployeeDto mapEmployee(Employee employee);


}
