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

public class App {

    public static void main(String[] args) throws IOException {
        //CsvFileReader.readFromFile("EmployeeRecords.csv");

        ArrayList<EmployeeDTO> employeeList = CsvFileReader.readFromFile("EmployeeRecords.csv");
        ArrayList<EmployeesDAO>cleanEmployeeList = new ArrayList<>();

        //compares method id's
        Collections.sort(employeeList, new Comparator<EmployeeDTO>() {
            @Override
            public int compare(EmployeeDTO o1, EmployeeDTO o2) {
                return o1.getEmployeeId()-o2.getEmployeeId();
            }
        });

//        comparator using lamda expressions
//        Comparator<EmployeeDTO> anotherComparator =
//                (o1,o2)->o1.getEmployeeId().compareTo(o2.getEmployeeId());

//        Files.lines(Path.of("EmployeeRecords.csv")).filter(s->s.contains("Prof.")).parallel().forEach(System.out::println);
//      split iterator, parallel processing

        System.out.println(employeeList);


//        employeesDAO.createRecord();
//        employeesDAO.printRecords();
    }
}
