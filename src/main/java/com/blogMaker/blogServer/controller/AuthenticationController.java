package com.blogMaker.blogServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogMaker.blogServer.dto.AuthenticationDTO;
import com.blogMaker.blogServer.dto.AuthenticationResponse;
import com.blogMaker.blogServer.entity.User;
import com.blogMaker.blogServer.repository.UserRepository;
import com.blogMaker.blogServer.security.JwtUtil;
import com.blogMaker.blogServer.service.UserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(
        @RequestBody AuthenticationDTO authenticationDTO,
        HttpServletResponse response
    ) throws Exception {
        // 1) Autentica
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
            authenticationDTO.getEmail(),
            authenticationDTO.getPassword()
          )
        );

        
        UserDetails userDetails = userDetailsService
            .loadUserByUsername(authenticationDTO.getEmail());

   
        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        
        User user = userRepository.findFirstByEmail(authenticationDTO.getEmail());
        	if (user == null) { throw new UsernameNotFoundException("Usuário não encontrado");
        }
        String name = user.getName();


    
        return new AuthenticationResponse(jwt, userDetails.getUsername(), name);
    }

}


