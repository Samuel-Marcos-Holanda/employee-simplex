package com.ms.employee.infra.controllers;

import java.util.List;

import com.ms.employee.core.useCases.delete.RemoveEmployeeInteractor;
import com.ms.employee.core.useCases.get.GetAllEmployeesInteractor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.employee.core.DTO.request.EmployeeRequestDTO;
import com.ms.employee.core.DTO.response.EmployeeResponseDTO;
import com.ms.employee.core.domain.Employee;
import com.ms.employee.core.useCases.post.CreateEmployeeInteractor;
import com.ms.employee.core.useCases.get.GetEmployeeInteractor;
import com.ms.employee.core.useCases.put.UpdateEmployeeInteractor;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final CreateEmployeeInteractor createInteractor;
    private final UpdateEmployeeInteractor updateInteractor;
    private final GetEmployeeInteractor getInteractor;
    private final GetAllEmployeesInteractor getAllInteractor;
    private final RemoveEmployeeInteractor deleteEmployeeInteractor;
    
    public EmployeeController(
            CreateEmployeeInteractor createInteractor, 
            GetEmployeeInteractor getInteractor, 
            GetAllEmployeesInteractor getAllInteractor, 
            UpdateEmployeeInteractor updateInteractor,
            RemoveEmployeeInteractor deleteEmployeeInteractor) {
        this.createInteractor = createInteractor;
        this.getInteractor = getInteractor;
        this.updateInteractor = updateInteractor;
        this.getAllInteractor = getAllInteractor;
        this.deleteEmployeeInteractor = deleteEmployeeInteractor;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() throws Exception
    {
        List<EmployeeResponseDTO> listEmp = getAllInteractor.execute();
        return new ResponseEntity<>(listEmp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") String id) throws Exception {
        Employee emp = getInteractor.execute(id);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO employeeDTO) throws Exception
    {
        EmployeeResponseDTO emp = createInteractor.execute(employeeDTO);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") String id, @RequestBody EmployeeRequestDTO employeeDTO) throws Exception {
        Employee emp = updateInteractor.execute(id, employeeDTO);
        return new ResponseEntity<>(emp, HttpStatus.OK);
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value="id") String id) throws Exception {
        deleteEmployeeInteractor.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
