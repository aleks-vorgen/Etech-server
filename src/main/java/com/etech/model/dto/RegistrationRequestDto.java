package com.etech.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationRequestDto {
    private String username;
    private String email;
    private String password;
}