package com.ideas2it.dao;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;

import com.ideas2it.model.Employee;
import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.enums.EmployeeType;
import com.ideas2it.enums.Gender;
import com.ideas2it.databaseconnection.DatabaseConnection;
import com.ideas2it.exception.EmployeeNotFoundException;

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
    private Session session = sessionFactory.openSession();
 
    @Override
    public String addEmployee(Employee employee) {
        String employeeId = null;
            
        try {
            Transaction transaction = session.beginTransaction();
            employeeId = (String) session.save(employee); 
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e); 
        } 
        return employeeId;
    }

    @Override
    public String generateEmployeeId() {
        String prefixId = "IT";
        String sequenceCount = "";
        int employeeCount = 100;
        String lastEmployeeId = getLastEmployeeId();
        if (lastEmployeeId.equals(null)) {
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
        String lastId = " ";
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select max(employeeId) from Employee");
            lastId = (String)query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        return lastId;
    }

    @Override
    public Employee getEmployeeById(String employeeId) throws EmployeeNotFoundException {
        Employee employee = null;
        try {
            Transaction transaction = session.beginTransaction();
            employee = (Employee) session.get(Employee.class, employeeId); 
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        return employee; 
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        boolean isEmployeeUpdated = false;
        try {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            isEmployeeUpdated = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return isEmployeeUpdated;
    }

    @Override
    public int removeEmployee(String employeeId) {
        int updatedRow = 0;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("update Employee set deleted = 1 where employeeId = :employeeId");
            query.setParameter("employeeId",employeeId);
            updatedRow = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        return updatedRow;
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery("from Employee where deleted<1").list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        return employees;
    }
}
