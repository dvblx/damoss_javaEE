package models;

import java.time.LocalDate;

public class Author {
    private Integer authorId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate registrationDate;

    public Integer getAuthorId() {return authorId;}

    public void setAuthorId(Integer authorId) {this.authorId = authorId;}

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

    public Author(Integer authorId, String firstName, String lastName, String email, LocalDate registrationDate) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return authorId+": "+firstName.charAt(0)+". "+lastName+": "+email;
    }
}