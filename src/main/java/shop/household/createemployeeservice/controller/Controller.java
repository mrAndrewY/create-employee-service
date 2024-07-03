package shop.household.createemployeeservice.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.CharStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shop.household.api.employee.EmployeeCreateResponseDto;
import shop.household.api.employee.ErrorDto;
import shop.household.api.employee.ServiceResponseDto;
import shop.household.api.employee.EmployeeCreateRequestDto;
import shop.household.createemployeeservice.mapper.RequestMapper;
import shop.household.createemployeeservice.mapper.ResponseMapper;
import shop.household.createemployeeservice.impl.EmployeeServiceImpl;
import shop.household.createemployeeservice.service.EmployeeService;
import shop.household.createemployeeservice.util.FieldChecker;

import java.util.Arrays;


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
    public ResponseEntity<ServiceResponseDto> employeeProcessing (@RequestBody @Validated EmployeeCreateRequestDto requestDto,
                                                           @RequestHeader HttpHeaders headers){

        var nonCorrect = false;//FieldChecker.hasNullsInFields(requestDto);
        var responseDto = new ServiceResponseDto();
        if(nonCorrect) {
            responseDto.setStatus(false);
            responseDto.setError(ErrorDto
                    .builder()
                    .name("non correct fields")
                    .status("status1")
                    .build());
        } else {
            var employeeModel = requestMapper.mapDtoToModelEmployee(requestDto);
            var result = employeeService.save(employeeModel);
            EmployeeCreateResponseDto createResponseDto = new EmployeeCreateResponseDto();
            result.ifPresentOrElse(
                    (employee -> {
                        var employeeDto = responseMapper.mapEmployee(employee);
                        createResponseDto.setEmployee(employeeDto);
                        responseDto.setStatus(true);
                        responseDto.setEmployeeCreateResponse(createResponseDto);
                    }),
                    () -> {
                        responseDto.setError(
                                new ErrorDto.Builder()
                                        .name("Cant save entity")
                                        .status("status10")
                                        .build());
                        responseDto.setStatus(false);
                    });
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }

    }

