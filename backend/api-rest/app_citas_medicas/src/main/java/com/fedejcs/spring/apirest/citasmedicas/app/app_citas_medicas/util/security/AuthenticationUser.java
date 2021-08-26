package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationUser implements UserDetails
{
    private String nickName;
    private String password;
    @Email
    private String email;   
    private Collection < ? extends GrantedAuthority > authorities;

    public AuthenticationUser(){}
    public AuthenticationUser(String nickName , String password , String email , Collection< ? extends GrantedAuthority > authorities ) 
    {
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public static AuthenticationUser build( User user )
    {
        List < GrantedAuthority > authoritiesUser = new ArrayList< GrantedAuthority >();
        authoritiesUser.add( new SimpleGrantedAuthority( "ROL_".concat( user.getType().getName() ) ) );
        return new AuthenticationUser( 
                                     user.getNick()
                                    ,user.getPassword()
                                    ,user.getEmail()
                                    ,authoritiesUser
                                );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return this.authorities; }

    @Override
    public String getPassword() { return this.password; }
    
    @Override
    public String getUsername() { return this.nickName; }

    public String getEmail(){ return this.email; }
    
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; } 

    
}
