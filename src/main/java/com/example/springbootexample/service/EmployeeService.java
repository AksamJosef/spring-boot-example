package com.example.springbootexample.service;

import com.example.springbootexample.CeoIsExistsException;
import com.example.springbootexample.dto.CompanyDto;
import com.example.springbootexample.dto.DepartmentDto;
import com.example.springbootexample.dto.EmployeeDto;
import com.example.springbootexample.entity.Company;
import com.example.springbootexample.entity.Department;
import com.example.springbootexample.entity.Employee;
import com.example.springbootexample.entity.Salary;
import com.example.springbootexample.enums.Citizenship;
import com.example.springbootexample.enums.DepartmentKind;
import com.example.springbootexample.enums.Month;
import com.example.springbootexample.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final CompanyService companyService;

    private final DepartmentService departmentService;

    private final SalaryService salaryService;

    public void addDefault() {
        Company company = Company.builder()
                .name("OOO BEST IT")
                .build();

        Department department = Department.builder()
                .name(DepartmentKind.IT)
                .company(company)
                .build();

        Employee employee = Employee.builder()
                .citizenship(Citizenship.RU)
                .name("Nigmat")
                .lastName("Nigmatullin")
                .employmentDate(LocalDate.of(2023, 9, 14))
                .salary(410_000)
                .department(department)
                .build();

        Employee savedEntity = employeeRepository.save(employee);

        System.out.println("Успешно сохранена сущность " + savedEntity);
    }

    public List<EmployeeDto> getAllEmployeesDto() {
        return employeeRepository.findAll().stream()
                .map(employee -> transformToDto(employee))
                .collect(Collectors.toList());
    }

    private EmployeeDto transformToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .position(employee.getPosition())
                .employmentDate(employee.getEmploymentDate())
                .citizenship(employee.getCitizenship())
                .department(transformToDto(employee.getDepartment()))
                .build();
    }

    private DepartmentDto transformToDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .company(CompanyDto.builder()
                        .id(department.getCompany().getId())
                        .name(department.getCompany().getName())
                        .build())
                .build();
    }

    @Transactional
    public EmployeeDto addNewEmployee(EmployeeDto employeeDto) {
//        CompanyDto companyDto = employeeDto.getDepartment().getCompany();
//
//        Company company = Company.builder()
//                .id(companyDto.getId())
//                .name(companyDto.getName())
//                .build();
//
//        DepartmentDto departmentDto = employeeDto.getDepartment();
//
//        Department department = Department.builder()
//                .id(departmentDto.getId())
//                .name(departmentDto.getName())
//                .company(company)
//                .build();

//        Department department = null;
//
//        if (employeeDto.getDepartment() == null) {
//            department = departmentService.getByKind(DepartmentKind.SERVICE);
//        } else {
//            department = departmentService.getById(employeeDto.getDepartment().getId());
//        }

        Department department = ofNullable(employeeDto.getDepartment())
                .map(departmentDto -> departmentService.getById(departmentDto.getId()))
                .orElse(departmentService.getByKind(DepartmentKind.SERVICE));

//        Company company = companyService.getById(employeeDto.getDepartment().getCompany().getId());
//
//        Department department = departmentService.getById(employeeDto.getDepartment().getId());
//        department.setCompany(company);

        Employee employee = Employee.builder()
                .name(employeeDto.getName())
                .lastName(employeeDto.getLastName())
                .salary(employeeDto.getSalary())
                .citizenship(employeeDto.getCitizenship())
                .position(employeeDto.getPosition())
                .employmentDate(employeeDto.getEmploymentDate())
                .department(department)
                .build();

        List<String> allPositions = employeeRepository.findAllPositions().stream()
                .map(Employee::getPosition)
                .toList();

        if ("CEO".equals(employee.getPosition()) && allPositions.contains("CEO")) {
            System.out.println("Нельзя добавить сотрудника с такой должностью, она уже присутствует!!!!!!!");

            throw new CeoIsExistsException("Нельзя добавить сотрудника с такой должностью, она уже присутствует!!!!!!!");
        }

        Employee savedEntity = employeeRepository.save(employee);

        return transformToDto(savedEntity);
    }

    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }

    public void initEmployees(UUID departmentId) {
        Department department = departmentService.getById(departmentId);

        Employee leo = Employee.builder()
                .name("Leo")
                .lastName("DiCaprio")
                .citizenship(Citizenship.RU)
                .salary(456_123)
                .employmentDate(LocalDate.of(2022, 1, 10))
                .age(25)
                .department(department)
                .build();

        Employee andrey = Employee.builder()
                .name("Andrey")
                .lastName("Andreev")
                .citizenship(Citizenship.GE)
                .salary(1_000)
                .employmentDate(LocalDate.of(2023, 7, 11))
                .age(18)
                .department(department)
                .build();

        Employee eren = Employee.builder()
                .name("Eren")
                .lastName("Yeager")
                .citizenship(Citizenship.KZ)
                .salary(123_123)
                .employmentDate(LocalDate.of(2013, 1, 12))
                .age(19)
                .department(department)
                .build();

        Employee mikasa = Employee.builder()
                .name("Mikasa")
                .lastName("Akkerman")
                .citizenship(Citizenship.KZ)
                .salary(360_000)
                .employmentDate(LocalDate.of(2024, 2, 15))
                .age(24)
                .department(department)
                .build();

        List<Employee> employees = List.of(leo, andrey, eren, mikasa);

        employeeRepository.saveAll(employees);
    }

    public List<EmployeeDto> sortByAge(Boolean fromBase, Integer age) {
        if (fromBase) {
            return employeeRepository.findAllOverFourty(age).stream()
                    .map(employee -> transformToDto(employee))
                    .collect(Collectors.toList());
        }

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .filter(employee -> employee.getAge() > age)
                .map(employee -> transformToDto(employee))
                .collect(Collectors.toList());
    }

    public List<EmployeeDto> sortBySalary(Integer salary) {
        return employeeRepository.findAllSortedBySalary(salary).stream()
                .map(this::transformToDto)
                .collect(Collectors.toList());
    }

    public Map<DepartmentKind, Double> getAverageSalary() {
        Map<DepartmentKind, Double> result = new HashMap<>();

        Department IT = departmentService.getByKind(DepartmentKind.IT);
        Department SERVICE = departmentService.getByKind(DepartmentKind.SERVICE);

        List<Employee> itEmployees = employeeRepository.findAllByDepartment(IT);
        List<Employee> serviceEmployees = employeeRepository.findAllByDepartment(SERVICE);

        LocalDate.of(2023, 9, 29);

        Double itAverageSalary = 0.0;

        for (Employee itEmployee : itEmployees) {
            itAverageSalary += itEmployee.getSalary();
        }

        itAverageSalary = itAverageSalary / itEmployees.size();

        result.put(DepartmentKind.IT, itAverageSalary);

        Double serviceAverageSalary = 0.0;

        for (Employee serviceEmployee : serviceEmployees) {
            serviceAverageSalary += serviceEmployee.getSalary();
        }

        serviceAverageSalary = serviceAverageSalary / serviceEmployees.size();

        result.put(DepartmentKind.SERVICE, serviceAverageSalary);

        return result;

//        List<Employee> collect = Arrays.stream(DepartmentKind.values())
//                .flatMap(kind -> employeeRepository.findAllByDepartment(departmentService.getByKind(kind)).stream())
//                .collect(Collectors.toList());
//
    }

    public EmployeeDto editEmployee(EmployeeDto employeeDto) {

        Department department = ofNullable(employeeDto.getDepartment())
                .map(departmentDto -> departmentService.getById(departmentDto.getId()))
                .orElse(departmentService.getByKind(DepartmentKind.SERVICE));

        Employee employee = Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .lastName(employeeDto.getLastName())
                .salary(employeeDto.getSalary())
                .citizenship(employeeDto.getCitizenship())
                .employmentDate(employeeDto.getEmploymentDate())
                .department(department)
                .build();

        return transformToDto(employeeRepository.save(employee));
    }

    public void test() {
        LocalDate localDate = LocalDate.of(2023, 9, 11);

        LocalDate now = LocalDate.now();


    }

    public void initMonthSalary() {
        List<Employee> allEmployees = employeeRepository.findAll();

        Month[] months = Month.values();

        for (Employee employee : allEmployees) {
            Map<Month, Integer> salaryByMonths = new HashMap<>();

            for (Month month : months) {
                salaryByMonths.put(month, employee.getSalary());
            }

            salaryService.createSalary(salaryByMonths, employee);
        }
    }

    public Long getSalaryByYear(UUID employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();

        List<Salary> salariesByEmployee = salaryService.getSalariesByEmployee(employee);

        Long result = 0L;

        for (Salary salary : salariesByEmployee) {
            result += salary.getValue();
        }

        return result;
    }

    public Employee getEmployeeById(UUID creatorId) {
        return employeeRepository.findById(creatorId).orElseThrow();
    }
}
