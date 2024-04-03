package com.example.springbootexample.repository;

import com.example.springbootexample.entity.Employee;
import com.example.springbootexample.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, UUID> {
    List<Salary> findAllByEmployee(Employee employee);

}
