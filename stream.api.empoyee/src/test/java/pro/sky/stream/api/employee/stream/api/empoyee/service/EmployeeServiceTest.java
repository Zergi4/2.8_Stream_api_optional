package pro.sky.stream.api.employee.stream.api.empoyee.service;

import org.junit.jupiter.api.Test;
import pro.sky.stream.api.employee.stream.api.empoyee.Employee;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();
    @Test
    void add_success() {
         //подготовка входных данных
        String firstName = "Ivan";
        String lastName = "Ivanov";
        double salary = 999;
        int depId = 2;

        //подготовка ожидаемого результата
        Employee expectedEmployee = new Employee(firstName, lastName, salary, depId);

        //Начало теста
        Employee addedEmployee = employeeService.add(firstName, lastName, salary, depId);
        assertEquals(expectedEmployee,addedEmployee);
    }

    @Test
    void add_withEmployeeStorageIsFullException() {
        //подготовка входных данных

        //подготовка ожидаемого результата

        //Начало теста
    }


    @Test
    void find_success() {
        //подготовка входных данных

        //подготовка ожидаемого результата

        //Начало теста
    }

    @Test
    void find_withEmployeeNotFoundException() {
        //подготовка входных данных

        //подготовка ожидаемого результата

        //Начало теста
    }

    @Test
    void remove_success() {
        //подготовка входных данных

        //подготовка ожидаемого результата

        //Начало теста
    }

}