package com.ideas2it.dao;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;

import com.ideas2it.databaseconnection.DatabaseConnection;
import com.ideas2it.exception.EmployeeNotFoundException;
import com.ideas2it.model.Employee;
import com.ideas2it.model.EmployeeProject;

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
  
    @Override
    public boolean addEmployeeProject(EmployeeProject employeeProject, String employeeId) {
        boolean isAdded = false;
        List<Employee> employees = new ArrayList<Employee>();
        Session session = sessionFactory.openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, employeeId);
            employees.add(employee);
            employeeProject.setEmployees(employees);
            session.save(employeeProject); 
            transaction.commit();
            isAdded = true;
        } catch (Exception e) {
            System.out.println(e); 
        } finally {
            session.close();
        }
        return isAdded;  
    }

    @Override
    public List<EmployeeProject> getEmployeeProjectByEmployeeId(String employeeId){
        Session session = sessionFactory.openSession();
        List<EmployeeProject> employeeProjects = new ArrayList<EmployeeProject>();
        try {
            Transaction transaction = session.beginTransaction();
            String projectInfo = "from EmployeeProject p where p.projectId = "
                                 +"(select p.id from p.employees e where e.employeeId = :employeeId)";
            Query query = session.createQuery(projectInfo);
            query.setParameter("employeeId",employeeId);
            employeeProjects = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
	return employeeProjects;
    }

    @Override
    public List<EmployeeProject> getEmployeeProjects() {
        Session session = sessionFactory.openSession();
        List<EmployeeProject> employeeProjects = new ArrayList<EmployeeProject>();

        try {
            Transaction transaction = session.beginTransaction();
            employeeProjects = session.createQuery("from EmployeeProject").list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return employeeProjects;
    }


    @Override
    public boolean updateEmployeeProject(EmployeeProject employeeProject) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(employeeProject); 
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return true;
    }
}