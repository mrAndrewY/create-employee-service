package shop.household.createemployeeservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;
import shop.household.api.employee.EmployeeDto;
import shop.household.createemployeeservice.entity.Employee;

@Mapper(componentModel = "spring")
@Component
public interface ResponseMapper  {

    @Mapping(expression = "java(String.valueOf(employee.getId()))", target = "employeeId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastname", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(source = "email", target = "email" , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(source = "telephone", target = "telephone" , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    EmployeeDto mapEmployee(Employee employee);


}
