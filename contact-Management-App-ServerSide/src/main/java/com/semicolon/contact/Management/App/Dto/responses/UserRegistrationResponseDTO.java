package com.semicolon.contact.Management.App.Dto.responses;
import java.util.List;

public class UserRegistrationResponseDTO {
        private long id;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String phone;
        private String username;
        private String message;
        private String token;

        public List<String> getRoles() {
                return roles;
        }

        public void setRoles(List<String> roles) {
                this.roles = roles;
        }

        private List<String> roles;

        public String getToken() {
                return token;
        }

        public void setToken(String token) {
                this.token = token;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }


        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }
}
