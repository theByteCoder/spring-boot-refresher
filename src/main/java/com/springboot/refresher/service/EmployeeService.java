package com.springboot.refresher.service;

import com.springboot.refresher.entity.Employee;
import com.springboot.refresher.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Cacheable(value = "emps")
    public Page<Employee> getEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return employeeRepository.findAll(pageable);
    }

    @Cacheable(value = "emp")
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public ResponseEntity<Employee> createEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmpNo(employee.getEmpNo());
        if (employeeOptional.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(employeeOptional.get());
        employeeRepository.save(employee);
        return ResponseEntity.ok().build();
    }
}
