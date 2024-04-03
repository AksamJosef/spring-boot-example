package com.example.springbootexample.repository;

import com.example.springbootexample.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    @Query("FROM Company c JOIN Address a ON c.address.id = a.id")
    List<Company> findAllCompanies();

}
