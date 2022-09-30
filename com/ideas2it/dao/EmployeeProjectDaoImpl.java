package com.ideas2it.dao;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;

import com.ideas2it.model.Employee;
import com.ideas2it.model.EmployeeProject;
import com.ideas2it.dao.EmployeeProjectDao;
import com.ideas2it.databaseconnection.DatabaseConnection;
import com.ideas2it.exception.EmployeeNotFoundException;

/**
 * This class implemenets the employee Project DAO 
 * Contains create and display methods
 * Deals with the employee project data
 *
 * @author Nithish K
 * @verison 1.0
 * @since 17.09.2022
 */
public class EmployeeProjectDaoImpl implements EmployeeProjectDao {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Session session = sessionFactory.openSession();
  
    @Override
    public boolean addEmployeeProject(EmployeeProject employeeProject, String employeeId) {
        boolean isAdded = false;
        List<Employee> employees = new ArrayList<Employee>();


        try {
            Transaction transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, employeeId);
            employees.add(employee);
            employeeProject.setEmployees(employees);
            //employee.setEmployeeProjects(employeeProject);
            session.saveOrUpdate(employeeProject); 
            transaction.commit();
            isAdded = true;
        } catch (Exception e) {
            System.out.println(e); 
        }
        return isAdded;  
    }

    @Override
    public List<EmployeeProject> getEmployeeProjectByEmployeeId(String employeeId) throws EmployeeNotFoundException {
        List<EmployeeProject> employeeProjects = new ArrayList<EmployeeProject>();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from EmployeeProject where id in (select id from employee_project where employee_id = "it108")");
            query.setParameter("employeeId",employeeId);
            employeeProjects = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
	return employeeProjects;
    }

    @Override
    public List<EmployeeProject> getEmployeeProjects() {
        List<EmployeeProject> employeeProjects = new ArrayList<EmployeeProject>();

        try {
            Transaction transaction = session.beginTransaction();
            employeeProjects = session.createQuery("from EmployeeProject").list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        return employeeProjects;
    }


    @Override
    public boolean updateEmployeeProject(EmployeeProject employeeProject) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(employeeProject); 
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public boolean removeEmployeeProject(String employeeId) {
        return true;
    }
}