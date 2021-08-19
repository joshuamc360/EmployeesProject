package cvsProject.jdbc;

public interface SQLQueries {

    String CLEAR_TABLE = "TRUNCATE TABLE employees";
    String SELECT_ALL_FROM_DB = "select * from user_db.employees";
    String INSERT_INTO_DB = "INSERT INTO `user_db`.`employees` (`EmployeeID`, `NamePrefix`, `FirstName`, `MiddleInitial`, `LastName`, `Gender`, `Email`, `DateOfBirth`, `DateOfJoining`, `Salary`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
}
