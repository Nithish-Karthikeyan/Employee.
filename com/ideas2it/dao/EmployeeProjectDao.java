package com.ideas2it.dao;

import com.ideas2it.model.EmployeeProject;
import java.util.List;
import java.sql.SQLException;
import com.ideas2it.exception.EmployeeNotFoundException;

/**
 * This is the interface for employee Project DAO 
 * This interface contains create and display methods
 * This interface deals with the employee project data
 *
 * @author Nithish K
 * @verison 1.0
 * @since 17.09.2022
 */
public interface EmployeeProjectDao {

    /**
     * Add project details of the employee to the data
     *
     * @param employeeProject  - to add project details of employee
     * @return boolean
     */
    public boolean addEmployeeProject(EmployeeProject employeeProject, String employeeId);

    /**
     * Get the employee project details by using the employee ID
     * Custom exception is created when employee doesn't 
     * match with the id it shows employee project details not found exception
     *
     *@param employeeId
     *@return EmployeeProject
     */
    public List<EmployeeProject> getEmployeeProjectByEmployeeId(String employeeId) throws EmployeeNotFoundException;

    /**
     * Get all the employee project list
     * from the data
     *
     * @return List<EmployeeProject> - returns the employee project list  
     */
    public List<EmployeeProject> getEmployeeProjects();

    /**
     * Insert the updated details of the employeeProject in the database
     *
     * @param employeeProject
     * @return boolean 
     */
    public boolean updateEmployeeProject(EmployeeProject employeeProject);


    /**
     * Delete the project details from the database
     * by using employeeId
     *
     * @param employeeId
     * @return boolean
     */
    public boolean removeEmployeeProject(String employeeId);

}