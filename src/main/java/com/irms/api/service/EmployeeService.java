package com.irms.api.service;

import com.irms.api.dto.entities.EmployeeDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(UUID id);

    EmployeeDto updateEmployeeById(UUID id, EmployeeDto updateEmployee);

    void deleteEmployeeById(UUID id);

    List<EmployeeDto> getAllEmployees();
}
