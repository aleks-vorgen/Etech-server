package com.etech.model;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String lastname;
    private String firstname;
    private String middlename;
    private String email;
    private String phone;
    private String password;
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permissions permissions;
    private Date createDate;
}
