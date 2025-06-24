package com.pptk.certificate_examination_center.controller;

import com.pptk.certificate_examination_center.dto.ApiResponseDto;
import com.pptk.certificate_examination_center.dto.SignInRequestDto;
import com.pptk.certificate_examination_center.dto.SignUpRequestDto;
import com.pptk.certificate_examination_center.entity.ResponseStatus;
import com.pptk.certificate_examination_center.exception.RoleNotFoundException;
import com.pptk.certificate_examination_center.exception.UserAlreadyExistsException;
import com.pptk.certificate_examination_center.security.jwt.JwtUtils;
import com.pptk.certificate_examination_center.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/check")
    public ResponseEntity<ApiResponseDto<Object>> checkAuth() {
        ApiResponseDto<Object> response = new ApiResponseDto<>();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
                response.setStatus(String.valueOf(ResponseStatus.SUCCESS));
                response.setMessage("Authenticated");
            } else {
                response.setStatus(String.valueOf(ResponseStatus.FAILED));
                response.setMessage("Not authenticated");
            }
        } catch (Exception e) {
            response.setStatus(String.valueOf(ResponseStatus.FAILED));
            response.setMessage("Authentication check failed");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponseDto<Object>> registerUser(@RequestBody @Valid SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return authService.signUp(signUpRequestDto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponseDto<Object>> loginUser(@RequestBody @Validated SignInRequestDto signInRequestDto, HttpServletResponse httpServletResponse) {
        return authService.signIn(signInRequestDto,httpServletResponse);
    }

    @PostMapping("/sign-out")
    @PreAuthorize("isAuthenticated()") 
    public ResponseEntity<ApiResponseDto<Object>> logout(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        // xoá cookie chứa JWT
        Cookie jwtCookie = new Cookie("jwt", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0); // Xóa cookie ngay lập tức
        jwtCookie.setAttribute("SameSite", "Strict");
        httpServletResponse.addCookie(jwtCookie);

        // xóa SecurityContext
        SecurityContextHolder.clearContext();

        ApiResponseDto<Object> response = new ApiResponseDto<>();
        response.setStatus(String.valueOf(ResponseStatus.SUCCESS));
        response.setMessage("User logged out successfully");
        response.setResponse(null);
        return ResponseEntity.ok(response);
    }
}
