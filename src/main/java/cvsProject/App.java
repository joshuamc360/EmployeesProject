package cvsProject;

import cvsProject.dto.EmployeeDTO;
import cvsProject.jdbc.ConnectionManager;
import cvsProject.jdbc.EmployeesDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws IOException {

        ArrayList<EmployeeDTO> employeeList = CsvFileReader.readFromFile("EmployeeRecords.csv");

        //create a hashset
        HashSet<EmployeeDTO> employeeDTOHashSet =  new HashSet<>(employeeList);

        //compares method id's
        Collections.sort(employeeList, (o1,o2)->o1.getEmployeeId()-o2.getEmployeeId());


        System.out.println(employeeDTOHashSet.size());

//        Files.lines(Path.of("EmployeeRecords.csv")).filter(s->s.contains("Prof.")).parallel().forEach(System.out::println);
//        split iterator, parallel processing


        //database connection


        Connection connection1 = ConnectionManager.connectToDB();
        Connection connection2 = ConnectionManager.connectToDB();

        EmployeesDAO employeesDAO1 = new EmployeesDAO(connection1);
        EmployeesDAO employeesDAO2 = new EmployeesDAO(connection2);

        Spliterator<EmployeeDTO> split1 = employeeDTOHashSet.spliterator();
        Spliterator<EmployeeDTO> split2 = split1.trySplit();

        System.out.println("1st half: "+split1.estimateSize());
        System.out.println("2nd half: "+ split2.estimateSize());

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
