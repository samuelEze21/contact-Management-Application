package com.semicolon.contact.Management.App.services;

import com.semicolon.contact.Management.App.Dto.requests.UserLoginRequestDTO;
import com.semicolon.contact.Management.App.Dto.responses.UserLoginResponseDTO;
import com.semicolon.contact.Management.App.Dto.requests.UserRegistrationRequestDTO;
import com.semicolon.contact.Management.App.Dto.responses.UserRegistrationResponseDTO;
import com.semicolon.contact.Management.App.Exception.UserNotFoundException;

public interface UserService {
    UserRegistrationResponseDTO registerUser(UserRegistrationRequestDTO requestDTO);
    UserLoginResponseDTO loginUser(UserLoginRequestDTO requestDTO) throws UserNotFoundException;
}





