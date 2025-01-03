package com.semicolon.contact.Management.App.Dto.requests;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

//@Validated
public class UserLoginRequestDTO {

//    @NotBlank(message = "Email can not be empty")
//    @Email(message = "Email should be valid")
//    @Valid
    private String email;

//    @NotBlank(message = "Password can not be empty")
//    @Email(message = "Password should be valid")
//    @Valid
    private String password;

    private String userName;




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
