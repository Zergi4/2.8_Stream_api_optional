package pro.sky.stream.api.employee.stream.api.empoyee.service;

import org.springframework.stereotype.Service;
import pro.sky.stream.api.employee.stream.api.empoyee.Employee;
import pro.sky.stream.api.employee.stream.api.empoyee.Exception.EmployeeAlreadyAddedException;
import pro.sky.stream.api.employee.stream.api.empoyee.Exception.EmployeeNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static Map<String, Employee> employees = new HashMap<>();

    public static String getKey(Employee employee) {
        return employee.getFirstName() + " " + employee.getLastName();
    }

    @Override
    public String addEmployee(String firstName, String lastName, int department, float salary) {
        Employee newEmployee = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(getKey(newEmployee))) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        employees.put(getKey(newEmployee), newEmployee);
        return newEmployee.toString();
    }

    @Override
    public String deleteEmployee(String firstName, String lastName) {
        String employeeKey = firstName + " " + lastName;
        if (employees.containsKey(employeeKey)) {
            employees.remove(employeeKey);
            return employeeKey + " удален";
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public String findEmployee(String firstName, String lastName) {
        String findKey = firstName + " " + lastName;
        if (employees.containsKey(findKey)) {
            return employees.get(findKey).toString();
        }
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }


    @Override
    public String printEmployeeList() {

        return employees.toString();
    }
}
