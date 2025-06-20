//package com.semicolon.contact.Management.App.services;
//
//import com.semicolon.contact.Management.App.Dto.requests.UserLoginRequestDTO;
//import com.semicolon.contact.Management.App.Dto.requests.UserRegistrationRequestDTO;
//import com.semicolon.contact.Management.App.Dto.responses.UserLoginResponseDTO;
//import com.semicolon.contact.Management.App.Dto.responses.UserRegistrationResponseDTO;
//import com.semicolon.contact.Management.App.Exception.EmailAlreadyExistsException;
//import com.semicolon.contact.Management.App.Exception.InvalidCredentialsException;
//import com.semicolon.contact.Management.App.Exception.UserNotFoundException;
//import com.semicolon.contact.Management.App.models.entities.User;
//import com.semicolon.contact.Management.App.models.repositories.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserService {
//    private final UserRepository userRepository;
//
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserRegistrationResponseDTO registerUser(UserRegistrationRequestDTO requestDTO) {
//        Optional<User> existingUser = userRepository.findByEmail(requestDTO.getEmail());
//        if (existingUser.isPresent()) {
//            throw new EmailAlreadyExistsException("User already exists");
//        }
//        User newUser = new User();
//        newUser.setEmail(requestDTO.getEmail());
//        newUser.setPassword(requestDTO.getPassword());
//        newUser.setFirstName(requestDTO.getFirstName());
//        newUser.setLastName(requestDTO.getLastName());
//        newUser.setPhone(requestDTO.getPhone());
//        User savedUser = userRepository.save(newUser);
//
//        UserRegistrationResponseDTO responseDTO = new UserRegistrationResponseDTO();
//        responseDTO.setId(savedUser.getId());
//        responseDTO.setEmail(savedUser.getEmail());
//        responseDTO.setPassword(savedUser.getPassword());
//        responseDTO.setFirstName(savedUser.getFirstName());
//        responseDTO.setLastName(savedUser.getLastName());
//        responseDTO.setPhone(savedUser.getPhone());
//        responseDTO.setMessage("Registration Completed Successfully");
//        return responseDTO;
//    }
//
//    @Override
//    public UserLoginResponseDTO loginUser(UserLoginRequestDTO requestDTO) throws UserNotFoundException {
//        Optional<User> existingUser = userRepository.findByEmail(requestDTO.getEmail());
//        if (!existingUser.isPresent()) {
//            throw new UserNotFoundException("Account with this email does not exist, Try To Register an Account");
//    }
//
//            User user = existingUser.get();
//        if (!user.getPassword().equals(requestDTO.getPassword())) {
//            throw new InvalidCredentialsException("Invalid password, please try again!");
//        }
//            UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
//            responseDTO.setId(user.getId());
//            responseDTO.setEmail(user.getEmail());
//            responseDTO.setPassword(user.getPassword());
//            responseDTO.setUserName(user.getFirstName());
//            responseDTO.setMessage("Login Successfully");
//            return responseDTO;
//        }
//
//    }
//
//
//
//
//
