package shop.household.createemployeeservice.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.CharStream;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shop.household.api.employee.EmployeeCreateResponseDto;
import shop.household.api.employee.ErrorDto;
import shop.household.api.employee.ServiceResponseDto;
import shop.household.api.employee.EmployeeCreateRequestDto;
import shop.household.createemployeeservice.impl.EmployeeService;
import shop.household.createemployeeservice.mapper.RequestMapper;
import shop.household.createemployeeservice.mapper.ResponseMapper;


import java.util.Arrays;
import java.util.Optional;


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
    public ServiceResponseDto employeeProcessing (@RequestHeader HttpHeaders headers, @RequestBody @Validated EmployeeCreateRequestDto requestDto,
                                                                  BindingResult res){

        var responseDto = new ServiceResponseDto();
        if(res.hasErrors()) {
            responseDto.setStatus(false);
            responseDto.setError(ErrorDto
                    .builder()
                    .name(res.getFieldError())
                    .status("status1")
                    .build());
        } else {
            employeeService.createEmployee(requestDto, responseDto);
        }
        return responseDto;
        }

    }

