package com.example.todolist.service;

import com.example.todolist.dto.LoginRequestDto;
import com.example.todolist.dto.TokenResponseDto;
import com.example.todolist.entity.User;
import com.example.todolist.repository.UserRepository;
import com.example.todolist.config.JwtUtil;
import com.example.todolist.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final com.example.todolist.service.impl.UserDetailsServiceImpl userDetailsService;

    public void register(LoginRequestDto request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getUsername(), encodedPassword, Role.USER);
        userRepository.save(user);
    }

    public TokenResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return new TokenResponseDto(token);
    }
}
