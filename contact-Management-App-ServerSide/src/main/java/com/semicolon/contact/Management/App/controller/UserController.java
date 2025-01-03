package com.semicolon.contact.Management.App.controller;
import com.semicolon.contact.Management.App.Dto.requests.UserLoginRequestDTO;
import com.semicolon.contact.Management.App.Dto.requests.UserRegistrationRequestDTO;
import com.semicolon.contact.Management.App.Dto.responses.UserLoginResponseDTO;
import com.semicolon.contact.Management.App.Dto.responses.UserRegistrationResponseDTO;
import com.semicolon.contact.Management.App.models.repositories.UserRepository;
import com.semicolon.contact.Management.App.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseDTO> registerUser(@Valid @RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO) {
        UserRegistrationResponseDTO responseDTO = userService.registerUser(userRegistrationRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> loginUser(@Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        UserLoginResponseDTO responseDTO = userService.loginUser(userLoginRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}