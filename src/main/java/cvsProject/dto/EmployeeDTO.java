package cvsProject.dto;

import java.util.Objects;

public class EmployeeDTO {
    private int employeeId;
    private String namePrefix;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private String dateOfBirth;
    private String dateOfJoining;
    private String salary;

    public EmployeeDTO(int employeeId, String namePrefix, String firstName, String middleInitial, String lastName, String gender, String email, String dateOfBirth, String dateOfJoining, String salary) {
        this.employeeId = employeeId;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return employeeId == that.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    public EmployeeDTO(String line) {
        String[] values = line.split(",");
        this.employeeId = Integer.parseInt(values[0]);
        this.namePrefix = values[1];
        this.firstName = values[2];
        this.middleInitial = values[3];
        this.lastName = values[4];
        this.gender = values[5];
        this.email = values[6];
        this.dateOfBirth = values[7];
        this.dateOfJoining = values[8];
        this.salary = values[9];
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeId=" + employeeId +
                ", namePrefix='" + namePrefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", dateOfJoining='" + dateOfJoining + '\'' +
                '}';
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public String getEmail() {
        return email;
    }

    public String getSalary() {
        return salary;
    }

}

//put all values in arraylist
//filter array list for erroneous values. Put valid values into a new arraylist