package com.ideas2it.service;

import java.util.List;

import com.ideas2it.exception.EmployeeNotFoundException;
import com.ideas2it.dao.EmployeeProjectDao;
import com.ideas2it.dao.EmployeeProjectDaoImpl;
import com.ideas2it.model.EmployeeProject;

public class EmployeeProjectServiceImpl implements EmployeeProjectService {

    private EmployeeProjectDao employeeProjectDaoImpl = new EmployeeProjectDaoImpl();
    
    @Override 
    public boolean addEmployeeProject(EmployeeProject employeeProject, String employeeId) {
        return employeeProjectDaoImpl.addEmployeeProject(employeeProject, employeeId);
    }

    @Override
    public List<EmployeeProject> getEmployeeProjectByEmployeeId(String employeeId) {
	return employeeProjectDaoImpl.getEmployeeProjectByEmployeeId(employeeId);
    } 

    @Override
    public List<EmployeeProject> getEmployeeProjects() {
        return employeeProjectDaoImpl.getEmployeeProjects();
    }

    @Override
    public boolean updateEmployeeProject(EmployeeProject employeeProject) {
        return employeeProjectDaoImpl.updateEmployeeProject(employeeProject);
    }

}