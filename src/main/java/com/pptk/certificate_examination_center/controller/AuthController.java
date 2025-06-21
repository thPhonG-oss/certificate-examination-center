package com.pptk.certificate_examination_center.controller;

import com.pptk.certificate_examination_center.dto.ApiResponseDto;
import com.pptk.certificate_examination_center.dto.SignInRequestDto;
import com.pptk.certificate_examination_center.dto.SignUpRequestDto;
import com.pptk.certificate_examination_center.exception.RoleNotFoundException;
import com.pptk.certificate_examination_center.exception.UserAlreadyExistsException;
import com.pptk.certificate_examination_center.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponseDto<Object>> registerUser(@RequestBody @Valid SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return authService.signUp(signUpRequestDto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponseDto<Object>> loginUser(@RequestBody @Validated SignInRequestDto signInRequestDto) {
        return authService.signIn(signInRequestDto);
    }
}
