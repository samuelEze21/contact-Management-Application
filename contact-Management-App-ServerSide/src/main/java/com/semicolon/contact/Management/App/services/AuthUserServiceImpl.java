package com.semicolon.contact.Management.App.services;

import com.semicolon.contact.Management.App.Dto.requests.AuthUserLoginDtoRequest;
import com.semicolon.contact.Management.App.Dto.requests.UserRegistrationRequestDTO;
import com.semicolon.contact.Management.App.Dto.responses.AuthUserLoginResponseDto;
import com.semicolon.contact.Management.App.Dto.responses.UserRegistrationResponseDTO;
import com.semicolon.contact.Management.App.Exception.EmailAlreadyExistsException;
import com.semicolon.contact.Management.App.models.entities.AppRole;
import com.semicolon.contact.Management.App.models.entities.Role;
import com.semicolon.contact.Management.App.models.entities.User;
import com.semicolon.contact.Management.App.repositories.RoleRepository;
import com.semicolon.contact.Management.App.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;  // Add this
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthUserServiceImpl(UserRepository userRepository,
                               RoleRepository roleRepository,  // Add this
                               PasswordEncoder passwordEncoder,
                               UserDetailsService userDetailsService,
                               JwtService jwtService,
                               AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;  // Add this
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
        registeredUser.setUsername(userRegistrationRequestDTO.getUsername());

        // Set required non-null fields
        registeredUser.setFirstName(userRegistrationRequestDTO.getFirstName());
        registeredUser.setLastName(userRegistrationRequestDTO.getLastName());
        registeredUser.setPhone(userRegistrationRequestDTO.getPhone());

        // Add default role
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(AppRole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        roles.add(userRole);
        registeredUser.setRoles(roles);

        User savedUser = userRepository.save(registeredUser);

        String token = jwtService.generateJwtToken(savedUser.getUsername());

        UserRegistrationResponseDTO responseDTO = new UserRegistrationResponseDTO();
        responseDTO.setMessage("User registered successfully");
        responseDTO.setToken(token);
        return responseDTO;
    }


    //    registeredUser.setRoles(List.of(String.valueOf(Role.USER)));



    public AuthUserLoginResponseDto auth(AuthUserLoginDtoRequest authUserLoginDtoRequest) {
        // First authenticate the user
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authUserLoginDtoRequest.getUsername(),
                authUserLoginDtoRequest.getPassword()));

        // Then load the user details
        User user = (User) userDetailsService.loadUserByUsername(authUserLoginDtoRequest.getUsername());

        // Generate token
        String token = jwtService.generateJwtToken(user.getUsername());

        AuthUserLoginResponseDto responseDTO = new AuthUserLoginResponseDto();
        responseDTO.setToken(token);
        responseDTO.setMessage("Logged in successfully");
        return responseDTO;
    }
}



// add session to login; //getuser, updateuser, deleteuSER


