package shop.household.createemployeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("shop.household")
//@ConfigurationPropertiesScan
public class CreateEmployeeServiceApplication {
	public static void main(String[] args) {
		var context = SpringApplication.run(CreateEmployeeServiceApplication.class, args);
//		var bean = context.getBean(ResponseMapperImpl.class);
//		var service = context.getBean(EmployeeService.class);
//		service.save(Employee.builder()
//				.withName("Andrew")
////				.withLastName("Yuchkin")
////				.withEmail("mrAndrew@yandex.ru")
////				.withTelephone("+7-555-555-55-55")
//				.build());
////		try{service.deleteEmployeeById(88L);}catch(ServiceException e) {
////			System.out.println(e.getMessage());
////		}

	}
}
