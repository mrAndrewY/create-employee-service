package shop.household.createemployeeservice.impl;

import shop.household.api.employee.EmployeeCreateRequestDto;
import shop.household.api.employee.ServiceResponseDto;
import shop.household.createemployeeservice.entity.Employee;
import shop.household.createemployeeservice.error.ServiceException;

import java.util.Optional;

import static shop.household.createemployeeservice.error.ExceptionCode.ENTITY_NOT_FOUND;

public interface EmployeeService {

    public boolean findEmployee(Long id);
    public void createEmployee(EmployeeCreateRequestDto request, ServiceResponseDto response);

    public Optional<Employee> save(Employee employee);


    public void deleteEmployeeById(Long id) throws ServiceException ;
}
