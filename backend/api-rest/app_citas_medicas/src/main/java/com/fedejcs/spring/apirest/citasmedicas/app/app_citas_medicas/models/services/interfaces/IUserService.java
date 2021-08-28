package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.User;

public interface IUserService 
{
    public List< User > findAll();

    public User findById ( Long id );

    public User findByNick( String nick );

    public User findByNickOrEmail( String nick , String email );

    public boolean existsByNick( String nick );

    public boolean existsByEmail( String email );

    public User save ( User user );

    public void deleteById ( Long id );

    

    
}
