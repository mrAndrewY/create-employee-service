package shop.household.createemployeeservice.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.household.api.employee.EmployeeCreateRequestDto;
import shop.household.api.employee.EmployeeCreateResponseDto;
import shop.household.api.employee.ErrorDto;
import shop.household.api.employee.ServiceResponseDto;
import shop.household.createemployeeservice.error.ServiceException;
import shop.household.createemployeeservice.entity.Employee;
import shop.household.createemployeeservice.mapper.RequestMapper;
import shop.household.createemployeeservice.mapper.ResponseMapper;
import shop.household.createemployeeservice.repository.EmployeeRepository;

import java.util.Optional;

import static shop.household.createemployeeservice.error.ExceptionCode.ENTITY_NOT_FOUND;

@Service
@Data
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;
    private final EmployeeRepository employeeRepository;

    public void createEmployee(EmployeeCreateRequestDto request, ServiceResponseDto response){
        var employeeModel = requestMapper.mapDtoToModelEmployee(request);
        var result =  save(employeeModel);
        result.ifPresentOrElse(
                (employee -> {
                    EmployeeCreateResponseDto createResponseDto = new EmployeeCreateResponseDto();
                    var employeeDto = responseMapper.mapEmployee(employee);
                    createResponseDto.setEmployee(employeeDto);
                    response.setStatus(true);
                    response.setEmployeeCreateResponse(createResponseDto);
                }),
                () -> {
                    response.setError(
                            new ErrorDto.Builder()
                                    .name("Cant save entity")
                                    .status("status10")
                                    .build());
                    response.setStatus(false);
                });
    }

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
