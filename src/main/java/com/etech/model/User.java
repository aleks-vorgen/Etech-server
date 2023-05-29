package com.etech.model;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permissions permissions;
    private Date createDate;

    public User(String username, String email, String password, Permissions permissions) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.permissions = permissions;
    }
}
