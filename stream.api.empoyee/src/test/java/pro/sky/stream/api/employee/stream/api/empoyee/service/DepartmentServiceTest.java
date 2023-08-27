package pro.sky.stream.api.employee.stream.api.empoyee.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.stream.api.employee.stream.api.empoyee.Employee;
import pro.sky.stream.api.employee.stream.api.empoyee.Exception.DepartmentNotFoundException;
import pro.sky.stream.api.employee.stream.api.empoyee.Exception.EmployeeNotFoundException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pro.sky.stream.api.employee.stream.api.empoyee.service.generator.EmployeeGenerator.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void getEmployeeWithMaxSalary_success() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата

        Employee employeeWithMaxSalary = getPetrFirstDep();
        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();
        Employee ilyaSecondDep = getIlyaSecondDep();
        Double MaxSalaryFirstDep = petrFirstDep.getSalary();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );

        //Начало теста
        Double actualMaxSalaryByDep = departmentService.getMaxSalaryByDepId(depId);
        assertEquals(MaxSalaryFirstDep, actualMaxSalaryByDep);
        assertTrue(petrFirstDep.getSalary() > ivanFirstDep.getSalary());
        verify(employeeService).getAll();
    }

    @Test
    void getMaxSalaryByDep_withEmployeeNotFoundException() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.singletonList(
                getIlyaSecondDep()
        ));
        String expectedMessage = "404 Сотрудник с максимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getMaxSalaryByDepId(depId));
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeeWithMinSalary_success() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Double minSalaryFirstDep = getIvanFirstDep().getSalary();

        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();
        Employee ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );

        //Начало теста
        Double actualMinSalaryFirstDep = departmentService.getMinSalaryByDepId(depId);
        assertEquals(minSalaryFirstDep, actualMinSalaryFirstDep);
        assertTrue(petrFirstDep.getSalary() > ivanFirstDep.getSalary());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeeWithMinSalary_EmployeeNotFoundException() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.singletonList(
                getIlyaSecondDep()
        ));
        String expectedMessage = "404 Сотрудник с минимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getMinSalaryByDepId(depId));
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeesByDepartment_success() {
        //Подготовка входных данных
        Integer depId = null;

        //Подготовка ожидаемого результата
        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();
        Employee ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );
        Map<Integer, List<Employee>> expectedEmployeeMap = new HashMap<>();
        expectedEmployeeMap.put(FIRST_DEPARTMENT_ID, List.of(petrFirstDep, ivanFirstDep));
        expectedEmployeeMap.put(SECOND_DEPARTMENT_ID, Collections.singletonList(ilyaSecondDep));

        //Начало теста
        Map<Integer, List<Employee>> actualEmployeeMap = departmentService.getEmployeesByDepartment(depId);
        assertEquals(expectedEmployeeMap, actualEmployeeMap);
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeesByDepartment_whenDepIdIsNull() {
        //Подготовка входных данных
        Integer depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();
        Employee ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );
        Map<Integer, List<Employee>> expectedEmployeeMap = new HashMap<>();
        expectedEmployeeMap.put(FIRST_DEPARTMENT_ID, List.of(petrFirstDep, ivanFirstDep));

        //Начало теста
        Map<Integer, List<Employee>> actualEmployeeMap = departmentService.getEmployeesByDepartment(depId);
        assertEquals(expectedEmployeeMap, actualEmployeeMap);
        verify(employeeService).getAll();
    }
}