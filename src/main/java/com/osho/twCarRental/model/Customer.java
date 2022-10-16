package com.osho.twCarRental.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "customerid")
    private int id;

    @Column(name = "ssn", nullable = false)
    private String ssn; // Social Security Number

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email")
    private String email; // Unique email used as username

    @Column(name = "first_name")
    private String fName;

    @Column(name = "last_name")
    private String lName;

    @Column(name = "address")
    private String address;

    // Empty constructor
    public Customer() {
    }

    // Constructor without ID
    public Customer(String ssn, LocalDate dateOfBirth, String email, String fName, String lName, String address) {
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
    }

    // Constructor with ID
    public Customer(int id, String ssn, LocalDate dateOfBirth, String email, String fName, String lName, String address) {
        this.id = id;
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
