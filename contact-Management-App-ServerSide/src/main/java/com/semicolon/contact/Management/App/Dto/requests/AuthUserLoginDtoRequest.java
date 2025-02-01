package com.semicolon.contact.Management.App.Dto.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AuthUserLoginDtoRequest {

    @NotBlank(message = "username is required")
    @Valid
    private String username;

    @NotBlank(message = "Password can not be empty")
    @Email(message = "Password should be valid")
    @Valid
    private String password;

    private String email;

    public @NotBlank(message = "username is required") @Valid String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "username is required") @Valid String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password can not be empty") @Email(message = "Password should be valid") @Valid String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password can not be empty") @Email(message = "Password should be valid") @Valid String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
