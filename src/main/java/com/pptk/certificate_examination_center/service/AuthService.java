package com.pptk.certificate_examination_center.service;

import com.pptk.certificate_examination_center.dto.ApiResponseDto;
import com.pptk.certificate_examination_center.dto.SignInRequestDto;
import com.pptk.certificate_examination_center.dto.SignUpRequestDto;
import com.pptk.certificate_examination_center.exception.RoleNotFoundException;
import com.pptk.certificate_examination_center.exception.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    ResponseEntity<ApiResponseDto<Object>> signUp(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException, RoleNotFoundException;
    ResponseEntity<ApiResponseDto<Object>> signIn(SignInRequestDto signInRequestDto, HttpServletResponse httpServletResponse);
}
