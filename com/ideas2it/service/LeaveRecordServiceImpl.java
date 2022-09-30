package com.ideas2it.service;

import java.util.List;
import com.ideas2it.model.LeaveRecord;
import com.ideas2it.model.Employee;
import com.ideas2it.dao.LeaveRecordDao;
import com.ideas2it.dao.LeaveRecordDaoImpl;
import com.ideas2it.exception.EmployeeNotFoundException;

/**
 * This class implements the Leave Record Service  
 * Contains methods for handling employee leave Records
 *
 * @author Nithish K
 * @verison 1.0
 * @since 19.09.2022
 */
public class LeaveRecordServiceImpl implements LeaveRecordService {

    private LeaveRecordDao leaveRecordDaoImpl = new LeaveRecordDaoImpl();
    
    @Override 
    public boolean addLeaveRecord(LeaveRecord leaveRecord, String employeeId) {
        return leaveRecordDaoImpl.addLeaveRecord(leaveRecord, employeeId);
    }

    @Override
    public List<LeaveRecord> getLeaveRecordByEmployeeId(String employeeId) throws EmployeeNotFoundException {
	return leaveRecordDaoImpl.getLeaveRecordByEmployeeId(employeeId);
    }

    @Override
    public boolean updateLeaveRecord(LeaveRecord leaveEntry) {
        return leaveRecordDaoImpl.updateLeaveRecord(leaveEntry);
    } 

    @Override
    public List<LeaveRecord> getLeaveRecords() {
        return leaveRecordDaoImpl.getLeaveRecords();
    }

    @Override
    public int removeLeaveRecord(String employeeId) {
        return leaveRecordDaoImpl.removeLeaveRecord(employeeId);
    }
}