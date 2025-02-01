package com.semicolon.contact.Management.App.services;

import com.semicolon.contact.Management.App.Dto.requests.AuthUserLoginDtoRequest;
import com.semicolon.contact.Management.App.Dto.requests.UserRegistrationRequestDTO;
import com.semicolon.contact.Management.App.Dto.responses.AuthUserLoginResponseDto;
import com.semicolon.contact.Management.App.Dto.responses.UserRegistrationResponseDTO;
import com.semicolon.contact.Management.App.Exception.EmailAlreadyExistsException;
import com.semicolon.contact.Management.App.models.entities.Role;
import com.semicolon.contact.Management.App.models.entities.User;
import com.semicolon.contact.Management.App.models.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthUserServiceImpl(UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               UserDetailsService userDetailsService,
                               JwtService jwtService, AuthenticationManager
                                       authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    @Transactional
    public UserRegistrationResponseDTO register(UserRegistrationRequestDTO userRegistrationRequestDTO) {
        if (userRepository.findByEmail(userRegistrationRequestDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(
                    String.format("Email already exists: %s", userRegistrationRequestDTO.getEmail())
            );
        }

        User registeredUser = new User();
        registeredUser.setEmail(userRegistrationRequestDTO.getEmail());
        registeredUser.setPassword(passwordEncoder.encode(userRegistrationRequestDTO.getPassword()));
        registeredUser.setFirstName(userRegistrationRequestDTO.getFirstName());
        registeredUser.setLastName(userRegistrationRequestDTO.getLastName());
        registeredUser.setUsername(userRegistrationRequestDTO.getUsername());
        registeredUser.setPhone(userRegistrationRequestDTO.getPhone());
        userRepository.save(registeredUser);

        String token = jwtService.generateJwtToken(registeredUser.getUsername());

        UserRegistrationResponseDTO responseDTO = new UserRegistrationResponseDTO();
        responseDTO.setId(registeredUser.getId());
        responseDTO.setEmail(registeredUser.getEmail());
        responseDTO.setFirstName(registeredUser.getFirstName());
        responseDTO.setLastName(registeredUser.getLastName());
        responseDTO.setPhone(registeredUser.getPhone());
        responseDTO.setUsername(registeredUser.getUsername());
        responseDTO.setMessage("User registered successfully");
        responseDTO.setRoles(List.of(String.valueOf(Role.USER)));
        responseDTO.setToken(token);

        return responseDTO;
    }


    //    registeredUser.setRoles(List.of(String.valueOf(Role.USER)));



    public AuthUserLoginResponseDto auth(AuthUserLoginDtoRequest authUserLoginDtoRequest) {
        User user = (User) userDetailsService.loadUserByUsername(authUserLoginDtoRequest.getUsername());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authUserLoginDtoRequest.getUsername(),
                authUserLoginDtoRequest.getPassword()));
        String token = jwtService.generateJwtToken(user.getUsername());

        AuthUserLoginResponseDto responseDTO = new AuthUserLoginResponseDto();
        responseDTO.setToken(token);
        responseDTO.setMessage("Logged in successfully");
        return responseDTO;
    }
}



// add session to login; //getuser, updateuser, deleteuSER















//public AuthUserLoginResponseDto auth(AuthUserLoginDtoRequest authUserLoginDtoRequest) {
//    // Check if username or password is null or empty
//    if (authUserLoginDtoRequest.getUsername() == null || authUserLoginDtoRequest.getUsername().isBlank()) {
//        throw new IllegalArgumentException("Username cannot be null or empty.");
//    }
//    if (authUserLoginDtoRequest.getPassword() == null || authUserLoginDtoRequest.getPassword().isBlank()) {
//        throw new IllegalArgumentException("Password cannot be null or empty.");
//    }
//
//    User user;
//    try {
//        // Attempt to load the user by username
//        user = (User) userDetailsService.loadUserByUsername(authUserLoginDtoRequest.getUsername());
//    } catch (UsernameNotFoundException e) {
//        throw new UsernameNotFoundException("Invalid username or user does not exist.");
//    }
//



//    try {
//        // Authenticate username and password
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                authUserLoginDtoRequest.getUsername(),
//                authUserLoginDtoRequest.getPassword()));
//    } catch (BadCredentialsException e) {
//        throw new BadCredentialsException("Invalid password. Please try again.");
//    }
//
//    // Generate the JWT token
//    String token = jwtService.generateJwtToken(user.getUsername());
//
//    // Create the response DTO
//    AuthUserLoginResponseDto responseDTO = new AuthUserLoginResponseDto();
//    responseDTO.setToken(token);
//    responseDTO.setMessage("Logged in successfully");
//    return responseDTO;
//}



// updateUser, getuser; deleteUser

//    private User mapToUser(UserRegistrationRequestDTO userRegistrationRequestDTO) {
//        return User.builder()
//                .username(userRegistrationRequestDTO.getUsername())
//                .firstName(userRegistrationRequestDTO.getFirstName())
//                .lastName(userRegistrationRequestDTO.getLastName())
//                .password(passwordEncoder.encode(userRegistrationRequestDTO.getPassword()))
//                .roles(List.of(Role.USER.name()))
//                .build();
//    }



















//@Override
//@Transactional
//public UserRegistrationResponseDTO register(UserRegistrationRequestDTO userRegistrationRequestDTO) {
//    if (userRepository.findByEmail(userRegistrationRequestDTO.getEmail()).isPresent()) {
//        throw new EmailAlreadyExistsException(STR."Email already exists: \{userRegistrationRequestDTO.getEmail()}");
//    }
//    User registeredUser = new User();
//    registeredUser.setEmail(userRegistrationRequestDTO.getEmail());
//    registeredUser.setPassword(passwordEncoder.encode(userRegistrationRequestDTO.getPassword()));
//    registeredUser.setFirstName(userRegistrationRequestDTO.getFirstName());
//    registeredUser.setLastName(userRegistrationRequestDTO.getLastName());
//    registeredUser.setUsername(userRegistrationRequestDTO.getEmail());
//    registeredUser.setPhone(userRegistrationRequestDTO.getPhone());
//    registeredUser.setRoles(List.of(String.valueOf(Role.USER)));
//    userRepository.save(registeredUser);
//
//    UserRegistrationResponseDTO responseDTO = new UserRegistrationResponseDTO();
//    responseDTO.setId(registeredUser.getId());
//    responseDTO.setEmail(registeredUser.getEmail());
//    responseDTO.setFirstName(registeredUser.getFirstName());
//    responseDTO.setLastName(registeredUser.getLastName());
//    responseDTO.setPhone(registeredUser.getPhone());
//    responseDTO.setUsername(registeredUser.getUsername());
//    responseDTO.setMessage("User registered successfully");
//    return responseDTO;
//}
//



//
//    // Login implementation with edge case handling
//    @Override
//    public Optional<User> login(String username, String password) throws InvalidCredentialsException {
//        // Check if the user exists
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        if (userOptional.isEmpty()) {
//            throw new InvalidCredentialsException("Invalid username or password.");
//        }
//
//        // Validate the password
//        User user = userOptional.get();
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new InvalidCredentialsException("Invalid username or password.");
//        }
//
//        return userOptional;
//    }
//
//    // Logout implementation
//    @Override
//    public String logout() {
//        // Token-based or session management can be handled here
//        // Example: Invalidate the user session or JWT token
//        return "User successfully logged out.";
//    }
//}
