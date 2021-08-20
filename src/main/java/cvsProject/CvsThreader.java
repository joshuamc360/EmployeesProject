package cvsProject;

import cvsProject.dto.EmployeeDTO;
import cvsProject.jdbc.EmployeesDAO;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Spliterator;

public class CvsThreader implements Runnable {

    private EmployeesDAO employeesDAO;
    private Spliterator<EmployeeDTO>employeeRecords;

    //for each thread, have a new connection to the database and split the hashset

    public CvsThreader(EmployeesDAO employeesDAO, Spliterator<EmployeeDTO> employeeRecords) {
        this.employeesDAO = employeesDAO;
        this.employeeRecords = employeeRecords;
    }

    public synchronized void uploadEmployeeData( ){
        employeesDAO.clearTable();
//        for (EmployeeDTO row : employeeRecords) {
//            employeesDAO.createRecord(row.getEmployeeId(), row.getNamePrefix(), row.getFirstName(),row.getMiddleInitial(),row.getLastName(), row.getGender(),row.getEmail(),row.getDateOfBirth(), row.getDateOfJoining(), row.getSalary());
//        }
        employeeRecords.forEachRemaining(r->employeesDAO.createRecord(r.getEmployeeId(), r.getNamePrefix(), r.getFirstName(),r.getMiddleInitial(),r.getLastName(), r.getGender(),r.getEmail(),r.getDateOfBirth(), r.getDateOfJoining(), r.getSalary()));
    }

    @Override
    public void run() {
        uploadEmployeeData();
    }

}
