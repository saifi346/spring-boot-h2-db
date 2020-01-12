package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {

		List<Employee> list = service.getAllEmployees();

		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException {
		Employee entity = service.getEmployeeById(id);

		return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping()
	public Long createOrUpdateEmployee(@RequestBody Employee emp) {
		service.createOrUpdateEmployee(emp);
		return emp.getId();
	}

	@DeleteMapping("{id}")
	public HttpStatus deleteEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteEmployeeById(id);
		return HttpStatus.OK;
	}

}
