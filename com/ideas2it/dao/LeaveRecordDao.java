package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.LeaveRecord;
import com.ideas2it.model.Employee;
import com.ideas2it.exception.EmployeeNotFoundException;

/**
 * This is the interface for Leave Record of employees 
 * Contains create, update and read methods
 * Deals with the employee leave Record
 *
 * @author Nithish K
 * @verison 1.0
 * @since 19.09.2022
 */
public interface LeaveRecordDao {

    /**
     * Add a new leave Record to the data
     *
     * @param leaveRecord  - to add a new leaveRecord
     * @return boolean
     */
    public int addLeaveRecord(LeaveRecord leaveRecord);

    /**
     * Get the leave records of the employee by using the employee ID
     * Custom exception is created when employee doesn't 
     * match with the id it shows employee not found exception
     *
     *@param employeeId
     *@return List<LeaveRecord> 
     */
    public List<LeaveRecord> getLeaveRecordByEmployeeId(String employeeId);

    /**
     *Insert the updated details of the leave Record in the database
     *
     *@param leaveEntry
     *@return boolean 
     */
    public boolean updateLeaveRecord(LeaveRecord leaveEntry);

    /**
     * Get all the leave records of the employees
     * from the data
     *
     * @return List<LeaveRecord> - returns the leave record list  
     */
    public List<LeaveRecord> getLeaveRecords();

    /**
     * Delete the employee leave record from the database
     * by using employeeId
     *
     * @param employeeId
     * @return boolean
     */
    public int removeLeaveRecord(String employeeId);

}