package shop.household.createemployeeservice.unit;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.household.createemployeeservice.entity.Employee;
import shop.household.createemployeeservice.repository.EmployeeRepository;
import shop.household.createemployeeservice.impl.EmployeeServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    public static final Long EMPLOYEE_ID = 1L;

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void save(){
        Employee employee =Employee.
                builder()
                .id(EMPLOYEE_ID)
                .name("Andrew")
                .lastName("Yuchkin")
                .email("yy@yandex.ru")
                .telephone( "+7-555-555-55-55")
                .build();

        Mockito.doReturn(employee)
                .when(repository)
                .save(employee);
        var actualResult = employeeService.save(employee);
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(res -> assertEquals(res, employee));

        Mockito.doReturn(Optional.of(employee))
                .when(repository)
                .findEmployeeByNameAndLastNameAndEmail("Andrew", "Yuchkin", "yy@yandex.ru");
        var secondSaveTheSame = employeeService.save(employee);
        assertFalse(secondSaveTheSame.isPresent());

    }
}
