package com.latptop.flexuy.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false, unique = true,name = "email")
    private String email;

    @Column(nullable = false, name="password")
    private String password;
    @Column(name="fullName")
    private String fullName;
    @Column(name="address")
    private String address;
    @Column(name="phone")
    private String phone;
    @Column(name="avatar")
    private String avatar;
    @Column(name="reset_password_token")
    private String resetPasswordToken;
    @Transient
    private Long roleId;
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role; // FK -> roles(id)
    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String reserPasswordToken) {
        this.resetPasswordToken = reserPasswordToken;
    }
  

}
