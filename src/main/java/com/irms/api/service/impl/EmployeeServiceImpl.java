package com.irms.api.service.impl;

import com.irms.api.dto.entities.EmployeeDto;
import com.irms.api.entity.Employee;
import com.irms.api.repository.EmployeeRepository;
import com.irms.api.service.EmployeeService;
import com.irms.api.util.ModelConverter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.irms.api.exception.ApiExceptionFactory.notFound;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Objects.requireNonNull(employeeDto, "Employee dto can not be null!");
        Employee employee = ModelConverter.toEmployee(employeeDto);
        return ModelConverter.toEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> notFound("Employee with id " + id + " not found." ));
        return ModelConverter.toEmployeeDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployeeById(UUID id, EmployeeDto updateEmployee) {
        Objects.requireNonNull(updateEmployee, "Update employee can not be null!");
        if (!employeeRepository.existsById(id)){
            throw notFound("Employee with id " + id + " not found");
        }
        Employee employee = ModelConverter.toEmployee(updateEmployee);
        return ModelConverter.toEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployeeById(UUID id) {
        if (!employeeRepository.existsById(id)) {
            throw notFound("Employee with id " + id + " not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw notFound("Employees list is empty");
        }
        return employees.stream()
                .map(ModelConverter::toEmployeeDto)
                .toList();
    }


}
