package com.latptop.flexuy.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private Long id;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    private String avatar;
    private Long roleId;
}
