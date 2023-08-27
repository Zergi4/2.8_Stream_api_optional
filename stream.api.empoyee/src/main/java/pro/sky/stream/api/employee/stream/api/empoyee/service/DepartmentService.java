package pro.sky.stream.api.employee.stream.api.empoyee.service;

import org.springframework.stereotype.Service;
import pro.sky.stream.api.employee.stream.api.empoyee.Employee;
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

    public Double getEmployeeWithMaxSalary(Integer departmentId) {
  /*      OptionalDouble max = employeeService.getAll().stream().mapToDouble(Double::doubleValue).max();

        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .map(x -> x.getSalary())
                .mapToInt(Double::doubleValue).max();
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с максимальной зарплатой не найден"));*/
        Double max = employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .map(x -> x.getSalary())
                .mapToDouble(Double::doubleValue).max().getAsDouble();
        return max;
    }

    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с минимальной зарплатой не найден"));
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