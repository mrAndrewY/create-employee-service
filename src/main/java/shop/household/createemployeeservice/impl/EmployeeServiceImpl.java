package shop.household.createemployeeservice.impl;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.household.createemployeeservice.error.ServiceException;
import shop.household.createemployeeservice.entity.Employee;
import shop.household.createemployeeservice.repository.EmployeeRepository;
import shop.household.createemployeeservice.service.EmployeeService;

import java.util.Optional;

import static shop.household.createemployeeservice.error.ExceptionCode.ENTITY_NOT_FOUND;

@Service
@Data
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    private boolean findEmployee(Employee employee){
        return employeeRepository
                .findEmployeeByNameAndLastNameAndEmail(employee.getName()
                        ,employee.getLastName()
                        ,employee.getEmail()).isPresent();
    }
    public boolean findEmployee(Long id){
        return employeeRepository.findEmployeeById(id).isPresent();
    }

    public Optional<Employee> save(Employee employee){
        return Optional.ofNullable(
                findEmployee(employee)?
                        null:employeeRepository.save(employee));
    }

    public void deleteEmployeeById(Long id) throws ServiceException {
         if (!findEmployee(id)) {
             throw new ServiceException(String.format("Entity with %s not found in base", id), ENTITY_NOT_FOUND);
         };
         employeeRepository.deleteEmployeeById(id);
    }

}
