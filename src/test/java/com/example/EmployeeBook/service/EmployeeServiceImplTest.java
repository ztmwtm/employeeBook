package com.example.EmployeeBook.service;

import com.example.employeebook.exception.EmployeeAlreadyAddedException;
import com.example.employeebook.exception.EmployeeNotFoundException;
import com.example.employeebook.exception.IncorrectInputException;
import com.example.employeebook.model.Employee;
import com.example.employeebook.service.EmployeeService;
import com.example.employeebook.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.junit.StrictStubsRunnerTestListener;

import static org.assertj.core.api.Assertions.*;

class EmployeeServiceImplTest {
    EmployeeService service = new EmployeeServiceImpl();

    @BeforeEach
    void setUp() {
        service.addEmployee("Ivan", "Ivanov", 10000, 1);
        service.addEmployee("Petr", "Ivanov", 10000, 1);
    }

    @Test
    void addEmployee() {
        Employee employee = new Employee("Sergey", "Ivanov", 10000, 1);
        assertThat(service.addEmployee("Sergey", "Ivanov", 10000, 1)).isNotNull().isEqualTo(employee);
        assertThatThrownBy(() -> service.addEmployee("Sergey", "Ivanov", 10000, 1))
                .isInstanceOf(EmployeeAlreadyAddedException.class);
        assertThatThrownBy(() -> service.addEmployee("Ivan", "Ivanov", 0, 0))
                .isInstanceOf(EmployeeAlreadyAddedException.class);
    }

    @Test
    void removeEmployee() {
        Employee employee = new Employee("Ivan", "Ivanov", 10000, 1);
        assertThat(service.removeEmployee("Ivan", "Ivanov")).isNotNull().isEqualTo(employee);
        assertThatThrownBy(() -> service.removeEmployee("Ivan", "Ivanov"))
                .isInstanceOf(EmployeeNotFoundException.class);
    }

    @Test
    void findEmployee() {
        Employee employee = new Employee("Ivan", "Ivanov", 10000, 1);
        assertThat(service.findEmployee("Ivan", "Ivanov")).isNotNull().isEqualTo(employee);
        assertThat(service.findEmployee("Ivan", "Ivanov")).isNotNull().isEqualTo(employee);


    }

    @Test
    void addAndFindEmployee() {
        Employee employee = new Employee("Sergey", "Ivanov", 10000, 1);
        assertThat(service.addEmployee("Sergey", "Ivanov", 10000, 1)).isNotNull().isEqualTo(employee);
        assertThat(service.findEmployee("Sergey", "Ivanov")).isNotNull().isEqualTo(employee);
    }

    @Test
    void getAllEmployees() {
        Employee employeeFirst = new Employee("Ivan", "Ivanov", 10000, 1);
        Employee employeeSecond = new Employee("Petr", "Ivanov", 10000, 1);
        assertThat(service.getAllEmployees()).isNotNull().containsOnly(employeeFirst, employeeSecond);
    }

    @Test
    void addAlreadyAddedEmployeeThenException() {
        String name = "Ivan";
        String secondName = "Ivanov";
        assertThatThrownBy(() -> service.addEmployee(name, secondName, 10000, 1))
                .isInstanceOf(EmployeeAlreadyAddedException.class)
                .hasMessageContaining("Employee with name %s and second name %s is already added.", name, secondName);
    }

    @Test
    void addIncorrectNameInputThenException() {
        String name = "!@#@!";
        String secondName = "Ivanov";
        assertThatThrownBy(() -> service.addEmployee(name, secondName, 1, 1))
                .isInstanceOf(IncorrectInputException.class)
                .hasMessageContaining("Incorrect data input in string " + name + " should contains only [a-zA-Z]");
    }

    @Test
    void addIncorrectSecondNameInputThenException() {
        String name = "Ivan";
        String secondName = "!@#@!";
        assertThatThrownBy(() -> service.addEmployee(name, secondName, 1, 1))
                .isInstanceOf(IncorrectInputException.class)
                .hasMessageContaining("Incorrect data input in string " + secondName + " should contains only [a-zA-Z]");
    }

    @Test
    void removeNonExistEmployee() {
        String name = "Ssss";
        String secondName = "Zzzz";
        assertThatThrownBy(() -> service.removeEmployee(name, secondName))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining(String.format("Employee with name %s and second name %s isn't found.", name, secondName));
    }
}