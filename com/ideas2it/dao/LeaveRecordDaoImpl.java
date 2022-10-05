package com.ideas2it.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;

import com.ideas2it.dao.LeaveRecordDao;
import com.ideas2it.databaseconnection.DatabaseConnection;
import com.ideas2it.enums.LeaveType;
import com.ideas2it.exception.EmployeeNotFoundException;
import com.ideas2it.model.Employee;
import com.ideas2it.model.LeaveRecord;

/**
 * This class implements Leave Record DAO  
 * Contains create, update and read methods
 * Deals with the employee leave Record
 *
 * @author Nithish K
 * @verison 1.0
 * @since 19.09.2022
 */
public class LeaveRecordDaoImpl implements LeaveRecordDao {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
  
    @Override
    public boolean addLeaveRecord(LeaveRecord leaveRecord, Employee employee) {
        Session session = sessionFactory.openSession();
        boolean isAdded = false;
        //List<LeaveRecord> leaves = new ArrayList<LeaveRecord>();
        //leaves.add(leaveRecord);

        try {
            Transaction transaction = session.beginTransaction();
            //Query query = session.createQuery("from Employee where employeeId = 'IT103'");
            //Employee employee = (Employee)query.uniqueResult();
            //employee.setLeaveRecords(leaves);
            session.save(leaveRecord);
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
    public List<LeaveRecord> getLeaveRecordByEmployeeId(String employeeId) {
        Session session = sessionFactory.openSession();
        List<LeaveRecord> leaveRecords = new ArrayList<LeaveRecord>();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from LeaveRecord where employee_id = :employeeId");
            query.setParameter("employeeId",employeeId);
            leaveRecords = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
	return leaveRecords;
    }

    @Override
    public boolean updateLeaveRecord(LeaveRecord leaveEntry) { 
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(leaveEntry); 
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public List<LeaveRecord> getLeaveRecords() {
        Session session = sessionFactory.openSession();
        List<LeaveRecord> leaveRecords = new ArrayList<LeaveRecord>();
        try {
            Transaction transaction = session.beginTransaction();
            leaveRecords = session.createQuery("from LeaveRecord where deleted < 1").list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return leaveRecords;
    }

    @Override
    public int removeLeaveRecord(String employeeId) {
        Session session = sessionFactory.openSession();
        int updatedRow = 0;
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("update LeaveRecord set deleted = 1 where employee_id = :employeeId");
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
}