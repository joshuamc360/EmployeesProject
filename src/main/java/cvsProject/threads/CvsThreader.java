package cvsProject.threads;

import cvsProject.dto.EmployeeDTO;
import cvsProject.jdbc.ConnectionManager;
import cvsProject.jdbc.EmployeesDAO;

import java.sql.Connection;
import java.util.ArrayList;
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
        employeeRecords.forEachRemaining(r->employeesDAO.createRecord(r.getEmployeeId(), r.getNamePrefix(), r.getFirstName(),r.getMiddleInitial(),r.getLastName(), r.getGender(),r.getEmail(),r.getDateOfBirth(), r.getDateOfJoining(), r.getSalary()));
    }

//    Code stub to make multiple threads
//    public Spliterator<EmployeeDTO> threadCreator(int noThreads, HashSet<EmployeeDTO> employeeDTOHashSet){
//        ArrayList<Thread> threads = new ArrayList<>();
//
//        for(int i = 0; i<= noThreads; i++){
//            Connection connection = ConnectionManager.connectToDB();
//            EmployeesDAO employeesDAO1 = new EmployeesDAO(connection);
//            Spliterator<EmployeeDTO> split1 = employeeDTOHashSet.spliterator();
//            CvsThreader cvsThreader1 = new CvsThreader(employeesDAO1, split1);
//            Thread thread1 = new Thread(cvsThreader1);
//            threads.add(thread1);
//        }
//    }

    @Override
    public void run() {
        uploadEmployeeData();
    }

}
