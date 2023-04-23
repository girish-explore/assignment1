package com.springboot.crud.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.crud.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Value("${baseURL}")
    private String baseURL;
    RestTemplate restTemplate=new RestTemplate();

    @Test
    void getAllEmployees() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(baseURL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        List<Employee> employeeList = new ObjectMapper().readValue(response, new TypeReference<List<Employee>>(){});
        assertEquals(5, employeeList.size());
        assertEquals(1, employeeList.get(0).getId());
        assertEquals("Girish", employeeList.get(0).getFirstName());
        assertEquals(2, employeeList.get(1).getId());
        assertEquals("Vinay", employeeList.get(1).getFirstName());
    }

    @Test
    void getEmployeeById() {
        Employee employee = restTemplate.getForObject(baseURL+"/3", Employee.class);
        assertNotNull(employee);
        assertEquals("Manju",employee.getFirstName());

    }

    @Test
    void updateEmployee() {
        Employee employee=restTemplate.getForObject(baseURL+"/3",Employee.class);
        employee.setLastName("L");
        restTemplate.put(baseURL+"/3",employee);
    }

/*    @Test
    void createEmployee() {
        Employee employee=new Employee();
        employee.setFirstName("Rahul");
        employee.setLastName("M");
        employee.setEmail("rahulm@gmail.com");
        Employee newEmployee = restTemplate.postForObject("http://localhost:8081/api/employees", employee, Employee.class);
        assertNotNull(newEmployee);
        assertNotNull(newEmployee.getId());
        assertEquals("Rahul",newEmployee.getFirstName());
    }*/

}