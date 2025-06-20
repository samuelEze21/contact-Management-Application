package com.semicolon.contact.Management.App.services;

import com.semicolon.contact.Management.App.Dto.requests.AuthUserLoginDtoRequest;
import com.semicolon.contact.Management.App.Dto.requests.UserRegistrationRequestDTO;
import com.semicolon.contact.Management.App.Dto.responses.AuthUserLoginResponseDto;
import com.semicolon.contact.Management.App.Dto.responses.UserRegistrationResponseDTO;

public interface AuthUserService {
    UserRegistrationResponseDTO register (UserRegistrationRequestDTO userRegistrationRequestDTO);
    AuthUserLoginResponseDto auth (AuthUserLoginDtoRequest authUserLoginDtoRequest);
    
//    String logout();
}