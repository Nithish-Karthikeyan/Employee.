package com.ideas2it.service;

import java.util.List;
import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.dao.EmployeeDaoImpl;
import com.ideas2it.model.Employee;
import com.ideas2it.exception.EmployeeNotFoundException;

/**
 * This is the interface for employee service 
 * This interface contains methods for manipulating employee details
 *
 * @author Nithish K
 * @verison 1.0
 * @since 12.09.2022
 */
public interface EmployeeService {

    /**
     * This method gets the input from the controller
     * and passes the employee object to the Employee Dao
     *
     * @param employee
     * @return boolean
     */
    public String addEmployee(Employee employee);

    /**
     * Passes the command to Employee Dao for generate 
     * a unique employee id 
     *
     * @return id - returns the id
     */
    public String createEmployeeId();

    /**
     * Passes employee ID to EmployeeDao to get employee Details
     * Custom exception is created when employee doesn't 
     * match with the id it shows employee not found exception
     *
     * @param employeeId
     * @return Employee
     */
    public Employee getEmployeeById(String id) throws EmployeeNotFoundException;

    /**
     * Calls the method in EmployeeDao to insert the updated value of Employee
     *
     * @param employee
     * @return boolean 
     */
    public boolean updateEmployee(Employee employee);

    /**
     * Passes the employee Id to EmployeeDao which the user want to delete 
     *
     * @param employeeId
     * @return boolean
     */
    public int removeEmployee(String EmployeeId);

    /**
     * Gets the entire employee list from
     * the Employee Dao and passes to the controller
     *
     * @return employees 
     */
    public List<Employee> getEmployees();
}