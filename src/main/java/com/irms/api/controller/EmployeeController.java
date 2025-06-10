package com.irms.api.controller;

import com.irms.api.dto.entities.EmployeeDto;
import com.irms.api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.irms.api.constants.EndpointsConstants.EMPLOYEE_ENDPOINT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = EMPLOYEE_ENDPOINT, produces = APPLICATION_JSON_VALUE)
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return ResponseEntity.status(200)
                .contentType(APPLICATION_JSON)
                .body(employeeService.createEmployee(employeeDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id")UUID id) {
        return ResponseEntity.status(200)
                .contentType(APPLICATION_JSON)
                .body(employeeService.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Valid EmployeeDto employeeDto) {
        return ResponseEntity.status(200)
                .contentType(APPLICATION_JSON)
                .body(employeeService.updateEmployeeById(id, employeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(200)
                .contentType(APPLICATION_JSON)
                .build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.status(200)
                .contentType(APPLICATION_JSON)
                .body(employeeService.getAllEmployees());
    }

}
