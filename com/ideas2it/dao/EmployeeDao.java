package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.exception.EmployeeNotFoundException;
import com.ideas2it.model.Employee;
import com.ideas2it.model.EmployeeProject;

/**
 * This is the interface for employee DAO 
 * This interface contains create, update, read and delete methods
 * This interface deals with the employee details
 *
 * @author Nithish K
 * @verison 1.0
 * @since 14.09.2022
 */
public interface EmployeeDao {

    /**
     * Add a new employee to the data
     *
     * @param employee  - to add a new employee
     * @return boolean
     */
    public String addEmployee(Employee employee);

    /**
     * Generate a unique id for the 
     * employee
     *
     * @return id - returns the unique id
     */
    public String generateEmployeeId();

    /**
     * Get the last employe Id from  the database
     *
     * @return String - returns the Id of the last employee
     */
    public String getLastEmployeeId();

    /**
     * Get the employee details by using the employee ID
     * Custom exception is created when employee doesn't 
     * match with the id it shows employee not found exception
     *
     * @param employeeId
     * @return Employee
     */
    public Employee getEmployeeById(String employeeId);

    /**
     * Insert the updated details of the employee in the database
     *
     * @param employee
     * @return boolean 
     */
    public boolean updateEmployee(Employee employee);

    /**
     * Delete the employee from the database
     * by using employeeId
     *
     * @param employeeId
     * @return boolean
     */
    public int removeEmployee(String employeeId);
 
    /**
     * Get all the Employees list
     * from the data
     *
     * @return List<Employee> - returns the employee list  
     */
    public List<Employee> getEmployees();

    public boolean assignProject(Employee employee, EmployeeProject project);    

    public List<Object[]> getEmployeeDetails(String employeeId);
}
