package com.example.EmployeeBook.service;

import com.example.employeebook.exception.DepartmentNotFoundException;
import com.example.employeebook.model.Employee;
import com.example.employeebook.service.DepartmentServiceImpl;
import com.example.employeebook.service.EmployeeService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    EmployeeService employeeServiceMock;
    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;
    private final List<Employee> employees = new ArrayList<>();

    @BeforeEach
    void setUp() {
        departmentServiceImpl = new DepartmentServiceImpl(employeeServiceMock);
        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(employees);
        employees.add(new Employee("Ivan", "Ivanov", 10000, 1));
        employees.add(new Employee("Petr", "Petrov", 12345, 2));
        employees.add(new Employee("Sidr", "Sidorov", 200000, 3));
        employees.add(new Employee("Alex", "Aleox", 50000, 4));
        employees.add(new Employee("Pavel", "Romanov", 70000, 1));
        employees.add(new Employee("Dmitriy", "Turk", 60000, 2));
        employees.add(new Employee("Kirill", "Salomon", 450000, 5));
    }

    public static @NotNull Stream<Arguments> provideParamsForMaxSalaryTest() {
        return Stream.of(
                Arguments.of(1, 70000),
                Arguments.of(2, 60000),
                Arguments.of(3, 200000),
                Arguments.of(4, 50000),
                Arguments.of(5, 450000)
        );
    }

    public static @NotNull Stream<Arguments> provideParamsForMinSalaryTest() {
        return Stream.of(
                Arguments.of(1, 10000),
                Arguments.of(2, 12345),
                Arguments.of(3, 200000),
                Arguments.of(4, 50000),
                Arguments.of(5, 450000)
        );
    }

    public static @NotNull Stream<Arguments> provideParamsForEmployeesByDepartmentIdTest() {
        return Stream.of(
                Arguments.of(0, Collections.EMPTY_LIST),
                Arguments.of(1, List.of(
                        new Employee("Ivan", "Ivanov", 10000, 1),
                        new Employee("Pavel", "Romanov", 70000, 1))),
                Arguments.of(2, List.of(
                        new Employee("Petr", "Petrov", 12345, 2),
                        new Employee("Dmitriy", "Turk", 60000, 2))),
                Arguments.of(3, List.of(new Employee("Sidr", "Sidorov", 200000, 3))),
                Arguments.of(4, List.of(new Employee("Alex", "Aleox", 50000, 4))),
                Arguments.of(5, List.of(new Employee("Kirill", "Salomon", 450000, 5)))
        );
    }

    public static @NotNull Stream<Arguments> provideParamsForSalarySumByDepartmentIdTest() {
        return Stream.of(
                Arguments.of(1, 80000),
                Arguments.of(2, 72345),
                Arguments.of(3, 200000),
                Arguments.of(4, 50000),
                Arguments.of(5, 450000)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForEmployeesByDepartmentIdTest")
    void getEmployeesByDepartmentId(int departmentId, List<Employee> employeesParam) {

        assertThat(departmentServiceImpl.getEmployeesByDepartmentId(departmentId)).isEqualTo(employeesParam);

        Mockito.verify(employeeServiceMock).getAllEmployees();
    }

    @ParameterizedTest
    @MethodSource("provideParamsForSalarySumByDepartmentIdTest")
    void getSalarySumByDepartmentId(int departmentId, double salarySum) {
        assertThat(departmentServiceImpl.getSalarySumByDepartmentId(departmentId)).isEqualTo(salarySum);

        Mockito.verify(employeeServiceMock).getAllEmployees();
    }

    @ParameterizedTest
    @MethodSource("provideParamsForMaxSalaryTest")
    void getMaxSalaryByDepartmentId(int departmentId, double salary) {
        assertThat(departmentServiceImpl.getMaxSalaryByDepartmentId(departmentId)).isEqualTo(salary);

        Mockito.verify(employeeServiceMock).getAllEmployees();
    }

    @ParameterizedTest
    @MethodSource("provideParamsForMinSalaryTest")
    void getMinSalaryByDepartmentId(int departmentId, double salary) {
        assertThat(departmentServiceImpl.getMinSalaryByDepartmentId(departmentId)).isEqualTo(salary);

        Mockito.verify(employeeServiceMock).getAllEmployees();
    }

    @Test
    void getMinSalaryByDepartmentIdWithNonExcitedIdThenException() {
        int departmentId = 0;

        assertThatThrownBy(() -> departmentServiceImpl.getMinSalaryByDepartmentId(departmentId))
                .isInstanceOf(DepartmentNotFoundException.class)
                .hasMessageContaining(String.format("Department with %s id not found", departmentId));

        Mockito.verify(employeeServiceMock).getAllEmployees();
    }

    @Test
    void getMaxSalaryByDepartmentIdWithNonExcitedIdThenException() {
        int departmentId = 0;

        assertThatThrownBy(() -> departmentServiceImpl.getMaxSalaryByDepartmentId(departmentId))
                .isInstanceOf(DepartmentNotFoundException.class)
                .hasMessageContaining(String.format("Department with %s id not found", departmentId));

        Mockito.verify(employeeServiceMock).getAllEmployees();
    }

    @Test
    void getSalarySumByDepartmentIdWithNonExcitedIdThenException() {
        int departmentId = 0;

        assertThat(departmentServiceImpl.getSalarySumByDepartmentId(departmentId)).isZero();

        Mockito.verify(employeeServiceMock).getAllEmployees();
    }

    @Test
    void getAllEmployeesOrderedById() {
        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        for (Employee value : employees) {
            List<Employee> currentList = expectedMap.get(value.getDepartmentID());
            if (currentList == null) {
                currentList = new ArrayList<>();
                currentList.add(value);
                expectedMap.put(value.getDepartmentID(), currentList);
            } else {
                expectedMap.get(value.getDepartmentID()).add(value);
            }
        }

        Map<Integer, List<Employee>> resultMap = departmentServiceImpl.getAllEmployeesOrderedById();

        assertThat(expectedMap).isEqualTo(resultMap);

        Mockito.verify(employeeServiceMock).getAllEmployees();
    }
}