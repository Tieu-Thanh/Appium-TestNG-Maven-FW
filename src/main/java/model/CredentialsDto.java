package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CredentialsDto {
    private String email;
    private String password;
    private String confirmPassword;

    @JsonCreator
    public CredentialsDto(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("confirmPassword") String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
