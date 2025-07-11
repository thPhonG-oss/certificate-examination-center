package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.dto.ApiResponseDto;
import com.pptk.certificate_examination_center.dto.SignInRequestDto;
import com.pptk.certificate_examination_center.dto.SignInResponseDto;
import com.pptk.certificate_examination_center.dto.SignUpRequestDto;
import com.pptk.certificate_examination_center.entity.ResponseStatus;
import com.pptk.certificate_examination_center.entity.Role;
import com.pptk.certificate_examination_center.entity.User;
import com.pptk.certificate_examination_center.exception.RoleNotFoundException;
import com.pptk.certificate_examination_center.exception.UserAlreadyExistsException;
import com.pptk.certificate_examination_center.factory.RoleFactory;
import com.pptk.certificate_examination_center.security.jwt.JwtUtils;
import com.pptk.certificate_examination_center.service.AuthService;
import com.pptk.certificate_examination_center.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class    AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleFactory roleFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public ResponseEntity<ApiResponseDto<Object>> signUp(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException, RoleNotFoundException {
        // (1) kiem tra username va email co ton tai hay chua
        if(userService.existByEmail(signUpRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("Registration Failed: Provided email already exists. Try sign in or provide another email.");
        }
        if(userService.existByUsername(signUpRequestDto.getUsername())) {
            throw new UserAlreadyExistsException("Registration Failed: Provided username already exists. Try sign in or provide another username.");
        }

        // (2) neu username va email chua ton tai thi tao doi tuong User tu cac thong tin do
        User user = createUser(signUpRequestDto);

        // (3) Luu thon tin user vua tao xuong database
        userService.save(user);
        // (4) Tra ve ket qua cho client
        Set<Role> roles = determineRoles(signUpRequestDto.getRoles());
        System.out.println(roles.toString());

        for (Role role : roles) {
            System.out.println(user.getId() + " --- " + role.getId());
            userService.addUserRole(user.getId(), role.getId());
        }

        ApiResponseDto<Object> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setStatus(String.valueOf(ResponseStatus.SUCCESS));
        apiResponseDto.setMessage("User registered successfully");
        apiResponseDto.setResponse(null);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponseDto);
    }

    private User createUser(SignUpRequestDto signUpRequestDto) throws RoleNotFoundException {
        User user = new User();
        user.setEmail(signUpRequestDto.getEmail());
        user.setUsername(signUpRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setPhone_number(signUpRequestDto.getPhone_number());
        user.setFull_name(signUpRequestDto.getFull_name());
        user.setGender(signUpRequestDto.getGender());
        user.setDob(signUpRequestDto.getDob());
        user.setAddress(signUpRequestDto.getAddress());
        user.setEnabled(true);
        return user;
    }

    private Set<Role> determineRoles(Set<String> strRoles) throws RoleNotFoundException {
        Set<Role> roles = new HashSet<>();

        if(strRoles==null) {
            roles.add(roleFactory.getInstance("user"));
        }
        else {
            for(String role : strRoles){
                roles.add(roleFactory.getInstance(role));
            }
        }
        return roles;
    }

    @Override
    public ResponseEntity<ApiResponseDto<Object>> signIn(SignInRequestDto signInRequestDto, HttpServletResponse httpServletResponse){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestDto.getEmail(), signInRequestDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt   = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles =  userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24); // 1 day
        cookie.setSecure(false); // Set to true if using HTTPS
        cookie.setAttribute("SameSite", "Strict"); // Set SameSite attribute for security
        httpServletResponse.addCookie(cookie);

        // (6) khoi tao doi tuong SignInResponseDto tra ve ket qua cho client
        SignInResponseDto signInResponseDto = new SignInResponseDto();
        signInResponseDto.setId(userDetails.getId());
        signInResponseDto.setUsername(userDetails.getUsername());
        signInResponseDto.setEmail(userDetails.getEmail());
        signInResponseDto.setRoles(roles);
        signInResponseDto.setToken(jwt);
        signInResponseDto.setType("Bearer");
        // (2) Tra ve ket qua dang nhap cho client
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDto<>(String.valueOf(ResponseStatus.SUCCESS), "User signed in successfully", signInResponseDto));
    }

}
