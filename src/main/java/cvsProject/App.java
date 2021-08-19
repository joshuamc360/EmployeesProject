package cvsProject;

import cvsProject.dto.EmployeeDTO;
import cvsProject.jdbc.ConnectionManager;
import cvsProject.jdbc.EmployeesDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws IOException {

        ArrayList<EmployeeDTO> employeeList = CsvFileReader.readFromFile("EmployeeRecords.csv");
        ArrayList<EmployeesDAO>cleanEmployeeList = new ArrayList<>();

        //create a hashset
        HashSet<EmployeeDTO> employeeDTOHashSet =  new HashSet<>(employeeList);

        //compares method id's
        Collections.sort(employeeList, (o1,o2)->o1.getEmployeeId()-o2.getEmployeeId());

//        comparator using lamda expressions
//        Comparator<EmployeeDTO> anotherComparator =
//                (o1,o2)->o1.getEmployeeId()-(o2.getEmployeeId());


        System.out.println(employeeDTOHashSet.size());

//        Files.lines(Path.of("EmployeeRecords.csv")).filter(s->s.contains("Prof.")).parallel().forEach(System.out::println);
//        split iterator, parallel processing


        //database connection

        Connection connection = ConnectionManager.connectToDB();
        EmployeesDAO employeesDAO = new EmployeesDAO(connection);

        System.nanoTime();
        employeesDAO.clearTable();

        long startTime = System.nanoTime();
        for (EmployeeDTO row : employeeDTOHashSet) {
            employeesDAO.createRecord(row.getEmployeeId(), row.getNamePrefix(), row.getFirstName(),row.getMiddleInitial(),row.getLastName(), row.getGender(),row.getEmail(),row.getDateOfBirth(), row.getDateOfJoining(), row.getSalary());
        }
        long stopTime = System.nanoTime();
        ConnectionManager.closeConnection();

        long seconds = TimeUnit.SECONDS.convert(stopTime - startTime, TimeUnit.NANOSECONDS);

        System.out.println("Upload Time in nanoseconds: "+ (stopTime - startTime));
        System.out.println("Upload Time in seconds: "+ seconds);

    }

}
