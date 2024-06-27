package shop.household.createemployeeservice.model;

import jakarta.persistence.*;
import lombok.*;
import shop.household.EmployeeDto;

@Data
@AllArgsConstructor
@Builder//(setterPrefix = "with")
@Entity
@Table(schema="shop", name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="employee_first_name")
    private String name;
    @Column(name="employee_last_name")
    private String lastName;
    @Column(name="employee_email")
    private String email;
    @Column(name="employee_phone")
    private String telephone;

    public Employee(){};



}
