package com.blogMaker.blogServer.service;



import com.blogMaker.blogServer.dto.SignupDTO;
import com.blogMaker.blogServer.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}
