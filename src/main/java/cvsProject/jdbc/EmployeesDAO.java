package cvsProject.jdbc;

import java.sql.*;

public class EmployeesDAO {
    //Create, Read, Update, Delete

    private Connection connection;
    private Statement statement;

    public EmployeesDAO(Connection connection){
        this.connection = connection;
        try {
            statement =connection.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //create a separate method to print out results.
    public void printRecords(){
        try{
            ResultSet resultSet = statement.executeQuery(SQLQueries.SELECT_ALL_FROM_DB);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createRecord(int id, String namePrefix, String fName, String middleInitial, String lastName, String gender, String email, String dateOfBirth, String dateOfJoining, String salary){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.INSERT_INTO_DB);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,namePrefix);
            preparedStatement.setString(3,fName);
            preparedStatement.setString(4,middleInitial);
            preparedStatement.setString(5,lastName);
            preparedStatement.setString(6,gender);
            preparedStatement.setString(7,email);
            preparedStatement.setString(8,dateOfBirth);
            preparedStatement.setString(9,dateOfJoining);
            preparedStatement.setString(10,salary);
            preparedStatement.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clearTable(){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.CLEAR_TABLE);
            preparedStatement.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
