package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IUserDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.User;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements IUserService
{
    @Autowired
    private IUserDAO userRepository;

    @Override
    @Transactional( readOnly = true )
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional( readOnly = true )
    public User findById(Long id) {
        return userRepository.findById( id ).orElse( null );
    }

    @Transactional( readOnly = true )
    public User findByNick( String nick ) {
        return userRepository.findByNick( nick );
    }

    @Transactional( readOnly = true )
    public User findByNickOrEmail( String nick , String email ) {
        return userRepository.findByNickOrEmail( nick , email );
    }

    @Transactional( readOnly = true )
    public boolean existsByNick( String nick ){
        return userRepository.existsByNick( nick );
    }

    @Transactional( readOnly = true )
    public boolean existsByEmail( String email ){
        return userRepository.existsByEmail( email );
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save( user );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById( id );
    }
    
}
