package pro.sky.stream.api.employee.stream.api.empoyee.service.generator;

import pro.sky.stream.api.employee.stream.api.empoyee.Employee;

public class EmployeeGenerator {
    private static final String IVAN_FIRST_NAME = "Ivan";
    private static final String IVAN_LAST_NAME = "Ivanov";
    private static final double IVAN_SALARY = 999;
    public static final int SECOND_DEPARTMENT_ID = 2;
    public static Employee getIvan() {
        return new Employee(IVAN_FIRST_NAME, IVAN_LAST_NAME, IVAN_SALARY, SECOND_DEPARTMENT_ID);
    }
}
