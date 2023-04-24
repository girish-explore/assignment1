package com.springboot.crud.controller;

import com.springboot.crud.entity.Employee;
import com.springboot.crud.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    private static final Logger LOGGER= LoggerFactory.getLogger(Employee.class);

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // build create Employee REST API
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // build get Employee by id REST API
    // http://localhost:8080/api/employees/1
    @GetMapping(value = "{id}",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        Employee Employee = employeeService.getEmployeeById(id);
        LOGGER.info("Employee Retrieved with ID: "+id);
        return new ResponseEntity<>(Employee, HttpStatus.OK);
    }

    // Build Get All Employees REST API
    // http://localhost:8080/api/employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> Employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(Employees, HttpStatus.OK);
    }

    // Build Update Employee REST API
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    // http://localhost:8080/api/employees/1
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id,
                                           @RequestBody Employee employee){
        employee.setId(id);
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    // Build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee successfully deleted!", HttpStatus.OK);
    }
    
}
