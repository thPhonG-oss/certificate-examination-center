package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.entity.Role;
import com.pptk.certificate_examination_center.entity.User;
import com.pptk.certificate_examination_center.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        Set<Role> roles = userRepository.findRolesByUserEmail(username);
        System.out.println(roles);

        return UserDetailsImpl.build(user, roles);
    }
}
