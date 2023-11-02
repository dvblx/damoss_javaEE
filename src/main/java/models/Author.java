package models;

import java.time.LocalDate;

public class Author {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate registrationDate;

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public LocalDate getRegistrationDate() {return registrationDate;}

    public void setRegistrationDate(LocalDate registrationDate) {this.registrationDate = registrationDate;}

    public Author() {this.registrationDate = LocalDate.now();}

    public Author(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registrationDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}