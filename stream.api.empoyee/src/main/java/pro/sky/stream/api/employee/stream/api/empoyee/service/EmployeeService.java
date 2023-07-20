package pro.sky.stream.api.employee.stream.api.empoyee.service;

public interface EmployeeService {
    String findEmployee(String firstName, String lastName);

    String deleteEmployee(String firstName, String lastName);
    String addEmployee(String firstName, String lastName,int department,float salary);
    String printEmployeeList();
}
