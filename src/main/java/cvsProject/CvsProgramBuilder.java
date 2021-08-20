package cvsProject;

import cvsProject.dto.EmployeeDTO;
import cvsProject.jdbc.ConnectionManager;
import cvsProject.jdbc.EmployeesDAO;
import cvsProject.threads.CvsThreader;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Spliterator;
import java.util.concurrent.TimeUnit;

public class CvsProgramBuilder {

    private String cvsFile;

    public CvsProgramBuilder(String cvsFile) {
        this.cvsFile = cvsFile;
    }

    public static void start(){
        CvsProgramBuilder cvsProgramBuilder = new CvsProgramBuilder("EmployeeRecords.csv");
        HashSet<EmployeeDTO> cleanRecords = cvsProgramBuilder.cleanRecords();
        uploadToDB(cleanRecords);
    }

    private HashSet<EmployeeDTO> cleanRecords(){
        ArrayList<EmployeeDTO> employeeList = CsvFileReader.readFromFile(cvsFile);

        //compares method id's
        Collections.sort(employeeList, (o1, o2)->o1.getEmployeeId()-o2.getEmployeeId());

        //create a hashset
        HashSet<EmployeeDTO> employeeDTOHashSet =  new HashSet<>(employeeList);

        return employeeDTOHashSet;
    }

    //Break down this method
    private static void uploadToDB(HashSet<EmployeeDTO> dataSet){
        Connection connection1 = ConnectionManager.connectToDB();
        Connection connection2 = ConnectionManager.connectToDB();

        EmployeesDAO employeesDAO1 = new EmployeesDAO(connection1);
        EmployeesDAO employeesDAO2 = new EmployeesDAO(connection2);

        Spliterator<EmployeeDTO> split1 = dataSet.spliterator();
        Spliterator<EmployeeDTO> split2 = split1.trySplit();

        System.out.println("The 1st thread takes  half the record: "+split1.estimateSize());
        System.out.println("The 2nd thread takes another half the records: "+ split2.estimateSize());

        CvsThreader cvsThreader1 = new CvsThreader(employeesDAO1, split1);
        CvsThreader cvsThreader2 = new CvsThreader(employeesDAO2, split2);

        Thread thread1 = new Thread(cvsThreader1);
        Thread thread2 = new Thread(cvsThreader2);

        long startTime = System.nanoTime();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long stopTime = System.nanoTime();


        long seconds = TimeUnit.SECONDS.convert(stopTime - startTime, TimeUnit.NANOSECONDS);

        System.out.println("Upload Time in nanoseconds: "+ (stopTime - startTime));
        System.out.println("Upload Time in seconds: "+ seconds);

        try {
            connection1.close();
            connection2.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
