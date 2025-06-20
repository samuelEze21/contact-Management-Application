package com.semicolon.contact.Management.App.controller;

import com.semicolon.contact.Management.App.Dto.requests.AuthUserLoginDtoRequest;
import com.semicolon.contact.Management.App.Dto.requests.UserRegistrationRequestDTO;
import com.semicolon.contact.Management.App.Dto.responses.AuthUserLoginResponseDto;
import com.semicolon.contact.Management.App.Dto.responses.UserRegistrationResponseDTO;
import com.semicolon.contact.Management.App.services.AuthUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class AuthUserController {

    private final AuthUserService authUserService;

    public AuthUserController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseDTO> registerUser(@RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO) {
        return ResponseEntity.ok(authUserService.register(userRegistrationRequestDTO));
    }


    @PostMapping("/auth")
    public ResponseEntity<AuthUserLoginResponseDto> login(@RequestBody AuthUserLoginDtoRequest authUserLoginDtoRequest) {
        return ResponseEntity.ok(authUserService.auth(authUserLoginDtoRequest));
    }











//    @PostMapping("/auth")
//    public ResponseEntity<?> authenticateUser(@RequestBody AuthUserLoginDtoRequest authUserLoginDtoRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtTokenProvider.generateToken(authentication);
//
//        return ResponseEntity.ok(new LoginResponse(token, "Login successful"));
    }











//    @PostMapping("/register")
//    public ResponseEntity<UserRegistrationResponseDTO> registerUser(@RequestBody UserRegistrationRequestDTO requestDTO) {
//        UserRegistrationResponseDTO responseDTO = userAuthService.register(requestDTO);
//        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
//    }


//    @PostMapping("/register")
//    public ResponseEntity<UserRegistrationResponseDTO> registerUser(@Valid @RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO) {
//        UserRegistrationResponseDTO responseDTO = userService.registerUser(userRegistrationRequestDTO);
//        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
//    }

//    @GetMapping("/login")
//    public ResponseEntity<AppUser> loginUser(@RequestParam String username, @RequestParam String password) {
