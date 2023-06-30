package com.charlancodes.fbclone.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String emailAddress;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "firstname", nullable = false, length = 100)
    private String firstName;
    @Column(name = "surname", nullable = false, length = 100)
    private String surname;
    @Column(name = "dateofbirth", nullable = false, length = 100)
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    private Gender gender;
    @Column(name = "phonenumber", unique = true, nullable = false, length = 100)
    private String phoneNo;
}
