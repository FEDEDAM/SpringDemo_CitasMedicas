package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.dto;

import javax.validation.constraints.NotBlank;

public class LoginUser 
{
    @NotBlank
    private String userName;    

    @NotBlank
    private String password;  

    public String getUserName() {return this.userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public String getPassword() {return this.password;}
    public void setPassword(String password) {this.password = password;}

}
