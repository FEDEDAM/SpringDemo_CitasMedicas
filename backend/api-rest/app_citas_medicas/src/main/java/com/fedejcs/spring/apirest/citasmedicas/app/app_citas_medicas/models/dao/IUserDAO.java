package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDAO extends JpaRepository< User , Long >
{
    public User findByNick( String nick );
    public User findByNickOrEmail( String nick , String email );
    public boolean existsByNick( String nickOrEmail );
    public boolean existsByEmail( String email );
}
