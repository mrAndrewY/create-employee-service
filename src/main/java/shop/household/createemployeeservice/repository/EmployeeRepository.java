package shop.household.createemployeeservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shop.household.createemployeeservice.entity.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    void deleteById(Long id);

    @Query
    Optional<Employee> findEmployeeByNameAndLastNameAndEmail(String name, String LastName, String email);

    @Query
    Optional<Employee> findEmployeeById(Long id);

    @Query
    void deleteEmployeeById(Long id);


}
