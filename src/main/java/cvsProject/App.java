package cvsProject;

import cvsProject.dto.EmployeeDTO;
import cvsProject.jdbc.ConnectionManager;
import cvsProject.jdbc.EmployeesDAO;
import cvsProject.threads.CvsThreader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws IOException {
        CvsProgramBuilder.start();
    }

}
