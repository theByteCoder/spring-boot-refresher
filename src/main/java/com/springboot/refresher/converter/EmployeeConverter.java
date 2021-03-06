package com.springboot.refresher.converter;

import com.springboot.refresher.dto.EmployeeDTO;
import com.springboot.refresher.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EmployeeConverter {

    public Employee dtoToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmpNo(employeeDTO.getEmpNo());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setGender(employeeDTO.getGender());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setBirthDate(employeeDTO.getBirthDate());
        employee.setHireDate(employeeDTO.getHireDate());
        return employee;
    }

    public EmployeeDTO entityToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmpNo(employee.getEmpNo());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setBirthDate(employee.getBirthDate());
        employeeDTO.setHireDate(employee.getHireDate());
        return employeeDTO;
    }

    public Page<Employee> dtoToEntity(Page<EmployeeDTO> employeeDTOPage) {
        return new PageImpl<>(employeeDTOPage.stream().map(this::dtoToEntity).collect(Collectors.toList()));
    }

    public Page<EmployeeDTO> entityToDTO(Page<Employee> employeePage) {
        return new PageImpl<>(employeePage.stream().map(this::entityToDTO).collect(Collectors.toList()));
    }
}
