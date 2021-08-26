package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RegisterUser 
{
    @NotBlank
    private String userName;    

    @NotBlank
    private String password;  

    @NotBlank
    @Email
    private String email;  

    private Set< String > roles = new HashSet<>();


    public String getUserName() {return this.userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public String getPassword() {return this.password;}
    public void setPassword(String password) {this.password = password;}

    public String getEmail() {return this.email;}
    public void setEmail(String email) {this.email = email;}

    public Set<String> getRoles() {return this.roles;}
    public void setRoles(Set<String> roles) {this.roles = roles;}


}
