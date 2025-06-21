package com.pptk.certificate_examination_center.dto;

import jakarta.validation.constraints.NotBlank;

public class SignInRequestDto {
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Password is required!")
    private String password;

    public SignInRequestDto(){}

    public SignInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public @NotBlank(message = "Email is required!") String getEmail() {
        return email;
    }

    public @NotBlank(message = "Password is required!") String getPassword() {
        return password;
    }
}
