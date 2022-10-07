package com.ideas2it.dao;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.databaseconnection.DatabaseConnection;
import com.ideas2it.exception.EmployeeNotFoundException;
import com.ideas2it.model.Employee;
import com.ideas2it.model.EmployeeProject;


/**
 * This class implements the employee DAO 
 * Has create, update read and display methods
 * It manages the employee data
 *
 * @author Nithish K
 * @verison 1.0
 * @since 14.09.2022
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
 
    @Override
    public String addEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        String employeeId = null;
            
        try {
            Transaction transaction = session.beginTransaction();
            employeeId = (String) session.save(employee); 
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e); 
        } finally {
            session.close();
        }
        return employeeId;
    }

    @Override
    public String generateEmployeeId() {
        String prefixId = "IT";
        String sequenceCount = "";
        String initialId = null;
        int employeeCount = 100;
        String lastEmployeeId = getLastEmployeeId();

        if (lastEmployeeId.equals(initialId)) {
            return prefixId + ++employeeCount;
        } else {
            for(int i = 2; i < lastEmployeeId.length(); i++) {
                sequenceCount += lastEmployeeId.charAt(i); 
            }
            employeeCount = Integer.parseInt(sequenceCount);
            return prefixId + ++employeeCount; 
        }
    }

    @Override
    public String getLastEmployeeId() {
        Session session = sessionFactory.openSession();
        String lastId = " ";
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select max(employeeId) from Employee");
            lastId = (String)query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return lastId;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        Session session = sessionFactory.openSession();
        Employee employee = null;
        try {
            Transaction transaction = session.beginTransaction();
            employee = (Employee) session.get(Employee.class, employeeId); 
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return employee; 
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        boolean isEmployeeUpdated = false;
        try {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            isEmployeeUpdated = true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return isEmployeeUpdated;
    }

    @Override
    public int removeEmployee(String employeeId) {
        Session session = sessionFactory.openSession();
        int updatedRow = 0;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("update Employee set deleted = 1 where employeeId = :employeeId");
            query.setParameter("employeeId",employeeId);
            updatedRow = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return updatedRow;
    }

    @Override
    public List<Employee> getEmployees() {
        Session session = sessionFactory.openSession();
        List<Employee> employees = new ArrayList<Employee>();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Employee where deleted = 0");
            employees = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public boolean assignProject(Employee employee, EmployeeProject project) {
        Session session = sessionFactory.openSession();
        boolean isAdded = false;    
        List<EmployeeProject> projects = new ArrayList<EmployeeProject>();
        projects.add(project);

        try {
            Transaction transaction = session.beginTransaction();
            employee.setEmployeeProjects(projects);
            session.saveOrUpdate(employee);
            transaction.commit();         
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return isAdded;
    }

    @Override
    public List<Object[]> getEmployeeDetails(String employeeId) {
       List<Object[]> employeeInformation = null;
        Session session = sessionFactory.openSession();

        try {
            Transaction transaction = session.beginTransaction();
            String employeeInfo =  "from Employee e "
                                  //+"inner join LeaveRecord l on l.employee = :employeeId "
                                  +"inner join EmployeeProject p on p.projectId = "
                                  +"(select p.id from p.employees em where em.employeeId = :employeeId)"
                                  +"where e.employeeId = :employeeId ";
            Query query = session.createQuery(employeeInfo);
            query.setParameter("employeeId",employeeId);
            employeeInformation = query.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return employeeInformation;
    }
}
