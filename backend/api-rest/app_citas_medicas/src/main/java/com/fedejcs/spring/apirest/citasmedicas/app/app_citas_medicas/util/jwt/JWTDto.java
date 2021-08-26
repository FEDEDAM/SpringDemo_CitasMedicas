package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JWTDto 
{
    private String token;    
    private String bearer;
    private String userName;
    private Collection< ? extends GrantedAuthority > authorities;

    public JWTDto(String token , String userName , Collection< ? extends GrantedAuthority > authorities) 
    {
        this.token = token;
        this.userName = userName;
        this.authorities = authorities;
        this.bearer = "Bearer";
    }
    
    public String getToken() {return this.token;}
    public void setToken(String token) {this.token = token;}

    public String getBearer() {return this.bearer;}
    public void setBearer(String bearer) {this.bearer = bearer;}

    public String getUserName() {return this.userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public Collection< ? extends GrantedAuthority > getAuthorities() {return this.authorities;}
    public void setAuthorities(Collection< ? extends GrantedAuthority > authorities) {this.authorities = authorities;}

}
