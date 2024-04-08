package com.example.springbootexample.repository;

import com.example.springbootexample.dto.EmployeeDto;
import com.example.springbootexample.entity.Department;
import com.example.springbootexample.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query("FROM Employee WHERE age < :age")
    List<Employee> findAllOverFourty(Integer age);

    @Query("FROM Employee WHERE salary > :salary")
    List<Employee> findAllSortedBySalary(Integer salary);

    List<Employee> findAllByDepartment(Department department);

    @Query("FROM Employee e WHERE e.position IS NOT NULL")
    List<Employee> findAllPositions();

    Optional<Employee> findByName(String name);
}
