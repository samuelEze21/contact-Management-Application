package com.semicolon.contact.Management.App.ComponentsTest;

import com.semicolon.contact.Management.App.Dto.requests.UserLoginRequestDTO;
import com.semicolon.contact.Management.App.Dto.requests.UserRegistrationRequestDTO;
import com.semicolon.contact.Management.App.Dto.responses.UserLoginResponseDTO;
import com.semicolon.contact.Management.App.Dto.responses.UserRegistrationResponseDTO;
import com.semicolon.contact.Management.App.Exception.EmailAlreadyExistsException;
import com.semicolon.contact.Management.App.Exception.InvalidCredentialsException;
import com.semicolon.contact.Management.App.Exception.UserNotFoundException;
import com.semicolon.contact.Management.App.models.entities.User;
import com.semicolon.contact.Management.App.models.repositories.UserRepository;
import com.semicolon.contact.Management.App.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void test_that_New_User_can_create_account_on_contactApp() {
        UserRegistrationRequestDTO requestDTO = new UserRegistrationRequestDTO();
        requestDTO.setEmail("chinedu@gmail.com");
        requestDTO.setPassword("123456");
        requestDTO.setFirstName("Chinedu");
        requestDTO.setLastName("Kalu");
        requestDTO.setPhone("+234 701 023 4744");
        UserRegistrationResponseDTO responseDTO = userService.registerUser(requestDTO);

        assertNotNull(responseDTO);
        assertEquals("chinedu@gmail.com", responseDTO.getEmail());
        assertEquals("123456", responseDTO.getPassword());
        assertEquals("Chinedu", responseDTO.getFirstName());
        assertEquals("Kalu", responseDTO.getLastName());
        assertEquals("+234 701 023 4744", responseDTO.getPhone());
        assertEquals("Registration Completed Successfully", responseDTO.getMessage());

        User savedUser = userRepository.findByEmail("chinedu@gmail.com").orElse(null);
        assertNotNull(savedUser);
        assertEquals("chinedu@gmail.com", savedUser.getEmail());
        assertEquals("123456", savedUser.getPassword());
        assertEquals("Chinedu", savedUser.getFirstName());
        assertEquals("Kalu", savedUser.getLastName());
        assertEquals("+234 701 023 4744", savedUser.getPhone());
    }

    @Test
    void test_that_two_users_can_register_repositoryUser_count_is_2(){
        UserRegistrationRequestDTO requestDTO = new UserRegistrationRequestDTO();
        requestDTO.setEmail("chinedu@gmail.com");
        requestDTO.setPassword("123456");
        requestDTO.setFirstName("Chinedu");
        requestDTO.setLastName("Kalu");
        requestDTO.setPhone("+234 701 023 4744");
        UserRegistrationResponseDTO responseDTO = userService.registerUser(requestDTO);
        assertNotNull(responseDTO);
        assertEquals(1, userRepository.count());

        UserRegistrationRequestDTO User2Request = new UserRegistrationRequestDTO();
        User2Request.setEmail("chinedu2@gmail.com");
        User2Request.setPassword("123456");
        User2Request.setFirstName("Chinedu");
        User2Request.setLastName("Kalu");
        User2Request.setPhone("+234 701 023 4744");
        UserRegistrationResponseDTO responseDTO2 = userService.registerUser(User2Request);
        assertNotNull(responseDTO2);
        assertEquals(2, userRepository.count());
    }


    @Test
    void test_That_registered_user_can_log_In_to_contactManagement_App() {
        UserRegistrationRequestDTO requestDTO = new UserRegistrationRequestDTO();
        requestDTO.setEmail("chinedu@gmail.com");
        requestDTO.setPassword("123456");
        requestDTO.setFirstName("Chinedu");
        requestDTO.setLastName("Kalu");
        requestDTO.setPhone("+234 701 023 4744");
        UserRegistrationResponseDTO responseDTO = userService.registerUser(requestDTO);
        assertNotNull(responseDTO);

        UserLoginRequestDTO LoginRequestDTO = new UserLoginRequestDTO();
        LoginRequestDTO.setEmail("chinedu@gmail.com");
        LoginRequestDTO.setPassword("123456");
        LoginRequestDTO.setUserName("Chinedu");
        UserLoginResponseDTO LoginResponseDTO = userService.loginUser(LoginRequestDTO);
        assertNotNull(LoginResponseDTO);
        assertEquals("chinedu@gmail.com", LoginResponseDTO.getEmail());
        assertEquals("123456", LoginResponseDTO.getPassword());
        assertEquals("Chinedu", LoginResponseDTO.getUserName());
        assertEquals("Login Successfully", LoginResponseDTO.getMessage());
        assertEquals(1, userRepository.count());

    }

    @Test
    void test_logging_in_with_unRegistered_email() {
        UserLoginRequestDTO requestDTO = new UserLoginRequestDTO();
        requestDTO.setEmail("001@gmail.com");
        requestDTO.setPassword("11112");
        requestDTO.setUserName("Nonexist");
        UserNotFoundException thrown = assertThrows(UserNotFoundException.class, () -> userService.loginUser(requestDTO));
        assertEquals("Account with this email does not exist, Try To Register an Account", thrown.getMessage());
    }


    @Test
    void test_that_NewUser_can_not_login_with_incorrect_password() {
        UserRegistrationRequestDTO requestDTO = new UserRegistrationRequestDTO();
        requestDTO.setEmail("chinedu@gmail.com");
        requestDTO.setPassword("123456");
        requestDTO.setFirstName("Chinedu");
        requestDTO.setLastName("Kalu");
        requestDTO.setPhone("+234 701 023 4744");
        UserRegistrationResponseDTO responseDTO = userService.registerUser(requestDTO);
        assertNotNull(responseDTO);

        UserLoginRequestDTO LoginRequestDTO = new UserLoginRequestDTO();
        LoginRequestDTO.setEmail("chinedu@gmail.com");
        LoginRequestDTO.setPassword("1255456");
        LoginRequestDTO.setUserName("Chinedu");
        InvalidCredentialsException thrown = assertThrows(InvalidCredentialsException.class, () -> userService.loginUser(LoginRequestDTO));
        assertEquals("Invalid password, please try again!", thrown.getMessage());
    }

    @Test
    void test_that_registration_of_new_user_with_AlreadyRegistered_existing_email_not_Successful(){
        UserRegistrationRequestDTO requestDTO = new UserRegistrationRequestDTO();
        requestDTO.setEmail("bigBenz@example.com");
        requestDTO.setPassword("123456");
        requestDTO.setFirstName("Benzema");
        requestDTO.setLastName("Francis");
        requestDTO.setPhone("+234 701 023 0000");
        UserRegistrationResponseDTO responseDTO = userService.registerUser(requestDTO);
        assertNotNull(responseDTO);

        UserRegistrationRequestDTO User2Request = new UserRegistrationRequestDTO();
        User2Request.setEmail("bigBenz@example.com");
        User2Request.setPassword("password");
        User2Request.setFirstName("Benson");
        User2Request.setLastName("Kalu");
        User2Request.setPhone("+234 701 023 1234");
        EmailAlreadyExistsException thrown = assertThrows(EmailAlreadyExistsException.class, () -> userService.registerUser(User2Request));
        assertEquals("User already exists", thrown.getMessage());
    }

}
