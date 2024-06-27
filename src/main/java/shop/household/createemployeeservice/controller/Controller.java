package shop.household.createemployeeservice.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shop.household.EmployeeCreateRequestDto;
import shop.household.EmployeeCreateResponseDto;
import shop.household.ErrorDto;
import shop.household.ServiceResponseDto;
import shop.household.createemployeeservice.mapper.RequestMapper;
import shop.household.createemployeeservice.mapper.ResponseMapper;
import shop.household.createemployeeservice.service.EmployeeService;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
@Component
public class Controller {

    private final EmployeeService employeeService;
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;


    @PostMapping("${endpoint}")
    ResponseEntity<ServiceResponseDto> employeeProcessing (@RequestBody @Validated EmployeeCreateRequestDto requestDto,
                                                           @RequestHeader HttpHeaders headers){
        var employeeModel = requestMapper.mapDtoToModelEmployee(requestDto);
        var result = employeeService.save(employeeModel);
        ServiceResponseDto responseDto = new ServiceResponseDto();
        EmployeeCreateResponseDto createResponseDto = new EmployeeCreateResponseDto();
        result.ifPresentOrElse(
                (employee -> { var employeeDto = responseMapper.mapEmployee(employee);
                createResponseDto.setEmployee(employeeDto);
                responseDto.setStatus(true);
                responseDto.setEmployeeCreateResponse(createResponseDto);
                }),
                ()-> {
                    responseDto.setError(new ErrorDto("Status10", "CantSaveEntity"));
                    responseDto.setStatus(false);
                });
        return new ResponseEntity<>(responseDto, HttpStatusCode.valueOf(200));
        }

    }

