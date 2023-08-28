package pro.sky.stream.api.employee.stream.api.empoyee.service;

import org.springframework.stereotype.Service;
import pro.sky.stream.api.employee.stream.api.empoyee.Employee;
import pro.sky.stream.api.employee.stream.api.empoyee.Exception.DepartmentNotFoundException;
import pro.sky.stream.api.employee.stream.api.empoyee.Exception.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Double getMaxSalaryByDepId(Integer departmentId) {

        Employee max = employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с максимальной зарплатой не найден"));
        return max.getSalary();
    }

    public Double getMinSalaryByDepId(Integer departmentId) {

        Employee min = employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с минимальной зарплатой не найден"));
        return min.getSalary();
    }
    public Map<Integer, List<Employee>> getEmployeesByDepartment(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> departmentId == null || e.getDepartmentId() == departmentId)
                .collect(groupingBy(Employee::getDepartmentId, toList()));
    }

    public double getSumSalaryByDep(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> departmentId == null || e.getDepartmentId() == departmentId)
                .map(x -> x.getSalary())
                .collect(Collectors.summingDouble(Double::doubleValue));
    }
}