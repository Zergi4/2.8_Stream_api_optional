package pro.sky.stream.api.employee.stream.api.empoyee.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import pro.sky.stream.api.employee.stream.api.empoyee.Employee;
import pro.sky.stream.api.employee.stream.api.empoyee.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @ExceptionHandler({HttpStatusCodeException.class})
    public String handleException(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + ". Error: " + e.getMessage();
    }

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/salary/max")
    public Double getEmployeeWithMaxSalary(@PathVariable Integer id) {
        return departmentService.getEmployeeWithMaxSalary(id);
    }

    @GetMapping("/{id}/salary/min")
    public Double getEmployeeWithMinSalary(@PathVariable Integer id) {
        return departmentService.getEmployeeWithMinSalary(id);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getEmployees(@RequestParam(required = false) Integer departmentId) {
        return departmentService.getEmployeesByDepartment(departmentId);
    }
    @GetMapping("/{id}/employees")
    public Map<Integer, List<Employee>> getEmployeesByDepartment(@PathVariable Integer id) {
        return departmentService.getEmployeesByDepartment(id);
    }
    @GetMapping("/{id}/salary/sum")
    public Double getSumSalaryByDep(@PathVariable Integer id) {
        return departmentService.getSumSalaryByDep(id);
    }
}