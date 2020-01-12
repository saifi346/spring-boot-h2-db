package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repo;

	public List<Employee> getAllEmployees() {

		List<Employee> empList = repo.findAll();

		if (!empList.isEmpty()) {
			return empList;
		} else {
			return new ArrayList<>();
		}
	}

	public Employee getEmployeeById(Long id) throws RecordNotFoundException {

		Optional<Employee> emp = repo.findById(id);

		if (emp.isPresent()) {
			return emp.get();
		} else {
			throw new RecordNotFoundException("No employee record found for the given id");
		}
	}

	public void createOrUpdateEmployee(Employee entity) {

		Optional<Employee> emp = repo.findById(entity.getId());

		if (emp.isPresent()) {
			Employee newEntity = new Employee();
			newEntity.setId(entity.getId());
			newEntity.setFirstName(entity.getFirstName());
			newEntity.setLastName(entity.getLastName());
			newEntity.setEmail(entity.getEmail());

			repo.save(newEntity);

		} else {
			repo.save(entity);
		}
	}

	public void deleteEmployeeById(Long id) throws RecordNotFoundException {

		Optional<Employee> employee = repo.findById(id);

		if (employee.isPresent()) {
			repo.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record found for the given id");
		}

	}

}
