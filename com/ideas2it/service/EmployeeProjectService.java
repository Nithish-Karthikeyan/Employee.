package com.ideas2it.service;

import java.util.List;
import com.ideas2it.dao.EmployeeProjectDao;
import com.ideas2it.dao.EmployeeProjectDaoImpl;
import com.ideas2it.model.EmployeeProject;
import com.ideas2it.exception.EmployeeNotFoundException;

/**
 * This is the interface for employee project service 
 * This interface contains methods for handling employee details
 *
 * @author Nithish K
 * @verison 1.0
 * @since 19.09.2022
 */
public interface EmployeeProjectService {

    /**
     * This method gets the input from the controller
     * and passes the employee object to the Employee Project Dao
     *
     * @param employeeProject
     * @return boolean
     */
    public boolean addEmployeeProject(EmployeeProject employeeProject, String employeeId);

    /**
     * Passes employee ID to EmployeeProjectDao to get Project details of particular employee
     * Custom exception is created when employee doesn't 
     * match with the id it shows employee not found exception
     *
     *@param employeeId
     *@return Employee
     */
    public List<EmployeeProject> getEmployeeProjectByEmployeeId(String employeeId) throws EmployeeNotFoundException;

    /**
     * Gets the entire employee project list from
     * the Employee Project Dao and passes to the controller
     *
     * @return List<EmployeeProject>
     */
    public List<EmployeeProject> getEmployeeProjects();

    /**
     * Passes the employee Id to EmployeeProjectDao which the user want to delete 
     *
     * @param employeeId
     * @return boolean
     */
    public boolean removeEmployeeProject(String employeeId);

    /**
     * Calls the method in LeaveRecordDao to insert the updated value of LeaveRecord
     *
     * @param employeeProject
     * @return boolean 
     */
    public boolean updateEmployeeProject(EmployeeProject employeeProject);

}