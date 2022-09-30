package com.ideas2it.service;

import java.util.List;
import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.dao.EmployeeDaoImpl;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.model.Employee;
import com.ideas2it.exception.EmployeeNotFoundException;

/**
 * This class implements Employee service interface 
 * It contains methods for manipulating employee details
 *
 * @author Nithish K
 * @verison 1.0
 * @since 14.09.2022
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDaoImpl = new EmployeeDaoImpl();
    
    @Override 
    public String addEmployee(Employee employee) {
        return employeeDaoImpl.addEmployee(employee);
    }


    @Override 
    public String createEmployeeId() {
        return employeeDaoImpl.generateEmployeeId();
    }

    @Override
    public Employee getEmployeeById(String id) throws EmployeeNotFoundException {
	return employeeDaoImpl.getEmployeeById(id);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeDaoImpl.updateEmployee(employee);
    } 

    @Override
    public int removeEmployee(String employeeId) {  
        return employeeDaoImpl.removeEmployee(employeeId);              
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeDaoImpl.getEmployees();
    }
}